package kieranvs.avatar.bending.fire;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import kieranvs.avatar.bending.ElementManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PassiveListener {

	@SubscribeEvent
	public void onEntityDamage(LivingHurtEvent event) {
		/* Less fire damage */
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			if (ElementManager.hasElement(player, ElementManager.FIRE)) {
				if (event.source == DamageSource.inFire || event.source == DamageSource.onFire) {
					if (player.getRNG().nextInt(3) == 0) {
						event.ammount = 1;
					}
					else {
						event.ammount = 0;
					}
				}
			}
		}
	}

}
