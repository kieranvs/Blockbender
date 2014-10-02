package kieranvs.avatar.bending.earth;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import kieranvs.avatar.Protection;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.entity.EntityBlock;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.Messaging;

public class EarthRaiseBlock extends AsynchronousAbility {

	private Location location;
	private EntityLivingBase user;
	private long time;
	private long interval = 50L;
	private static int cooldown = 500;
	private int counter = 0;
	private int range = 5;
	private EntityBlock blockEntity;
	private Block blockType;
	private int metadata;

	public EarthRaiseBlock(EntityLivingBase user, Location location, int level) {
		super(user, cooldown + (level * 1500));
		this.time = System.currentTimeMillis();
		this.user = user;
		this.location = location;
		switch(level){
		case 0:
			range = 3;
			break;
		case 1:
			range = 6;
			break;
		case 2:
			range = 12;
			break;
		}
		if(!BendingUtils.isEarthBendable(location.getBlock().getType()) || location.getBlock().getType() == Blocks.air){
			Messaging.avatarMessage((EntityPlayer) user, "Not an Earth bendable block!");
			destroy();
			return;
		}
		Block m = location.getBlock().getType();
		if(BendingUtils.isEarthBendable(m)){
			blockType = Blocks.stone;
		}
		if(m == Blocks.dirt || m == Blocks.grass){
			blockType = Blocks.dirt;
		}
		if(m == Blocks.sand || m == Blocks.sandstone){
			blockType = Blocks.sandstone;
		}
		if(m == Blocks.stained_hardened_clay || m == Blocks.hardened_clay || m == Blocks.clay || m == Blocks.gravel){
			blockType = m;
			metadata = location.getWorld().getBlockMetadata(location.getBlockX(), location.getBlockY(), location.getBlockZ());
		}

		blockEntity = new EntityBlock(user.worldObj, location.getBlockX() + 0.5D, location.getBlockY() + 0.5D, location.getBlockZ() + 0.5D, blockType);		
		user.worldObj.spawnEntityInWorld(blockEntity);
		blockEntity.setOwner(user);
		Protection.trySetBlock(user.worldObj, Blocks.air, location.getBlock().getX(), location.getBlock().getY(), 
				location.getBlock().getZ());
	}

	@Override
	public void update() {
		if(!(blockEntity == null)){
			if(user.isSneaking()){
				Location l = new Location(user.worldObj, user.posX, user.posY, user.posZ);
				Location userL = new Location(user);
				Vector d = userL.getDirection();
				d.normalize();
				d.multiply(3.5);
				l.add(d);
				
				blockEntity.setPosition(l.getX(), l.getY(), l.getZ());
			}else{
				blockEntity.setDead();
				destroy();
			}
		}else{
			destroy();
			return;
		}

	}

}
