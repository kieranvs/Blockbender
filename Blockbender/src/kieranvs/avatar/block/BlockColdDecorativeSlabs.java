package kieranvs.avatar.block;

import java.util.List;
import java.util.Random;

import kieranvs.avatar.mod_Avatar;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockColdDecorativeSlabs extends BlockSlab {

	@SideOnly(Side.CLIENT)
	private IIcon IceSlab, SnowSlab, PackedIceSlab;

	public int id;

	public BlockColdDecorativeSlabs(boolean doubleSlab, Material material, int id){
		super(doubleSlab, material);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.id = id;
		if(this.id == 1){
			this.setStepSound(soundTypeSnow);
		} else{
			this.slipperiness = 0.98F;
			this.setStepSound(soundTypeGlass);
		}
		this.useNeighborBrightness = true;
	}

	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass(){
		if(id != 1) return 1;
		return 0;
	}
	
	@Override
    public boolean isOpaqueCube(){
		return false;
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta){   	
		switch(id){
		case 0:
			return Blocks.ice.getBlockTextureFromSide(side);
		case 1:
			return Blocks.snow.getBlockTextureFromSide(side);
		case 2: 
			return Blocks.packed_ice.getBlockTextureFromSide(side);
		default:
			return null;

		}
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_){
		switch(id){
		case 0:
			return Item.getItemFromBlock(mod_Avatar.IceSlab);
		case 1:
			return Item.getItemFromBlock(mod_Avatar.SnowSlab);
		case 2: 
			return Item.getItemFromBlock(mod_Avatar.PackedIceSlab);
		default:
			return null;
		}
	}

	@Override
	protected ItemStack createStackedBlock(int par1){
		switch(id){
		case 0:
			return new ItemStack(mod_Avatar.IceSlab, 2, par1 & 7);
		case 1:
			return new ItemStack(mod_Avatar.SnowSlab, 2, par1 & 7);
		case 2: 
			return new ItemStack(mod_Avatar.PackedIceSlab, 2, par1 & 7);
		default:
			return null;	
		}
	}

	@Override
	public String func_150002_b(int p_150002_1_) {
		switch(id){
		case 0:
			return "Ice Slab";
		case 1:
			return "Snow Slab";
		case 2: 
			return "Packed Ice Slab";
		default:
			return null;
		}
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List){
		if(id == 0){
			if(par1 != Item.getItemFromBlock(mod_Avatar.IceDoubleSlab)){
				par3List.add(new ItemStack(par1, 1, 0));
			}
		}
		if(id == 1){
			if(par1 != Item.getItemFromBlock(mod_Avatar.SnowDoubleSlab)){
				par3List.add(new ItemStack(par1, 1, 0));
			}
		}
		if(id == 2){
			if(par1 != Item.getItemFromBlock(mod_Avatar.PackedIceDoubleSlab)){
				par3List.add(new ItemStack(par1, 1, 0));
			}
		}

	}
	
	@Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z){
        switch(id){
        case 0:
        	 return new ItemStack(mod_Avatar.IceSlab);
        case 1:
        	return new ItemStack(mod_Avatar.SnowSlab);
        case 2:
        	return new ItemStack(mod_Avatar.PackedIceSlab);
        default:
        	return null;
        }
    }

}
