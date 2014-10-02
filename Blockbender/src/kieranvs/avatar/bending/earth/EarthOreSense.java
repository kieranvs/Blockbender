package kieranvs.avatar.bending.earth;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;



import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.util.Messaging;


public class EarthOreSense extends AsynchronousAbility {
	
	private EntityPlayer player;
	private long time;
	private int level;
	private Random r = new Random();

	public EarthOreSense(EntityLivingBase e, int level) {
		super(e, 1000);
		if(e instanceof EntityPlayer){
			this.player = (EntityPlayer) e;
		}
		this.time = System.currentTimeMillis();
		this.level = level;
		int radius = level * 3;
		Location loc = new Location(e);
		ArrayList<BlockBukkit> loot = new ArrayList<BlockBukkit>();
		for(int i = -radius; i<=radius; i++){
			for(int j = -radius; j<=radius; j++){
				for(int k = -radius; k<=radius; k++){
					BlockBukkit b = loc.getBlock().getRelative(BlockBukkit.DOWN, i).getRelative(BlockBukkit.EAST, j).getRelative(BlockBukkit.NORTH, k);
					if(b.getType() == Blocks.diamond_ore || b.getType() == Blocks.iron_ore || b.getType() == Blocks.redstone_ore || b.getType() == Blocks.gold_ore 
							|| b.getType() == Blocks.lapis_ore || b.getType() == Blocks.emerald_ore || b.getType() == Blocks.coal_ore){
						loot.add(b);
					}
				}
			}
		}
		if(level == 1){
			Messaging.avatarMessage(this.player, "Found " + loot.size() + " ores within " + radius + " blocks.");
			destroy();
			return;
		}
		if(level == 2){
			Messaging.avatarMessage(this.player, "Found " + loot.size() + " ores within " + radius + " blocks.");
			int diamondCount = 0, goldCount = 0, ironCount = 0, redstoneCount = 0, lapisCount = 0, emeraldCount = 0, coalCount = 0;
			for(int i = 0; i < loot.size(); i++){
				if(loot.get(i).getType() == Blocks.diamond_ore) diamondCount++;
				if(loot.get(i).getType() == Blocks.gold_ore) goldCount++;
				if(loot.get(i).getType() == Blocks.iron_ore) ironCount++;
				if(loot.get(i).getType() == Blocks.redstone_ore) redstoneCount++;
				if(loot.get(i).getType() == Blocks.lapis_ore) lapisCount++;
				if(loot.get(i).getType() == Blocks.emerald_ore) emeraldCount++;
				if(loot.get(i).getType() == Blocks.coal_ore) coalCount++;
			}
			
			if(diamondCount > 0) Messaging.avatarMessage(this.player, diamondCount + " diamond ore.");
			if(goldCount > 0)Messaging.avatarMessage(this.player, goldCount + " gold ore.");
			if(ironCount > 0)Messaging.avatarMessage(this.player, ironCount + " iron ore.");
			if(redstoneCount > 0)Messaging.avatarMessage(this.player, redstoneCount + " redstone ore.");
			if(lapisCount > 0)Messaging.avatarMessage(this.player, lapisCount + " lapis lazuli ore.");
			if(emeraldCount > 0)Messaging.avatarMessage(this.player, emeraldCount + " emerald ore.");
			if(coalCount > 0)Messaging.avatarMessage(this.player, coalCount + " coal.");
			destroy();
			return;
		}
		if(level == 3){
			Messaging.avatarMessage(this.player, "Found " + loot.size() + " ores within " + radius + " blocks.");
			int diamondCount = 0, goldCount = 0, ironCount = 0, redstoneCount = 0, lapisCount = 0, emeraldCount = 0, coalCount = 0;
			double diamondDistance = 0, goldDistance = 0, ironDistance = 0, redstoneDistance = 0, lapisDistance = 0, emeraldDistance = 0, coalDistance = 0;
			Location playerLoc = new Location(this.user);
			for(int i = 0; i < loot.size(); i++){
				if(loot.get(i).getType() == Blocks.diamond_ore){
					diamondCount++;
					if(diamondDistance == 0 || diamondDistance >  playerLoc.distance(loot.get(i).getLocation())){
						diamondDistance = playerLoc.distance(loot.get(i).getLocation());
					}
				}
				if(loot.get(i).getType() == Blocks.gold_ore){
					goldCount++;
					if(goldDistance == 0 || goldDistance > playerLoc.distance(loot.get(i).getLocation())){
						goldDistance = playerLoc.distance(loot.get(i).getLocation());
					}
				}
				if(loot.get(i).getType() == Blocks.iron_ore){
					ironCount++;
					if(ironDistance == 0 || ironDistance > playerLoc.distance(loot.get(i).getLocation())){
						ironDistance = playerLoc.distance(loot.get(i).getLocation());
					}
				}
				if(loot.get(i).getType() == Blocks.redstone_ore){
					redstoneCount++;
					if(redstoneDistance == 0 || redstoneDistance > playerLoc.distance(loot.get(i).getLocation())){
						redstoneDistance = playerLoc.distance(loot.get(i).getLocation());
					}
				}
				if(loot.get(i).getType() == Blocks.lapis_ore){
					lapisCount++;
					if(lapisDistance == 0 || lapisDistance > playerLoc.distance(loot.get(i).getLocation())){
						lapisDistance = playerLoc.distance(loot.get(i).getLocation());
					}
				}
				if(loot.get(i).getType() == Blocks.emerald_ore){
					emeraldCount++;
					if(emeraldDistance == 0 || emeraldDistance > playerLoc.distance(loot.get(i).getLocation())){
						emeraldDistance = playerLoc.distance(loot.get(i).getLocation());
					}
				}
				if(loot.get(i).getType() == Blocks.coal_ore){
					coalCount++;
					if(coalDistance == 0 || coalDistance > playerLoc.distance(loot.get(i).getLocation())){
						coalDistance = playerLoc.distance(loot.get(i).getLocation());
					}
				}
			}
			
			if(diamondCount > 0) Messaging.avatarMessage(this.player, diamondCount + " diamond ore. You sense one " + Math.round(diamondDistance * 100D)/100D + " blocks away.");
			if(goldCount > 0)Messaging.avatarMessage(this.player, goldCount + " gold ore. You sense one " + Math.round(goldDistance * 100D)/100D + " blocks away.");
			if(ironCount > 0)Messaging.avatarMessage(this.player, ironCount + " iron ore. You sense one " + Math.round(ironDistance * 100D)/100D + " blocks away.");
			if(redstoneCount > 0)Messaging.avatarMessage(this.player, redstoneCount + " redstone ore. You sense one " + Math.round(redstoneDistance * 100D)/100D + " blocks away.");
			if(lapisCount > 0)Messaging.avatarMessage(this.player, lapisCount + " lapis lazuli ore. You sense one " + Math.round(lapisDistance * 100D)/100D + " blocks away.");
			if(emeraldCount > 0)Messaging.avatarMessage(this.player, emeraldCount + " emerald ore. You sense one " + Math.round(emeraldDistance * 100D)/100D + " blocks away.");
			if(coalCount > 0)Messaging.avatarMessage(this.player, coalCount + " coal. You sense one " + Math.round(coalDistance * 100D)/100D + " blocks away.");
			destroy();
			return;
		}
		
	}

	@Override
	public void update() {
		if(this.player == null){
			destroy();
			return;
		}
	}

}
