package kieranvs.avatar.bending.air;

import java.util.Random;

import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import net.minecraft.entity.EntityLivingBase;

public class AirGust extends AsynchronousAbility {
	
	public AirGust(EntityLivingBase user, Long cooldown, int strength, int range){
		super(user, cooldown);
		Location location = new Location(user, true);
		Vector direction = location.getDirection();
		Random rand = new Random();
		
		for(int i = 0; i<30; i++){
			
	        double vx = rand.nextGaussian() * 0.08D;
	        double vy = rand.nextGaussian() * 0.08D;
	        double vz = rand.nextGaussian() * 0.08D;
	        
	        Vector newdirection = direction.clone();
	        newdirection.setX(newdirection.getX() + vx);
	        newdirection.setY(newdirection.getY() + vy);
	        newdirection.setZ(newdirection.getZ() + vz);
	        
	        new AirStream(user, cooldown, location, newdirection, range, strength, 1);
		}
		
		destroy();
		return;
		
	}

	@Override
	public void update() {
	}
	
}
