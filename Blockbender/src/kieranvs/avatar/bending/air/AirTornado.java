package kieranvs.avatar.bending.air;

import io.netty.buffer.Unpooled;

import java.util.Random;

import kieranvs.avatar.bending.AbilityTCFix;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.client.EntityAvatarBubbleFX;
import kieranvs.avatar.util.AvatarDamageSource;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.EntityActionPerformer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityCloudFX;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.particle.EntitySplashFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class AirTornado extends AsynchronousAbility implements AbilityTCFix {
	
	private long starttime;
	private long lifetime;
	private int radius = 3;
	private int height;
	private Vector direction;
	private Location[] locations;
	private Random r = new Random();
	private int twistanim = 0;
	private int animspeed;

	public AirTornado(EntityLivingBase entity, Long cooldown, Location location, Vector direction, int height, int radius, float speed, float slant, long lifetime, int animspeed) {
		super(entity, cooldown + lifetime);
		this.height = height;
		this.radius = radius;
		this.lifetime = lifetime;
		this.animspeed = animspeed;
		this.direction = direction.clone().normalize().multiply(speed);
		this.direction.setY(0);
		this.locations = new Location[height];
		for(int i = 0; i < height; i++){
			this.locations[i] = location.clone();
			float laidbacknessforthislayer = (float)((float)i/(float)height) * slant;
			this.locations[i].subtract(direction.clone().multiply(laidbacknessforthislayer));
		}
		this.starttime = System.currentTimeMillis();
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER){
			PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
			try {
				pb.writeInt("AirTornado".getBytes().length);
				pb.writeBytes("AirTornado".getBytes());
				pb.writeInt(entity.getUniqueID().toString().length());
				pb.writeBytes(entity.getUniqueID().toString().getBytes());
				pb.writeDouble(location.getX());
				pb.writeDouble(location.getY());
				pb.writeDouble(location.getZ());
				pb.writeDouble(direction.getX());
				pb.writeDouble(direction.getY());
				pb.writeDouble(direction.getZ());
				pb.writeInt(height);
				pb.writeInt(radius);
				pb.writeFloat(speed);
				pb.writeFloat(slant);
				pb.writeLong(lifetime);
				pb.writeInt(animspeed);
				pb.writeLong(cooldown + lifetime);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			S3FPacketCustomPayload packet = new S3FPacketCustomPayload("AvatarBend", pb);
			
			MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(packet);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateClientSide(){
		if(System.currentTimeMillis() > starttime + lifetime){
			destroy();
			return;
		}
		updateLocations(direction);
		for(int i = 0; i < height; i += 1D){
			Location t = locations[i].clone();
			t.setY(locations[i].getY() + (i/10F));
			float thisradius = (float) (radius + Math.sin(Math.toRadians(i - 90))) + (i/height) * 2F; 
			
			if(i % animspeed == 0) twistanim++;
			float modradius = (float) ((float)i/(float)height) * 4F;
			double modx = modradius * Math.sin(Math.toRadians(i + twistanim));
			double modz = modradius * Math.cos(Math.toRadians(i + twistanim));
			t.setX(locations[i].getX() + modx);
			t.setZ(locations[i].getZ() + modz);
			doTheThing(t.getX(), t.getY(), t.getZ(), thisradius, 6, 15, 0);
			
			BendingUtils.damageAllEntitiesWithCustomAction(null, t, thisradius * 2, AvatarDamageSource.airbending, 0, new EntityActionPerformer(){
				@Override
				public void performAction(Entity e) {
					Random r = new Random();
					if(r.nextInt(3) == 0){
						e.setVelocity(0, 0.4D, 0);
					}

					if(r.nextInt(50) == 1){
						e.setVelocity(0.5D, 0, 0);
						if(r.nextInt(50) == 2){
							e.setVelocity(0, 0, 0.5D);
						}
					}
					if(r.nextInt(50) == 2){
						e.setVelocity(0, 0, 0.5D);
						if(r.nextInt(50) == 3){
							e.setVelocity(0, 0, 0.5D);
						}
					}
				}
			});
		}		
	}

	@Override
	public void update() {
		if(System.currentTimeMillis() > starttime + lifetime){
			destroy();
			return;
		}
	}
	
	@SideOnly(Side.CLIENT)
	private void doTheThing(double x, double y, double z, double radius, int xzquality, int rarity, int element){
		Random r = new Random();
		for(int t = 0; t < 360; t++){
			if((t % xzquality == 0) && (r.nextInt(rarity) == 0)){
				double spawnX = x + (radius * Math.cos(Math.toRadians(t)));
				double spawnY = y;
				double spawnZ = z + (radius * Math.sin(Math.toRadians(t)));
				if(element == 0){
					if(r.nextInt(2) == 0){
						Minecraft.getMinecraft().effectRenderer.addEffect(new EntityCloudFX(Minecraft.getMinecraft().theWorld, spawnX, spawnY, spawnZ, 0, 0, 0));
					}
					else{
						Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySmokeFX(Minecraft.getMinecraft().theWorld, spawnX, spawnY, spawnZ, 0, 0, 0));
					}
				}
				if(element == 1){
					if(r.nextInt(7) == 0){
						Minecraft.getMinecraft().effectRenderer.addEffect(new EntityAvatarBubbleFX(Minecraft.getMinecraft().theWorld, spawnX, spawnY, spawnZ, 0, 0, 0));										
					}
					else{
						Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySplashFX(Minecraft.getMinecraft().theWorld, spawnX, spawnY, spawnZ, 0, 0, 0));										
					}
				}
				if(element == 2) Minecraft.getMinecraft().effectRenderer.addEffect(new EntityFlameFX(Minecraft.getMinecraft().theWorld, spawnX, spawnY, spawnZ, 0, 0, 0));
			}
		}
	}
	
	public void updateLocations(Vector direction){
	}

}
