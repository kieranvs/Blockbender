package kieranvs.avatar.entity;

import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.util.FileLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderOtterPenguin extends RenderLiving {
	
	private static final ResourceLocation res = new ResourceLocation(FileLocation.ENTITYTEXTURE + "OtterPenguin.png");
	
	public RenderOtterPenguin(ModelBase par1ModelBase, float par2){
        super(par1ModelBase, par2);
    }

	@Override
    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        if(par1Entity instanceof EntityOtterPenguin) return res;
        return null;
    }

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.doRender((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
	}

}