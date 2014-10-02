package kieranvs.avatar.block;

import java.util.Random;

import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.util.PacketSender;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityCloudFX;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockAirVent extends Block {

    @SideOnly(Side.CLIENT)
    private IIcon airVentTop;
    @SideOnly(Side.CLIENT)
    private IIcon airVentSide;
    @SideOnly(Side.CLIENT)
    private IIcon airVentBottom;

	public BlockAirVent() {
		super(Material.iron);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setLightLevel(0.4F);
	}
	
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata){
        if(side == 0){
        	return this.airVentBottom;
        }
        if(side == 1){
        	return this.airVentTop;
        }
        else{
        	return this.airVentSide;
        }
    	
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
		return new TileEntityAirVent();
	}

	@Override
	public void registerBlockIcons(IIconRegister iconregister) {
		this.airVentBottom = iconregister.registerIcon(mod_Avatar.modid + ":" + this.getUnlocalizedName() + "_bottom");
		this.airVentTop = iconregister.registerIcon(mod_Avatar.modid + ":" + this.getUnlocalizedName() + "_top");
		this.airVentSide= iconregister.registerIcon(mod_Avatar.modid + ":" + this.getUnlocalizedName() + "_side");
	}

}
