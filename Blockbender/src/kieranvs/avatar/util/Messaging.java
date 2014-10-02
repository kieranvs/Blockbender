package kieranvs.avatar.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;

public class Messaging {

	public static void avatarMessage(EntityPlayer player, String message) {
		player.addChatMessage(new ChatComponentText(StringColour.AQUA + StringColour.BOLD + "[Avatar] " + StringColour.RESET + message));
	}

	public static void avatarPublicMessage(String message) {
		for (Object player : BendingUtils.playerList()) {
			avatarMessage((EntityPlayer) player, message);
		}
	}

	public static void avatarAdminMessage(String message) {
		for (Object player : BendingUtils.playerList()) {
			avatarMessage((EntityPlayer) player, message);
		}
	}

}
