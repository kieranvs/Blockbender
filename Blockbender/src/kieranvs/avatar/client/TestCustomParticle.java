package kieranvs.avatar.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityAuraFX;
import net.minecraft.client.particle.EntityEnchantmentTableParticleFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityNoteFX;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.world.World;

public class TestCustomParticle{
	
	private static Minecraft mc = Minecraft.getMinecraft();
	private static World theWorld = mc.theWorld;

	public static EntityFX spawnParticle(String particleName, double posX, double posY, double posZ, double par8, double par10, double par12){
		
		if (mc != null && mc.renderViewEntity != null && mc.effectRenderer != null){
			
			int particleSetting = mc.gameSettings.particleSetting;

			if (particleSetting == 1 && theWorld.rand.nextInt(3) == 0){
				particleSetting = 2;
			}

			double x = mc.renderViewEntity.posX - posX;
			double y = mc.renderViewEntity.posY - posY;
			double z = mc.renderViewEntity.posZ - posZ;
			EntityFX particle = null;
			double var22 = 16.0D; //density?

			if (x * x + y * y + z * z > var22 * var22){
				return null;
			}
			else if (particleSetting > 1){
				return null;
			}
			else{
				if (particleName.equals("testCustomParticle")){
					particle = new EntitySpellParticleFX(theWorld, posX, posY, posZ, (float)par8, (float)par10, (float)par12);
				}


				mc.effectRenderer.addEffect((EntityFX)particle);
				return (EntityFX)particle;
			}
		}
		return null;
	}
}