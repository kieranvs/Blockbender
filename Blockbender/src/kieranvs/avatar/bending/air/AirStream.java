package kieranvs.avatar.bending.air;

import kieranvs.avatar.Protection;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.AvatarDamageSource;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.EntityActionPerformer;
import kieranvs.avatar.util.PacketSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class AirStream extends AsynchronousAbility {

	private long interval = 20;
	private Location origin;
	private Location location;
	private Vector direction;
	private int range;
	private long time;
	private int strength;
	private int damage;
	private World worldObj;

	public AirStream(EntityLivingBase user, Long cooldown, Location location, Vector direction, int range, int strength, int damage) {
		super(user, cooldown);
		this.time = System.currentTimeMillis();
		this.range = range;
		this.origin = location.clone();
		this.location = location.clone();
		this.direction = direction.clone();
		this.direction.normalize();
		this.location.add(this.direction);
		this.strength = strength;
		this.damage = damage;
		this.worldObj = user.worldObj;

	}

	@Override
	public void update() {
		if (location.distance(origin) > range) {
			destroy();
			return;
		}
		if (System.currentTimeMillis() > time + interval) {
			if (location.getBlock().getType() == Blocks.air || location.getBlock().getRelative(BlockBukkit.UP).getType() == Blocks.air || BendingUtils.isDroppablePlant(location.getBlock().getType())) {
				time = System.currentTimeMillis();
				PacketSender.spawnParticle("Avatar_cloud", location.getWorld(), location.getX(), location.getY(), location.getZ(), 1, 0.1);
				if(damage == 0){
					BendingUtils.performCustomAction(user, location, 1F, new EntityActionPerformer(this){
						@Override
						public void performAction(Entity e) {
							if (!e.equals(this.getAbility().user)){
								PacketSender.sendVelocity(e, direction.clone().multiply(strength));
							}
						}
					});

				}
				else{
					BendingUtils.damageAllEntitiesWithCustomAction(user, location, 1F, AvatarDamageSource.airbending, damage, new EntityActionPerformer(this){
						@Override
						public void performAction(Entity e) {
							if (!e.equals(this.getAbility().user)){
								PacketSender.sendVelocity(e, direction.clone().multiply(strength));
							}
						}
					});
				}
				BendingUtils.affectLevers(worldObj, location.getBlock());
				BendingUtils.affectButtons(worldObj, location.getBlock());
				BendingUtils.douseFlames(worldObj, location.getBlock());
				if(location.getBlock().getType() == Blocks.lava){
					Protection.trySetBlock(user.worldObj, Blocks.obsidian, (int)location.getX(), (int)location.getY(), (int)location.getY());
					worldObj.playSoundEffect((double)location.getX() + 0.5D, (double)location.getY() + 0.5D, (double)location.getZ() + 0.5D, "random.fizz", 1.0F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
				}
				if(location.getBlock().getType() == Blocks.flowing_lava){
					Protection.trySetBlock(user.worldObj, Blocks.cobblestone, (int)location.getX(), (int)location.getY(), (int)location.getY());
					worldObj.playSoundEffect((double)location.getX() + 0.5D, (double)location.getY() + 0.5D, (double)location.getZ() + 0.5D, "random.fizz", 1.0F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
				}
				if(BendingUtils.isPlant(location.getBlock().getType())){
					if(Protection.trySetBlock(user.worldObj, Blocks.air, (int)location.getX(), (int)location.getY(), (int)location.getY())){
						if(BendingUtils.isDroppablePlant(location.getBlock().getType())){
							location.getBlock().getType().dropBlockAsItem(worldObj, (int)location.getX(), (int)location.getY(), (int)location.getZ(), 1, 0);
						}
						if(location.getBlock().getType() == Blocks.tallgrass){
							location.getBlock().getType().dropBlockAsItem(worldObj, (int)location.getX(), (int)location.getY(), (int)location.getZ(), 1, 0);
						}
						if(location.getBlock().getType() == Blocks.cactus){
							worldObj.playSoundEffect((double)location.getX() + 0.5D, (double)location.getY() + 0.5D, (double)location.getZ() + 0.5D, "dig.cloth", 1.0F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
						}
						else{
							worldObj.playSoundEffect((double)location.getX() + 0.5D, (double)location.getY() + 0.5D, (double)location.getZ() + 0.5D, "dig.grass", 1.0F, worldObj.rand.nextFloat() * 0.1F + 0.9F);
						}
						Protection.trySetBlock(user.worldObj, Blocks.air, (int)location.getX(), (int)location.getY(), (int)location.getY());
					}
				}

				location.add(direction);
			}
			else {
				destroy();
			}
		}
	}

}