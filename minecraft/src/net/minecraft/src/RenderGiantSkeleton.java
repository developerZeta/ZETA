package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class RenderGiantSkeleton extends RenderLiving {

    public RenderGiantSkeleton(ModelBase model, float shadowSize) {
        super(model, shadowSize * 2.0F);
    }

    protected void preRenderCallback(EntityLiving entity, float partialTick) {
        GL11.glScalef(2.0F, 2.0F, 2.0F);
    }
}