package kieranvs.avatar.block;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import kieranvs.avatar.Protection;
import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.util.AvatarDamageSource;
import kieranvs.avatar.util.PacketSender;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityBlueFire extends TileEntity {

	private static Random rand = new Random();
	private static Block[] destroyBlocks = {Blocks.air, Blocks.planks, Blocks.sapling, Blocks.leaves, Blocks.web, Blocks.deadbush, Blocks.tallgrass, Blocks.wool, Blocks.yellow_flower, Blocks.red_flower, Blocks.brown_mushroom, Blocks.red_mushroom, Blocks.fire, Blocks.wheat, Blocks.wall_sign, Blocks.lever, Blocks.snow, Blocks.ice, Blocks.snow_layer, Blocks.cactus, Blocks.reeds};
	private static Block[] unburnableBlocks = {Blocks.air, Blocks.flowing_lava, Blocks.lava, Blocks.glass, mod_Avatar.BlueFireIns, Blocks.bedrock, mod_Avatar.CharredGrassBlock};

	private int lifetime;

	public TileEntityBlueFire(){
		this.lifetime = 0;
	}

	public TileEntityBlueFire(int life){
		this.lifetime = life;
	}

	public int getLifeTime(){
		return lifetime;
	}

	public void setLifeTime(int life){
		this.lifetime = life;
	}

	@Override
	public void updateEntity() {
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT){
			return;
		}
		if(this.lifetime < 0){
			this.lifetime = 0;
		}
		boolean autodelete = false;
		int c = 0;
		for(Object e : this.worldObj.loadedTileEntityList){
			if(e instanceof TileEntityBlueFire) c++;
		}
		if(c > 1000){
			autodelete = true;
		}
		if(!Protection.canBlockChangeHere(this.worldObj, this.xCoord, this.yCoord, this.zCoord)){
			autodelete = true;
		}
		this.lifetime++;
		if(this.lifetime > 1000 || autodelete){
			if(rand.nextInt(5) == 0 || autodelete || this.lifetime > 1500){
				this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, Blocks.air);
				this.worldObj.removeTileEntity(this.xCoord, this.yCoord, this.zCoord);
				this.invalidate();
				if(this.worldObj.getBlock(this.xCoord, this.yCoord - 1, this.zCoord) == Blocks.grass){
					if(rand.nextInt(4) == 0){
						this.worldObj.setBlock(this.xCoord, this.yCoord - 1, this.zCoord, mod_Avatar.CharredGrassBlock, 0, 0x02);
					}
					else{
						this.worldObj.setBlock(this.xCoord, this.yCoord - 1, this.zCoord, Blocks.dirt, 0, 0x02);
					}
				}
				else if(this.worldObj.getBlock(this.xCoord, this.yCoord - 1, this.zCoord) == Blocks.sand) this.worldObj.setBlock(this.xCoord, this.yCoord - 1, this.zCoord, Blocks.glass, 0, 0x02);
				return;
			}
		}
		spread();
		burnNearbyEntites();
		checkNearbyStuff(this.xCoord, this.yCoord, this.zCoord);
	}

	private void spread(){
		burnRelativeWithChance(1, 0, 0, 100);
		burnRelativeWithChance(-1, 0, 0, 100);
		burnRelativeWithChance(0, 1, 0, 20);
		burnRelativeWithChance(0, -1, 0, 2000);
		burnRelativeWithChance(0, 0, 1, 100);
		burnRelativeWithChance(0, 0, -1, 100);
	}

	private void burnRelativeWithChance(int x, int y, int z, int chance){
		if(canBlockBurn(this.worldObj, this.xCoord + x, this.yCoord + y, this.zCoord + z)){
			if(rand.nextInt(chance) == 0){
				burnBlock(this.xCoord + x, this.yCoord + y, this.zCoord + z);
			}
		}
	}

	private void burnBlock(int x, int y, int z){
		this.worldObj.setBlock(x, y, z, mod_Avatar.BlueFireIns, 0, 0x02);
		if(this.worldObj.getTileEntity(x, y, z) != null && this.worldObj.getTileEntity(x, y, z) instanceof TileEntityBlueFire){			
			((TileEntityBlueFire) this.worldObj.getTileEntity(x, y, z)).setLifeTime(this.lifetime);
		}
	}
	
	private void checkNearbyStuff(int a, int b, int c){
		for(int i = -3; i < 4; i++){
			for(int j = -3; j < 4; j++){
				for(int k = -3; k < 4; k++){
					int x = a + i;
					int y = b + j;
					int z = c + k;
					if(this.worldObj.getBlock(x, y, z) == Blocks.tnt){
						this.lifetime += 850f;
						this.worldObj.setBlock(x, y, z, Blocks.air);
						this.worldObj.createExplosion(null, x, y, z, 40F, true);
					}
					if(this.worldObj.getBlock(x, y, z) == Blocks.lava || this.worldObj.getBlock(x, y, z) == Blocks.flowing_lava){
						this.lifetime += 850f;
						this.worldObj.setBlock(x, y, z, Blocks.air);
						this.worldObj.createExplosion(null, x, y, z, 4F, true);
						orangeFireSurroundings(x, y, z);
					}
					if(this.worldObj.getBlock(x, y, z) == Blocks.leaves){
						if(rand.nextInt(500) == 0){
							this.lifetime -= 50f;
							//burnBlock(x, y, z);
							orangeFireSurroundings(x, y, z, 200);							
						}
					}
					if(this.worldObj.getBlock(x, y, z) == Blocks.water || this.worldObj.getBlock(x, y, z) == Blocks.flowing_water){
						if(rand.nextInt(500) == 0){
							this.lifetime += 100f;
							burnBlock(x, y, z);
							PacketSender.spawnParticle("Avatar_cloud", this.worldObj, x, y, z, 50, 1.5f);
							orangeFireSurroundings(x, y, z, 500);							
						}
					}
				}
			}
		}
	}
	
	private void orangeFireSurroundings(int x, int y, int z){
		orangeFireSurroundings(x, y, z, 1);
	}
	
	private void orangeFireSurroundings(int x, int y, int z, int chance){
		for(int i = -2; i < 3; i++){
			for(int j = -2; j < 3; j++){
				for(int k = -2; k < 3; k++){
					if(rand.nextInt(chance) == 0){
						if(this.worldObj.getBlock(x + i, y + j, z + k) == Blocks.air){
							this.worldObj.setBlock(x + i, y + j, z + k, Blocks.fire);
						}						
					}
				}
			}
		}
	}

	private boolean canBlockBurn(World w, int x, int y, int z){
		if(!contains(destroyBlocks, w.getBlock(x, y, z))) return false;
		if(contains(unburnableBlocks, w.getBlock(x, y - 1, z)) &&
				contains(unburnableBlocks, w.getBlock(x + 1, y, z)) &&
				contains(unburnableBlocks, w.getBlock(x - 1, y, z)) &&
				contains(unburnableBlocks, w.getBlock(x, y, z + 1)) &&
				contains(unburnableBlocks, w.getBlock(x, y, z - 1))) return false;
		return true;
	}

	public static boolean canRenderSideFire(IBlockAccess w, int x, int y, int z, ForgeDirection face){
		Block block = w.getBlock(x, y, z);
		if (block != null)
		{
			return true;
		}
		return false;
	}

	private boolean contains(final Block[] array, final Block key) {  
		for(Block i : array){
			if(i == key) return true;
		}
		return false;
	}  

	private void burnNearbyEntites(){
		Location location = new Location(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
		Object[] o = location.getWorld().loadedEntityList.toArray();

		for(int i = 0; i<o.length; i++){
			if(o[i] instanceof EntityLivingBase){
				EntityLivingBase e = (EntityLivingBase) o[i];
				if (location.distance(e) < 1.2) {
					if(e instanceof EntityPlayer){						
						e.attackEntityFrom(AvatarDamageSource.firebending, 4);
					}
					else{
						e.attackEntityFrom(AvatarDamageSource.firebending, 12);
					}
					e.setFire(20);
					if(e instanceof EntityCreeper){
						e.worldObj.createExplosion(e, e.posX, e.posY, e.posZ, 10f, true);
						e.setDead();
					}
				}
			}		
		}
	}

	@Override
	public boolean canUpdate() {
		return true;
	}

}
