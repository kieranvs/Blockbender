package kieranvs.avatar.bending.fire;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bending.water.WaterIceBridge;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;

import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.BlockActionPerformer;
import kieranvs.avatar.util.GenericProgressor;
import kieranvs.avatar.util.PacketSender;

public class FireMelt extends AsynchronousAbility {

	private int range;
	private static int cooldown = 2000;
	
	public FireMelt(EntityLivingBase entity, int level){
		this(entity, level, true);
	}
	
	public FireMelt(EntityLivingBase entity, int level, final boolean meltObsidian) {
		super(entity, cooldown + (level * 2000));
		if(level == 0){
			range = 3;
		}
		if(level == 1){
			range = 6;
		}
		if(level == 2){
			range = 12;
		}
		Location location = BlockBukkit.getTargetBlock(entity).getLocation();
		Location origin = location.clone();
		Vector direction = new Location(entity).getDirection();
		
		for (int i = -180; i <= 180; i += 10) {
			double angle = Math.toRadians(i);

			double x = direction.getX();
			double y = direction.getY();
			double z = direction.getZ();

			double vx = x * Math.cos(angle) - z * Math.sin(angle);
			double vz = x * Math.sin(angle) + z * Math.cos(angle);

			direction.setX(vx);
			direction.setZ(vz);
			
			new GenericProgressor(entity, location, direction, range, true, new BlockActionPerformer(){
				@Override
				public void performAction(BlockBukkit b) {
					if(b.getType() == Blocks.ice || b.getType() == Blocks.packed_ice || b.getType() == Blocks.snow){
						b.setTypeWithProtection(Blocks.water);
						PacketSender.spawnParticle("Avatar_cloud", b.getLocation().getWorld(), b.getX(), b.getY() + 1, b.getZ(), 10, 0.5);
					}
					if(b.getType() == Blocks.snow_layer){
						b.setTypeWithProtection(Blocks.air);
						PacketSender.spawnParticle("Avatar_cloud", b.getLocation().getWorld(), b.getX(), b.getY() + 1, b.getZ(), 10, 0.5);
					}
					if(b.getType() == Blocks.obsidian && meltObsidian == true){
						b.setTypeWithProtection(Blocks.lava);
						PacketSender.spawnParticle("Avatar_smoke", b.getLocation().getWorld(), b.getX(), b.getY() + 1, b.getZ(), 10, 0.5);
					}
				}
			});
		}
		
		destroy();

	}

	@Override
	public void update() {		
	}

}
