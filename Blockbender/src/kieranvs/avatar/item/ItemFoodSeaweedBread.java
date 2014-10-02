package kieranvs.avatar.item;

import kieranvs.avatar.mod_Avatar;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemFoodSeaweedBread extends ItemFood {

	public ItemFoodSeaweedBread() {
		super(5, 0.6F, false);
		this.setUnlocalizedName("SeaweedBread");
		LanguageRegistry.addName(this, "Seaweed Bread");
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setTextureName(mod_Avatar.modid + ":" + this.getUnlocalizedName());
	}

}
