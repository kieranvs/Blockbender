package kieranvs.avatar.bending.fire;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.bending.Ability;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;

import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.AvatarDamageSource;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.EntityActionPerformer;
import kieranvs.avatar.util.PacketSender;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;

public class FireStream extends AsynchronousAbility {

	private float speed = 15F;
	private long interval = (long) (1000L / speed);
	private long burntime = 400L;
	private ConcurrentHashMap<BlockBukkit, Long> onFire = new ConcurrentHashMap<BlockBukkit, Long>();
	private Location origin;
	private Location location;
	private Vector direction;
	private int range;
	private long time;
	private Block fireBlock = Blocks.fire;
	private boolean finished = false;
	private Random r;

	public FireStream(Location location, Vector direction, int level, EntityLivingBase user) {
		super(user, 3000 + (level * 3000));
		this.time = System.currentTimeMillis();
		this.origin = location.clone();
		this.location = location.clone();
		this.direction = direction.clone();
		this.direction.setY(0);
		this.direction.normalize();
		this.location.add(this.direction);
		this.user = user;
		if(level == 0){
			range = 6;
		}
		if(level == 1){
			range = 12;
		}
		if(level == 2){
			fireBlock = mod_Avatar.BlueFireIns;
			range = 20;
		}
		r = new Random();
	}

	@Override
	public void update() {
		if(user.isBurning()){
			user.extinguish();
		}
		for (BlockBukkit block : onFire.keySet()) {
			if(r.nextInt(300) == 0) PacketSender.spawnParticle("Avatar_flames", this.location.getWorld(), block.getX() + r.nextDouble(), block.getY() + 0.5D + r.nextDouble(), block.getZ() + r.nextDouble());
			if (block.getType() != fireBlock) {
				onFire.remove(block);
			}
			else {
				long time = onFire.get(block).longValue();
				if (System.currentTimeMillis() > time + burntime) {
					block.setTypeWithProtection(Blocks.air);
					onFire.remove(block);
				}
			}
		}
		if (finished) {
			if (onFire.isEmpty()) {
				destroy();
				return;
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
			if (isIgnitable(block)) {
				ignite(block);
				return;
			}
			if (isIgnitable(block.getRelative(BlockBukkit.DOWN))) {
				ignite(block.getRelative(BlockBukkit.DOWN));
				location = block.getRelative(BlockBukkit.DOWN).getLocation();
				return;
			}
			if (isIgnitable(block.getRelative(BlockBukkit.UP))) {
				ignite(block.getRelative(BlockBukkit.UP));
				location = block.getRelative(BlockBukkit.UP).getLocation();
				return;
			}
			finished = true;
			return;
		}
	}

	public void ignite(BlockBukkit block) {
		block.setTypeWithProtection(fireBlock);
		onFire.put(block, System.currentTimeMillis());
		if(fireBlock == mod_Avatar.BlueFireIns){
			BendingUtils.damageEntitiesWithCustomAction(user, block.getLocation(), 1F, AvatarDamageSource.firebending, 5, new EntityActionPerformer(){
				@Override
				public void performAction(Entity e) {
					e.setFire(2);				
				}
			});
		}
		else{
			BendingUtils.damageEntitiesWithCustomAction(user, block.getLocation(), 1F, AvatarDamageSource.firebending, 3, new EntityActionPerformer(){
				@Override
				public void performAction(Entity e) {
					e.setFire(1);				
				}
			});
		}
	}

	public static boolean isIgnitable(BlockBukkit block) {
		Block[] overwriteable = { Blocks.sapling, Blocks.tallgrass, Blocks.deadbush, Blocks.yellow_flower, Blocks.red_flower, Blocks.brown_mushroom, Blocks.red_mushroom, Blocks.fire, Blocks.snow, Blocks.torch };

		if (Arrays.asList(overwriteable).contains(block.getType())) {
			return true;
		}
		if (block.getType() == mod_Avatar.BlueFireIns) {
			return true;
		}
		if (block.getType() != Blocks.air) {
			return false;
		}

		Block[] ignitable = { mod_Avatar.CharredGrassBlock, Blocks.bedrock, Blocks.bookshelf, Blocks.brick_block, Blocks.clay, Blocks.coal_ore, Blocks.cobblestone, Blocks.diamond_ore, Blocks.diamond_block, Blocks.dirt, Blocks.end_stone, Blocks.redstone_ore, Blocks.gold_block, Blocks.gravel, Blocks.grass, Blocks.red_mushroom_block, Blocks.brown_mushroom_block, Blocks.lapis_block, Blocks.lapis_ore, Blocks.log, Blocks.mossy_cobblestone, Blocks.mycelium, Blocks.nether_brick, Blocks.netherrack, Blocks.obsidian, Blocks.redstone_ore, Blocks.sand, Blocks.sandstone, Blocks.stonebrick, Blocks.stone, Blocks.soul_sand, Blocks.snow, Blocks.planks, Blocks.wool, Blocks.leaves };

		BlockBukkit belowblock = block.getRelative(BlockBukkit.DOWN);
		if (Arrays.asList(ignitable).contains(belowblock.getType())) {
			return true;
		}
		return false;
	}

}
