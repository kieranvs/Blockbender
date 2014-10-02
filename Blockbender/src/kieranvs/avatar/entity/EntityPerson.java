package kieranvs.avatar.entity;

import kieranvs.avatar.ai.EntityAIWanderInTown;
import kieranvs.avatar.util.FileLocation;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class EntityPerson extends EntityMob {
	
	public ResourceLocation res;
	private String textureString;

	public int radiusOfTown;
	public int xOrigin = 0;
	public int zOrigin = 0;
	public int yOrigin = 0;
	
	@Deprecated
	public EntityPerson(World par1World) {
		this(par1World, 0, 0, 0, 1);
	}

	@Deprecated
	public EntityPerson(World par1World, int xOrigin, int yOrigin, int zOrigin, int radiusOfTown) {
		super(par1World);
		this.radiusOfTown = radiusOfTown;
		this.xOrigin = xOrigin;
		this.zOrigin = zOrigin;
		this.yOrigin = yOrigin;
		setupTasks();
		this.textureString = getTextureLocation();
		this.res = new ResourceLocation(textureString);
	}
	
	abstract public String getTextureLocation();
	
	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5D);
	}

	public int getTotalArmourValue() {
		return 4;
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
		return null;
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
		return;
	}

	@Override
	protected Item getDropItem() {
		return null;
	}

	@Override
	protected void dropRareDrop(int par1) {
		return;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		return;
	}

	@Override
	public boolean canDespawn() {
		return false;
	}

	abstract protected void setupTasks();

	protected void TemplateSetupBasic() {
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.3F, false));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 10F));
		this.tasks.addTask(3, new EntityAIWanderInTown(this, 1F));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("radius", this.radiusOfTown);
		par1NBTTagCompound.setInteger("xOrigin", this.xOrigin);
		par1NBTTagCompound.setInteger("yOrigin", this.yOrigin);
		par1NBTTagCompound.setInteger("zOrigin", this.zOrigin);
		par1NBTTagCompound.setString("textureString", this.textureString);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		this.radiusOfTown = par1NBTTagCompound.getInteger("radius");
		this.xOrigin = par1NBTTagCompound.getInteger("xOrigin");
		this.yOrigin = par1NBTTagCompound.getInteger("yOrigin");
		this.zOrigin = par1NBTTagCompound.getInteger("zOrigin");
		this.textureString = par1NBTTagCompound.getString("textureString");
	}
}
