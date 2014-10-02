package kieranvs.avatar.block;

import java.util.Random;

import kieranvs.avatar.mod_Avatar;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGrassCustom extends Block {
	
	private int type;
	@SideOnly(Side.CLIENT)
	private IIcon yellowTexture, greenTexture, charredTexture;

	public BlockGrassCustom(int type){
		super(Material.grass);
		this.stepSound = soundTypeGrass;
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.type = type;
		if(type == 0 || type == 2){
			this.setTickRandomly(true);
		}
	}
		
	@Override
	public IIcon getIcon(int par1, int par2){
		switch(type){
		case 0:
			return yellowTexture;
		case 1:
			return greenTexture;
		case 2:
			return charredTexture;
		default:
				return null;
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister){
		this.yellowTexture = par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "tile.YellowGrass");
		this.greenTexture = par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "tile.SpiritGrass");
		this.charredTexture = par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "tile.CharredDirt");
	}

	
	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random){
		if(type == 1){
		}
		if(type == 2){
			for(int i = -2; i < 3; i++){
				for(int j = -2; j < 3; j++){
					for(int k = -2; k < 3; k++){
						if(par1World.getBlock(par2 + i, par3 + j, par4 + k) == Blocks.water){
							par1World.setBlock(par2, par3, par4, Blocks.grass);
						}
					}
				}
			}

		}
	}

	@Override
	public int quantityDropped(Random par1Random){
		if(type == 0) return 0;
		return super.quantityDropped(par1Random);
	}

	@Override
	public int quantityDroppedWithBonus(int par1, Random par2Random){
		if(type == 0) return 0;
		return super.quantityDroppedWithBonus(par1, par2Random);
	}

	@Override
	public int quantityDropped(int meta, int fortune, Random random){
		if(type == 0) return 0;
		return super.quantityDropped(meta, fortune, random);
	}

}
