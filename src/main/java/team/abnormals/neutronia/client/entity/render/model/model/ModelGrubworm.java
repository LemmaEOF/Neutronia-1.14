package team.abnormals.neutronia.client.entity.render.model.model;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

/**
 * Grubworm - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelGrubworm extends EntityModel {
    public Cuboid Head;
    public Cuboid Body1;
    public Cuboid Body2;

    public ModelGrubworm() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.Head = new Cuboid(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 22.0F, -2.0F);
        this.Head.addBox(-2.0F, -1.0F, -2.0F, 4, 2, 2, 0.0F);
        this.Body1 = new Cuboid(this, 0, 4);
        this.Body1.setRotationPoint(0.0F, 22.0F, -2.0F);
        this.Body1.addBox(-3.0F, -1.5F, 0.0F, 6, 4, 5, 0.0F);
        this.Body2 = new Cuboid(this, 0, 13);
        this.Body2.setRotationPoint(-0.01F, 0.01F, 4.0F);
        this.Body2.addBox(-3.0F, -1.5F, 0.0F, 6, 4, 5, 0.0F);
        this.Body1.addChild(this.Body2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.Head.x, this.Head.y, this.Head.z);
        GlStateManager.translatef(this.Head.rotationPointX * f5, this.Head.rotationPointY * f5, this.Head.rotationPointZ * f5);
        GlStateManager.scaled(2.0D, 2.0D, 2.0D);
        GlStateManager.translatef(-this.Head.x, -this.Head.y, -this.Head.z);
        GlStateManager.translatef(-this.Head.rotationPointX * f5, -this.Head.rotationPointY * f5, -this.Head.rotationPointZ * f5);
        this.Head.render(f5);
        GlStateManager.popMatrix();
        this.Body1.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(Cuboid Cuboid, float x, float y, float z) {
        Cuboid.rotationPointX = x;
        Cuboid.rotationPointY = y;
        Cuboid.rotationPointZ = z;
    }
}
