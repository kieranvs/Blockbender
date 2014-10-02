package kieranvs.avatar.bending.fire;

import java.util.ArrayList;

import kieranvs.avatar.bending.Ability;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;

import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.AvatarDamageSource;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.PacketSender;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;

public class FireWall extends AsynchronousAbility {

	private ArrayList<BlockBukkit> rootBlocks = new ArrayList<BlockBukkit>();
	private Long time;
	private Long starttime;
	private Long walltime;
	private int total = 10;
	private double size = 1;
	private int height;
	private int length;
	private static int cooldown = 15000;
	private int level;

	public FireWall(EntityLivingBase user, int level) {
		super(user, cooldown + (level * 3000));
		time = System.currentTimeMillis();
		starttime = System.currentTimeMillis();
		if(level == 0){
			walltime = 5000L;
			height = 4;
			length = 4;
		}
		if(level == 1){
			walltime = 10000L;
			height = 8;
			length = 10;
		}
		if(level == 2){
			walltime = 20000L;
			height = 12;
			length = 17;
		}
		this.level = level;
		selectRootBlocks(user);
	}

	@Override
	public void update() {
		if (System.currentTimeMillis() > starttime + walltime) {
			for(BlockBukkit b : rootBlocks){
				if(b.getType() == Blocks.fire){
					b.setTypeWithProtection(Blocks.air);
				}
			}
			destroy();
		}
		if (System.currentTimeMillis() > time + 30L) {
			time = System.currentTimeMillis();
			for (BlockBukkit b : rootBlocks) {
				if (b.getType() != Blocks.fire) {
					b.setTypeWithProtection(Blocks.fire);
				}
				for (int i = 0; i < height; i++) {
					int x = b.getRelative(BlockBukkit.UP, i).getX();
					int y = b.getRelative(BlockBukkit.UP, i).getY();
					int z = b.getRelative(BlockBukkit.UP, i).getZ();
					PacketSender.spawnParticle("Avatar_flamesbig", b.getLocation().getWorld(), x, y, z, total, size);
				}
				
				for(Object o : user.worldObj.loadedEntityList){
					if(o instanceof Entity){
						Entity e = (Entity) o;
						Location l = b.getLocation();
						Location el = new Location(e);
						if(l.distance(el) < 2 && e != user){
							e.setFire(20);
						}

					}
				}
				
				BendingUtils.damageEntities(b.getLocation(), 1, AvatarDamageSource.firebending, level);
			}
		}
	}

	private void selectRootBlocks(EntityLivingBase user) {
		Location origin = new Location(user);
		Vector temp = origin.getDirection();
		temp.normalize();
		double x = temp.getZ();
		double y = 0;
		double z = -temp.getX();
		Vector direction = new Vector(x, y, z);
		int range = length;
		origin.add(temp);
		origin.add(temp);
		Location locationforward = origin.clone();
		Location locationbackward = origin.clone();
		
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
		if (FireStream.isIgnitable(b)) {
			b.setType(Blocks.fire);
			rootBlocks.add(b);
		}
		else if (FireStream.isIgnitable(b.getRelative(BlockBukkit.UP))) {
			b.getRelative(BlockBukkit.UP).setTypeWithProtection(Blocks.fire);
			rootBlocks.add(b.getRelative(BlockBukkit.UP));
		}
		else if (FireStream.isIgnitable(b.getRelative(BlockBukkit.DOWN))) {
			b.getRelative(BlockBukkit.DOWN).setTypeWithProtection(Blocks.fire);
			rootBlocks.add(b.getRelative(BlockBukkit.DOWN));
		}
	}

}
