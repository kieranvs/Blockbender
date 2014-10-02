package kieranvs.avatar.bending.earth;

import kieranvs.avatar.Protection;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.entity.EntityRock;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.Messaging;
import kieranvs.avatar.util.ProjectileLauncher;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class EarthRockThrow extends AsynchronousAbility {

	public EarthRockThrow(EntityLivingBase player, Long cooldown, int level) {
		super(player, cooldown);
		Location location = new Location(player);
		if(BendingUtils.isEarthBendable(location.getBlock().getRelative(BlockBukkit.DOWN).getType())){
			ProjectileLauncher.launchProjectile(player, new EntityRock(player.worldObj, player, level, true, level/2));
		}else{
			if(player instanceof EntityPlayer){
				Messaging.avatarMessage((EntityPlayer) player, "Not standing on Earth bendable block!");
			}
		}
		destroy();
	}

	@Override
	public void update() {
	}

}
