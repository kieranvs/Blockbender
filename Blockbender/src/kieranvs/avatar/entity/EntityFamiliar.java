package kieranvs.avatar.entity;

import io.netty.buffer.Unpooled;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Random;

import kieranvs.avatar.mod_Avatar;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityFamiliar extends EntityCreature implements EntityChatListener {

	public String owner = "";
	public int mood = 0;
	private int healthlastsent = -1;
	private int moodlastsent = -1;
	private int moodrandom = 0;
	private long moodrandomtime = 0;

	public EntityFamiliar(World par1World) {
		super(par1World);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataWatcher.addObject(19, "");
	}

	public void setOwner(EntityPlayer owner) {
		if (getFamiliarByOwner(owner) == null) {
			this.owner = owner.getDisplayName();
			this.sendNameUpdate();
			this.sendHealthUpdate();
		}
		else {
			owner.addChatComponentMessage(new ChatComponentText("You already have a familiar."));
		}

	}

	public void setName(String name) {
		this.dataWatcher.updateObject(19, name);
		this.sendNameUpdate();
	}

	public String getName() {
		return this.dataWatcher.getWatchableObjectString(19);
	}

	@Override
	public void onDeath(DamageSource source) {
		super.onDeath(source);

		if (this.owner != null) {
			this.sendDeathUpdate();
		}

	}

	@Override
	public boolean interact(EntityPlayer player) {
		if (!this.worldObj.isRemote && (this.riddenByEntity == null || this.riddenByEntity == player)) {
			if (player.getHeldItem() != null && player.getHeldItem().getItem() == this.getTameItem()) {
				player.getHeldItem().stackSize--;
				Random r = this.getRNG();
				for (int var3 = 0; var3 < 7; ++var3) {
					double vx = r.nextGaussian() * 202D;
					double vy = r.nextGaussian() * 202D;
					double vz = r.nextGaussian() * 202D;
					this.worldObj.spawnParticle("heart", this.posX + r.nextFloat() * this.width * 2.0F - this.width, this.posY + 0.5D + r.nextFloat() * this.height, this.posZ + r.nextFloat() * this.width * 2.0F - this.width, vx, vy, vz);
				}
				this.setOwner(player);
				// this.setName("unnamed");
			}
			else {
				player.mountEntity(this);
			}
			return true;
		}
		return false;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (System.currentTimeMillis() - moodrandomtime > 1000) {
			moodrandomtime = System.currentTimeMillis();
			moodrandom = this.rand.nextInt(40) - 20;
		}

		updateMood();

		if ((int) this.getHealth() != healthlastsent)
			sendHealthUpdate();
		if (this.mood != moodlastsent)
			sendMoodUpdate();
	}

	public void updateMood() {
		if ((int) this.getHealth() < 20) {
			this.mood = 3;
			return;
		}
		int moodval = (int) this.getHealth();
		moodval += moodrandom;

		if (moodval < 50) {
			this.mood = 2;
			return;
		}
		if (moodval < 100) {
			this.mood = 0;
			return;
		}
		if (moodval < 2000) {
			this.mood = 1;
			return;
		}
		return;
	}

	@Override
	public boolean canDespawn() {
		return false;
	}

	public void sendNameUpdate() {
		String name = this.dataWatcher.getWatchableObjectString(19);
		if (owner != null && name != null) {
			EntityPlayer o = (EntityPlayer) this.worldObj.getPlayerEntityByName(owner);
			if (o != null) {
				PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
				try {
					pb.writeInt(1);
					pb.writeInt(name.getBytes().length);
					pb.writeBytes(name.getBytes());
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				S3FPacketCustomPayload packet = new S3FPacketCustomPayload("AvatarFam", pb);
				
				((EntityPlayerMP) o).playerNetServerHandler.sendPacket(packet);
			}
		}
	}

	public void sendDeathUpdate() {
		if (owner != null) {
			EntityPlayer o = (EntityPlayer) this.worldObj.getPlayerEntityByName(owner);
			if (o != null) {
				PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
				try {
					pb.writeInt(4);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				S3FPacketCustomPayload packet = new S3FPacketCustomPayload("AvatarFam", pb);
				
				((EntityPlayerMP) o).playerNetServerHandler.sendPacket(packet);
			}

		}
	}

	public void sendHealthUpdate() {
		if (owner != null) {
			EntityPlayer o = (EntityPlayer) this.worldObj.getPlayerEntityByName(owner);
			if (o != null) {
				PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
				try {
					pb.writeInt(2);
					pb.writeInt((int) this.getHealth());
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				S3FPacketCustomPayload packet = new S3FPacketCustomPayload("AvatarFam", pb);
				
				((EntityPlayerMP) o).playerNetServerHandler.sendPacket(packet);
				
				this.healthlastsent = (int) this.getHealth();
			}
		}
	}

	public void sendMoodUpdate() {
		if (owner != null) {
			EntityPlayer o = (EntityPlayer) this.worldObj.getPlayerEntityByName(owner);
			if (o != null) {
				PacketBuffer pb = new PacketBuffer(Unpooled.buffer());
				try {
					pb.writeInt(3);
					pb.writeInt(this.mood);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				S3FPacketCustomPayload packet = new S3FPacketCustomPayload("AvatarFam", pb);
				
				((EntityPlayerMP) o).playerNetServerHandler.sendPacket(packet);
				
				this.moodlastsent = this.mood;
			}
			
			
		}
	}

	abstract public Item getTameItem();

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setString("Name", this.dataWatcher.getWatchableObjectString(19));
		par1NBTTagCompound.setString("Owner", owner);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		if (par1NBTTagCompound.getString("Name") != null) {
			this.dataWatcher.updateObject(19, par1NBTTagCompound.getString("Name"));
		}
		this.owner = par1NBTTagCompound.getString("Owner");
	}

	public static EntityFamiliar getFamiliarByOwner(EntityPlayer owner) {
		String ownerStr = owner.getDisplayName();
		for (Object o : owner.worldObj.getLoadedEntityList()) {
			if (o instanceof EntityFamiliar) {
				EntityFamiliar e = (EntityFamiliar) o;
				if (e.owner == ownerStr) {
					return e;
				}
			}
		}
		return null;
	}

	public static String getMoodName(int mood) {
		switch (mood) {
		case 0:
			return "Neutral";
		case 1:
			return "Happy";
		case 2:
			return "Unhappy";
		case 3:
			return "Scared";
		}
		return "";
	}

	public static int getMoodColor(int mood) {
		switch (mood) {
		case 0:
			return 0x00FFFF;
		case 1:
			return 0x00FF00;
		case 2:
			return 0xFF6600;
		case 3:
			return 0xFF0000;
		}
		return 0xFFFFFF;
	}

}
