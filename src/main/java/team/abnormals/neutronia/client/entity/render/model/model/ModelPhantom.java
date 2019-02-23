package team.abnormals.neutronia.client.entity.render.model.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelPhantom extends EntityModel {

    Cuboid head;
    Cuboid body;
    Cuboid leftWing;
    Cuboid rightWing;
    Cuboid bodyLeft;
    Cuboid bodyMiddle;
    Cuboid tailStart;
    Cuboid tailEnd;

    public ModelPhantom() {
        this.textureWidth = 128;
        this.textureHeight = 32;
        this.bodyLeft = new Cuboid(this, 0, 8);
        this.bodyLeft.setRotationPoint(2.6F, 17.0F, 1.1F);
        this.bodyLeft.addBox(0.0F, 0.0F, 0.0F, 6, 2, 9, 0.0F);
        this.leftWing = new Cuboid(this, 0, 20);
        this.leftWing.setRotationPoint(6.0F, 0.0F, 0.0F);
        this.leftWing.addBox(0.0F, 0.0F, 0.0F, 13, 1, 9, 0.0F);
        this.tailStart = new Cuboid(this, 26, 0);
        this.tailStart.setRotationPoint(-1.4F, 17.0F, 10.1F);
        this.tailStart.addBox(0.0F, 0.0F, 0.0F, 3, 2, 6, 0.0F);
        this.tailEnd = new Cuboid(this, 46, 0);
        this.tailEnd.setRotationPoint(1.0F, 0.0F, 6.0F);
        this.tailEnd.addBox(0.0F, 0.0F, 0.0F, 1, 1, 6, 0.0F);
        this.rightWing = new Cuboid(this, 44, 20);
        this.rightWing.setRotationPoint(-13.0F, 0.0F, 0.0F);
        this.rightWing.addBox(0.0F, 0.0F, 0.0F, 13, 1, 9, 0.0F);
        this.head = new Cuboid(this, 0, 0);
        this.head.setRotationPoint(0.0F, 18.0F, 1.27F);
        this.head.addBox(-3.4F, 0.0F, -5.0F, 7, 3, 5, 0.0F);
        this.bodyMiddle = new Cuboid(this, 28, 8);
        this.bodyMiddle.setRotationPoint(-2.4F, 17.0F, 1.1F);
        this.bodyMiddle.addBox(0.0F, 0.0F, 0.0F, 5, 3, 9, 0.0F);
        this.body = new Cuboid(this, 56, 8);
        this.body.setRotationPoint(-8.4F, 17.0F, 1.1F);
        this.body.addBox(0.0F, 0.0F, 0.0F, 6, 2, 9, 0.0F);
        this.bodyLeft.addChild(this.leftWing);
        this.tailStart.addChild(this.tailEnd);
        this.body.addChild(this.rightWing);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.bodyLeft.render(f5);
        this.tailStart.render(f5);
        this.head.render(f5);
        this.bodyMiddle.render(f5);
        this.body.render(f5);
    }

    private void setRotation(Cuboid model, float x, float y, float z) {
        model.rotationPointX = x;
        model.rotationPointY = y;
        model.rotationPointZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

}