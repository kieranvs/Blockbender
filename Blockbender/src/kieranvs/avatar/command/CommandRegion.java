package kieranvs.avatar.command;

import java.util.List;

import kieranvs.avatar.Protection;
import kieranvs.avatar.util.Messaging;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandRegion extends CommandBase {
	
	@Override
	public String getCommandName() {
		return "region";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "";
	}
	
	@Override
	public List getCommandAliases() {
		return null;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (!(sender instanceof EntityPlayer)) {
			return;
		}
		EntityPlayer p = (EntityPlayer) sender;
		if(!isOp(p)){
			Messaging.avatarMessage(p, "You must be op to use this command.");
			return;
		}
		
		if(args.length == 0){
			Messaging.avatarMessage(p, "Usage: /region create name pos1x pos1y pos1x pos2x pos2y pos2z cancelBending cancelBendingGriefing");
			Messaging.avatarMessage(p, "or: /region list");
			return;
		}
		
		if(args[0].equals("create")){
			if(args.length != 10){
				Messaging.avatarMessage(p, "Usage: /region create name pos1x pos1y pos1x pos2x pos2y pos2z cancelBending cancelBendingGriefing");
				return;
			}
			String name = args[1];
			int pos1x = Integer.valueOf(args[2]);
			int pos1y = Integer.valueOf(args[3]);
			int pos1z = Integer.valueOf(args[4]);
			int pos2x = Integer.valueOf(args[5]);
			int pos2y = Integer.valueOf(args[6]);
			int pos2z = Integer.valueOf(args[7]);
			boolean cancelBending = args[8].equals("true");
			boolean cancelGriefing = args[9].equals("true");
			Protection.addRegion(p.worldObj.getWorldInfo().getWorldName(), name, pos1x, pos1y, pos1z, pos2x, pos2y, pos2z, cancelGriefing, cancelBending);
			Messaging.avatarMessage(p, "Added region " + name);
			return;
		}
		if(args[0].equals("list")){
			Protection.listRegions(p);
			return;
		}
		if(args[0].equals("info")){
			Messaging.avatarMessage(p, "Can bend here: " + Protection.canBendHere(p.worldObj, (int) p.posX, (int) p.posY, (int) p.posZ));
			Messaging.avatarMessage(p, "Can bending grief here: " + Protection.canBlockChangeHere(p.worldObj, (int) p.posX, (int) p.posY, (int) p.posZ));
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