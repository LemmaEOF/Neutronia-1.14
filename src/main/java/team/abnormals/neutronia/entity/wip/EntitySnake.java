package team.abnormals.neutronia.entity.wip;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import team.hdt.neutronia.base.lib.LibMisc;

import java.util.Set;

/**
 * Created by Joseph on 10/2/2018.
 */

public class EntitySnake extends EntityFamiliar {

    private static final double maxHPWild = 8;
    private static final ResourceLocation loot = new ResourceLocation(LibMisc.MOD_ID, "entities/small_snake");
    private static final String[] names = {};
    private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Items.RABBIT, Items.CHICKEN);
    private static final DataParameter<Integer> TINT = EntityDataManager.createKey(EntitySnake.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> SIZE = EntityDataManager.createKey(EntitySnake.class, DataSerializers.VARINT);

    public EntitySnake(World worldIn) {
        super(worldIn);
    }

    public static boolean isSnakeFodder(Entity entity) {
        return entity instanceof RabbitEntity || entity instanceof SpiderEntity || entity instanceof ChickenEntity);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(TINT, 0xFFFFFF);
        dataManager.register(SIZE, 0);
        this.aiSit = new EntityAISit(this);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger(String.valueOf(SIZE), 0);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        compound.getTag(String.valueOf(SIZE));
        super.readFromNBT(compound);
    }

    @Override
    protected void setFamiliarAttributes(boolean isFamiliar) {
        this.getAttributeContainer().get(EntityAttributes.MAX_HEALTH).setBaseValue(isFamiliar ? 20 : maxHPWild);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (this.isEntityInvulnerable(source)) {
            return false;
        }
        if (this.aiSit != null) {
            this.aiSit.setSitting(false);
        }
        return super.attackEntityFrom(source, amount);
    }

    @Override
    public boolean getCanSpawnHere() {
        int i = MathHelper.floor(this.posX);
        int j = MathHelper.floor(this.getEntityBoundingBox().minY);
        int k = MathHelper.floor(this.posZ);
        BlockPos blockpos = new BlockPos(i, j, k);
        Block block = this.world.getBlockState(blockpos.down()).getBlock();
        return block instanceof BlockGrass && this.world.getLight(blockpos) > 8 && super.getCanSpawnHere();
    }

    @Override
    public int getTotalVariants() {
        return 2;
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.tasks.addTask(4, new EntityAIWatchClosest2(this, EntityPlayer.class, 5f, 1f));
        this.tasks.addTask(3, new EntityAIMate(this, 1d));
        this.tasks.addTask(4, this.aiSit);
        this.targetTasks.addTask(4, new EntityAITargetNonTamed<>(this, EntityLivingBase.class, false, EntitySnake::isSnakeFodder));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, false));
    }

    @Override
    protected ResourceLocation getLootTable() {
        return loot;
    }

    @Override
    public boolean canBePushed() {
        return true;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 6;
    }

    //Todo: Allow this snake to actually kill mobs properly
    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.POISON, 2000, 1));
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 2000, 1));
        }
        return super.attackEntityAsMob(entity);
    }

    @Override
    public boolean canMateWith(EntityAnimal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        } else if (!this.isTamed()) {
            return false;
        } else if (!(otherAnimal instanceof EntitySnake)) {
            return false;
        } else {
            EntitySnake entitysnake = (EntitySnake) otherAnimal;

            if (!entitysnake.isTamed()) {
                return false;
            } else if (entitysnake.isSitting()) {
                return false;
            } else {
                return this.isInLove() && entitysnake.isInLove();
            }
        }
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        {
            ItemStack itemstack = player.getHeldItem(hand);

            if (!this.isTamed() && TAME_ITEMS.contains(itemstack.getItem())) {
                if (!player.capabilities.isCreativeMode) {
                    itemstack.shrink(1);
                }

                if (!this.isSilent()) {
                    this.world.playSound(null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_PARROT_EAT, this.getSoundCategory(), 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
                }

                if (!this.world.isRemote) {
                    if (this.rand.nextInt(10) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
                        this.setTamedBy(player);
                        this.playTameEffect(true);
                        this.world.setEntityState(this, (byte) 7);
                    } else {
                        this.playTameEffect(false);
                        this.world.setEntityState(this, (byte) 6);
                    }
                }
                return true;
            }
            return true;
        }
    }

    @Override
    protected void collideWithEntity(Entity entityIn) {
        if (!entityIn.equals(getOwner())) {
            super.collideWithEntity(entityIn);
        }
    }

    @Override
    public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount) {
        if (forSpawnCount && isFamiliar()) {
            return false;
        }
        return super.isCreatureType(type, forSpawnCount);
    }


    @Override
    public boolean isNoDespawnRequired() {
        return super.isNoDespawnRequired() || isFamiliar();
    }

    @Override
    public String[] getRandomNames() {
        return names;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == Items.RABBIT;
    }

    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return new EntitySnake(world);
    }
}