package kieranvs.avatar.block;

import java.util.Random;

import kieranvs.avatar.mod_Avatar;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSeaPrunePlant extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon texture;
	
	public BlockSeaPrunePlant() {
		super(Material.leaves);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.setStepSound(soundTypeGrass);
		this.setHardness(0.01F);
		this.setLightLevel(0.1F);
		float var4 = 0.4F;
		this.setBlockBounds(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);
	}


	@Override
	public IIcon getIcon(int par1, int par2){
		return texture;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister){
		this.texture = par1IconRegister.registerIcon(mod_Avatar.modid + ":" + "tile.Sea Prune Plant");
	}


	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return null;
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public Item getItemDropped(int par1, Random r, int par3) {
		return mod_Avatar.SeaPruneItem;
	}

	@Override
	public int quantityDropped(Random r) {
		return r.nextInt(3) + 1;
	}

}
