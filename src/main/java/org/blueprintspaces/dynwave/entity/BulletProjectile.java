package org.blueprintspaces.dynwave.entity;

import net.minecraft.block.AbstractBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.blueprintspaces.dynwave.init.EntityInit;

import java.util.Objects;

public class BulletProjectile extends PersistentProjectileEntity {
    private static final TrackedData<Boolean> HIT =
            DataTracker.registerData(BulletProjectile.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int counter = 0;

    public BulletProjectile(EntityType<? extends PersistentProjectileEntity> type, World world, ItemStack stack) {
        super(type, world, stack);
        setOwner(getOwner());
        BlockPos blockpos = Objects.requireNonNull(getOwner()).getBlockPos();
        double d0 = (double)blockpos.getX() + 0.5D;
        double d1 = (double)blockpos.getY() + 1.75D;
        double d2 = (double)blockpos.getZ() + 0.5D;
        this.refreshPositionAndAngles(d0, d1, d2, this.getYaw(), this.getPitch());
    }

    public BulletProjectile(EntityType<BulletProjectile> bulletProjectileEntityType, World world)
    {
        super(bulletProjectileEntityType,world,ItemStack.EMPTY);
    }


    @Override
    public void tick() {
        super.tick();
        if(this.inGround) {
            this.discard();
        }

        if(this.dataTracker.get(HIT)) {
            if(this.age >= counter) {
                this.discard();
            }
        }

        if (this.age >= 300) {
            this.remove(RemovalReason.DISCARDED);
        }

        Vec3d vec3 = this.getVelocity();
        HitResult hitresult = ProjectileUtil.getCollision(this, this::canHit);
        if (hitresult.getType() != HitResult.Type.MISS)
            this.onCollision(hitresult);

        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
        this.updateRotation();

        double d5 = vec3.x;
        double d6 = vec3.y;
        double d7 = vec3.z;

        for(int i = 1; i < 5; ++i) {
            //TODO::PARTICLES
//            this.getWorld().addParticle(ParticleDa.PINK_GARNET_PARTICLE, d0-(d5*2), d1-(d6*2), d2-(d7*2),
//                    -d5, -d6 - 0.1D, -d7);
        }
        for (int i = 0; i < 5; ++i) {
            double trailX = d0 - (vec3.x * 0.2 * i);
            double trailY = d1 - (vec3.y * 0.2 * i);
            double trailZ = d2 - (vec3.z * 0.2 * i);
            this.getWorld().addParticle(ParticleTypes.SPLASH, trailX, trailY, trailZ, 0, 0, 0);
        }
        if (this.getWorld().getStatesInBox(this.getBoundingBox()).noneMatch(AbstractBlock.AbstractBlockState::isAir)) {
            this.discard();
        } else if (this.isInsideWaterOrBubbleColumn()) {
            this.discard();
        } else {
            this.setVelocity(vec3.multiply(0.99F));
            this.setPos(d0, d1, d2);
        }
    }
    @Override
    protected ItemStack asItemStack() {
        return ItemStack.EMPTY;
    }


    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if(this.getWorld().isClient()) {
            return;
        }

        if(hitResult.getType() == HitResult.Type.ENTITY && hitResult instanceof EntityHitResult entityHitResult) {
            Entity hit = entityHitResult.getEntity();
            Entity owner = this.getOwner();

            if(owner != hit) {
                this.dataTracker.set(HIT, true);
                counter = this.age + 5;
            }
        } else if(hitResult.getType() == HitResult.Type.BLOCK) {
            this.dataTracker.set(HIT, true);
            counter = this.age + 5;
        }
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }
    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        if(entity instanceof LivingEntity)
        {
            ((LivingEntity) entity).takeKnockback(3,entity.getX(),entity.getZ());
        }

    }
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(HIT, false);
    }
}
