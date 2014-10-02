package kieranvs.avatar.entity;

import kieranvs.avatar.util.FileLocation;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderAirBall extends Render {

	protected ModelBase modelBall;
	
	private static final ResourceLocation res = new ResourceLocation(FileLocation.ENTITYTEXTURE + "rock.png");

	public RenderAirBall() {
		this.shadowSize = 0.4F;
		this.modelBall = new ModelAirBall();
	}

	public void renderAirBall(EntityAirBall par1Entity, double par2, double par4, double par6, float par8, float par9) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) par2, (float) par4, (float) par6);
		float var20 = 1F;
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glScalef(var20, var20, var20);
		GL11.glNormal3f(var20, 0.0F, 0.0F);

		this.modelBall.render(par1Entity, 0F, 0F, -0.1F, 0F, 0F, 0.0625F);

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
	}

	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
		this.renderAirBall((EntityAirBall) par1Entity, par2, par4, par6, par8, par9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		if(entity instanceof EntityAirBall) return res;
        return null;
	}
}
