package kieranvs.avatar.entity;

import kieranvs.avatar.util.FileLocation;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityArcherBandit extends EntityMob implements IRangedAttackMob {

	private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 40, 60, 15.0F);
	public ResourceLocation res;
	
	public EntityArcherBandit(World world) {
		super(world);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, this.aiArrowAttack);
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityFireBandit.class, 0, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityWaterBandit.class, 0, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityEarthBandit.class, 0, false));
		this.targetTasks.addTask(3,  new EntityAINearestAttackableTarget(this, EntityBandit.class, 0, false));
		
		res = new ResourceLocation(FileLocation.ENTITYTEXTURE + "ArcherBandit.png");
		this.setCurrentItemOrArmor(0, new ItemStack(Items.bow));	

	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4D);
	}	
	
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_) {
		EntityArrow entityarrow = new EntityArrow(this.worldObj, this, p_82196_1_, 1.6F, (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
        int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
        int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());
        entityarrow.setDamage((double)(p_82196_2_ * 2.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.difficultySetting.getDifficultyId() * 0.11F));

        if (i > 0)
        {
            entityarrow.setDamage(entityarrow.getDamage() + (double)i * 0.5D + 0.5D);
        }

        if (j > 0)
        {
            entityarrow.setKnockbackStrength(j);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem()) > 0)
        {
            entityarrow.setFire(100);
        }

        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(entityarrow);
	}
	
	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEFINED;
	}

}
