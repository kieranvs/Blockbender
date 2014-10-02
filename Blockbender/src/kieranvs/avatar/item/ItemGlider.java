package kieranvs.avatar.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.util.FileLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGlider extends Item {
	
	private int color;

	public ItemGlider(int color) {
		super();
		this.setUnlocalizedName("Glider");
		LanguageRegistry.addName(this, "Glider");
		this.setCreativeTab(CreativeTabs.tabTransport);
		this.color = color;
		this.setTextureName(getOpenTexture(this.color));
	}

	@Override
	@SideOnly(Side.CLIENT)
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer){
		if(!mod_Avatar.data.isPlayerGliding(par3EntityPlayer.getDisplayName())){
			mod_Avatar.data.addGlidingPlayerName(par3EntityPlayer.getDisplayName());
		} else{
			mod_Avatar.data.removeGlidingPlayerName(par3EntityPlayer.getDisplayName());
		}
		
		return par1ItemStack;
	}
	
	public static String getOpenTexture(int color){
		switch(color){
		case 0:
			return mod_Avatar.modid + ":" + "WhiteStaffIcon";
		case 1:
			return mod_Avatar.modid + ":" + "OrangeStaffIcon";
		case 2:
			return mod_Avatar.modid + ":" + "MagentaStaffIcon";
		case 3:
			return mod_Avatar.modid + ":" + "LightBlueStaffIcon";
		case 4:
			return mod_Avatar.modid + ":" + "YellowStaffIcon";
		case 5:
			return mod_Avatar.modid + ":" + "LightGreenStaffIcon";
		case 6:
			return mod_Avatar.modid + ":" + "PinkStaffIcon";
		case 7:
			return mod_Avatar.modid + ":" + "GreyStaffIcon";
		case 8:
			return mod_Avatar.modid + ":" + "LightGreyStaffIcon";
		case 9:
			return mod_Avatar.modid + ":" + "CyanStaffIcon";
		case 10:
			return mod_Avatar.modid + ":" + "PurpleStaffIcon";
		case 11:
			return mod_Avatar.modid + ":" + "BlueStaffIcon";
		case 12:
			return mod_Avatar.modid + ":" + "BrownStaffIcon";
		case 13:
			return mod_Avatar.modid + ":" + "GreenStaffIcon";
		case 14:
			return mod_Avatar.modid + ":" + "RedStaffIcon";
		case 15:
			return mod_Avatar.modid + ":" + "BlackStaffIcon";
		default:
			return mod_Avatar.modid + ":" + "OrangeStaffIcon";
		}
	}

	
	public int getColor(){
		return color;
	}
	
}
