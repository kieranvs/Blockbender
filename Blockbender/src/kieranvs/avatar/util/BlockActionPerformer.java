package kieranvs.avatar.util;

import kieranvs.avatar.bukkit.BlockBukkit;

public abstract class BlockActionPerformer {
	
	public BlockActionPerformer(){
	}
	
	abstract public void performAction(BlockBukkit b);

}
