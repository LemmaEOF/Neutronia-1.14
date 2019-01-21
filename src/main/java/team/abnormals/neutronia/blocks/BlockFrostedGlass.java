package team.abnormals.neutronia.blocks;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class BlockFrostedGlass extends BlockModBase implements INeutroniaBlock {

	public BlockFrostedGlass() {
		super(Settings.of(Material.GLASS).strength(3.0F, 10.0F), "frosted_glass");
	}
	
	@Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isSimpleFullBlock(BlockState blockState, BlockView blockView, BlockPos blockPos) {
        return false;
    }

    public boolean isWaterLogged(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
        return true;
    }
    
}