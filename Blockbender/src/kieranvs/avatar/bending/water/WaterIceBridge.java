package kieranvs.avatar.bending.water;

import kieranvs.avatar.bending.Ability;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;

import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.AvatarDamageSource;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.Messaging;
import kieranvs.avatar.util.PacketSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;

public class WaterIceBridge extends AsynchronousAbility {

	private long interval = 20;
	private Location origin;
	private Location location;
	private Vector direction;
	private int range;
	private long time;
	private int damage;
	private static int cooldown = 3000;

	public static void createTriple(EntityLivingBase user, Location location, Vector direction, int range, int damage) {
		double x = direction.getZ();
		double y = 0;
		double z = -direction.getX();
		Vector rightangle = new Vector(x, y, z);
		rightangle.normalize();

		Location side1 = location.clone();
		Location side2 = location.clone();
		side1.add(rightangle);
		side2.subtract(rightangle);

		new WaterIceBridge(user, location, direction, 2);
		new WaterIceBridge(user, side1, direction, 2);
		new WaterIceBridge(user, side2, direction, 2);
	}
	
	public WaterIceBridge(EntityLivingBase user, Location location, Vector direction, int range, int damage) {
		super(user, cooldown);
		this.time = System.currentTimeMillis();
		this.range = range;
		this.origin = location.clone();
		this.location = location.clone();
		this.direction = direction.clone();
		this.direction.setY(0);
		this.direction.normalize();
		this.location.add(this.direction);
		this.damage = damage;
	}
	
	public WaterIceBridge(EntityLivingBase user, Location location, Vector direction, int level) {
		super(user, cooldown + (level * 3000));
		this.time = System.currentTimeMillis();
		this.origin = location.clone();
		this.location = location.clone();
		this.direction = direction.clone();
		this.direction.setY(0);
		this.direction.normalize();
		this.location.add(this.direction);
		
		if(level == 0){
			this.damage = 1;
			this.range =10;
			if(location.distance(user) > 5){
				destroy();
				return;
			}
		}
		if(level == 1){
			this.damage = 2;
			this.range = 18;
			if(location.distance(user) > 10){
				destroy();
				return;
			}
		}
		if(level == 2){
			this.damage = 4;
			this.range = 22;
			if(location.distance(user) > 15){
				destroy();
				return;
			}
		}

	}

	@Override
	public void update() {
		if (location.distance(origin) > range) {
			destroy();
			return;
		}
		if (System.currentTimeMillis() > time + interval) {
			if (location.getBlock().getType() == Blocks.water || location.getBlock().getType() == Blocks.flowing_water || location.getBlock().getType() == Blocks.ice) {
				time = System.currentTimeMillis();
				location.getBlock().setTypeWithProtection(Blocks.ice);
				PacketSender.spawnParticle("Avatar_watersplash", location.getWorld(), location.getX(), location.getY(), location.getZ());
				BendingUtils.damageEntities(user, location, 3.5F, AvatarDamageSource.waterbending, damage);
				location.add(direction);
			}
			else {
				destroy();
				return;
			}
		}
	}

	public void damageEntities(Location loc) {
		for (Object o : loc.getWorld().getLoadedEntityList()) {
			if (o instanceof EntityLivingBase) {
				EntityLivingBase e = (EntityLivingBase) o;
				if (loc.distance(e) < 3.5) {
					e.attackEntityFrom(DamageSource.drown, damage);
				}
			}
		}
	}

}
