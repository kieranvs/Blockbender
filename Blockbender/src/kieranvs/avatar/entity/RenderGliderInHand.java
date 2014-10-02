package kieranvs.avatar.entity;

import kieranvs.avatar.util.FileLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public class RenderGliderInHand  implements IItemRenderer {

	private ModelAirStaffClosed model = new ModelAirStaffClosed();

	public RenderGliderInHand() {
	}

	public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
		return type == IItemRenderer.ItemRenderType.EQUIPPED;
	}

	public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType t, ItemStack i, IItemRenderer.ItemRendererHelper h){
		return false;
	}

	public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object... data){
		GL11.glRotatef(-15.0F, 2.0F, 3.0F, -5.0F);
		GL11.glTranslatef(0.3F, -0.3F, -0.1F);
		GL11.glScalef(2F, 2F, 1F);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(FileLocation.ENTITYTEXTURE + "Airbending staff closed.png"));

		this.model.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
	}
}