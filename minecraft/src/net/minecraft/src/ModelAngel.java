package net.minecraft.src;

public class ModelAngel extends ModelBiped{
    public ModelRenderer leftWing;
    public ModelRenderer rightWing;
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer leftArm;
    public ModelRenderer rightArm;
    public ModelRenderer leftLeg;
    public ModelRenderer rightLeg;

    public ModelAngel(){
        this(0.0F);
    }
    public ModelAngel(float var1) {
        this(var1, 0.0F);
    }
    public ModelAngel(float var1 , float var2){
        this.head = new ModelRenderer(0, 0);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, var1);
        this.head.setRotationPoint(0.0F, 0.0F + var2, 0.0F);

        this.body = new ModelRenderer(16, 16);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, var1);
        this.body.setRotationPoint(0.0F, 0.0F + var2, 0.0F);

        this.rightWing = new ModelRenderer(46, 0);
        this.rightWing.addBox(2.0F, 0.0F, 2.0F, 8, 12, 1, var1);
        this.rightWing.setRotationPoint(0.0F, 0.0F + var2, 0.0F);
        this.rightWing.mirror = true;
        this.leftWing = new ModelRenderer(46, 0);
        this.leftWing.addBox(-10.0F, 0.0F, 2.0F, 8, 12, 1, var1);
        this.leftWing.setRotationPoint(0.0F, 0.0F + var2, 0.0F);

        this.rightArm = new ModelRenderer(40, 16);
        this.rightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, var1);
        this.rightArm.setRotationPoint(-5.0F, 2.0F + var2, 0.0F);
        this.leftArm = new ModelRenderer(40, 16);
        this.leftArm.mirror = true;
        this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, var1);
        this.leftArm.setRotationPoint(5.0F, 2.0F + var2, 0.0F);
        this.rightLeg = new ModelRenderer(0, 16);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, var1);
        this.rightLeg.setRotationPoint(-2.0F, 12.0F + var2, 0.0F);
        this.leftLeg = new ModelRenderer(0, 16);
        this.leftLeg.mirror = true;
        this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, var1);
        this.leftLeg.setRotationPoint(2.0F, 12.0F + var2, 0.0F);

    }

    public void render(float var1, float var2, float var3, float var4, float var5, float var6) {
        this.setRotationAngles(var1, var2, var3, var4, var5, var6);
        this.head.render(var6);
        this.body.render(var6);
        this.rightArm.render(var6);
        this.leftArm.render(var6);
        this.rightLeg.render(var6);
        this.leftLeg.render(var6);
        this.rightWing.render(var6);
        this.leftWing.render(var6);
    }

    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6) {
        this.bipedHead = head;
        this.bipedRightArm = rightArm;
        this.bipedBody = body;
        this.bipedLeftArm = leftArm;
        this.bipedLeftLeg = leftLeg;
        this.bipedRightLeg = rightLeg;

        this.rightWing.rotateAngleY = MathHelper.cos(var1 * 0.6662F) * 0.5F * var2;
        this.leftWing.rotateAngleY = MathHelper.cos(var1 * 0.6662F + (float)Math.PI) * 0.5F * var2;
        this.rightWing.rotateAngleX = 0.0F;
        this.leftWing.rotateAngleX = 0.0F;

        this.bipedHead.rotateAngleY = var4 / (180.0F / (float)Math.PI);
        this.bipedHead.rotateAngleX = var5 / (180.0F / (float)Math.PI);
        this.bipedHeadwear.rotateAngleY = this.bipedHead.rotateAngleY;
        this.bipedHeadwear.rotateAngleX = this.bipedHead.rotateAngleX;
        this.bipedRightArm.rotateAngleX = MathHelper.cos(var1 * 0.6662F + (float)Math.PI) * 2.0F * var2 * 0.5F;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(var1 * 0.6662F) * 2.0F * var2 * 0.5F;
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;
        this.bipedRightLeg.rotateAngleX = MathHelper.cos(var1 * 0.6662F) * 1.4F * var2;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(var1 * 0.6662F + (float)Math.PI) * 1.4F * var2;
        this.bipedRightLeg.rotateAngleY = 0.0F;
        this.bipedLeftLeg.rotateAngleY = 0.0F;
        if(this.isRiding) {
            this.bipedRightArm.rotateAngleX += (float)Math.PI * -0.2F;
            this.bipedLeftArm.rotateAngleX += (float)Math.PI * -0.2F;
            this.bipedRightLeg.rotateAngleX = (float)Math.PI * -0.4F;
            this.bipedLeftLeg.rotateAngleX = (float)Math.PI * -0.4F;
            this.bipedRightLeg.rotateAngleY = (float)Math.PI * 0.1F;
            this.bipedLeftLeg.rotateAngleY = (float)Math.PI * -0.1F;
        }

        if(this.field_1279_h) {
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - (float)Math.PI * 0.1F;
        }

        if(this.field_1278_i) {
            this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - (float)Math.PI * 0.1F;
        }

        this.bipedRightArm.rotateAngleY = 0.0F;
        this.bipedLeftArm.rotateAngleY = 0.0F;
        if(this.onGround > -9990.0F) {
            float var7 = this.onGround;
            this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(var7) * (float)Math.PI * 2.0F) * 0.2F;
            this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
            var7 = 1.0F - this.onGround;
            var7 *= var7;
            var7 *= var7;
            var7 = 1.0F - var7;
            float var8 = MathHelper.sin(var7 * (float)Math.PI);
            float var9 = MathHelper.sin(this.onGround * (float)Math.PI) * -(this.bipedHead.rotateAngleX - 0.7F) * (12.0F / 16.0F);
            this.bipedRightArm.rotateAngleX = (float)((double)this.bipedRightArm.rotateAngleX - ((double)var8 * 1.2D + (double)var9));
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
            this.bipedRightArm.rotateAngleZ = MathHelper.sin(this.onGround * (float)Math.PI) * -0.4F;
        }

        if(this.isSneak) {
            this.bipedBody.rotateAngleX = 0.5F;
            this.bipedRightLeg.rotateAngleX -= 0.0F;
            this.bipedLeftLeg.rotateAngleX -= 0.0F;
            this.bipedRightArm.rotateAngleX += 0.4F;
            this.bipedLeftArm.rotateAngleX += 0.4F;
            this.bipedRightLeg.rotationPointZ = 4.0F;
            this.bipedLeftLeg.rotationPointZ = 4.0F;
            this.bipedRightLeg.rotationPointY = 9.0F;
            this.bipedLeftLeg.rotationPointY = 9.0F;
            this.bipedHead.rotationPointY = 1.0F;
        } else {
            this.bipedBody.rotateAngleX = 0.0F;
            this.bipedRightLeg.rotationPointZ = 0.0F;
            this.bipedLeftLeg.rotationPointZ = 0.0F;
            this.bipedRightLeg.rotationPointY = 12.0F;
            this.bipedLeftLeg.rotationPointY = 12.0F;
            this.bipedHead.rotationPointY = 0.0F;
        }

        this.bipedRightArm.rotateAngleZ += MathHelper.cos(var3 * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(var3 * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(var3 * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(var3 * 0.067F) * 0.05F;
    }

}
