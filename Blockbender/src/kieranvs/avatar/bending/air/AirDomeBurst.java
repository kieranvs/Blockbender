package kieranvs.avatar.bending.air;

import java.util.Random;

import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import net.minecraft.entity.EntityLivingBase;

public class AirDomeBurst extends AsynchronousAbility{

	public AirDomeBurst(EntityLivingBase user, Long cooldown, int strength, int range, boolean pull){
		super(user, cooldown);
		Location location = new Location(user, true);
		Vector direction = location.getDirection();

		Random rand = new Random();

		if(!pull){
			for(int i = 0; i < 540; i++){
				double vx = rand.nextGaussian();
				double vy = rand.nextGaussian();
				double vz = rand.nextGaussian();

				Vector newdirection = direction.clone();
				newdirection.setX(newdirection.getX() + vx);
				newdirection.setY(newdirection.getY() + vy);
				newdirection.setZ(newdirection.getZ() + vz);

				new AirStream(user, cooldown, location, newdirection, range, strength, 0);
			}			
		}
		else{
			for(int i = 0; i < 540; i++){
				double vx = rand.nextGaussian();
				double vy = rand.nextGaussian();
				double vz = rand.nextGaussian();

				Vector newdirection = direction.clone();
				newdirection.setX(newdirection.getX() + vx);
				newdirection.setY(newdirection.getY() + vy);
				newdirection.setZ(newdirection.getZ() + vz);

				new AirStream(user, cooldown, location.clone().subtract(newdirection.clone().multiply(range)), newdirection, range, strength, 0);
			}	
		}
		
		destroy();
		return;

	}

	@Override
	public void update() {
	}
	
}
