package team.abnormals.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

/**
 * ModelInfernoProtect - cybercat5555
 * Created using Tabula 5.1.0
 */
public class ModelInfernoProtect extends EntityModel {
    public Cuboid middleRod;
    public Cuboid head;
    public Cuboid crown;
    public Cuboid shield01;
    public Cuboid shield02;
    public Cuboid shield03;
    public Cuboid shield03_1;

    public ModelInfernoProtect() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.crown = new Cuboid(this, 11, 46);
        this.crown.setRotationPoint(0.0F, -4.6F, 0.0F);
        this.crown.addBox(-4.0F, -9.1F, -4.0F, 8, 8, 8, 0.5F);
        this.shield03_1 = new Cuboid(this, 37, 30);
        this.shield03_1.mirror = true;
        this.shield03_1.setRotationPoint(0.0F, -3.5F, 6.0F);
        this.shield03_1.addBox(-5.5F, -8.5F, -1.0F, 11, 17, 2, 0.0F);
        this.setRotateAngle(shield03_1, 0.0F, 3.141592653589793F, 0.0F);
        this.middleRod = new Cuboid(this, 0, 21);
        this.middleRod.setRotationPoint(0.0F, 8.0F, 0.0F);
        this.middleRod.addBox(-2.0F, -11.5F, -2.0F, 4, 23, 4, 0.0F);
        this.shield02 = new Cuboid(this, 37, 0);
        this.shield02.mirror = true;
        this.shield02.setRotationPoint(-6.0F, -3.5F, 0.0F);
        this.shield02.addBox(-1.0F, -8.5F, -5.5F, 2, 17, 11, 0.0F);
        this.shield01 = new Cuboid(this, 37, 0);
        this.shield01.setRotationPoint(6.0F, -3.5F, 0.0F);
        this.shield01.addBox(-1.0F, -8.5F, -5.5F, 2, 17, 11, 0.0F);
        this.head = new Cuboid(this, 0, 0);
        this.head.setRotationPoint(0.0F, -4.6F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.shield03 = new Cuboid(this, 37, 30);
        this.shield03.setRotationPoint(0.0F, -3.5F, -6.0F);
        this.shield03.addBox(-5.5F, -8.5F, -1.0F, 11, 17, 2, 0.0F);
        this.middleRod.addChild(this.shield03_1);
        this.middleRod.addChild(this.shield02);
        this.middleRod.addChild(this.shield01);
        this.middleRod.addChild(this.shield03);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.crown.render(f5);
        this.middleRod.render(f5);
        this.head.render(f5);
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
