package kieranvs.avatar.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAirStaffClosed extends ModelBase {
	
	ModelRenderer Shaft; 
	ModelRenderer Knob1; 
	ModelRenderer Knob2; 
	ModelRenderer Knob3; 
	ModelRenderer Knob4; 
	ModelRenderer Knob5;
	ModelRenderer Knob6; 
	ModelRenderer Knob7; 
	ModelRenderer Knob8; 

	public ModelAirStaffClosed() {
		textureWidth = 64;
		textureHeight = 64;

		Shaft = new ModelRenderer(this, 0, 0);
		Shaft.addBox(0F, 0F, 0F, 1, 40, 1);
		Shaft.setRotationPoint(0F, -16F, 0F);
		Shaft.setTextureSize(64, 64);
		Shaft.mirror = true;
		setRotation(Shaft, 0F, 0F, 0F);
		Knob1 = new ModelRenderer(this, 0, 0);
		Knob1.addBox(0F, 0F, 0F, 1, 2, 1);
		Knob1.setRotationPoint(0F, -7.9F, 0F);
		Knob1.setTextureSize(64, 64);
		Knob1.mirror = true;
		setRotation(Knob1, 0F, 0F, 0.4833219F);
		Knob2 = new ModelRenderer(this, 0, 0);
		Knob2.addBox(0F, 0F, 0F, 1, 2, 1);
		Knob2.setRotationPoint(-0.9F, -6.1F, 0F);
		Knob2.setTextureSize(64, 64);
		Knob2.mirror = true;
		setRotation(Knob2, 0F, 0F, -0.4833219F);
		Knob3 = new ModelRenderer(this, 0, 0);
		Knob3.addBox(-1F, 0F, 0F, 1, 2, 1);
		Knob3.setRotationPoint(1F, -7.9F, 0F);
		Knob3.setTextureSize(64, 64);
		Knob3.mirror = true;
		setRotation(Knob3, 0F, 0F, -0.4833219F);
		Knob4 = new ModelRenderer(this, 0, 0);
		Knob4.addBox(-1F, 0F, 0F, 1, 2, 1);
		Knob4.setRotationPoint(1.9F, -6.1F, 0F);
		Knob4.setTextureSize(64, 64);
		Knob4.mirror = true;
		setRotation(Knob4, 0F, 0F, 0.4833219F);
		Knob5 = new ModelRenderer(this, 0, 0);
		Knob5.addBox(0F, 0F, 0F, 1, 2, 1);
		Knob5.setRotationPoint(0F, 14.1F, 0F);
		Knob5.setTextureSize(64, 64);
		Knob5.mirror = true;
		setRotation(Knob5, 0F, 0F, 0.4833219F);
		Knob6 = new ModelRenderer(this, 0, 0);
		Knob6.addBox(0F, 0F, 0F, 1, 2, 1);
		Knob6.setRotationPoint(-0.9F, 15.9F, 0F);
		Knob6.setTextureSize(64, 64);
		Knob6.mirror = true;
		setRotation(Knob6, 0F, 0F, -0.4833219F);
		Knob7 = new ModelRenderer(this, 0, 0);
		Knob7.addBox(-1F, 0F, 0F, 1, 2, 1);
		Knob7.setRotationPoint(1F, 14.1F, 0F);
		Knob7.setTextureSize(64, 64);
		Knob7.mirror = true;
		setRotation(Knob7, 0F, 0F, -0.4833219F);
		Knob8 = new ModelRenderer(this, 0, 0);
		Knob8.addBox(-1F, 0F, 0F, 1, 2, 1);
		Knob8.setRotationPoint(1.9F, 15.9F, 0F);
		Knob8.setTextureSize(64, 64);
		Knob8.mirror = true;
		setRotation(Knob8, 0F, 0F, 0.4833219F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Shaft.render(f5);
		Knob1.render(f5);
		Knob2.render(f5);
		Knob3.render(f5);
		Knob4.render(f5);
		Knob5.render(f5);
		Knob6.render(f5);
		Knob7.render(f5);
		Knob8.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
