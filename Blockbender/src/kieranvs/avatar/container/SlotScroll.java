package kieranvs.avatar.container;

import kieranvs.avatar.item.ItemSpellTome;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotScroll extends Slot {

	public SlotScroll(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack) {
		if(par1ItemStack.getItem() instanceof ItemSpellTome){
			return true;
		}
		return false;
	}

}
