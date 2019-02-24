package team.abnormals.neutronia.blocks;

import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import team.abnormals.neutronia.INeutroniaInfo;

public class NeutroniaBaseBlock extends BaseModBlock implements INeutroniaInfo {

    public NeutroniaBaseBlock(Material material, String name) {
        super(material, name);
    }

    public NeutroniaBaseBlock(Settings builder, String name) {
        super(builder, name);
    }

    public NeutroniaBaseBlock(Material material, String name, float hardness, float resistant) {
        super(Settings.of(material).strength(hardness, resistant), name);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isSimpleFullBlock(BlockState blockState, BlockView blockView, BlockPos blockPos) {
        return false;
    }

}
