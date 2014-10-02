package kieranvs.avatar.bending.water;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import kieranvs.avatar.bending.Ability;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.Messaging;
import kieranvs.avatar.util.PacketSender;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;

public class WaterIceWall extends AsynchronousAbility {

	private ArrayList<BlockBukkit> rootBlocks = new ArrayList<BlockBukkit>();
	private ArrayList<BlockBukkit> toCrumble = new ArrayList<BlockBukkit>();
	private int stage = 0;
	private Long time;
	private Long starttime;
	private Long endtime;
	private int iteration = 0;
	private boolean isSnow = false;
	private Random r = new Random();
	private static int cooldown = 5000;
	private int level;

	public WaterIceWall(EntityLivingBase user, int level, boolean isSnow) {
		super(user, cooldown + (level * 2500));
		time = System.currentTimeMillis();
		starttime = System.currentTimeMillis();
		this.isSnow = isSnow;
		this.level = level;
		selectRootBlocks(user);
	}

	@Override
	public void update() {
		if (stage == 0) {
			if (System.currentTimeMillis() > time + 100L) {
				iteration++;
				if (iteration > (level * 2) + 5) {
					stage = 1;
					endtime = System.currentTimeMillis();
					return;
				}
				time = System.currentTimeMillis();
				for (BlockBukkit b : rootBlocks) {
					if (b.getRelative(BlockBukkit.UP, iteration).getType() == Blocks.air) {
						if(isSnow){
							b.getRelative(BlockBukkit.UP, iteration).setTypeWithProtection(Blocks.snow);	
						}
						else{
							b.getRelative(BlockBukkit.UP, iteration).setTypeWithProtection(Blocks.ice);		
						}
						toCrumble.add(b.getRelative(BlockBukkit.UP, iteration));
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
						PacketSender.spawnParticle("Avatar_cloud", b.getLocation().getWorld(), b.getX(), b.getY(), b.getZ(), 10, 0.5);
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
		BlockBukkit t = BlockBukkit.getTargetBlock(user);
		Location origin = t.getLocation();
		Location userLoc = new Location(user);
		if(level == 0){
			if(userLoc.distance(origin) > 5){
				if(user instanceof EntityPlayer){
					Messaging.avatarMessage((EntityPlayer) this.user, "Out of range!");
				}
				destroy();
			}
		}
		if(level == 1){
			if(userLoc.distance(origin) > 10){
				if(user instanceof EntityPlayer){
					Messaging.avatarMessage((EntityPlayer) this.user, "Out of range!");
				}
				destroy();
			}
		}
		if(level == 2){
			if(userLoc.distance(origin) > 20){
				if(user instanceof EntityPlayer){
					Messaging.avatarMessage((EntityPlayer) this.user, "Out of range!");
				}
				destroy();
			}
		}
		Location locationforward = origin.clone();
		Location locationbackward = origin.clone();
		Vector temp = userLoc.getDirection();
		temp.normalize();
		double x = temp.getZ();
		double y = 0;
		double z = -temp.getX();
		Vector direction = new Vector(x, y, z);
		int range = 0;
		if(level == 0){
			range = 4;
		}
		if(level == 1){
			range = 8;
		}
		if(level == 2){
			range = 12;
		}

		while (locationforward.distance(origin) < range) {
			locationforward.add(direction);
			addRootBlock(locationforward.getBlock());
		}

		while (locationbackward.distance(origin) < range) {
			locationbackward.subtract(direction);
			addRootBlock(locationbackward.getBlock());
		}

	}

	private void addRootBlock(BlockBukkit b) {
		if (isWaterbendable(b)) {
			rootBlocks.add(b);
		}
		else if (isWaterbendable(b.getRelative(BlockBukkit.UP))) {
			rootBlocks.add(b.getRelative(BlockBukkit.UP));
		}
		else if (isWaterbendable(b.getRelative(BlockBukkit.DOWN))) {
			rootBlocks.add(b.getRelative(BlockBukkit.DOWN));
		}
	}

	private boolean isWaterbendable(BlockBukkit b) {
		Block[] bendable = { Blocks.ice, Blocks.water, Blocks.flowing_water, Blocks.snow, Blocks.snow };
		if (Arrays.asList(bendable).contains(b.getType())) {
			return true;
		}
		else {
			return false;
		}
	}
}
