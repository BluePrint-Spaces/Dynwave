package org.blueprintspaces.dynwave.init;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.blueprintspaces.dynwave.Dynwave;
import org.blueprintspaces.dynwave.items.ItemBullets;
import org.blueprintspaces.dynwave.items.ItemPoolNoodles;
import org.blueprintspaces.dynwave.items.ItemSpinningBarSpwaner;
import org.blueprintspaces.dynwave.items.ItemWaterGun;

public class ItemInit
{

    public static final Item ITEM_POOL_NOODLES = registerItem("pool_noodles",
            new ItemPoolNoodles(new Item.Settings()));

    public static final Item ITEM_SPINNING_BAR_SPAWNER = registerItem("spinning_bar_spawner",
            new ItemSpinningBarSpwaner(new Item.Settings()));

    public static final Item ITEM_WATER_BULLET = registerItem("water_bullet",
            new ItemBullets(new Item.Settings(),256));

    public static final Item ITEM_WATER_GUN = registerItem("water_gun",
            new ItemWaterGun(new Item.Settings(),64, (ItemBullets) ITEM_WATER_BULLET.asItem()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Dynwave.MOD_ID, name), item);

    }

    public static void registerModItems() {
        Dynwave.LOGGER.info("Registering ModItems for " + Dynwave.MOD_ID);
    }

}
