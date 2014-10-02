package kieranvs.avatar.bending.fire;

import io.netty.buffer.Unpooled;
import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.bending.Ability;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.client.MovingSoundEffect;
import kieranvs.avatar.client.SoundHandler;
import kieranvs.avatar.util.Messaging;
import kieranvs.avatar.util.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSoundMinecart;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.biome.BiomeGenBase;

public class FireFlight extends AsynchronousAbility {

	private long starttime;
	private int flightTime;
	private double flightSpeed;
	private static int cooldown = 18000;

	long soundTime = 1500;
	long startTime = System.currentTimeMillis();

	public FireFlight(EntityLivingBase user, int level) {
		super(user, cooldown + (level * 5000));
		this.user = user;
		starttime = System.currentTimeMillis();
		if(level == 0){
			flightTime = 1700;
			flightSpeed = 0.8;
		}
		if(level == 1){
			flightTime = 3000;
			flightSpeed = 1;
		}
		if(level == 2){
			flightTime = 5000;
			flightSpeed = 1.3;
		}
		
		PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
		try {
			pb.writeInt("ContinueSound".getBytes().length);
			pb.writeBytes("ContinueSound".getBytes());
			pb.writeInt((mod_Avatar.modid + ":" + "fire06looping").getBytes().length);
			pb.writeBytes((mod_Avatar.modid + ":" + "fire06looping").getBytes());
			pb.writeInt(user.getUniqueID().toString().getBytes().length);
			pb.writeBytes(user.getUniqueID().toString().getBytes());
			pb.writeBoolean(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		S3FPacketCustomPayload packet = new S3FPacketCustomPayload("AvatarBend", pb);

		MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(packet);

	}

	@Override
	public void update() {
		if (System.currentTimeMillis() - starttime > flightTime || !isOverLand()) {
			PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
			try {
				pb.writeInt("ContinueSound".getBytes().length);
				pb.writeBytes("ContinueSound".getBytes());
				pb.writeInt((mod_Avatar.modid + ":" + "fire06looping").getBytes().length);
				pb.writeBytes((mod_Avatar.modid + ":" + "fire06looping").getBytes());
				pb.writeInt(user.getUniqueID().toString().getBytes().length);
				pb.writeBytes(user.getUniqueID().toString().getBytes());
				pb.writeBoolean(false);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			S3FPacketCustomPayload packet = new S3FPacketCustomPayload("AvatarBend", pb);

			MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(packet);

			destroy();
			return;
		}
		Location l = new Location(user);

		if((System.currentTimeMillis() - startTime) > soundTime){
			startTime = System.currentTimeMillis();

			PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
			try {
				pb.writeInt("ContinueSound".getBytes().length);
				pb.writeBytes("ContinueSound".getBytes());
				pb.writeInt((mod_Avatar.modid + ":" + "fire06looping").getBytes().length);
				pb.writeBytes((mod_Avatar.modid + ":" + "fire06looping").getBytes());
				pb.writeInt(user.getUniqueID().toString().getBytes().length);
				pb.writeBytes(user.getUniqueID().toString().getBytes());
				pb.writeBoolean(true);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			S3FPacketCustomPayload packet = new S3FPacketCustomPayload("AvatarBend", pb);

			MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(packet);

		}

		PacketSender.spawnParticle("Avatar_flamesbig", l.getWorld(), l.getX(), l.getY(), l.getZ());

		Vector v = new Vector(user.getLookVec().xCoord, user.getLookVec().yCoord, user.getLookVec().zCoord);
		v.normalize();
		v.multiply(flightSpeed);
		v.setY(v.getY() + 0.6f);
		PacketSender.sendVelocity(user, v);
	}

	public boolean isOverLand() {
		if(user.worldObj.isRaining()){
			if((user.worldObj.getBiomeGenForCoords((int)user.posX, (int)user.posZ)) != BiomeGenBase.desert){
				if((user.worldObj.getBiomeGenForCoords((int)user.posX, (int)user.posZ)) != BiomeGenBase.desertHills){
					if((user.worldObj.getBiomeGenForCoords((int)user.posX, (int)user.posZ)) != BiomeGenBase.mesa){
						if((user.worldObj.getBiomeGenForCoords((int)user.posX, (int)user.posZ)) != BiomeGenBase.mesaPlateau){
							if((user.worldObj.getBiomeGenForCoords((int)user.posX, (int)user.posZ)) != BiomeGenBase.mesaPlateau_F){
								if(user instanceof EntityPlayer){
									Messaging.avatarMessage((EntityPlayer) this.user, "You can't fly when it's raining!");
								}
								return false;
							}
						}
					}
				}
			}
		}
		boolean isGround = false;
		BlockBukkit b = new Location(user).getBlock();
		while (!isGround) {
			if (b.getType() == Blocks.air) {
				b = b.getRelative(BlockBukkit.DOWN);
			}
			else {
				if (b.getType() == Blocks.water || b.getType() == Blocks.ice || b.getType() == Blocks.snow || b.getType() == Blocks.water || b.getType() == Blocks.water) {
					if(user instanceof EntityPlayer){
						Messaging.avatarMessage((EntityPlayer) this.user, "You can't fly over water!");
					}
					return false;
				}
				return true;
			}
		}
		if(user instanceof EntityPlayer){
			Messaging.avatarMessage((EntityPlayer) this.user, "You can't fly over water!");
		}
		return false;
	}

}
