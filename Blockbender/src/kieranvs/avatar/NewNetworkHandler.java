package kieranvs.avatar;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

import kieranvs.avatar.bending.BendingHandler;
import kieranvs.avatar.client.EntityAvatarBubbleFX;
import kieranvs.avatar.client.EntityAvatarFlameFX;
import kieranvs.avatar.entity.EntityEarthBandit;
import kieranvs.avatar.entity.EntityFireBandit;
import kieranvs.avatar.entity.EntityWaterBandit;
import kieranvs.avatar.item.ItemBendingSatchel;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.StringColour;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityCloudFX;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.client.particle.EntitySplashFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Sharable
public class NewNetworkHandler extends SimpleChannelInboundHandler<FMLProxyPacket> {
	
	BendingPacketHandler bendingHandler = new BendingPacketHandler();
	
	int count = 0;
	long last = 0;
	long first = 0;

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FMLProxyPacket packet) throws Exception {
		if(count == 0) first = System.currentTimeMillis();
		count++;
		if(count % 1000 == 0){
			double delta = System.currentTimeMillis() - last;
			last = System.currentTimeMillis();
			if(first != last && delta != 0) System.out.println(count + "packets received. approx " + (1000000D/delta) + "/sec. Avg:" + (count/(last-first)) + "/sec");			
		}
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		if (side == Side.CLIENT) {
			onClientPacketData(packet);
		}
		else {
			onServerPacketData(packet);
		}
	}

	public void onServerPacketData(FMLProxyPacket packet) {
		if (packet.channel().equals("AvatarKey")){
			int key = packet.payload().readInt();
			boolean down = packet.payload().readBoolean();
			int l = packet.payload().readInt();
			byte[] name = new byte[l];
			packet.payload().readBytes(name);
			
			EntityPlayer theplayer = BendingUtils.getPlayerByName(new String(name));

			if(key == 10){
				if(theplayer.getHeldItem() != null && theplayer.getHeldItem().getItem() instanceof ItemBendingSatchel){
					theplayer.openGui(mod_Avatar.instance, 0, theplayer.worldObj, (int) theplayer.posX, (int) theplayer.posY, (int) theplayer.posZ);
					return;						
				}
				theplayer.addChatMessage(new ChatComponentText(StringColour.GRAY + StringColour.ITALIC + "You aren't holding a satchel."));
				return;
			}

			ItemStack i = ItemBendingSatchel.getSatchelContents(theplayer, key);
			if(i != null){
				BendingHandler.bend(theplayer, i);											
			}
		}
		if(packet.channel().equals("AvatarMisc")){
			int l = packet.payload().readInt(); 
			byte[] name = new byte[l];
			packet.payload().readBytes(name);
			String type = new String(name);

			if(type.equals("keyInput")){
				
 			}
			
			if (type.equals("gliderdata")) {
				int artype = packet.payload().readInt();
				int len = packet.payload().readInt();
				byte[] glidingname = new byte[len];
				packet.payload().readBytes(glidingname);
				String gname = new String(glidingname);
				if(artype == 0){
					mod_Avatar.data.addGlidingPlayerName(gname);
				} else {
					mod_Avatar.data.removeGlidingPlayerName(gname);
				}
			}

		}
	}

	@SideOnly(Side.CLIENT)
	public void onClientPacketData(FMLProxyPacket packet){
		if(packet.channel().equals("AvatarBend")){
			bendingHandler.processPacket(packet);
		}
		if(packet.channel().equals("AvatarChi")){
			mod_Avatar.data.Chi = packet.payload().readInt();
		}
		if(packet.channel().equals("AvatarMisc")){
			int l = packet.payload().readInt(); 
			byte[] name = new byte[l];
			packet.payload().readBytes(name);
			String type = new String(name);

			if (type.equals("gliderdata")) {
				int artype = packet.payload().readInt();
				int len = packet.payload().readInt();
				byte[] glidingname = new byte[len];
				packet.payload().readBytes(glidingname);
				String gname = new String(glidingname);
				if(artype == 0){
					mod_Avatar.data.addGlidingPlayerName(gname, true);
				} else {
					mod_Avatar.data.removeGlidingPlayerName(gname, true);
				}
			}
			if (type.equals("velocity")) {
				int id = packet.payload().readInt();
				double x = packet.payload().readDouble();
				double y = packet.payload().readDouble();
				double z = packet.payload().readDouble();
				if(Minecraft.getMinecraft().theWorld.getEntityByID(id) != null){
					Minecraft.getMinecraft().theWorld.getEntityByID(id).motionX = x;
					Minecraft.getMinecraft().theWorld.getEntityByID(id).motionY = y;
					Minecraft.getMinecraft().theWorld.getEntityByID(id).motionZ = z;
				}
			}
			if (type.equals("texture")) {
			}
			if (type.equals("music")) {
			}
			if (type.equals("performance")){
				mod_Avatar.data.pshow = true;
				mod_Avatar.data.pmoves = packet.payload().readInt();
			}
			if(type.equals("isBending")){
				boolean isBending = packet.payload().readBoolean();
				int entId = packet.payload().readInt();
				
				int element = packet.payload().readInt();
				
				if(element == 0){
					EntityFireBandit ent = (EntityFireBandit)Minecraft.getMinecraft().theWorld.getEntityByID(entId);
					if(ent != null) ent.setIsBending(isBending);
				} else if(element == 1){
					EntityWaterBandit ent = (EntityWaterBandit)Minecraft.getMinecraft().theWorld.getEntityByID(entId);
					if(ent != null) ent.setIsBending(isBending);
				} else if(element == 2){
					EntityEarthBandit ent = (EntityEarthBandit)Minecraft.getMinecraft().theWorld.getEntityByID(entId);
					if(ent != null) ent.setIsBending(isBending);
				}
			}
		}
		if(packet.channel().equals("AvatarPar")){
			int l = packet.payload().readInt();
			byte[] name = new byte[l];
			packet.payload().readBytes(name);
			String particle = new String(name);
			double x = packet.payload().readDouble();
			double y = packet.payload().readDouble();
			double z = packet.payload().readDouble();
			double total = packet.payload().readInt();
			double size = packet.payload().readDouble();
			if (particle.equals("Avatar_flames")) {
				Minecraft.getMinecraft().effectRenderer.addEffect(new EntityAvatarFlameFX(Minecraft.getMinecraft().theWorld, x, y, z, 0, 0, 0));
			}
			else if (particle.equals("Avatar_flamesbig")) {
				Random r = new Random();
				for (int i = 0; i < 10; i++) {
					double xr = r.nextGaussian();
					double yr = r.nextGaussian();
					double zr = r.nextGaussian();
					Minecraft.getMinecraft().effectRenderer.addEffect(new EntityAvatarFlameFX(Minecraft.getMinecraft().theWorld, x + xr, y + yr, z + zr, 0, 0, 0));
				}
			}else if (particle.equals("Avatar_flamesjet")) {
				Random r = new Random();
				for (int i = 0; i < 2; i++) {
					double xr = r.nextGaussian() * 0.5;
					double yr = r.nextGaussian() * 0.5;
					double zr = r.nextGaussian() * 0.5;
					Minecraft.getMinecraft().effectRenderer.addEffect(new EntityAvatarFlameFX(Minecraft.getMinecraft().theWorld, x + xr, y + yr, z + zr, 0, 0, 0));
				}
			}
			else if (particle.equals("Avatar_flamesnew")) {
				Random r = new Random();
				for (int i = 0; i < total; i++) {
					double xr = r.nextGaussian() * size;
					double yr = r.nextGaussian() * size;
					double zr = r.nextGaussian() * size;
					Minecraft.getMinecraft().effectRenderer.addEffect(new EntityAvatarFlameFX(Minecraft.getMinecraft().theWorld, x + xr, y + yr, z + zr, 0, 0, 0));
				}
			}
			else if (particle.equals("Avatar_bubbles")) {
				Minecraft.getMinecraft().effectRenderer.addEffect(new EntityAvatarBubbleFX(Minecraft.getMinecraft().theWorld, x, y, z, 0, 0, 0));
			}
			else if (particle.equals("Avatar_dig")) {
				Random r = new Random();
				for (int i = 0; i < total; i++) {
					double xr = r.nextGaussian() * size;
					double yr = r.nextGaussian() * size;
					double zr = r.nextGaussian() * size;
					Minecraft.getMinecraft().effectRenderer.addEffect(new EntityDiggingFX(Minecraft.getMinecraft().theWorld, x + xr, y + yr, z + zr, 0, 0, 0, Blocks.dirt, 0, 0));		
				}
			}
			else if (particle.equals("Avatar_bubblesplash")) {
				Random r = new Random();
				for (int i = 0; i < 5; i++) {
					double xr = r.nextGaussian() / 4;
					double yr = r.nextGaussian() / 4;
					double zr = r.nextGaussian() / 4;
					Minecraft.getMinecraft().effectRenderer.addEffect(new EntityAvatarBubbleFX(Minecraft.getMinecraft().theWorld, x + xr, y + yr, z + zr, 0, 0, 0));
				}
			}
			else if (particle.equals("Avatar_watersplash")) {
				Random r = new Random();
				for (int i = 0; i < total; i++) {
					double xr = r.nextGaussian() * size;
					double yr = r.nextGaussian() * size;
					double zr = r.nextGaussian() * size;
					Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySplashFX(Minecraft.getMinecraft().theWorld, x + xr, y + yr, z + zr, 0, 0, 0));
				}
			}
			else if (particle.equals("Avatar_watersplashsmall")) {
				Random r = new Random();
				for (int i = 0; i < 10; i++) {
					double xr = r.nextGaussian() / 5;
					double yr = r.nextGaussian() / 5;
					double zr = r.nextGaussian() / 5;
					Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySplashFX(Minecraft.getMinecraft().theWorld, x + xr, y + yr, z + zr, 0, 0, 0));
				}
			}
			else if (particle.equals("Avatar_cloud")) {
				Random r = new Random();
				for (int i = 0; i < total; i++) {
					double xr = r.nextGaussian() * size;
					double yr = r.nextGaussian() * size;
					double zr = r.nextGaussian() * size;
					Minecraft.getMinecraft().effectRenderer.addEffect(new EntityCloudFX(Minecraft.getMinecraft().theWorld, x + xr, y + yr, z + zr, 0, 0, 0));
					Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySpellParticleFX(Minecraft.getMinecraft().theWorld,  x + xr, y + yr, z + zr, 0, 0, 0));
				}
			}
			else if (particle.equals("Avatar_shield")) {
				Random r = new Random();
				int height = packet.payload().readInt();
				int radius = packet.payload().readInt();
				double yquality = packet.payload().readInt();
				int xzquality = packet.payload().readInt();
				int rarity = packet.payload().readInt();
				int element = packet.payload().readInt();
				for(int i = 0; i < height * yquality; i++){
					double radiusR = (double) radius * Math.cos(Math.toRadians(90D * (i / (height * yquality))));
					for(int t = 0; t < 360; t++){
						if((t % xzquality == 0) && (r.nextInt(rarity) == 0)){
							double spawnX = x + (radiusR * Math.cos(Math.toRadians(t)));
							double spawnY = (double) y + (double)(i / yquality);
							double spawnZ = z + (radiusR * Math.sin(Math.toRadians(t)));
							if(element == 0) Minecraft.getMinecraft().effectRenderer.addEffect(new EntityCloudFX(Minecraft.getMinecraft().theWorld, spawnX, spawnY, spawnZ, 0, 0, 0));
							if(element == 1){
								if(r.nextInt(7) == 0){
									Minecraft.getMinecraft().effectRenderer.addEffect(new EntityAvatarBubbleFX(Minecraft.getMinecraft().theWorld, spawnX, spawnY, spawnZ, 0, 0, 0));										
								}
								else{
									Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySplashFX(Minecraft.getMinecraft().theWorld, spawnX, spawnY, spawnZ, 0, 0, 0));										
								}
							}
							if(element == 2) Minecraft.getMinecraft().effectRenderer.addEffect(new EntityFlameFX(Minecraft.getMinecraft().theWorld, spawnX, spawnY, spawnZ, 0, 0, 0));
						}
					}
				}
			}
			else if (particle.equals("Avatar_circle")) {
				Random r = new Random();
				double radius = packet.payload().readDouble();
				int xzquality = packet.payload().readInt();
				int rarity = packet.payload().readInt();
				int element = packet.payload().readInt();
				for(int t = 0; t < 360; t++){
					if((t % xzquality == 0) && (r.nextInt(rarity) == 0)){
						double spawnX = x + (radius * Math.cos(Math.toRadians(t)));
						double spawnY = y;
						double spawnZ = z + (radius * Math.sin(Math.toRadians(t)));
						if(element == 0){
							if(r.nextInt(2) == 0){
								Minecraft.getMinecraft().effectRenderer.addEffect(new EntityCloudFX(Minecraft.getMinecraft().theWorld, spawnX, spawnY, spawnZ, 0, 0, 0));
							}
							else{
								Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySmokeFX(Minecraft.getMinecraft().theWorld, spawnX, spawnY, spawnZ, 0, 0, 0));
							}
						}
						if(element == 1){
							if(r.nextInt(7) == 0){
								Minecraft.getMinecraft().effectRenderer.addEffect(new EntityAvatarBubbleFX(Minecraft.getMinecraft().theWorld, spawnX, spawnY, spawnZ, 0, 0, 0));										
							}
							else{
								Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySplashFX(Minecraft.getMinecraft().theWorld, spawnX, spawnY, spawnZ, 0, 0, 0));										
							}
						}
						if(element == 2) Minecraft.getMinecraft().effectRenderer.addEffect(new EntityFlameFX(Minecraft.getMinecraft().theWorld, spawnX, spawnY, spawnZ, 0, 0, 0));
					}
				}
			}
			else if (particle.equals("Avatar_disc")) {
				Random r = new Random();
				int radius = packet.payload().readInt();
				int xzquality = packet.payload().readInt();
				int rarity = packet.payload().readInt();
				int element = packet.payload().readInt();
				for(int i = 0; i < 10; i++){
					double dRadius = (double) i * (radius / 10D);
					for(int t = 0; t<360; t++){
						if((t % xzquality == 0) && (r.nextInt(rarity) == 0)){
							double spawnX = x + (dRadius * Math.cos(Math.toRadians(t)));
							double spawnY = y;
							double spawnZ = z + (dRadius * Math.sin(Math.toRadians(t)));
							if(element == 0) Minecraft.getMinecraft().effectRenderer.addEffect(new EntityCloudFX(Minecraft.getMinecraft().theWorld, spawnX, spawnY, spawnZ, 0, 0, 0));
							if(element == 1){
								if(r.nextInt(7) == 0){
									Minecraft.getMinecraft().effectRenderer.addEffect(new EntityAvatarBubbleFX(Minecraft.getMinecraft().theWorld, spawnX, spawnY, spawnZ, 0, 0, 0));										
								}
								else{
									Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySplashFX(Minecraft.getMinecraft().theWorld, spawnX, spawnY, spawnZ, 0, 0, 0));										
								}
							}
							if(element == 2) Minecraft.getMinecraft().effectRenderer.addEffect(new EntityFlameFX(Minecraft.getMinecraft().theWorld, spawnX, spawnY, spawnZ, 0, 0, 0));
						}
					}
				}
			}
			else if (particle.equals("Avatar_smoke")) {
				Random r = new Random();
				for (int i = 0; i < total; i++) {
					double xr = r.nextGaussian() * size;
					double yr = r.nextGaussian() * size;
					double zr = r.nextGaussian() * size;
					Minecraft.getMinecraft().effectRenderer.addEffect(new EntitySmokeFX(Minecraft.getMinecraft().theWorld, x + xr, y + yr, z + zr, 0, 0, 0));
				}
			}
			else {
				Minecraft.getMinecraft().theWorld.spawnParticle(particle, x, y, z, 0, 0, 0);
			}
		}
		if(packet.channel().equals("AvatarFam")){
			int packettype = packet.payload().readInt();
			if (packettype == 1) {
				int l = packet.payload().readInt(); 
				byte[] name = new byte[l];
				packet.payload().readBytes(name);
				String familiarName = new String(name);
				mod_Avatar.data.familiarname = familiarName;
			}
			if (packettype == 2) {
				mod_Avatar.data.familiarhealth = packet.payload().readInt();
			}
			if (packettype == 3) {
				mod_Avatar.data.familiarmood = packet.payload().readInt();
			}
			if (packettype == 4) {
				mod_Avatar.data.familiarname = null;
				mod_Avatar.data.familiarhealth = -1;
				mod_Avatar.data.familiarmood = -1;
			}
		}
	}
}
