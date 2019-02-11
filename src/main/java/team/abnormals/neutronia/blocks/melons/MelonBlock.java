package team.abnormals.neutronia.blocks.melons;

import net.fabricmc.fabric.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import team.abnormals.neutronia.blocks.BaseModBlock;
import team.abnormals.neutronia.blocks.INeutroniaBlock;
import team.abnormals.neutronia.enums.CarvedFaceTypes;
import team.abnormals.neutronia.init.NBlocks;
import team.abnormals.neutronia.utils.helpers.CarvedBlockHelper;
import team.abnormals.neutronia.utils.helpers.ICarvable;

public class MelonBlock extends BaseModBlock implements INeutroniaBlock, ICarvable {

    public static final DirectionProperty FACING = Properties.FACING_HORIZONTAL;

    public MelonBlock(Identifier identifier) {
        super(FabricBlockSettings.of(Material.PUMPKIN).hardness(1.0F).resistance(1.0F).sounds(BlockSoundGroup.WOOD).build(), identifier.getPath());
        this.setDefaultState(this.stateFactory.getDefaultState().with(FACING, Direction.NORTH));
    }

    public MelonBlock() {
        super(FabricBlockSettings.of(Material.PUMPKIN).hardness(1.0F).resistance(1.0F).sounds(BlockSoundGroup.WOOD).build(), "carved_melon");
        this.setDefaultState(this.stateFactory.getDefaultState().with(FACING, Direction.NORTH));
        CarvedBlockHelper.init(this);
    }

    @Override
    public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {
        ItemStack stack = playerEntity_1.getStackInHand(hand_1);
        if (!world_1.isClient) {
            if (stack.getItem() == Items.SHEARS)
                world_1.setBlockState(blockPos_1, playerEntity_1.isSneaking() ?
                        CarvedBlockHelper.getLast((ICarvable) getUncarvedBlock(), Registry.BLOCK.getId(this)) :
                        CarvedBlockHelper.getNext((ICarvable) getUncarvedBlock(), Registry.BLOCK.getId(this)));
        }
        return super.activate(blockState_1, world_1, blockPos_1, playerEntity_1, hand_1, blockHitResult_1);
    }

    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        return this.getDefaultState().with(FACING, itemPlacementContext_1.getPlayerHorizontalFacing().getOpposite());
    }

    public BlockState rotate(BlockState blockState_1, Rotation rotation_1) {
        return blockState_1.with(FACING, rotation_1.rotate(blockState_1.get(FACING)));
    }

    public BlockState mirror(BlockState blockState_1, Mirror mirror_1) {
        return blockState_1.rotate(mirror_1.getRotation(blockState_1.get(FACING)));
    }

    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.with(FACING);
    }

    @Override
    public String getFormatString() {
        return "carved_%s_melon";
    }

    @Override
    public CarvedFaceTypes fromIdentifier(Identifier identifier) {
        String[] values = identifier.getPath().split("_");
        if(values.length == 2){
            return null;
        }
        return CarvedFaceTypes.valueOf(values[1].toUpperCase());
    }

    @Override
    public ICarvable newInstance(Identifier identifier) {
        return new MelonBlock(identifier);
    }

    @Override
    public Block getUncarvedBlock() {
        return NBlocks.MELON;
    }

}