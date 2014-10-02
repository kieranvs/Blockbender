package kieranvs.avatar.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import kieranvs.avatar.mod_Avatar;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class GenerationUtils {
	
	public static ArrayList<Item> fireNovice = new ArrayList<Item>();
	public static ArrayList<Item> fireAdept = new ArrayList<Item>();
	public static ArrayList<Item> fireMaster = new ArrayList<Item>();
	public static ArrayList<Item> waterNovice = new ArrayList<Item>();
	public static ArrayList<Item> waterAdept = new ArrayList<Item>();
	public static ArrayList<Item> waterMaster = new ArrayList<Item>();
	public static ArrayList<Item> earthNovice = new ArrayList<Item>();
	public static ArrayList<Item> earthAdept = new ArrayList<Item>();
	public static ArrayList<Item> earthMaster = new ArrayList<Item>();
	public static ArrayList<Item> airNovice = new ArrayList<Item>();
	public static ArrayList<Item> airAdept = new ArrayList<Item>();
	public static ArrayList<Item> airMaster = new ArrayList<Item>();
	
	public static ArrayList<Item> gliders = new ArrayList<Item>();
	
	public static int[] shouldSpawnHere(Random random, int chunkX, int chunkZ, World world, int size, int tolerance, int rarity, BiomeGenBase[] biomes){
		
		int[] result = new int[4];
		int x = (chunkX * 16) + random.nextInt(16);
		int z = (chunkZ * 16) + random.nextInt(16);
		
		BiomeGenBase biomegenbase = world.getWorldChunkManager().getBiomeGenAt(x, z);
		if (!Arrays.asList(biomes).contains(biomegenbase)) {
			result[0] = 1;
			return result;
		}
				
		if (random.nextInt(rarity) != 0){
			result[0] = 2;
			return result;
		}

		int sum = 0;
		int count = 0;
		int max = 0;
		int min = 999;
		int water = 0;

		for (int a = 0; a <= size; a++) {
			for (int b = 0; b <= size; b++) {
				int v = world.getHeightValue(x + a, z + b);
				if(v != 0){
					count++;
					sum += v;
					if (world.getBlock(x + a, v - 1, z + b) == Blocks.water){
						water++;
					}
					if (v > max)
						max = v;
					if (v < min)
						min = v;					
				}
			}
		}
		
		if(count == 0){
			result[0] = 99;
			return result;
		}

		int avg = sum / count;
		int range = max - min;

		if (range > tolerance) {
			result[0] = 3;
			result[1] = range;
			return result;
		}
		
		if (water > (float)(count / 10)){
			result[0] = 4;
			return result;
		}
		
		result[0] = 0;
		result[1] = x;
		result[2] = avg;
		result[3] = z;
		return result;
	}
	
	public static void generateScrolls(Random random, TileEntityChest chest, int element, int fortune){
		int numScrolls = 1;
		
		switch(fortune){
		case 1:
			numScrolls = 0 + random.nextInt(2);
			break;
		case 2:
			numScrolls = 0 + random.nextInt(3);
			break;
		case 3:
			numScrolls = 1 + random.nextInt(2);
			break;
		case 4:
			numScrolls = 2 + random.nextInt(3);
			break;
		case 5: 
			numScrolls = 3 + random.nextInt(2);
			break;
		}
		for(int i = 0; i < numScrolls; i++){
			int slot = random.nextInt(27);
			int randomNext = random.nextInt(100);
			int level = 0;
			if(randomNext > 0){
				level = 0;
				if(randomNext > 70){
					level = 1;
					if(randomNext > 95){
						level = 2;
					}
				}
			}
			chest.setInventorySlotContents(slot, getRandomScroll(random, element, level));			
		}
	}
	
	public static ItemStack getRandomScroll(Random random, int element, int level){
		Item itemType = null;
		if(element == 0){
			if(level == 0){
				itemType = fireNovice.get(random.nextInt(fireNovice.size()));
			}
			if(level == 1){
				itemType = fireAdept.get(random.nextInt(fireAdept.size()));
			}
			if(level == 2){
				itemType = fireMaster.get(random.nextInt(fireMaster.size()));
			}
		}
		if(element == 1){
			if(level == 0){
				itemType = earthNovice.get(random.nextInt(earthNovice.size()));
			}
			if(level == 1){
				itemType = earthAdept.get(random.nextInt(earthAdept.size()));
			}
			if(level == 2){
				itemType = earthMaster.get(random.nextInt(earthMaster.size()));
			}
		}
		if(element == 2){
			if(level == 0){
				itemType = waterNovice.get(random.nextInt(waterNovice.size()));
			}
			if(level == 1){
				itemType = waterAdept.get(random.nextInt(waterAdept.size()));
			}
			if(level == 2){
				itemType = waterMaster.get(random.nextInt(waterMaster.size()));
			}
		}
		if(element == 3){
			if(level == 0){
				itemType = airNovice.get(random.nextInt(airNovice.size()));
			}
			if(level == 1){
				itemType = airAdept.get(random.nextInt(airAdept.size()));
			}
			if(level == 2){
				itemType = airMaster.get(random.nextInt(airMaster.size()));
			}
		}
		return new ItemStack(itemType, 1, 0);
	}
	
	public static void registerScroll(int element, int level, Item itemType){
		if(element == 0){
			if(level == 0){
				fireNovice.add(itemType);
			}
			if(level == 1){
				fireAdept.add(itemType); 
			}
			if(level == 2){
				fireMaster.add(itemType);
			}
		}
		if(element == 1){
			if(level == 0){
				waterNovice.add(itemType);
			}
			if(level == 1){
				waterAdept.add(itemType);
			}
			if(level == 2){
				waterMaster.add(itemType);
			}
		}
		if(element == 2){
			if(level == 0){
				earthNovice.add(itemType);
			}
			if(level == 1){
				earthAdept.add(itemType);
			}
			if(level == 2){
				earthMaster.add(itemType);
			}
		}
		if(element == 3){
			if(level == 0){
				airNovice.add(itemType);
			}
			if(level == 1){
				airAdept.add(itemType);
			}
			if(level == 2){
				airMaster.add(itemType);
			}
		}
	}
	
	
	/**
	 * 
	 * @param random 
	 * @param chest - the chest you want to add monies to
	 * @param element - 0 = fire, 1 = water, 2 = earth, 3 = air
	 * @param fortune - int value from 1 - 5; 1 is low fortune, 5 is high fortune.
	 */
	
	public static void generateRandomMoney(Random random, TileEntityChest chest, int element, int fortune){
		
		if(chest.getSizeInventory() == 27){
			//i.e. if it's a single chest  
			switch(fortune){
			case 1:
				for(int i = 0; i < 2; i++){
					int slot = random.nextInt(27);
					if(random.nextInt(5) == 1){
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireBronzeItem, random.nextInt(3)));
					}
				}
				break;
			case 2:
				for(int i = 0; i < 4; i++){
					int slot = random.nextInt(27);
					if(random.nextInt(14) == 1){
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireSilverItem, random.nextInt(2)));
					}
					else{
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireBronzeItem, random.nextInt(6)));
					}
				}
				break;
			case 3:
				for(int i = 0; i < 6; i++){
					int slot = random.nextInt(27);
					if(random.nextInt(9) == 1){
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireSilverItem, random.nextInt(4)));
					}
					else{
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireBronzeItem, random.nextInt(10)));
					}
				}
				break;
			case 4:
				for(int i = 0; i < 8; i++){
					int slot = random.nextInt(27);
					if(random.nextInt(9) == 1){
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireGoldItem, random.nextInt(1)));
					}
					if(random.nextInt(3) == 1){
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireSilverItem, random.nextInt(6)));
					}
					else{
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireBronzeItem, random.nextInt(15)));
					}
				}
				break;
			case 5:
				for(int i = 0; i < 10; i++){
					int slot = random.nextInt(27);
					if(random.nextInt(3) == 1){
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireGoldItem, random.nextInt(3)));
					}
					if(random.nextInt(1) == 1){
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireSilverItem, random.nextInt(8)));
					}
					else{
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireBronzeItem, random.nextInt(25)));
					}
				}
				break;
			}
		}
		else{ //If the chest is double, do everything again but generate a slot in 54, not 27, also everything has a slightly bigger chance of spawning.
			switch(fortune){
			case 1:
				for(int i = 0; i < 2; i++){
					int slot = random.nextInt(54);
					if(random.nextInt(19) == 1){
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireSilverItem, random.nextInt(2)));
					}
					else{
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireBronzeItem, random.nextInt(10)));
					}
				}
				break;
			case 2:
				for(int i = 0; i < 4; i++){
					int slot = random.nextInt(54);
					if(random.nextInt(13) == 1){
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireSilverItem, random.nextInt(4)));
					}
					else{
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireBronzeItem, random.nextInt(15)));
					}
				}
				break;
			case 3:
				for(int i = 0; i < 6; i++){
					int slot = random.nextInt(54);
					if(random.nextInt(8) == 1){
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireSilverItem, random.nextInt(6)));
					}
					else{
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireBronzeItem, random.nextInt(20)));
					}
				}
				break;
			case 4:
				for(int i = 0; i < 8; i++){
					int slot = random.nextInt(54);
					if(random.nextInt(8) == 1){
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireGoldItem, random.nextInt(2)));
					}
					if(random.nextInt(2) == 1){
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireSilverItem, random.nextInt(8)));
					}
					else{
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireBronzeItem, random.nextInt(30)));
					}
				}
				break;
			case 5:
				for(int i = 0; i < 10; i++){
					int slot = random.nextInt(54);
					if(random.nextInt(2) == 1){
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireGoldItem, random.nextInt(5)));
					}
					else{
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireSilverItem, random.nextInt(10)));
						chest.setInventorySlotContents(slot, new ItemStack(mod_Avatar.MoneyFireBronzeItem, random.nextInt(50)));
					}
				}
				break;
			}

		}

	}
	
	
	
}
