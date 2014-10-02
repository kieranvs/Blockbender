package kieranvs.avatar.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

public class ModelRock extends ModelBase {

	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;

	public ModelRock() {
		textureWidth = 64;
		textureHeight = 64;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 4, 4, 4);
		Shape1.setRotationPoint(-2F, 17F, -2F);
		Shape1.setTextureSize(64, 64);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 17, 0);
		Shape2.addBox(0F, 0F, 0F, 2, 6, 2);
		Shape2.setRotationPoint(-1F, 16F, -1F);
		Shape2.setTextureSize(64, 64);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 0, 9);
		Shape3.addBox(0F, 0F, 0F, 6, 2, 2);
		Shape3.setRotationPoint(-3F, 18F, -1F);
		Shape3.setTextureSize(64, 64);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 0, 14);
		Shape4.addBox(0F, 0F, 0F, 2, 2, 6);
		Shape4.setRotationPoint(-1F, 18F, -3F);
		Shape4.setTextureSize(64, 64);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
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
