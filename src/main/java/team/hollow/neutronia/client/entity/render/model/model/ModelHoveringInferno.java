package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

/**
 * ModelHoveringInferno - Created By: Dion/Trikzon
 * Created using Tabula 7.0.0
 */
public class ModelHoveringInferno extends EntityModel {
    public Cuboid head;
    public Cuboid rod;
    public Cuboid helmetFront1;
    public Cuboid helmetFront2;
    public Cuboid helmetFront3;
    public Cuboid helmetFront4;
    public Cuboid helmetFront5;
    public Cuboid helmetFront6;
    public Cuboid helmetFront7;
    public Cuboid helmetLeft1;
    public Cuboid helmetRight1;
    public Cuboid helmetBack1;
    public Cuboid helmetFrontGold;
    public Cuboid shield1;
    public Cuboid shield2;
    public Cuboid shield3;
    public Cuboid shield4;

    public ModelHoveringInferno() {
        this.textureWidth = 128;
        this.textureHeight = 64;
        this.helmetFrontGold = new Cuboid(this, 27, 6);
        this.helmetFrontGold.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.helmetFrontGold.addBox(-1.0F, -7.5F, -4.5F, 2, 2, 1, 0.0F);
        this.helmetRight1 = new Cuboid(this, 1, 14);
        this.helmetRight1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.helmetRight1.addBox(-4.0F, -8.0F, -3.0F, 1, 8, 6, 0.0F);
        this.helmetFront3 = new Cuboid(this, 35, 23);
        this.helmetFront3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.helmetFront3.addBox(2.0F, -4.0F, -4.0F, 2, 4, 1, 0.0F);
        this.shield2 = new Cuboid(this, 16, 44);
        this.shield2.setRotationPoint(6.0F, 0.0F, -6.0F);
        this.shield2.addBox(-5.0F, 0.0F, -1.0F, 10, 18, 2, 0.0F);
        this.setRotateAngle(shield2, -0.2617993877991494F, -0.7853981633974483F, 0.0F);
        this.helmetLeft1 = new Cuboid(this, 45, 14);
        this.helmetLeft1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.helmetLeft1.addBox(3.0F, -8.0F, -3.0F, 1, 8, 6, 0.0F);
        this.helmetFront2 = new Cuboid(this, 18, 17);
        this.helmetFront2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.helmetFront2.addBox(-4.0F, -8.0F, -4.0F, 1, 4, 1, 0.0F);
        this.helmetBack1 = new Cuboid(this, 21, 29);
        this.helmetBack1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.helmetBack1.addBox(-4.0F, -8.0F, 3.0F, 8, 8, 1, 0.0F);
        this.helmetFront4 = new Cuboid(this, 38, 17);
        this.helmetFront4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.helmetFront4.addBox(3.0F, -8.0F, -4.0F, 1, 4, 1, 0.0F);
        this.head = new Cuboid(this, 46, 50);
        this.head.setRotationPoint(0.0F, -2.0F, 0.0F);
        this.head.addBox(-3.0F, -8.0F, -3.0F, 6, 8, 6, 0.0F);
        this.helmetFront7 = new Cuboid(this, 27, 10);
        this.helmetFront7.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.helmetFront7.addBox(-1.0F, -9.0F, -4.0F, 2, 1, 1, 0.0F);
        this.rod = new Cuboid(this, 0, 38);
        this.rod.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rod.addBox(-2.0F, 0.0F, -2.0F, 4, 22, 4, 0.0F);
        this.shield4 = new Cuboid(this, 16, 44);
        this.shield4.setRotationPoint(-6.0F, 0.0F, 6.0F);
        this.shield4.addBox(-5.0F, 0.0F, -1.0F, 10, 18, 2, 0.0F);
        this.setRotateAngle(shield4, 0.2617993877991494F, -0.7853981633974483F, 0.0F);
        this.helmetFront6 = new Cuboid(this, 25, 13);
        this.helmetFront6.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.helmetFront6.addBox(-2.0F, -8.0F, -4.0F, 4, 1, 1, 0.0F);
        this.shield1 = new Cuboid(this, 16, 44);
        this.shield1.setRotationPoint(-6.0F, 0.0F, -6.0F);
        this.shield1.addBox(-5.0F, 0.0F, -1.0F, 10, 18, 2, 0.0F);
        this.setRotateAngle(shield1, -0.2617993877991494F, 0.7853981633974483F, 0.0F);
        this.shield3 = new Cuboid(this, 16, 44);
        this.shield3.setRotationPoint(6.0F, 0.0F, 6.0F);
        this.shield3.addBox(-5.0F, 0.0F, -1.0F, 10, 18, 2, 0.0F);
        this.setRotateAngle(shield3, 0.2617993877991494F, 0.7853981633974483F, 0.0F);
        this.helmetFront1 = new Cuboid(this, 19, 23);
        this.helmetFront1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.helmetFront1.addBox(-4.0F, -4.0F, -4.0F, 2, 4, 1, 0.0F);
        this.helmetFront5 = new Cuboid(this, 23, 16);
        this.helmetFront5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.helmetFront5.addBox(-3.0F, -7.0F, -4.0F, 6, 2, 1, 0.0F);
        this.head.addChild(this.helmetFrontGold);
        this.head.addChild(this.helmetRight1);
        this.head.addChild(this.helmetFront3);
        this.rod.addChild(this.shield2);
        this.head.addChild(this.helmetLeft1);
        this.head.addChild(this.helmetFront2);
        this.head.addChild(this.helmetBack1);
        this.head.addChild(this.helmetFront4);
        this.head.addChild(this.helmetFront7);
        this.rod.addChild(this.shield4);
        this.head.addChild(this.helmetFront6);
        this.rod.addChild(this.shield1);
        this.rod.addChild(this.shield3);
        this.head.addChild(this.helmetFront1);
        this.head.addChild(this.helmetFront5);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.head.render(f5);
        this.rod.render(f5);
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
