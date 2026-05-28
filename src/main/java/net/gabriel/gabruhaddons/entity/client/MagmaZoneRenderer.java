package net.gabriel.gabruhaddons.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.gabriel.gabruhaddons.entity.custom.MagmaZoneEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;

public class MagmaZoneRenderer extends EntityRenderer<MagmaZoneEntity> {
    public MagmaZoneRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public void render(MagmaZoneEntity pEntity,
                       float pEntityYaw,
                       float pPartialTick,
                       PoseStack pPoseStack,
                       MultiBufferSource pBuffer,
                       int pPackedLight) {
    }

    @Override
    public ResourceLocation getTextureLocation(MagmaZoneEntity magmaZoneEntity) {
        return null;
    }
}
