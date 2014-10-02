package kieranvs.avatar;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.entity.Entity;
import kieranvs.avatar.bending.Ability;
import kieranvs.avatar.bending.air.AirTornado;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.client.MovingSoundEffect;
import kieranvs.avatar.util.PacketSender;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BendingPacketHandler {

	private Random rand = new Random();
	private Entity entity;
	private MovingSoundEffect soundEffect;

	@SideOnly(Side.CLIENT)
	public void processPacket(FMLProxyPacket packet){
		int l = packet.payload().readInt(); 
		byte[] name = new byte[l];
		packet.payload().readBytes(name);
		String type = new String(name);

		if(type.equals("ContinueSound")){
			int soundNameLength = packet.payload().readInt(); 
			byte[] soundName = new byte[soundNameLength];
			packet.payload().readBytes(soundName);
			String sound = new String(soundName);
			int entIdLength = packet.payload().readInt();
			byte[] entId = new byte[entIdLength];
			packet.payload().readBytes(entId);
			String entityId = new String(entId);
			boolean play = packet.payload().readBoolean();
			
			if(entity == null){
				for(Object o : Minecraft.getMinecraft().theWorld.loadedEntityList){
					Entity e = (Entity) o;
					if(e.getUniqueID().toString().equals(entityId)){
						entity = e;
					}
				}
			}
			soundEffect = new MovingSoundEffect(entity, sound);
			if(!play){
				Minecraft.getMinecraft().getSoundHandler().stopSound(soundEffect);
				Minecraft.getMinecraft().getSoundHandler().stopSounds();
			} else {
				Minecraft.getMinecraft().getSoundHandler().playSound(soundEffect);
			}
		}
		else if(type.equals("FireLightning")){
			double x = packet.payload().readDouble();
			double y = packet.payload().readDouble();
			double z = packet.payload().readDouble();
			for(int i = 0; i < 25; i++){
				double xr = rand.nextGaussian() * 2;
				double yr = rand.nextGaussian() * 2;
				double zr = rand.nextGaussian() * 2;
				Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySmokeFX(Minecraft.getMinecraft().theWorld, x + xr, y + yr, z + zr, 0, 0, 0));
			}
		}
		else if(type.equals("AirTornado")){
			int entlen = packet.payload().readInt();
			byte[] entname = new byte[entlen];
			packet.payload().readBytes(entname);
			String entnamestr = new String(entname);
			double lx = packet.payload().readDouble();
			double ly = packet.payload().readDouble();
			double lz = packet.payload().readDouble();
			double dx = packet.payload().readDouble();
			double dy = packet.payload().readDouble();
			double dz = packet.payload().readDouble();
			int height = packet.payload().readInt();
			int radius = packet.payload().readInt();
			float speed = packet.payload().readFloat();
			float slant = packet.payload().readFloat();
			long lifetime = packet.payload().readLong();
			int animspeed = packet.payload().readInt();
			long cooldown = packet.payload().readLong();
			Ability.clientInstances.add(new AirTornado(null, cooldown, new Location(Minecraft.getMinecraft().theWorld, lx, ly, lz), new Vector(dx, dy, dz), height, radius, speed, slant, lifetime, animspeed));
		}
	}

}
