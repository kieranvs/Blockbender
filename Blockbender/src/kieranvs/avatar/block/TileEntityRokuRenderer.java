package kieranvs.avatar.block;

import kieranvs.avatar.util.FileLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class TileEntityRokuRenderer extends TileEntitySpecialRenderer {

	private ModelStatueRoku model;

	public TileEntityRokuRenderer() {
		model = new ModelStatueRoku();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double var2, double var4, double var6, float var8) {
		renderModelAt(tile, var2, var4, var6, var8);
	}

	public void renderModelAt(TileEntity tile, double var2, double var4, double var6, double var8) {
		int meta = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);

		GL11.glPushMatrix();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslatef((float) var2, (float) var4 + 1.0F, (float) var6 + 1.0F);
		GL11.glScalef(1.0F, -1.0F, -1.0F);
		GL11.glTranslatef(0.5F, 0.5F - 1F, 0.5F);
		float angle = 0;
		switch(meta){
		case 0:
			angle = 90;
			break;
		case 1:
			angle = 0;
			break;
		case 2:
			angle = 270;
			break;
		case 3:
			angle = 180;
			break;
		}
		GL11.glRotatef(angle, 0, (float)var4, 0);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(FileLocation.STATUETEXTURE + "AvatarRokuStatue.png"));

		model.render(0.0625F);

		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		GL11.glPopMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

}
