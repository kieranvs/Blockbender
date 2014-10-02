package kieranvs.avatar.generation;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import kieranvs.avatar.mod_Avatar;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderSpiritWorld extends WorldProvider {
	
	Random r = new Random();
	int trippycounter = 0;

	@Override
	public void registerWorldChunkManager(){
		this.worldChunkMgr = new WorldChunkManagerSpiritWorld();
		this.dimensionId = mod_Avatar.dimensionIdSpiritWorld;
	}

	@Override
	public IChunkProvider createChunkGenerator(){
		return new ChunkProviderSimple(worldObj);
	}

	@Override
	public String getDimensionName() {
		return "Spirit World";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean getWorldHasVoidParticles(){
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3 drawClouds(float partialTicks){
		return Vec3.createVectorHelper(0.4D, 0.1D, 0.8D);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getStarBrightness(float par1){
		return super.getStarBrightness(par1 * 5);
	}

	@Override
	public void updateWeather(){
		super.updateWeather();
	}

	@Override
	public Vec3 getSkyColor(Entity cameraEntity, float partialTicks)
	{
		trippycounter++;
		double lol = Math.abs(Math.sin(trippycounter / 1000D));
		return Vec3.createVectorHelper(lol/2D, 0.2, Math.max(lol,0.4));
	}

	@Override
	public boolean isSkyColored()
	{
		return true;
	}

}
