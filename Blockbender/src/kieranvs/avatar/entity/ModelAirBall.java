package kieranvs.avatar.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAirBall extends ModelBase {

	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape5;

	public ModelAirBall() {
		textureWidth = 128;
		textureHeight = 128;

		Shape1 = new ModelRenderer(this, 1, 0);
		Shape1.addBox(-3F, -8F, -3F, 6, 16, 6);
		Shape1.setRotationPoint(0F, 16F, 0F);
		Shape1.setTextureSize(128, 128);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 23);
		Shape2.addBox(-4F, -7F, -4F, 8, 14, 8);
		Shape2.setRotationPoint(0F, 16F, 0F);
		Shape2.setTextureSize(128, 128);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 46);
		Shape3.addBox(-5F, -7F, -5F, 10, 12, 10);
		Shape3.setRotationPoint(0F, 17F, 0F);
		Shape3.setTextureSize(128, 128);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 42, 46);
		Shape4.addBox(-4F, -5F, -6F, 8, 10, 12);
		Shape4.setRotationPoint(0F, 16F, 0F);
		Shape4.setTextureSize(128, 128);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 41, 70);
		Shape6.addBox(-7F, -4F, -3F, 14, 8, 6);
		Shape6.setRotationPoint(0F, 16F, 0F);
		Shape6.setTextureSize(128, 128);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 0, 70);
		Shape7.addBox(-3F, -4F, -7F, 6, 8, 14);
		Shape7.setRotationPoint(0F, 16F, 0F);
		Shape7.setTextureSize(128, 128);
		Shape7.mirror = true;
		setRotation(Shape7, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 83, 46);
		Shape5.addBox(-6F, -5F, -4F, 12, 10, 8);
		Shape5.setRotationPoint(0F, 16F, 0F);
		Shape5.setTextureSize(128, 128);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
	}
	
	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		GL11.glTranslatef(0F, -2F, 0F);
		float scale = 1F;
		GL11.glScalef(scale, scale, scale);
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Shape1.render(par7);
		Shape2.render(par7);
		Shape3.render(par7);
		Shape4.render(par7);
		Shape6.render(par7);
		Shape7.render(par7);
		Shape5.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
	}

}
