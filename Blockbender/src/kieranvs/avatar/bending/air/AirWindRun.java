package kieranvs.avatar.bending.air;

import java.util.Vector;

import kieranvs.avatar.bending.Ability;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.client.SoundHandler;
import kieranvs.avatar.util.PacketSender;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;

public class AirWindRun extends AsynchronousAbility {

	private long starttime;
	private long soundStartTime;
	private int totaltime;
	private long prevtime = 0L;
	private long interval = 500;
	private int level;
	private long soundTime = 1371;
	
	public AirWindRun(EntityLivingBase user, int time, int power, int level) {
		super(user, time + (2000L + level * 3000));
		totaltime = time;
		starttime = System.currentTimeMillis();
		this.user.addPotionEffect(new PotionEffect(1, 20 * totaltime, power));
		this.level = level;
	}

	@Override
	public void update() {
		if (System.currentTimeMillis() - starttime > totaltime * 1000) {
			this.destroy();
			return;
		}
		if (System.currentTimeMillis() - prevtime > interval) {
			PacketSender.spawnParticle("Avatar_cloud", this.user.worldObj, this.user.posX, this.user.posY, this.user.posZ, 10, 0.1);
		}
	}

}
