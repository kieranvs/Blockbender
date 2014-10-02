package kieranvs.avatar.bending.water;

import java.util.ArrayList;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.AvatarDamageSource;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.Messaging;
import kieranvs.avatar.util.PacketSender;

public class WaterOctopusForm extends AsynchronousAbility {
	
	private ArrayList<BlockBukkit> theBlocks;
	private double delta = 0;
	private float height = 0;

	public WaterOctopusForm(EntityLivingBase e, long cooldowntime) {
		super(e, cooldowntime);
		theBlocks = new ArrayList<BlockBukkit>();
		if(!e.isSneaking()){
			Messaging.avatarMessage((EntityPlayer) e, "Press shift while you use the move to use octopus form.");
		}
	}

	@Override
	public void update() {
		Location userLoc = new Location(this.user);

		for(int i = -2; i < 3; i++){
			for(int j = -2; j < 3; j++){
				if(userLoc.getBlock().getRelative(BlockBukkit.DOWN).getRelative(BlockBukkit.EAST, i).getRelative(BlockBukkit.NORTH, j).getType() == Blocks.water || userLoc.getBlock().getRelative(BlockBukkit.DOWN).getRelative(BlockBukkit.EAST, i).getRelative(BlockBukkit.NORTH, j).getType() == Blocks.flowing_water){
					userLoc.getBlock().getRelative(BlockBukkit.DOWN).getRelative(BlockBukkit.EAST, i).getRelative(BlockBukkit.NORTH, j).setTypeWithProtection(Blocks.ice);
				}
			}
		}
		
		for(BlockBukkit b : theBlocks){
			if(b.getType() == Blocks.water || b.getType() == Blocks.flowing_water) b.setTypeWithProtection(Blocks.air);
			if(b.getRelative(BlockBukkit.DOWN).getType() == Blocks.flowing_water) b.getRelative(BlockBukkit.DOWN).setTypeWithProtection(Blocks.air);
			if(b.getRelative(BlockBukkit.NORTH).getType() == Blocks.flowing_water) b.getRelative(BlockBukkit.NORTH).setTypeWithProtection(Blocks.air);
			if(b.getRelative(BlockBukkit.EAST).getType() == Blocks.flowing_water) b.getRelative(BlockBukkit.EAST).setTypeWithProtection(Blocks.air);
			if(b.getRelative(BlockBukkit.SOUTH).getType() == Blocks.flowing_water) b.getRelative(BlockBukkit.SOUTH).setTypeWithProtection(Blocks.air);
			if(b.getRelative(BlockBukkit.WEST).getType() == Blocks.flowing_water) b.getRelative(BlockBukkit.WEST).setTypeWithProtection(Blocks.air);
		}
		theBlocks.clear();

		if(!this.user.isSneaking()){
			if(height > 0){
				height -= 0.07f;
			}
			else{
				destroy();
				return;
			}
		}
		
		delta += 1;
		height = Math.min(3, height + 0.02f);
		double radius = 5;
		for(double a = 0; a < 0 + 360D; a += 60D){
			double theta = Math.toRadians(a + delta);
			branch(new Location(this.user), new Vector(radius * Math.cos(theta), height, radius * Math.sin(theta)));
		}
		
		BendingUtils.damageEntities(this.user, userLoc, (float) radius, AvatarDamageSource.waterbending, 2);
		BendingUtils.repelEntitiesOnYourLevelAndAbove(this.user, userLoc, (float) radius);
	}
	
	private void branch(Location l, Vector v){
		v.normalize();
		l.add(v);
		l.add(v);
		
		for(int i = 0; i < 5; i++){
			l.add(v);
			affectBlock(l.getBlock());
		}
	}
	
	private void affectBlock(BlockBukkit b){
		if(BendingUtils.isOverwriteable(b.getType())){
			b.setTypeWithProtection(Blocks.water);
			if(!theBlocks.contains(b)){
				theBlocks.add(b);
			}
		}
	}


}
