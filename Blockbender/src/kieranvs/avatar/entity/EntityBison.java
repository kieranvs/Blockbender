package kieranvs.avatar.entity;

import java.util.List;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import kieranvs.avatar.KeyBind;
import kieranvs.avatar.util.FileLocation;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIControlledByPlayer;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityBison extends EntityFamiliar {

	public float tailAngle = 0f;
	private boolean isTailOnWayUp = true;

	private final float maxSpeed;
	private float currentSpeed;

	private double moveSpeedAir = 1.5;
	public float moveSpeedAirVert = 0;
	private float yawAdd;
	private int yawSpeed = 30;

	public EntityBison(World par1World) {
		super(par1World);
		this.getNavigator().setAvoidsWater(true);
		this.setSize(3F, 4.5F);
		this.maxSpeed = (float)getEntityAttribute(SharedMonsterAttributes.movementSpeed).getBaseValue();
		this.currentSpeed = 0.0F;
	}

	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
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
                this.setAIMoveSpeed((float) 0.25);
                super.moveEntityWithHeading(strafeMovement, forwardMovement);
            }
        }
	}


	@Override
	public void onUpdate() {
		super.onUpdate();

	}

	public void setMoveSpeedAir(double moveSpeedAir){
		this.moveSpeedAir = moveSpeedAir;
	}

	public void setMoveSpeedAirVertical(float moveSpeedAirVert){
		this.moveSpeedAirVert = moveSpeedAirVert;
	}
	
	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	public Item getTameItem() {
		return Items.apple;
	}

	public int getTotalArmourValue() {
		return 8;
	}

	public int getAttackStrength(Entity par1Entity) {
		return 10;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

	@Override
	protected String getLivingSound() {
		return "avatarMobs.BisonRoar";
	}

	@Override
	protected String getHurtSound() {
		return "mob.cow.hurt";
	}

	@Override
	protected String getDeathSound() {
		return "avatarMobs.BisonDeath";
	}

	@Override
	protected void func_145780_a(int par1, int par2, int par3, Block par4) {
		this.worldObj.playSoundAtEntity(this, "mob.cow.step", 0.4F, 1.0F);
	}

	@Override
	protected Item getDropItem() {
		return this.isBurning() ? Items.cooked_beef : Items.beef;
	}

	@Override
	protected void dropRareDrop(int par1) {
		this.dropItem(Items.beef, 27);
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		this.dropItem(Items.beef, 27);
	}

	@Override
	public void receivedMessage(String message) {

	}

}
