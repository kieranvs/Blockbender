package kieranvs.avatar.bending;

import java.util.ArrayList;
import java.util.Vector;

import net.minecraft.entity.EntityLivingBase;

public abstract class AsynchronousAbility extends Ability {
	
	private long cooldowntime;
	
	public AsynchronousAbility(EntityLivingBase e, long cooldowntime){
		super(e);
		this.cooldowntime = cooldowntime;
	}

	@Override
	public void destroy() {
		super.destroy();
		Ability.registerNextUseTime(this.user, this.getClass(), System.currentTimeMillis() + this.cooldowntime);
	}
	
}
