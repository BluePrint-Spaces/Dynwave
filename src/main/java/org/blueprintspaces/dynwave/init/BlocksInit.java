package org.blueprintspaces.dynwave.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.blueprintspaces.dynwave.Dynwave;
import org.blueprintspaces.dynwave.blocks.BlockJumpBoost;
import org.blueprintspaces.dynwave.blocks.BlockWaterGeyser;

public class BlocksInit
{

    public static final Block WATER_GEYSER_BLOCK = registerBlock("water_geyser_block",
            new BlockWaterGeyser(AbstractBlock.Settings.copy(Blocks.STONE)));
    public static final Block BLOCK_JUMP_BOOST = registerBlock("jump_boost_block",
            new BlockJumpBoost(AbstractBlock.Settings.copy(Blocks.STONE)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Dynwave.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Dynwave.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        Dynwave.LOGGER.info("Registering ModBlocks for " + Dynwave.MOD_ID);
    }
}
