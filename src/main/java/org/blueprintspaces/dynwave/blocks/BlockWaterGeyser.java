package org.blueprintspaces.dynwave.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.UUID;

public class BlockWaterGeyser extends Block
{
    public BlockWaterGeyser(Settings properties) {
        super(properties);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if(!world.isClient() && entity instanceof PlayerEntity player){
            Vec3d vec3 = new Vec3d(0,20,0);
            player.addVelocity(vec3.x, vec3.y, vec3.z);
            player.velocityModified = true;
            StatusEffectInstance slowFallingEffect = new StatusEffectInstance(StatusEffects.SLOW_FALLING,200,0);
            player.setStatusEffect(slowFallingEffect,null);
            world.playSound(null, pos, SoundEvents.BLOCK_BUBBLE_COLUMN_UPWARDS_AMBIENT, SoundCategory.BLOCKS, 1.0F, 1.0F);

        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        for (int i = 0; i < 200; i++) {
            double offsetX = random.nextDouble() * 0.6D - 0.3D;
            double offsetY = random.nextDouble() * 0.5D;
            double offsetZ = random.nextDouble() * 0.6D - 0.3D;

            double motionX = (random.nextDouble() - 0.5) * 0.1;
            double motionY = random.nextDouble() * 2;
            double motionZ = (random.nextDouble() - 0.5) * 0.1;



            world.addParticle(ParticleTypes.SPLASH,
                    pos.getX() + 0.5D + offsetX,
                    pos.getY() + 1.0D + offsetY,
                    pos.getZ() + 0.5D + offsetZ,
                    motionX, motionY, motionZ);
        }
        for(int i=0;i<3;i++)
        {
            double offsetX = random.nextDouble() * 0.6D - 0.3D;
            double offsetY = random.nextDouble() * 0.5D;
            double offsetZ = random.nextDouble() * 0.6D - 0.3D;

            double motionX = (random.nextDouble() - 0.5) * 0.1;
            double motionY = random.nextDouble() * 0.5;
            double motionZ = (random.nextDouble() - 0.5) * 0.1;
            world.addParticle(ParticleTypes.CLOUD,
                    pos.getX() + 0.5D + offsetX,
                    pos.getY() + 1.0D + offsetY,
                    pos.getZ() + 0.5D + offsetZ,
                    motionX, motionY, motionZ);
        }
    }
}
