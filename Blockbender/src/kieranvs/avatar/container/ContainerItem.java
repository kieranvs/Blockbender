package kieranvs.avatar.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ContainerItem extends Container
{
	/** The Item Inventory for this Container */
	public final IInventoryItem inventory;

	/** Stores ItemStack that was used to open the container; used for saving to NBT
	 * Without this variable, the game will crash when GUI is open and you move the
	 * ItemStore whose inventory is currently in use */
	private final ItemStack containerstack;

	/** Set to true when contents of container have changed and need to be saved */
	public boolean needsUpdate;

	/** Using these will make transferStackInSlot easier to understand and implement
	 * INV_START is the index of the first slot in the Player's Inventory, so our
	 * InventoryItem's number of slots (e.g. 5 slots is array indices 0-4, so start at 5)
	 * Notice how we don't have to remember how many slots we made? We can just use
	 * InventoryItem.INV_SIZE and if we ever change it, the Container updates automatically. */
	private static final int INV_START = IInventoryItem.INV_SIZE, INV_END = INV_START+26,
			HOTBAR_START = INV_END+1, HOTBAR_END = HOTBAR_START+8;

	// If you're planning to add armor slots, put those first like this:
	// ARMOR_START = InventoryItem.INV_SIZE, ARMOR_END = ARMOR_START+3,
	// INV_START = ARMOR_END+1, and then carry on like above.

	public ContainerItem(EntityPlayer par1Player, InventoryPlayer inventoryPlayer, IInventoryItem inventoryItem)
	{
		this.inventory = inventoryItem;
		this.containerstack = par1Player.getHeldItem();

		int i;

		// ITEM INVENTORY - you'll need to adjust the slot locations to match your texture file
		// I have them set vertically in columns of 4 to the right of the player model
		for (i = 0; i < IInventoryItem.INV_SIZE; ++i)
		{
			this.addSlotToContainer(new SlotScroll(this.inventory, i, 8 + i * 18, 18));
		}

		// If you want, you can add ARMOR SLOTS here as well, but you need to
		// make a public version of SlotArmor. I won't be doing that in this tutorial.
		/*
for (i = 0; i < 4; ++i)
{
// These are the standard positions for survival inventory layout
this.addSlotToContainer(new SlotArmor(this.player, inventoryPlayer, inventoryPlayer.getSizeInventory() - 1 - i, 8, 8 + i * 18, i));
}
		 */

		// PLAYER INVENTORY - uses default locations for standard inventory texture file
		for (i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 49 + i * 18));
			}
		}

		// PLAYER ACTION BAR - uses default locations for standard action bar texture file
		for (i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 107));
		}
	}

	/**
	 * Writes contents of inventory to correct itemstack's NBT Tag Compound
	 * This is the method we will call from our custom Item's onUpdate method
	 */
	public void writeToNBT()
	{
		// Use this.containerstack for getting compound
		if (!this.containerstack.hasTagCompound())
		{
			this.containerstack.setTagCompound(new NBTTagCompound());
		}
		// Cast to InventoryItem so we can call the method from that class:
		((IInventoryItem) inventory).writeToNBT(this.containerstack.getTagCompound());
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer)
	{
		return true;
	}

	/**
	 * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
	 * Only real change we make to this is to set needsUpdate to true at the end
	 */
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(par2);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			// If item is in our custom Inventory or armor slot
			if (par2 < INV_START)
			{
				// try to place in player inventory / action bar
				if (!this.mergeItemStack(itemstack1, INV_START, HOTBAR_END + 1, true))
				{
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			}
			// Item is in inventory / hotbar, try to place in custom inventory or armor slots
			else
			{
				/* If your inventory only stores certain instances of Items,
				 * you can implement shift-clicking to your inventory like this:
// Check that the item is the right type
if (itemstack1.getItem() instanceof ItemCustom)
{
// Try to merge into your custom inventory slots
// We use 'InventoryItem.INV_SIZE' instead of INV_START just in case
// you also add armor or other custom slots
if (!this.mergeItemStack(itemstack1, 0, InventoryItem.INV_SIZE, false))
{
return null;
}
}
// If you added armor slots, check them here as well:
// Item being shift-clicked is armor - try to put in armor slot
if (itemstack1.getItem() instanceof ItemArmor)
{
int type = ((ItemArmor) itemstack1.getItem()).armorType;
if (!this.mergeItemStack(itemstack1, ARMOR_START + type, ARMOR_START + type + 1, false))
{
return null;
}
}
				 * Otherwise, you have basically 2 choices:
				 * 1. shift-clicking between action bar and inventory
				 * 2. shift-clicking between player inventory and custom inventory
				 * I've implemented number 1:
				 */
				// item is in player's inventory, but not in action bar
				if (par2 >= INV_START && par2 < HOTBAR_START)
				{
					// place in action bar
					if (!this.mergeItemStack(itemstack1, HOTBAR_START, HOTBAR_END + 1, false))
					{
						return null;
					}
				}
				// item in action bar - place in player inventory
				else if (par2 >= HOTBAR_START && par2 < HOTBAR_END + 1)
				{
					if (!this.mergeItemStack(itemstack1, INV_START, INV_END + 1, false))
					{
						return null;
					}
				}
			}

			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack) null);
			}
			else
			{
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}

			slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
		}

		// This flag tells our custom Item to call ContainerItem's writeToNBT method
		this.needsUpdate = true;

		return itemstack;
	}

	/**
	 * We only override this so that we can tell our InventoryItem to update
	 */
	@Override
	public ItemStack slotClick(int slotID, int buttonPressed, int flag, EntityPlayer player)
	{
		this.needsUpdate = true;
		return super.slotClick(slotID, buttonPressed, flag, player);
	}
}