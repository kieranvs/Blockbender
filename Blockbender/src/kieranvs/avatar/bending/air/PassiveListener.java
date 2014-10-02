package kieranvs.avatar.bending.air;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kieranvs.avatar.bending.ElementManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PassiveListener {

	@SubscribeEvent
	public void onEntityDamage(LivingHurtEvent event) {
		/* Less fall damage */
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entity;
			if (ElementManager.hasElement(player, ElementManager.AIR)) {
				if (event.source == DamageSource.fall) {
					event.ammount *= 0.2;
				} 
				
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onEntityDamageClient(LivingHurtEvent event) {
		if(event.ammount == 0){
			event.setCanceled(true);
		}
	}


}
