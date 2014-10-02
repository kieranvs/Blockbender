package kieranvs.avatar.bending.earth;

import kieranvs.avatar.Protection;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.Messaging;
import kieranvs.avatar.util.PacketSender;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;

public class EarthBridge extends AsynchronousAbility {
	
	private Location location;
	private int length;
	private long time;
	private int iteration;
	private Block blockType;
	private int metadata;
	private int forwardDirection = BlockBukkit.WEST;
	private int sideDirectionA = BlockBukkit.NORTH;
	private int sideDirectionB = BlockBukkit.SOUTH;
	private boolean hasNaggedUser = false;

	public EarthBridge(EntityLivingBase e, Location l, int length) {
		super(e, 20000);
		this.location = l;
		this.length = length;
		this.time = System.currentTimeMillis();
		this.iteration = -1;
		Block m = this.location.getBlock().getRelative(BlockBukkit.DOWN).getType();
		if(BendingUtils.isEarthBendable(m)){
			this.blockType = Blocks.stone;
		}
		if(m == Blocks.dirt || m == Blocks.grass){
			this.blockType = Blocks.dirt;
		}
		if(m == Blocks.sand || m == Blocks.sandstone){
			this.blockType = Blocks.sandstone;
		}
		if(m == Blocks.stained_hardened_clay || m == Blocks.hardened_clay || m == Blocks.clay || m == Blocks.gravel){
			this.blockType = m;
			Location loc2 = this.location.getBlock().getRelative(BlockBukkit.DOWN).getLocation();
			metadata = loc2.getWorld().getBlockMetadata(loc2.getBlockX(), loc2.getBlockY(), loc2.getBlockZ());
		}
		Vector direction = l.getDirection().straightifyXZ();
		if(direction.getX() == 1){
			forwardDirection = BlockBukkit.EAST;
			sideDirectionA = BlockBukkit.NORTH;
			sideDirectionB = BlockBukkit.SOUTH;
		}
		if(direction.getX() == -1){
			forwardDirection = BlockBukkit.WEST;
			sideDirectionA = BlockBukkit.NORTH;
			sideDirectionB = BlockBukkit.SOUTH;
		}
		if(direction.getZ() == 1){
			forwardDirection = BlockBukkit.SOUTH;
			sideDirectionA = BlockBukkit.EAST;
			sideDirectionB = BlockBukkit.WEST;
		}
		if(direction.getZ() == -1){
			forwardDirection = BlockBukkit.NORTH;
			sideDirectionA = BlockBukkit.EAST;
			sideDirectionB = BlockBukkit.WEST;
		}
	}

	@Override
	public void update() {
		if(iteration >= this.length){
			destroy();
			return;
		}
		if(this.blockType == null){
			if(this.user instanceof EntityPlayer){
				Messaging.avatarMessage((EntityPlayer)this.user, "Not standing on an Earth bendable block!");
			}
			destroy();
			return;
		}
		
		iteration++;
		BlockBukkit b = this.location.getBlock().getRelative(BlockBukkit.DOWN);
		BlockBukkit w = b.getRelative(forwardDirection, iteration + 1);
		if(w.getType() == Blocks.air || w.getType() == Blocks.water || w.getType() == Blocks.lava){
			Protection.trySetBlock(user.worldObj, blockType, w.getX(), w.getY(), w.getY());
			PacketSender.spawnParticle("Avatar_dig", user.worldObj, w.getX(), w.getY(), w.getZ(), 50, 0.5);
			w.getLocation().getWorld().setBlockMetadataWithNotify(w.getX(), w.getY(), w.getZ(), metadata, 0x02);
			hasNaggedUser = true;
		}else{
			if(this.user instanceof EntityPlayer && !hasNaggedUser){
				Messaging.avatarMessage((EntityPlayer)this.user, "Bridge must go through air, water or lava!");
				hasNaggedUser = true;
			}
		}
		w = b.getRelative(forwardDirection, iteration + 1).getRelative(sideDirectionA);
		if(w.getType() == Blocks.air || w.getType() == Blocks.water || w.getType() == Blocks.lava){
			Protection.trySetBlock(user.worldObj, blockType, w.getX(), w.getY(), w.getY());
			PacketSender.spawnParticle("Avatar_dig", user.worldObj, w.getX(), w.getY(), w.getZ(), 50, 0.5);
			w.getLocation().getWorld().setBlockMetadataWithNotify(w.getX(), w.getY(), w.getZ(), metadata, 0x02);
			hasNaggedUser = true;
		}else{
			if(this.user instanceof EntityPlayer && !hasNaggedUser){
				Messaging.avatarMessage((EntityPlayer)this.user, "Bridge must go through air, water or lava!");
				hasNaggedUser = true;
			}
		}
		w = b.getRelative(forwardDirection, iteration + 1).getRelative(sideDirectionB);
		if(w.getType() == Blocks.air || w.getType() == Blocks.water || w.getType() == Blocks.lava){
			Protection.trySetBlock(user.worldObj, blockType, w.getX(), w.getY(), w.getY());
			PacketSender.spawnParticle("Avatar_dig", user.worldObj, w.getX(), w.getY(), w.getZ(), 50, 0.5);
			w.getLocation().getWorld().setBlockMetadataWithNotify(w.getX(), w.getY(), w.getZ(), metadata, 0x02);
			hasNaggedUser = true;
		}else{
			if(this.user instanceof EntityPlayer && !hasNaggedUser){
				Messaging.avatarMessage((EntityPlayer)this.user, "Bridge must go through air, water or lava!");
				hasNaggedUser = true;
			}
		}
	}

}
