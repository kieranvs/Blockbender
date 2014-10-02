package kieranvs.avatar.entity;

import org.lwjgl.opengl.GL11;

import kieranvs.avatar.util.FileLocation;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderBadgerMole extends RenderLiving {
	private static final ResourceLocation res = new ResourceLocation(FileLocation.ENTITYTEXTURE + "badger_mole.png");
	
	public RenderBadgerMole(ModelBase par1ModelBase, float par2){
        super(par1ModelBase, par2);
    }

	@Override
    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        if(par1Entity instanceof EntityBadgerMole) return res;
        return null;
    }

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.doRender((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
		if ((((EntityBadgerMole) par1Entity).getName() != null) && ((EntityBadgerMole) par1Entity).getName() != "") {
			this.func_147906_a((EntityBadgerMole) par1Entity, ((EntityBadgerMole) par1Entity).getName(), par2, par4 + par1Entity.getMountedYOffset(), par6, 25);
		}
	}

}
