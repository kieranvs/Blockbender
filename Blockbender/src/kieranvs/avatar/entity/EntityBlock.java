package kieranvs.avatar.entity;

import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBlock extends Entity {
	
	public int metadata;
	public boolean shouldDropItem;
	public NBTTagCompound nbt;
	
	public int state = 0;
	private float magicangle = 0;
	private String ownerstr = "";
	public Entity owner;

	public EntityBlock(World par1World){
		super(par1World);
		this.shouldDropItem = true;
	}

	public EntityBlock(World par1, double par2, double par3, double par4, Block par5){
		this(par1, par2, par3, par4, par5, 0);
	}

	public EntityBlock(World world, double posX, double posY, double posZ, Block block, int meta){
		super(world);
		this.setImitatingBlock(block);
		this.shouldDropItem = true;
		this.metadata = meta;
		this.preventEntitySpawning = true;
		this.setSize(0.98F, 0.98F);
		this.setPosition(posX, posY, posZ);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = posX;
		this.prevPosY = posY;
		this.prevPosZ = posZ;
	}
	
	@Override
	protected void entityInit() {
		this.dataWatcher.addObject(19, Integer.valueOf(0));
		this.dataWatcher.addObject(20, "");
	}
	
	public void setOwner(Entity owner){
		this.owner = owner;
		this.ownerstr = owner.getUniqueID().toString();
		this.dataWatcher.updateObject(20, ownerstr);
	}
	
	public void setOwner(String newowner){
		for(Object o : this.worldObj.loadedEntityList){
			Entity e = (Entity) o;
			if(e.getUniqueID().toString().equals(newowner)){
				this.setOwner(e);
				return;
			}
		}
		return;
	}
	
	public Entity getOwner(){
		if(this.ownerstr.equals(this.dataWatcher.getWatchableObjectString(20))){
			return owner;
		}
		else{
			this.ownerstr = this.dataWatcher.getWatchableObjectString(20);
			for(Object o : this.worldObj.loadedEntityList){
				Entity e = (Entity) o;
				if(e.getUniqueID().toString().equals(ownerstr)){
					this.owner = e;
					return owner;
				}
			}
			return null;
		}
	}
	
	protected boolean canTriggerWalking(){
		return false;
	}

	public boolean canBeCollidedWith(){
		return !this.isDead;
	}

	@Override
	public void onUpdate(){
		magicangle += 0.1f;
		if (getImitatingBlock().getMaterial() == Material.air){
			this.setDead();
		}
		else{
			this.prevPosX = this.posX;
			this.prevPosY = this.posY;
			this.prevPosZ = this.posZ;
		}
	}

	protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound){
		par1NBTTagCompound.setByte("Tile", (byte)Block.getIdFromBlock(getImitatingBlock()));
		par1NBTTagCompound.setInteger("TileID", Block.getIdFromBlock(getImitatingBlock()));
		par1NBTTagCompound.setByte("Data", (byte)this.metadata);
		par1NBTTagCompound.setBoolean("DropItem", this.shouldDropItem);
		par1NBTTagCompound.setString("Owner", this.dataWatcher.getWatchableObjectString(20));

		if (this.nbt != null){
			par1NBTTagCompound.setTag("TileEntityData", this.nbt);
		}
	}

	protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound){
		if (par1NBTTagCompound.hasKey("TileID", 99)){
			setImitatingBlock(Block.getBlockById(par1NBTTagCompound.getInteger("TileID")));
		}
		else{
			setImitatingBlock(Block.getBlockById(par1NBTTagCompound.getByte("Tile") & 255));
		}
		
		if(par1NBTTagCompound.hasKey("Owner")){
			this.setOwner(par1NBTTagCompound.getString("Owner"));
		}
			
		this.metadata = par1NBTTagCompound.getByte("Data") & 255;

		if (par1NBTTagCompound.hasKey("DropItem", 99)){
			this.shouldDropItem = par1NBTTagCompound.getBoolean("DropItem");
		}

		if (par1NBTTagCompound.hasKey("TileEntityData", 10)){
			this.nbt = par1NBTTagCompound.getCompoundTag("TileEntityData");
		}
	}

	public Block getImitatingBlock(){
		return Block.getBlockById(this.dataWatcher.getWatchableObjectInt(19));
	}

	public void setImitatingBlock(Block blockType){
		this.dataWatcher.updateObject(19, Block.getIdFromBlock(blockType));
	}
}