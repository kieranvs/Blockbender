package kieranvs.avatar.item;

import kieranvs.avatar.mod_Avatar;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemGliderWing extends Item {

	public ItemGliderWing(){
		super();
		this.setUnlocalizedName("GliderWing");
		this.setCreativeTab(CreativeTabs.tabTransport);
		this.setTextureName(mod_Avatar.modid + ":" + "GliderWing");
	}
	
}
