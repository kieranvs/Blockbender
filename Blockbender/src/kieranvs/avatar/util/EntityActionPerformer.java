package kieranvs.avatar.util;

import kieranvs.avatar.bending.Ability;
import net.minecraft.entity.Entity;

public abstract class EntityActionPerformer {
	
	private Ability ability;
	
	public EntityActionPerformer(){
		this.ability = null;
	}
	
	public EntityActionPerformer(Ability a){
		this.ability = a;
	}
	
	public Ability getAbility(){
		return this.ability;
	}
	
	abstract public void performAction(Entity e);

}
