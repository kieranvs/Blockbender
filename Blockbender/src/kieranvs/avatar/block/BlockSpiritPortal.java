package kieranvs.avatar.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import kieranvs.avatar.mod_Avatar;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockSpiritPortal extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon texture;
	
	public BlockSpiritPortal(int par1) {
		super(Material.iron);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setLightLevel(0.4F);
	}

	@Override
	public IIcon getIcon(int par1, int par2){
		return texture;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister){
		this.texture = par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "tile.YellowGrass");
	}


	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);
		par1World.setTileEntity(par2, par3, par4, createTileEntity(par1World, par1World.getBlockMetadata(par2, par3, par4)));
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return new TileEntitySpiritPortal();
	}

	@Override
	public boolean isOpaqueCube(){
		return false;
	}

	@Override
	public boolean isBlockNormalCube(){
		return false;
	}

}
