package kieranvs.avatar.bending.air;

import kieranvs.avatar.Protection;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.AvatarDamageSource;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.EntityActionPerformer;
import kieranvs.avatar.util.PacketSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;

public class AirDisc extends AsynchronousAbility {

	private int radius;
	private long lifetime;
	private long time;
	private long interval = 20;
	private Location location;
	private Vector velocity;
	private int damage;
	private int metadata;


	public AirDisc(EntityLivingBase user, Long cooldown, Vector direction, int radius, long lifetime, int damage) {
		super(user, cooldown);
		this.radius = radius;
		this.damage = damage;
		this.lifetime = lifetime + System.currentTimeMillis();
		this.time = System.currentTimeMillis();
		this.velocity = direction.multiply(1);
		this.location = new Location(user);
		this.location.setY(this.location.getY() + 1.62);
	}

	@Override
	public void update() {
		if(System.currentTimeMillis() > lifetime){
			destroy();
			return;
		}
		if(System.currentTimeMillis() > time + interval){
			time = System.currentTimeMillis();
			location.add(velocity);
			if(!user.isSneaking()){
				PacketSender.sendDisc(location, radius, 1, 10, 0);
				BendingUtils.damageAllEntitiesWithCustomAction(user, location, 1, AvatarDamageSource.airbending, damage, new EntityActionPerformer(this){
					@Override
					public void performAction(Entity e) {
						this.getAbility().destroy();
					}

				});
				BendingUtils.affectLevers(user.worldObj, location.getBlock());
				BendingUtils.affectButtons(user.worldObj, location.getBlock());
				BendingUtils.douseFlames(user.worldObj, location.getBlock());
				if(location.getBlock().getType() == Blocks.leaves){
					user.worldObj.playSoundEffect((double)location.getX() + 0.5D, (double)location.getY() + 0.5D, (double)location.getZ() + 0.5D, "dig.grass", 1.0F, user.worldObj.rand.nextFloat() * 0.1F + 0.9F);
					Protection.trySetBlock(user.worldObj, Blocks.air, (int)location.getX(), (int)location.getY(), (int)location.getY());
				}
				if(location.getBlock().getType() == Blocks.log){
					metadata = user.worldObj.getBlockMetadata((int)location.getX(), (int)location.getY(), (int)location.getZ());
					if(Protection.trySetBlock(user.worldObj, Blocks.air, (int)location.getX(), (int)location.getY(), (int)location.getY())){
						Protection.trySetBlock(user.worldObj, Blocks.air, (int)location.getX(), (int)location.getY(), (int)location.getY());
						location.getBlock().getType().dropBlockAsItem(user.worldObj, (int)location.getX(), (int)location.getY(), (int)location.getZ(), 0, 0);
					}
				}
				if(location.getBlock().getType() == Blocks.lava){
					Protection.trySetBlock(user.worldObj, Blocks.obsidian, (int)location.getX(), (int)location.getY(), (int)location.getY());
					user.worldObj.playSoundEffect((double)location.getX() + 0.5D, (double)location.getY() + 0.5D, (double)location.getZ() + 0.5D, "random.fizz", 1.0F, user.worldObj.rand.nextFloat() * 0.1F + 0.9F);
				}
				if(location.getBlock().getType() == Blocks.flowing_lava){
					Protection.trySetBlock(user.worldObj, Blocks.cobblestone, (int)location.getX(), (int)location.getY(), (int)location.getY());
					user.worldObj.playSoundEffect((double)location.getX() + 0.5D, (double)location.getY() + 0.5D, (double)location.getZ() + 0.5D, "random.fizz", 1.0F, user.worldObj.rand.nextFloat() * 0.1F + 0.9F);
				}
				if(BendingUtils.isPlant(location.getBlock().getType())){
					if(Protection.trySetBlock(user.worldObj, Blocks.air, (int)location.getX(), (int)location.getY(), (int)location.getY())){
						if(BendingUtils.isDroppablePlant(location.getBlock().getType())){
							location.getBlock().getType().dropBlockAsItem(user.worldObj, (int)location.getX(), (int)location.getY(), (int)location.getZ(), 1, 0);
						}
						if(location.getBlock().getType() == Blocks.tallgrass){
							location.getBlock().getType().dropBlockAsItem(user.worldObj, (int)location.getX(), (int)location.getY(), (int)location.getZ(), 1, 0);
						}
						if(location.getBlock().getType() == Blocks.cactus){
							user.worldObj.playSoundEffect((double)location.getX() + 0.5D, (double)location.getY() + 0.5D, (double)location.getZ() + 0.5D, "dig.cloth", 1.0F, user.worldObj.rand.nextFloat() * 0.1F + 0.9F);
						}
						else{
							user.worldObj.playSoundEffect((double)location.getX() + 0.5D, (double)location.getY() + 0.5D, (double)location.getZ() + 0.5D, "dig.grass", 1.0F, user.worldObj.rand.nextFloat() * 0.1F + 0.9F);
						}
						Protection.trySetBlock(user.worldObj, Blocks.air, (int)location.getX(), (int)location.getY(), (int)location.getY());
					}
				}

			}
			else{
				destroy();
				return;
			}
		}
	}

}
