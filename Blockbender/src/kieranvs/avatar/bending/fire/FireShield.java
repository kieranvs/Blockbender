package kieranvs.avatar.bending.fire;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import net.minecraft.entity.EntityLivingBase;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.PacketSender;

public class FireShield extends AsynchronousAbility {

	private int radius;
	private int height;
	private long lifetime;
	private long time;
	private long interval = 20;
	private Location location;
	private static int cooldown = 7000;

	public FireShield(EntityLivingBase user, Location location, int level) {
		super(user, cooldown + (level * 5000));
		this.time = System.currentTimeMillis();
		this.location = location;
		if(level == 0){
			radius = 5;
			lifetime = 7500L + System.currentTimeMillis();
		}
		if(level == 1){
			radius = 8;
			lifetime = 12500L + System.currentTimeMillis();
		}
		if(level == 2){
			radius = 13;
			lifetime = 20000L + System.currentTimeMillis();
		}
		this.height = radius;

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
				PacketSender.sendShield(location, height, radius, 10, 3, 10, 2);
				BendingUtils.repelEntitiesOnYourLevelAndAbove(user, location, radius);
			}
			else{
				destroy();
				return;
			}
		}
	}

}
