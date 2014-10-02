package kieranvs.avatar.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.relauncher.Side;
import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class PacketSender {

	public static void spawnParticle(String particle, World w, double x, double y, double z) {
		spawnParticle(particle, w, x, y, z, 10, 1);
	}

	public static void spawnParticle(String particle, World w, double x, double y, double z, int total, double size) {

		PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
		try {
			pb.writeInt(particle.getBytes().length);
			pb.writeBytes(particle.getBytes());
			pb.writeDouble(x);
			pb.writeDouble(y);
			pb.writeDouble(z);
			pb.writeInt(total);
			pb.writeDouble(size);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		S3FPacketCustomPayload packet = new S3FPacketCustomPayload("AvatarPar", pb);
		
		MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(packet);

	}
	/**
	 * 
	 * @param location - where the shield will spawn around
	 * @param height - of the shield
	 * @param radius - of the shield
	 * @param yquality - Y axis quality, don't mess with please. Higher is better quality.
	 * @param xzquality - XZ axis quality, feel free to experiment. It is the number of degrees between particle spawns in the circle. Lower is better quality. 6 is a good value.
	 * @param chance - The chance of particles spawning, feel free to experiment. eg 10 is one tenth. Lower is better quality. 10 is a good value.
	 * @param element - Element of the shield (water, air or fire)
	 */
	public static void sendShield(Location location, int height, int radius, int yquality, int xzquality, int chance, int element){
		PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
		try {
			pb.writeInt("Avatar_shield".getBytes().length);
			pb.writeBytes("Avatar_shield".getBytes());
			pb.writeDouble(location.getX());
			pb.writeDouble(location.getY());
			pb.writeDouble(location.getZ());
			pb.writeInt(0);
			pb.writeDouble(0);
			pb.writeInt(height);
			pb.writeInt(radius);
			pb.writeInt(yquality); 
			pb.writeInt(xzquality); 
			pb.writeInt(chance); 
			pb.writeInt(element);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		S3FPacketCustomPayload packet = new S3FPacketCustomPayload("AvatarPar", pb);

		MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(packet);	
	}
	
	public static void sendCircle(Location location, double radius, int xzquality, int chance, int element){
		PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
		try {
			pb.writeInt("Avatar_circle".getBytes().length);
			pb.writeBytes("Avatar_circle".getBytes());
			pb.writeDouble(location.getX());
			pb.writeDouble(location.getY());
			pb.writeDouble(location.getZ());
			pb.writeInt(0);
			pb.writeDouble(0);
			pb.writeDouble(radius);
			pb.writeInt(xzquality); //XZ axis quality, feel free to experiment. It is the number of degrees between particle spawns in the circle. Lower is better quality. 6 is a good value.
			pb.writeInt(chance); //The chance of particles spawning, feel free to experiment. eg 10 is one tenth. Lower is better quality. 10 is a good value.
			pb.writeInt(element); //Element
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		S3FPacketCustomPayload packet = new S3FPacketCustomPayload("AvatarPar", pb);

		MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(packet);	
	}
	
	public static void sendDisc(Location location, int radius, int xzquality, int chance, int element){
		PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
		try {
			pb.writeInt("Avatar_disc".getBytes().length);
			pb.writeBytes("Avatar_disc".getBytes());
			pb.writeDouble(location.getX());
			pb.writeDouble(location.getY());
			pb.writeDouble(location.getZ());
			pb.writeInt(0);
			pb.writeDouble(0);
			pb.writeInt(radius);
			pb.writeInt(xzquality); //XZ axis quality, feel free to experiment. It is the number of degrees between particle spawns in the circle. Lower is better quality. 6 is a good value.
			pb.writeInt(chance); //The chance of particles spawning, feel free to experiment. eg 10 is one tenth. Lower is better quality. 10 is a good value.
			pb.writeInt(element); //Element
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		S3FPacketCustomPayload packet = new S3FPacketCustomPayload("AvatarPar", pb);

		MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(packet);	
	}

	public static void sendVelocity(Entity entity, Vector velocity) {
		sendVelocity(entity, velocity.getX(), velocity.getY(), velocity.getZ());
	}

	public static void sendVelocity(Entity entity, double x, double y, double z) {

		PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
		try {
			pb.writeInt("velocity".getBytes().length);
			pb.writeBytes("velocity".getBytes());
			pb.writeInt(entity.getEntityId());
			pb.writeDouble(x);
			pb.writeDouble(y);
			pb.writeDouble(z);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		S3FPacketCustomPayload packet = new S3FPacketCustomPayload("AvatarMisc", pb);

		MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(packet);		

		entity.motionX = x;
		entity.motionY = y;
		entity.motionZ = z;
	}

	public static void sendIsBending(Entity entity, boolean isBending, int element) {
		
		PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
		try {
			pb.writeInt("isBending".getBytes().length);
			pb.writeBytes("isBending".getBytes());
			pb.writeBoolean(isBending);
			pb.writeInt(entity.getEntityId());
			pb.writeInt(element);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		S3FPacketCustomPayload packet = new S3FPacketCustomPayload("AvatarMisc", pb);

		MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(packet);
		
	}

	
	public static void playPublicMusic(World world, String song) {

		PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
		try {
			pb.writeInt("music".getBytes().length);
			pb.writeBytes("music".getBytes());
			pb.writeStringToBuffer(song);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		S3FPacketCustomPayload packet = new S3FPacketCustomPayload("AvatarMisc", pb);

		MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(packet);		
	}

	public static void playMusic(EntityPlayer player, String song) {
		PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
		try {
			pb.writeInt("music".getBytes().length);
			pb.writeBytes("music".getBytes());
			pb.writeStringToBuffer(song);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		S3FPacketCustomPayload packet = new S3FPacketCustomPayload("AvatarMisc", pb);

		((EntityPlayerMP) player).playerNetServerHandler.sendPacket(packet);	
	}
}
