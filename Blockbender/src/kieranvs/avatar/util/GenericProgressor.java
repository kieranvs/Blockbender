package kieranvs.avatar.util;


import net.minecraft.entity.EntityLivingBase;
import kieranvs.avatar.bending.Ability;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;

public class GenericProgressor extends Ability {
	
	private int interval = 20;
	private Location origin;
	private Location location;
	private Vector direction;
	private int range;
	private long time;
	
	private BlockActionPerformer action;

	public GenericProgressor(EntityLivingBase entity, Location location, Vector direction, int range, boolean flat, BlockActionPerformer action) {
		super(entity);
		this.time = System.currentTimeMillis();
		this.range = range;
		this.origin = location.clone();
		this.location = location.clone();
		this.direction = direction.clone();
		if(flat) this.direction.setY(0);
		this.direction.normalize();
		this.location.add(this.direction);
		this.action = action;
	}

	@Override
	public void update() {
		if (location.distance(origin) > range) {
			destroy();
			return;
		}
		if (System.currentTimeMillis() > time + interval) {
			time = System.currentTimeMillis();
			action.performAction(location.getBlock());
			location.add(direction);
		}
	}

}
