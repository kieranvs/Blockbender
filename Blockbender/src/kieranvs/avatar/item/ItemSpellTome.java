package kieranvs.avatar.item;

import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.util.GenerationUtils;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSpellTome extends Item {

	private int level = 0;
	private String texturename;
	private IIcon openIcon;

	@Deprecated
	public ItemSpellTome(int id, String name, int element) {
		this(name, element, 0);
	}

	public ItemSpellTome(String name, int element, int level){
		super();
		String itemname = "";
		if (level == 0) {
			itemname = "Novice " + name;
		}
		if (level == 1) {
			itemname = "Adept " + name;
		}
		if (level == 2) {
			itemname = "Master " + name;
		}
		if (level == -1) {
			itemname = name;
		}
		this.level = level;
		this.maxStackSize = 1;
		if (element == 0) {
			this.setCreativeTab(mod_Avatar.tabFirebending);
			this.texturename = "Scroll_Red";
		}
		else if (element == 1) {
			this.setCreativeTab(mod_Avatar.tabWaterbending);
			this.texturename = "Scroll_Blue";
		}
		else if (element == 2) {
			this.setCreativeTab(mod_Avatar.tabEarthbending);
			this.texturename = "Scroll_Green";
		}
		else if (element == 3) {
			this.setCreativeTab(mod_Avatar.tabAirbending);
			this.texturename = "Scroll_Grey";
		}
		else if (element == -1){
			this.setCreativeTab(CreativeTabs.tabMisc);
			this.texturename = "GenericToken";
		}
		this.setUnlocalizedName(itemname);
		GenerationUtils.registerScroll(element, level, this);

	}
	
	@Deprecated
	public ItemSpellTome(int id, String name, int element, int level) {
		this(name, element, level);
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		if (level == 2) {
			return EnumRarity.uncommon;
		}
		return EnumRarity.common;
	}
	
	@Override
	public void registerIcons(IIconRegister iconregister) {
		this.itemIcon = iconregister.registerIcon(mod_Avatar.modid + ":" + "item." + this.texturename);
		this.openIcon = iconregister.registerIcon(mod_Avatar.modid + ":" + "item." + "Scroll_Open");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1) {
		if(par1 == 1){
			return this.openIcon;
		}
		else {
			return this.itemIcon;
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if(par1ItemStack.getItemDamage() == 0){			
			par1ItemStack.setItemDamage(1);
		}
		return par1ItemStack;
	}
	
	private String helpMessage;
	
	public void setHelpMessage(String help){
		helpMessage = help;
	}
	
	public String getHelpMessage(){
		return helpMessage;
	}

}
