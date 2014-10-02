package kieranvs.avatar.bending.fire;

import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;

import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.AvatarDamageSource;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.PacketSender;

public class FireJet extends AsynchronousAbility {

	private static float speed = 10F;
	private static long interval = (long) (1000 / speed);

	private ConcurrentHashMap<BlockBukkit, Long> onFire = new ConcurrentHashMap<BlockBukkit, Long>();
	private Location origin;
	private Location location;
	private Vector direction;
	private float range; 
	private long time;
	private BlockBukkit previous;
	private int damage; 
	private boolean finished = false;
	private boolean firstblock = true;

	public FireJet(EntityLivingBase user, Location location, Vector direction, int level) {
		super(user, 5000 + (level * 4000));
		this.user = user;
		this.time = System.currentTimeMillis();
		this.origin = location.clone();
		this.location = location.clone();
		this.direction = direction.clone();
		this.direction.normalize();
		this.location.add(this.direction);
		if(level == 0){
			range = 7F;
			damage = 1;
		}
		if(level == 1){
			range = 14F;
			damage = 2;
		}
		if(level == 2){
			range = 20F;
			damage = 4;
		}
	}

	@Override
	public void update() {
		if(firstblock){
			location.add(direction);
			firstblock = false;
		}
		for (BlockBukkit block : onFire.keySet()) {
			PacketSender.spawnParticle("Avatar_flamesjet", location.getWorld(), block.getLocation().getX(), block.getLocation().getY(), block.getLocation().getZ());
			long time = onFire.get(block).longValue();
			if (System.currentTimeMillis() > time + 400L) {
				onFire.remove(block);
			}
		}

		if (finished) {
			if (onFire.isEmpty()) {
				destroy();
			}
			else {
				return;
			}
		}

		if (System.currentTimeMillis() - time >= interval) {
			time = System.currentTimeMillis();
			location.add(direction);
			if (location.distance(origin) > range) {
				finished = true;
				return;
			}
			BlockBukkit block = this.location.getBlock();
			if (block.getType() != Blocks.air) {
				finished = true;
				if (previous != null)
					previous.setTypeWithProtection(Blocks.fire);
				return;
			}
			PacketSender.spawnParticle("Avatar_flames", location.getWorld(), block.getLocation().getX(), block.getLocation().getY(), block.getLocation().getZ());
			previous = block;
			onFire.put(block, System.currentTimeMillis());
			BendingUtils.damageEntities(user, block.getLocation(), 2F, AvatarDamageSource.firebending, damage);
			return;
		}
	}

	public void damageEntities(Location loc) {
		for (Object o : loc.getWorld().getLoadedEntityList()) {
			if (o instanceof EntityLivingBase) {
				EntityLivingBase e = (EntityLivingBase) o;
				if (loc.distance(e) < 3.5) {
					e.attackEntityFrom(AvatarDamageSource.firebending, 3);
				}
			}
		}
	}

}
