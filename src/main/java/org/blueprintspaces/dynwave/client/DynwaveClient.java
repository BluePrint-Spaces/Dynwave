package org.blueprintspaces.dynwave.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import org.blueprintspaces.dynwave.client.renderer.ModModelLayers;
import org.blueprintspaces.dynwave.client.renderer.MovingObstacleRenderer;
import org.blueprintspaces.dynwave.init.EntityInit;

public class DynwaveClient implements ClientModInitializer {

    @Override
    public void onInitializeClient()
    {
        EntityRendererRegistry.register(EntityInit.MOVING_OBSTACLE, MovingObstacleRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MOVING_OBS_LAYER,MovingObstacleRenderer::getTexturedModelData);

        EntityRendererRegistry.register(EntityInit.THROWN_NOODLE_PROJECTILE, FlyingItemEntityRenderer::new);

    }
}
