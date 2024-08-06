package org.blueprintspaces.dynwave.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.blueprintspaces.dynwave.init.EntityInit;
import org.blueprintspaces.dynwave.init.ItemInit;

public class ThrowablePoolNoodle extends ThrownItemEntity
{

    public static final TrackedData<Boolean> HIT = DataTracker.registerData(ThrowablePoolNoodle.class,
            TrackedDataHandlerRegistry.BOOLEAN);
    public ThrowablePoolNoodle(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThrowablePoolNoodle(LivingEntity livingEntity, World world) {
        super(EntityInit.THROWN_NOODLE_PROJECTILE, livingEntity, world);
    }


    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        PlayerEntity player = ((PlayerEntity) entityHitResult.getEntity());

        if(!getWorld().isClient())
        {
            player.takeKnockback(5.0f,1,1);
        }
    }

    @Override
    public boolean shouldRender(double distance) {
        return super.shouldRender(distance);
    }

    @Override
    protected Item getDefaultItem() {
        return ItemInit.ITEM_POOL_NOODLES.asItem();
    }
}
