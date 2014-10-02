package kieranvs.avatar.generation;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TeleporterSpiritWorld extends Teleporter {

	public TeleporterSpiritWorld(WorldServer par1WorldServer){
		super(par1WorldServer);
	}
	
	@Override
	public void placeInPortal(Entity entity, double par2, double par4, double par6, float par8){
		entity.setLocationAndAngles(0, 80, 0, entity.rotationYaw, 0.0F);
		entity.motionX = entity.motionY = entity.motionZ = 0.0D;
	}

	@Override
	public boolean placeInExistingPortal(Entity entity, double par2, double par4, double par6, float par8){
		entity.setLocationAndAngles(0, 80, 0, entity.rotationYaw, 0.0F);
		entity.motionX = entity.motionY = entity.motionZ = 0.0D;
		return true;
	}

}

