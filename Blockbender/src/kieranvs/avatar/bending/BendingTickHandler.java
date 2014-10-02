package kieranvs.avatar.bending;

import java.util.EnumSet;

import kieranvs.avatar.KeyBind;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class BendingTickHandler {

	private kieranvs.avatar.bending.air.PassiveTickHandler AirTickHandler;

	public BendingTickHandler() {
		AirTickHandler = new kieranvs.avatar.bending.air.PassiveTickHandler();
	}

	private long lastTime = 0;

	@SubscribeEvent
	public void tickEnd(TickEvent event) {
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT){
			if(System.currentTimeMillis() - lastTime > 50L){
				lastTime = System.currentTimeMillis();
				Ability.updateTempClientFix();			
			}
		}
		else{
			Ability.updateAll();			
		}

		if(event.side.isClient()){
			return;
		}

		AirTickHandler.update();

		for (Object o : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
			EntityPlayer player = (EntityPlayer) o;
		}

	}

}
