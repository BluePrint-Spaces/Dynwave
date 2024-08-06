package org.blueprintspaces.dynwave.client.renderer;

import net.minecraft.client.model.*;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.blueprintspaces.dynwave.Dynwave;
import org.blueprintspaces.dynwave.entity.MovingEntity;
import org.joml.Quaternionf;

public class MovingObstacleRenderer extends EntityRenderer<MovingEntity>

{
    private float SINE_45_DEGREES = (float)MathHelper.sin(0.7853981633974483F);
    ModelPart bone;
    public MovingObstacleRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        ModelPart root = ctx.getPart(ModModelLayers.MOVING_OBS_LAYER);
        this.bone = root.getChild("bone");
    }


    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData bone = modelPartData.addChild("bone", ModelPartBuilder.create().uv(0, 0).cuboid(-88.0F, -8.0F, -8.0F, 176.0F, 16.0F, 16.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 16.0F, 0.0F));
        return TexturedModelData.of(modelData, 512, 512);
    }

    @Override
    public void render(MovingEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        float time = (float)entity.age + tickDelta;

        float radius = 5.0F;
        float speed = 0.1F;
        float angle = time * speed;

        double posX = entity.getX() + MathHelper.cos(angle) * radius;
        double posZ = entity.getZ() + MathHelper.sin(angle) * radius;

        entity.setPos(posX, entity.getY(), posZ);

        entity.refreshPositionAndAngles(posX, entity.getY(), posZ, entity.getYaw(), entity.getPitch());

        matrices.push();
        matrices.translate(posX, entity.getY(), posZ);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.sin(time * speed) * 360.0F));

        bone.render(matrices, vertexConsumers.getBuffer(RenderLayer.getEntitySolid(getTexture(entity))), light, OverlayTexture.DEFAULT_UV);

        matrices.pop();
    }

    @Override
    public Identifier getTexture(MovingEntity entity) {
        return new Identifier(Dynwave.MOD_ID,"textures/entity/moving_obs.png");
    }
}
