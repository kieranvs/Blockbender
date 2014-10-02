package kieranvs.avatar.bending.air;

import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.PacketSender;
import net.minecraft.entity.EntityLivingBase;

public class AirShield extends AsynchronousAbility {

	private int radius;
	private int height;
	private long lifetime;
	private long time;
	private long interval = 50;
	private Location location;

	public AirShield(EntityLivingBase user, Long cooldown, Location location, int radius, long lifetime) {
		super(user, cooldown + lifetime);
		this.radius = radius;
		this.height = radius;
		this.lifetime = lifetime + System.currentTimeMillis();
		this.time = System.currentTimeMillis();
		this.location = location;
	}

	@Override
	public void update() {
		if(System.currentTimeMillis() > lifetime){
			destroy();
			return;
		}
		if(System.currentTimeMillis() > time + interval){
			time = System.currentTimeMillis();
			location = new Location(user);
			if(user.isSneaking()){
				PacketSender.sendShield(location, height, radius, 10, 6, 10, 0);
				BendingUtils.repelEntitiesOnYourLevelAndAbove(user, location, radius);
			}
			else{
				destroy();
				return;
			}
		}
	}

}
