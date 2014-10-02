package kieranvs.avatar.client;

import java.io.File;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kieranvs.avatar.mod_Avatar;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundLoadEvent;

public class SoundHandler {

	public static void playOnEntity(String name, World world, Entity entity, float volume, float pitch){
		world.playSoundAtEntity(entity, ("kieranvs_avatar:" + name), (float)volume, (float)pitch); 
	}
	
	public static void playOnEntityVanilla(String name, World world, Entity entity, float volume, float pitch){
		world.playSoundAtEntity(entity, name, (float)volume, (float)pitch); 
	}

	
	public static void playOnBlock(String name, World world, double x, double y, double z, float volume, float pitch, boolean randomPitch){
		if(randomPitch){
			world.playSound(x + 0.5D, y + 0.5D, z + 0.5D, ("kieranvs_avatar:" + name), volume, world.rand.nextFloat() * 0.1F + 0.9F, false);
		}else{
			world.playSound(x + 0.5D, y + 0.5D, z + 0.5D, ("kieranvs_avatar:" + name), volume, pitch, false);
		}
	}
	
	public static void playOnEntityRandom(World world, Entity entity, float vol, float ptc, String... names){
		int i = world.rand.nextInt(names.length);
		playOnEntity(names[i], world, entity, vol, ptc);
	}
	
	public static void playOnBlockRandom(World world, double x, double y, double z, float volume, float pitch, boolean randomPitch, String... names){
		int i = world.rand.nextInt(names.length);
		playOnBlock(names[i], world, x, y, z, volume, pitch, randomPitch);
	}
	
	@SubscribeEvent
	public void onSound(SoundLoadEvent event) {
		
	}

}
