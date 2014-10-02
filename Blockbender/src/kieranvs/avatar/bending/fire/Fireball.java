package kieranvs.avatar.bending.fire;

import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.entity.EntityAvatarFireball;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityFireball;

public class Fireball extends AsynchronousAbility {

	private static int cooldown = 6000;
	
	public Fireball(EntityLivingBase entity, int level) {
		super(entity, cooldown + (level * 2000));
		BlockBukkit b = BlockBukkit.getTargetBlock(entity);
		EntityFireball f = new EntityAvatarFireball(entity.worldObj, entity, entity.getLookVec().xCoord, entity.getLookVec().yCoord, entity.getLookVec().zCoord, level + 1);
		entity.worldObj.spawnEntityInWorld(f);
		destroy();
	}

	@Override
	public void update() {
	}

}
