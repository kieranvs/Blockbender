package kieranvs.avatar.generation;

import java.util.List;

import kieranvs.avatar.mod_Avatar;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderSimple implements IChunkProvider {
	
	private World worldObj;
	
	public ChunkProviderSimple(World world){
		this.worldObj = world;
	}

	@Override
	public boolean chunkExists(int i, int j){
		return true;
	}

	@Override
	public Chunk provideChunk(int i, int j){
		Chunk c = new Chunk(worldObj, i, j);
		for(int x = 0; x<16; x++){
			for(int z = 0; z<16; z++){
				for(int y = 0; y<41; y++){
					c.func_150807_a(x, y, z, Blocks.stone, 0); //setBlockIDWithMetadata
				}
				for(int y = 40; y<65; y++){
					c.func_150807_a(x, y, z, Blocks.dirt, 0); //setBlockIDWithMetadata				
				}
				c.func_150807_a(x, 65, z, mod_Avatar.SpiritGrassBlock, 0); //setBlockIDWithMetadata
			}
		}
		return c;
	}
	
	@Override
	public Chunk loadChunk(int i, int j){
		return this.provideChunk(i, j);
	}

	@Override
	public void populate(IChunkProvider ichunkprovider, int i, int j){
	}

	@Override
	public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate){
		return true;
	}

	@Override
	public boolean unloadQueuedChunks(){
		return false;
	}

	@Override
	public boolean canSave(){
		return true;
	}

	@Override
	public String makeString(){
		return "sup";
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType enumcreaturetype, int i, int j, int k){
		return null;
	}

	/*findClosestStructure*/
	@Override
	public ChunkPosition func_147416_a(World world, String s, int i, int j, int k){
		return null;
	}
	
	@Override
	public int getLoadedChunkCount(){
		return 0;
	}

	@Override
	public void recreateStructures(int i, int j){
	}

	@Override
	public void saveExtraData(){
	}

}
