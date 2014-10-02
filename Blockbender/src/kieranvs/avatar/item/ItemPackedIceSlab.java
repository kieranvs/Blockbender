package kieranvs.avatar.item;

import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.block.BlockColdDecorativeSlabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSlab;

public class ItemPackedIceSlab extends ItemSlab {

	public ItemPackedIceSlab(Block block) {
		super(block, mod_Avatar.PackedIceSlab, mod_Avatar.PackedIceDoubleSlab, false);
	}

}
