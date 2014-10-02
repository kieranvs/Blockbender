package kieranvs.avatar.entity;

import kieranvs.avatar.client.SoundHandler;
import kieranvs.avatar.util.FileLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.entity.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;

public class RenderGliderActive extends Render {
	
	ModelBiped player = new ModelBiped();
	private ModelBiped modelArmorChestplate = new ModelBiped(1.0F);
	private ModelBiped modelArmor = new ModelBiped(0.5F);
	private ModelBiped modelbiped;
	private ModelGlider model = new ModelGlider();
	
	public void doRender(Entity ent, double d0, double d1, double d2, float f, float f1)
	{
		EntityGlider entity = (EntityGlider)ent;
		entity.renderDistanceWeight = 300;
		if(entity.ridingEntity != null) entity.updateRotations();
		GL11.glPushMatrix();
		float deltaX = (float) (Minecraft.getMinecraft().thePlayer.posX - entity.posX);
		float deltaY = (float) (Minecraft.getMinecraft().thePlayer.posY - entity.posY + 1.35);
		float deltaZ = (float) (Minecraft.getMinecraft().thePlayer.posZ - entity.posZ);
		GL11.glTranslatef(-deltaX, -deltaY, -deltaZ);
		
		try {
			GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			if ((Math.abs(entity.ridingEntity.motionX) < 0.01D) && (Math.abs(entity.ridingEntity.motionZ) < 0.01D))
			{
				GL11.glRotatef((float)entity.rotationYAW, 0.0F, 1.0F, 0.0F);
			} else {
				GL11.glRotatef((float)entity.rotationYAW, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef((float)entity.rotationRoll, 0.0F, 0.0F, 1.0F);
			}
			GL11.glRotatef((float)entity.rotationPitch, 1.0F, 0.0F, 0.0F);

		}
		catch (Throwable ex)
		{
			entity.setDead();

			GL11.glPopMatrix();
			return;
		}
		float errr = -1/32f;
		GL11.glTranslatef(errr, -0.4F, 0.4F);
		GL11.glScalef(1.2f, 1.2f, 1.2f);
		try
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(((EntityGlider) ent).getTextureFile()));
			this.model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		} catch (Throwable ex) {
		}
		GL11.glScalef(1/1.2f, 1/1.2f, 1/1.2f);
		GL11.glTranslatef(-errr, 0.4F, -0.4F);
		try
		{
			if(entity.ridingEntity instanceof EntityPlayer){
				Minecraft.getMinecraft().renderEngine.bindTexture(((AbstractClientPlayer) entity.ridingEntity).getLocationSkin());
			}
		}
		catch (Throwable ex)
		{
		}
		this.player.isChild = false;

		GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);

		this.player.bipedHead.render(0.0625F);
		this.player.bipedBody.render(0.0625F);
		this.player.bipedRightArm.rotateAngleX = 160.0F;
		this.player.bipedRightArm.rotateAngleZ = -0.35F;
		this.player.bipedRightArm.render(0.0625F);
		this.player.bipedLeftArm.rotateAngleX = 160.0F;
		this.player.bipedLeftArm.rotateAngleZ = 0.35F;
		this.player.bipedLeftArm.render(0.0625F);
		this.player.bipedRightLeg.render(0.0625F);
		this.player.bipedLeftLeg.render(0.0625F);
		this.player.bipedHeadwear.render(0.0625F);

		for (int i = 0; i < 4; i++) {
			ItemStack itemstack = ((EntityLivingBase)entity.ridingEntity).getEquipmentInSlot(i+1);

			if (itemstack != null) {
				Item item = itemstack.getItem();

				if ((item instanceof ItemArmor)) {
					ItemArmor itemarmor = (ItemArmor)item;

					this.modelbiped = (i == 2 ? this.modelArmor : this.modelArmorChestplate);

					this.modelbiped.bipedHead.showModel = (i == 0);
					this.modelbiped.bipedHeadwear.showModel = (i == 0);
					this.modelbiped.bipedBody.showModel = ((i == 1) || (i == 2));
					this.modelbiped.bipedRightArm.showModel = (i == 1);
					this.modelbiped.bipedLeftArm.showModel = (i == 1);
					this.modelbiped.bipedRightLeg.showModel = ((i == 2) || (i == 3));
					this.modelbiped.bipedLeftLeg.showModel = ((i == 2) || (i == 3));
					this.modelbiped = ForgeHooksClient.getArmorModel((EntityLivingBase)entity.ridingEntity, itemstack, i, this.modelbiped);

					f1 = 1.0F;

					int j = itemarmor.getColor(itemstack);
					if (j != -1) {
						float f2 = (j >> 16 & 0xFF) / 255.0F;
						float f3 = (j >> 8 & 0xFF) / 255.0F;
						float f4 = (j & 0xFF) / 255.0F;
						GL11.glColor3f(f1 * f2, f1 * f3, f1 * f4);
					}
					this.modelbiped.bipedHead.render(0.0625F);
					this.modelbiped.bipedBody.render(0.0625F);
					this.modelbiped.bipedRightArm.rotateAngleX = 160.0F;
					this.modelbiped.bipedRightArm.rotateAngleZ = -0.35F;
					this.modelbiped.bipedRightArm.render(0.0625F);
					this.modelbiped.bipedLeftArm.rotateAngleX = 160.0F;
					this.modelbiped.bipedLeftArm.rotateAngleZ = 0.35F;
					this.modelbiped.bipedLeftArm.render(0.0625F);
					this.modelbiped.bipedRightLeg.render(0.0625F);
					this.modelbiped.bipedLeftLeg.render(0.0625F);
					this.modelbiped.bipedHeadwear.render(0.0625F);
					GL11.glColor3f(1.0F, 1.0F, 1.0F);
				}
			}
		}
		GL11.glTranslatef(deltaX, deltaY, deltaZ);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return null;
	}

}
