package kieranvs.avatar.bending.earth;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;

import kieranvs.avatar.Protection;
import kieranvs.avatar.bending.Ability;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;

import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.AvatarDamageSource;
import kieranvs.avatar.util.BendingUtils;

public class EarthStream extends AsynchronousAbility {

	private long interval = 200L;
	private long risetime = 600L;
	private ConcurrentHashMap<BlockBukkit, Long> movedBlocks = new ConcurrentHashMap<BlockBukkit, Long>();
	private Location origin;
	private Location location;
	private Vector direction;
	private int range;
	private long time;
	private boolean finished = false;

	public EarthStream(EntityLivingBase user, Location location, Vector direction, int range) {
		super(user, 2000);
		this.time = System.currentTimeMillis();
		this.range = range;
		this.origin = location.clone();
		this.location = location.clone();
		this.direction = direction.clone();
		this.direction.setY(0);
		this.direction.normalize();
		this.location.add(this.direction);
	}

	@Override
	public void update() {
		for (BlockBukkit block : movedBlocks.keySet()) {
			long time = movedBlocks.get(block).longValue();
			if (System.currentTimeMillis() > time + risetime) {
				Protection.trySetBlock(user.worldObj, Blocks.air, block.getRelative(BlockBukkit.UP).getX(), block.getRelative(BlockBukkit.UP).getY(), 
						block.getRelative(BlockBukkit.UP).getZ());
				movedBlocks.remove(block);
			}
		}

		if (finished) {
			if (movedBlocks.isEmpty()) {
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
			if (isMoveable(block)) {
				moveBlock(block);
			}
			if (isMoveable(block.getRelative(BlockBukkit.DOWN))) {
				moveBlock(block.getRelative(BlockBukkit.DOWN));
				location = block.getRelative(BlockBukkit.DOWN).getLocation();
				return;
			}
			if (isMoveable(block.getRelative(BlockBukkit.UP))) {
				moveBlock(block.getRelative(BlockBukkit.UP));
				location = block.getRelative(BlockBukkit.UP).getLocation();
				return;
			}
			else {
				finished = true;
			}
			return;
		}
	}

	public void moveBlock(BlockBukkit block) {
		// block.getRelative(Block.UP).breakNaturally();
		//block.getRelative(Block.UP).setType(block.getType());
		//movedBlocks.put(block, System.currentTimeMillis());
		//damageEntities(block.getLocation());
		//BendingUtils.damageEntities(block.getLocation(), 3.5F, AvatarDamageSource.earthbending, 3);
		Protection.trySetBlock(user.worldObj, Blocks.dirt, block.getRelative(BlockBukkit.UP).getX(), block.getRelative(BlockBukkit.UP).getY(), 
				block.getRelative(BlockBukkit.UP).getZ());
		movedBlocks.put(block, System.currentTimeMillis());
	}

	public void damageEntities(Location loc) {
		for (Object o : loc.getWorld().getLoadedEntityList()) {
			if (o instanceof EntityLivingBase) {
				EntityLivingBase e = (EntityLivingBase) o;
				if (loc.distance(e) < 3.5) {
					e.attackEntityFrom(AvatarDamageSource.earthbending, 3);
				}
			}
		}
	}

	public static boolean isMoveable(BlockBukkit block) {

		Block[] overwriteable = { Blocks.air, Blocks.sapling, Blocks.tallgrass, Blocks.deadbush, Blocks.yellow_flower, Blocks.red_flower, Blocks.brown_mushroom, Blocks.red_mushroom, Blocks.fire, Blocks.snow, Blocks.torch, Blocks.leaves, Blocks.cactus, Blocks.reeds, Blocks.web, Blocks.waterlily, Blocks.vine };

		if (!Arrays.asList(overwriteable).contains(block.getRelative(BlockBukkit.UP).getType())) {
			return false;
		}

		Block[] moveable = { Blocks.brick_block, Blocks.clay, Blocks.coal_ore, Blocks.cobblestone, Blocks.dirt, Blocks.grass, Blocks.gravel, Blocks.mossy_cobblestone, Blocks.mycelium, Blocks.nether_brick, Blocks.netherrack, Blocks.obsidian, Blocks.sand, Blocks.sandstone, Blocks.farmland, Blocks.soul_sand, Blocks.stone };

		if (Arrays.asList(moveable).contains(block.getType())) {
			return true;
		}

		return false;
	}

}
