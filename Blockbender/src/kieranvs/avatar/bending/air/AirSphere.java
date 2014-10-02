package kieranvs.avatar.bending.air;


import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.PacketSender;
import net.minecraft.entity.EntityLivingBase;

public class AirSphere extends AsynchronousAbility{

	private Location location;
	private int radius;
	private long lifetime;
	private long time;
	private long interval = 50;
	private double circleRadius = 1;
	private boolean bottomHalf = true;
	
	public AirSphere(EntityLivingBase user, Long cooldown, int radius, long lifetime) {
		super(user, cooldown + lifetime);
		this.location = new Location(user);
		this.radius = radius;
		this.lifetime = lifetime + System.currentTimeMillis();
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
				for(double i = -(radius); i < radius; i += 0.5D){
					if(i < 0){
						bottomHalf = true;
					}
					else{
						bottomHalf = true;
					}
					location.setY(location.getY() + i);
					if(bottomHalf){
						circleRadius += 0.5;
					}
					else{
						circleRadius -= 0.5;
					}
					PacketSender.sendCircle(location, circleRadius, 6, 6, 0);
				}
				BendingUtils.repelEntitiesInSphere(user, location, radius);
			}
			else{
				destroy();
				return;
			}
		}
	}

}
