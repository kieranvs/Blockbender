package kieranvs.avatar.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntitySpiritPortalRenderer extends TileEntitySpecialRenderer{
	
    private static final ResourceLocation textureLocation = new ResourceLocation("textures/entity/beacon_beam.png");

    public void renderTileEntityBeaconAt(TileEntitySpiritPortal par1TileEntityBeacon, double par2, double par4, double par6, float par8) {

        if (true) {
            Tessellator tessellator = Tessellator.instance;
            this.bindTexture(textureLocation);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, 10497.0F);
            GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, 10497.0F);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDepthMask(true);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            float f2 = (float)par1TileEntityBeacon.getWorldObj().getTotalWorldTime() + par8;
            float f3 = -f2 * 0.2F - (float)MathHelper.floor_float(-f2 * 0.1F);
            byte b0 = 1;
            double d3 = (double)f2 * 0.025D * (1.0D - (double)(b0 & 1) * 2.5D);
            tessellator.startDrawingQuads();
            tessellator.setColorRGBA(255, 0, 255, 32);
            double radius = (double)b0 * 1D;
            double d5 = 0.5D + Math.cos(d3 + 2.356194490192345D) * radius;
            double d6 = 0.5D + Math.sin(d3 + 2.356194490192345D) * radius;
            double d7 = 0.5D + Math.cos(d3 + (Math.PI / 4D)) * radius;
            double d8 = 0.5D + Math.sin(d3 + (Math.PI / 4D)) * radius;
            double d9 = 0.5D + Math.cos(d3 + 3.9269908169872414D) * radius;
            double d10 = 0.5D + Math.sin(d3 + 3.9269908169872414D) * radius;
            double d11 = 0.5D + Math.cos(d3 + 5.497787143782138D) * radius;
            double d12 = 0.5D + Math.sin(d3 + 5.497787143782138D) * radius;
            double d13 = (double)(256.0F);
            double d14 = 0.0D;
            double d15 = 1.0D;
            double d16 = (double)(-1.0F + f3);
            double d17 = (double)(256.0F) * (0.5D / radius) + d16;
            tessellator.addVertexWithUV(par2 + d5, par4 + d13, par6 + d6, d15, d17);
            tessellator.addVertexWithUV(par2 + d5, par4, par6 + d6, d15, d16);
            tessellator.addVertexWithUV(par2 + d7, par4, par6 + d8, d14, d16);
            tessellator.addVertexWithUV(par2 + d7, par4 + d13, par6 + d8, d14, d17);
            tessellator.addVertexWithUV(par2 + d11, par4 + d13, par6 + d12, d15, d17);
            tessellator.addVertexWithUV(par2 + d11, par4, par6 + d12, d15, d16);
            tessellator.addVertexWithUV(par2 + d9, par4, par6 + d10, d14, d16);
            tessellator.addVertexWithUV(par2 + d9, par4 + d13, par6 + d10, d14, d17);
            tessellator.addVertexWithUV(par2 + d7, par4 + d13, par6 + d8, d15, d17);
            tessellator.addVertexWithUV(par2 + d7, par4, par6 + d8, d15, d16);
            tessellator.addVertexWithUV(par2 + d11, par4, par6 + d12, d14, d16);
            tessellator.addVertexWithUV(par2 + d11, par4 + d13, par6 + d12, d14, d17);
            tessellator.addVertexWithUV(par2 + d9, par4 + d13, par6 + d10, d15, d17);
            tessellator.addVertexWithUV(par2 + d9, par4, par6 + d10, d15, d16);
            tessellator.addVertexWithUV(par2 + d5, par4, par6 + d6, d14, d16);
            tessellator.addVertexWithUV(par2 + d5, par4 + d13, par6 + d6, d14, d17);
            tessellator.draw();
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glDepthMask(false);
            tessellator.startDrawingQuads();
            tessellator.setColorRGBA(255, 255, 255, 32);
            double d18 = 0.2D;
            double d19 = 0.2D;
            double d20 = 0.8D;
            double d21 = 0.2D;
            double d22 = 0.2D;
            double d23 = 0.8D;
            double d24 = 0.8D;
            double d25 = 0.8D;
            double d26 = (double)(256.0F);
            double d27 = 0.0D;
            double d28 = 1.0D;
            double d29 = (double)(-1.0F + f3);
            double d30 = (double)(256.0F) + d29;
            tessellator.addVertexWithUV(par2 + d18, par4 + d26, par6 + d19, d28, d30);
            tessellator.addVertexWithUV(par2 + d18, par4, par6 + d19, d28, d29);
            tessellator.addVertexWithUV(par2 + d20, par4, par6 + d21, d27, d29);
            tessellator.addVertexWithUV(par2 + d20, par4 + d26, par6 + d21, d27, d30);
            tessellator.addVertexWithUV(par2 + d24, par4 + d26, par6 + d25, d28, d30);
            tessellator.addVertexWithUV(par2 + d24, par4, par6 + d25, d28, d29);
            tessellator.addVertexWithUV(par2 + d22, par4, par6 + d23, d27, d29);
            tessellator.addVertexWithUV(par2 + d22, par4 + d26, par6 + d23, d27, d30);
            tessellator.addVertexWithUV(par2 + d20, par4 + d26, par6 + d21, d28, d30);
            tessellator.addVertexWithUV(par2 + d20, par4, par6 + d21, d28, d29);
            tessellator.addVertexWithUV(par2 + d24, par4, par6 + d25, d27, d29);
            tessellator.addVertexWithUV(par2 + d24, par4 + d26, par6 + d25, d27, d30);
            tessellator.addVertexWithUV(par2 + d22, par4 + d26, par6 + d23, d28, d30);
            tessellator.addVertexWithUV(par2 + d22, par4, par6 + d23, d28, d29);
            tessellator.addVertexWithUV(par2 + d18, par4, par6 + d19, d27, d29);
            tessellator.addVertexWithUV(par2 + d18, par4 + d26, par6 + d19, d27, d30);
            tessellator.draw();
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDepthMask(true);
        }
    }

    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderTileEntityBeaconAt((TileEntitySpiritPortal)par1TileEntity, par2, par4, par6, par8);
    }
}
