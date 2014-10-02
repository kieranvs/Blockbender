package kieranvs.avatar.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import kieranvs.avatar.mod_Avatar;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemFoodFruitPie extends ItemFood {
	
	public ItemFoodFruitPie(){
		super(9, 0.6F, false);
		this.setUnlocalizedName("FruitPie");
		LanguageRegistry.addName(this, "Fruit Pie");
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setTextureName(mod_Avatar.modid + ":" + this.getUnlocalizedName());
	}

}
