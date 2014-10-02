package kieranvs.avatar.container;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

@SideOnly(Side.CLIENT)
public class GuiItemInventory extends GuiContainer {

	private int inventoryRows;
	private ResourceLocation background;
	
	public IInventoryItem s;

	public GuiItemInventory(EntityPlayer p, InventoryPlayer inventoryPlayer, IInventoryItem inventorySatchel)
	{
		super(new ContainerItem(p, inventoryPlayer, inventorySatchel));
		this.s = inventorySatchel;
		this.inventoryRows = (inventorySatchel.getSizeInventory() / 9);
		this.ySize = (114 + this.inventoryRows * 18);
		this.background = new ResourceLocation("textures/gui/container/generic_54.png");
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRendererObj.drawString("Satchel", 8, 6, 4210752);
		this.fontRendererObj.drawString("Inventory", 8, this.ySize - 94, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(background);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.inventoryRows * 18 + 17);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop + this.inventoryRows * 18 + 17, 0, 126, this.xSize, 96);
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		s.closeInventory();
	}
	
}