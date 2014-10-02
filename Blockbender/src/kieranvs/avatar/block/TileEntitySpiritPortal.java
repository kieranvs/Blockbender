package kieranvs.avatar.block;

import java.util.Random;

import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.client.EntityAvatarBubbleFX;
import kieranvs.avatar.util.AvatarDamageSource;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.EntityActionPerformer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityEnchantmentTableParticleFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntitySpiritPortal extends TileEntity {

	Random rand = new Random();
	
	@Override
	public void updateEntity(){
		if(FMLCommonHandler.instance().getEffectiveSide().isClient()){
			this.updateEntityClient();
		}
		else{
			this.updateEntityServer();
		}
	}

	@SideOnly(Side.CLIENT)
	public void updateEntityClient() {
		float particlex;
		float particley;
		float particlez;
		
		int num = rand.nextInt(10);
		for(int i = 0; i < num; i++){
			particlex = (float)this.xCoord + rand.nextFloat();
			particley = (float)this.yCoord + 1 + rand.nextFloat();
			particlez = (float)this.zCoord + rand.nextFloat();

			Minecraft.getMinecraft().effectRenderer.addEffect(new EntityEnchantmentTableParticleFX(Minecraft.getMinecraft().theWorld, particlex, particley, particlez, 0, 3, 0));
		}
		
		num = rand.nextInt(2);
		for(int i = 0; i < num; i++){
			particlex = (float)this.xCoord + rand.nextFloat();
			particley = (float)this.yCoord + 1 + rand.nextFloat();
			particlez = (float)this.zCoord + rand.nextFloat();

			Minecraft.getMinecraft().effectRenderer.addEffect(new EntityAvatarBubbleFX(Minecraft.getMinecraft().theWorld, particlex, particley, particlez, 0, 0.7, 0));
		}
	}
	
	public void updateEntityServer(){
		Location tloc = new Location(worldObj, xCoord, yCoord, zCoord);
		for(int i = 0; i < 20; i++){
			tloc.setY(yCoord + i);
			BendingUtils.damageAllEntitiesWithCustomAction(null, tloc, 1.5F * 2, AvatarDamageSource.airbending, 0, new EntityActionPerformer(){
				@Override
				public void performAction(Entity e) {
					if(e instanceof EntityPlayer){
						if(e.dimension != 8){
							BendingUtils.sendToDimension((EntityPlayerMP) e, 8);							
						}
						else{
							BendingUtils.sendToDimension((EntityPlayerMP) e, 0);
						}
					}
				}
			});
		}
	}

	@Override
	public boolean canUpdate() {
		return true;
	}
	
	public static final AxisAlignedBB INFINITE_EXTENT_AABB = AxisAlignedBB.getBoundingBox(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox(){
		return INFINITE_EXTENT_AABB;
	}
}
