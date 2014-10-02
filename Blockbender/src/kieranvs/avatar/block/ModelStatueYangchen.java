package kieranvs.avatar.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelStatueYangchen extends ModelBase{

	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer base;
	ModelRenderer upperBase;
	ModelRenderer rightdress;
	ModelRenderer leftdress;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer middlearm;
	ModelRenderer RightShoulder;
	ModelRenderer LeftShoulder;
	ModelRenderer rightsleeve;
	ModelRenderer leftsleeve;
	ModelRenderer BackCloak;
	ModelRenderer RightCloak;
	ModelRenderer LeftCloak;
	ModelRenderer hairfront;
	ModelRenderer hairback;

	public ModelStatueYangchen(){
		textureWidth = 128;
		textureHeight = 128;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4F, -8F, -4F, 8, 8, 8);
		head.setRotationPoint(0F, -3F, 0F);
		head.setTextureSize(128, 128);
		head.mirror = true;
		setRotation(head, 0F, 0F, 0F);
		body = new ModelRenderer(this, 16, 16);
		body.addBox(-4F, 0F, -2F, 8, 12, 4);
		body.setRotationPoint(0F, -3F, 0F);
		body.setTextureSize(128, 128);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		rightleg = new ModelRenderer(this, 0, 16);
		rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		rightleg.setRotationPoint(-2F, 9F, 0F);
		rightleg.setTextureSize(128, 128);
		rightleg.mirror = true;
		setRotation(rightleg, 0F, 0F, 0F);
		leftleg = new ModelRenderer(this, 0, 16);
		leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
		leftleg.setRotationPoint(2F, 9F, 0F);
		leftleg.setTextureSize(128, 128);
		leftleg.mirror = true;
		setRotation(leftleg, 0F, 0F, 0F);
		base = new ModelRenderer(this, 79, 0);
		base.addBox(-7F, 2F, -5F, 14, 1, 10);
		base.setRotationPoint(0F, 21F, 0F);
		base.setTextureSize(128, 128);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		upperBase = new ModelRenderer(this, 87, 12);
		upperBase.addBox(-6F, 0F, -4F, 12, 2, 8);
		upperBase.setRotationPoint(0F, 21F, 0F);
		upperBase.setTextureSize(128, 128);
		upperBase.mirror = true;
		setRotation(upperBase, 0F, 0F, 0F);
		rightdress = new ModelRenderer(this, 11, 33);
		rightdress.addBox(0F, 0F, -0.1F, 1, 12, 4);
		rightdress.setRotationPoint(3F, 9F, -2F);
		rightdress.setTextureSize(128, 128);
		rightdress.mirror = true;
		setRotation(rightdress, 0F, 0F, -0.0743572F);
		leftdress = new ModelRenderer(this, 0, 33);
		leftdress.addBox(0F, 0F, -0.1F, 1, 12, 4);
		leftdress.setRotationPoint(-4F, 9F, -2.1F);
		leftdress.setTextureSize(128, 128);
		leftdress.mirror = true;
		setRotation(leftdress, 0F, 0F, 0.0743572F);
		rightarm = new ModelRenderer(this, 40, 16);
		rightarm.addBox(-2F, -1F, -1F, 3, 10, 4);
		rightarm.setRotationPoint(5F, -1F, 0F);
		rightarm.setTextureSize(128, 128);
		rightarm.mirror = true;
		setRotation(rightarm, -0.8922867F, 0F, 0F);
		leftarm = new ModelRenderer(this, 40, 16);
		leftarm.addBox(-2F, -1F, -1F, 3, 10, 4);
		leftarm.setRotationPoint(-4F, -1F, 0F);
		leftarm.setTextureSize(128, 128);
		leftarm.mirror = true;
		setRotation(leftarm, -0.8922867F, 0F, 0F);
		middlearm = new ModelRenderer(this, 0, 50);
		middlearm.addBox(-2F, -2F, -2F, 6, 4, 4);
		middlearm.setRotationPoint(-1F, 4.2F, -4.8F);
		middlearm.setTextureSize(128, 128);
		middlearm.mirror = true;
		setRotation(middlearm, 0.6866683F, 0F, 0F);
		RightShoulder = new ModelRenderer(this, 0, 120);
		RightShoulder.addBox(-6F, 0F, 0F, 6, 2, 6);
		RightShoulder.setRotationPoint(-4F, -3F, -2F);
		RightShoulder.setTextureSize(128, 128);
		RightShoulder.mirror = true;
		setRotation(RightShoulder, -0.0743572F, 0F, -0.1858931F);
		LeftShoulder = new ModelRenderer(this, 0, 120);
		LeftShoulder.addBox(0F, 0F, 0F, 6, 2, 6);
		LeftShoulder.setRotationPoint(4F, -3F, -2F);
		LeftShoulder.setTextureSize(128, 128);
		LeftShoulder.mirror = true;
		setRotation(LeftShoulder, -0.0743572F, 0F, 0.1858931F);
		rightsleeve = new ModelRenderer(this, 40, 33);
		rightsleeve.addBox(0F, -1F, -1F, 1, 10, 4);
		rightsleeve.setRotationPoint(6F, -1F, 0F);
		rightsleeve.setTextureSize(128, 128);
		rightsleeve.mirror = true;
		setRotation(rightsleeve, -0.8922867F, 0F, 0.1487144F);
		leftsleeve = new ModelRenderer(this, 40, 33);
		leftsleeve.addBox(0F, -1F, -1F, 1, 10, 4);
		leftsleeve.setRotationPoint(-7F, -0.8F, 0F);
		leftsleeve.setTextureSize(128, 128);
		leftsleeve.mirror = true;
		setRotation(leftsleeve, -0.8922867F, 0F, -0.1487144F);
		BackCloak = new ModelRenderer(this, 0, 93);
		BackCloak.addBox(-7F, 0F, 0F, 14, 22, 1);
		BackCloak.setRotationPoint(0F, -2F, 3F);
		BackCloak.setTextureSize(128, 128);
		BackCloak.mirror = true;
		setRotation(BackCloak, 0F, 0F, 0F);
		RightCloak = new ModelRenderer(this, 31, 90);
		RightCloak.addBox(0F, 0F, -3F, 1, 18, 6);
		RightCloak.setRotationPoint(-8F, 0F, 1F);
		RightCloak.setTextureSize(128, 128);
		RightCloak.mirror = true;
		setRotation(RightCloak, 0F, 0.42869F, 0F);
		LeftCloak = new ModelRenderer(this, 48, 90);
		LeftCloak.addBox(0F, 0F, -3F, 1, 18, 6);
		LeftCloak.setRotationPoint(7F, 0F, 0F);
		LeftCloak.setTextureSize(128, 128);
		LeftCloak.mirror = true;
		setRotation(LeftCloak, 0F, -0.426418F, 0F);
		hairfront = new ModelRenderer(this, 50, 50);
		hairfront.addBox(-5F, 0F, 0F, 10, 9, 4);
		hairfront.setRotationPoint(0F, -12F, 0F);
		hairfront.setTextureSize(128, 128);
		hairfront.mirror = true;
		setRotation(hairfront, 0F, 0F, 0F);
		hairback = new ModelRenderer(this, 50, 65);
		hairback.addBox(-4F, 0F, 0F, 8, 13, 1);
		hairback.setRotationPoint(0F, -11F, 4F);
		hairback.setTextureSize(128, 128);
		hairback.mirror = true;
		setRotation(hairback, 0F, 0F, 0F);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		render(f5);
	}


	public void render(float f5){
		head.render(f5);
		body.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
		base.render(f5);
		upperBase.render(f5);
		rightdress.render(f5);
		leftdress.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
		middlearm.render(f5);
		RightShoulder.render(f5);
		LeftShoulder.render(f5);
		rightsleeve.render(f5);
		leftsleeve.render(f5);
		BackCloak.render(f5);
		RightCloak.render(f5);
		LeftCloak.render(f5);
		hairfront.render(f5);
		hairback.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z){
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par6Entity){
		super.setRotationAngles(f, f1, f2, f3, f4, f5, par6Entity);
	}

}
