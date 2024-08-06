package org.blueprintspaces.dynwave.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.UUID;

public class MovingEntity extends Entity {

    public MovingEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
    @Override
    public void tick() {
        super.tick();

        if (!this.getWorld().isClient) {
            for (PlayerEntity player : this.getWorld().getPlayers()) {
                if (this.squaredDistanceTo(player) < 1.0D) {
                    pushPlayer(player);
                }
            }
        }
        Vec3d pos = this.getPos();
        //this.setBoundingBox(new Box(pos.x - 1.0, pos.y - 1.0, pos.z - 1.0, pos.x + 1.0, pos.y + 1.0, pos.z + 1.0));
    }

    private void pushPlayer(PlayerEntity player) {
        Vec3d direction = player.getPos().subtract(this.getPos()).normalize();

        double pushStrength = 1.0D;
        player.addVelocity(direction.x * pushStrength, 0.5D * pushStrength, direction.z * pushStrength);

        player.velocityModified = true;
    }
    @Override
    public boolean isCollidable() {
        return this.isAlive();
    }
}
