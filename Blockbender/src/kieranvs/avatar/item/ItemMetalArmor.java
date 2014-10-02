package kieranvs.avatar.item;

import kieranvs.avatar.mod_Avatar;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemMetalArmor extends ItemArmor {
	
	public ItemMetalArmor(int id, ItemArmor.ArmorMaterial material, int renderIndex, int armorType) {
		super(material, renderIndex, armorType);
	}
	
	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String type) {
		if (itemstack.getItem() == mod_Avatar.MetalHelmet || itemstack.getItem() == mod_Avatar.MetalChestplate || itemstack.getItem() == mod_Avatar.MetalBoots) {
			return mod_Avatar.modid + ":" + "textures/armor/metal_1.png";
		}
		else if (itemstack.getItem() == mod_Avatar.MetalLeggings) {
			return mod_Avatar.modid + ":" + "textures/armor/metal_2.png";
		}

		return null;
	}

	
	@Override
	public void registerIcons(IIconRegister iconregister) {
		this.itemIcon = iconregister.registerIcon(mod_Avatar.modid + ":" + this.getUnlocalizedName());
	}

}
