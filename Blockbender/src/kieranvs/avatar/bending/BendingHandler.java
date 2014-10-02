package kieranvs.avatar.bending;

import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.bending.air.AirDisc;
import kieranvs.avatar.bending.air.AirDomeBurst;
import kieranvs.avatar.bending.air.AirGust;
import kieranvs.avatar.bending.air.AirHighJump;
import kieranvs.avatar.bending.air.AirRing;
import kieranvs.avatar.bending.air.AirShield;
import kieranvs.avatar.bending.air.AirSphere;
import kieranvs.avatar.bending.air.AirSpring;
import kieranvs.avatar.bending.air.AirStream;
import kieranvs.avatar.bending.air.AirTornado;
import kieranvs.avatar.bending.air.AirWindRun;
import kieranvs.avatar.bending.earth.EarthBridge;
import kieranvs.avatar.bending.earth.EarthBuildTower;
import kieranvs.avatar.bending.earth.EarthBuildWall;
import kieranvs.avatar.bending.earth.EarthLaunch;
import kieranvs.avatar.bending.earth.EarthOreSense;
import kieranvs.avatar.bending.earth.EarthRaisePlatform;
import kieranvs.avatar.bending.earth.EarthRockThrow;
import kieranvs.avatar.bending.earth.EarthTent;
import kieranvs.avatar.bending.earth.EarthThrow;
import kieranvs.avatar.bending.earth.EarthTunnelColumn;
import kieranvs.avatar.bending.earth.EarthWall;
import kieranvs.avatar.bending.fire.FireBlast;
import kieranvs.avatar.bending.fire.FireExplosion;
import kieranvs.avatar.bending.fire.FireFlight;
import kieranvs.avatar.bending.fire.FireJet;
import kieranvs.avatar.bending.fire.FireLightning;
import kieranvs.avatar.bending.fire.FireMelt;
import kieranvs.avatar.bending.fire.FireRing;
import kieranvs.avatar.bending.fire.FireStar;
import kieranvs.avatar.bending.fire.FireStream;
import kieranvs.avatar.bending.fire.FireWall;
import kieranvs.avatar.bending.fire.Fireball;
import kieranvs.avatar.bending.water.SnowballThrow;
import kieranvs.avatar.bending.water.WaterBlobRaise;
import kieranvs.avatar.bending.water.WaterFreezeWaterfall;
import kieranvs.avatar.bending.water.WaterIceRing;
import kieranvs.avatar.bending.water.WaterIceBridge;
import kieranvs.avatar.bending.water.WaterIceStream;
import kieranvs.avatar.bending.water.WaterIceWall;
import kieranvs.avatar.bending.water.WaterOctopusForm;
import kieranvs.avatar.bending.water.WaterWaterShield;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.client.SoundHandler;
import kieranvs.avatar.item.ItemSpellTome;
import kieranvs.avatar.util.Messaging;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class BendingHandler {

	public static void bend(EntityPlayer player){
		bend(player, player.getHeldItem());
	}

	public static void bend(EntityLivingBase user, ItemStack item){

		if(user instanceof EntityPlayer && !ElementManager.hasElement(user, ElementManager.FIRE)
				&& !ElementManager.hasElement(user, ElementManager.WATER)
				&& !ElementManager.hasElement(user, ElementManager.EARTH)
				&& !ElementManager.hasElement(user, ElementManager.AIR)){
			if(item.getItem() instanceof ItemSpellTome){
				Messaging.avatarMessage((EntityPlayer) user, "No element selected. Use /bending <element> to choose your element first.");				
			}
		}

		/* FIRE */
		if (ElementManager.hasElement(user, ElementManager.FIRE)) {
			if (item.getItem() == mod_Avatar.FireStreamNoviceItem) {
				if(Ability.canStart(user, FireStream.class)){
					Location l = new Location(user);
					new FireStream(l, l.getDirection(), 0, user);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "fire01basic", "fire02basic");
				}
			}
			if (item.getItem() == mod_Avatar.FireStreamAdeptItem) {
				if(Ability.canStart(user, FireStream.class)){
					Location l = new Location(user);
					new FireStream(l, l.getDirection(), 1, user);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.2F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.6F, "fire01basic", "fire02basic");
				}
			}
			if (item.getItem() == mod_Avatar.FireStreamMasterItem) {
				if(Ability.canStart(user, FireStream.class)){
					Location l = new Location(user);
					new FireStream(l, l.getDirection(), 2, user);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.4F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.9F, "fire01basic", "fire02basic");
				}
			}
			if (item.getItem() == mod_Avatar.FireBlastNoviceItem) {
				if(Ability.canStart(user, FireBlast.class)){
					new FireBlast(user, 20, 0);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.0F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "fire01basic", "fire02basic");
				}
			}
			if (item.getItem() == mod_Avatar.FireBlastAdeptItem) {
				if(Ability.canStart(user, FireBlast.class)){
					new FireBlast(user, 20, 1);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.4F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.6F, "fire01basic", "fire02basic");
				}
			}
			if (item.getItem() == mod_Avatar.FireBlastMasterItem) {
				if(Ability.canStart(user, FireBlast.class)){
					new FireBlast(user, 20, 2);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.6F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.9F, "fire01basic", "fire02basic");
				}
			}
			if (item.getItem() == mod_Avatar.FireExplosionNoviceItem) {
				if(Ability.canStart(user, FireExplosion.class)){
					new FireExplosion(user, 0);
				}
			}
			if (item.getItem() == mod_Avatar.FireExplosionAdeptItem) {
				if(Ability.canStart(user, FireExplosion.class)){
					new FireExplosion(user, 1);
				}
			}
			if (item.getItem() == mod_Avatar.FireExplosionMasterItem) {
				if(Ability.canStart(user, FireExplosion.class)){
					new FireExplosion(user, 2);
				}
			}
			if (item.getItem() == mod_Avatar.FireJetNoviceItem) {
				if(Ability.canStart(user, FireJet.class)){
					Location l = new Location(user);
					l.setY(l.getY() + 1.62);
					new FireJet(user, l, l.getDirection(), 0);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "fire04quick", "fire05quick");
				}
			}
			if (item.getItem() == mod_Avatar.FireJetAdeptItem) {
				if(Ability.canStart(user, FireJet.class)){
					Location l = new Location(user);
					l.setY(l.getY() + 1.62);
					new FireJet(user, l, l.getDirection(), 1);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.2F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.6F, "fire04quick", "fire05quick");
				}
			}
			if (item.getItem() == mod_Avatar.FireJetMasterItem) {
				if(Ability.canStart(user, FireJet.class)){
					Location l = new Location(user);
					l.setY(l.getY() + 1.62);
					new FireJet(user, l, l.getDirection(), 2);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.4F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.9F, "fire04quick", "fire05quick");
				}
			}
			if (item.getItem() == mod_Avatar.FireFlightNoviceItem) {
				if(Ability.canStart(user, FireFlight.class)){
					new FireFlight(user, 0);
				}
			}
			if (item.getItem() == mod_Avatar.FireFlightAdeptItem) {
				if(Ability.canStart(user, FireFlight.class)){
					new FireFlight(user, 1);
				}
			}
			if (item.getItem() == mod_Avatar.FireFlightMasterItem) {
				if(Ability.canStart(user, FireFlight.class)){
					new FireFlight(user, 2);
				}
			}
			if (item.getItem() == mod_Avatar.FireWallNoviceItem) {
				if(Ability.canStart(user, FireWall.class)){
					new FireWall(user, 0);
				}
			}
			if (item.getItem() == mod_Avatar.FireWallAdeptItem) {
				if(Ability.canStart(user, FireWall.class)){
					new FireWall(user, 1);
				}
			}
			if (item.getItem() == mod_Avatar.FireWallMasterItem) {
				if(Ability.canStart(user, FireWall.class)){
					new FireWall(user, 2);
				}
			}
			if (item.getItem() == mod_Avatar.FireLightningNoviceItem) {
				if(Ability.canStart(user, FireLightning.class)){
					new FireLightning(user, 1, 0);
				}
			}
			if (item.getItem() == mod_Avatar.FireLightningAdeptItem) {
				if(Ability.canStart(user, FireLightning.class)){
					new FireLightning(user, 3, 1);						
				}
			}
			if (item.getItem() == mod_Avatar.FireLightningMasterItem) {
				if(Ability.canStart(user, FireLightning.class)){
					new FireLightning(user, 5, 2);
				}
			}
			if (item.getItem() == mod_Avatar.FireRingNoviceItem){
				if(Ability.canStart(user, FireRing.class)){
					new FireRing(user, 0);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "fire01basic", "fire02basic");
				}
			}
			if (item.getItem() == mod_Avatar.FireRingAdeptItem){
				if(Ability.canStart(user, FireRing.class)){
					new FireRing(user, 1);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.2F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.6F, "fire01basic", "fire02basic");
				}
			}
			if (item.getItem() == mod_Avatar.FireRingMasterItem){
				if(Ability.canStart(user, FireRing.class)){
					new FireRing(user, 2);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.4F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.9F, "fire01basic", "fire02basic");
				}
			}
			if (item.getItem() == mod_Avatar.FireStarNoviceItem){
				if(Ability.canStart(user, FireStar.class)){
					new FireStar(user, 0);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "fire01basic", "fire02basic");
				}
			}
			if (item.getItem() == mod_Avatar.FireStarAdeptItem){
				if(Ability.canStart(user, FireStar.class)){
					new FireStar(user, 1);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.2F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.6F, "fire01basic", "fire02basic");
				}
			}
			if (item.getItem() == mod_Avatar.FireStarMasterItem){
				if(Ability.canStart(user, FireStar.class)){
					new FireStar(user, 2);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.4F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.9F, "fire01basic", "fire02basic");
				}
			}
			if (item.getItem() == mod_Avatar.FireFireballNoviceItem){
				if(Ability.canStart(user, Fireball.class)){
					new Fireball(user, 0);
				}
			}
			if (item.getItem() == mod_Avatar.FireFireballAdeptItem){
				if(Ability.canStart(user, Fireball.class)){
					new Fireball(user, 1);
				}
			}
			if (item.getItem() == mod_Avatar.FireFireballMasterItem){
				if(Ability.canStart(user, Fireball.class)){
					new Fireball(user, 2);
				}
			}
			if (item.getItem() == mod_Avatar.FireMeltNoviceItem){
				if(Ability.canStart(user, FireMelt.class)){
					new FireMelt(user, 0);
					SoundHandler.playOnEntityVanilla("random.fizz", user.worldObj, user, 1.4F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.9F);
				}
			}
			if (item.getItem() == mod_Avatar.FireMeltAdeptItem){
				if(Ability.canStart(user, FireMelt.class)){
					new FireMelt(user, 1);
					SoundHandler.playOnEntityVanilla("random.fizz",user.worldObj, user, 1.4F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.9F);
				}
			}
			if (item.getItem() == mod_Avatar.FireMeltMasterItem){
				if(Ability.canStart(user, FireMelt.class)){
					new FireMelt(user, 2);
					SoundHandler.playOnEntityVanilla("random.fizz",user.worldObj, user, 1.4F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.9F);
				}
			}
		}

		/* WATER */
		if (ElementManager.hasElement(user, ElementManager.WATER)) {
			if (item.getItem() == mod_Avatar.WaterIceBridgeNoviceItem) {
				if(Ability.canStart(user, WaterIceBridge.class)){
					BlockBukkit t = BlockBukkit.getTargetBlock(user);
					Location l = t.getLocation();
					Location d = new Location(user);
					new WaterIceBridge(user, l, d.getDirection(), 0);
				}
			}
			if (item.getItem() == mod_Avatar.WaterIceBridgeAdeptItem) {
				if(Ability.canStart(user, WaterIceBridge.class)){
					BlockBukkit t = BlockBukkit.getTargetBlock(user);
					Location l = t.getLocation();
					Location d = new Location(user);
					new WaterIceBridge(user, l, d.getDirection(), 1);
				}
			}
			if (item.getItem() == mod_Avatar.WaterIceBridgeMasterItem) {
				if(Ability.canStart(user, WaterIceBridge.class)){
					BlockBukkit t = BlockBukkit.getTargetBlock(user);
					Location l = t.getLocation();
					Location d = new Location(user);
					WaterIceBridge.createTriple(user, l, d.getDirection(), 20, 4);
				}
			}
			if(item.getItem() == mod_Avatar.WaterIceStreamNoviceItem) {
				if(Ability.canStart(user, WaterIceStream.class)){
					Location l = new Location(user);
					l.setY(l.getY() + 1);
					new WaterIceStream(user, l, l.getDirection(), 0);
				}
			}
			if(item.getItem() == mod_Avatar.WaterIceStreamAdeptItem) {
				if(Ability.canStart(user, WaterIceStream.class)){
					Location l = new Location(user);
					l.setY(l.getY() + 1);
					new WaterIceStream(user, l, l.getDirection(), 1);
				}
			}
			if(item.getItem() == mod_Avatar.WaterIceStreamMasterItem) {
				if(Ability.canStart(user, WaterIceStream.class)){
					Location l = new Location(user);
					l.setY(l.getY() + 1);
					new WaterIceStream(user, l, l.getDirection(), 2);
				}
			}
			if (item.getItem() == mod_Avatar.WaterIceRingNoviceItem) {
				if(Ability.canStart(user, WaterIceBridge.class)){
					new WaterIceRing(user, 0);
				}
			}
			if (item.getItem() == mod_Avatar.WaterIceRingAdeptItem) {
				if(Ability.canStart(user, WaterIceBridge.class)){
					new WaterIceRing(user, 1);
				}
			}
			if (item.getItem() == mod_Avatar.WaterIceRingMasterItem) {
				if(Ability.canStart(user, WaterIceBridge.class)){
					new WaterIceRing(user, 2);
				}
			}
			if (item.getItem() == mod_Avatar.WaterFreezeWaterfallNoviceItem) {
				if(Ability.canStart(user, WaterFreezeWaterfall.class)){
					BlockBukkit t = BlockBukkit.getTargetBlock(user);
					Location l = t.getLocation();
					new WaterFreezeWaterfall(l, user, 0);
				}
			}
			if (item.getItem() == mod_Avatar.WaterIceWallNoviceItem) {
				new WaterIceWall(user, 0, false);
			}
			if (item.getItem() == mod_Avatar.WaterIceWallAdeptItem) {
				new WaterIceWall(user, 1, false);
			}
			if (item.getItem() == mod_Avatar.WaterIceWallMasterItem) {
				new WaterIceWall(user, 2, false);
			}
			if (item.getItem() == mod_Avatar.WaterSnowWallNoviceItem) {
				new WaterIceWall(user, 0, true);
			}
			if (item.getItem() == mod_Avatar.WaterSnowWallAdeptItem) {
				new WaterIceWall(user, 1, true);
			}
			if (item.getItem() == mod_Avatar.WaterSnowWallMasterItem) {
				new WaterIceWall(user, 2, true);
			}
			if (item.getItem() == mod_Avatar.WaterSnowballNoviceItem) {
				new SnowballThrow(user, 0);
			}
			if (item.getItem() == mod_Avatar.WaterSnowballAdeptItem) {
				new SnowballThrow(user, 1);
			}
			if (item.getItem() == mod_Avatar.WaterSnowballMasterItem) {
				new SnowballThrow(user, 2);
			}
			if (item.getItem() == mod_Avatar.WaterManipulateItem) {
				if(Ability.canStart(user, WaterBlobRaise.class)){
					BlockBukkit t = BlockBukkit.getTargetBlock(user);
					Location l = t.getLocation();
					new WaterBlobRaise(user, l, 0);
				}
			}
			if (item.getItem() == mod_Avatar.WaterManipulateShootNoviceItem) {
				Location l = new Location(user);
				for(Ability a : Ability.Instances){
					if(a instanceof WaterBlobRaise && a.user == user){
						((WaterBlobRaise) a).shootProjectile(l.getDirection().normalize().multiply(0.005), 5, 2);
					}
				}
			}
			if (item.getItem() == mod_Avatar.WaterManipulateShootAdeptItem) {
				Location l = new Location(user);
				for(Ability a : Ability.Instances){
					if(a instanceof WaterBlobRaise && a.user == user){
						((WaterBlobRaise) a).shootProjectile(l.getDirection().normalize().multiply(0.006), 12, 4);
					}
				}
			}
			if (item.getItem() == mod_Avatar.WaterManipulateShootMasterItem) {
				Location l = new Location(user);
				for(Ability a : Ability.Instances){
					if(a instanceof WaterBlobRaise && a.user == user){
						((WaterBlobRaise) a).shootProjectile(l.getDirection().normalize().multiply(0.007), 18, 6);
					}
				}
			}
			if (item.getItem() == mod_Avatar.WaterManipulateFreezeItem) {
				Location l = new Location(user);
				for(Ability a : Ability.Instances){
					if(a instanceof WaterBlobRaise && a.user == user){
						((WaterBlobRaise) a).freezeWaterProjectile();
					}
				}
			}
			if (item.getItem() == mod_Avatar.WaterOctopusFormNoviceItem) {
				if(Ability.canStart(user, WaterOctopusForm.class)){
					new WaterOctopusForm(user, 30000L);
				}
			}
			if (item.getItem() == mod_Avatar.WaterMeltNoviceItem) {
				if(Ability.canStart(user, FireMelt.class)){
					new FireMelt(user, 0, false);
					SoundHandler.playOnEntityVanilla("random.fizz", user.worldObj, user, 1.4F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.9F);
				}
			}
			if (item.getItem() == mod_Avatar.WaterMeltAdeptItem) {
				if(Ability.canStart(user, FireMelt.class)){
					new FireMelt(user, 1, false);
					SoundHandler.playOnEntityVanilla("random.fizz", user.worldObj, user, 1.4F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.9F);
				}
			}
			if (item.getItem() == mod_Avatar.WaterMeltMasterItem) {
				if(Ability.canStart(user, FireMelt.class)){
					new FireMelt(user, 2, false);
					SoundHandler.playOnEntityVanilla("random.fizz", user.worldObj, user, 1.4F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.9F);
				}
			}
		}

		/* EARTH */
		if (ElementManager.hasElement(user, ElementManager.EARTH)) {
			if (item.getItem() == mod_Avatar.EarthRockThrowNoviceItem) {
				if(Ability.canStart(user, EarthRockThrow.class)){
					new EarthRockThrow(user, 3000L, 1);
				}
			}
			if (item.getItem() == mod_Avatar.EarthRockThrowAdeptItem) {
				if(Ability.canStart(user, EarthRockThrow.class)){
					new EarthRockThrow(user, 4000L, 2);
				}
			}
			if (item.getItem() == mod_Avatar.EarthRockThrowMasterItem) {
				if(Ability.canStart(user, EarthRockThrow.class)){
					new EarthRockThrow(user, 6000L, 3);
				}
			}
			if (item.getItem() == mod_Avatar.EarthRaisePlatformNoviceItem) {
				if(Ability.canStart(user, EarthRaisePlatform.class)){
					new EarthRaisePlatform(user, 3, 1);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "Earthbending01", "Earthbending02");
				}
			}
			if (item.getItem() == mod_Avatar.EarthRaisePlatformAdeptItem) {
				if(Ability.canStart(user, EarthRaisePlatform.class)){
					new EarthRaisePlatform(user, 6, 2);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.2F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.6F, "Earthbending01", "Earthbending02");
				}
			}
			if (item.getItem() == mod_Avatar.EarthRaisePlatformMasterItem) {
				if(Ability.canStart(user, EarthRaisePlatform.class)){
					new EarthRaisePlatform(user, 10, 3);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.6F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 2.0F, "Earthbending01", "Earthbending02");
				}
			}
			if (item.getItem() == mod_Avatar.EarthWallNoviceItem) {
				if(Ability.canStart(user, EarthWall.class)){
					new EarthWall(user, 3000L, 5, 5);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "Earthbending01", "Earthbending02");
				}
			}
			if (item.getItem() == mod_Avatar.EarthWallAdeptItem) {
				if(Ability.canStart(user, EarthWall.class)){
					new EarthWall(user, 5000L, 5, 7);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.2F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.6F, "Earthbending01", "Earthbending02");
				}
			}
			if (item.getItem() == mod_Avatar.EarthWallMasterItem) {
				if(Ability.canStart(user, EarthWall.class)){
					new EarthWall(user, 10000L, 8, 10);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.6F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 2.0F, "Earthbending01", "Earthbending02");
				}
			}
			if (item.getItem() == mod_Avatar.EarthTentNoviceItem) {
				if(Ability.canStart(user, EarthTent.class)){
					new EarthTent(user, 300L);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "Earthbending01", "Earthbending02");
				}
			}
			if (item.getItem() == mod_Avatar.EarthLaunchNoviceItem) {
				if(Ability.canStart(user, EarthLaunch.class)){
					new EarthLaunch(user, 3000L, 1);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "Earthbending03");
				}
			}
			if (item.getItem() == mod_Avatar.EarthLaunchAdeptItem) {
				if(Ability.canStart(user, EarthLaunch.class)){
					new EarthLaunch(user, 5000L, 1.5f);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.2F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.6F, "Earthbending03");
				}
			}
			if (item.getItem() == mod_Avatar.EarthLaunchMasterItem) {
				if(Ability.canStart(user, EarthLaunch.class)){
					new EarthLaunch(user, 10000L, 2f);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.6F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 2.0F, "Earthbending03");
				}
			}
			if (item.getItem() == mod_Avatar.EarthTunnelColumnNoviceItem) {
				if(Ability.canStart(user, EarthTunnelColumn.class)){
					BlockBukkit t = BlockBukkit.getTargetBlock(user);
					Location l = t.getLocation();
					Location d = new Location(user);
					new EarthTunnelColumn(user, 10000, d.getDirection(), 6, -1);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "Earthbending03");
				}
			}
			if (item.getItem() == mod_Avatar.EarthTunnelColumnAdeptItem) {
				if(Ability.canStart(user, EarthTunnelColumn.class)){
					BlockBukkit t = BlockBukkit.getTargetBlock(user);
					Location l = t.getLocation();
					Location d = new Location(user);
					new EarthTunnelColumn(user, 20000, d.getDirection(), 10, 5);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.2F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.6F, "Earthbending03");
				}
			}
			if (item.getItem() == mod_Avatar.EarthTunnelColumnMasterItem) {
				if(Ability.canStart(user, EarthTunnelColumn.class)){
					BlockBukkit t = BlockBukkit.getTargetBlock(user);
					Location l = t.getLocation();
					Location d = new Location(user);
					new EarthTunnelColumn(user, 30000, d.getDirection(), 15, 2);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.6F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 2.0F, "Earthbending03");
				}
			}
			if (item.getItem() == mod_Avatar.EarthBuildWallNoviceItem) {
				if(Ability.canStart(user, EarthBuildWall.class)){
					Location d = new Location(user);
					new EarthBuildWall(user, d, d.getDirection(), 4, 6);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "Earthbending01", "Earthbending02");
				}
			}
			if (item.getItem() == mod_Avatar.EarthBuildWallAdeptItem) {
				if(Ability.canStart(user, EarthBuildWall.class)){
					Location d = new Location(user);
					new EarthBuildWall(user, d, d.getDirection(), 5, 12);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.2F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.6F, "Earthbending01", "Earthbending02");
				}
			}
			if (item.getItem() == mod_Avatar.EarthBuildWallMasterItem) {
				if(Ability.canStart(user, EarthBuildWall.class)){
					Location d = new Location(user);
					new EarthBuildWall(user, d, d.getDirection(), 7, 18);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.6F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 2.0F, "Earthbending01", "Earthbending02");
				}
			}
			if (item.getItem() == mod_Avatar.EarthBuildTowerNoviceItem) {
				if(Ability.canStart(user, EarthBuildTower.class)){
					Location d = new Location(user);
					new EarthBuildTower(user, d, 5);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "Earthbending01", "Earthbending02");
				}
			}
			if (item.getItem() == mod_Avatar.EarthBuildTowerAdeptItem) {
				if(Ability.canStart(user, EarthBuildTower.class)){
					Location d = new Location(user);
					new EarthBuildTower(user, d, 8);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.2F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.6F, "Earthbending01", "Earthbending02");
				}
			}
			if (item.getItem() == mod_Avatar.EarthBuildTowerMasterItem) {
				if(Ability.canStart(user, EarthBuildTower.class)){
					Location d = new Location(user);
					new EarthBuildTower(user, d, 12);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.6F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 2.0F, "Earthbending01", "Earthbending02");
				}
			}
			if (item.getItem() == mod_Avatar.EarthBridgeNoviceItem) {
				if(Ability.canStart(user, EarthBridge.class)){
					Location d = new Location(user);
					new EarthBridge(user, d, 5);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "Earthbending03");
				}
			}
			if (item.getItem() == mod_Avatar.EarthBridgeAdeptItem) {
				if(Ability.canStart(user, EarthBridge.class)){
					Location d = new Location(user);
					new EarthBridge(user, d, 11);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.2F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.6F, "Earthbending03");
				}
			}
			if (item.getItem() == mod_Avatar.EarthBridgeMasterItem) {
				if(Ability.canStart(user, EarthBridge.class)){
					Location d = new Location(user);
					new EarthBridge(user, d, 18);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.6F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 2.0F, "Earthbending03");
				}
			}
			if (item.getItem() == mod_Avatar.EarthOreSenseNoviceItem) {
				if(Ability.canStart(user, EarthOreSense.class)){
					Location d = new Location(user);
					new EarthOreSense(user, 1);
				}
			}
			if (item.getItem() == mod_Avatar.EarthOreSenseAdeptItem) {
				if(Ability.canStart(user, EarthOreSense.class)){
					Location d = new Location(user);
					new EarthOreSense(user, 2);
				}
			}
			if (item.getItem() == mod_Avatar.EarthOreSenseMasterItem) {
				if(Ability.canStart(user, EarthOreSense.class)){
					Location d = new Location(user);
					new EarthOreSense(user, 3);
				}
			}
			if (item.getItem() == mod_Avatar.EarthManipulateItem) {
				if(Ability.canStart(user, EarthThrow.class)){
					BlockBukkit t = BlockBukkit.getTargetBlock(user);	
					new EarthThrow(user, 2500L, t.getLocation(), 0);
				}
			}
			if(item.getItem()== mod_Avatar.EarthManipulateShootNoviceItem) {
				Location l = new Location(user);
				for(Ability a : Ability.Instances){
					if(a instanceof EarthThrow && a.user == user){
						((EarthThrow) a).fireProjectile(l.getDirection().normalize().multiply(0.005), 5, 2);
					}
				}
			}
			if(item.getItem()== mod_Avatar.EarthManipulateShootAdeptItem) {
				Location l = new Location(user);
				for(Ability a : Ability.Instances){
					if(a instanceof EarthThrow && a.user == user){
						((EarthThrow) a).fireProjectile(l.getDirection().normalize().multiply(0.006), 12, 4);
					}
				}
			}
			if(item.getItem()== mod_Avatar.EarthManipulateShootMasterItem) {
				Location l = new Location(user);
				for(Ability a : Ability.Instances){
					if(a instanceof EarthThrow && a.user == user){
						((EarthThrow) a).fireProjectile(l.getDirection().normalize().multiply(0.007), 18, 6);
					}
				}
			}
		}

		/* AIR */
		if (ElementManager.hasElement(user, ElementManager.AIR)) {
			if (item.getItem() == mod_Avatar.AirJumpNoviceItem) {
				if(Ability.canStart(user, AirHighJump.class)){
					new AirHighJump(user, 2000L, 1.2F);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirJumpAdeptItem) {
				if(Ability.canStart(user, AirHighJump.class)){
					new AirHighJump(user, 3000L, 2.2F);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.2F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.6F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirJumpMasterItem) {
				if(Ability.canStart(user, AirHighJump.class)){
					new AirHighJump(user, 5000L, 4F);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.6F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 2.0F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirRingNoviceItem) {
				if(Ability.canStart(user, AirRing.class)){
					new AirRing(user, 3000L, 6, 2, 2, false);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirRingAdeptItem) {
				if(Ability.canStart(user, AirRing.class)){
					new AirRing(user, 5000L, 9, 3, 3, false);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.2F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.6F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirRingMasterItem) {
				if(Ability.canStart(user, AirRing.class)){
					new AirRing(user, 7000L, 16, 5, 5, false);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.6F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 2.0F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirGustNoviceItem) {
				if(Ability.canStart(user, AirGust.class)){
					new AirGust(user, 3000L, 2, 7);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirGustAdeptItem) {
				if(Ability.canStart(user, AirGust.class)){
					new AirGust(user, 4000L, 3, 10);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.2F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.6F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirGustMasterItem) {
				if(Ability.canStart(user, AirGust.class)){
					new AirGust(user, 6000L, 5, 15);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.6F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 2.0F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirSpringNoviceItem) {
				if(Ability.canStart(user, AirSpring.class)){
					new AirSpring(user, 2, 7000);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirSpringAdeptItem) {
				if(Ability.canStart(user, AirSpring.class)){
					new AirSpring(user, 3, 9000);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.2F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.6F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirSpringMasterItem) {
				if(Ability.canStart(user, AirSpring.class)){
					new AirSpring(user, 4, 10000);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.6F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 2.0F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirWindRunNoviceItem) {
				if(Ability.canStart(user, AirWindRun.class)){
					new AirWindRun(user, 5, 4, 0);						
				}
			}
			if (item.getItem() == mod_Avatar.AirWindRunAdeptItem) {
				if(Ability.canStart(user, AirWindRun.class)){
					new AirWindRun(user, 5, 6, 1);						
				}
			}
			if (item.getItem() == mod_Avatar.AirWindRunMasterItem) {
				if(Ability.canStart(user, AirWindRun.class)){
					new AirWindRun(user, 8, 8, 2);						
				}
			}
			if (item.getItem() == mod_Avatar.AirStreamNoviceItem) {
				if(Ability.canStart(user, AirStream.class)){
					Location l = new Location(user, true);
					new AirStream(user, 2000L, l, l.getDirection(), 7, 2, 0);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.5F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirStreamAdeptItem) {
				if(Ability.canStart(user, AirStream.class)){
					Location l = new Location(user, true);
					new AirStream(user, 3000L, l, l.getDirection(), 12, 3, 0);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.6F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.6F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirStreamMasterItem) {
				if(Ability.canStart(user, AirStream.class)){
					Location l = new Location(user, true);
					new AirStream(user, 4000L, l, l.getDirection(), 17, 5, 0);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.7F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 2.0F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirDomeBurstNoviceItem) {
				if(Ability.canStart(user, AirDomeBurst.class)){
					new AirDomeBurst(user, 3000L, 2, 6, false);					
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirDomeBurstAdeptItem) {
				if(Ability.canStart(user, AirDomeBurst.class)){
					new AirDomeBurst(user, 5000L, 3, 10, false);					
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.2F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.6F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirDomeBurstMasterItem) {
				if(Ability.canStart(user, AirDomeBurst.class)){
					new AirDomeBurst(user, 8000L, 5, 15, false);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.6F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 2.0F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirTwisterNoviceItem) {
				if(Ability.canStart(user, AirTornado.class)){
					BlockBukkit t = BlockBukkit.getTargetBlock(user);
					Location l = t.getLocation();
					Location d = new Location(user);
					new AirTornado(user, 15000L, l, d.getDirection(), 50, 2, 5F, 1, 7500L, 100);
				}
			}
			if (item.getItem() == mod_Avatar.AirTwisterAdeptItem) {
				if(Ability.canStart(user, AirTornado.class)){
					BlockBukkit t = BlockBukkit.getTargetBlock(user);
					Location l = t.getLocation();
					Location d = new Location(user);
					new AirTornado(user, 20000L, l, d.getDirection(), 90, 3, 1F, 3, 12000L, 30);
				}
			}
			if (item.getItem() == mod_Avatar.AirTwisterMasterItem) {
				if(Ability.canStart(user, AirTornado.class)){
					BlockBukkit t = BlockBukkit.getTargetBlock(user);
					Location l = t.getLocation();
					Location d = new Location(user);
					new AirTornado(user, 30000L, l, d.getDirection(), 200, 4, 0.8F, 5, 20000L, 40);
				}
			}
			if (item.getItem() == mod_Avatar.AirShieldNoviceItem) {
				if(Ability.canStart(user, AirShield.class)){
					Location l = new Location(user);
					new AirShield(user, 20000L, l, 4, 7500L); //FOR TESTING
				}
			}
			if (item.getItem() == mod_Avatar.AirShieldAdeptItem) {
				if(Ability.canStart(user, AirShield.class)){
					Location l = new Location(user);
					new AirShield(user, 25000L, l, 6, 12500L);
				}
			}
			if (item.getItem() == mod_Avatar.AirShieldMasterItem) {
				if(Ability.canStart(user, AirShield.class)){
					Location l = new Location(user);
					new AirShield(user, 35000L, l, 9, 20000L);
				}
			}
			if (item.getItem() == mod_Avatar.AirDiscNoviceItem) {
				if(Ability.canStart(user, AirDisc.class)){
					Location l = new Location(user);
					new AirDisc(user, 4000L, l.getDirection(), 1, 3000L, 3);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "AirbendingFast05", "AirbendingFast06");
				}
			}
			if (item.getItem() == mod_Avatar.AirDiscAdeptItem) {
				if(Ability.canStart(user, AirDisc.class)){				
					Location l = new Location(user);
					new AirDisc(user, 6000L, l.getDirection(), 2, 7500L, 5);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "AirbendingFast05", "AirbendingFast06");
				}
			}
			if (item.getItem() == mod_Avatar.AirDiscMasterItem) {
				if(Ability.canStart(user, AirDisc.class)){
					Location l = new Location(user);
					new AirDisc(user, 8000L, l.getDirection(), 2, 12500L, 7);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "AirbendingFast05", "AirbendingFast06");
				}
			}
			if (item.getItem() == mod_Avatar.AirPullNoviceItem) {
				if(Ability.canStart(user, AirStream.class)){
					BlockBukkit t = BlockBukkit.getTargetBlock(user, 8);
					Location l = t.getLocation();
					Location d = new Location(user);
					new AirStream(user, 2000L, l, d.getDirection().clone().multiply(-1), (int) user.getDistance(l.getX(), l.getY(), l.getZ()), 1, 0);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.5F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirPullAdeptItem) {
				if(Ability.canStart(user, AirStream.class)){
					BlockBukkit t = BlockBukkit.getTargetBlock(user, 13);
					Location l = t.getLocation();
					Location d = new Location(user);
					new AirStream(user, 3000L, l, d.getDirection().clone().multiply(-1), (int) user.getDistance(l.getX(), l.getY(), l.getZ()), 3, 0);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.6F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.6F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirPullMasterItem) {
				if(Ability.canStart(user, AirStream.class)){
					BlockBukkit t = BlockBukkit.getTargetBlock(user, 18);
					Location l = t.getLocation();
					Location d = new Location(user);
					new AirStream(user, 4000L, l, d.getDirection().clone().multiply(-1), (int) user.getDistance(l.getX(), l.getY(), l.getZ()), 5, 0);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.7F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 2.0F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirRingPullNoviceItem) {
				if(Ability.canStart(user, AirRing.class)){
					new AirRing(user, 3000L, 6, 2, 2, true);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirRingPullAdeptItem) {
				if(Ability.canStart(user, AirRing.class)){
					new AirRing(user, 5000L, 9, 3, 3, true);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.2F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.6F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirRingPullMasterItem) {
				if(Ability.canStart(user, AirRing.class)){
					new AirRing(user, 7000L, 16, 5, 5, true);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.6F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 2.0F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirDomeSuckNoviceItem) {
				if(Ability.canStart(user, AirDomeBurst.class)){
					new AirDomeBurst(user, 3000L, 2, 6, true);					
					SoundHandler.playOnEntityRandom(user.worldObj, user, 0.8F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.3F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirDomeSuckAdeptItem) {
				if(Ability.canStart(user, AirDomeBurst.class)){
					new AirDomeBurst(user, 5000L, 3, 10, true);					
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.2F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 1.6F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}
			if (item.getItem() == mod_Avatar.AirDomeSuckMasterItem) {
				if(Ability.canStart(user, AirDomeBurst.class)){
					new AirDomeBurst(user, 8000L, 5, 15, true);
					SoundHandler.playOnEntityRandom(user.worldObj, user, 1.6F, (user.worldObj.rand.nextInt(6) - 3) * 0.1F + 2.0F, "Airbending01", "Airbending02", "Airbending03", "Airbending04");
				}
			}

		}
	}
}
