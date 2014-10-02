package kieranvs.avatar.entity;


import kieranvs.avatar.Protection;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityRock extends EntityThrowable {

	private boolean explode;
	public float size;
	private float explodePower;

	public EntityRock(World par1World) {
		super(par1World);
	}

	public EntityRock(World par1World, EntityLivingBase par2EntityLiving, float size, boolean explode, float explodePower) {
		super(par1World, par2EntityLiving);
		this.setSize(0.5F * size, 0.5F * size);
		this.size = size;
		this.explode = explode;
		this.explodePower = explodePower;
	}

	public EntityRock(World par1World, double par2, double par4, double par6) {
		super(par1World, par2, par4, par6);
	}

	@Override
	protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
		if (par1MovingObjectPosition.entityHit != null) {
			par1MovingObjectPosition.entityHit.attackEntityFrom(DamageSource.generic, 8);
		}

		for (int i = 0; i < 8; i++) {
			this.worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			if (explode){
				if(Protection.canBlockChangeHere(this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ)){
					this.worldObj.createExplosion(null, this.posX, this.posY, this.posZ, explodePower, true);					
				}
			}
		}

		if (!this.worldObj.isRemote) {
			this.setDead();
		}
	}

}
