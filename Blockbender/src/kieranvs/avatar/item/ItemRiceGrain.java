package kieranvs.avatar.item;

import kieranvs.avatar.mod_Avatar;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemRiceGrain extends ItemSeeds {

	public ItemRiceGrain() {
		super(mod_Avatar.RiceCropBlock, Blocks.farmland);
		setUnlocalizedName("RiceGrains");
		this.setTextureName(mod_Avatar.modid + ":" + this.getUnlocalizedName());
	}

}
