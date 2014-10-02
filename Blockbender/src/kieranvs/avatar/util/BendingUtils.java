package kieranvs.avatar.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.generation.TeleporterSpiritWorld;

public class BendingUtils {

	public static void sendToDimension(EntityPlayerMP e, int dimension){
		if (e.timeUntilPortal > 0)
		{
			e.timeUntilPortal = 10;
		}
		else if (e.dimension != dimension)
		{
			e.timeUntilPortal = 10;
			MinecraftServer.getServer().getConfigurationManager().transferPlayerToDimension(e, dimension, new TeleporterSpiritWorld(MinecraftServer.getServer().worldServerForDimension(dimension)));
		}
	}

	@Deprecated
	public static void damageEntities(Location location, float range, AvatarDamageSource source, int damage){
		damageEntities(null, location, range, source, damage);
	}

	public static void damageEntities(EntityLivingBase damager, Location location, float range, AvatarDamageSource source, int damage){
		Object[] o = location.getWorld().loadedEntityList.toArray();

		for(int i = 0; i<o.length; i++){
			if((o[i] instanceof EntityLivingBase) && (o[i] != damager)){
				EntityLivingBase e = (EntityLivingBase) o[i];
				if (location.distance(e) < range) {
					e.attackEntityFrom(source, damage);
				}
			}		
		}
	}

	public static void damageEntitiesWithCustomAction(Location location, float range, AvatarDamageSource source, int damage, EntityActionPerformer action){
		damageEntitiesWithCustomAction(null, location, range, source, damage, action);
	}

	public static void damageEntitiesWithCustomAction(EntityLivingBase damager, Location location, float range, AvatarDamageSource source, int damage, EntityActionPerformer action){
		Object[] o = location.getWorld().loadedEntityList.toArray();

		for(int i = 0; i<o.length; i++){
			if((o[i] instanceof EntityLivingBase) && (o[i] != damager)){
				EntityLivingBase e = (EntityLivingBase) o[i];
				if (location.distance(e) < range) {
					e.attackEntityFrom(source, damage);
					action.performAction(e);
				}
			}		
		}

		location.setY(location.getY() - 1);
		Object[] oDown = location.getWorld().loadedEntityList.toArray();

		for(int i = 0; i<oDown.length; i++){
			if((oDown[i] instanceof EntityLivingBase) && (oDown[i] != damager)){
				EntityLivingBase e = (EntityLivingBase) oDown[i];
				if (location.distance(e) < range) {
					e.attackEntityFrom(source, damage);
					action.performAction(e);
				}
			}		
		}

		location.setY(location.getY() + 1);

	}

	public static void performCustomAction(EntityLivingBase damager, Location location, float range, EntityActionPerformer action){
		Object[] o = location.getWorld().loadedEntityList.toArray();

		for(int i = 0; i<o.length; i++){
			if((o[i] instanceof EntityLivingBase) && (o[i] != damager)){
				EntityLivingBase e = (EntityLivingBase) o[i];
				if (location.distance(e) < range) {
					action.performAction(e);
				}
			}		
		}

		location.setY(location.getY() - 1);
		Object[] oDown = location.getWorld().loadedEntityList.toArray();

		for(int i = 0; i<oDown.length; i++){
			if((oDown[i] instanceof EntityLivingBase) && (oDown[i] != damager)){
				EntityLivingBase e = (EntityLivingBase) oDown[i];
				if (location.distance(e) < range) {
					action.performAction(e);
				}
			}		
		}

		location.setY(location.getY() + 1);

	}


	public static void repelEntitiesInSphere(EntityLivingBase damager, Location location, float range){
		Object[] o = location.getWorld().loadedEntityList.toArray();

		for(int i = 0; i < o.length; i++){
			if((o[i] instanceof Entity) && (o[i] != damager)){
				Entity e = (Entity) o[i];
				Location eLoc = new Location(e);
				if (location.distance(e) < range) {
					double x, z, vx, vz, mag;
					double angle = 50;
					angle = Math.toRadians(angle);

					x = eLoc.getX() - location.getX();
					z = eLoc.getZ() - location.getZ();

					mag = Math.sqrt(x * x + z * z);

					vx = (x * Math.cos(angle) - z * Math.sin(angle)) / mag;
					vz = (x * Math.sin(angle) + z * Math.cos(angle)) / mag;

					Vector velocity = eLoc.getDirection();
					velocity.setX(vx);
					velocity.setZ(vz);

					e.addVelocity(velocity.getX(), velocity.getY(), velocity.getZ());
					e.fallDistance = 0;


				}
			}
		}

	}

	public static void repelEntitiesOnYourLevelAndAbove(EntityLivingBase damager, Location location, float range){
		Object[] o = location.getWorld().loadedEntityList.toArray();

		for(int i = 0; i < o.length; i++){
			if((o[i] instanceof Entity) && (o[i] != damager)){
				Entity e = (Entity) o[i];
				Location eLoc = new Location(e);
				if (location.distance(e) < range) {
					if(location.getY() >= new Location(damager).getY()){
						double x, z, vx, vz, mag;
						double angle = 50;
						angle = Math.toRadians(angle);

						x = eLoc.getX() - location.getX();
						z = eLoc.getZ() - location.getZ();

						mag = Math.sqrt(x * x + z * z);

						vx = (x * Math.cos(angle) - z * Math.sin(angle)) / mag;
						vz = (x * Math.sin(angle) + z * Math.cos(angle)) / mag;

						Vector velocity = eLoc.getDirection();
						velocity.setX(vx);
						velocity.setZ(vz);

						e.addVelocity(velocity.getX(), velocity.getY(), velocity.getZ());
						e.fallDistance = 0;


					}

				}
			}
		}

	}

	public static void damageAllEntitiesWithCustomAction(EntityLivingBase damager, Location location, float range, AvatarDamageSource source, int damage, EntityActionPerformer action){
		Object[] o = location.getWorld().loadedEntityList.toArray();

		for(int i = 0; i<o.length; i++){
			if((o[i] instanceof Entity) && (o[i] != damager)){
				Entity e = (Entity) o[i];
				if (location.distance(e) < range) {
					action.performAction(e);
					if(o[i] instanceof EntityLivingBase){
						e.attackEntityFrom(source, damage);
					}
				}
			}		
		}

		location.setY(location.getY() - 1);
		Object[] oDown = location.getWorld().loadedEntityList.toArray();

		for(int i = 0; i<oDown.length; i++){
			if((oDown[i] instanceof Entity) && (oDown[i] != damager)){
				Entity e = (Entity) oDown[i];
				if (location.distance(e) < range) {
					action.performAction(e);
					if(o[i] instanceof EntityLivingBase){
						e.attackEntityFrom(source, damage);
					}

				}
			}		
		}

		location.setY(location.getY() + 1);

	}

	public static boolean isPlant(Block block){
		Block[] plants = new Block[20];
		plants[0] = Blocks.sapling;
		plants[1] = Blocks.tallgrass;
		plants[2] = Blocks.deadbush;
		plants[3] = Blocks.yellow_flower;
		plants[4] = Blocks.red_flower;
		plants[5] = Blocks.brown_mushroom;
		plants[6] = Blocks.red_mushroom;
		plants[7] = Blocks.cactus;
		plants[8] = Blocks.reeds;
		plants[9] = Blocks.waterlily;
		plants[10] = Blocks.vine;
		plants[11] = Blocks.melon_stem;
		plants[12] = Blocks.pumpkin_stem;
		plants[13] = Blocks.leaves;
		plants[14] = Blocks.leaves2;
		plants[15] = Blocks.wheat;
		plants[16] = Blocks.cocoa;
		plants[17] = Blocks.carrots;
		plants[18] = Blocks.potatoes;
		plants[19] = Blocks.double_plant;

		if(Arrays.asList(plants).contains(block)){
			return true;
		}
		else{
			return false;
		}
	}

	public static boolean isDroppablePlant(Block block){
		Block[] plants = new Block[18];
		plants[0] = Blocks.sapling;
		plants[1] = Blocks.tallgrass;
		plants[2] = Blocks.deadbush;
		plants[3] = Blocks.yellow_flower;
		plants[4] = Blocks.red_flower;
		plants[5] = Blocks.brown_mushroom;
		plants[6] = Blocks.red_mushroom;
		plants[7] = Blocks.cactus;
		plants[8] = Blocks.reeds;
		plants[9] = Blocks.waterlily;
		plants[10] = Blocks.vine;
		plants[11] = Blocks.melon_stem;
		plants[12] = Blocks.pumpkin_stem;
		plants[13] = Blocks.wheat;
		plants[14] = Blocks.cocoa;
		plants[15] = Blocks.carrots;
		plants[16] = Blocks.potatoes;
		plants[17] = Blocks.double_plant;

		if(Arrays.asList(plants).contains(block)){
			return true;
		}
		else{
			return false;
		}
	}

	public static boolean isWaterBendable(Block block){
		Block[] waterBendable = new Block[6];
		waterBendable[0] = Blocks.snow_layer;
		waterBendable[1] = Blocks.ice;
		waterBendable[2] = Blocks.water;
		waterBendable[3] = Blocks.packed_ice;
		waterBendable[4] = Blocks.snow;
		waterBendable[5] = Blocks.flowing_water;

		if(Arrays.asList(waterBendable).contains(block)){
			return true;
		}
		else{
			return false;
		}

	}
	
	public static boolean isWaterBendableOrPlant(Block block){
		Block[] plants = new Block[31];
		plants[0] = Blocks.sapling;
		plants[1] = Blocks.tallgrass;
		plants[2] = Blocks.snow_layer;
		plants[3] = Blocks.yellow_flower;
		plants[4] = Blocks.red_flower;
		plants[5] = Blocks.brown_mushroom;
		plants[6] = Blocks.red_mushroom;
		plants[7] = Blocks.cactus;
		plants[8] = Blocks.reeds;
		plants[9] = Blocks.waterlily;
		plants[10] = Blocks.vine;
		plants[11] = Blocks.melon_stem;
		plants[12] = Blocks.pumpkin_stem;
		plants[13] = Blocks.leaves;
		plants[14] = Blocks.leaves2;
		plants[15] = Blocks.wheat;
		plants[16] = Blocks.pumpkin;
		plants[17] = Blocks.melon_block;
		plants[18] = Blocks.brown_mushroom_block;
		plants[19] = Blocks.red_mushroom_block;
		plants[20] = Blocks.cocoa;
		plants[21] = Blocks.carrots;
		plants[22] = Blocks.potatoes;
		plants[23] = Blocks.hay_block;
		plants[24] = Blocks.double_plant;
		plants[25] = Blocks.water;
		plants[26] = Blocks.ice;
		plants[27] = Blocks.packed_ice;
		plants[28] = Blocks.snow;

		if(Arrays.asList(plants).contains(block)){
			return true;
		}
		else{
			return false;
		}

	}

	public static void affectLevers(World worldObj, BlockBukkit block){
		if(block.getType() == Blocks.lever){
			int i1 = worldObj.getBlockMetadata((int)block.getX(), (int)block.getY(), (int)block.getZ());
			int j1 = i1 & 7;
			int k1 = 8 - (i1 & 8);
			worldObj.setBlockMetadataWithNotify((int)block.getX(), (int)block.getY(), (int)block.getZ(), j1 + k1, 3);
			worldObj.playSoundEffect((double)block.getX() + 0.5D, (double)block.getY() + 0.5D, (double)block.getZ() + 0.5D, "random.click", 0.3F, k1 > 0 ? 0.6F : 0.5F);
			worldObj.notifyBlocksOfNeighborChange((int)block.getX(), (int)block.getY(), (int)block.getZ(), Blocks.lever);

			if (j1 == 1)
			{
				worldObj.notifyBlocksOfNeighborChange((int)block.getX() - 1, (int)block.getY(), (int)block.getZ(), Blocks.lever);
			}
			else if (j1 == 2)
			{
				worldObj.notifyBlocksOfNeighborChange((int)block.getX() + 1, (int)block.getY(), (int)block.getZ(), Blocks.lever);
			}
			else if (j1 == 3)
			{
				worldObj.notifyBlocksOfNeighborChange((int)block.getX(), (int)block.getY(), (int)block.getZ() - 1, Blocks.lever);
			}
			else if (j1 == 4)
			{
				worldObj.notifyBlocksOfNeighborChange((int)block.getX(), (int)block.getY(), (int)block.getZ() + 1, Blocks.lever);
			}
			else if (j1 != 5 && j1 != 6)
			{
				if (j1 == 0 || j1 == 7)
				{
					worldObj.notifyBlocksOfNeighborChange((int)block.getX(), (int)block.getY() + 1, (int)block.getZ(), Blocks.lever);
				}
			}
			else
			{
				worldObj.notifyBlocksOfNeighborChange((int)block.getX(), (int)block.getY() - 1, (int)block.getZ(), Blocks.lever);
			}

		}

	}

	public static void affectButtons(World worldObj, BlockBukkit block){
		if(block.getType() == Blocks.wooden_button || block.getType() == Blocks.stone_button){
			net.minecraft.block.Block buttonId;
			boolean woodenButton = false;
			if(block.getType() == Blocks.wooden_button){
				woodenButton = true;
				buttonId = Blocks.wooden_button;
			}
			else{
				buttonId = Blocks.stone_button;
			}
			int i1 = worldObj.getBlockMetadata((int)block.getX(), (int)block.getY(), (int)block.getZ());
			int j1 = i1 & 7;
			int k1 = 8 - (i1 & 8);

			if (!(k1 == 0)){
				worldObj.setBlockMetadataWithNotify((int)block.getX(), (int)block.getY(), (int)block.getZ(), j1 + k1, 3);
				worldObj.markBlockRangeForRenderUpdate((int)block.getX(), (int)block.getY(), (int)block.getZ(), (int)block.getX(), (int)block.getY(), (int)block.getZ());
				worldObj.playSoundEffect((double)(int)block.getX() + 0.5D, (double)(int)block.getY() + 0.5D, (double)(int)block.getZ() + 0.5D, "random.click", 0.3F, 0.6F);

				worldObj.notifyBlocksOfNeighborChange((int)block.getX(), (int)block.getY(), (int)block.getZ(), buttonId);

				if (j1 == 1)
				{
					worldObj.notifyBlocksOfNeighborChange((int)block.getX() - 1, (int)block.getY(), (int)block.getZ(), buttonId);
				}
				else if (j1 == 2)
				{
					worldObj.notifyBlocksOfNeighborChange((int)block.getX() + 1, (int)block.getY(), (int)block.getZ(), buttonId);
				}
				else if (j1 == 3)
				{
					worldObj.notifyBlocksOfNeighborChange((int)block.getX(), (int)block.getY(), (int)block.getZ() - 1, buttonId);
				}
				else if (j1 == 4)
				{
					worldObj.notifyBlocksOfNeighborChange((int)block.getX(), (int)block.getY(), (int)block.getZ() + 1, buttonId);
				}
				else
				{
					worldObj.notifyBlocksOfNeighborChange((int)block.getX(), (int)block.getY() - 1, (int)block.getZ(), buttonId);
				}

				worldObj.scheduleBlockUpdate((int)block.getX(), (int)block.getY(), (int)block.getZ(), block.getType(), 10);
			}

		}


	}

	public static void douseFlames(World worldObj, BlockBukkit block){
		if(block.getType() == Blocks.fire){
			block.setType(Blocks.air);
			worldObj.playSoundEffect((double)block.getX() + 0.5D, (double)block.getY() + 0.5D, (double)block.getZ() + 0.5D, "random.fizz", 1.0F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
		}
	}

	private static Block[] moveable = { Blocks.brick_block, Blocks.clay, Blocks.coal_ore, Blocks.cobblestone, Blocks.dirt, Blocks.grass, 
		Blocks.gravel, Blocks.mossy_cobblestone, Blocks.mycelium, Blocks.sand, Blocks.sandstone, Blocks.farmland, Blocks.stone, 
		Blocks.coal_block, Blocks.iron_ore, Blocks.gold_ore, Blocks.diamond_ore, Blocks.emerald_ore, Blocks.hardened_clay, 
		Blocks.lapis_ore, Blocks.quartz_block, Blocks.quartz_ore, Blocks.redstone_ore, Blocks.stonebrick, Blocks.stained_hardened_clay
	};

	public static boolean isEarthBendable(Block block) {

		if (Arrays.asList(moveable).contains(block)) {
			return true;
		}

		return false;
	}

	private static Block[] overwriteable = { Blocks.air, Blocks.brown_mushroom_block, Blocks.cactus, Blocks.cake, Blocks.carrots, Blocks.cocoa, Blocks.flower_pot,
		Blocks.lit_pumpkin, Blocks.melon_block, Blocks.melon_stem, Blocks.potatoes, Blocks.pumpkin, Blocks.pumpkin_stem, Blocks.reeds, Blocks.redstone_torch, Blocks.redstone_wire, Blocks.red_mushroom_block,
		Blocks.sapling, Blocks.snow_layer, Blocks.standing_sign, Blocks.torch, Blocks.tripwire, Blocks.tallgrass, Blocks.tripwire_hook,
		Blocks.unlit_redstone_torch, Blocks.vine, Blocks.wall_sign, Blocks.waterlily, Blocks.web, Blocks.wheat, Blocks.wooden_button, Blocks.yellow_flower
	};

	public static boolean isOverwriteable(Block block) {

		if (Arrays.asList(overwriteable).contains(block)) {
			return true;
		}

		return false;

	}

	public static EntityPlayer getPlayerByName(String name){
		int numDimensions = MinecraftServer.getServer().worldServers.length;
		for(int x = 0; x < numDimensions; x++){
			if(MinecraftServer.getServer().worldServers[x].getPlayerEntityByName(name) != null){
				return MinecraftServer.getServer().worldServers[x].getPlayerEntityByName(name);
			}
		}

		return null;
	}
	
	public static List playerList(){
		int numDimensions = MinecraftServer.getServer().worldServers.length;
		List<EntityPlayer> playerList = new ArrayList<EntityPlayer>();
		for(int x = 0; x < numDimensions; x++){
			playerList.addAll(MinecraftServer.getServer().worldServers[x].playerEntities);
		}
		
		return playerList;
	}

}
