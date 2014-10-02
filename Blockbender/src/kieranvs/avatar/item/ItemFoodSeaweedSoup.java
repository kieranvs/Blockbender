package kieranvs.avatar.item;

import kieranvs.avatar.mod_Avatar;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSoup;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemFoodSeaweedSoup extends ItemSoup {

	public ItemFoodSeaweedSoup() {
		super(7);
		this.setUnlocalizedName("SeaweedStew");
		LanguageRegistry.addName(this, "Seaweed Soup");
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setTextureName(mod_Avatar.modid + ":" + this.getUnlocalizedName());
	}

}
