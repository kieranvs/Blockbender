package kieranvs.avatar.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import kieranvs.avatar.mod_Avatar;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

public class ItemFoodCustardTart extends ItemFood{
	
	public ItemFoodCustardTart(){
		super(9, 0.6F, false);
		this.setUnlocalizedName("CustardTart");
		LanguageRegistry.addName(this, "Egg Custard Tart");
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setTextureName(mod_Avatar.modid + ":" + this.getUnlocalizedName());
	}

}
