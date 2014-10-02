package kieranvs.avatar.entity;

import org.lwjgl.opengl.GL11;

import kieranvs.avatar.client.SoundHandler;
import kieranvs.avatar.util.FileLocation;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityGlider extends Entity {
	
	public double prevRotationRoll = 0.0D;
	public double rotationRoll = 0.0D;

	public double prevRotationYAW = 0.0D;
	public double rotationYAW = 0.0D;
	
	public double rotationPitch = 0.0D;
	
	private double lastHeight;
	public boolean isRising;
	
	private int special;
	
	private int color;
	
	public EntityGlider(World w){
		this(w, 1);
	}

	public EntityGlider(World par1World, int color) {
		super(par1World);
		special = this.worldObj.rand.nextInt(10000);
		this.color = color;
	}

	@Override
	public void onUpdate(){
		super.onUpdate();
		if(this.ridingEntity == null){
			this.setDead();
			return;
		}
		if(this.ridingEntity instanceof EntityPlayer){
			if(((EntityPlayer) this.ridingEntity).getHeldItem() == null){
				this.ridingEntity.riddenByEntity = null;
				this.ridingEntity = null;
				this.setDead();
				return;
			}
		}
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if(side == Side.SERVER){
			this.ridingEntity.riddenByEntity = null;
			this.ridingEntity = null;
			this.setDead();
			return;
		}
		if(this.posY > lastHeight + 0.05D){
			isRising = true;
		}
		else{
			isRising = false;
		}
		lastHeight = this.posY;
		updateRotations();
	}
	
	public void updateRotations(){
		if(ridingEntity.isSneaking()){
			rotationPitch += 1D;
			if(rotationPitch > 30D){
				rotationPitch = 30D;
			}
		}
		if(!ridingEntity.isSneaking() && isRising){
			rotationPitch -= 1D;
			if(rotationPitch < -80D){
				rotationPitch = -80D;
			}
		}
		if(!ridingEntity.isSneaking() && !isRising){
			if(rotationPitch != 0D){
				if(rotationPitch < 0D){
					rotationPitch += 2D;
				}
				else if (rotationPitch > 0){
					rotationPitch -= 1D;
				}					
			}
		}
		
		if ((Math.abs(ridingEntity.motionX) < 0.01D) && (Math.abs(ridingEntity.motionZ) < 0.01D))
		{
			prevRotationYAW = (rotationYAW = ridingEntity.rotationYaw);
		} else {
			prevRotationYAW = rotationYAW;
			rotationYAW = Math.toDegrees(-Math.atan2(ridingEntity.motionX, ridingEntity.motionZ));

			rotationYAW = interpolateRotation((float)prevRotationYAW, (float)rotationYAW, 0.5F);

			prevRotationRoll = rotationRoll;
			rotationRoll = (rotationYAW - ridingEntity.rotationYaw);

			rotationRoll = interpolateRotation((float)prevRotationRoll, (float)rotationRoll, 0.5F);
		}
	}
		
	private float interpolateRotation(float angle1, float angle2, float centerPoint)
	{
		float f3;
		for (f3 = angle2 - angle1; f3 < -180.0F; f3 += 360.0F);
		while (f3 >= 180.0F) {
			f3 -= 360.0F;
		}

		return angle1 + centerPoint * f3;
	}

	@Override
	protected void entityInit() {
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
	}
	
	public String getTextureFile(){
		return getOpenTexture(this.color);
	}
	
	public static String getOpenTexture(int color){
		switch(color){
		case 0:
			return FileLocation.ENTITYTEXTURE + "WhiteStaff.png";
		case 1:
			return FileLocation.ENTITYTEXTURE + "OrangeStaff.png";
		case 2:
			return FileLocation.ENTITYTEXTURE + "MagentaStaff.png";
		case 3:
			return FileLocation.ENTITYTEXTURE + "LightBlueStaff.png";
		case 4:
			return FileLocation.ENTITYTEXTURE + "YellowStaff.png";
		case 5:
			return FileLocation.ENTITYTEXTURE + "LightGreenStaff.png";
		case 6:
			return FileLocation.ENTITYTEXTURE + "PinkStaff.png";
		case 7:
			return FileLocation.ENTITYTEXTURE + "GreyStaff.png";
		case 8:
			return FileLocation.ENTITYTEXTURE + "LightGreyStaff.png";
		case 9:
			return FileLocation.ENTITYTEXTURE + "CyanStaff.png";
		case 10:
			return FileLocation.ENTITYTEXTURE + "PurpleStaff.png";
		case 11:
			return FileLocation.ENTITYTEXTURE + "BlueStaff.png";
		case 12:
			return FileLocation.ENTITYTEXTURE + "BrownStaff.png";
		case 13:
			return FileLocation.ENTITYTEXTURE + "GreenStaff.png";
		case 14:
			return FileLocation.ENTITYTEXTURE + "RedStaff.png";
		case 15:
			return FileLocation.ENTITYTEXTURE + "BlackStaff.png";
		default:
			return FileLocation.ENTITYTEXTURE + "OrangeStaff.png";
		}
	}

}
