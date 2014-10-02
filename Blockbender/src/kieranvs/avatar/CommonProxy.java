package kieranvs.avatar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import kieranvs.avatar.bending.BendingTickHandler;
import kieranvs.avatar.client.ClientTickHandler;
import kieranvs.avatar.container.ContainerItem;
import kieranvs.avatar.container.GuiItemInventory;
import kieranvs.avatar.container.IInventoryItem;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy implements IGuiHandler {

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case 0:
			return new GuiItemInventory(player, player.inventory, new IInventoryItem(player.getHeldItem()));
		}
		return null;

	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z){
		switch(ID){
		case 0: 
			return new ContainerItem(player, player.inventory, new IInventoryItem(player.getHeldItem()));
		}
		return null;

	}


	public void loadCommons() {
		FMLCommonHandler.instance().bus().register(new BendingTickHandler());
	}

	public void registerRenderers() {
		System.out.println("[Avatar] The CommonProxy has loaded.");
	}

	public void registerSoundHandler() {

	}

}
