package kieranvs.avatar.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kieranvs.avatar.mod_Avatar;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class BlockTreeOfTimeWood extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon texture;

	public BlockTreeOfTimeWood() {
		super(Material.wood);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(1F);
		this.setLightLevel(0.5F);
		this.setBlockUnbreakable();
	}

	@Override
	public IIcon getIcon(int par1, int par2){
		return texture;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister){
		this.texture = par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "tile.TreeOfTimeWood");
	}

}
