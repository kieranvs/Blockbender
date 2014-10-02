package kieranvs.avatar;

import java.io.File;

import kieranvs.avatar.bending.Ability;
import net.minecraftforge.common.config.Configuration;

public class Configurator {
	
	Configuration cfg;
	
	public void doTheThing(File file){
		cfg = new Configuration(file);
		cfg.load();
		loadValues();
		cfg.save();
	}
	
	private void loadValues(){
		Ability.disableCooldown = cfg.get("General", "DisableCooldowns", false).getBoolean(false);
		mod_Avatar.shouldSpawnStructures = cfg.get("General", "ShouldSpawnStructures", true).getBoolean(true);
		mod_Avatar.shouldSpawnPlants = cfg.get("General", "ShouldSpawnPlants", true).getBoolean(true);
		mod_Avatar.isAvatarAllowed = cfg.get("General", "IsAvatarEnabled", false, "Even when true, only server ops can do /bending avatar").getBoolean(false);
	}

}