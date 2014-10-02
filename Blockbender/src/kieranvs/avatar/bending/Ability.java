package kieranvs.avatar.bending;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import kieranvs.avatar.util.StringColour;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public abstract class Ability {

	private boolean destroyed = false;
	public EntityLivingBase user;

	public static Vector<Ability> Instances = new Vector<Ability>();
	private static Vector<Ability> Queued = new Vector<Ability>();
	
	public static Vector<AbilityTCFix> clientInstances = new Vector<AbilityTCFix>();

	public static boolean disableCooldown = false;
	
	public Ability(EntityLivingBase user) {
		this.user = user;
		Queued.add(this);
	}

	public void destroy() {
		destroyed = true;
	}
	
	public static void updateTempClientFix(){
		for(AbilityTCFix a : clientInstances){
			a.updateClientSide();
		}
		
		synchronized (clientInstances) {
			Iterator<AbilityTCFix> i = clientInstances.iterator();
			while(i.hasNext()){
				AbilityTCFix a = i.next();
				if(((Ability) a).destroyed){
					i.remove();
				}
			}
		}
	}

	public static void updateAll() {
		synchronized (Instances) {
			Iterator<Ability> i = Instances.iterator();
			while (i.hasNext()) {
				Ability a = i.next();
				a.update();
				if (a.destroyed) {
					i.remove();
				}
			}
		}
		if(Instances.size() > 4000){
			for(int i = 0; i < 200; i++){
				Instances.get(i).destroy();
			}
		}
		if(!Queued.isEmpty()){
			for(int i = 0; i < Queued.size(); i++){
				Instances.add(Queued.get(i));
			}
			Queued.clear();
		}
	}
		
	public static boolean canStart(EntityLivingBase entity, Class<? extends Ability> type){
		if(disableCooldown){
			return true;
		}
		if(AsynchronousAbility.class.isAssignableFrom(type)){
			if(isInUse(entity, (Class<? extends AsynchronousAbility>) type)){
				if(entity instanceof EntityPlayer){
					((EntityPlayer)entity).addChatMessage(new ChatComponentText(StringColour.GRAY + StringColour.ITALIC + "You are still using this move!"));
				}
				return false;
			}
			long timetill = getTimeTillNextUse(entity, (Class<? extends AsynchronousAbility>) type);
			if(timetill <= 0){
				return true;
			}
			else{
				if(entity instanceof EntityPlayer){
					((EntityPlayer)entity).addChatMessage(new ChatComponentText(StringColour.GRAY + StringColour.ITALIC + "Wait " + (int)(timetill / 1000 + 1) + " seconds to reuse this move!"));
				}
				return false;
			}
		}
		else{
			return true;
		}
	}
	
	private static long getTimeTillNextUse(EntityLivingBase entity, Class<? extends AsynchronousAbility> type){
		if(cooldowns.containsKey(entity)){
			if(cooldowns.get(entity).containsKey(type)){
				long nexttime = cooldowns.get(entity).get(type).longValue();
				return nexttime - System.currentTimeMillis();
			}
			else{
				return -1;
			}
		}
		else{
			return -1;
		}
	}
	
	public static boolean isInUse(EntityLivingBase entity, Class<? extends AsynchronousAbility> type){
		for(Ability a : Instances){
			if(type.isInstance(a)){
				if(a.user.equals(entity)){
					return true;
				}
			}
		}
		return false;
	}
	
	public static void registerNextUseTime(EntityLivingBase entity, Class<? extends AsynchronousAbility> type, Long time){
		if(cooldowns.containsKey(entity)){
				cooldowns.get(entity).put(type, time);
		}
		else{
			cooldowns.put(entity, new HashMap<Class<? extends AsynchronousAbility>, Long>());
			cooldowns.get(entity).put(type, time);			
		}
	}
	
	private static HashMap<EntityLivingBase, HashMap<Class<? extends AsynchronousAbility>, Long>> cooldowns = new HashMap<EntityLivingBase, HashMap<Class<? extends AsynchronousAbility>, Long>>();
	
	public abstract void update();

}
