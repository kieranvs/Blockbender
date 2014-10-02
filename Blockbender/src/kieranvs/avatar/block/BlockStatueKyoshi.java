package kieranvs.avatar.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kieranvs.avatar.mod_Avatar;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockStatueKyoshi extends Block {

	@SideOnly(Side.CLIENT)
	private IIcon avatarKyoshiStatue;

	public BlockStatueKyoshi() {
		super(Material.rock);
		this.setBlockName("AvatarKyoshiStatue");
		this.setHardness(100000f);
		this.setResistance(100000f);
		this.setCreativeTab(mod_Avatar.tabEarthbending);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 2.5F, 1F);
	}

	@Override
	public TileEntity createTileEntity(World world, int meta) {
		return new TileEntityKyoshiStatue();
	}

	@Override
	public void registerBlockIcons(IIconRegister iconregister) {
		this.avatarKyoshiStatue = iconregister.registerIcon(mod_Avatar.modid + ":" + "AvatarKyoshiStatue");
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata){
		return this.avatarKyoshiStatue;
	}


	@Override
	public int getRenderType() {
		return mod_Avatar.renderID;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {
		super.onBlockAdded(par1World, par2, par3, par4);
		par1World.setTileEntity(par2, par3, par4, createTileEntity(par1World, par1World.getBlockMetadata(par2, par3, par4)));
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemStack)
	{
		int l = MathHelper.floor_double((double)(entity.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		int i1 = world.getBlockMetadata(x, y, z) >> 2;
		++l;
		l %= 4;

		if (l == 0)
		{
			world.setBlockMetadataWithNotify(x, y, z, 2 | i1 << 2, 2);
		}

		if (l == 1)
		{
			world.setBlockMetadataWithNotify(x, y, z, 3 | i1 << 2, 2);
		}

		if (l == 2)
		{
			world.setBlockMetadataWithNotify(x, y, z, 0 | i1 << 2, 2);
		}

		if (l == 3)
		{
			world.setBlockMetadataWithNotify(x, y, z, 1 | i1 << 2, 2);
		}
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}

}
