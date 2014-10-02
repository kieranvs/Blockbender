package kieranvs.avatar.block;

import kieranvs.avatar.util.FileLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class TileEntityKyoshiRenderer extends TileEntitySpecialRenderer {

	private ModelStatueKyoshi model;

	public TileEntityKyoshiRenderer() {
		model = new ModelStatueKyoshi();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double var2, double var4, double var6, float var8) {
		this.renderModelAt(tile, var2, var4, var6, var8);
	}

	public void renderModelAt(TileEntity tile, double var2, double var4, double var6, float var8) {
		int meta = tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord);
		
		
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(FileLocation.STATUETEXTURE + "AvatarKyoshiStatue.png"));

		GL11.glPushMatrix();
		GL11.glTranslatef((float) var2 + 0.5F, (float) var4 + 1.5F, (float) var6 + 0.5F);
		GL11.glScalef(1F, -1F, -1F);
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

		model.render(0.0625F);
		GL11.glPopMatrix();
	}

}
