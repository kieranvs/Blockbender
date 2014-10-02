package kieranvs.avatar.bending.water;

import java.util.Random;

import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.util.ProjectileLauncher;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;

public class SnowballThrow {
	
	public SnowballThrow(EntityLivingBase player, int level) {
		BlockBukkit t = BlockBukkit.getTargetBlock(player);
		Random r = new Random();
		double x = t.getX();
		double y = t.getY();
		double z = t.getZ();
		if(level == 0){
			x += r.nextGaussian() * 3D;
			y += r.nextGaussian() * 3D;
			z += r.nextGaussian() * 3D;
			ProjectileLauncher.launchProjectile(player, new WaterDamagingSnowball(player.worldObj, player), x, y, z);
		}
		if(level == 1){
			for(int i = 0; i<2; i++){
				x += r.nextGaussian() * 2D;
				y += r.nextGaussian() * 2D;
				z += r.nextGaussian() * 2D;
				ProjectileLauncher.launchProjectile(player, new WaterDamagingSnowball(player.worldObj, player), x, y, z);
			}
		}
		if(level == 2){
			for(int i = 0; i<4; i++){
				x += r.nextGaussian() * 0.5D;
				y += r.nextGaussian() * 0.5D;
				z += r.nextGaussian() * 0.5D;
				ProjectileLauncher.launchProjectile(player, new WaterDamagingSnowball(player.worldObj, player), x, y, z);
			}
		}	
	}
	
}
