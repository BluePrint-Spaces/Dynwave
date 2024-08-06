package org.blueprintspaces.dynwave.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockJumpBoost extends Block
{
    public BlockJumpBoost(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);
        if(!world.isClient()&&entity instanceof PlayerEntity)
        {
            ((PlayerEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST,200));
        }
    }
}
