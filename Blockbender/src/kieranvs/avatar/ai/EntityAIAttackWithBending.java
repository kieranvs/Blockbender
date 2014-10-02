package kieranvs.avatar.ai;

import kieranvs.avatar.bending.air.AirGust;
import kieranvs.avatar.bending.earth.EarthRockThrow;
import kieranvs.avatar.bending.fire.FireStream;
import kieranvs.avatar.bending.water.WaterIceBridge;
import kieranvs.avatar.bending.water.WaterIceStream;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.entity.EntityBandit;
import kieranvs.avatar.entity.EntityEarthBandit;
import kieranvs.avatar.entity.EntityFireBandit;
import kieranvs.avatar.entity.EntityWaterBandit;
import kieranvs.avatar.util.PacketSender;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIAttackWithBending extends EntityAIBase {

	private EntityLiving attacker;
	private EntityLivingBase entityTarget;
	private String element;
	private int countdown;

	public EntityAIAttackWithBending(EntityLiving user,  String element) { 
		this.attacker = user;
		this.element = element;
	}

	@Override
	public boolean shouldExecute() {
		EntityLivingBase entityliving = this.attacker.getAttackTarget();

		if (entityliving == null) {
			return false;
		} 
		else {
			this.entityTarget = entityliving;
			return true;
		}
	}

	@Override
	public boolean continueExecuting() {
		EntityLivingBase e = this.attacker.getAttackTarget();
		if(e == null){
			return false;
		}
		if(!this.entityTarget.isEntityAlive()){
			return false;
		} 
		if(this.attacker.getHealth() <= 3){ 
			backOff();
		}
		return true;
	}

	@Override
	public void startExecuting() {
		this.countdown = 0;
	}

	@Override
	public void resetTask() {
		if(this.attacker instanceof EntityFireBandit){
			EntityFireBandit guy = (EntityFireBandit) this.attacker;
			guy.setIsBending(false);
			PacketSender.sendIsBending(this.attacker, false, 0);
		}
		if(this.attacker instanceof EntityWaterBandit){
			EntityWaterBandit guy = (EntityWaterBandit) this.attacker;
			guy.setIsBending(false);
			PacketSender.sendIsBending(this.attacker, false, 1);
		}
		if(this.attacker instanceof EntityEarthBandit){
			EntityEarthBandit guy = (EntityEarthBandit) this.attacker;
			guy.setIsBending(false);
			PacketSender.sendIsBending(this.attacker, false, 2);
		}
		this.entityTarget = null;
		this.countdown = 0;
		this.attacker.getNavigator().clearPathEntity();
	}

	boolean tryingToMove = false;

	@Override
	public void updateTask() {

		Location lAttacker = new Location(this.attacker);
		Location lTarget = new Location(this.entityTarget);

		this.attacker.getLookHelper().setLookPositionWithEntity(this.entityTarget, 30.0F, 30.0F);

		this.countdown--;

		if(lAttacker.distance(lTarget) > 5){
			tryingToMove = false;
			this.attacker.getNavigator().tryMoveToEntityLiving(this.entityTarget, 1.0F);	
		}
		else{
			if(!tryingToMove){
				int x = (int) (this.entityTarget.posX + this.attacker.getRNG().nextGaussian());
				int z = (int) (this.entityTarget.posZ + this.attacker.getRNG().nextGaussian());
				tryingToMove = this.attacker.getNavigator().tryMoveToXYZ(x, this.entityTarget.worldObj.getHeightValue(x, z), z, 0.7f);
			}
			if(tryingToMove){
				if(this.attacker.getNavigator().noPath()){
					tryingToMove = false;
				}
			}
		}

		if ((this.attacker.getEntitySenses().canSee(this.entityTarget)) && this.countdown <= 0) {
			this.countdown = 50 + this.attacker.getRNG().nextInt(40);

			if(this.attacker instanceof EntityFireBandit){
				PacketSender.sendIsBending(this.attacker, true, 0);
				EntityFireBandit guy = (EntityFireBandit)this.attacker;
				guy.setIsBending(true);

				if(guy.isBending() == true){
					bend(element);
					guy.setIsBending(false);
				}
			} else if(this.attacker instanceof EntityWaterBandit){
				PacketSender.sendIsBending(this.attacker, true, 1);
				EntityWaterBandit guy = (EntityWaterBandit)this.attacker;
				guy.setIsBending(true);

				if(guy.isBending() == true){
					bend(element);
					guy.setIsBending(false);
				}
			}else if(this.attacker instanceof EntityEarthBandit){
				PacketSender.sendIsBending(this.attacker, true, 2);
				EntityEarthBandit guy = (EntityEarthBandit)this.attacker;
				guy.setIsBending(true);

				if(guy.isBending() == true){
					bend(element);
					guy.setIsBending(false);
				}
			} else {
				bend(element);

			}
		} 
		if(this.countdown <= 45 && this.countdown > 40){
			if(this.attacker instanceof EntityFireBandit){
				if(!((EntityFireBandit)this.attacker).isBending()){
					PacketSender.sendIsBending(this.attacker, false, 0);
				}
			} else if(this.attacker instanceof EntityWaterBandit){
				if(!((EntityWaterBandit)this.attacker).isBending()){
					PacketSender.sendIsBending(this.attacker, false, 1);
				}
			} else if(this.attacker instanceof EntityEarthBandit){
				if(!((EntityEarthBandit)this.attacker).isBending()){
					PacketSender.sendIsBending(this.attacker, false, 2);
				}
			}
		}


	}

	public boolean bend(String element){
		if(element.equals("WATER")){
			Location lAttacker = new Location(this.attacker);
			Location lTarget = new Location(this.entityTarget);
			new WaterIceStream(this.attacker, lAttacker, lAttacker.getDirectionTo(lTarget), 6, 4);
			return true;
		} else if(element.equals("EARTH")){
			new EarthRockThrow(this.attacker, 3000L, 0);
			return true;
		} else if(element.equals("FIRE")){
			Location lAttacker = new Location(this.attacker);
			Location lTarget = new Location(this.entityTarget);
			new FireStream(lAttacker, lAttacker.getDirectionTo(lTarget), 0, this.attacker);
			return true;
		} else if(element.equals("AIR")){
			Location lAttacker = new Location(this.attacker);
			Location lTarget = new Location(this.entityTarget);
			new AirGust(this.attacker, 3000L, 2, 6);
			return true;
		} 
		return false;
	}

	public void backOff(){
		int x = (int) (this.attacker.posX + this.attacker.getRNG().nextInt(40) - 20);
		int z = (int) (this.attacker.posZ + this.attacker.getRNG().nextInt(40) - 20);
		
		this.attacker.getNavigator().tryMoveToXYZ(x, this.entityTarget.worldObj.getHeightValue(x, z), z, 0.9f);
	}

	public void moveToLocation(Location l, float speed){
		this.attacker.getNavigator().tryMoveToXYZ(l.getX(), l.getY(), l.getZ(), speed);
	}

}
