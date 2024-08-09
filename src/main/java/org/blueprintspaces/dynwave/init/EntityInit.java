package org.blueprintspaces.dynwave.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.blueprintspaces.dynwave.Dynwave;
import org.blueprintspaces.dynwave.entity.BulletProjectile;
import org.blueprintspaces.dynwave.entity.MovingEntity;
import org.blueprintspaces.dynwave.entity.ThrowablePoolNoodle;

public class EntityInit
{
    public static final EntityType<ThrowablePoolNoodle> THROWN_NOODLE_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Dynwave.MOD_ID, "noodle_projectile"),
            FabricEntityTypeBuilder.<ThrowablePoolNoodle>create(SpawnGroup.MISC, ThrowablePoolNoodle::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f)).build());

    public static final EntityType<BulletProjectile> MAGIC_PROJECTILE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(Dynwave.MOD_ID, "bullet"),
            FabricEntityTypeBuilder.<BulletProjectile>create(SpawnGroup.MISC, BulletProjectile::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build());

    public static void registerModEntities() {
        Dynwave.LOGGER.info("Registering Mod Entities for " + Dynwave.MOD_ID);
    }
}
