package kieranvs.avatar.bending.fire;

import java.util.Random;
import java.util.Vector;

import kieranvs.avatar.Protection;
import kieranvs.avatar.bending.Ability;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.util.PacketSender;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;

public class FireExplosion extends AsynchronousAbility {

	private Long starttime;
	private float chargeSpeed, maxPower, minPower;
	private static int cooldown = 15000;
	private Random rand;
	private int range;

	public FireExplosion(EntityLivingBase user, int level) {
		super(user, cooldown + (level * 3000));
		this.user = user;
		this.starttime = System.currentTimeMillis();
		if(level == 0){
			chargeSpeed = 1500F;
			maxPower = 20;
			minPower = 2;
			this.range = 10;
		}
		if(level == 1){
			chargeSpeed = 1000F;
			maxPower = 30;
			minPower = 4;
			this.range = 20;
		}
		if(level == 2){
			chargeSpeed = 500F;
			maxPower = 40;
			minPower = 6;
			range = 40;
		}
		rand = new Random();
	}

	@Override
	public void update() {
		if (user.isSneaking()) {
			BlockBukkit t = BlockBukkit.getTargetBlock(user);
			if(t.getLocation().distance(user) > range){
				destroy();
				return;
			}
			PacketSender.spawnParticle("smoke", user.worldObj, t.getX(), t.getY(), t.getZ(), 100, 40);
			return;
		}
		else {
			strike(System.currentTimeMillis() - starttime);
		}
	}

	private void strike(long charge) {
		float power = (float) charge / chargeSpeed;
		if (power > maxPower)
			power = maxPower;
		if (power < minPower)
			power = minPower;
		BlockBukkit t = BlockBukkit.getTargetBlock(user);
		if(t.getLocation().distance(user) > range){
			destroy();
			return;
		}
		user.worldObj.createExplosion(null, t.getX(), t.getY(), t.getZ(), power, true);
		if(power > 32){
			for(int i = -10; i < 11; i++){
				for(int j = -20; j < 0; j++){
					for(int k = -10; k < 11; k++){
						Block blockType = user.worldObj.getBlock(t.getX() + i, t.getY() + j, t.getZ() + k);
						Block above = user.worldObj.getBlock(t.getX() + i, t.getY() + j + 1, t.getZ() + k);
						Block below = user.worldObj.getBlock(t.getX() + i, t.getY() + j - 1, t.getZ() + k);
						if(blockType != Blocks.coal_block && above == Blocks.air && below != Blocks.air && below != Blocks.coal_block){
							if(rand.nextInt(5) == 0){
								Protection.trySetBlock(user.worldObj, Blocks.coal_block, t.getX() + i, t.getY() + j, t.getZ() + k);
								if(rand.nextInt(7) < 2){
									Protection.trySetBlock(user.worldObj, Blocks.fire, t.getX() + i, t.getY() + j + 1, t.getZ() + k);
								}
							}
						}
					}
				}
			}			
		}
		destroy();
	}

}
