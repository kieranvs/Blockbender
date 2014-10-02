package kieranvs.avatar.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class ModelPolarBearDog extends ModelBase {

	ModelRenderer ForeBody;
	ModelRenderer Shoulders;
	ModelRenderer BackPadding;
	ModelRenderer LowerBody;
	ModelRenderer LeftForeThigh;
	ModelRenderer LeftForeShin;
	ModelRenderer LeftForePaw;
	ModelRenderer RightForeThigh;
	ModelRenderer RightForeShin;
	ModelRenderer RightForePaw;
	ModelRenderer LeftBackThigh;
	ModelRenderer LeftBackShin;
	ModelRenderer LeftBackPaw;
	ModelRenderer RightBackThigh;
	ModelRenderer RightBackShin;
	ModelRenderer RightBackPaw;
	ModelRenderer Neck;
	ModelRenderer Head;
	ModelRenderer Nose;
	ModelRenderer LeftEar;
	ModelRenderer RightEar;
	ModelRenderer Tail;

	public ModelPolarBearDog() {
		textureWidth = 256;
		textureHeight = 256;

		ForeBody = new ModelRenderer(this, 0, 0);
		ForeBody.addBox(0F, 0F, 0F, 24, 10, 20);
		ForeBody.setRotationPoint(0F, -2F, -10F);
		ForeBody.setTextureSize(64, 32);
		ForeBody.mirror = true;
		setRotation(ForeBody, 0F, 0F, -0.074351F);
		Shoulders = new ModelRenderer(this, 100, 35);
		Shoulders.addBox(0F, 0F, 0F, 14, 3, 20);
		Shoulders.setRotationPoint(10F, -6F, -10F);
		Shoulders.setTextureSize(64, 32);
		Shoulders.mirror = true;
		setRotation(Shoulders, 0F, 0F, 0.0743572F);
		BackPadding = new ModelRenderer(this, 158, 0);
		BackPadding.addBox(0F, 0F, 0F, 14, 12, 18);
		BackPadding.setRotationPoint(0F, -3F, -9F);
		BackPadding.setTextureSize(64, 32);
		BackPadding.mirror = true;
		setRotation(BackPadding, 0F, 0F, -0.0743572F);
		LowerBody = new ModelRenderer(this, 91, 0);
		LowerBody.addBox(0F, 0F, 0F, 14, 8, 18);
		LowerBody.setRotationPoint(-13F, 1F, -9F);
		LowerBody.setTextureSize(64, 32);
		LowerBody.mirror = true;
		setRotation(LowerBody, 0F, 0F, -0.1487144F);
		LeftForeThigh = new ModelRenderer(this, 73, 32);
		LeftForeThigh.addBox(0F, 0F, 0F, 6, 15, 5);
		LeftForeThigh.setRotationPoint(18F, -3F, 10F);
		LeftForeThigh.setTextureSize(64, 32);
		LeftForeThigh.mirror = true;
		setRotation(LeftForeThigh, 0F, 0F, 0.2230717F);
		LeftForeShin = new ModelRenderer(this, 72, 55);
		LeftForeShin.addBox(0F, 0F, 0F, 5, 13, 5);
		LeftForeShin.setRotationPoint(15F, 11F, 10F);
		LeftForeShin.setTextureSize(64, 32);
		LeftForeShin.mirror = true;
		setRotation(LeftForeShin, 0F, 0F, -0.3346075F);
		LeftForePaw = new ModelRenderer(this, 79, 75);
		LeftForePaw.addBox(0F, 0F, 0F, 9, 3, 5);
		LeftForePaw.setRotationPoint(19F, 21F, 10F);
		LeftForePaw.setTextureSize(64, 32);
		LeftForePaw.mirror = true;
		setRotation(LeftForePaw, 0F, 0F, 0F);
		RightForeThigh = new ModelRenderer(this, 50, 32);
		RightForeThigh.addBox(0F, 0F, 0F, 6, 15, 5);
		RightForeThigh.setRotationPoint(18F, -3F, -15F);
		RightForeThigh.setTextureSize(64, 32);
		RightForeThigh.mirror = true;
		setRotation(RightForeThigh, 0F, 0F, 0.2230717F);
		RightForeShin = new ModelRenderer(this, 50, 55);
		RightForeShin.addBox(0F, 0F, 0F, 5, 13, 5);
		RightForeShin.setRotationPoint(15F, 11F, -15F);
		RightForeShin.setTextureSize(64, 32);
		RightForeShin.mirror = true;
		setRotation(RightForeShin, 0F, 0F, -0.3346075F);
		RightForePaw = new ModelRenderer(this, 50, 75);
		RightForePaw.addBox(0F, 0F, 0F, 9, 3, 5);
		RightForePaw.setRotationPoint(19F, 21F, -15F);
		RightForePaw.setTextureSize(64, 32);
		RightForePaw.mirror = true;
		setRotation(RightForePaw, 0F, 0F, 0F);
		LeftBackThigh = new ModelRenderer(this, 21, 42);
		LeftBackThigh.addBox(0F, 0F, 0F, 6, 11, 4);
		LeftBackThigh.setRotationPoint(-10F, 1F, 9F);
		LeftBackThigh.setTextureSize(64, 32);
		LeftBackThigh.mirror = true;
		setRotation(LeftBackThigh, 0F, 0F, 0.0371786F);
		LeftBackShin = new ModelRenderer(this, 17, 59);
		LeftBackShin.addBox(0F, 0F, 0F, 4, 12, 4);
		LeftBackShin.setRotationPoint(-9F, 10F, 9F);
		LeftBackShin.setTextureSize(64, 32);
		LeftBackShin.mirror = true;
		setRotation(LeftBackShin, 0F, 0F, 0.3346075F);
		LeftBackPaw = new ModelRenderer(this, 21, 76);
		LeftBackPaw.addBox(0F, 0F, 0F, 6, 3, 4);
		LeftBackPaw.setRotationPoint(-13F, 21F, 9F);
		LeftBackPaw.setTextureSize(64, 32);
		LeftBackPaw.mirror = true;
		setRotation(LeftBackPaw, 0F, 0F, 0F);
		RightBackThigh = new ModelRenderer(this, 0, 42);
		RightBackThigh.addBox(0F, 0F, 0F, 6, 11, 4);
		RightBackThigh.setRotationPoint(-10F, 1F, -13F);
		RightBackThigh.setTextureSize(64, 32);
		RightBackThigh.mirror = true;
		setRotation(RightBackThigh, 0F, 0F, 0.0371786F);
		RightBackShin = new ModelRenderer(this, 0, 59);
		RightBackShin.addBox(0F, 0F, 0F, 4, 12, 4);
		RightBackShin.setRotationPoint(-9F, 10F, -13F);
		RightBackShin.setTextureSize(64, 32);
		RightBackShin.mirror = true;
		setRotation(RightBackShin, 0F, 0F, 0.3346075F);
		RightBackPaw = new ModelRenderer(this, 0, 76);
		RightBackPaw.addBox(0F, 0F, 0F, 6, 3, 4);
		RightBackPaw.setRotationPoint(-13F, 21F, -13F);
		RightBackPaw.setTextureSize(64, 32);
		RightBackPaw.mirror = true;
		setRotation(RightBackPaw, 0F, 0F, 0F);
		Neck = new ModelRenderer(this, 18, 105);
		Neck.addBox(0F, 0F, 0F, 3, 5, 8);
		Neck.setRotationPoint(24F, -3F, -4F);
		Neck.setTextureSize(64, 32);
		Neck.mirror = true;
		setRotation(Neck, 0F, 0F, 0F);
		Head = new ModelRenderer(this, 18, 85);
		Head.addBox(0F, 0F, 0F, 6, 6, 12);
		Head.setRotationPoint(27F, -4F, -6F);
		Head.setTextureSize(64, 32);
		Head.mirror = true;
		setRotation(Head, 0F, 0F, 0F);
		Nose = new ModelRenderer(this, 0, 85);
		Nose.addBox(0F, 0F, 0F, 3, 3, 4);
		Nose.setRotationPoint(33F, -2F, -2F);
		Nose.setTextureSize(64, 32);
		Nose.mirror = true;
		setRotation(Nose, 0F, 0F, 0.1115358F);
		LeftEar = new ModelRenderer(this, 0, 93);
		LeftEar.addBox(0F, 0F, 0F, 3, 5, 1);
		LeftEar.setRotationPoint(28F, -3F, 6F);
		LeftEar.setTextureSize(64, 32);
		LeftEar.mirror = true;
		setRotation(LeftEar, 0.4089647F, 0.1858931F, -0.0743572F);
		RightEar = new ModelRenderer(this, 9, 93);
		RightEar.addBox(0F, 0F, 0F, 3, 5, 1);
		RightEar.setRotationPoint(28F, -3.5F, -7F);
		RightEar.setTextureSize(64, 32);
		RightEar.mirror = true;
		setRotation(RightEar, -0.4089656F, -0.185895F, -0.0743634F);
		Tail = new ModelRenderer(this, 0, 100);
		Tail.addBox(0F, 0F, 0F, 2, 16, 3);
		Tail.setRotationPoint(-13F, 2F, -1F);
		Tail.setTextureSize(64, 32);
		Tail.mirror = true;
		setRotation(Tail, 0F, 0F, 0.6320364F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		GL11.glRotatef(90F, 0F, 1F, 0.0F);
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		ForeBody.render(f5);
		Shoulders.render(f5);
		BackPadding.render(f5);
		LowerBody.render(f5);
		LeftForeThigh.render(f5);
		LeftForeShin.render(f5);
		LeftForePaw.render(f5);
		RightForeThigh.render(f5);
		RightForeShin.render(f5);
		RightForePaw.render(f5);
		LeftBackThigh.render(f5);
		LeftBackShin.render(f5);
		LeftBackPaw.render(f5);
		RightBackThigh.render(f5);
		RightBackShin.render(f5);
		RightBackPaw.render(f5);
		Neck.render(f5);
		Head.render(f5);
		Nose.render(f5);
		LeftEar.render(f5);
		RightEar.render(f5);
		Tail.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		this.LeftForeShin.rotateAngleZ = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.RightForeShin.rotateAngleZ = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.LeftBackShin.rotateAngleZ = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		this.RightBackShin.rotateAngleZ = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
	}

}
