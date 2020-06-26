package com.bytelaw.bytesstructures.client;

import com.bytelaw.bytesstructures.BytesStructures;
import com.bytelaw.bytesstructures.entity.GuardEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GuardRenderer extends MobRenderer<GuardEntity, BipedModel<GuardEntity>> {
    private static final ResourceLocation TEXTURE = BytesStructures.resource("textures/entity/guard.png");

    public GuardRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BipedModel<>(RenderType::getEntityTranslucent, 0.0F, 0.0F, 64, 64), 0.5F);
    }

    @Override
    public ResourceLocation getEntityTexture(GuardEntity entity) {
        return TEXTURE;
    }
}
