package kieranvs.avatar.entity;

import kieranvs.avatar.ai.EntityAIAttackWithBending;
import kieranvs.avatar.util.FileLocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveIndoors;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import kieranvs.avatar.bending.Ability;
import kieranvs.avatar.bending.water.WaterIceBridge;
import kieranvs.avatar.bukkit.Location;

public class EntityBandit extends EntityMob {
	
	public ResourceLocation res;
	
	public EntityBandit(World par1World) {
		super(par1World);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 0.7F, false));	
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityFireBandit.class, 0, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWaterBandit.class, 0, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityEarthBandit.class, 0, false));
		this.targetTasks.addTask(3,  new EntityAINearestAttackableTarget(this, EntityArcherBandit.class, 0, false));
		
		res = new ResourceLocation(FileLocation.ENTITYTEXTURE + "WarriorBandit.png");
		this.setCurrentItemOrArmor(0, new ItemStack(Items.iron_sword));	
	}
		
	
	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0D);
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
