package org.blueprintspaces.dynwave.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import org.blueprintspaces.dynwave.client.model.BulletModel;
import org.blueprintspaces.dynwave.client.renderer.BulletRenderer;
import org.blueprintspaces.dynwave.client.renderer.ModModelLayers;
import org.blueprintspaces.dynwave.client.renderer.MovingObstacleRenderer;
import org.blueprintspaces.dynwave.init.EntityInit;

public class DynwaveClient implements ClientModInitializer {

    @Override
    public void onInitializeClient()
    {

        EntityRendererRegistry.register(EntityInit.THROWN_NOODLE_PROJECTILE, FlyingItemEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.BULLET, BulletModel::createBodyLayer);

        EntityRendererRegistry.register(EntityInit.MAGIC_PROJECTILE, BulletRenderer::new);


    }
}
