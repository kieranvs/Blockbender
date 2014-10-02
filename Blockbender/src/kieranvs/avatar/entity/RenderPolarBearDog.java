package kieranvs.avatar.entity;

import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.util.FileLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.ResourceLocation;

public class RenderPolarBearDog extends RenderLiving {
	
	private static final ResourceLocation res = new ResourceLocation(FileLocation.ENTITYTEXTURE + "EntityPolarBearDog.png");
	
	public RenderPolarBearDog(ModelBase par1ModelBase, float par2){
        super(par1ModelBase, par2);
    }

	@Override
    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        if(par1Entity instanceof EntityPolarBearDog) return res;
        return null;
    }

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.doRender((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
		if ((((EntityPolarBearDog) par1Entity).getName() != null) && ((EntityPolarBearDog) par1Entity).getName() != "") {
			this.func_147906_a((EntityPolarBearDog) par1Entity, ((EntityPolarBearDog) par1Entity).getName(), par2, par4 + par1Entity.getMountedYOffset(), par6, 25);
		}
	}

}