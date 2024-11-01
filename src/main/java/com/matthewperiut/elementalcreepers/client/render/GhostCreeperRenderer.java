package com.matthewperiut.elementalcreepers.client.render;

import com.matthewperiut.elementalcreepers.entity.behavior.GhostCreeper;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class GhostCreeperRenderer extends LivingEntityRenderer
{
    private EntityModel model = new CreeperEntityModel(2.0F);
    private EntityModel scaleAmount;

    public GhostCreeperRenderer(EntityModel modelbase)
    {
        super(new CreeperEntityModel(), 0.5F);
        this.scaleAmount = modelbase;
    }

    protected void applyScale(GhostCreeper entityghostcreeper, float f) {
        float f1 = entityghostcreeper.getScale(f);
        float f2 = 1.0F + MathHelper.sin(f1 * 100.0F) * f1 * 0.01F;
        if(f1 < 0.0F) {
            f1 = 0.0F;
        }

        if(f1 > 1.0F) {
            f1 = 1.0F;
        }

        f1 *= f1;
        f1 *= f1;
        float f3 = (1.0F + f1 * 0.4F) * f2;
        float f4 = (1.0F + f1 * 0.1F) / f2;
        GL11.glScalef(f3, f4, f3);
    }

    protected int getOverlayColor(GhostCreeper entityghostcreeper, float f, float f1) {
        float f2 = entityghostcreeper.getScale(f1);
        if((int)(f2 * 10.0F) % 2 == 0) {
            return 0;
        } else {
            int i = (int)(f2 * 0.2F * 255.0F);
            if(i < 0) {
                i = 0;
            }

            if(i > 255) {
                i = 255;
            }

            short c = 255;
            short c1 = 255;
            short c2 = 255;
            return i << 24 | c << 16 | c1 << 8 | c2;
        }
    }

    protected boolean method_1407(GhostCreeper entityghostcreeper, int i, float f) {
        if(entityghostcreeper.isCharged()) {
            if(i == 1) {
                float f1 = (float)entityghostcreeper.age + f; // field_1645 = ticksExisted
                this.bindTexture("/armor/power.png");
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glLoadIdentity();
                float f2 = f1 * 0.01F;
                float f3 = f1 * 0.01F;
                GL11.glTranslatef(f2, f3, 0.0F);
                this.setDecorationModel(this.model);
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glEnable(GL11.GL_BLEND);
                float f4 = 0.5F;
                GL11.glColor4f(f4, f4, f4, 1.0F);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
                return true;
            }

            if(i == 2) {
                GL11.glMatrixMode(GL11.GL_TEXTURE);
                GL11.glLoadIdentity();
                GL11.glMatrixMode(GL11.GL_MODELVIEW);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_BLEND);
            }
        }

        return false;
    }

    protected void func_178_a(GhostCreeper entityghostcreeper, float f) {
        GL11.glEnable(GL11.GL_NORMALIZE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    }

    protected boolean renderSlimePassModel(GhostCreeper entityghostcreeper, int i, float f) {
        if(i == 0) {
            this.setDecorationModel(this.scaleAmount);
            GL11.glEnable(GL11.GL_NORMALIZE);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            return true;
        } else {
            if(i == 1) {
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            }

            return false;
        }
    }

    protected boolean bindTexture(GhostCreeper entityghostcreeper, int i, float f) {
        return false;
    }

    protected void applyScale(LivingEntity entityliving, float f) {
        this.applyScale((GhostCreeper) entityliving, f);
        this.func_178_a((GhostCreeper)entityliving, f);
    }

    protected int getOverlayColor(LivingEntity entityliving, float f, float f1) {
        return this.getOverlayColor((GhostCreeper)entityliving, f, f1);
    }

    protected boolean method_1408(LivingEntity entityliving, int i, float f) {
        return this.method_1407((GhostCreeper)entityliving, i, f) && this.renderSlimePassModel((GhostCreeper)entityliving, i, f);
    }

    protected boolean bindTexture(LivingEntity entityliving, int i, float f) {
        return this.bindTexture((GhostCreeper)entityliving, i, f);
    }


}
