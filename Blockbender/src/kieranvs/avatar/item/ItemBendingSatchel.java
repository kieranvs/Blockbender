package kieranvs.avatar.item;

import io.netty.buffer.Unpooled;

import org.lwjgl.input.Keyboard;

import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.container.ContainerItem;
import kieranvs.avatar.container.IInventoryItem;
import kieranvs.avatar.util.StringColour;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class ItemBendingSatchel extends Item {

	public ItemBendingSatchel() {
		super();
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setTextureName(mod_Avatar.modid + ":" + "item.satchel");
		this.setUnlocalizedName("satchel");
	}
	
	@Override
	public boolean isValidArmor(ItemStack stack, int armorType, Entity entity) {
		if(armorType == 1){
			return true;
		}
		return false;
	}

	@Override
	public int getItemStackLimit() {
		return 1;
	}
	
	
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        return false;
    }
	
	@Override
	public void onUpdate(ItemStack itemstack, World world, Entity entity, int par4, boolean isCurrentItem)
	{
		if (!world.isRemote && entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			if(player.openContainer != null && player.openContainer instanceof ContainerItem && ((ContainerItem) player.openContainer).needsUpdate)
			{
				((ContainerItem) player.openContainer).writeToNBT();
				((ContainerItem) player.openContainer).needsUpdate = false;
			}
		}
	}
	
	public static ItemStack getSatchelContents(EntityPlayer player, int slot){
		if(player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() instanceof ItemBendingSatchel){
			IInventoryItem inv = new IInventoryItem(player.inventory.armorItemInSlot(2));
			return inv.getStackInSlot(slot);
		}
		player.addChatMessage(new ChatComponentText(StringColour.GRAY + StringColour.ITALIC + "You aren't wearing a satchel. (chest slot)"));
		return null;
	}

}
