package kieranvs.avatar.util;

import kieranvs.avatar.bukkit.BlockBukkit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;

public class ProjectileLauncher {

	public static void launchProjectile(EntityLivingBase shooter, EntityThrowable proj) {
		BlockBukkit b = BlockBukkit.getTargetBlock(shooter);
		launchProjectile(shooter, proj, b.getX(), b.getY(), b.getZ());
	}

	public static void launchProjectile(Entity shooter, EntityThrowable proj, Entity target) {
		launchProjectile(shooter, proj, target.posX, target.posY, target.posZ);
	}

	public static void launchProjectile(Entity entity, EntityThrowable proj, double targetX, double targetY, double targetZ) {
		double dx = targetX - entity.posX;
		double dy = targetY - entity.posY;
		double dz = targetZ - entity.posZ;
		proj.setThrowableHeading(dx, dy, dz, 1.6F, 0F);
		entity.worldObj.spawnEntityInWorld(proj);
	}

}
