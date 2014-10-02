package kieranvs.avatar.generation;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenSeaPlants implements IWorldGenerator {
	
	private Block block;
	
	public WorldGenSeaPlants(Block block){
		this.block = block;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

		int x = (chunkX * 16) + random.nextInt(16);
		int y = 100;
		int z = (chunkZ * 16) + random.nextInt(16);

		boolean foundWater = false;
		boolean keepGoing = true;

		while (keepGoing) {
			y--;
			if (world.getBlock(x, y, z) == Blocks.water) {
				y--;
				if(world.getBlock(x, y, z) == Blocks.water){
					foundWater = true;
					keepGoing = false;
				}
			}
			else if (world.getBlock(x, y, z) == Blocks.bedrock) {
				keepGoing = false;
				foundWater = false;
				return;
			}
			if (y < 10)
				return;
		}

		while (foundWater) {
			y--;
			if (world.getBlock(x, y, z) != Blocks.water) {
				y++;
				world.setBlock(x, y, z, block);
				foundWater = false;
			}
		}
	}
}
