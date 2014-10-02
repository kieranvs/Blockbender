package kieranvs.avatar.bending.water;

import kieranvs.avatar.bending.Ability;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class WaterIceRing extends AsynchronousAbility{

	private static int cooldown = 5000;

	@Deprecated
	public WaterIceRing(EntityLivingBase user, int range, int damage) {
		super(user, cooldown);
		BlockBukkit t = BlockBukkit.getTargetBlock(user);
		Location location = t.getLocation();
		Location ld = new Location(user);

		for (int i = -180; i <= 180; i += 10) {
			double angle = Math.toRadians(i);
			Vector direction = ld.getDirection();

			double x = direction.getX();
			double z = direction.getZ();

			double vx = x * Math.cos(angle) - z * Math.sin(angle);
			double vz = x * Math.sin(angle) + z * Math.cos(angle);

			direction.setX(vx);
			direction.setZ(vz);

			new WaterIceBridge(user, location, direction, range, damage);

		}
		
		destroy();

	}

	public WaterIceRing(EntityLivingBase user, int level) {
		super(user, cooldown + (level * 3000));
		BlockBukkit t = BlockBukkit.getTargetBlock(user);
		Location location = t.getLocation();
		Location ld = new Location(user);
		int range = 0;
		int damage = 0;
		if(level == 0){
			range = 5;
			damage = 1;
			if(location.distance(user) > 5){
				destroy();
				return;
			}
		}
		if(level == 1){
			range = 8;
			damage = 2;
			if(location.distance(user) > 10){
				destroy();
				return;
			}
		}
		if(level == 2){
			range = 15;
			damage = 4;
			if(location.distance(user) > 15){
				destroy();
				return;
			}
		}

		for (int i = -180; i <= 180; i += 10) {
			double angle = Math.toRadians(i);
			Vector direction = ld.getDirection();

			double x = direction.getX();
			double z = direction.getZ();

			double vx = x * Math.cos(angle) - z * Math.sin(angle);
			double vz = x * Math.sin(angle) + z * Math.cos(angle);

			direction.setX(vx);
			direction.setZ(vz);

			new WaterIceBridge(user, location, direction, range, damage);
		}
		
		destroy();

	}


	public WaterIceRing(Location location, EntityLivingBase user, int range, int damage) {
		super(user, cooldown);
		Location ld = new Location(user);

		for (int i = -180; i <= 180; i += 10) {
			double angle = Math.toRadians(i);
			Vector direction = ld.getDirection();

			double x = direction.getX();
			double z = direction.getZ();

			double vx = x * Math.cos(angle) - z * Math.sin(angle);
			double vz = x * Math.sin(angle) + z * Math.cos(angle);

			direction.setX(vx);
			direction.setZ(vz);

			new WaterIceBridge(user, location, direction, range, damage);

		}
		
		destroy();
	}

	@Override
	public void update() {
	}

}
