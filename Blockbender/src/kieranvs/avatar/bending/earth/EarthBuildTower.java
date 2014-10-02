package kieranvs.avatar.bending.earth;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import kieranvs.avatar.Protection;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.PacketSender;

public class EarthBuildTower extends AsynchronousAbility {

	private Location location;
	private int height;
	private long time;
	private int iteration;
	private Block blockType;
	private int metadata;

	public EarthBuildTower(EntityLivingBase e, Location l, int height) {
		super(e, 50000);
		this.location = l;
		this.height = height;
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

	}

	@Override
	public void update() {
		if(iteration > height || this.blockType == null){
			this.destroy();
			return;
		}
		if(System.currentTimeMillis() > time + 500L){
			this.time = System.currentTimeMillis();
			iteration++;
			if(iteration == 0){
				BlockBukkit b = location.getBlock().getRelative(BlockBukkit.DOWN);
				PacketSender.spawnParticle("Avatar_dig", user.worldObj, b.getX(), b.getY(), b.getZ(), 150, 1);
				Protection.trySetBlockAndMeta(user.worldObj, blockType, b.getX(), b.getY(), b.getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, blockType, b.getRelative(BlockBukkit.NORTH).getX(), b.getRelative(BlockBukkit.NORTH).getY(), b.getRelative(BlockBukkit.NORTH).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, blockType, b.getRelative(BlockBukkit.WEST).getX(), b.getRelative(BlockBukkit.WEST).getY(), b.getRelative(BlockBukkit.WEST).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, blockType, b.getRelative(BlockBukkit.EAST).getX(), b.getRelative(BlockBukkit.EAST).getY(), b.getRelative(BlockBukkit.EAST).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, blockType, b.getRelative(BlockBukkit.SOUTH).getX(), b.getRelative(BlockBukkit.SOUTH).getY(), b.getRelative(BlockBukkit.SOUTH).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, blockType, b.getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.EAST).getX(), b.getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.EAST).getY(), 
						b.getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.EAST).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, blockType, b.getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.WEST).getX(), b.getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.WEST).getY(), 
						b.getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.WEST).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, blockType, b.getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.EAST).getX(), b.getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.EAST).getY(), 
						b.getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.EAST).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, blockType, b.getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.WEST).getX(), b.getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.WEST).getY(), 
						b.getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.WEST).getZ(), metadata, 0x02);
			}
			if(iteration > 0 && iteration < height && iteration != 1 && iteration != 2){
				BlockBukkit b = location.getBlock().getRelative(BlockBukkit.UP, iteration - 1);
				PacketSender.spawnParticle("Avatar_dig", user.worldObj, b.getX(), b.getY(), b.getZ(), 150, 1);

				Protection.trySetBlockAndMeta(user.worldObj, blockType, b.getRelative(BlockBukkit.NORTH).getX(), b.getRelative(BlockBukkit.NORTH).getY(), b.getRelative(BlockBukkit.NORTH).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, blockType, b.getRelative(BlockBukkit.WEST).getX(), b.getRelative(BlockBukkit.WEST).getY(), b.getRelative(BlockBukkit.WEST).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, blockType, b.getRelative(BlockBukkit.EAST).getX(), b.getRelative(BlockBukkit.EAST).getY(), b.getRelative(BlockBukkit.EAST).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, blockType, b.getRelative(BlockBukkit.SOUTH).getX(), b.getRelative(BlockBukkit.SOUTH).getY(), b.getRelative(BlockBukkit.SOUTH).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, blockType, b.getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.EAST).getX(), b.getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.EAST).getY(), 
						b.getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.EAST).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, blockType, b.getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.WEST).getX(), b.getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.WEST).getY(), 
						b.getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.WEST).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, blockType, b.getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.EAST).getX(), b.getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.EAST).getY(), 
						b.getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.EAST).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, blockType, b.getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.WEST).getX(), b.getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.WEST).getY(), 
						b.getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.WEST).getZ(), metadata, 0x02);
			}
			if(iteration == height || iteration == 1 || iteration == 2){
				BlockBukkit b = location.getBlock().getRelative(BlockBukkit.UP, iteration - 1);
				PacketSender.spawnParticle("Avatar_dig", user.worldObj, b.getX(), b.getY(), b.getZ(), 150, 1);
				Protection.trySetBlockAndMeta(user.worldObj, blockType, b.getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.EAST).getX(), b.getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.EAST).getY(), 
						b.getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.EAST).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, blockType, b.getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.WEST).getX(), b.getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.WEST).getY(), 
						b.getRelative(BlockBukkit.NORTH).getRelative(BlockBukkit.WEST).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, blockType, b.getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.EAST).getX(), b.getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.EAST).getY(), 
						b.getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.EAST).getZ(), metadata, 0x02);
				Protection.trySetBlockAndMeta(user.worldObj, blockType, b.getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.WEST).getX(), b.getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.WEST).getY(), 
						b.getRelative(BlockBukkit.SOUTH).getRelative(BlockBukkit.WEST).getZ(), metadata, 0x02);
			}
		}
	}

}
