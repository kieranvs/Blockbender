package kieranvs.avatar.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class ModelFlyingBison extends ModelBase {

	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer leg1; // back right
	ModelRenderer leg2; // mid left
	ModelRenderer leg3; // front right
	ModelRenderer leg4; // front left
	ModelRenderer leg5; // mid right
	ModelRenderer leg6; // back left
	ModelRenderer body2;
	ModelRenderer Shape1;
	ModelRenderer Shape2;

	public ModelFlyingBison() {

		textureWidth = 256;
		textureHeight = 64;

		head = new ModelRenderer(this, 108, 0);
		head.addBox(-4F, -4F, -6F, 16, 9, 10);
		head.setRotationPoint(2F, 1F, -12F);
		head.setTextureSize(256, 64);
		head.mirror = true;
		setRotation(head, 0.0011085F, 0F, 0F);
		body = new ModelRenderer(this, 30, 0);
		body.addBox(-6F, -10F, -7F, 24, 36, 13);
		body.setRotationPoint(0F, 5F, 2F);
		body.setTextureSize(256, 64);
		body.mirror = true;
		setRotation(body, 1.570796F, 0F, 0F);
		leg1 = new ModelRenderer(this, 0, 0);
		leg1.addBox(-3F, 0F, -2F, 7, 14, 7);
		leg1.setRotationPoint(-3F, 12F, 20F);
		leg1.setTextureSize(256, 64);
		leg1.mirror = true;
		setRotation(leg1, 0F, 0F, 0F);
		leg2 = new ModelRenderer(this, 0, 0);
		leg2.addBox(-1F, 0F, -2F, 7, 14, 7);
		leg2.setRotationPoint(12F, 12F, 7F);
		leg2.setTextureSize(256, 64);
		leg2.mirror = true;
		setRotation(leg2, 0F, 0F, 0F);
		leg3 = new ModelRenderer(this, 0, 0);
		leg3.addBox(-3F, 0F, -3F, 7, 14, 7);
		leg3.setRotationPoint(-3F, 12F, -5F);
		leg3.setTextureSize(256, 64);
		leg3.mirror = true;
		setRotation(leg3, 0F, 0F, 0F);
		leg4 = new ModelRenderer(this, 0, 0);
		leg4.addBox(-1F, 0F, -3F, 7, 14, 7);
		leg4.setRotationPoint(12F, 12F, -5F);
		leg4.setTextureSize(256, 64);
		leg4.mirror = true;
		setRotation(leg4, 0F, 0F, 0F);
		leg5 = new ModelRenderer(this, 0, 0);
		leg5.addBox(-3F, 0F, -2F, 7, 14, 7);
		leg5.setRotationPoint(-3F, 12F, 7F);
		leg5.setTextureSize(256, 64);
		leg5.mirror = true;
		setRotation(leg5, 0F, 0F, 0F);
		leg6 = new ModelRenderer(this, 0, 0);
		leg6.addBox(-1F, 0F, -2F, 7, 14, 7);
		leg6.setRotationPoint(12F, 12F, 20F);
		leg6.setTextureSize(256, 64);
		leg6.mirror = true;
		setRotation(leg6, 0F, 0F, 0F);
		body2 = new ModelRenderer(this, 107, 28);
		body2.addBox(0F, 0F, 0F, 15, 28, 5);
		body2.setRotationPoint(-1.533333F, 3.333333F, 24F);
		body2.setTextureSize(256, 64);
		body2.mirror = true;
		setRotation(body2, 0.7479983F, 0F, 0F);
		Shape1 = new ModelRenderer(this, 0, 22);
		Shape1.addBox(0F, 0F, 0F, 2, 6, 3);
		Shape1.setRotationPoint(14F, -6F, -12F);
		Shape1.setTextureSize(256, 64);
		Shape1.mirror = true;
		setRotation(Shape1, -0.7853982F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 22);
		Shape2.addBox(0F, 0F, 0F, 2, 6, 3);
		Shape2.setRotationPoint(-4F, -6F, -12F);
		Shape2.setTextureSize(256, 64);
		Shape2.mirror = true;
		setRotation(Shape2, -0.7853982F, 0F, 0F);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		float scale = 2;
		GL11.glScalef(scale, scale, scale);
		GL11.glTranslatef(-0.4F, -1F, -0.4F);
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		leg1.render(par7);
		leg2.render(par7);
		leg3.render(par7);
		leg4.render(par7);
		leg5.render(par7);
		leg6.render(par7);
		body2.render(par7);
		Shape1.render(par7);
		Shape2.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		this.head.rotateAngleX = par5 / (180F / (float) Math.PI);
		this.head.rotateAngleY = par4 / (180F / (float) Math.PI);
		this.body.rotateAngleX = ((float) Math.PI / 2F);
		this.leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.leg6.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.leg5.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.body2.rotateAngleX = (float) (0.7479983F + Math.toRadians(((EntityBison) par7Entity).tailAngle));
	}

}
