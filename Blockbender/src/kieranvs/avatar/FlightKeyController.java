package kieranvs.avatar;

import java.util.HashMap;

import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import kieranvs.avatar.client.ClientProxy;

public class FlightKeyController {
	
	private int lastSentFlightState = -1;
	
	public void updateClientSide(){
		
		if(ClientProxy.key.getFlightKeyStates() != lastSentFlightState){
			sendState(ClientProxy.key.getFlightKeyStates());
			lastSentFlightState = ClientProxy.key.getFlightKeyStates();
		}
		
	}
	
	private static void sendState(int state){
		PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
		try {
			pb.writeInt("keyInput".getBytes().length);
			pb.writeBytes("keyInput".getBytes());
			pb.writeInt(state);
			pb.writeInt(Minecraft.getMinecraft().thePlayer.getDisplayName().getBytes().length);
			pb.writeBytes(Minecraft.getMinecraft().thePlayer.getDisplayName().getBytes());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		C17PacketCustomPayload packet = new C17PacketCustomPayload("AvatarMisc", pb);
		
		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(packet);
	}
	
	public HashMap<String, Integer> flightStates = new HashMap<String, Integer>();
	
	
	

}
