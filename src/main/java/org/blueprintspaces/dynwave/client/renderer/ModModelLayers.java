package org.blueprintspaces.dynwave.client.renderer;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import org.blueprintspaces.dynwave.Dynwave;

public class ModModelLayers
{
    public static final EntityModelLayer MOVING_OBS_LAYER =
            new EntityModelLayer(new Identifier(Dynwave.MOD_ID,"moving_obstacle_layer"),"main");
}
