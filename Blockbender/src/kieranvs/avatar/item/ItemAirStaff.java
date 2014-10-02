package kieranvs.avatar.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import kieranvs.avatar.mod_Avatar;
import net.minecraft.creativetab.CreativeTabs;

public class ItemAirStaff extends ItemBendingTool {

	public int state = 0;
	public float spin = 0;
	private String unlocalizedName = "Air Staff Closed";
	
	public ItemAirStaff() {
		super();
		if(state != 0){
			this.unlocalizedName = "Air Staff Open";
		}
		this.setUnlocalizedName(unlocalizedName);
		LanguageRegistry.addName(this, "Air Staff");
		this.setCreativeTab(mod_Avatar.tabAirbending);
		this.setFull3D();
		this.setTextureName(mod_Avatar.modid + ":" + this.getUnlocalizedName());
	}

}
