package org.blueprintspaces.dynwave.items;

import net.minecraft.item.Item;

public class ItemBullets extends Item {
    private final int maxWaterCapacity;

    public ItemBullets(Settings settings, int waterCapacity) {
        super(settings);
        this.maxWaterCapacity = waterCapacity;
    }

    public int getMaxWaterCapacity() {
        return maxWaterCapacity;
    }
}
