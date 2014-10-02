package kieranvs.avatar.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBison extends ModelBase {

	ModelRenderer Body;
	ModelRenderer Head;
	ModelRenderer MidLegRight;
	ModelRenderer MidLegLeft;
	ModelRenderer BackLegRight;
	ModelRenderer BackLegLeft;
	ModelRenderer FrontLegRight;
	ModelRenderer FrontLegLeft;

	public ModelBison() {
		textureWidth = 512;
		textureHeight = 512;
		setTextureOffset("MidLegRight.Foot", 50, 100);
		setTextureOffset("MidLegRight.Leg", 50, 0);
		setTextureOffset("MidLegLeft.Foot", 50, 100);
		setTextureOffset("MidLegLeft.Leg", 50, 0);
		setTextureOffset("BackLegRight.Foot", 50, 100);
		setTextureOffset("BackLegRight.Leg", 50, 0);
		setTextureOffset("BackLegLeft.Foot", 50, 100);
		setTextureOffset("BackLegLeft.Leg", 50, 0);
		setTextureOffset("FrontLegRight.Foot", 50, 100);
		setTextureOffset("FrontLegRight.Leg", 50, 0);
		setTextureOffset("FrontLegLeft.Foot", 50, 100);
		setTextureOffset("FrontLegLeft.Leg", 50, 0);

		Body = new ModelRenderer(this, 150, 0);
		Body.addBox(0F, 0F, 0F, 54, 25, 70);
		Body.setRotationPoint(-27F, -25F, -33F);
		Body.setTextureSize(512, 512);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		Head = new ModelRenderer(this, 148, 106);
		Head.addBox(0F, 0F, 0F, 27, 17, 17);
		Head.setRotationPoint(-13.5F, -32F, -50F);
		Head.setTextureSize(512, 512);
		Head.mirror = true;
		setRotation(Head, 0F, 0F, 0F);
		MidLegRight = new ModelRenderer(this, "MidLegRight");
		MidLegRight.setRotationPoint(-23F, 0F, -4F);
		setRotation(MidLegRight, 0F, 0F, 0F);
		MidLegRight.mirror = true;
		MidLegRight.addBox("Foot", -1F, 21F, -4F, 16, 3, 16);
		MidLegRight.addBox("Leg", 0F, 0F, 0F, 14, 21, 12);
		MidLegLeft = new ModelRenderer(this, "MidLegLeft");
		MidLegLeft.setRotationPoint(9F, 0F, -4F);
		setRotation(MidLegLeft, 0F, 0F, 0F);
		MidLegLeft.mirror = true;
		MidLegLeft.addBox("Foot", -1F, 21F, -4F, 16, 3, 16);
		MidLegLeft.addBox("Leg", 0F, 0F, 0F, 14, 21, 12);
		BackLegRight = new ModelRenderer(this, "BackLegRight");
		BackLegRight.setRotationPoint(-23F, 0F, 20F);
		setRotation(BackLegRight, 0F, 0F, 0F);
		BackLegRight.mirror = true;
		BackLegRight.addBox("Foot", -1F, 21F, -4F, 16, 3, 16);
		BackLegRight.addBox("Leg", 0F, 0F, 0F, 14, 21, 12);
		BackLegLeft = new ModelRenderer(this, "BackLegLeft");
		BackLegLeft.setRotationPoint(9F, 0F, 20F);
		setRotation(BackLegLeft, 0F, 0F, 0F);
		BackLegLeft.mirror = true;
		BackLegLeft.addBox("Foot", -1F, 21F, -4F, 16, 3, 16);
		BackLegLeft.addBox("Leg", 0F, 0F, 0F, 14, 21, 12);
		FrontLegRight = new ModelRenderer(this, "FrontLegRight");
		FrontLegRight.setRotationPoint(-23F, 0F, -28F);
		setRotation(FrontLegRight, 0F, 0F, 0F);
		FrontLegRight.mirror = true;
		FrontLegRight.addBox("Foot", -1F, 21F, -4F, 16, 3, 16);
		FrontLegRight.addBox("Leg", 0F, 0F, 0F, 14, 21, 12);
		FrontLegLeft = new ModelRenderer(this, "FrontLegLeft");
		FrontLegLeft.setRotationPoint(9F, 0F, -28F);
		setRotation(FrontLegLeft, 0F, 0F, 0F);
		FrontLegLeft.mirror = true;
		FrontLegLeft.addBox("Foot", -1F, 21F, -4F, 16, 3, 16);
		FrontLegLeft.addBox("Leg", 0F, 0F, 0F, 14, 21, 12);
	}

	@Override
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Body.render(par7);
		Head.render(par7);
		MidLegRight.render(par7);
		MidLegLeft.render(par7);
		BackLegRight.render(par7);
		BackLegLeft.render(par7);
		FrontLegRight.render(par7);
		FrontLegLeft.render(par7);
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
