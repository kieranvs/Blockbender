package kieranvs.avatar.entity;


import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityAirBall extends EntityThrowable {

	public float size;

	public EntityAirBall(World par1World) {
		super(par1World);
	}

	public EntityAirBall(World par1World, EntityLivingBase par2EntityLiving, float size) {
		super(par1World, par2EntityLiving);
		this.setSize(0.25F * size, 0.25F * size);
		this.size = size;
	}

	public EntityAirBall(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
	}

	@Override
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
		if (par1MovingObjectPosition.entityHit != null) {
			par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.generic, 8);
		}

		for (int i = 0; i < 8; i++) {
			this.worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		}
		
		for (int i = 0; i < 800; i++) {
			this.worldObj.spawnParticle("cloud", this.posX + this.rand.nextGaussian(), this.posY + (this.rand.nextGaussian() * 0.25F), this.posZ + this.rand.nextGaussian(), 0.0D, 0.0D, 0.0D);
		}

		if (!this.worldObj.isRemote) {
			this.setDead();
		}
	}

	@Override
	protected float getGravityVelocity() {
		return 0.001F;
	}
	
}
