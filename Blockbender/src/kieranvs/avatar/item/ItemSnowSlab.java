package kieranvs.avatar.item;

import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.block.BlockColdDecorativeSlabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSlab;

public class ItemSnowSlab extends ItemSlab {

	public ItemSnowSlab(Block block) {
		super(block, mod_Avatar.SnowSlab, mod_Avatar.SnowDoubleSlab, false);
	}

}
