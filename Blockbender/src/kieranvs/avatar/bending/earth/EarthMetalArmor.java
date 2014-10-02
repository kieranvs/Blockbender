package kieranvs.avatar.bending.earth;

import kieranvs.avatar.Protection;
import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.client.SoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EarthMetalArmor {

	private EntityPlayer player;
	private Location location;
	private ItemStack metalHelmet, metalPlate, metalLegs, metalBoots;
	private ItemStack ironHelmet, ironPlate, ironLegs, ironBoots;

	public EarthMetalArmor(EntityPlayer player, Location location) {
		super();
		this.player = player;
		this.location = location.clone();
		metalHelmet = new ItemStack(mod_Avatar.MetalHelmet);
		metalPlate = new ItemStack(mod_Avatar.MetalChestplate);
		metalLegs = new ItemStack(mod_Avatar.MetalLeggings);
		metalBoots = new ItemStack(mod_Avatar.MetalBoots);
		ironHelmet = new ItemStack(Items.iron_helmet);
		ironPlate = new ItemStack(Items.iron_chestplate);
		ironLegs = new ItemStack(Items.iron_leggings);
		ironBoots = new ItemStack(Items.iron_boots);

		if (location.getBlock().getRelative(BlockBukkit.DOWN).getType() == mod_Avatar.MetalArmorBlock) {
			if (player.inventory.armorItemInSlot(3) != null || player.inventory.armorItemInSlot(2) != null || player.inventory.armorItemInSlot(1) != null || player.inventory.armorItemInSlot(0) != null) {
				return;
			}
			else {
				player.inventory.setInventorySlotContents(5, metalHelmet);//.inventoryContainer.putStackInSlot(5, metalHelmet);
				player.inventoryContainer.putStackInSlot(6, metalPlate);
				player.inventoryContainer.putStackInSlot(7, metalLegs);
				player.inventoryContainer.putStackInSlot(8, metalBoots);
				Protection.trySetBlock(player.worldObj, Blocks.air, location.getBlock().getRelative(BlockBukkit.DOWN).getX(), location.getBlock().getRelative(BlockBukkit.DOWN).getY(), 
						location.getBlock().getRelative(BlockBukkit.DOWN).getZ());
				SoundHandler.playOnBlock("random.anvil_land", player.worldObj, player.posX, player.posY, player.posZ, 1F, 1F, false);
			}
		}

		if (location.getBlock().getRelative(BlockBukkit.DOWN).getType() == Blocks.iron_block) {
			if (player.inventory.armorItemInSlot(3) != null || player.inventory.armorItemInSlot(2) != null || player.inventory.armorItemInSlot(1) != null || player.inventory.armorItemInSlot(0) != null) {
				return;
			}
			else {
				player.inventoryContainer.putStackInSlot(5, ironHelmet);
				player.inventoryContainer.putStackInSlot(6, ironPlate);
				player.inventoryContainer.putStackInSlot(7, ironLegs);
				player.inventoryContainer.putStackInSlot(8, ironBoots);
				Protection.trySetBlock(player.worldObj, Blocks.air, location.getBlock().getRelative(BlockBukkit.DOWN).getX(), location.getBlock().getRelative(BlockBukkit.DOWN).getY(), 
						location.getBlock().getRelative(BlockBukkit.DOWN).getZ());
				SoundHandler.playOnBlock("random.anvil_land", player.worldObj, player.posX, player.posY, player.posZ, 1F, 1F, false);
			}

		}
	}

}
