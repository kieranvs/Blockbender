package kieranvs.avatar.entity;

import kieranvs.avatar.ai.EntityAIAttackWithBending;
import kieranvs.avatar.util.FileLocation;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityWaterBandit extends EntityMob {
	
	private boolean isBending;
	public ResourceLocation res;

	public EntityWaterBandit(World par1World) {
		super(par1World);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackWithBending(this, "WATER"));	
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityBandit.class, 0, false));
		this.targetTasks.addTask(3,  new EntityAINearestAttackableTarget(this, EntityArcherBandit.class, 0, false));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityFireBandit.class, 0, false));
		this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityEarthBandit.class, 0, false));
		
		res = new ResourceLocation(FileLocation.ENTITYTEXTURE + "WaterBandit.png");
	}
		
	
	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4D);
	}
	
	public void setIsBending(boolean bending){
		isBending = bending;
	}
	
	public boolean isBending(){
		return isBending;
	}


	public int getTotalArmourValue() {
		return 10;
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}
	
}
