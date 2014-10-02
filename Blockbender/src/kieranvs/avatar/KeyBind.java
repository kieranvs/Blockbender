package kieranvs.avatar;

import io.netty.buffer.Unpooled;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.relauncher.Side;

public class KeyBind {
	
	private KeyBinding scrollOne;
	private KeyBinding scrollTwo;
	private KeyBinding scrollThree;
	private KeyBinding scrollFour;
	private KeyBinding scrollFive;
	private KeyBinding scrollSix;
	private KeyBinding scrollSeven;
	private KeyBinding scrollEight;
	private KeyBinding scrollNine;
	private KeyBinding openSatchel;
	private KeyBinding toggleHelp;
	private KeyBinding flightUP;
	private KeyBinding flightDOWN;
	
	public KeyBind(){
		scrollOne = new KeyBinding("Scroll One", Keyboard.KEY_Z, "Avatar");
		scrollTwo = new KeyBinding("Scroll Two", Keyboard.KEY_X, "Avatar");
		scrollThree = new KeyBinding("Scroll Three", Keyboard.KEY_C, "Avatar");
		scrollFour = new KeyBinding("Scroll Four", Keyboard.KEY_V, "Avatar");
		scrollFive = new KeyBinding("Scroll Five", Keyboard.KEY_B, "Avatar");
		scrollSix = new KeyBinding("Scroll Six", Keyboard.KEY_N, "Avatar");
		scrollSeven = new KeyBinding("Scroll Seven", Keyboard.KEY_M, "Avatar");
		scrollEight = new KeyBinding("Scroll Eight", Keyboard.KEY_COMMA, "Avatar");
		scrollNine = new KeyBinding("Scroll Nine", Keyboard.KEY_PERIOD, "Avatar");
		openSatchel = new KeyBinding("Open Satchel", Keyboard.KEY_I, "Avatar");
		toggleHelp = new KeyBinding("Toggle Help", Keyboard.KEY_H, "Avatar");
		flightUP = new KeyBinding("Fly Up", Keyboard.KEY_R, "Avatar");
		flightDOWN = new KeyBinding("Fly Down", Keyboard.KEY_F, "Avatar");
		ClientRegistry.registerKeyBinding(scrollOne);
		ClientRegistry.registerKeyBinding(scrollTwo);
		ClientRegistry.registerKeyBinding(scrollThree);
		ClientRegistry.registerKeyBinding(scrollFour);
		ClientRegistry.registerKeyBinding(scrollFive);
		ClientRegistry.registerKeyBinding(scrollSix);
		ClientRegistry.registerKeyBinding(scrollSeven);
		ClientRegistry.registerKeyBinding(scrollEight);
		ClientRegistry.registerKeyBinding(scrollNine);
		ClientRegistry.registerKeyBinding(openSatchel);
		ClientRegistry.registerKeyBinding(toggleHelp);
		ClientRegistry.registerKeyBinding(flightUP);
		ClientRegistry.registerKeyBinding(flightDOWN);
	}
	
	@SubscribeEvent
	public void KeyInputEvent(KeyInputEvent event) {
		if(toggleHelp.getIsKeyPressed()){
			helpKeyPressed();
			return;
		}
		
		int keyInt = -1;
		if(scrollOne.getIsKeyPressed()) keyInt = 0;
		if(scrollTwo.getIsKeyPressed()) keyInt = 1;
		if(scrollThree.getIsKeyPressed()) keyInt = 2;
		if(scrollFour.getIsKeyPressed()) keyInt = 3;
		if(scrollFive.getIsKeyPressed()) keyInt = 4;
		if(scrollSix.getIsKeyPressed()) keyInt = 5;
		if(scrollSeven.getIsKeyPressed()) keyInt = 6;
		if(scrollEight.getIsKeyPressed()) keyInt = 7;
		if(scrollNine.getIsKeyPressed()) keyInt = 8;
		if(openSatchel.getIsKeyPressed()) keyInt = 10;
		
		if(!FMLClientHandler.instance().getClient().inGameHasFocus || keyInt == -1){
			return;
		}
		
		sendPacket(keyInt);
	}
	
	private static void helpKeyPressed(){
		mod_Avatar.data.showHelp = !mod_Avatar.data.showHelp;
	}
	
	private static void sendPacket(int keyInt){
		PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
		try {
			pb.writeInt(keyInt);
			pb.writeBoolean(true);
			pb.writeInt(Minecraft.getMinecraft().thePlayer.getDisplayName().getBytes().length);
			pb.writeBytes(Minecraft.getMinecraft().thePlayer.getDisplayName().getBytes());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		C17PacketCustomPayload packet = new C17PacketCustomPayload("AvatarKey", pb);
		
		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(packet);		
	}
	
	/* 0 = nothing, 1 = up, 2 = down */
	public int getFlightKeyStates(){
		if(flightUP.isPressed()) return 1;
		if(flightDOWN.isPressed()) return 2;
		return 0;
	}

}