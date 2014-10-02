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

public class WaterIceStream extends AsynchronousAbility {

	private long interval = 20;
	private Location origin;
	private Location location;
	private Vector direction;
	private int range;
	private long time;
	private int damage;
	private static int cooldown = 3000;

	public WaterIceStream(EntityLivingBase user, Location location, Vector direction, int range, int damage) {
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
		
		if(!isWaterNearby(new Location(user))){
			if(user instanceof EntityPlayer) Messaging.avatarMessage((EntityPlayer) this.user, "No water nearby!");
			destroy();
			return;
		}
	}

	public WaterIceStream(EntityLivingBase user, Location location, Vector direction, int level) {
		super(user, cooldown + (level * 3000));
		this.time = System.currentTimeMillis();
		this.origin = location.clone();
		this.location = location.clone();
		this.direction = direction.clone();
		this.direction.normalize();
		this.location.add(this.direction);

		if(!isWaterNearby(new Location(user))){
			if(user instanceof EntityPlayer) Messaging.avatarMessage((EntityPlayer) this.user, "No water nearby!");
			destroy();
			return;
		}
		
		if(level == 0){
			this.damage = 1;
			this.range =7;
		}
		if(level == 1){
			this.damage = 2;
			this.range = 15;
		}
		if(level == 2){
			this.damage = 4;
			this.range = 20;
		}


	}

	@Override
	public void update(){
		if(System.currentTimeMillis() > time + interval){
			time = System.currentTimeMillis();
			this.location.add(this.direction);
			if(location.distance(origin) > range){
				destroy();
				return;
			}
			if(this.location.getBlock().getType() == Blocks.air || this.location.getBlock().getType() == Blocks.ice || this.location.getBlock().getType() == Blocks.water){
				this.location.getBlock().setTypeWithProtection(Blocks.ice);
				BendingUtils.damageEntities(user, location, 1F, AvatarDamageSource.waterbending, damage);
				PacketSender.spawnParticle("Avatar_watersplash", location.getWorld(), location.getX(), location.getY(), location.getZ());
				
				if(this.location.getBlock().getRelative(BlockBukkit.UP).getType() == Blocks.air || this.location.getBlock().getRelative(BlockBukkit.UP).getType() == Blocks.water || this.location.getBlock().getRelative(BlockBukkit.UP).getType() == Blocks.flowing_water){
					this.location.getBlock().getRelative(BlockBukkit.UP).setTypeWithProtection(Blocks.ice);
				}
				if(this.location.getBlock().getRelative(BlockBukkit.DOWN).getType() == Blocks.air || this.location.getBlock().getRelative(BlockBukkit.DOWN).getType() == Blocks.water || this.location.getBlock().getRelative(BlockBukkit.DOWN).getType() == Blocks.flowing_water){
					this.location.getBlock().getRelative(BlockBukkit.DOWN).setTypeWithProtection(Blocks.ice);
				}
				if(this.location.getBlock().getRelative(BlockBukkit.NORTH).getType() == Blocks.air || this.location.getBlock().getRelative(BlockBukkit.NORTH).getType() == Blocks.water || this.location.getBlock().getRelative(BlockBukkit.NORTH).getType() == Blocks.flowing_water){
					this.location.getBlock().getRelative(BlockBukkit.NORTH).setTypeWithProtection(Blocks.ice);
				}
				if(this.location.getBlock().getRelative(BlockBukkit.EAST).getType() == Blocks.air || this.location.getBlock().getRelative(BlockBukkit.EAST).getType() == Blocks.water || this.location.getBlock().getRelative(BlockBukkit.EAST).getType() == Blocks.flowing_water){
					this.location.getBlock().getRelative(BlockBukkit.EAST).setTypeWithProtection(Blocks.ice);
				}
				if(this.location.getBlock().getRelative(BlockBukkit.SOUTH).getType() == Blocks.air || this.location.getBlock().getRelative(BlockBukkit.SOUTH).getType() == Blocks.water || this.location.getBlock().getRelative(BlockBukkit.SOUTH).getType() == Blocks.flowing_water){
					this.location.getBlock().getRelative(BlockBukkit.SOUTH).setTypeWithProtection(Blocks.ice);
				}
				if(this.location.getBlock().getRelative(BlockBukkit.WEST).getType() == Blocks.air || this.location.getBlock().getRelative(BlockBukkit.WEST).getType() == Blocks.water || this.location.getBlock().getRelative(BlockBukkit.WEST).getType() == Blocks.flowing_water){
					this.location.getBlock().getRelative(BlockBukkit.WEST).setTypeWithProtection(Blocks.ice);
				}
			}
		}

	}

	public boolean isWaterNearby(Location l){
		for(int i = -2; i < 3; i++){
			for(int j = -2; j < 3; j++){
				if(BendingUtils.isWaterBendable(l.getBlock().getRelative(BlockBukkit.DOWN, 2).getRelative(BlockBukkit.EAST, i).getRelative(BlockBukkit.NORTH, j).getType()) || 
						BendingUtils.isWaterBendable(l.getBlock().getRelative(BlockBukkit.DOWN).getRelative(BlockBukkit.EAST, i).getRelative(BlockBukkit.NORTH, j).getType()) ||
						BendingUtils.isWaterBendable(l.getBlock().getRelative(BlockBukkit.EAST, i).getRelative(BlockBukkit.NORTH, j).getType())){
					return true;
				}
			}
		}

		return false;
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
