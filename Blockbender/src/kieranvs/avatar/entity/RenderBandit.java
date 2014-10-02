package kieranvs.avatar.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderBandit extends RenderBiped {

	public RenderBandit(ModelBiped par1ModelBiped, float par2) {
		super(par1ModelBiped, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		if(par1Entity instanceof EntityBandit) return ((EntityBandit)par1Entity).res;
		if(par1Entity instanceof EntityFireBandit) return ((EntityFireBandit)par1Entity).res;
		if(par1Entity instanceof EntityWaterBandit) return ((EntityWaterBandit)par1Entity).res;
		if(par1Entity instanceof EntityEarthBandit) return ((EntityEarthBandit)par1Entity).res;
		if(par1Entity instanceof EntityArcherBandit) return ((EntityArcherBandit)par1Entity).res;
        System.out.println("RenderBandit needs to load the texture!");
		return null;
	}

}
