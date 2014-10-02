package kieranvs.avatar.bending;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kieranvs.avatar.entity.EntityBandit;
import kieranvs.avatar.util.Messaging;
import kieranvs.avatar.util.StringColour;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class ElementManager {

	public static int FIRE = 0;
	public static int EARTH = 1;
	public static int WATER = 2;
	public static int AIR = 3;
	public static int AVATAR = 9;

	private static Vector<String> Firebenders = new Vector<String>();
	private static Vector<String> Earthbenders = new Vector<String>();
	private static Vector<String> Waterbenders = new Vector<String>();
	private static Vector<String> Airbenders = new Vector<String>();
	
	private static boolean loaded = false;
	private static String lastLoadFile = "";

	public static void setElement(EntityPlayer player, int element) {
		loadIfNecessary();
		removeBending(player);
		if (element == EARTH) {
			Earthbenders.add(player.getDisplayName());
			Messaging.avatarPublicMessage(player.getDisplayName() + " is now an " + StringColour.DARKGREEN + "Earthbender.");
			Messaging.avatarMessage(player, StringColour.ITALIC + StringColour.GRAY + "Earth is the element of substance. The people of");
			Messaging.avatarMessage(player, StringColour.ITALIC + StringColour.GRAY + "the earth kingdom are diverse and strong. They ");
			Messaging.avatarMessage(player, StringColour.ITALIC + StringColour.GRAY + "are persistent and enduring. - Iroh");
			Messaging.avatarMessage(player, StringColour.DARKGREEN + "Earthbending provides a balance of attack and");
			Messaging.avatarMessage(player, StringColour.DARKGREEN + "defense moves.");
		}
		if (element == WATER) {
			Waterbenders.add(player.getDisplayName());
			Messaging.avatarPublicMessage(player.getDisplayName() + " is now a " + StringColour.BLUE + "Waterbender.");
			Messaging.avatarMessage(player, StringColour.ITALIC + StringColour.GRAY + "Water is the element of change. The people of the");
			Messaging.avatarMessage(player, StringColour.ITALIC + StringColour.GRAY + "water tribe are capable of adapting to many things.");
			Messaging.avatarMessage(player, StringColour.ITALIC + StringColour.GRAY + "They have a deep sense of community and love");
			Messaging.avatarMessage(player, StringColour.ITALIC + StringColour.GRAY + "that holds them together through anything. - Iroh");
			Messaging.avatarMessage(player, StringColour.BLUE + "Waterbending provides a balance of attack and");
			Messaging.avatarMessage(player, StringColour.BLUE + "defense moves.");
		}
		if (element == FIRE) {
			Firebenders.add(player.getDisplayName());
			Messaging.avatarPublicMessage(player.getDisplayName() + " is now a " + StringColour.DARKRED + "Firebender.");
			Messaging.avatarMessage(player, StringColour.ITALIC + StringColour.GRAY + "Fire is the element of power. The people of the fire");
			Messaging.avatarMessage(player, StringColour.ITALIC + StringColour.GRAY + "nation have desire and will, and the energy and");
			Messaging.avatarMessage(player, StringColour.ITALIC + StringColour.GRAY + "drive to achieve what they want. - Iroh");
			Messaging.avatarMessage(player, StringColour.DARKRED + "Firebending provides a strong set of attack moves.");
			Messaging.avatarMessage(player, StringColour.DARKRED + "A successful firebender must learn to control");
			Messaging.avatarMessage(player, StringColour.DARKRED + "his bending or risk damaging himself.");
		}
		if (element == AIR) {
			Airbenders.add(player.getDisplayName());
			Messaging.avatarPublicMessage(player.getDisplayName() + " is now an " + StringColour.DARKAQUA + "Airbender.");
			Messaging.avatarMessage(player, StringColour.ITALIC + StringColour.GRAY + "Air is the element of freedom. The air nomads");
			Messaging.avatarMessage(player, StringColour.ITALIC + StringColour.GRAY + "detached themselves from worldly concerns, and");
			Messaging.avatarMessage(player, StringColour.ITALIC + StringColour.GRAY + "found peace and freedom. - Iroh");
			Messaging.avatarMessage(player, StringColour.DARKAQUA + "Airbending provides a mainly defensive moves.");
			Messaging.avatarMessage(player, StringColour.DARKAQUA + "Typical airbender tactics are to avoid and");
			Messaging.avatarMessage(player, StringColour.DARKAQUA + "evade, or risk taking heavy damage from opponents.");
		}
		if (element == AVATAR){
			Airbenders.add(player.getDisplayName());
			Firebenders.add(player.getDisplayName());
			Waterbenders.add(player.getDisplayName());
			Earthbenders.add(player.getDisplayName());
			Messaging.avatarPublicMessage(player.getDisplayName() + " is now an " + StringColour.GOLD + "Avatar.");
			Messaging.avatarMessage(player, StringColour.ITALIC + StringColour.GRAY + "My grandmother used to tell me stories about the old");
			Messaging.avatarMessage(player, StringColour.ITALIC + StringColour.GRAY + "days, a time of peace when the Avatar kept balance");
			Messaging.avatarMessage(player, StringColour.ITALIC + StringColour.GRAY + "between the Water Tribes, Earth Kingdom, Fire Nation,");
			Messaging.avatarMessage(player, StringColour.ITALIC + StringColour.GRAY + "and Air Nomads. But that all changed when the Fire");
			Messaging.avatarMessage(player, StringColour.ITALIC + StringColour.GRAY + "Nation attacked. Only the Avatar mastered all four");
			Messaging.avatarMessage(player, StringColour.ITALIC + StringColour.GRAY + "elements. Only he could stop the ruthless");
			Messaging.avatarMessage(player, StringColour.ITALIC + StringColour.GRAY + "firebenders. But when the world needed him most, he");
			Messaging.avatarMessage(player, StringColour.ITALIC + StringColour.GRAY + "vanished. - Katara");
		}
		save();
	}
	
	public static String getBendingFile(){
		if(FMLCommonHandler.instance().getSide() == Side.CLIENT){
			return "saves/" + FMLCommonHandler.instance().getMinecraftServerInstance().getFolderName() + "/bending";
		}
		else {
			return FMLCommonHandler.instance().getMinecraftServerInstance().getFolderName() + "/bending";
		}
	}
	
	public static void loadIfNecessary(){
		if(!lastLoadFile.equals(getBendingFile()) || !loaded){
			load();
		}
	}
	
	public static void load(){
		Firebenders.clear();
		Waterbenders.clear();
		Airbenders.clear();
		Earthbenders.clear();
		File cf = new File(getBendingFile());
		try {
			cf.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new RuntimeException("Bending file error");
		}
		
		BufferedReader fstream = null;
		try {
			fstream = new BufferedReader(new FileReader(getBendingFile()));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Bending file error");
		}
		
		try {
			int activeElement = -1;
			while(true){
				String line = fstream.readLine();
				if(line == null){
					break;
				}
				if(line.equals("FIRE")){
					activeElement = FIRE;
				}
				else if(line.equals("WATER")){
					activeElement = WATER;
				}
				else if(line.equals("EARTH")){
					activeElement = EARTH;
				}
				else if(line.equals("AIR")){
					activeElement = AIR;
				}
				else {
					if(activeElement == FIRE){
						Firebenders.add(line);
					}
					else if(activeElement == WATER){
						Waterbenders.add(line);
					}
					else if(activeElement == EARTH){
						Earthbenders.add(line);
					}
					else if(activeElement == AIR){
						Airbenders.add(line);
					}
				}
			}			
			fstream.close();
		} catch (IOException e) {
			throw new RuntimeException("Bending file error");
		}
		loaded = true;
		lastLoadFile = getBendingFile();
	}
	
	public static void save(){
		loadIfNecessary();
		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream(getBendingFile());
		} catch (FileNotFoundException e) {
			System.exit(-1);
		}
		PrintStream fstream = new PrintStream(fout);
		fstream.println("FIRE");
		for(String s : Firebenders){
			fstream.println(s);
		}
		fstream.println("AIR");
		for(String s : Airbenders){
			fstream.println(s);
		}
		fstream.println("WATER");
		for(String s : Waterbenders){
			fstream.println(s);
		}
		fstream.println("EARTH");
		for(String s : Earthbenders){
			fstream.println(s);
		}
		fstream.close();
	}
		
	public static boolean hasElement(EntityLivingBase user, int element){
		loadIfNecessary();
		if(user instanceof EntityBandit){
			return true;
		}
		if(user instanceof EntityPlayer){
			return hasElement((EntityPlayer) user, element);
		}
		return false;
	}

	public static boolean hasElement(EntityPlayer player, int element) {
		loadIfNecessary();
		if (element == EARTH && Earthbenders.contains(player.getDisplayName()))
			return true;
		if (element == FIRE && Firebenders.contains(player.getDisplayName()))
			return true;
		if (element == WATER && Waterbenders.contains(player.getDisplayName()))
			return true;
		if (element == AIR && Airbenders.contains(player.getDisplayName()))
			return true;
		if (element == AVATAR && Airbenders.contains(player.getDisplayName()) && Waterbenders.contains(player.getDisplayName()) && Firebenders.contains(player.getDisplayName()) && Earthbenders.contains(player.getDisplayName()))
			return true;
		return false;
	}

	private static void removeBending(EntityPlayer player) {
		loadIfNecessary();
		for (String s : Firebenders) {
			if (s.equalsIgnoreCase(player.getDisplayName())) {
				Firebenders.remove(s);
				break;
			}
		}
		for (String s : Waterbenders) {
			if (s.equalsIgnoreCase(player.getDisplayName())) {
				Waterbenders.remove(s);
				break;
			}
		}
		for (String s : Earthbenders) {
			if (s.equalsIgnoreCase(player.getDisplayName())) {
				Earthbenders.remove(s);
				break;
			}
		}
		for (String s : Airbenders) {
			if (s.equalsIgnoreCase(player.getDisplayName())) {
				Airbenders.remove(s);
				break;
			}
		}
	}

}
