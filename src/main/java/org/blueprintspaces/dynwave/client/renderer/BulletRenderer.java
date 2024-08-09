package org.blueprintspaces.dynwave.client.renderer;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import org.blueprintspaces.dynwave.Dynwave;
import org.blueprintspaces.dynwave.client.model.BulletModel;
import org.blueprintspaces.dynwave.entity.BulletProjectile;

public class BulletRenderer extends EntityRenderer<BulletProjectile> {
    public static final Identifier TEXTURE = new Identifier(Dynwave.MOD_ID, "textures/entity/bullet_texture.png");

    private BulletModel model;
    public BulletRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        model = new BulletModel(ctx.getPart(ModModelLayers.BULLET));
    }

    @Override
    public void render(BulletProjectile entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light)
    {
        matrices.push();
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevYaw, entity.getYaw()) - 90.0F));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(tickDelta, entity.prevPitch, entity.getPitch()) + 90.0F));
        VertexConsumer vertexconsumer = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, this.model.getLayer(TEXTURE), false, false);
        this.model.render(matrices, vertexconsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 0.0F, 0.0F, 1.0F);
        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(BulletProjectile entity) {
        return TEXTURE;
    }
}
