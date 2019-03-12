package team.hollow.neutronia.client.entity.render.model.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

/**
 * ModelScorpion - Dion - Trikzon
 * Created using Tabula 7.0.0
 */
public class ModelScorpion extends EntityModel {
    public Cuboid body;
    public Cuboid head;
    public Cuboid tail1;
    public Cuboid clawRight1;
    public Cuboid clawLeft1;
    public Cuboid tail2;
    public Cuboid tail3;
    public Cuboid tail4;
    public Cuboid tail5;
    public Cuboid stinger;
    public Cuboid clawRight2;
    public Cuboid clawLeft2;
    public Cuboid legLeft1;
    public Cuboid legLeft2;
    public Cuboid legLeft3;
    public Cuboid legLeft4;
    public Cuboid legRight1;
    public Cuboid legRight2;
    public Cuboid legRight3;
    public Cuboid legRight4;

    public ModelScorpion() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.legLeft4 = new Cuboid(this, 45, 24);
        this.legLeft4.setRotationPoint(4.0F, 20.5F, 3.5F);
        this.legLeft4.addBox(-1.0F, -1.0F, -1.0F, 7, 2, 2, 0.0F);
        this.setRotateAngle(legLeft4, 0.0F, -0.4363323129985824F, 0.4886921905584123F);
        this.tail5 = new Cuboid(this, 0, 8);
        this.tail5.setRotationPoint(0.0F, 0.2F, 3.2F);
        this.tail5.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(tail5, 0.5009094953223726F, 0.0F, 0.0F);
        this.clawLeft2 = new Cuboid(this, 18, 4);
        this.clawLeft2.setRotationPoint(0.0F, 0.0F, -2.8F);
        this.clawLeft2.addBox(-1.0F, -1.0F, -2.8F, 2, 2, 3, 0.0F);
        this.setRotateAngle(clawLeft2, 0.0F, 0.6981317007977318F, 0.0F);
        this.head = new Cuboid(this, 44, 16);
        this.head.setRotationPoint(0.0F, 20.5F, -5.0F);
        this.head.addBox(-3.0F, -2.0F, -4.0F, 6, 4, 4, 0.0F);
        this.tail1 = new Cuboid(this, 0, 22);
        this.tail1.setRotationPoint(0.0F, 20.0F, 4.0F);
        this.tail1.addBox(-2.5F, -2.0F, 0.0F, 5, 4, 6, 0.0F);
        this.setRotateAngle(tail1, 0.3490658503988659F, 0.0F, 0.0F);
        this.clawRight1 = new Cuboid(this, 20, 0);
        this.clawRight1.setRotationPoint(-2.5F, 20.5F, -6.0F);
        this.clawRight1.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(clawRight1, 0.0F, 0.6981317007977318F, 0.0F);
        this.legRight3 = new Cuboid(this, 45, 24);
        this.legRight3.setRotationPoint(-4.0F, 20.5F, 1.1F);
        this.legRight3.addBox(-6.0F, -1.0F, -1.0F, 7, 2, 2, 0.0F);
        this.setRotateAngle(legRight3, 0.0F, 0.2617993877991494F, -0.4886921905584123F);
        this.legLeft3 = new Cuboid(this, 45, 24);
        this.legLeft3.setRotationPoint(4.0F, 20.5F, 1.1F);
        this.legLeft3.addBox(-1.0F, -1.0F, -1.0F, 7, 2, 2, 0.0F);
        this.setRotateAngle(legLeft3, 0.0F, -0.2617993877991494F, 0.4886921905584123F);
        this.legRight2 = new Cuboid(this, 45, 24);
        this.legRight2.setRotationPoint(-4.0F, 20.5F, -1.2F);
        this.legRight2.addBox(-6.0F, -1.0F, -1.0F, 7, 2, 2, 0.0F);
        this.setRotateAngle(legRight2, 0.0F, -0.2617993877991494F, -0.4886921905584123F);
        this.body = new Cuboid(this, 28, 0);
        this.body.setRotationPoint(0.0F, 20.5F, 0.0F);
        this.body.addBox(-4.0F, -3.0F, -5.0F, 8, 6, 10, 0.0F);
        this.clawLeft1 = new Cuboid(this, 20, 0);
        this.clawLeft1.setRotationPoint(2.5F, 20.5F, -6.0F);
        this.clawLeft1.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(clawLeft1, 0.0F, -0.6981317007977318F, 0.0F);
        this.tail3 = new Cuboid(this, 0, 14);
        this.tail3.setRotationPoint(0.0F, 0.0F, 4.3F);
        this.tail3.addBox(-2.0F, -1.5F, 0.0F, 4, 3, 5, 0.0F);
        this.setRotateAngle(tail3, 0.3490658503988659F, 0.0F, 0.0F);
        this.legLeft1 = new Cuboid(this, 45, 24);
        this.legLeft1.setRotationPoint(4.0F, 20.5F, -3.5F);
        this.legLeft1.addBox(-1.0F, -1.0F, -1.0F, 7, 2, 2, 0.0F);
        this.setRotateAngle(legLeft1, 0.0F, 0.4363323129985824F, 0.4886921905584123F);
        this.legRight1 = new Cuboid(this, 45, 24);
        this.legRight1.setRotationPoint(-4.0F, 20.5F, -3.5F);
        this.legRight1.addBox(-6.0F, -1.0F, -1.0F, 7, 2, 2, 0.0F);
        this.setRotateAngle(legRight1, 0.0F, -0.4363323129985824F, -0.4886921905584123F);
        this.stinger = new Cuboid(this, 0, 4);
        this.stinger.setRotationPoint(0.0F, 0.0F, 4.0F);
        this.stinger.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(stinger, 0.2617993877991494F, 0.0F, 0.0F);
        this.tail2 = new Cuboid(this, 0, 14);
        this.tail2.setRotationPoint(0.0F, -0.9F, 4.9F);
        this.tail2.addBox(-2.0F, -1.5F, 0.0F, 4, 3, 5, 0.0F);
        this.setRotateAngle(tail2, 0.7853981633974483F, 0.0F, 0.0F);
        this.legRight4 = new Cuboid(this, 45, 24);
        this.legRight4.setRotationPoint(-4.0F, 20.5F, 3.5F);
        this.legRight4.addBox(-6.0F, -1.0F, -1.0F, 7, 2, 2, 0.0F);
        this.setRotateAngle(legRight4, 0.0F, 0.4455668605402397F, -0.4886921905584123F);
        this.legLeft2 = new Cuboid(this, 45, 24);
        this.legLeft2.setRotationPoint(4.0F, 20.5F, -1.2F);
        this.legLeft2.addBox(-1.0F, -1.0F, -1.0F, 7, 2, 2, 0.0F);
        this.setRotateAngle(legLeft2, 0.0F, 0.2617993877991494F, 0.4886921905584123F);
        this.tail4 = new Cuboid(this, 0, 8);
        this.tail4.setRotationPoint(0.0F, 0.0F, 4.3F);
        this.tail4.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 4, 0.0F);
        this.setRotateAngle(tail4, 0.8726646259971648F, 0.0F, 0.0F);
        this.clawRight2 = new Cuboid(this, 18, 4);
        this.clawRight2.setRotationPoint(0.0F, 0.0F, -2.8F);
        this.clawRight2.addBox(-1.0F, -1.0F, -2.8F, 2, 2, 3, 0.0F);
        this.setRotateAngle(clawRight2, 0.0F, -0.6981317007977318F, 0.0F);
        this.tail4.addChild(this.tail5);
        this.clawLeft1.addChild(this.clawLeft2);
        this.tail2.addChild(this.tail3);
        this.tail5.addChild(this.stinger);
        this.tail1.addChild(this.tail2);
        this.tail3.addChild(this.tail4);
        this.clawRight1.addChild(this.clawRight2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.legLeft4.render(f5);
        this.head.render(f5);
        this.tail1.render(f5);
        this.clawRight1.render(f5);
        this.legRight3.render(f5);
        this.legLeft3.render(f5);
        this.legRight2.render(f5);
        this.body.render(f5);
        this.clawLeft1.render(f5);
        this.legLeft1.render(f5);
        this.legRight1.render(f5);
        this.legRight4.render(f5);
        this.legLeft2.render(f5);
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
