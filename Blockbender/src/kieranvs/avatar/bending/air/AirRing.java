package kieranvs.avatar.bending.air;

import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import net.minecraft.entity.EntityLivingBase;

public class AirRing extends AsynchronousAbility {

	public AirRing(EntityLivingBase user, Long cooldown, int range, int strength, int damage, boolean pull) {
		super(user, cooldown);
		Location location = new Location(user);
		location.setY(location.getY() + 0.5);
		
		int anglebetween = 10;
		if(range > 10) anglebetween = 5;

		if(!pull){
			for (int i = -180; i <= 180; i += 10) {
				double angle = Math.toRadians(i);
				Vector direction = location.getDirection();
				
				double x = direction.getX();
				double z = direction.getZ();
				
				double vx = x * Math.cos(angle) - z * Math.sin(angle);
				double vz = x * Math.sin(angle) + z * Math.cos(angle);
				
				direction.setX(vx);
				direction.setZ(vz);
				
				direction.setY(-0.1);
				
				new AirStream(user, cooldown, location, direction, range, strength, damage);
			}			
		}
		else{
			for (int i = -180; i <= 180; i += 10) {
				double angle = Math.toRadians(i);
				Vector direction = location.getDirection();
				
				double x = direction.getX();
				double z = direction.getZ();
				
				double vx = x * Math.cos(angle) - z * Math.sin(angle);
				double vz = x * Math.sin(angle) + z * Math.cos(angle);
				
				direction.setX(-vx);
				direction.setZ(-vz);
				
				direction.setY(-0.1);
				
				new AirStream(user, cooldown, location.clone().subtract(direction.clone().multiply(range)), direction, range, strength, damage);
			}
		}
		
		destroy();
		return;

	}

	@Override
	public void update() {
	}

}
