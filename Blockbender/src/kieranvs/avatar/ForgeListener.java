package kieranvs.avatar;

import java.util.HashMap;
import kieranvs.avatar.bending.ElementManager;
import kieranvs.avatar.client.MovingSoundEffect;
import kieranvs.avatar.client.SoundHandler;
import kieranvs.avatar.item.ItemGlider;
import kieranvs.avatar.util.Messaging;
import kieranvs.avatar.util.StringColour;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class ForgeListener {
	
	private boolean isClient;
	private boolean play;
	private MovingSoundEffect soundEffect;
	
	public ForgeListener(){
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT){
			isClient = true;
		}
	}

	@SubscribeEvent
	public void onEntityDrop(LivingDropsEvent event) {
		if (event.entityLiving instanceof EntitySquid) {
			event.entityLiving.dropItem(mod_Avatar.SeaSquidItem, 1);
		}
	}
	
	private HashMap<EntityLivingBase, Long> ridiculousListOfFlyingPeeps = new HashMap<EntityLivingBase, Long>();
	
	@SubscribeEvent
	public void PlayerUpdate(LivingEvent.LivingUpdateEvent evt)
	{
		if(!(evt.entityLiving instanceof EntityPlayer)){
			return;
		}
		EntityPlayer entityPlayer = (EntityPlayer) evt.entityLiving;
		if (evt.entityLiving != null) {
			ItemStack held = evt.entityLiving.getHeldItem();
			if (ridiculousListOfFlyingPeeps.containsKey(evt.entityLiving)){
				if((held != null) && (!(held.getItem() instanceof ItemGlider)) || held == null || evt.entityLiving.onGround || (Math.abs(evt.entityLiving.motionX) + Math.abs(evt.entityLiving.motionZ) > 0.2D)){
					ridiculousListOfFlyingPeeps.remove(evt.entityLiving);
				}
			}
			if ((held != null) && (held.getItem() instanceof ItemGlider) && (!evt.entityLiving.onGround) && (!evt.entityLiving.isInWater()) && (evt.entityLiving.motionY <= -0.1000000014901161D) && mod_Avatar.data.isPlayerGliding(entityPlayer.getDisplayName())) {
				if (Math.abs(evt.entityLiving.motionX) + Math.abs(evt.entityLiving.motionZ) < 0.2D){
					if(!ridiculousListOfFlyingPeeps.containsKey(evt.entityLiving)){
						ridiculousListOfFlyingPeeps.put(evt.entityLiving, System.currentTimeMillis());
					}
					else {
						if((System.currentTimeMillis() - ridiculousListOfFlyingPeeps.get(evt.entityLiving)) > 500){
							return;
						}
					}
				}
				if(evt.entityLiving.isSneaking()){
					evt.entityLiving.motionY = -0.2D;
					if(play){
						play = false;
						if(isClient) handleSoundEffect(mod_Avatar.modid + ":" + "wind", (Entity)evt.entityLiving, true);
					}
					evt.entityLiving.motionX *= 1.05D; //acceleration
					evt.entityLiving.motionZ *= 1.05D; //acceleration

				}
				else{
					play = true;
					if(isClient) handleSoundEffect(mod_Avatar.modid + ":" + "wind", (Entity)evt.entityLiving, false);
					if(evt.entityLiving.motionY < -0.07D){ //fall speed for non airbenders
						evt.entityLiving.motionY = -0.07D;					
					}					
				}
				if(ElementManager.hasElement(evt.entityLiving, ElementManager.AIR) && Math.abs(evt.entityLiving.motionX) + Math.abs(evt.entityLiving.motionZ) < 2.0D) //top speed for airbenders
				{
					evt.entityLiving.motionX *= 1.05D; //acceleration
					evt.entityLiving.motionZ *= 1.05D; //acceleration
				}
				if(!ElementManager.hasElement(evt.entityLiving, ElementManager.AIR) && Math.abs(evt.entityLiving.motionX) + Math.abs(evt.entityLiving.motionZ) < 2.0D) //top speed for non airbenders
				{
					evt.entityLiving.motionX *= 1.05D; //acceleration
					evt.entityLiving.motionZ *= 1.05D; //acceleration
				}

			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void handleSoundEffect(String sound, Entity ent, boolean start){
		if(start){
			soundEffect = new MovingSoundEffect(ent, sound);
			Minecraft.getMinecraft().getSoundHandler().playSound(soundEffect);
		} else{
			Minecraft.getMinecraft().getSoundHandler().stopSound(soundEffect);
		}
	}

	@SubscribeEvent
	public void cancelFallDamage(LivingFallEvent evt)
	{
		if (evt.entityLiving != null) {
			ItemStack held = evt.entityLiving.getHeldItem();
			if ((held != null) && (held.getItem() instanceof ItemGlider))
				evt.distance = 1.1F;
		}
	}
	
	@SubscribeEvent
	public void onPlayerJoin(PlayerLoggedInEvent evt){
		Messaging.avatarMessage(evt.player, StringColour.YELLOW + "Welcome to Avatar: The Last Blockbender! v" + mod_Avatar.version);
		Messaging.avatarMessage(evt.player, StringColour.RED + "Warning: Blue fire has been made extremely dangerous. Use with caution.");
	}

}
