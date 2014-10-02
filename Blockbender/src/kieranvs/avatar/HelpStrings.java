package kieranvs.avatar;

import cpw.mods.fml.common.registry.GameRegistry;
import kieranvs.avatar.item.ItemSpellTome;

public class HelpStrings {
	
	public static String getHelpMessage(ItemSpellTome i){
		if(i.getHelpMessage() != null){
			return i.getHelpMessage();
		}
		return "No help";
	}
	
	public static void loadHelpMessages(){
		mod_Avatar.FireExplosionNoviceItem.setHelpMessage("Create an explosion. Hold shift to charge.");
		mod_Avatar.FireExplosionAdeptItem.setHelpMessage("Create an explosion. Hold shift to charge.");
		mod_Avatar.FireExplosionMasterItem.setHelpMessage("Create an explosion. Hold shift to charge.");
		mod_Avatar.FireLightningNoviceItem.setHelpMessage("Strike lightning. Hold shift to charge.");
		mod_Avatar.FireLightningAdeptItem.setHelpMessage("Strike lightning. Hold shift to charge.");
		mod_Avatar.FireLightningMasterItem.setHelpMessage("Strike lightning. Hold shift to charge.");
		mod_Avatar.FireFireballNoviceItem.setHelpMessage("Launch a weak fireball.");
		mod_Avatar.FireFireballAdeptItem.setHelpMessage("Launch a fireball.");
		mod_Avatar.FireFireballMasterItem.setHelpMessage("Launch a powerful fireball.");
		mod_Avatar.FireBlastNoviceItem.setHelpMessage("Shoot a simple blast of fire.");
		mod_Avatar.FireBlastAdeptItem.setHelpMessage("Shoot a large blast of fire.");
		mod_Avatar.FireBlastMasterItem.setHelpMessage("Shoot a powerful blast of blue fire.");
		mod_Avatar.FireFlightNoviceItem.setHelpMessage("Propel yourself into flight with fire. Does not work when raining, or when over water.");
		mod_Avatar.FireFlightAdeptItem.setHelpMessage("Propel yourself into flight with fire. Does not work when raining, or when over water.");
		mod_Avatar.FireFlightMasterItem.setHelpMessage("Propel yourself into flight with fire. Does not work when raining, or when over water.");
		mod_Avatar.FireJetNoviceItem.setHelpMessage("Create a short stream of fire through the air.");
		mod_Avatar.FireJetAdeptItem.setHelpMessage("Create a stream of fire through the air.");
		mod_Avatar.FireJetMasterItem.setHelpMessage("Create a long stream of fire through the air.");
		mod_Avatar.FireWallNoviceItem.setHelpMessage("Create a small, blazing wall of fire.");
		mod_Avatar.FireWallAdeptItem.setHelpMessage("Create a blazing wall of fire.");
		mod_Avatar.FireWallMasterItem.setHelpMessage("Create a large, blazing wall of fire.");
		mod_Avatar.FireStreamNoviceItem.setHelpMessage("Create a stream of fire on the ground.");
		mod_Avatar.FireStreamAdeptItem.setHelpMessage("Create a long stream of fire on the ground.");
		mod_Avatar.FireStreamMasterItem.setHelpMessage("Create a powerful stream of blue fire on the ground.");
		mod_Avatar.FireRingNoviceItem.setHelpMessage("Send fire out in all directions on the ground.");
		mod_Avatar.FireRingAdeptItem.setHelpMessage("Send fire out in all directions on the ground.");
		mod_Avatar.FireRingMasterItem.setHelpMessage("Send blue fire out in all directions on the ground.");
		mod_Avatar.FireStarNoviceItem.setHelpMessage("Send streams of fire out in all directions on the ground.");
		mod_Avatar.FireStarAdeptItem.setHelpMessage("Send streams of fire out in all directions on the ground.");
		mod_Avatar.FireStarMasterItem.setHelpMessage("Send streams of fire out in all directions on the ground.");
		mod_Avatar.FireMeltNoviceItem.setHelpMessage("Use to melt ice or obsidian.");
		mod_Avatar.FireMeltAdeptItem.setHelpMessage("Use to melt ice or obsidian.");
		mod_Avatar.FireMeltMasterItem.setHelpMessage("Use to melt ice or obsidian.");
		
		mod_Avatar.EarthRockThrowNoviceItem.setHelpMessage("Throw a small rock that explodes on impact.");
		mod_Avatar.EarthRockThrowAdeptItem.setHelpMessage("Throw a small rock that explodes on impact.");
		mod_Avatar.EarthRockThrowMasterItem.setHelpMessage("Throw a small rock that explodes on impact.");
		mod_Avatar.EarthRaisePlatformNoviceItem.setHelpMessage("Raise a collumn of earth below you.");
		mod_Avatar.EarthRaisePlatformAdeptItem.setHelpMessage("Raise a tall collumn of earth below you.");
		mod_Avatar.EarthRaisePlatformMasterItem.setHelpMessage("Raise a thick collumn of earth below you.");
		mod_Avatar.EarthWallNoviceItem.setHelpMessage("Raise a small wall at your feet. Be warned - it doesn't last forever!");
		mod_Avatar.EarthWallAdeptItem.setHelpMessage("Raise a wall at your feet. Be warned - it doesn't last forever!");
		mod_Avatar.EarthWallMasterItem.setHelpMessage("Raise a big wall at your feet. Be warned - it doesn't last forever!");
		mod_Avatar.EarthTentNoviceItem.setHelpMessage("Raise a small tent to protect yourself.");
		mod_Avatar.EarthLaunchNoviceItem.setHelpMessage("Launch entities by bringing earth up wherever you're looking.");
		mod_Avatar.EarthLaunchAdeptItem.setHelpMessage("Launch entities by bringing earth up wherever you're looking.");
		mod_Avatar.EarthLaunchMasterItem.setHelpMessage("Launch entities by bringing earth up wherever you're looking.");
		mod_Avatar.EarthTunnelColumnNoviceItem.setHelpMessage("Dig into the earth.");
		mod_Avatar.EarthTunnelColumnAdeptItem.setHelpMessage("Dig into the earth.");
		mod_Avatar.EarthTunnelColumnMasterItem.setHelpMessage("Dig deep into the earth.");
		mod_Avatar.EarthBuildWallNoviceItem.setHelpMessage("Build a small permanent wall in front of you.");
		mod_Avatar.EarthBuildWallAdeptItem.setHelpMessage("Build a permanent wall in front of you.");
		mod_Avatar.EarthBuildWallMasterItem.setHelpMessage("Build a large permanent wall in front of you.");
		mod_Avatar.EarthBuildTowerNoviceItem.setHelpMessage("Build a small permanent tower at your feet.");
		mod_Avatar.EarthBuildTowerAdeptItem.setHelpMessage("Build a permanent tower at your feet.");
		mod_Avatar.EarthBuildTowerMasterItem.setHelpMessage("Build a tall permanent tower at your feet.");
		mod_Avatar.EarthBridgeNoviceItem.setHelpMessage("Build a short bridge at your feet.");
		mod_Avatar.EarthBridgeAdeptItem.setHelpMessage("Build a bridge at your feet.");
		mod_Avatar.EarthBridgeMasterItem.setHelpMessage("Build a long bridge at your feet.");
		mod_Avatar.EarthOreSenseNoviceItem.setHelpMessage("Feel the impurities in the earth below you to detect the presence of nearby ores.");
		mod_Avatar.EarthOreSenseAdeptItem.setHelpMessage("Feel the impurities in the earth below you to detect the presence and type of ores.");
		mod_Avatar.EarthOreSenseMasterItem.setHelpMessage("Feel the impurities in the earth below you to detect the presence, type and location of ores.");
		mod_Avatar.EarthManipulateItem.setHelpMessage("Hold shift and click an earth block to control it.");
		mod_Avatar.EarthManipulateShootNoviceItem.setHelpMessage("Launch a block that you are controlling.");
		mod_Avatar.EarthManipulateShootAdeptItem.setHelpMessage("Launch a block that you are controlling.");
		mod_Avatar.EarthManipulateShootMasterItem.setHelpMessage("Launch a block that you are controlling.");
	
		mod_Avatar.AirJumpNoviceItem.setHelpMessage("Launch yourself upwards. Or use to fly up with a glider.");
		mod_Avatar.AirJumpAdeptItem.setHelpMessage("Launch yourself upwards. Or use to fly up with a glider.");
		mod_Avatar.AirJumpMasterItem.setHelpMessage("Launch yourself upwards. Or use to fly up with a glider.");
		mod_Avatar.AirRingNoviceItem.setHelpMessage("Push air outwards in a ring.");
		mod_Avatar.AirRingAdeptItem.setHelpMessage("Push air outwards in a ring.");
		mod_Avatar.AirRingMasterItem.setHelpMessage("Push air outwards in a ring.");
		mod_Avatar.AirGustNoviceItem.setHelpMessage("Blast air in front of you.");
		mod_Avatar.AirGustAdeptItem.setHelpMessage("Blast air in front of you.");
		mod_Avatar.AirGustMasterItem.setHelpMessage("Blast air in front of you.");
		mod_Avatar.AirSpringNoviceItem.setHelpMessage("Launch yourself into the air wherever you're looking.");
		mod_Avatar.AirSpringAdeptItem.setHelpMessage("Launch yourself into the air wherever you're looking.");
		mod_Avatar.AirSpringMasterItem.setHelpMessage("Launch yourself into the air wherever you're looking.");
		mod_Avatar.AirWindRunNoviceItem.setHelpMessage("Run super fast.");
		mod_Avatar.AirWindRunAdeptItem.setHelpMessage("Run super fast.");
		mod_Avatar.AirWindRunMasterItem.setHelpMessage("Run super fast.");
		mod_Avatar.AirStreamNoviceItem.setHelpMessage("Push a stream of air.");
		mod_Avatar.AirStreamAdeptItem.setHelpMessage("Push a stream of air.");
		mod_Avatar.AirStreamMasterItem.setHelpMessage("Push a stream of air.");
		mod_Avatar.AirDomeBurstNoviceItem.setHelpMessage("Push air outwards in a sphere.");
		mod_Avatar.AirDomeBurstAdeptItem.setHelpMessage("Push air outwards in a sphere.");
		mod_Avatar.AirDomeBurstMasterItem.setHelpMessage("Push air outwards in a sphere.");
		mod_Avatar.AirTwisterNoviceItem.setHelpMessage("Create a small whirling tornado.");
		mod_Avatar.AirTwisterAdeptItem.setHelpMessage("Create a whirling tornado.");
		mod_Avatar.AirTwisterMasterItem.setHelpMessage("Create a big whirling tornado.");
		mod_Avatar.AirShieldNoviceItem.setHelpMessage("Create a shield of wind around you, pushing back entities.");
		mod_Avatar.AirShieldAdeptItem.setHelpMessage("Create a shield of wind around you, pushing back entities.");
		mod_Avatar.AirShieldMasterItem.setHelpMessage("Create a shield of wind around you, pushing back entities.");
		mod_Avatar.AirDiscNoviceItem.setHelpMessage("Shoot a thin, sharp blade of air.");
		mod_Avatar.AirDiscAdeptItem.setHelpMessage("Shoot a thin, sharp blade of air.");
		mod_Avatar.AirDiscMasterItem.setHelpMessage("Shoot a thin, sharp blade of air.");
		mod_Avatar.AirPullNoviceItem.setHelpMessage("Pull a stream of air towards you.");
		mod_Avatar.AirPullAdeptItem.setHelpMessage("Pull a stream of air towards you.");
		mod_Avatar.AirPullMasterItem.setHelpMessage("Pull a stream of air towards you.");
		mod_Avatar.AirRingPullNoviceItem.setHelpMessage("Pull a ring of air towards you.");
		mod_Avatar.AirRingPullAdeptItem.setHelpMessage("Pull a ring of air towards you.");
		mod_Avatar.AirRingPullMasterItem.setHelpMessage("Pull a ring of air towards you.");
		mod_Avatar.AirDomeSuckNoviceItem.setHelpMessage("Pull a dome of air towards you.");
		mod_Avatar.AirDomeSuckAdeptItem.setHelpMessage("Pull a dome of air towards you.");
		mod_Avatar.AirDomeSuckMasterItem.setHelpMessage("Pull a dome of air towards you.");
	
		mod_Avatar.WaterIceStreamNoviceItem.setHelpMessage("Freeze water into a stream of ice where you are aiming.");
		mod_Avatar.WaterIceStreamAdeptItem.setHelpMessage("Freeze water into a stream of ice where you are aiming.");
		mod_Avatar.WaterIceStreamMasterItem.setHelpMessage("Freeze water into a stream of ice where you are aiming.");
		mod_Avatar.WaterIceRingNoviceItem.setHelpMessage("Freeze water into a ring of ice where you are aiming.");
		mod_Avatar.WaterIceRingAdeptItem.setHelpMessage("Freeze water into a ring of ice where you are aiming.");
		mod_Avatar.WaterIceRingMasterItem.setHelpMessage("Freeze water into a ring of ice where you are aiming.");
		mod_Avatar.WaterFreezeWaterfallNoviceItem.setHelpMessage("Freeze a 1 block thin waterfall of water.");
		mod_Avatar.WaterIceWallNoviceItem.setHelpMessage("Raise a small temporary wall of ice at your feet.");
		mod_Avatar.WaterIceWallAdeptItem.setHelpMessage("Raise a temporary wall of ice at your feet.");
		mod_Avatar.WaterIceWallMasterItem.setHelpMessage("Raise a large temporary wall of ice at your feet.");
		mod_Avatar.WaterSnowWallNoviceItem.setHelpMessage("Raise a small temporary wall of snow at your feet.");
		mod_Avatar.WaterSnowWallAdeptItem.setHelpMessage("Raise a temporary wall of snow at your feet.");
		mod_Avatar.WaterSnowWallMasterItem.setHelpMessage("Raise a large temporary wall of snow at your feet.");
		mod_Avatar.WaterSnowballNoviceItem.setHelpMessage("Throw a snowball.");
		mod_Avatar.WaterSnowballAdeptItem.setHelpMessage("Throw a volley of snowballs.");
		mod_Avatar.WaterSnowballMasterItem.setHelpMessage("Throw a dense volley of snowballs.");
		mod_Avatar.WaterManipulateItem.setHelpMessage("Hold shift and click a water block to control a blob of water.");
		mod_Avatar.WaterManipulateShootNoviceItem.setHelpMessage("Click to shoot a blob of water or ice under your control.");
		mod_Avatar.WaterManipulateFreezeItem.setHelpMessage("Click to freeze a blob of water under your control.");
		mod_Avatar.WaterOctopusFormNoviceItem.setHelpMessage("Hold shift and click to protect yourself with octopus tentacles of water.");
		
	}
	
	private static final String[] fireHelp = {
		"Use to create an explosion where you look. Hold shift to charge up.",
		"Use to create an explosion where you look. Hold shift to charge up.",
		"Use to create an explosion where you look. Hold shift to charge up.",
		"Use to strike lightning where you look. Hold shift to charge up.",
		"Use to strike lightning where you look. Hold shift to charge up.",
		"Use to strike lightning where you look. Hold shift to charge up.",
		"Use to launch a weak fireball.",
		"Use to launch a fireball.",
		"Use to launch a powerful fireball.",
		"Use to shoot a simple blast of fire.",
		"Use to shoot a large blast of fire.",
		"Use to shoot a powerful blast of blue fire.",
		"Use to propel yourself into flight with fire. Does not work when raining, or when over water.",
		"Use to propel yourself into flight with fire. Does not work when raining, or when over water.",
		"Use to propel yourself into flight with fire. Does not work when raining, or when over water.",
		"Use to create a short stream of fire through the air.",
		"Use to create a stream of fire through the air.",
		"Use to create a long stream of fire through the air.",
		"Use to create a temporary wall of fire.",
		"Use to create a temporary wall of fire.",
		"Use to create a temporary wall of fire.",
		"Use to create a stream of fire on the ground.",
		"Use to create a long stream of fire on the ground.",
		"Use to create a powerful stream of blue fire on the ground.",
		"Use to send fire out in all directions on the ground.",
		"Use to send fire out in all directions on the ground.",
		"Use to send blue fire out in all directions on the ground.",
		"Use to send streams of fire out in all directions on the ground.",
		"Use to send streams of fire out in all directions on the ground.",
		"Use to send streams of blue fire out in all directions on the ground.",
		"Use to create a fire whip. [Error: Broken/Buggy]",
		"Use to create a fire whip. [Error: Broken/Buggy]",
		"Use to create a fire whip. [Error: Broken/Buggy]",
		"Use to melt ice or obsidian.",
		"Use to melt ice or obsidian.",
		"Use to melt ice or obsidian.",
		"Hold shift and use to create a small, temporary shield of fire.", 
		"Hold shift and use to create a temporary shield of fire.", 
		"Hold shift and use to create a large, temporary shield of fire."
	};
	
	private static final String[] waterHelp = {
		"Use to freeze water into a stream of ice where you are aiming.",
		"Use to freeze water into a stream of ice where you are aiming.",
		"Use to freeze water into a stream of ice where you are aiming.",
		"Use to freeze water into a circle of ice where you are aiming.",
		"Use to freeze water into a circle of ice where you are aiming.",
		"Use to freeze water into a circle of ice where you are aiming.",
		"Use to manipulate water. [Error: Broken/Buggy]",
		"Use to manipulate water. [Error: Broken/Buggy]",
		"Use to manipulate water. [Error: Broken/Buggy]",
		"Use to create a wave. [Error: Broken/Buggy]",
		"Use to create a wave. [Error: Broken/Buggy]",
		"Use to create a wave. [Error: Broken/Buggy]",
		"Use to freeze a waterfall.",
		"Use to create a temporary wall of ice at your targeted block.",
		"Use to create a temporary wall of ice at your targeted block.",
		"Use to create a temporary wall of ice at your targeted block.",
		"Use to create a temporary wall of snow at your targeted block.",
		"Use to create a temporary wall of snow at your targeted block.",
		"Use to create a temporary wall of snow at your targeted block.",
		"Use to launch one snowball.",
		"Use to launch a volley of snowballs.",
		"Use to launch a dense volley of snowballs.",
		"Hold shift and use to create a shield of water.",
		"Click when you have a blob of water to shoot it through the air"
	};
	
	private static final String[] earthHelp = {
		"Use to shoot a stream of earth. [Error: Broken/Buggy]",
		"Use to shoot a stream of earth. [Error: Broken/Buggy]",
		"Use to shoot a stream of earth. [Error: Broken/Buggy]",
		"Use to create an impact ring in the ground. [Error: Broken/Buggy]",
		"Use to create an impact ring in the ground. [Error: Broken/Buggy]",
		"Use to create an impact ring in the ground. [Error: Broken/Buggy]",
		"Hold shift and use while standing on a metal armour block to wear armour.",
		"Use to lob an exploding rock of badassery. :D",
		"Use to raise a platform of earth.",
		"Use to raise a platform of earth.",
		"Use to raise a platform of earth.",
		"Use to create a temporary wall of earth at your targeted block.",
		"Use to create a temporary wall of earth at your targeted block.",
		"Use to create a temporary wall of earth at your targeted block.",
		"Use to create a stone tent for protection.",
		"Use to create a stone tent for protection.",
		"Use to create a stone tent for protection.",
		"Use to make some weird shit happen... [Error: Broken/Buggy]",
		"Use to make some weird shit happen... [Error: Broken/Buggy]",
		"Use to make some weird shit happen... [Error: Broken/Buggy]",
		"Use to launch a creature or person into the air.",
		"Use to launch a creature or person into the air.",
		"Use to launch a creature or person into the air.",
		"Use to crash your game lol. [Error: Broken/Buggy]",
		"Use to crash your game lol. [Error: Broken/Buggy]",
		"Use to crash your game lol. [Error: Broken/Buggy]",
		"Use to construct a wall for a building.",
		"Use to construct a wall for a building.",
		"Use to construct a wall for a building.",
		"Use to construct a tower for a building.",
		"Use to construct a tower for a building.",
		"Use to construct a tower for a building.",
		"Use to construct a bridge.",
		"Use to construct a bridge.",
		"Use to construct a bridge.",
		"Use to detect nearby ores.",
		"Use to detect nearby ores.",
		"Use to detect nearby ores.",
		"Use to make some weird shit happen... [Error: Broken/Buggy]"
	};
	
	private static final String[] airHelp = {
		"Use to shoot a deadly disc of air.",
	};

}
