package kieranvs.avatar.item;

import kieranvs.avatar.mod_Avatar;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemFoodCabbage extends ItemFood {

	public ItemFoodCabbage(){
		super(2, 0.6F, false);
		this.setUnlocalizedName("Cabbage");
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setTextureName(mod_Avatar.modid + ":" + "Cabbage");
	}


}
