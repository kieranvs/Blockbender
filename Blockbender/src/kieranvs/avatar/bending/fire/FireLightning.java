package kieranvs.avatar.bending.fire;

import io.netty.buffer.Unpooled;

import java.util.Random;
import java.util.Vector;

import kieranvs.avatar.bending.Ability;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.util.AvatarDamageSource;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.PacketSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.server.MinecraftServer;

public class FireLightning extends AsynchronousAbility {

	private Long starttime;
	private int level;
	private int damage;
	private boolean isRaining = false;
	private static int cooldown = 20000;
	private Random rand;

	public FireLightning(EntityLivingBase user, int damage, int level) {
		super(user, cooldown + (level * 5000));
		this.user = user;
		this.level = level;
		this.damage = damage;
		this.starttime = System.currentTimeMillis();
		rand = new Random();
	}

	@Override
	public void update() {
		if (user.isSneaking()) {
			BlockBukkit t = BlockBukkit.getTargetBlock(user);
			PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
			try {
				pb.writeInt("FireLightning".getBytes().length);
				pb.writeBytes("FireLightning".getBytes());
				pb.writeDouble(t.getX());
				pb.writeDouble(t.getY());
				pb.writeDouble(t.getZ());
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			S3FPacketCustomPayload packet = new S3FPacketCustomPayload("AvatarBend", pb);
			
			MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(packet);
			return;
		}
		else {
			strike(System.currentTimeMillis() - starttime);
		}
		
		if(user.worldObj.isRaining()){
			isRaining = true;
		}
	}

	private void strike(Long charge) {
		float power;
		switch (level){
			case 0:
				if(isRaining){
					power = charge / 1700F;
					break;
				}
				power = charge / 2000F;
				break;
			case 1:
				if(isRaining){
					power = charge / 1200F;
					break;
				}
				power = charge / 1500F;
				break;
			case 2:
				if(isRaining){
					power = charge / 300F;
					break;
				}
				power = charge / 500F;
				break;
			default:
				if(isRaining){
					power = charge / 1700F;
					break;
				}
				power = charge / 2000F;
				break;
		}
		if (power > 20)
			power = 20;
		if (power < 1)
			power = 1;
		Random random = new Random();
		for (int i = 0; i < (power * 2); i++) {
			Location l = BlockBukkit.getTargetBlock(user).getLocation();
			l.setX(l.getX() + (random.nextInt() % power) - (power / 2));
			l.setZ(l.getZ() + (random.nextInt() % power) - (power / 2));
			user.worldObj.addWeatherEffect(new EntityLightningBolt(user.worldObj, l.getX(), l.getY(), l.getZ()));
			if(l.getBlock().getType() == Blocks.sand){
				l.getBlock().setTypeWithProtection(Blocks.glass);
			}
			BendingUtils.damageEntities(l, 2.5F, AvatarDamageSource.firebending, damage);
		}
		if (power > 15) {
			for (int i = 0; i < (power - 14.9); i++) {
				Location l = BlockBukkit.getTargetBlock(user).getLocation();
				l.setX(l.getX() + (random.nextInt() % power) - (power / 2));
				l.setZ(l.getZ() + (random.nextInt() % power) - (power / 2));
				user.worldObj.createExplosion(null, l.getX(), l.getY(), l.getZ(), 2, true);
			}
			for (int i = 0; i < (power - 14.9); i++) {
				Location l = BlockBukkit.getTargetBlock(user).getLocation();
				l.setX(l.getX() + (random.nextInt() % power) - (power / 2));
				l.setZ(l.getZ() + (random.nextInt() % power) - (power / 2));
				if (l.getBlock().getType() == Blocks.air) {
					boolean isAir = true;
					while (isAir) {
						l.setY(l.getY() - 1);
						if (l.getBlock().getType() != Blocks.air)
							isAir = false;
					}
				}
				l.getBlock().setTypeWithProtection(Blocks.fire);
			}
		}
		destroy();
	}

	public void damageEntities(Location loc) {
		for (Object o : loc.getWorld().getLoadedEntityList()) {
			if (o instanceof EntityLivingBase) {
				EntityLivingBase e = (EntityLivingBase) o;
				if (loc.distance(e) < 2.5) {
					e.attackEntityFrom(AvatarDamageSource.firebending, 4);
				}
			}
		}
	}
}
