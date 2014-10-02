package kieranvs.avatar.bending.air;

import kieranvs.avatar.bending.Ability;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.util.PacketSender;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;

public class AirItemSweep extends Ability {
	
	private Location location;
	
	public AirItemSweep(EntityLivingBase user, int range){
		super(user);
		this.location = new Location(user);
		
		Object[] o = location.getWorld().loadedEntityList.toArray();
		for(int f = 0; f < o.length; f++){
			if(o[f] instanceof EntityItem){
				EntityItem e = (EntityItem) o[f];
				Location eLoc = new Location(e);
				if (location.distance(e) < range) {
					//PacketSender.sendVelocity(e, shootDirection.multiply(strength/5));
				}
			}		
		}
		
		
	}

	@Override
	public void update() {
		
	}

}
