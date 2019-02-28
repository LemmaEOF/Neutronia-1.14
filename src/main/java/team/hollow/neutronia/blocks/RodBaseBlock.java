package team.hollow.neutronia.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.ViewableWorld;
import team.hollow.neutronia.INeutroniaInfo;

public class RodBaseBlock extends DirectionalBlock implements INeutroniaInfo {

    protected static final VoxelShape BB_AXIS_Y = Block.createCuboidShape(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);
    protected static final VoxelShape BB_AXIS_Z = Block.createCuboidShape(0.375D, 0.375D, 0.0D, 0.625D, 0.625D, 1.0D);
    protected static final VoxelShape BB_AXIS_X = Block.createCuboidShape(0.0D, 0.375D, 0.375D, 1.0D, 0.625D, 0.625D);

    @SuppressWarnings("unused")
    public RodBaseBlock(String name, boolean emitsLight) {
        super(emitsLight ? FabricBlockSettings.of(Material.STONE).strength(0.3F, 1.0F).lightLevel(13) : FabricBlockSettings.of(Material.STONE), name);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.UP));
    }

    @SuppressWarnings("unused")
    public RodBaseBlock(Material material, String name, boolean emitsLight) {
        super(emitsLight ? FabricBlockSettings.of(material).hardness(0.3F).lightLevel(13) : FabricBlockSettings.of(material), name);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.UP));
    }

    @Override
    public BlockState rotate(BlockState blockState_1, Rotation rotation_1) {
        return blockState_1.with(FACING, rotation_1.rotate(blockState_1.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState blockState_1, Mirror mirror_1) {
        return blockState_1.with(FACING, mirror_1.apply(blockState_1.get(FACING)));
    }

    @Override
    public VoxelShape getRayTraceShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        switch (blockState_1.get(FACING).getAxis()) {
            case X:
            default:
                return BB_AXIS_X;
            case Z:
                return BB_AXIS_Z;
            case Y:
                return BB_AXIS_Y;
        }
    }

    @Override
    public boolean isTranslucent(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        return false;
    }

    @Override
    public boolean isSimpleFullBlock(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        return false;
    }

    @Override
    public boolean canPlaceAt(BlockState blockState_1, ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
        return true;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        Direction direction_1 = itemPlacementContext_1.getFacing();
        BlockState blockState_1 = itemPlacementContext_1.getWorld().getBlockState(itemPlacementContext_1.getBlockPos().offset(direction_1.getOpposite()));
        return blockState_1.getBlock() == this && blockState_1.get(FACING) == direction_1 ? this.getDefaultState().with(FACING, direction_1.getOpposite()) : this.getDefaultState().with(FACING, direction_1);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        stateFactory$Builder_1.with(FACING);
    }

}