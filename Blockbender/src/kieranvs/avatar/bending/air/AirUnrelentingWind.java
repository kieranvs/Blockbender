package kieranvs.avatar.bending.air;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import kieranvs.avatar.bending.Ability;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.PacketSender;

public class AirUnrelentingWind extends Ability {

	private Vector direction;
	private Location origin;
	private Location location;
	private int range;
	private int strength;
	private long lifetime;
	private long time;
	private long interval = 20;
	private boolean isSneaking = false;
	private boolean keepMoving = true;
	private ConcurrentHashMap<Location, Long> windBlocks = new ConcurrentHashMap<Location, Long>();
	
	public AirUnrelentingWind(EntityLivingBase user, Location location, Vector direction, int range, int strength, long lifetime) {
		super(user);
		this.direction = direction;
		this.origin = location.clone();
		this.location = location.clone();
		this.range = range;
		this.strength = strength/5;
		this.lifetime = lifetime + System.currentTimeMillis();
		this.time = System.currentTimeMillis();
		if(!(user instanceof EntityPlayer)){
			isSneaking = true;
		}
	}

	@Override
	public void update() {
		if (location.distance(origin) > range) {
			keepMoving = false;
		}
		if(System.currentTimeMillis() > lifetime){
			isSneaking = false;
			windBlocks.clear();
			destroy();
			return;
		}
		if(keepMoving){
			if(System.currentTimeMillis() > time + interval){
				if(user.isSneaking() || isSneaking){
					Random random = new Random(5);
					PacketSender.spawnParticle("Avatar_cloud", location.getWorld(), location.getX() + random.nextGaussian(), location.getY() + random.nextGaussian(), location.getZ() + random.nextGaussian(), 1, 0.6);
					BendingUtils.repelEntitiesOnYourLevelAndAbove(user, location, range);
					windBlocks.put(location, System.currentTimeMillis());
					location.add(direction);
				}
				else{
					windBlocks.clear();
					destroy();
					return;
				}
			}
		}
		else{
			if(System.currentTimeMillis() > time + interval){
				if(user.isSneaking() || isSneaking){
					Random rand = new Random(6);
					for(Location l : windBlocks.keySet()){
						PacketSender.spawnParticle("Avatar_cloud", l.getWorld(), l.getX() + rand.nextGaussian(), l.getY() + rand.nextGaussian(), l.getZ() + rand.nextGaussian(), 1, 1);
						BendingUtils.repelEntitiesOnYourLevelAndAbove(user, l, range);
					}
				}
				else{
					windBlocks.clear();
					destroy();
					return;
				}

			}

		}
		
	}

}
