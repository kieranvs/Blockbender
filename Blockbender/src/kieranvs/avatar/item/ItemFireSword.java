package kieranvs.avatar.item;

import kieranvs.avatar.mod_Avatar;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;

public class ItemFireSword extends ItemSword {

	public ItemFireSword() {
		super(mod_Avatar.BENDINGFIRE);
		this.setTextureName(mod_Avatar.modid + ":" + this.getUnlocalizedName());
	}

}
