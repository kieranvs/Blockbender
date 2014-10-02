package kieranvs.avatar.bending.air;

import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.util.PacketSender;
import net.minecraft.entity.EntityLivingBase;

public class AirHighJump extends AsynchronousAbility {

	public AirHighJump(EntityLivingBase user, Long cooldown, float power) {
		super(user, cooldown);
		if (!(new Location(user)).getBlock().getRelative(BlockBukkit.DOWN).isLiquid()) {
			PacketSender.sendVelocity(user, 0, power, 0);
			PacketSender.spawnParticle("Avatar_cloud", user.worldObj, user.posX, user.posY, user.posZ, 100, 0.2);
		}
		destroy();
		return;
	}

	@Override
	public void update() {
	}

}
