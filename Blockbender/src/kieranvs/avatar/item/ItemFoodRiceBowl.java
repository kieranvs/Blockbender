package kieranvs.avatar.item;

import kieranvs.avatar.mod_Avatar;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSoup;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemFoodRiceBowl extends ItemSoup {

	public ItemFoodRiceBowl() {
		super(8);
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setUnlocalizedName("BowlofRice");
		this.setTextureName(mod_Avatar.modid + ":" + "RiceBowl");
	}

}
