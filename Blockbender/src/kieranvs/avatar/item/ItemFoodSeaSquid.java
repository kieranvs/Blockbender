package kieranvs.avatar.item;

import kieranvs.avatar.mod_Avatar;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemFoodSeaSquid extends ItemFood {

	public ItemFoodSeaSquid() {
		super(3, 0.6F, true);
		this.setUnlocalizedName("SeaSquid");
		LanguageRegistry.addName(this, "Sea Squid");
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setTextureName(mod_Avatar.modid + ":" + this.getUnlocalizedName());
	}
}
