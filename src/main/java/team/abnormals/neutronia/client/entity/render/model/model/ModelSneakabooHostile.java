package team.abnormals.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

/**
 * Sneakaboo - Undefined
 * Created using Tabula 7.0.0
 */
public class ModelSneakabooHostile extends EntityModel {
    public Cuboid Head;
    public Cuboid Body;
    public Cuboid HostileRightArm;
    public Cuboid HostileLeftArm;
    public Cuboid RightLeg;
    public Cuboid LeftLeg;
    public Cuboid HostileHead;
    public Cuboid RightArm1;
    public Cuboid LeftArm1;
    public Cuboid RightArm2;
    public Cuboid LeftArm2;

    public ModelSneakabooHostile() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.Head = new Cuboid(this, 0, 0);
        this.Head.setRotationPoint(0.0F, -2.5F, -1.0F);
        this.Head.addBox(-4.5F, -8.0F, -4.5F, 9, 8, 9, 0.0F);
        this.Body = new Cuboid(this, 16, 17);
        this.Body.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.Body.addBox(-3.5F, 0.0F, -2.5F, 7, 16, 5, 0.0F);
        this.HostileLeftArm = new Cuboid(this, 40, 18);
        this.HostileLeftArm.mirror = true;
        this.HostileLeftArm.setRotationPoint(4.5F, -2.0F, 0.0F);
        this.HostileLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 20, 4, 0.0F);
        this.setRotateAngle(HostileLeftArm, -1.5707963267948966F, -0.4363323129985824F, 0.0F);
        this.LeftLeg = new Cuboid(this, 0, 17);
        this.LeftLeg.mirror = true;
        this.LeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        this.LeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.RightLeg = new Cuboid(this, 0, 17);
        this.RightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        this.RightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
        this.LeftArm1 = new Cuboid(this, 36, 48);
        this.LeftArm1.mirror = true;
        this.LeftArm1.setRotationPoint(4.5F, -3.0F, 2.0F);
        this.LeftArm1.addBox(-1.0F, -1.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(LeftArm1, -1.1344640137963142F, -0.08726646259971647F, -0.8726646259971648F);
        this.LeftArm2 = new Cuboid(this, 36, 0);
        this.LeftArm2.mirror = true;
        this.LeftArm2.setRotationPoint(1.0F, 9.0F, -2.0F);
        this.LeftArm2.addBox(-2.0F, -2.0F, -8.0F, 4, 4, 8, 0.0F);
        this.HostileRightArm = new Cuboid(this, 40, 18);
        this.HostileRightArm.setRotationPoint(-4.5F, -2.0F, 0.0F);
        this.HostileRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 20, 4, 0.0F);
        this.setRotateAngle(HostileRightArm, -1.5707963267948966F, 0.4363323129985824F, 0.0F);
        this.HostileHead = new Cuboid(this, 0, 47);
        this.HostileHead.setRotationPoint(0.0F, -2.5F, -1.0F);
        this.HostileHead.addBox(-4.5F, -8.0F, -4.5F, 9, 8, 9, 0.0F);
        this.RightArm2 = new Cuboid(this, 36, 0);
        this.RightArm2.setRotationPoint(-1.0F, 9.0F, -2.0F);
        this.RightArm2.addBox(-2.0F, -2.0F, -8.0F, 4, 4, 8, 0.0F);
        this.RightArm1 = new Cuboid(this, 36, 48);
        this.RightArm1.setRotationPoint(-4.5F, -3.0F, 2.0F);
        this.RightArm1.addBox(-3.0F, -1.0F, -2.0F, 4, 12, 4, 0.0F);
        this.setRotateAngle(RightArm1, -1.1344640137963142F, 0.08726646259971647F, 0.8726646259971648F);
        this.LeftArm1.addChild(this.LeftArm2);
        this.RightArm1.addChild(this.RightArm2);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.Head.render(f5);
        this.Body.render(f5);
        this.HostileLeftArm.render(f5);
        this.LeftLeg.render(f5);
        this.RightLeg.render(f5);
        this.LeftArm1.render(f5);
        this.HostileRightArm.render(f5);
        this.HostileHead.render(f5);
        this.RightArm1.render(f5);
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
