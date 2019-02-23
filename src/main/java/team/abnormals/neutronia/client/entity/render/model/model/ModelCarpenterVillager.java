package team.abnormals.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

/**
 * ModelCarpenterVillager - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelCarpenterVillager extends EntityModel {
    public Cuboid Head;
    public Cuboid HeadLayer;
    public Cuboid Body;
    public Cuboid MiddleArm;
    public Cuboid RightLeg;
    public Cuboid LeftLeg;
    public Cuboid RightBoot;
    public Cuboid LeftBoot;
    public Cuboid Hat;
    public Cuboid Nose;
    public Cuboid LeftArm;
    public Cuboid RightArm;

    public ModelCarpenterVillager() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Nose = new Cuboid(this, 24, 0);
        this.Nose.setRotationPoint(0.0F, -3.0F, -4.0F);
        this.Nose.addBox(-1.0F, 0.0F, -2.0F, 2, 4, 2, 0.0F);
        this.Hat = new Cuboid(this, 0, 51);
        this.Hat.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Hat.addBox(-5.0F, -8.15F, -7.0F, 10, 1, 12, 0.0F);
        this.Body = new Cuboid(this, 0, 18);
        this.Body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Body.addBox(-4.0F, 0.0F, -3.0F, 8, 12, 6, 0.0F);
        this.RightLeg = new Cuboid(this, 28, 20);
        this.RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.MiddleArm = new Cuboid(this, 16, 36);
        this.MiddleArm.setRotationPoint(0.0F, 3.0F, -1.0F);
        this.MiddleArm.addBox(-4.0F, 2.0F, -2.0F, 8, 4, 4, 0.0F);
        this.setRotateAngle(MiddleArm, -0.7853981633974483F, 0.0F, 0.0F);
        this.Head = new Cuboid(this, 0, 0);
        this.Head.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Head.addBox(-4.0F, -10.0F, -4.0F, 8, 10, 8, 0.0F);
        this.RightArm = new Cuboid(this, 0, 36);
        this.RightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.RightArm.addBox(-8.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.HeadLayer = new Cuboid(this, 32, 0);
        this.HeadLayer.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.HeadLayer.addBox(-4.0F, -12.0F, -4.0F, 8, 12, 8, 0.5F);
        this.LeftArm = new Cuboid(this, 0, 36);
        this.LeftArm.mirror = true;
        this.LeftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.LeftArm.addBox(4.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F);
        this.LeftLeg = new Cuboid(this, 28, 20);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.RightBoot = new Cuboid(this, 44, 20);
        this.RightBoot.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightBoot.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.LeftBoot = new Cuboid(this, 44, 20);
        this.LeftBoot.mirror = true;
        this.LeftBoot.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftBoot.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        this.Head.addChild(this.Nose);
        this.Head.addChild(this.Hat);
        this.MiddleArm.addChild(this.RightArm);
        this.MiddleArm.addChild(this.LeftArm);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Body.render(f5);
        this.RightLeg.render(f5);
        this.MiddleArm.render(f5);
        this.Head.render(f5);
        this.HeadLayer.render(f5);
        this.LeftLeg.render(f5);
        this.RightBoot.render(f5);
        this.LeftBoot.render(f5);
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
