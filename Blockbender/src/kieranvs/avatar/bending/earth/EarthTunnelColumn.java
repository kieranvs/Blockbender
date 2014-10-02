package kieranvs.avatar.bending.earth;

import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;

import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.Messaging;
import kieranvs.avatar.util.PacketSender;

public class EarthTunnelColumn extends AsynchronousAbility{

	private int depth;
	private Location origin, current;
	private Vector direction;
	private Long lasttime;
	private boolean hasNagged = false;
	private int chance;
	private Random r;
	
	public EarthTunnelColumn(EntityLivingBase e, long cooldowntime, Vector direction, int depth, int chance) {
		super(e, cooldowntime);
		this.depth = depth;
		this.origin = new Location(e);
		this.current = origin.clone();
		this.direction = direction.clone().normalize();
		this.lasttime = System.currentTimeMillis();
		this.chance = chance;
		r = new Random();
	}

	@Override
	public void update() {
		if(System.currentTimeMillis() > lasttime + 100L){
			lasttime = System.currentTimeMillis();
			this.current.add(this.direction);
			if(current.distance(origin) > depth){
				destroy();
				return;
			}
			if(BendingUtils.isEarthBendable(this.current.getBlock().getType()) || this.current.getBlock().getType() == Blocks.air){
				hasNagged = true;
				dropWithChance(current, chance);
				this.current.getBlock().setTypeWithProtection(Blocks.air);
				PacketSender.spawnParticle("Avatar_dig", user.worldObj, current.getX(), current.getY(), current.getZ(), 50, 0.5);
				
				if(BendingUtils.isEarthBendable(this.current.getBlock().getRelative(BlockBukkit.UP).getType())){
					dropWithChance(current.getBlock().getRelative(BlockBukkit.UP).getLocation(), chance);
					this.current.getBlock().getRelative(BlockBukkit.UP).setTypeWithProtection(Blocks.air);
				}
				if(BendingUtils.isEarthBendable(this.current.getBlock().getRelative(BlockBukkit.DOWN).getType())){
					dropWithChance(current.getBlock().getRelative(BlockBukkit.DOWN).getLocation(), chance);
					this.current.getBlock().getRelative(BlockBukkit.DOWN).setTypeWithProtection(Blocks.air);
				}
				if(BendingUtils.isEarthBendable(this.current.getBlock().getRelative(BlockBukkit.NORTH).getType())){
					dropWithChance(current.getBlock().getRelative(BlockBukkit.NORTH).getLocation(), chance);
					this.current.getBlock().getRelative(BlockBukkit.NORTH).setTypeWithProtection(Blocks.air);
				}
				if(BendingUtils.isEarthBendable(this.current.getBlock().getRelative(BlockBukkit.EAST).getType())){
					dropWithChance(current.getBlock().getRelative(BlockBukkit.EAST).getLocation(), chance);
					this.current.getBlock().getRelative(BlockBukkit.EAST).setTypeWithProtection(Blocks.air);
				}
				if(BendingUtils.isEarthBendable(this.current.getBlock().getRelative(BlockBukkit.SOUTH).getType())){
					dropWithChance(current.getBlock().getRelative(BlockBukkit.SOUTH).getLocation(), chance);
					this.current.getBlock().getRelative(BlockBukkit.SOUTH).setTypeWithProtection(Blocks.air);
				}
				if(BendingUtils.isEarthBendable(this.current.getBlock().getRelative(BlockBukkit.WEST).getType())){
					dropWithChance(current.getBlock().getRelative(BlockBukkit.WEST).getLocation(), chance);
					this.current.getBlock().getRelative(BlockBukkit.WEST).setTypeWithProtection(Blocks.air);
				}
			}else{
				if(!hasNagged){
					Messaging.avatarMessage((EntityPlayer) this.user, "Can only dig Earth bendable blocks!");
					hasNagged = true;
				}
				destroy();
				return;

			}
		}
	}
	
	public final void dropAsItem(Location location){
	     location.getBlock().getType().dropBlockAsItem(user.worldObj, location.getBlock().getX(), location.getBlock().getY(),location.getBlock().getZ(), 0, 0);      
	}

	public void dropWithChance(Location location, int c){
		if(c == -1){
			return;
		}
		if(r.nextInt(c) == 0){
			dropAsItem(location);
		}
	}
	
}
