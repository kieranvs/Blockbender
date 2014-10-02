package kieranvs.avatar.item;

import kieranvs.avatar.mod_Avatar;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemFoodNoodles extends ItemFood{

	public ItemFoodNoodles() {
		super(7, 0.6F, false);
		this.setUnlocalizedName("Noodles");
		LanguageRegistry.addName(this, "Noodles");
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setTextureName(mod_Avatar.modid + ":" + this.getUnlocalizedName());

	}

}
