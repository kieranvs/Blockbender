package kieranvs.avatar.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelOtterPenguin extends ModelBase {

	ModelRenderer Left_foot;
	ModelRenderer Right_foot;
	ModelRenderer Right_leg;
	ModelRenderer Left_leg;
	ModelRenderer Back;
	ModelRenderer Stomach;
	ModelRenderer Tail;
	ModelRenderer Flipper_1;
	ModelRenderer Flipper_4;
	ModelRenderer Flipper_3;
	ModelRenderer Flipper_2;
	ModelRenderer Neck;
	ModelRenderer Head;
	ModelRenderer Left_toes;
	ModelRenderer Right_toes;

	public ModelOtterPenguin() {
		textureWidth = 64;
		textureHeight = 64;

		Left_foot = new ModelRenderer(this, 30, 3);
		Left_foot.addBox(0F, 0F, 0F, 3, 1, 3);
		Left_foot.setRotationPoint(1F, 23F, 0F);
		Left_foot.setTextureSize(64, 64);
		Left_foot.mirror = true;
		setRotation(Left_foot, 0F, 0F, 0F);
		Right_foot = new ModelRenderer(this, 30, 3);
		Right_foot.addBox(0F, 0F, 0F, 3, 1, 3);
		Right_foot.setRotationPoint(-4F, 23F, 0F);
		Right_foot.setTextureSize(64, 64);
		Right_foot.mirror = true;
		setRotation(Right_foot, 0F, 0F, 0F);
		Right_leg = new ModelRenderer(this, 30, 8);
		Right_leg.addBox(0F, 0F, 0F, 2, 2, 2);
		Right_leg.setRotationPoint(-3F, 21F, 1F);
		Right_leg.setTextureSize(64, 64);
		Right_leg.mirror = true;
		setRotation(Right_leg, 0F, 0F, 0F);
		Left_leg = new ModelRenderer(this, 30, 8);
		Left_leg.addBox(0F, 0F, 0F, 2, 2, 2);
		Left_leg.setRotationPoint(1F, 21F, 1F);
		Left_leg.setTextureSize(64, 64);
		Left_leg.mirror = true;
		setRotation(Left_leg, 0F, 0F, 0F);
		Back = new ModelRenderer(this, 0, 25);
		Back.addBox(0F, 0F, 1F, 8, 13, 2);
		Back.setRotationPoint(-4F, 8F, 0F);
		Back.setTextureSize(64, 64);
		Back.mirror = true;
		setRotation(Back, 0F, 0F, 0F);
		Stomach = new ModelRenderer(this, 0, 41);
		Stomach.addBox(0F, 0F, 0F, 8, 10, 1);
		Stomach.setRotationPoint(-4F, 8F, 0F);
		Stomach.setTextureSize(64, 64);
		Stomach.mirror = true;
		setRotation(Stomach, 0F, 0F, 0F);
		Tail = new ModelRenderer(this, 0, 0);
		Tail.addBox(0F, 0F, 0F, 6, 1, 7);
		Tail.setRotationPoint(-3F, 18F, 3F);
		Tail.setTextureSize(64, 64);
		Tail.mirror = true;
		setRotation(Tail, -0.4833219F, 0F, 0F);
		Flipper_1 = new ModelRenderer(this, 29, 15);
		Flipper_1.addBox(0F, 0F, 0F, 1, 7, 2);
		Flipper_1.setRotationPoint(4F, 10F, 0F);
		Flipper_1.setTextureSize(64, 64);
		Flipper_1.mirror = true;
		setRotation(Flipper_1, -0.4833219F, 0F, -0.2602503F);
		Flipper_4 = new ModelRenderer(this, 0, 15);
		Flipper_4.addBox(0F, 0F, 0F, 1, 7, 2);
		Flipper_4.setRotationPoint(-5F, 10F, 0F);
		Flipper_4.setTextureSize(64, 64);
		Flipper_4.mirror = true;
		setRotation(Flipper_4, -0.4833219F, 0F, 0.2602503F);
		Flipper_3 = new ModelRenderer(this, 0, 15);
		Flipper_3.addBox(0F, 0F, 0F, 1, 7, 2);
		Flipper_3.setRotationPoint(-5F, 12F, 0F);
		Flipper_3.setTextureSize(64, 64);
		Flipper_3.mirror = true;
		setRotation(Flipper_3, -0.1487144F, 0F, 0.2230717F);
		Flipper_2 = new ModelRenderer(this, 29, 15);
		Flipper_2.addBox(0F, 0F, 0F, 1, 7, 2);
		Flipper_2.setRotationPoint(5F, 12F, 0F);
		Flipper_2.setTextureSize(64, 64);
		Flipper_2.mirror = true;
		setRotation(Flipper_2, -0.1487144F, 0F, -0.2230717F);
		Neck = new ModelRenderer(this, 0, 9);
		Neck.addBox(0F, 0F, 0F, 6, 1, 3);
		Neck.setRotationPoint(-3F, 7F, 0F);
		Neck.setTextureSize(64, 64);
		Neck.mirror = true;
		setRotation(Neck, 0F, 0F, 0F);
		Head = new ModelRenderer(this, 7, 15);
		Head.addBox(-3F, -4F, -2F, 6, 4, 4);
		Head.setRotationPoint(0F, 7F, 1F);
		Head.setTextureSize(64, 64);
		Head.mirror = true;
		setRotation(Head, 0F, 0F, 0F);
		Left_toes = new ModelRenderer(this, 11, 31);
		Left_toes.addBox(0F, 0F, 0F, 3, 1, 1);
		Left_toes.setRotationPoint(1F, 23F, -1F);
		Left_toes.setTextureSize(64, 64);
		Left_toes.mirror = true;
		setRotation(Left_toes, 0F, 0F, 0F);
		Right_toes = new ModelRenderer(this, 11, 31);
		Right_toes.addBox(0F, 0F, 0F, 3, 1, 1);
		Right_toes.setRotationPoint(-4F, 23F, -1F);
		Right_toes.setTextureSize(64, 64);
		Right_toes.mirror = true;
		setRotation(Right_toes, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Left_foot.render(f5);
		Right_foot.render(f5);
		Right_leg.render(f5);
		Left_leg.render(f5);
		Back.render(f5);
		Stomach.render(f5);
		Tail.render(f5);
		Flipper_1.render(f5);
		Flipper_4.render(f5);
		Flipper_3.render(f5);
		Flipper_2.render(f5);
		Neck.render(f5);
		Head.render(f5);
		Left_toes.render(f5);
		Right_toes.render(f5);
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
		this.Left_leg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.Left_foot.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.Left_toes.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.Right_leg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.Right_foot.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.Right_toes.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.Flipper_1.rotateAngleZ = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.Flipper_2.rotateAngleZ = MathHelper.cos(par1 * 0.6662F + (float) Math.PI) * 1.4F * par2;
		this.Flipper_3.rotateAngleZ = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.Flipper_4.rotateAngleZ = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;

	}

}
