package kieranvs.avatar.bending.air;

import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.PacketSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class AirSpring extends AsynchronousAbility {

	public AirSpring(EntityLivingBase player, int strength, int cooldown) {
		super(player, cooldown);
		Location location = new Location(player);
		if (!location.getBlock().getRelative(BlockBukkit.DOWN).isLiquid()) {
			Vector vel = location.getDirection().normalize().multiply(strength);
			PacketSender.sendVelocity(player, vel.getX(), vel.getY() / 2, vel.getZ());
			PacketSender.spawnParticle("Avatar_cloud", player.worldObj, (int)location.getX(), (int)location.getY(), (int)location.getZ());
		}
		this.destroy();
		return;
	}

	@Override
	public void update() {
	}

}
