package kieranvs.avatar.bending.water;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import net.minecraft.entity.EntityLivingBase;
import kieranvs.avatar.bending.Ability;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.PacketSender;

public class WaterWaterShield extends Ability {

	private int radius;
	private int height;
	private long lifetime;
	private long time;
	private long interval = 20;
	private Location location;

	public WaterWaterShield(EntityLivingBase user, Location location, int radius, long lifetime) {
		super(user);
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
				PacketSender.sendShield(location, height, radius, 10, 6, 10, 1);
				BendingUtils.repelEntitiesOnYourLevelAndAbove(user, location, radius);
			}
			else{
				destroy();
				return;
			}
		}
	}

}
