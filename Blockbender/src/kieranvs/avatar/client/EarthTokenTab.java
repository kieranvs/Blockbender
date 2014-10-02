package kieranvs.avatar.client;

import kieranvs.avatar.mod_Avatar;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EarthTokenTab extends CreativeTabs {

	public EarthTokenTab(int position, String tabId) {
		super(position, tabId);
	}

	@Override
	public String getTranslatedTabLabel() {
		return "Earthbending";
	}

	@Override
	public Item getTabIconItem() {
		return mod_Avatar.EarthLaunchNoviceItem;
	}

}
