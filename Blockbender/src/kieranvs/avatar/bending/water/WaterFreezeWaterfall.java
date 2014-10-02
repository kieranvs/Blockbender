package kieranvs.avatar.bending.water;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import kieranvs.avatar.bending.Ability;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;

import kieranvs.avatar.util.PacketSender;
import kieranvs.avatar.bending.water.WaterIceRing;

public class WaterFreezeWaterfall extends AsynchronousAbility{
	
	private Location origin;
	private Location originUp;
	private Location originDown;
	private Location originNorth;
	private Location originUpNorth;
	private Location originDownNorth;
	private long interval = 30;
	private long time;
	private int currentFreezingBlock = 0;
	private int range;
	private int damage;
	private static int cooldown = 2000;
	
	@Deprecated
	public WaterFreezeWaterfall(Location location, EntityLivingBase user, int range, int damage){
		super(user, cooldown);
		this.origin = location.clone();
		this.originNorth = location.clone();
		this.originUp = location.clone();
		this.originUp.setY(this.originUp.getY() + 1);
		this.originDown = location.clone();
		this.originDown.setY(this.originDown.getY() - 1);
		this.range = range;
		this.damage = damage;
		this.time = System.currentTimeMillis();
	}
	
	public WaterFreezeWaterfall(Location location, EntityLivingBase user, int level){
		super(user, cooldown + (level * 1000));
		this.origin = location.clone();
		this.originNorth = location.clone();
		this.originUp = location.clone();
		this.originUp.setY(this.originUp.getY() + 1);
		this.originDown = location.clone();
		this.originDown.setY(this.originDown.getY() - 1);
		if(level == 0){
			this.range = 5;
			this.damage = 1;
		}
		if(level == 1){
			
		}
		if(level == 2){
			
		}
		this.time = System.currentTimeMillis();
	}

	@Override
	public void update() {
		if (System.currentTimeMillis() > time + interval) {
	
			if(origin.getBlock().getType() == Blocks.water || origin.getBlock().getType() == Blocks.flowing_water){
				time = System.currentTimeMillis();
				origin.getBlock().setTypeWithProtection(Blocks.ice);
				PacketSender.spawnParticle("Avatar_watersplash", origin.getWorld(), origin.getX(), origin.getY(), origin.getZ());
			}
			if(originUp.getBlock().getType() == Blocks.water || origin.getBlock().getType() == Blocks.flowing_water){
				time = System.currentTimeMillis();
				originUp.getBlock().setTypeWithProtection(Blocks.ice);
				PacketSender.spawnParticle("Avatar_watersplash", originUp.getWorld(), originUp.getX(), originUp.getY(), originUp.getZ());
				originUp.setY(originUp.getY() + 1);
			}
			if(originDown.getBlock().getType() == Blocks.water || origin.getBlock().getType() == Blocks.flowing_water){
				time = System.currentTimeMillis();
				originDown.getBlock().setTypeWithProtection(Blocks.ice);
				PacketSender.spawnParticle("Avatar_watersplash", originDown.getWorld(), originDown.getX(), originDown.getY(), originDown.getZ());
				originDown.setY(originDown.getY() - 1);
			}

		}
		
		destroy();
		return;
	}		

}
