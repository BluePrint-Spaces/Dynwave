package org.blueprintspaces.dynwave.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.blueprintspaces.dynwave.entity.MovingEntity;
import org.blueprintspaces.dynwave.init.EntityInit;

public class ItemSpinningBarSpwaner extends Item
{

    public ItemSpinningBarSpwaner(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        Direction direction = context.getSide();

        if (!world.isClient()) {
            BlockPos spawnPos = pos.offset(direction);


        }

        return ActionResult.SUCCESS;
    }
}
