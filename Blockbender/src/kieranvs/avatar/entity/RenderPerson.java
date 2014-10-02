package kieranvs.avatar.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderPerson extends RenderBiped {

	public RenderPerson(ModelBiped par1ModelBiped, float par2) {
		super(par1ModelBiped, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		if(par1Entity instanceof EntityPerson) return ((EntityPerson)par1Entity).res;
        return null;
	}

}
