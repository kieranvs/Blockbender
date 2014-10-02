package kieranvs.avatar.command;

import java.util.ArrayList;
import java.util.List;

import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.bending.ElementManager;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.Messaging;
import kieranvs.avatar.util.StringColour;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandBending extends CommandBase {
	
	public CommandBending(){
		aliases = new ArrayList<String>();
		aliases.add("blockbender");
	}

	@Override
	public String getCommandName() {
		return "bending";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/bending <element> [playerName]";
	}
	
	private static List aliases;

	@Override
	public List getCommandAliases() {
		return aliases;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (!(sender instanceof EntityPlayer)) {
			return;
		}
		EntityPlayer p = (EntityPlayer) sender;
		if(ElementManager.hasElement(p, ElementManager.FIRE) || ElementManager.hasElement(p, ElementManager.AIR) || ElementManager.hasElement(p, ElementManager.EARTH) || ElementManager.hasElement(p, ElementManager.WATER)){
			if(!isOp(p)){
				Messaging.avatarMessage(p, StringColour.ITALIC + StringColour.GRAY + "You have already selected your element!");
				return;
			}
		}
		if (args[0].equalsIgnoreCase("fire")) {
			if(args.length == 1) ElementManager.setElement(p, ElementManager.FIRE);
			else{
				if(isOp(p)) ElementManager.setElement(BendingUtils.getPlayerByName(args[1]), ElementManager.FIRE);
			}
		}
		if (args[0].equalsIgnoreCase("water")) {
			if(args.length == 1) ElementManager.setElement(p, ElementManager.WATER);
			else{
				if(isOp(p)) ElementManager.setElement(BendingUtils.getPlayerByName(args[1]), ElementManager.WATER);
			}
		}
		if (args[0].equalsIgnoreCase("earth")) {
			if(args.length == 1) ElementManager.setElement(p, ElementManager.EARTH);
			else{
				if(isOp(p)) ElementManager.setElement(BendingUtils.getPlayerByName(args[1]), ElementManager.EARTH);
			}
		}
		if (args[0].equalsIgnoreCase("air")) {
			if(args.length == 1) ElementManager.setElement(p, ElementManager.AIR);
			else{
				if(isOp(p)) ElementManager.setElement(BendingUtils.getPlayerByName(args[1]), ElementManager.AIR);
			}
		}
		if (args[0].equalsIgnoreCase("avatar")) {
			if(!isOp(p)){
				Messaging.avatarMessage(BendingUtils.getPlayerByName(sender.getCommandSenderName()), StringColour.ITALIC + StringColour.GRAY + "Only server ops wield the power to decide the Avatar!");
				return;
			}
			if(!mod_Avatar.isAvatarAllowed){
				Messaging.avatarMessage(BendingUtils.getPlayerByName(sender.getCommandSenderName()), StringColour.ITALIC + StringColour.GRAY + "Avatar is disabled in the config!");
				return;
			}
			if(args.length == 1) ElementManager.setElement(p, ElementManager.AVATAR);
			else ElementManager.setElement(BendingUtils.getPlayerByName(args[1]), ElementManager.AVATAR);
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return true;
	}
	
	public static boolean isOp(EntityPlayer player){
		return MinecraftServer.getServer().getConfigurationManager().func_152596_g(player.getGameProfile());
	}

}
