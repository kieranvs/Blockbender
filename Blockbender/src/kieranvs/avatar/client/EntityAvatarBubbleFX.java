package kieranvs.avatar.client;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityAvatarBubbleFX extends EntityFX {

	public EntityAvatarBubbleFX(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
		super(world, posX, posY, posZ, motionX, motionY, motionZ);
		this.particleRed = 1.0F;
		this.particleGreen = 1.0F;
		this.particleBlue = 1.0F;
		this.setParticleTextureIndex(32);
		this.setSize(0.02F, 0.02F);
		this.particleScale *= this.rand.nextFloat() * 0.6F + 0.2F;
		this.motionX = motionX * 0.20000000298023224D + (float) (Math.random() * 2.0D - 1.0D) * 0.02F;
		this.motionY = motionY * 0.20000000298023224D + (float) (Math.random() * 2.0D - 1.0D) * 0.02F;
		this.motionZ = motionZ * 0.20000000298023224D + (float) (Math.random() * 2.0D - 1.0D) * 0.02F;
		this.particleMaxAge = (int) (8.0D / (Math.random() * 0.8D + 0.2D));
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.motionY += 0.002D;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.8500000238418579D;
		this.motionY *= 0.8500000238418579D;
		this.motionZ *= 0.8500000238418579D;

		if (this.particleMaxAge-- <= 0) {
			this.setDead();
		}
	}
}
