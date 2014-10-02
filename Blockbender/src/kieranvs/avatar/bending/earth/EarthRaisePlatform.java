package kieranvs.avatar.bending.earth;

import kieranvs.avatar.Protection;
import kieranvs.avatar.bending.Ability;
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

public class EarthRaisePlatform extends AsynchronousAbility {

	private long time;
	private int iteration;
	private Location location;
	private int height;
	private Block platformBlock;
	private int metadata;
	private Block block;
	private int level;

	public EarthRaisePlatform(EntityLivingBase user, int height, int level) {
		super(user, level*level*level * 1000);
		this.user = user;
		this.time = System.currentTimeMillis();
		this.iteration = 0;
		this.location = new Location(user);
		this.height = height;
		this.level = level;
	}

	@Override
	public void update() {
		if (iteration > height) {
			destroy();
		}

		if (System.currentTimeMillis() - time > 100L) {
			iteration++;
			time = System.currentTimeMillis();
			block = location.getBlock().getRelative(BlockBukkit.DOWN).getType();
			if(BendingUtils.isEarthBendable(block)){
				platformBlock = Blocks.stone;
			} else{
				Messaging.avatarMessage((EntityPlayer) this.user, "Not standing on an Earth bendable block!");
				destroy();
				return;
			}
			if(block == Blocks.dirt || block == Blocks.grass){
				platformBlock = Blocks.dirt;
			}
			if(block == Blocks.sand || block == Blocks.sandstone){
				platformBlock = Blocks.sandstone;
			}
			if(block == Blocks.stained_hardened_clay || block == Blocks.hardened_clay || block == Blocks.clay || block == Blocks.gravel){
				platformBlock = block;
				Location loc2 = this.location.getBlock().getRelative(BlockBukkit.DOWN).getLocation();
				metadata = loc2.getWorld().getBlockMetadata(loc2.getBlockX(), loc2.getBlockY(), loc2.getBlockZ());
			}

			PacketSender.sendVelocity(user, new Vector(0, 0.5, 0));
			Protection.trySetBlockAndMeta(user.worldObj,platformBlock, location.getBlock().getX(), location.getBlock().getY(), 
					location.getBlock().getZ(), metadata, 0x02);

			PacketSender.spawnParticle("Avatar_dig", user.worldObj, location.getX(), location.getY(), location.getZ(), 50, 0.5);
			if(level == 3){
				PacketSender.spawnParticle("Avatar_dig", user.worldObj, location.getX(), location.getY(), location.getZ(), 100, 1);
				Protection.trySetBlockAndMeta(user.worldObj, platformBlock, location.getBlock().getX(), location.getBlock().getY(), location.getBlock().getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, platformBlock, location.getBlock().getRelative(BlockBukkit.NORTH).getX(), location.getBlock().getRelative(BlockBukkit.NORTH).getY(), location.getBlock().getRelative(BlockBukkit.NORTH).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, platformBlock, location.getBlock().getRelative(BlockBukkit.WEST).getX(), location.getBlock().getRelative(BlockBukkit.WEST).getY(), location.getBlock().getRelative(BlockBukkit.WEST).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, platformBlock, location.getBlock().getRelative(BlockBukkit.EAST).getX(), location.getBlock().getRelative(BlockBukkit.EAST).getY(), location.getBlock().getRelative(BlockBukkit.EAST).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, platformBlock, location.getBlock().getRelative(BlockBukkit.SOUTH).getX(), location.getBlock().getRelative(BlockBukkit.SOUTH).getY(), location.getBlock().getRelative(BlockBukkit.SOUTH).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, platformBlock, location.getBlock().getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.EAST).getX(), location.getBlock().getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.EAST).getY(), 
						location.getBlock().getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.EAST).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, platformBlock, location.getBlock().getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.WEST).getX(), location.getBlock().getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.WEST).getY(), 
						location.getBlock().getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.WEST).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, platformBlock, location.getBlock().getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.EAST).getX(), location.getBlock().getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.EAST).getY(), 
						location.getBlock().getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.EAST).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, platformBlock, location.getBlock().getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.WEST).getX(), location.getBlock().getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.WEST).getY(), 
						location.getBlock().getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.WEST).getZ(), metadata, 0x02);

			}
			if (location.getY() > 254) {
				destroy();
				return;
			}
			location.setY(location.getY() + 1);
		}

	}

}
