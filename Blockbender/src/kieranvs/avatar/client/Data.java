package kieranvs.avatar.client;

import io.netty.buffer.Unpooled;

import java.util.ArrayList;

import kieranvs.avatar.entity.EntityGlider;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.server.MinecraftServer;

public class Data {

	public int Chi = -1;

	public String familiarname;
	public int familiarhealth = -1;
	public int familiarmood = -1;
	
	public boolean pshow = false;
	public int pmoves;
	
	public boolean showHelp = true;
	
	public boolean updateAvailable = false;

	private ArrayList<String> glidingPlayerNames = new ArrayList<String>();
	
	public void addGlidingPlayerName(String name){
		addGlidingPlayerName(name, false);
	}
	
	public void addGlidingPlayerName(String name, boolean skipSync){
		glidingPlayerNames.add(name);
		if(!skipSync){
			Side side = FMLCommonHandler.instance().getEffectiveSide();
			if (side == Side.CLIENT) {
				syncGlidingCLIENT(0, name);
			}
			else {
				syncGlidingSERVER(0, name);
			}
		}
	}
	
	public void removeGlidingPlayerName(String name){
		removeGlidingPlayerName(name, false);
	}
	
	public void removeGlidingPlayerName(String name, boolean skipSync){
		glidingPlayerNames.remove(name);
		if(!skipSync){
			Side side = FMLCommonHandler.instance().getEffectiveSide();
			if (side == Side.CLIENT) {
				syncGlidingCLIENT(1, name);
			}
			else {
				syncGlidingSERVER(1, name);
			}
		}
	}
	
	public boolean isPlayerGliding(String name){
		return glidingPlayerNames.contains(name);
	}
	
	public void syncGlidingSERVER(int type, String name){ /*0 - add, 1 - remove*/
		PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
		try {
			pb.writeInt("gliderdata".getBytes().length);
			pb.writeBytes("gliderdata".getBytes());
			pb.writeInt(type);
			pb.writeInt(name.getBytes().length);
			pb.writeBytes(name.getBytes());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		S3FPacketCustomPayload packet = new S3FPacketCustomPayload("AvatarMisc", pb);
		
		MinecraftServer.getServer().getConfigurationManager().sendPacketToAllPlayers(packet);
	}
	
	public void syncGlidingCLIENT(int type, String name){ /*0 - add, 1 - remove*/
		PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
		try {
			pb.writeInt("gliderdata".getBytes().length);
			pb.writeBytes("gliderdata".getBytes());
			pb.writeInt(type);
			pb.writeInt(name.getBytes().length);
			pb.writeBytes(name.getBytes());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		C17PacketCustomPayload packet = new C17PacketCustomPayload("AvatarMisc", pb);

		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(packet);
	}
	
}
