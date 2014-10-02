package kieranvs.avatar.item;

import kieranvs.avatar.mod_Avatar;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSoup;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemFoodSeaPruneStew extends ItemSoup {

	public ItemFoodSeaPruneStew() {
		super(7);
		this.setUnlocalizedName("SeaPruneStew");
		LanguageRegistry.addName(this, "Sea Prune Stew");
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setTextureName(mod_Avatar.modid + ":" + this.getUnlocalizedName());
	}
	
}
