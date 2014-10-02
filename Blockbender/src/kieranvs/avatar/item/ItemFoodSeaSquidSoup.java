package kieranvs.avatar.item;

import kieranvs.avatar.mod_Avatar;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSoup;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemFoodSeaSquidSoup extends ItemSoup {

	public ItemFoodSeaSquidSoup() {
		super(8);
		this.setUnlocalizedName("SeaSquidStew");
		LanguageRegistry.addName(this, "Sea Squid Soup");
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setTextureName(mod_Avatar.modid + ":" + this.getUnlocalizedName());
	}

}
