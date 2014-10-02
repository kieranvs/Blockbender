package kieranvs.avatar.bending.earth;

import java.util.ArrayList;
import java.util.Random;

import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.PacketSender;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;

public class EarthWall extends AsynchronousAbility {

	private ArrayList<BlockBukkit> rootBlocks = new ArrayList<BlockBukkit>();
	private ArrayList<BlockBukkit> toCrumble = new ArrayList<BlockBukkit>();
	private int stage = 0;
	private Long time;
	private Long starttime;
	private Long endtime;
	private int iteration = 0;
	private Random r = new Random();
	private int range = 5;
	private int height = 5;

	public EarthWall(EntityLivingBase user, Long cooldown, int height, int range) {
		super(user, cooldown);
		time = System.currentTimeMillis();
		starttime = System.currentTimeMillis();
		this.range = range;
		this.height = height;
		selectRootBlocks(user);
	}

	@Override
	public void update() {
		if (stage == 0) {
			if (System.currentTimeMillis() > time + 100L) {
				iteration++;
				if (iteration > this.height) {
					stage = 1;
					endtime = System.currentTimeMillis();
					return;
				}
				time = System.currentTimeMillis();
				for (BlockBukkit b : rootBlocks) {
					if (b.getRelative(BlockBukkit.UP, iteration).getType() == Blocks.air) {
						Block m = b.getRelative(BlockBukkit.UP, iteration - 1).getType();
						Block blockType = null;
						boolean shouldSetBlock = true;
						int metadata = 0;
						if(BendingUtils.isEarthBendable(m)){
							blockType = Blocks.stone;
						} else{
							shouldSetBlock = false;
						}
						if(m == Blocks.dirt || m == Blocks.grass){
							blockType = Blocks.dirt;
						}
						if(m == Blocks.sand || m == Blocks.sandstone){
							blockType = Blocks.sandstone;
						}
						if(m == Blocks.stained_hardened_clay || m == Blocks.hardened_clay || m == Blocks.clay || m == Blocks.gravel){
							blockType = m;
							Location loc2 = b.getRelative(BlockBukkit.UP, iteration - 1).getLocation();
							metadata = loc2.getWorld().getBlockMetadata(loc2.getBlockX(), loc2.getBlockY(), loc2.getBlockZ());
						}
						if(shouldSetBlock){
							b.getRelative(BlockBukkit.UP, iteration).setTypeWithProtection(blockType).setMetadataWithProtection(metadata);
							toCrumble.add(b.getRelative(BlockBukkit.UP, iteration));
						}
					}
				}
			}
		}
		else {
			if (System.currentTimeMillis() - endtime > 3000L) {
				if (System.currentTimeMillis() - time > 250L) {
					time = System.currentTimeMillis();
					if (toCrumble.size() > 0) {
						BlockBukkit b = toCrumble.get(r.nextInt(toCrumble.size()));
						boolean isTop = false;
						while (!isTop) {
							if (toCrumble.contains(b.getRelative(BlockBukkit.UP))) {
								b = b.getRelative(BlockBukkit.UP);
							}
							else {
								isTop = true;
							}
						}
						toCrumble.remove(b);
						b.setTypeWithProtection(Blocks.air);
						PacketSender.spawnParticle("Avatar_dig", user.worldObj, b.getX(), b.getY(), b.getZ(), 50, 0.5);			
					}
					else {
						destroy();
						return;
					}
				}
			}
		}
	}

	private void selectRootBlocks(EntityLivingBase user) {
		Location origin = new Location(user);
		Location locationforward = origin.clone();
		Location locationbackward = origin.clone();
		Vector temp = origin.getDirection();
		temp.normalize();
		double x = temp.getZ();
		double y = 0;
		double z = -temp.getX();
		Vector direction = new Vector(x, y, z);

		while (locationforward.distance(origin) < this.range) {
			locationforward.add(direction);
			addRootBlock(locationforward.getBlock());
		}

		while (locationbackward.distance(origin) < this.range) {
			locationbackward.subtract(direction);
			addRootBlock(locationbackward.getBlock());
		}

	}

	private void addRootBlock(BlockBukkit b) {
		if (BendingUtils.isEarthBendable(b.getType())) {
			rootBlocks.add(b);
		}
		else if (BendingUtils.isEarthBendable(b.getRelative(BlockBukkit.UP).getType())) {
			rootBlocks.add(b.getRelative(BlockBukkit.UP));
		}
		else if (BendingUtils.isEarthBendable(b.getRelative(BlockBukkit.DOWN).getType())) {
			rootBlocks.add(b.getRelative(BlockBukkit.DOWN));
		}
	}

}
