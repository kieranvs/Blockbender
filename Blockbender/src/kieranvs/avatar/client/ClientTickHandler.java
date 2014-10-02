package kieranvs.avatar.client;

import io.netty.buffer.Unpooled;

import java.util.EnumSet;
import java.util.StringTokenizer;

import org.lwjgl.input.Keyboard;

import kieranvs.avatar.HelpStrings;
import kieranvs.avatar.KeyBind;
import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.entity.EntityFamiliar;
import kieranvs.avatar.item.ItemSpellTome;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.MovementInput;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ClientTickHandler {

	int alphapulse = 60;
	long lastContactUpdate = System.currentTimeMillis() - 500000;

	private final Minecraft client = Minecraft.getMinecraft();

	@SubscribeEvent
	public void tickEnd(TickEvent event) {
		if(Minecraft.getMinecraft().debug != null && lastContactUpdate < System.currentTimeMillis() - 1000 * 60 * 10){
			lastContactUpdate = System.currentTimeMillis();
		}
		alphapulse--;
		if (event.type.equals(TickEvent.Type.RENDER)) {
			onRenderTick();
		}
	}
	
	private int lastSentInput = 0;
	
	private int hash(MovementInput mi){
		int hash = 0;
		hash += mi.moveForward * 23;
		hash -= mi.moveStrafe * 17;
		if(mi.jump) hash += 22;
		if(mi.sneak) hash -= 35;
		return hash;
	}

	public void onRenderTick() {
		GuiScreen guiscreen = Minecraft.getMinecraft().currentScreen;
		if (guiscreen == null && mod_Avatar.data.Chi != -1) {
			FMLClientHandler.instance().getClient().fontRenderer.drawStringWithShadow("Chi: " + mod_Avatar.data.Chi, 0, 1, 16777215);
		}
		if (guiscreen == null && mod_Avatar.data.pshow){
			FMLClientHandler.instance().getClient().fontRenderer.drawStringWithShadow("Moves: " + mod_Avatar.data.pmoves, 0, 41, 16777215);
		}
		if (guiscreen instanceof GuiMainMenu) {
			if(mod_Avatar.data.updateAvailable){
				FMLClientHandler.instance().getClient().fontRenderer.drawStringWithShadow("Avatar The Last Blockbender " + mod_Avatar.version + " - UPDATE AVAILABLE!", 10, 10, 0xFF0000);
			}
			else{
				FMLClientHandler.instance().getClient().fontRenderer.drawStringWithShadow("Avatar The Last Blockbender " + mod_Avatar.version, 10, 10, 0xFF0000);
			}
		}

		if (guiscreen == null) {
			if(mod_Avatar.data.showHelp){
				renderHelpInfo();				
			}
			renderFamiliarInfo();
		}
	}

	private void renderHelpInfo() {
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		int width = res.getScaledWidth();
		int height = res.getScaledHeight();

		int indent = width - 100;
		int top = height - 100;

		if(FMLClientHandler.instance().getClient().thePlayer == null || FMLClientHandler.instance().getClient().thePlayer.getHeldItem() == null || FMLClientHandler.instance().getClient().thePlayer.getHeldItem().getItem() == null){
			return;
		}
		if( FMLClientHandler.instance().getClient().thePlayer.getHeldItem().getItem() instanceof ItemSpellTome){
			String s = HelpStrings.getHelpMessage((ItemSpellTome) FMLClientHandler.instance().getClient().thePlayer.getHeldItem().getItem());
			if(s != null){
				FMLClientHandler.instance().getClient().fontRenderer.drawStringWithShadow("Help:", indent - 100, top, 0xFFFFFF);
				int gap = 0;
				String towrite = "";
				StringTokenizer tok = new StringTokenizer(s, " ");
				int lineLen = 0;
				while (tok.hasMoreTokens()) {
					String word = tok.nextToken();
					if (lineLen + word.length() > 25) {
						FMLClientHandler.instance().getClient().fontRenderer.drawStringWithShadow(towrite, indent - 100, top + 10 + gap, 0xFFFFFF);
						gap += 10;
						lineLen = 0;
						towrite = "";
					}
					towrite = towrite + " " + word;
					lineLen += word.length();
				}
				if(!towrite.equals("")){
					FMLClientHandler.instance().getClient().fontRenderer.drawStringWithShadow(towrite, indent - 100, top + 10 + gap, 0xFFFFFF);
				}
			}
		}
	}

	private void renderFamiliarInfo() {
		int top;
		if (FMLClientHandler.instance().getClient().gameSettings.guiScale == 0) {
			top = 250;
		}
		else {
			top = FMLClientHandler.instance().getClient().displayHeight / FMLClientHandler.instance().getClient().gameSettings.guiScale;
		}
		top -= 50;
		int indent = 15;
		int spacing = 5;
		if (mod_Avatar.data.familiarname != null) {
			FMLClientHandler.instance().getClient().fontRenderer.drawStringWithShadow("Companion: " + mod_Avatar.data.familiarname, indent, top, 0xFFFFFF);
			if (mod_Avatar.data.familiarhealth != -1) {
				FMLClientHandler.instance().getClient().fontRenderer.drawStringWithShadow("Health: " + mod_Avatar.data.familiarhealth, indent, top + spacing + 10, 0xFFFFFF);
			}
			if (mod_Avatar.data.familiarmood != -1) {
				FMLClientHandler.instance().getClient().fontRenderer.drawStringWithShadow("Mood: " + EntityFamiliar.getMoodName(mod_Avatar.data.familiarmood), indent, top + 2 * spacing + 2 * 10, EntityFamiliar.getMoodColor(mod_Avatar.data.familiarmood));
			}
		}
	}
}
