package kieranvs.avatar.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelFlamingo extends ModelBase {

	
	ModelRenderer RightLeg;
	ModelRenderer LeftLeg;
	ModelRenderer Body;
	ModelRenderer Tail;
	ModelRenderer LeftWing;
	ModelRenderer RightWing;
	ModelRenderer Neck1;
	ModelRenderer Neck2;
	ModelRenderer Neck3;
	ModelRenderer Head;
	ModelRenderer Beak1;
	ModelRenderer Beak2;

	public ModelFlamingo() {
		textureWidth = 64;
		textureHeight = 64;

		RightLeg = new ModelRenderer(this, 27, 0);
		RightLeg.addBox(0F, 0F, 0F, 1, 8, 1);
		RightLeg.setRotationPoint(-2.5F, 16F, 0F);
		RightLeg.setTextureSize(64, 32);
		RightLeg.mirror = true;
		setRotation(RightLeg, 0F, 0F, 0F);
		LeftLeg = new ModelRenderer(this, 27, 0);
		LeftLeg.addBox(0F, 0F, 0F, 1, 8, 1);
		LeftLeg.setRotationPoint(1.5F, 16F, 0F);
		LeftLeg.setTextureSize(64, 32);
		LeftLeg.mirror = true;
		setRotation(LeftLeg, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 0, 0);
		Body.addBox(0F, 0F, 0F, 6, 4, 7);
		Body.setRotationPoint(-3F, 12F, -4F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		Tail = new ModelRenderer(this, 0, 12);
		Tail.addBox(0F, 0F, 0F, 4, 3, 1);
		Tail.setRotationPoint(-2F, 13F, 3F);
		Tail.setTextureSize(64, 32);
		Tail.mirror = true;
		setRotation(Tail, 0F, 0F, 0F);
		LeftWing = new ModelRenderer(this, 0, 17);
		LeftWing.addBox(0F, -1F, 0F, 1, 2, 5);
		LeftWing.setRotationPoint(3F, 13.5F, -3F);
		LeftWing.setTextureSize(64, 32);
		LeftWing.mirror = true;
		setRotation(LeftWing, -0.1858931F, 0F, 0F);
		RightWing = new ModelRenderer(this, 0, 17);
		RightWing.addBox(-1F, -1F, 0F, 1, 2, 5);
		RightWing.setRotationPoint(-3F, 13.5F, -3F);
		RightWing.setTextureSize(64, 32);
		RightWing.mirror = true;
		setRotation(RightWing, -0.1858931F, 0F, 0F);
		Neck1 = new ModelRenderer(this, 13, 12);
		Neck1.addBox(0F, 0F, 0F, 2, 2, 2);
		Neck1.setRotationPoint(-1F, 11F, -5F);
		Neck1.setTextureSize(64, 32);
		Neck1.mirror = true;
		setRotation(Neck1, 0.1487144F, 0F, 0F);
		Neck2 = new ModelRenderer(this, 15, 17);
		Neck2.addBox(0F, 0F, 0F, 2, 5, 2);
		Neck2.setRotationPoint(-1F, 6F, -5.5F);
		Neck2.setTextureSize(64, 32);
		Neck2.mirror = true;
		setRotation(Neck2, 0.0371786F, 0F, 0F);
		Neck3 = new ModelRenderer(this, 0, 34);
		Neck3.addBox(0F, 0F, 0F, 2, 2, 2);
		Neck3.setRotationPoint(-1F, 5.5F, -6.5F);
		Neck3.setTextureSize(64, 32);
		Neck3.mirror = true;
		setRotation(Neck3, 0.669215F, 0F, 0F);
		Head = new ModelRenderer(this, 0, 25);
		Head.addBox(-1.5F, -2F, -5F, 3, 3, 5);
		Head.setRotationPoint(0F, 5F, -5F);
		Head.setTextureSize(64, 32);
		Head.mirror = true;
		setRotation(Head, 0F, 0F, 0F);
		Beak1 = new ModelRenderer(this, 32, 0);
		Beak1.addBox(-1F, -2F, -8F, 2, 2, 4);
		Beak1.setRotationPoint(0F, 5F, -5F);
		Beak1.setTextureSize(64, 32);
		Beak1.mirror = true;
		setRotation(Beak1, 0.2230717F, 0F, 0F);
		Beak2 = new ModelRenderer(this, 0, 0);
		Beak2.addBox(-0.5F, -3F, -8.5F, 1, 1, 1);
		Beak2.setRotationPoint(0F, 5F, -5F);
		Beak2.setTextureSize(64, 32);
		Beak2.mirror = true;
		setRotation(Beak2, 0.4833219F, 0F, 0F);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		RightLeg.render(par7);
		LeftLeg.render(par7);
		Body.render(par7);
		Tail.render(par7);
		LeftWing.render(par7);
		RightWing.render(par7);
		Neck1.render(par7);
		Neck2.render(par7);
		Neck3.render(par7);
		Head.render(par7);
		Beak1.render(par7);
		Beak2.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		this.Head.rotateAngleX = par5 / (180F / (float) Math.PI);
		this.Head.rotateAngleY = par4 / (180F / (float) Math.PI);
		this.RightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.LeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.LeftWing.rotateAngleY = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.RightWing.rotateAngleY = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	}

}
