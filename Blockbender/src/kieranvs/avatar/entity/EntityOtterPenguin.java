package kieranvs.avatar.entity;

import kieranvs.avatar.util.FileLocation;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityOtterPenguin extends EntityMob {

	public EntityOtterPenguin(World par1World) {
		super(par1World);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.3F, false));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 10F));
		this.tasks.addTask(3, new EntityAIWander(this, 0.3D));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
	}
	
	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(6D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4D);
	}


	public int getTotalArmourValue() {
		return 2;
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	@Override
	protected String getLivingSound() {
		return "avatarMobs.OtterPenguinSay";
	}

	@Override
	protected String getHurtSound() {
		return null;
	}

	@Override
	protected String getDeathSound() {
		return null;
	}

	@Override
	protected void func_145780_a(int par1, int par2, int par3, Block par4) {
	}

}
