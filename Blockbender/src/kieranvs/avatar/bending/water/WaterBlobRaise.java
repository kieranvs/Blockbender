package kieranvs.avatar.bending.water;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.AvatarDamageSource;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.Messaging;
import kieranvs.avatar.util.PacketSender;

public class WaterBlobRaise extends AsynchronousAbility {
	
	private Location sourceLocation;
	private Location blobLocation;
	private Location iceLocation;
	private int blobMovementState;
	private int blobMaterialState;
	private Vector velocity;
	private int range;
	private int damage;
	private int level;
	private long lastmovement = 0;
	
	private static int BLOB_MVE_IDLE = 0;
	private static int BLOB_MVE_TRAVELLING = 1;

	private static int BLOB_MAT_WATER = 10;
	private static int BLOB_MAT_FROZEN = 11;

	public WaterBlobRaise(EntityLivingBase user, Location l, int level) {
		super(user, 2500);
		if(!BendingUtils.isWaterBendable(l.getBlock().getType())){
			if(user instanceof EntityPlayer){
				Messaging.avatarMessage((EntityPlayer) this.user, "No nearby water source.");
			}
			this.destroy();
			return;
		}
		if(l.getBlock().getType() != Blocks.water){
			l.getBlock().setTypeWithProtection(Blocks.air);
		}
		this.level = level;
		sourceLocation = l.clone();
		blobLocation = l.clone();
		blobMovementState = BLOB_MVE_IDLE;
		blobMaterialState = BLOB_MAT_WATER;
		iceLocation = new Location(blobLocation.getWorld(), 0, 240, 0);
	}

	@Override
	public void update() {
		if(blobMovementState == BLOB_MVE_IDLE){
			if(!user.isSneaking()){
				if(blobMaterialState == BLOB_MAT_FROZEN) iceLocation.getBlock().setTypeWithProtection(Blocks.air);
				this.destroy();
				return;
			}
			Location l = new Location(user.worldObj, user.posX, user.posY + 1.62, user.posZ);
			Vector v = new Vector(user.getLookVec().xCoord, user.getLookVec().yCoord, user.getLookVec().zCoord);
			v.normalize();
			v.multiply(3);
			l.add(v);
			blobLocation = l.clone();
		}
		if(blobMovementState == BLOB_MVE_TRAVELLING){
			long delta;
			if(lastmovement == 0){
				delta = 10;
			}
			else{
				delta = System.currentTimeMillis() - lastmovement;
			}
			lastmovement = System.currentTimeMillis();
			blobLocation.add(velocity.clone().multiply(delta));
			if(blobLocation.distance(sourceLocation) > range){
				if(blobMaterialState == BLOB_MAT_FROZEN) iceLocation.getBlock().setTypeWithProtection(Blocks.air);
				this.destroy();
			}
			BendingUtils.damageEntities(this.user, this.blobLocation, 2F, AvatarDamageSource.waterbending, damage);
		}
		if(blobMaterialState == BLOB_MAT_FROZEN){
			if(!(blobLocation.getBlockX() == iceLocation.getBlockX() && blobLocation.getBlockY() == iceLocation.getBlockY() && blobLocation.getBlockZ() == iceLocation.getBlockZ())){
				if(blobLocation.getBlock().getType() == Blocks.air || blobLocation.getBlock().getType() == Blocks.water){
					iceLocation.getBlock().setTypeWithProtection(Blocks.air);
					blobLocation.getBlock().setTypeWithProtection(Blocks.ice);
					iceLocation = blobLocation.clone();					
				}
			}
		}
		renderProjectile();
	}
	
	private void renderProjectile(){
		if(blobMaterialState == BLOB_MAT_WATER){
			PacketSender.spawnParticle("Avatar_bubblesplash", user.worldObj, blobLocation.getX(), blobLocation.getY(), blobLocation.getZ());
			PacketSender.spawnParticle("Avatar_watersplashsmall", user.worldObj, blobLocation.getX(), blobLocation.getY(), blobLocation.getZ());
		}
	}
	
	public void shootProjectile(Vector vel, int range, int damage){
		if(this.blobMovementState != BLOB_MVE_IDLE){
			return;
		}
		this.range = range;
		this.damage = damage;
		sourceLocation = blobLocation.clone();
		velocity = vel;
		blobMovementState = BLOB_MVE_TRAVELLING;
	}
	
	public void freezeWaterProjectile(){
		blobMaterialState = BLOB_MAT_FROZEN;
	}

}
