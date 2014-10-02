package kieranvs.avatar.item;

import kieranvs.avatar.mod_Avatar;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemMoney extends Item {

	private int denomination;

	public ItemMoney(int denomination) {
		super();
		this.denomination = denomination;
		String texturename = "";
		String itemname = "";
		switch (denomination) {
		case 0:
			texturename = "GoldPiece";
			itemname = "Gold Piece";
			this.maxStackSize = 8;
			break;
		case 1:
			texturename = "SilverPiece";
			itemname = "Silver Piece";
			this.maxStackSize = 32;
			break;
		case 2:
			texturename = "BronzePiece";
			itemname = "Bronze Piece";
			this.maxStackSize = 64;
			break;
		}
		this.setUnlocalizedName(texturename);
		LanguageRegistry.addName(this, itemname);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.setTextureName(mod_Avatar.modid + ":" + this.getUnlocalizedName());
	}

	@Override
	public EnumRarity getRarity(ItemStack par1ItemStack) {
		if (denomination == 0) {
			return EnumRarity.uncommon;
		}
		return EnumRarity.common;
	}

}
