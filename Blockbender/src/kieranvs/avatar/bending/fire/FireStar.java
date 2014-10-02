package kieranvs.avatar.bending.fire;

import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class FireStar extends AsynchronousAbility {

	private int starSize;
	private static int cooldown = 4000;

	public FireStar(EntityLivingBase player, int level) {
		super(player, cooldown + (level * 2000));
		Location location = new Location(player);
		if(level == 2){
			starSize = 90;
		}
		else{
			starSize = 45;
		}

		for (int i = -180; i <= 180; i += starSize) {
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
