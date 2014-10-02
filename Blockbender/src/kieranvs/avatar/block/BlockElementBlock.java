package kieranvs.avatar.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kieranvs.avatar.mod_Avatar;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.IIcon;

public class BlockElementBlock extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon WaterBlock, WaterNationBlock, EarthBlock, EarthKingdomBlock, FireBlock, FireNationBlock, AirBlock, AirNomadsBlock;
	private int id;
	
	/*
	 * Id is only to identify type of block so as to use the correct texture.
	 * 0 = Water Block, 1 = waternation, 2= earth, 3= earthkingdom, 4= fire, 5= firenation, 6=air, 7= airnomad
	 */
	public BlockElementBlock(int id) {
		super(Material.cloth);
		this.id = id;
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(2F);
		this.setLightLevel(0.5F);
	}
	
	@Override
	public IIcon getIcon(int par1, int par2){
		switch(id){
		case 0:
			return WaterBlock;
		case 1:
			return WaterNationBlock;
		case 2:
			return EarthBlock;
		case 3:
			return EarthKingdomBlock;
		case 4:
			return FireBlock;
		case 5:
			return FireNationBlock;
		case 6:
			return AirBlock;
		case 7:
			return AirNomadsBlock;
		default:
			return null;
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister){
		this.WaterBlock = par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "tile.WaterBlock");
		this.WaterNationBlock = par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "tile.WaterTribeBlock");
		this.EarthBlock = par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "tile.EarthBlock");
		this.EarthKingdomBlock = par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "tile.EarthKingdomBlock");
		this.FireBlock = par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "tile.FireBlock");
		this.FireNationBlock = par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "tile.FireNationBlock");
		this.AirBlock = par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "tile.AirBlock");
		this.AirNomadsBlock = par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "tile.AirNomadsBlock");

	}

}
