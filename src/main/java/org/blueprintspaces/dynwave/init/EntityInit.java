package org.blueprintspaces.dynwave.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.blueprintspaces.dynwave.Dynwave;
import org.blueprintspaces.dynwave.entity.MovingEntity;
import org.blueprintspaces.dynwave.entity.ThrowablePoolNoodle;

public class EntityInit
{
    public static final EntityType<ThrowablePoolNoodle> THROWN_NOODLE_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Dynwave.MOD_ID, "noodle_projectile"),
            FabricEntityTypeBuilder.<ThrowablePoolNoodle>create(SpawnGroup.MISC, ThrowablePoolNoodle::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());

    public static final EntityType<MovingEntity> MOVING_OBSTACLE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Dynwave.MOD_ID,"moving_obstacle"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, MovingEntity::new)
                    .dimensions(EntityDimensions.fixed(8.0f, 1.0f)
                    ).build());

    public static void registerModEntities() {
        Dynwave.LOGGER.info("Registering Mod Entities for " + Dynwave.MOD_ID);
    }
}
