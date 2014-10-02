package kieranvs.avatar.item;

import kieranvs.avatar.mod_Avatar;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemFoodSeaweed extends ItemFood {

	public ItemFoodSeaweed() {
		super(1, 0.6F, false);
		this.setUnlocalizedName("Seaweed");
		LanguageRegistry.addName(this, "Seaweed");
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setTextureName(mod_Avatar.modid + ":" + this.getUnlocalizedName());
	}

}
