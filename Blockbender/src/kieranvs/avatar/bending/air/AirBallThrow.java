package kieranvs.avatar.bending.air;

import kieranvs.avatar.entity.EntityAirBall;
import kieranvs.avatar.util.ProjectileLauncher;
import net.minecraft.entity.player.EntityPlayer;

public class AirBallThrow {

	public AirBallThrow(EntityPlayer player) {
		ProjectileLauncher.launchProjectile(player, new EntityAirBall(player.worldObj, player, 1));
	}

}
