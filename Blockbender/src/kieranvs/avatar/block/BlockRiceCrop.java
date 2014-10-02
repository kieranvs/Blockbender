package kieranvs.avatar.block;

import java.util.ArrayList;
import java.util.Random;

import kieranvs.avatar.mod_Avatar;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockRiceCrop extends BlockCrops {
	
	@SideOnly(Side.CLIENT)
	private IIcon grownIcon;

	public BlockRiceCrop() {
		super();
		setTickRandomly(true);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	@Override
	public int getRenderType() {
		return 6;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
    public IIcon getIcon(int side, int metadata){
		if (metadata == 0)
			return this.blockIcon;
		else return this.grownIcon;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlockType) {
		if (!canBlockStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlock(x, y, z, Blocks.air);
		}
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		Block soil = world.getBlock(x, y - 1, z);
		return (world.getFullBlockLightValue(x, y, z) >= 8 || world.canBlockSeeTheSky(x, y, z)) && (soil != null && soil.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, mod_Avatar.RiceGrainItem));
	}

	@Override
    public void func_149863_m(World par1World, int par2, int par3, int par4){
        int l = par1World.getBlockMetadata(par2, par3, par4) + MathHelper.getRandomIntegerInRange(par1World.rand, 2, 5);

        if (l > 2){
            l = 2;
        }
        else if (l < 1){
        	l = 1;
        }

        par1World.setBlockMetadataWithNotify(par2, par3, par4, l, 2);
    }
	
	@Override
    public Item func_149866_i(){
        return mod_Avatar.RiceGrainItem;
    }
	
	@Override
	public Item func_149865_P(){
        return mod_Avatar.RiceGrainItem;
    }
	
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune){
        ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);

        if (metadata == 2){
            for (int i = 0; i < 3 + fortune; ++i){
                if (world.rand.nextInt(5) <= metadata){
                    ret.add(new ItemStack(mod_Avatar.RiceGrainItem, world.rand.nextInt(6), 0));
                }
            }
        }

        return ret;
    }

	@Override
	public void registerBlockIcons(IIconRegister iconregister) {
		this.blockIcon = iconregister.registerIcon(mod_Avatar.modid + ":" + "tile.Rice Crop");
		this.grownIcon = iconregister.registerIcon(mod_Avatar.modid + ":" + "tile.Rice Crop" + "_grown");
	}

}
