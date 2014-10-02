package kieranvs.avatar.bending.fire;

import java.util.concurrent.ConcurrentHashMap;

import kieranvs.avatar.bending.Ability;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.PacketSender;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class FireWhip extends Ability {

	private long burntime = 400L;
	private ConcurrentHashMap<Location, Long> onFire = new ConcurrentHashMap<Location, Long>();
	private float range = 1;

	public FireWhip(EntityLivingBase user) {
		super(user);
		this.user = user;
	}

	@Override
	public void update() {
		range += 0.05F;

		if (range >= 5.0) {
			destroy();
		}

		for (Location loc : onFire.keySet()) {
			long time = onFire.get(loc).longValue();
			if (System.currentTimeMillis() > time + burntime) {
				onFire.remove(loc);
			}
		}

		Location l = new Location(user.worldObj, user.posX, user.posY + 1.62, user.posZ);
		Vector v = new Vector(user.getLookVec().xCoord, user.getLookVec().yCoord, user.getLookVec().zCoord);
		v.normalize();
		v.multiply(range);
		l.add(v);

		onFire.put(l, System.currentTimeMillis());

		for (Location loc : onFire.keySet()) {
			PacketSender.spawnParticle("Avatar_flames", loc.getWorld(), loc.getX(), loc.getY(), loc.getZ());
		}

	}

}
