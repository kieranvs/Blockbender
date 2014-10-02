package kieranvs.avatar.item;

import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.block.BlockColdDecorativeSlabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSlab;

public class ItemIceSlab extends ItemSlab {

	public ItemIceSlab(Block block) {
		super(block, mod_Avatar.IceSlab, mod_Avatar.IceDoubleSlab, false);
	}

}
