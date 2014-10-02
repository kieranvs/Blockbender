package kieranvs.avatar.bending.fire;

import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import net.minecraft.entity.EntityLivingBase;

public class FireBlast extends AsynchronousAbility {

	private static int cooldown = 4000;
	
	public FireBlast(EntityLivingBase player, int arc, int level) {
		super(player, cooldown + (level * 2500));
		Location location = new Location(player);

		for (int i = -arc; i <= arc; i += 2) {
			double angle = Math.toRadians(i);
			Vector direction = location.getDirection();

			double x = direction.getX();
			double z = direction.getZ();

			double vx = x * Math.cos(angle) - z * Math.sin(angle);
			double vz = x * Math.sin(angle) + z * Math.cos(angle);

			direction.setX(vx);
			direction.setZ(vz);

			new FireStream(location, direction, level, player);
		}
		
		destroy();

	}

	@Override
	public void update() {		
	}
}
