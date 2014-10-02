package kieranvs.avatar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import kieranvs.avatar.bending.Ability;
import kieranvs.avatar.bending.AbilityTCFix;
import kieranvs.avatar.util.Messaging;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class Protection {

	private static ArrayList<ProtectionRegion> regions = new ArrayList<ProtectionRegion>();
	private static boolean isLoaded = false;

	public static void addRegion(String worldName, String name, int pos1x, int pos1y, int pos1z, int pos2x, int pos2y, int pos2z, boolean bl, boolean bend){
		if(!isLoaded) loadRegions();
		regions.add(new ProtectionRegion(worldName, name, pos1x, pos1y, pos1z, pos2x, pos2y, pos2z, bl, bend));
		saveRegions();
	}
	
	public static void removeRegion(String name){
		if(!isLoaded) loadRegions();
		Iterator<ProtectionRegion> i = regions.iterator();
		while(i.hasNext()){
			ProtectionRegion r = i.next();
			if(r.name.equals(name)){
				i.remove();
			}
		}
		saveRegions();
	}
	
	public static boolean canBendHere(World world, int x, int y, int z){
		if(!isLoaded) loadRegions();
		for(ProtectionRegion r : regions){
			if(world.getWorldInfo().getWorldName() == r.worldName){
				if(r.contains(x, y, z) && r.cancelBending){
					System.out.println("bending cancelled due to region " + r.name);
					return false;
				}
			}
		}
		return true;
	}

	public static boolean canBlockChangeHere(World world, int x, int y, int z){
		if(!isLoaded) loadRegions();
		for(ProtectionRegion r : regions){
			if(world.getWorldInfo().getWorldName() == r.worldName){
				if(r.contains(x, y, z) && r.cancelBlockChanges){
					System.out.println("block change cancelled due to region " + r.name);
					return false;
				}
			}
		}
		return true;
	}

	public static void listRegions(EntityPlayer p){
		if(!isLoaded) loadRegions();
		Messaging.avatarMessage(p, "=======");
		for(ProtectionRegion r : regions){
			Messaging.avatarMessage(p, r.name);
		}
		Messaging.avatarMessage(p, "=======");
	}

	public static boolean trySetBlockAndMeta(World world, Block b, int x, int y, int z, int meta, int flag){
		if(canBlockChangeHere(world, x, y, z)){
			world.setBlock(x, y, z, b, meta, flag);
			return true;
		} else {
			return false;
		}
	}

	public static boolean trySetMeta(World world, int x, int y, int z, int meta, int flag){
		if(canBlockChangeHere(world, x, y, z)){
			world.setBlockMetadataWithNotify(x, y, z, meta, flag);
			return true;
		} else {
			return false;
		}

	}
	
	public static boolean trySetBlock(World world, Block b, int x, int y, int z){
		if(canBlockChangeHere(world, x, y, z)){
			world.setBlock(x, y, z, b);
			return true;
		} else {
			return false;
		}
	}
	
	public static void saveRegions(){
		
		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream(getRegionFile());
		} catch (FileNotFoundException e) {
			System.exit(-1);
		}
		PrintStream fstream = new PrintStream(fout);
		for(ProtectionRegion r: regions){
			fstream.println(r.worldName);
			fstream.println(r.name);
			fstream.println(r.pos1x);
			fstream.println(r.pos1y);
			fstream.println(r.pos1z);
			fstream.println(r.pos2x);
			fstream.println(r.pos2y);
			fstream.println(r.pos2z);
			fstream.println(r.cancelBlockChanges);
			fstream.println(r.cancelBending);
		}
		fstream.close();
		
	}
	
	public static void loadRegions(){
		File cf = new File(getRegionFile());
		try {
			cf.createNewFile();
		} catch (IOException e1) {
			throw new RuntimeException("Region file error");
		}
		
		BufferedReader fstream = null;
		try {
			fstream = new BufferedReader(new FileReader(getRegionFile()));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Region file error");
		}
		
		try {
			while(true){
				String worldName = fstream.readLine();
				if(worldName == null){
					break;
				}
				
				String name = fstream.readLine();
				int pos1x = Integer.valueOf(fstream.readLine());
				int pos1y = Integer.valueOf(fstream.readLine());
				int pos1z = Integer.valueOf(fstream.readLine());
				int pos2x = Integer.valueOf(fstream.readLine());
				int pos2y = Integer.valueOf(fstream.readLine());
				int pos2z = Integer.valueOf(fstream.readLine());
				boolean cbc = Boolean.valueOf(fstream.readLine());
				boolean cb = Boolean.valueOf(fstream.readLine());
				
				regions.add(new ProtectionRegion(worldName, name, pos1x, pos1y, pos1z, pos2x, pos2y, pos2z, cbc, cb));
			}			
			fstream.close();
		} catch (IOException e) {
			throw new RuntimeException("Region file error");
		}
		isLoaded = true;
	}
	
	public static String getRegionFile(){
		if(FMLCommonHandler.instance().getSide() == Side.CLIENT){
			return "saves/" + FMLCommonHandler.instance().getMinecraftServerInstance().getFolderName() + "/tlbregion";
		}
		else {
			return FMLCommonHandler.instance().getMinecraftServerInstance().getFolderName() + "/tlbregion";
		}
	}

}

class ProtectionRegion {

	String name, worldName;

	boolean cancelBlockChanges = false;
	boolean cancelBending = false;

	int pos1x, pos1y, pos1z, pos2x, pos2y, pos2z;

	public ProtectionRegion(String worldName, String name, int pos1x, int pos1y, int pos1z, int pos2x, int pos2y, int pos2z, boolean cancelBlockChanges, boolean cancelBending){
		this.cancelBlockChanges = cancelBlockChanges;
		this.cancelBending = cancelBending;

		this.worldName = worldName;
		this.name = name;
		this.pos1x = pos1x;
		this.pos1y = pos1y;
		this.pos1z = pos1z;
		this.pos2x = pos2x;
		this.pos2y = pos2y;
		this.pos2z = pos2z;
	}

	public boolean contains(int x, int y, int z){
		return (between(x, this.pos1x, this.pos2x) && between(y, this.pos1y, this.pos2y) && between(z, this.pos1z, this.pos2z));
	}

	private static boolean between(int x, int a, int b){
		return x <= Math.max(a,b) && x >= Math.min(a, b);
	}

}

