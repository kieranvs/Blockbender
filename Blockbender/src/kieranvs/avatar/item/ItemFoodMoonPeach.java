package kieranvs.avatar.item;

import kieranvs.avatar.mod_Avatar;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemFoodMoonPeach extends ItemFood{
	
	public ItemFoodMoonPeach(){
		super(3, 0.6F, false);
		this.setUnlocalizedName("Peach");
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setTextureName(mod_Avatar.modid + ":" + "Peach");
	}
}
