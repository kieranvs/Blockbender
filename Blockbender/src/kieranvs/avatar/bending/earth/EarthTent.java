package kieranvs.avatar.bending.earth;

import kieranvs.avatar.bending.Ability;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.Messaging;
import kieranvs.avatar.util.PacketSender;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;

public class EarthTent extends Ability {

	private Location origin;
	private int iteration;
	private long time;
	private long speed;
	private Block blockType;
	private int metadata;

	public EarthTent(EntityLivingBase user, long speed) {
		super(user);
		origin = new Location(user);
		iteration = 0;
		this.speed = speed;
		time = System.currentTimeMillis();
		Block m = origin.getBlock().getRelative(BlockBukkit.DOWN).getType();
		if(BendingUtils.isEarthBendable(m)){
			this.blockType = Blocks.stone;
		}else{
			Messaging.avatarMessage((EntityPlayer) this.user, "Not standing on Earth bendable block!");
			destroy();
			return;
		}
		if(m == Blocks.dirt || m == Blocks.grass){
			this.blockType = Blocks.dirt;
		}
		if(m == Blocks.sand || m == Blocks.sandstone){
			this.blockType = Blocks.sandstone;
		}
		if(m == Blocks.stained_hardened_clay || m == Blocks.hardened_clay || m == Blocks.clay || m == Blocks.gravel){
			this.blockType = m;
			Location loc2 = origin.getBlock().getRelative(BlockBukkit.DOWN).getLocation();
			metadata = loc2.getWorld().getBlockMetadata(loc2.getBlockX(), loc2.getBlockY(), loc2.getBlockZ());
		}
	}

	@Override
	public void update() {
		if (System.currentTimeMillis() - time > speed) {
			iteration++;
			time = System.currentTimeMillis();
			if (iteration > 4) {
				destroy();
				return;
			}
			PacketSender.spawnParticle("Avatar_dig", user.worldObj, origin.getX(), origin.getY(), origin.getZ(), 500, 1.5);
			if (iteration == 1) {
				origin.getBlock().getRelative(BlockBukkit.WEST, 2).getRelative(BlockBukkit.NORTH, 2).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.WEST, 1).getRelative(BlockBukkit.NORTH, 2).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.WEST, 2).getRelative(BlockBukkit.NORTH, 1).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.WEST, 2).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.WEST, 2).getRelative(BlockBukkit.SOUTH, 1).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.WEST, 1).getRelative(BlockBukkit.SOUTH, 2).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.WEST, 2).getRelative(BlockBukkit.SOUTH, 2).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);

				origin.getBlock().getRelative(BlockBukkit.EAST, 2).getRelative(BlockBukkit.NORTH, 2).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.EAST, 1).getRelative(BlockBukkit.NORTH, 2).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.EAST, 2).getRelative(BlockBukkit.NORTH, 1).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.EAST, 2).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.EAST, 2).getRelative(BlockBukkit.SOUTH, 1).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.EAST, 1).getRelative(BlockBukkit.SOUTH, 2).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.EAST, 2).getRelative(BlockBukkit.SOUTH, 2).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
			}
			if (iteration == 2) {
				origin.getBlock().getRelative(BlockBukkit.UP, 1).getRelative(BlockBukkit.WEST, 2).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.UP, 1).getRelative(BlockBukkit.WEST, 2).getRelative(BlockBukkit.NORTH, 1).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.UP, 1).getRelative(BlockBukkit.WEST).getRelative(BlockBukkit.NORTH, 2).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.UP, 1).getRelative(BlockBukkit.WEST, 2).getRelative(BlockBukkit.SOUTH, 1).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.UP, 1).getRelative(BlockBukkit.WEST).getRelative(BlockBukkit.SOUTH, 2).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);

				origin.getBlock().getRelative(BlockBukkit.UP, 1).getRelative(BlockBukkit.EAST, 2).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.UP, 1).getRelative(BlockBukkit.EAST, 2).getRelative(BlockBukkit.NORTH, 1).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.UP, 1).getRelative(BlockBukkit.EAST).getRelative(BlockBukkit.NORTH, 2).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.UP, 1).getRelative(BlockBukkit.EAST, 2).getRelative(BlockBukkit.SOUTH, 1).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.UP, 1).getRelative(BlockBukkit.EAST).getRelative(BlockBukkit.SOUTH, 2).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
			}
			if (iteration == 3) {
				origin.getBlock().getRelative(BlockBukkit.UP, 2).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.UP, 2).getRelative(BlockBukkit.NORTH, 1).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.UP, 2).getRelative(BlockBukkit.NORTH, 2).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.UP, 2).getRelative(BlockBukkit.SOUTH, 1).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.UP, 2).getRelative(BlockBukkit.SOUTH, 2).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.UP, 2).getRelative(BlockBukkit.NORTH, 1).getRelative(BlockBukkit.WEST, 1).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.UP, 2).getRelative(BlockBukkit.WEST, 1).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.UP, 2).getRelative(BlockBukkit.SOUTH, 1).getRelative(BlockBukkit.WEST, 1).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.UP, 2).getRelative(BlockBukkit.NORTH, 1).getRelative(BlockBukkit.EAST, 1).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.UP, 2).getRelative(BlockBukkit.EAST, 1).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.UP, 2).getRelative(BlockBukkit.SOUTH, 1).getRelative(BlockBukkit.EAST, 1).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);

			}
			if (iteration == 4) {
				origin.getBlock().getRelative(BlockBukkit.NORTH, 2).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.NORTH, 2).getRelative(BlockBukkit.UP).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.SOUTH, 2).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
				origin.getBlock().getRelative(BlockBukkit.SOUTH, 2).getRelative(BlockBukkit.UP).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
			}
		}
	}

}
