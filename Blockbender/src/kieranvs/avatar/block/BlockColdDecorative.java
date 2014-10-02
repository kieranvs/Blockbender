package kieranvs.avatar.block;

import kieranvs.avatar.mod_Avatar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockColdDecorative extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon IceBrick, IceBrickChiseled, SnowBrick, SnowBrickChiseled, PackedIceBrick, PackedIceBrickChiseled;

	public int id;
	
	public BlockColdDecorative(Material material, int id) {
		super(material);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.id = id;
		if(this.id == 2 || this.id == 3){
			this.setStepSound(soundTypeSnow);
		} else{
			this.slipperiness = 0.98F;
			this.setStepSound(soundTypeGlass);
		}
	}
	
	@Override
    public boolean isOpaqueCube(){
		if(this.id == 0 || this.id == 1) return false;
		return true;
    }
	
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_){
        return super.shouldSideBeRendered(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, 1 - p_149646_5_);
    }
	
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass(){
    	if(id != 2 || id != 3) return 1;
    	return 0;
    }
	
	@Override
	public IIcon getIcon(int par1, int par2){
		switch(id){
		case 0:
			return IceBrick;
		case 1:
			return IceBrickChiseled;
		case 2:
			return SnowBrick;
		case 3:
			return SnowBrickChiseled;
		case 4:
			return PackedIceBrick;
		case 5:
			return PackedIceBrickChiseled;
		default:
			return null;
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister){
		this.IceBrick = par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "ice_brick");
		this.IceBrickChiseled = par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "ice_brick_chiseled");
		this.SnowBrick = par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "snow_brick");
		this.SnowBrickChiseled = par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "snow_brick_chiseled");
		this.PackedIceBrick = par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "packed_ice_brick");
		this.PackedIceBrickChiseled = par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "packed_ice_brick_chiseled");

	}



}
