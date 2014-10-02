package kieranvs.avatar.bending;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class BendingListener {

	@SubscribeEvent
	public void onBending(PlayerInteractEvent event) {
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if (side == Side.CLIENT) {
			return;
		}
		if (event.entityPlayer.getHeldItem() != null) {
			BendingHandler.bend(event.entityPlayer);
		}
	}
}