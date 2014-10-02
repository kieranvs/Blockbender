package kieranvs.avatar.bending.earth;

import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.AvatarDamageSource;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.Messaging;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;

public class EarthThrow extends AsynchronousAbility {

	private Location sourceLocation;
	private Location blockLocation;
	private Location prevBlockLocation;
	private int blockMovementState;
	private int blockMaterialState;
	private Vector velocity;
	private int range;
	private int level;
	private long lastmovement = 0;
	private Block blockType;
	private int metadata;
	private boolean finished = false;
	private Long time;
	private int damage;

	private static int BLOCK_MVE_IDLE = 0;
	private static int BLOCK_MVE_TRAVELLING = 1;

	public EarthThrow(EntityLivingBase user, Long cooldowntime, Location l, int level) {
		super(user, cooldowntime);
		if(!(BendingUtils.isEarthBendable(l.getBlock().getType()))){
			if(user instanceof EntityPlayer){
				Messaging.avatarMessage((EntityPlayer) this.user, "Not Earth bendable!");
			}
			this.destroy();
			return;
		}
		Block m = l.getBlock().getType();
		if(BendingUtils.isEarthBendable(m)){
			this.blockType = Blocks.stone;
		}
		if(m == Blocks.dirt || m == Blocks.grass){
			this.blockType = Blocks.dirt;
		}
		if(m == Blocks.sand || m == Blocks.sandstone){
			this.blockType = Blocks.sandstone;
		}
		if(m == Blocks.stained_hardened_clay || m == Blocks.hardened_clay || m == Blocks.clay){
			this.blockType = m;
			Location loc2 = l.getBlock().getLocation();
			metadata = loc2.getWorld().getBlockMetadata(loc2.getBlockX(), loc2.getBlockY(), loc2.getBlockZ());
		}

		this.level = level;
		sourceLocation = l.clone();
		blockLocation = l.clone();
		blockMovementState = BLOCK_MVE_IDLE;
		blockMaterialState = 11;
		prevBlockLocation = new Location(blockLocation.getWorld(), 0, 240, 0);
		time = System.currentTimeMillis();
		blockLocation.getBlock().setTypeWithProtection(Blocks.air);
	}

	@Override
	public void update() {
		if(blockMovementState == BLOCK_MVE_IDLE){
			if(!user.isSneaking()){
				prevBlockLocation.getBlock().setTypeWithProtection(Blocks.air);
				this.destroy();
				return;
			}
			Location l = new Location(user.worldObj, user.posX, user.posY + 1.62, user.posZ);
			Vector v = new Vector(user.getLookVec().xCoord, user.getLookVec().yCoord, user.getLookVec().zCoord);
			v.normalize();
			v.multiply(3);
			l.add(v);
			blockLocation = l.clone();
		}
		if(blockMovementState == BLOCK_MVE_TRAVELLING){
			long delta;
			if(lastmovement == 0){
				delta = 10;
			}
			else{
				delta = System.currentTimeMillis() - lastmovement;
			}
			lastmovement = System.currentTimeMillis();
			Location newLoc = blockLocation.add(velocity.clone().multiply(delta));
			if(BendingUtils.isEarthBendable(newLoc.getBlock().getType()) || newLoc.getBlock().getType() == Blocks.air || newLoc.getBlock().getType() == Blocks.water || newLoc.getBlock().getType() == Blocks.lava){
				blockLocation.add(velocity.clone().multiply(delta));
				BendingUtils.damageEntities(this.user, this.blockLocation, 2F, AvatarDamageSource.earthbending, damage);
			}else{
				finished = true;
			}
			if(blockLocation.distance(sourceLocation) > range){
				finished = true;
			}
		}
		if(blockMaterialState == 11){
			if(!(blockLocation == prevBlockLocation)){
				if(blockLocation.getBlock().getType() == Blocks.air || blockLocation.getBlock().getType() == Blocks.water || blockLocation.getBlock().getType() == Blocks.lava){
					prevBlockLocation.getBlock().setTypeWithProtection(Blocks.air);
					blockLocation.getBlock().setTypeWithProtection(blockType);
					prevBlockLocation = blockLocation.clone();					
				}
				
			}
		}
		if(finished){
			prevBlockLocation.getBlock().setTypeWithProtection(Blocks.air);
			this.destroy();
			return;
		}

	}

	public void fireProjectile(Vector vel, int range, int damage){
		if(this.blockMovementState != BLOCK_MVE_IDLE){
			return;
		}
		this.range = range;
		this.damage = damage;
		sourceLocation = blockLocation.clone();
		velocity = vel;
		blockMovementState = BLOCK_MVE_TRAVELLING;
	}
	
}
