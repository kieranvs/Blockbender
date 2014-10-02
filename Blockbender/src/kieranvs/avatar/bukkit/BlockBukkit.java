package kieranvs.avatar.bukkit;


import kieranvs.avatar.Protection;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockBukkit {

	private World w;

	private int x;
	private int y;
	private int z;

	public static int UP = 0;
	public static int DOWN = 1;
	public static int EAST = 2;
	public static int WEST = 3;
	public static int NORTH = 4;
	public static int SOUTH = 5;

	public BlockBukkit(World w, int x, int y, int z) {
		this.w = w;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public static BlockBukkit getTargetBlock(EntityLivingBase user){
		return getTargetBlock(user, 120);
	}

	public static BlockBukkit getTargetBlock(EntityLivingBase user, int range) {
		Location l = new Location(user.worldObj, user.posX, user.posY + 1.62, user.posZ);
		Vector v = new Vector(user.getLookVec().xCoord, user.getLookVec().yCoord, user.getLookVec().zCoord);
		v.normalize();
		l.add(v);
		while (l.getBlock().getType() == Blocks.air) {
			l.add(v);
			if (l.getY() < 0 || l.getY() > 255 || l.distance(user) > range) {
				l.subtract(v);
				return l.getBlock();
			}
		}
		return l.getBlock();
	}
	
	public void setMetadata(int meta){
		this.w.setBlockMetadataWithNotify(x, y, z, meta, 0x02);
	}
	
	public void setMetadataWithProtection(int meta){
		Protection.trySetMeta(w, x, y, z, meta, 0x02);
	}
	
	public int getMetadata(){
		return w.getBlockMetadata(x, y, z);
	}
	
	public net.minecraft.block.Block getType() {
		return w.getBlock(x, y, z);
	}

	public BlockBukkit setType(net.minecraft.block.Block b) {
		w.setBlock(x, y, z, b);
		return this;
	}
	
	public BlockBukkit setTypeWithProtection(net.minecraft.block.Block b) {
		Protection.trySetBlock(w, b, x, y, z);
		return this;
	}

	public BlockBukkit getRelative(int direction) {
		return this.getRelative(direction, 1);
	}

	public BlockBukkit getRelative(int direction, int magnitude) {
		BlockBukkit b;
		switch (direction) {
		case 0:
			b = new BlockBukkit(w, x, y + magnitude, z);
			break;
		case 1:
			b = new BlockBukkit(w, x, y - magnitude, z);
			break;
		case 2:
			b = new BlockBukkit(w, x + magnitude, y, z);
			break;
		case 3:
			b = new BlockBukkit(w, x - magnitude, y, z);
			break;
		case 4:
			b = new BlockBukkit(w, x, y, z - magnitude);
			break;
		case 5:
			b = new BlockBukkit(w, x, y, z + magnitude);
			break;
		default:
			b = new BlockBukkit(w, x, y, z);
		}
		return b;
	}

	public Location getLocation() {
		return new Location(w, x, y, z);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public boolean isLiquid() {
		return (getType() == Blocks.water) || (getType() == Blocks.lava);
	}

}
