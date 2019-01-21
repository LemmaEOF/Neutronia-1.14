package team.abnormals.neutronia.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.block.BlockItem;
import net.minecraft.util.registry.Registry;

public abstract class BlockModBase extends Block implements IModBlockInfo {

    public BlockModBase(Material material, String name) {
        super(Settings.of(material));

        register(name, this, ItemGroup.BUILDING_BLOCKS);
    }

    public BlockModBase(Settings builder, String name) {
        super(builder);
        register(name, this, ItemGroup.BUILDING_BLOCKS);
    }

    private void register(String name, Block block, ItemGroup tab) {
        Registry.register(Registry.BLOCK, getPrefix() + name, block);
        BlockItem item = new BlockItem(block, new Item.Settings().itemGroup(tab));
        item.registerBlockItemMap(Item.BLOCK_ITEM_MAP, item);
        Registry.register(Registry.ITEM, getPrefix() + name, item);
    }

}