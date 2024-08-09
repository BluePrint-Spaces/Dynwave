package org.blueprintspaces.dynwave.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.blueprintspaces.dynwave.Dynwave;

public class DynWaveGroup {
    public static final ItemGroup DYNWAVE_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Dynwave.MOD_ID, "dynwave_group"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.dynwave_group"))
                    .icon(() -> new ItemStack(ItemInit.ITEM_POOL_NOODLES))
                    .entries((displayContext, entries) -> {
                        entries.add(ItemInit.ITEM_POOL_NOODLES);
                        entries.add(ItemInit.ITEM_WATER_GUN);
                        entries.add(ItemInit.ITEM_WATER_BULLET);
                        entries.add(BlocksInit.BLOCK_JUMP_BOOST.asItem());
                        entries.add(BlocksInit.WATER_GEYSER_BLOCK.asItem());
                    })
                    .build()
    );
    public static void registerItemGroups() {

    }
}
