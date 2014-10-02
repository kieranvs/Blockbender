package kieranvs.avatar.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.entity.EntityGlider;
import kieranvs.avatar.item.ItemGlider;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ClientForgeListener {
	
	@SubscribeEvent
	public void RenderPlayerUpdate(PlayerEvent evt)
	{
		if ((evt.isCancelable()) && (evt.entityLiving != null)) {
			ItemStack held = evt.entityLiving.getHeldItem();
			if ((held != null) && (held.getItem() instanceof ItemGlider)) {
				if ((!evt.entityLiving.onGround) && (!evt.entityLiving.isInWater()) && mod_Avatar.data.isPlayerGliding(evt.entityPlayer.getDisplayName())) {
					evt.setCanceled(true);
					if (!(evt.entityLiving.riddenByEntity instanceof EntityGlider)) {
						EntityGlider entity = new EntityGlider(evt.entityLiving.worldObj, ((ItemGlider) held.getItem()).getColor());

						entity.setLocationAndAngles(evt.entityLiving.posX, evt.entityLiving.posY, evt.entityLiving.posZ, 0.0F, 0.0F);

						entity.ridingEntity = evt.entityLiving;
						entity.prevRotationYAW = evt.entityLiving.rotationYaw;
						entity.rotationYAW = evt.entityLiving.rotationYaw;
						entity.rotationRoll = 0.01D;
						entity.prevRotationRoll = 0.01D;
						evt.entityLiving.riddenByEntity = entity;
						evt.entityLiving.worldObj.spawnEntityInWorld(entity);
					}
				}
				else if ((evt.entityLiving.riddenByEntity != null) && ((evt.entityLiving.riddenByEntity instanceof EntityGlider))) {
					mod_Avatar.data.removeGlidingPlayerName(evt.entityPlayer.getDisplayName());
					evt.entityLiving.riddenByEntity.ridingEntity = null;
					evt.entityLiving.riddenByEntity.setDead();
					evt.entityLiving.riddenByEntity = null;
				}

			}
			else if ((evt.entityLiving.riddenByEntity != null) && ((evt.entityLiving.riddenByEntity instanceof EntityGlider))) {
				mod_Avatar.data.removeGlidingPlayerName(evt.entityPlayer.getDisplayName());
				evt.entityLiving.riddenByEntity.ridingEntity = null;
				evt.entityLiving.riddenByEntity.setDead();
				evt.entityLiving.riddenByEntity = null;
			}
		}
	}

}
