package kieranvs.avatar.entity;

import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.item.ItemAirStaff;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class ItemRenderAirStaff implements IItemRenderer {
	
	protected ModelAirStaffClosed airStaffModelClosed;
	protected ModelAirStaffOpen airStaffModelOpen;
	private ResourceLocation openTexture;
	private ResourceLocation closedTexture;
	
	public ItemRenderAirStaff(){
		airStaffModelClosed = new ModelAirStaffClosed();
		airStaffModelOpen = new ModelAirStaffOpen();
		openTexture = new ResourceLocation(mod_Avatar.modid, "Airbending staff open.png");
		closedTexture = new ResourceLocation(mod_Avatar.modid, "Airbending staff closed.png");
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch(type){
			case EQUIPPED: 
				return true;
			default: 
				return false;
		}
		
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@SuppressWarnings("unused")
	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {		
		switch(type){
		case EQUIPPED:
			GL11.glPushMatrix();
			
			boolean isFirstPerson = false;
			
			if(data[1] != null && data[1] instanceof EntityPlayer){
				
		        if(true){
		        	mod_Avatar.AirStaffItem.state = 1;
		        	GL11.glRotatef(85, 0F, 1F, 0F);
		        	GL11.glRotatef(-10, 1F, 0F, 0F);
		        	GL11.glTranslatef(-0.1F, -1F, 0.5F);
				}
		        else if(false){
		        	mod_Avatar.AirStaffItem.state = 2;
		        	mod_Avatar.AirStaffItem.spin += 10;
		        	if(mod_Avatar.AirStaffItem.spin > 360) mod_Avatar.AirStaffItem.spin = 0;
		        	GL11.glTranslatef(-0.6F, -0.3F, 0.3F);
		        	GL11.glRotatef(90, 0F, 1F, 0F);
		        	GL11.glRotatef(mod_Avatar.AirStaffItem.spin, 0F, 0F, 1F);
		        }
		        else{
		        	mod_Avatar.AirStaffItem.state = 0;
					GL11.glRotatef(100, 0.0F, 0.0F, 1.0F);
					if(!((EntityPlayer)data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && !((Minecraft.getMinecraft().currentScreen instanceof GuiInventory || Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) && RenderManager.instance.playerViewY == 180.0F))){
						GL11.glTranslatef(0.2F, -0.7F, 0.0F);
					}
					else{
						isFirstPerson = true;
						GL11.glRotatef(100, 0.0F, 0.0F, 1.0F);
						GL11.glTranslatef(-1.1F, -0.6F, 0.5F);

					}

		        }
				
			}
			else{
				GL11.glTranslatef(0.2F, -0.7F, 0.0F);
			}
			
			if(mod_Avatar.AirStaffItem.state == 1){
				Minecraft.getMinecraft().renderEngine.bindTexture(openTexture);
				airStaffModelOpen.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);	
			}
			else{
				Minecraft.getMinecraft().renderEngine.bindTexture(closedTexture);
				airStaffModelClosed.render((Entity)data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);	
			}
			GL11.glPopMatrix();
		default:
			break;
		}		

	}

}
