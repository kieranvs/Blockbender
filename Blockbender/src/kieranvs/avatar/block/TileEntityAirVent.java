package kieranvs.avatar.block;

import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.client.EntityAvatarBubbleFX;
import kieranvs.avatar.client.SoundHandler;
import kieranvs.avatar.util.AvatarDamageSource;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.EntityActionPerformer;
import kieranvs.avatar.util.PacketSender;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityBubbleFX;
import net.minecraft.client.particle.EntityCloudFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.particle.EntitySplashFX;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityAirVent extends TileEntity {

	Random rand = new Random();
	long soundTime = 749;
	long startTime = System.currentTimeMillis();

	@SideOnly(Side.CLIENT)
	@Override
	public void updateEntity() {
		if(worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord)){
			if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
				float particlex;
				float particley;
				float particlez;
				
				if(!isBlockTypeNearby(Blocks.water) && !isBlockTypeNearby(Blocks.lava)){
					return;
				}
				if(!isBlockTypeNearby(Blocks.water)){
					int num = rand.nextInt(10);
					for(int i = 0; i < num; i++){
						particlex = (float)this.xCoord + rand.nextFloat();
						particley = (float)this.yCoord + 1 + rand.nextFloat();
						particlez = (float)this.zCoord + rand.nextFloat();

						Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySmokeFX(Minecraft.getMinecraft().theWorld, particlex, particley, particlez, 0, -0.0005, 0));
					}
					return;
				}
				if(!isBlockTypeNearby(Blocks.lava)){
					int num = rand.nextInt(10);
					for(int i = 0; i < num; i++){
						particlex = (float)this.xCoord + rand.nextFloat();
						particley = (float)this.yCoord + 1 + rand.nextFloat();
						particlez = (float)this.zCoord + rand.nextFloat();

						Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySplashFX(Minecraft.getMinecraft().theWorld, particlex, particley, particlez, 0, 0.7, 0));
					}
					return;
				}
				if(worldObj.getBlock(xCoord, yCoord + 1, zCoord) == Blocks.air){
					if(worldObj.getBlock(xCoord, yCoord, zCoord) == Blocks.air){
						return;
					}
					if((System.currentTimeMillis() - startTime) > soundTime){
						startTime = System.currentTimeMillis();
						SoundHandler.playOnBlock("AirVent", worldObj, this.xCoord, this.yCoord, this.zCoord, 3f, 1f, false);
					}

					int num = rand.nextInt(10);
					for(int i = 0; i < num; i++){
						particlex = (float)this.xCoord + rand.nextFloat();
						particley = (float)this.yCoord + 1 + rand.nextFloat();
						particlez = (float)this.zCoord + rand.nextFloat();

						Minecraft.getMinecraft().effectRenderer.addEffect(new EntityCloudFX(Minecraft.getMinecraft().theWorld, particlex, particley, particlez, 0, 0.7, 0));
					}

					if(isFacingSky()){
						num = rand.nextInt(6);
						for(int i = 0; i < num; i++){
							particley = (float)this.yCoord + 7 + rand.nextFloat() * 13;
							particlex = (float)this.xCoord + rand.nextFloat();
							particlez = (float)this.zCoord + rand.nextFloat();

							Minecraft.getMinecraft().effectRenderer.addEffect(new EntityCloudFX(Minecraft.getMinecraft().theWorld, particlex, particley, particlez, 0, 0.7, 0));
						}
					}

					Location tloc = new Location(worldObj, xCoord, yCoord, zCoord);
					for(int i = 0; i < 20; i++){
						tloc.setY(yCoord + i);
						BendingUtils.damageAllEntitiesWithCustomAction(null, tloc, 1.5F * 2, AvatarDamageSource.airbending, 0, new EntityActionPerformer(){
							@Override
							public void performAction(Entity e) {
								e.motionY += 0.015;
							}
						});
					}
				}
				if(worldObj.getBlock(xCoord, yCoord + 1, zCoord) == Blocks.water){
					int num = rand.nextInt(10);
					for(int i = 0; i < num; i++){
						particlex = (float)this.xCoord + rand.nextFloat();
						particley = (float)this.yCoord + 1 + rand.nextFloat();
						particlez = (float)this.zCoord + rand.nextFloat();

						Minecraft.getMinecraft().effectRenderer.addEffect(new EntityAvatarBubbleFX(Minecraft.getMinecraft().theWorld, particlex, particley, particlez, 0, 0.7, 0));
					}
				}
			}
		}
	}

	public boolean isFacingSky(){
		for(int i = 0; i<256; i++){
			if(this.yCoord + i > 253){
				return true;
			}
			else{
				if(this.worldObj.getBlock(this.xCoord, this.yCoord + i, this.zCoord) != Blocks.air){
					return false;
				}
			}
		}
		return true;
	}

	public boolean isBlockTypeNearby(Block blockType){
		for(int i = -2; i < 3; i++){
			for(int j = -2; j < 3; j++){
				for(int k = -2; k < 3; k++){
					if(this.worldObj.getBlock(this.xCoord + i, this.yCoord + j, this.zCoord + k) == blockType){
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean canUpdate() {
		return true;
	}
}
