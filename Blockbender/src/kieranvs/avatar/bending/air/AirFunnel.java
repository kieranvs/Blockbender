package kieranvs.avatar.bending.air;

import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.PacketSender;
import net.minecraft.entity.EntityLivingBase;

public class AirFunnel extends AsynchronousAbility {
	
	private int strength;
	private Vector direction;
	private Vector shootDirection;
	
	public AirFunnel(EntityLivingBase user, Long cooldown, Location location, int pushStrength, int range){
		super(user, cooldown);
		this.strength = pushStrength;
		
		for(int i = 0; i < 40; i++){
			
			double angle = i;
			direction = new Vector(-0.321, 0.891, 0.321);
			shootDirection = new Vector(-0.0, 1.00, 0.00);
			
			double x = direction.getX();
			double z = direction.getZ();

			double vx = x * Math.cos(angle) - z * Math.sin(angle);
			double vz = x * Math.sin(angle) + z * Math.cos(angle);

			direction.setX(vx);
			direction.setZ(vz);
			
			new AirStream(user, cooldown, location, direction, range, 0, 0);
			
			Object[] o = location.getWorld().loadedEntityList.toArray();

			for(int f = 0; f < o.length; f++){
				if(o[f] instanceof EntityLivingBase){
					EntityLivingBase e = (EntityLivingBase) o[f];
					Location eLoc = new Location(e);
					if (location.distance(e) < 5 || location.distance(e) < 7 && eLoc.getY() - location.getY() < 10 && eLoc.getY() - location.getY() > 0) {
						PacketSender.sendVelocity(e, shootDirection.multiply(strength/5)); 
						e.fallDistance = 0;
					}
				}		
			}
	        
		}
		
		destroy();
		return;

	}

	@Override
	public void update() {
	}

}
