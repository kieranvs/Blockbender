package kieranvs.avatar.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAirStaffOpen extends ModelBase {
	
	ModelRenderer Staff;
	ModelRenderer Left_small_wing;
	ModelRenderer Right_large_wing;
	ModelRenderer Left_large_wing;
	ModelRenderer Right_small_wing;

	public ModelAirStaffOpen() {
		textureWidth = 64;
		textureHeight = 64;

		Staff = new ModelRenderer(this, 0, 0);
		Staff.addBox(0F, 0F, 0F, 1, 1, 40);
		Staff.setRotationPoint(0F, 7F, -17F);
		Staff.setTextureSize(64, 64);
		Staff.mirror = true;
		setRotation(Staff, 0F, 0F, 0F);
		Left_small_wing = new ModelRenderer(this, 0, 58);
		Left_small_wing.addBox(0F, 0F, 0F, 9, 0, 5);
		Left_small_wing.setRotationPoint(0F, 6.8F, 12F);
		Left_small_wing.setTextureSize(64, 64);
		Left_small_wing.mirror = true;
		setRotation(Left_small_wing, 0F, -0.4461433F, 0F);
		Left_small_wing.mirror = false;
		Right_large_wing = new ModelRenderer(this, -15, 0);
		Right_large_wing.addBox(0F, 0F, 0F, 20, 0, 15);
		Right_large_wing.setRotationPoint(-18F, 6.9F, -7F);
		Right_large_wing.setTextureSize(64, 64);
		Right_large_wing.mirror = true;
		setRotation(Right_large_wing, 0F, 0.4089647F, 0F);
		Left_large_wing = new ModelRenderer(this, 0, 42);
		Left_large_wing.addBox(0F, 0F, 0F, 20, 0, 15);
		Left_large_wing.setRotationPoint(1F, 6.8F, -15F);
		Left_large_wing.setTextureSize(64, 64);
		Left_large_wing.mirror = true;
		setRotation(Left_large_wing, 0F, -0.4089647F, 0F);
		Right_small_wing = new ModelRenderer(this, 0, 58);
		Right_small_wing.addBox(0F, 0F, 0F, 9, 0, 5);
		Right_small_wing.setRotationPoint(-7F, 6.9F, 16F);
		Right_small_wing.setTextureSize(64, 64);
		Right_small_wing.mirror = true;
		setRotation(Right_small_wing, 0F, 0.4461433F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Staff.render(f5);
		Left_small_wing.render(f5);
		Right_large_wing.render(f5);
		Left_large_wing.render(f5);
		Right_small_wing.render(f5);
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
