package kieranvs.avatar.entity;

import kieranvs.avatar.util.FileLocation;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityPolarBearDog extends EntityFamiliar {


	public EntityPolarBearDog(World par1World) {
		super(par1World);
		this.setSize(2F, 2.4F);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.3F, false));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 10F));
		this.tasks.addTask(4, new EntityAIWander(this, 0.35D));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));

	}
	
	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(80D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4D);
	}
	
	@Override
	public void moveEntityWithHeading(float strafeMovement, float forwardMovement){
        if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase){
            this.prevRotationYaw = this.rotationYaw = this.riddenByEntity.rotationYaw;
            this.rotationPitch = this.riddenByEntity.rotationPitch * 0.5F;
            this.setRotation(this.rotationYaw, this.rotationPitch);
            this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
            strafeMovement = ((EntityLivingBase)this.riddenByEntity).moveStrafing * 0.5F;
            forwardMovement = ((EntityLivingBase)this.riddenByEntity).moveForward;

            if (forwardMovement <= 0.0F)
            {
                forwardMovement *= 0.25F;
            }

            this.stepHeight = 1.0F;

            if (!this.worldObj.isRemote){
                this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue());
                super.moveEntityWithHeading(strafeMovement, forwardMovement);
            }
            
        }
		
	}

	@Override
	protected String getLivingSound() {
		return "mob.wolf.bark";
	}

	@Override
	protected String getHurtSound() {
		return "mob.wolf.hurt";
	}

	@Override
	protected String getDeathSound() {
		return "mob.wolf.death";
	}

	@Override
	protected void func_145780_a(int par1, int par2, int par3, Block par4) {
		this.worldObj.playSoundAtEntity(this, "mob.wolf.step", 0.15F, 1.0F);
	}

	@Override
	protected Item getDropItem() {
		return Items.bone;
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	public int getTotalArmourValue() {
		return 4;
	}

	@Override
	public void receivedMessage(String message) {

	}

	@Override
	public Item getTameItem() {
		return Items.cooked_fished;
	}

}
