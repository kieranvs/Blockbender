package kieranvs.avatar.block;

import java.util.Random;

import javax.swing.Icon;

import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.bukkit.Location;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityCloudFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlueFire extends BlockFire {

	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public BlueFire() {
		super();
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setLightLevel(1.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.icons = new IIcon[] { par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "bluefire_0"), par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "bluefire_1") };
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getFireIcon(int par1) {
		return this.icons[par1];
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		return this.icons[0];
	}
	
	@Override
	public int getRenderType(){
		return 99;
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
	public TileEntity createTileEntity(World world, int meta){
		return createTileEntity(world, meta, 0);
	}
	
	public TileEntity createTileEntity(World world, int meta, int life) {
		return new TileEntityBlueFire(life);
	}
	
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random){
	}
	
}
