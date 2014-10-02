package kieranvs.avatar;

import java.util.EnumMap;

import kieranvs.avatar.bending.BendingListener;
import kieranvs.avatar.block.BlockAirVent;
import kieranvs.avatar.block.BlockColdDecorative;
import kieranvs.avatar.block.BlockColdDecorativeSlabs;
import kieranvs.avatar.block.BlockElementBlock;
import kieranvs.avatar.block.BlockGrassCustom;
import kieranvs.avatar.block.BlockMetalArmor;
import kieranvs.avatar.block.BlockRiceCrop;
import kieranvs.avatar.block.BlockSeaPrunePlant;
import kieranvs.avatar.block.BlockSeaweedPlant;
import kieranvs.avatar.block.BlockSpiritPortal;
import kieranvs.avatar.block.BlockStatueKyoshi;
import kieranvs.avatar.block.BlockStatueRoku;
import kieranvs.avatar.block.BlockStatueYangchen;
import kieranvs.avatar.block.BlockTreeOfTimeWood;
import kieranvs.avatar.block.BlueFire;
import kieranvs.avatar.block.TileEntityAirVent;
import kieranvs.avatar.block.TileEntityBlueFire;
import kieranvs.avatar.block.TileEntityKyoshiStatue;
import kieranvs.avatar.block.TileEntityRokuStatue;
import kieranvs.avatar.block.TileEntityYangchenStatue;
import kieranvs.avatar.client.AirTokenTab;
import kieranvs.avatar.client.Data;
import kieranvs.avatar.client.EarthTokenTab;
import kieranvs.avatar.client.FireTokenTab;
import kieranvs.avatar.client.WaterTokenTab;
import kieranvs.avatar.command.CommandBending;
import kieranvs.avatar.command.CommandRegion;
import kieranvs.avatar.entity.EntityAirBall;
import kieranvs.avatar.entity.EntityAirMan;
import kieranvs.avatar.entity.EntityAirWoman;
import kieranvs.avatar.entity.EntityArcherBandit;
import kieranvs.avatar.entity.EntityBadgerMole;
import kieranvs.avatar.entity.EntityBandit;
import kieranvs.avatar.entity.EntityBison;
import kieranvs.avatar.entity.EntityBlock;
import kieranvs.avatar.entity.EntityEarthBandit;
import kieranvs.avatar.entity.EntityEarthMan;
import kieranvs.avatar.entity.EntityEarthWoman;
import kieranvs.avatar.entity.EntityFireBandit;
import kieranvs.avatar.entity.EntityFireMan;
import kieranvs.avatar.entity.EntityFireWoman;
import kieranvs.avatar.entity.EntityGlider;
import kieranvs.avatar.entity.EntityOtterPenguin;
import kieranvs.avatar.entity.EntityPolarBearDog;
import kieranvs.avatar.entity.EntityRock;
import kieranvs.avatar.entity.EntityWaterBandit;
import kieranvs.avatar.entity.EntityWaterMan;
import kieranvs.avatar.entity.EntityWaterWoman;
import kieranvs.avatar.generation.BiomeSpirit;
import kieranvs.avatar.generation.WorldGenAirNomadTemple;
import kieranvs.avatar.generation.WorldGenAirNomadVillageOne;
import kieranvs.avatar.generation.WorldGenAirNomadVillageTwo;
import kieranvs.avatar.generation.WorldGenAirNomadWatchTower;
import kieranvs.avatar.generation.WorldGenEarthKingdomCompound;
import kieranvs.avatar.generation.WorldGenEarthKingdomHouseOne;
import kieranvs.avatar.generation.WorldGenEarthKingdomHouseTwo;
import kieranvs.avatar.generation.WorldGenEarthKingdomTower;
import kieranvs.avatar.generation.WorldGenFireNationHouse;
import kieranvs.avatar.generation.WorldGenFireNationTower;
import kieranvs.avatar.generation.WorldGenSeaPlants;
import kieranvs.avatar.generation.WorldGenWaterNorthTribeVillage;
import kieranvs.avatar.generation.WorldGenWaterNorthWatchTower;
import kieranvs.avatar.generation.WorldGenWaterSouthTribeVillage;
import kieranvs.avatar.generation.WorldGenWaterSouthWatchTower;
import kieranvs.avatar.generation.WorldProviderSpiritWorld;
import kieranvs.avatar.item.ItemAirStaff;
import kieranvs.avatar.item.ItemBendingSatchel;
import kieranvs.avatar.item.ItemFoodCabbage;
import kieranvs.avatar.item.ItemFoodCustardTart;
import kieranvs.avatar.item.ItemFoodFruitPie;
import kieranvs.avatar.item.ItemFoodMoonPeach;
import kieranvs.avatar.item.ItemFoodRiceBowl;
import kieranvs.avatar.item.ItemFoodSeaPrune;
import kieranvs.avatar.item.ItemFoodSeaPruneStew;
import kieranvs.avatar.item.ItemFoodSeaSquid;
import kieranvs.avatar.item.ItemFoodSeaSquidSoup;
import kieranvs.avatar.item.ItemFoodSeaweed;
import kieranvs.avatar.item.ItemFoodSeaweedBread;
import kieranvs.avatar.item.ItemFoodSeaweedSoup;
import kieranvs.avatar.item.ItemGlider;
import kieranvs.avatar.item.ItemGliderWing;
import kieranvs.avatar.item.ItemIceSlab;
import kieranvs.avatar.item.ItemMetalArmor;
import kieranvs.avatar.item.ItemMoney;
import kieranvs.avatar.item.ItemPackedIceSlab;
import kieranvs.avatar.item.ItemRiceGrain;
import kieranvs.avatar.item.ItemSnowSlab;
import kieranvs.avatar.item.ItemSpellTome;
import kieranvs.avatar.util.GenerationUtils;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.command.CommandHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = mod_Avatar.modid, name = "Avatar", version = mod_Avatar.version)
public class mod_Avatar {

	public static final String modid = "kieranvs_avatar";
	public static final String version = "0.7.4";

	public static Data data = new Data();
	
	public static int dimensionIdSpiritWorld = 8;

	/* Blocks */
	public static BlockElementBlock EarthBlock, EarthKingdomBlock, WaterBlock, WaterTribeBlock, AirBlock, AirNomadsBlock, FireBlock, FireNationBlock;
	public static BlueFire BlueFireIns = new BlueFire();
	public static BlockSeaPrunePlant SeaPrunePlantBlock;
	public static BlockRiceCrop RiceCropBlock;
	public static BlockMetalArmor MetalArmorBlock;
	public static BlockStatueRoku StatueRokuBlock;
	public static BlockStatueKyoshi StatueKyoshiBlock;
	public static BlockStatueYangchen StatueYangchenBlock;
	public static BlockSeaweedPlant SeaweedPlantBlock;
	public static BlockAirVent AirVentBlock;
	public static BlockSpiritPortal SpiritPortalBlock;
	public static BlockTreeOfTimeWood TreeOfTimeWoodBlock;
	public static BlockGrassCustom CharredGrassBlock, SpiritGrassBlock, YellowGrassBlock;
	public static BlockColdDecorative IceBrick, IceBrickChiseled, SnowBrick, SnowBrickChiseled, PackedIceBrick, PackedIceBrickChiseled;
	public static BlockSlab IceSlab, SnowSlab, PackedIceSlab, IceDoubleSlab, SnowDoubleSlab, PackedIceDoubleSlab;
	
	/* Items */

	/* Spell Tomes */
	/* Fire */
	public static ItemSpellTome FireExplosionNoviceItem, FireExplosionAdeptItem, FireExplosionMasterItem;
	public static ItemSpellTome FireLightningNoviceItem, FireLightningAdeptItem, FireLightningMasterItem;
	public static ItemSpellTome FireFireballNoviceItem, FireFireballAdeptItem, FireFireballMasterItem;
	public static ItemSpellTome FireBlastNoviceItem, FireBlastAdeptItem, FireBlastMasterItem;
	public static ItemSpellTome FireFlightNoviceItem, FireFlightAdeptItem, FireFlightMasterItem;
	public static ItemSpellTome FireJetNoviceItem, FireJetAdeptItem, FireJetMasterItem;
	public static ItemSpellTome FireWallNoviceItem, FireWallAdeptItem, FireWallMasterItem;
	public static ItemSpellTome FireStreamNoviceItem, FireStreamAdeptItem, FireStreamMasterItem;
	public static ItemSpellTome FireRingNoviceItem, FireRingAdeptItem, FireRingMasterItem;
	public static ItemSpellTome FireStarNoviceItem, FireStarAdeptItem, FireStarMasterItem;
	public static ItemSpellTome FireMeltNoviceItem, FireMeltAdeptItem, FireMeltMasterItem;

	/* Water */
	public static ItemSpellTome WaterIceStreamNoviceItem, WaterIceStreamAdeptItem, WaterIceStreamMasterItem;
	public static ItemSpellTome WaterIceBridgeNoviceItem, WaterIceBridgeAdeptItem, WaterIceBridgeMasterItem;
	public static ItemSpellTome WaterIceRingNoviceItem, WaterIceRingAdeptItem, WaterIceRingMasterItem;
	public static ItemSpellTome WaterManipulateItem;
	public static ItemSpellTome WaterFreezeWaterfallNoviceItem;
	public static ItemSpellTome WaterIceWallNoviceItem, WaterIceWallAdeptItem, WaterIceWallMasterItem;
	public static ItemSpellTome WaterSnowWallNoviceItem, WaterSnowWallAdeptItem, WaterSnowWallMasterItem;
	public static ItemSpellTome WaterSnowballNoviceItem, WaterSnowballAdeptItem, WaterSnowballMasterItem;
	public static ItemSpellTome WaterManipulateShootNoviceItem, WaterManipulateShootAdeptItem, WaterManipulateShootMasterItem;
	public static ItemSpellTome WaterManipulateFreezeItem;
	public static ItemSpellTome WaterOctopusFormNoviceItem;
	public static ItemSpellTome WaterMeltNoviceItem, WaterMeltAdeptItem, WaterMeltMasterItem;

	/* Earth */
	public static ItemSpellTome EarthRockThrowNoviceItem, EarthRockThrowAdeptItem, EarthRockThrowMasterItem;
	public static ItemSpellTome EarthRaisePlatformNoviceItem, EarthRaisePlatformAdeptItem, EarthRaisePlatformMasterItem;
	public static ItemSpellTome EarthWallNoviceItem, EarthWallAdeptItem, EarthWallMasterItem;
	public static ItemSpellTome EarthTentNoviceItem;
	public static ItemSpellTome EarthManipulateItem;
	public static ItemSpellTome EarthManipulateShootNoviceItem, EarthManipulateShootAdeptItem, EarthManipulateShootMasterItem;
	public static ItemSpellTome EarthLaunchNoviceItem, EarthLaunchAdeptItem, EarthLaunchMasterItem;
	public static ItemSpellTome EarthTunnelColumnNoviceItem, EarthTunnelColumnAdeptItem, EarthTunnelColumnMasterItem;
	public static ItemSpellTome EarthBuildWallNoviceItem, EarthBuildWallAdeptItem, EarthBuildWallMasterItem;
	public static ItemSpellTome EarthBuildTowerNoviceItem, EarthBuildTowerAdeptItem, EarthBuildTowerMasterItem;
	public static ItemSpellTome EarthBridgeNoviceItem, EarthBridgeAdeptItem, EarthBridgeMasterItem;
	public static ItemSpellTome EarthOreSenseNoviceItem, EarthOreSenseAdeptItem, EarthOreSenseMasterItem;

	/* Air */
	public static ItemSpellTome AirGustNoviceItem, AirGustAdeptItem, AirGustMasterItem;
	public static ItemSpellTome AirRingNoviceItem, AirRingAdeptItem, AirRingMasterItem;
	public static ItemSpellTome AirJumpNoviceItem, AirJumpAdeptItem, AirJumpMasterItem;
	public static ItemSpellTome AirSpringNoviceItem, AirSpringAdeptItem, AirSpringMasterItem;
	public static ItemSpellTome AirWindRunNoviceItem, AirWindRunAdeptItem, AirWindRunMasterItem;
	public static ItemSpellTome AirStreamNoviceItem, AirStreamAdeptItem, AirStreamMasterItem;
	public static ItemSpellTome AirDomeBurstNoviceItem, AirDomeBurstAdeptItem, AirDomeBurstMasterItem;
	public static ItemSpellTome AirTwisterNoviceItem, AirTwisterAdeptItem, AirTwisterMasterItem;
	public static ItemSpellTome AirShieldNoviceItem, AirShieldAdeptItem, AirShieldMasterItem;
	public static ItemSpellTome AirDiscNoviceItem, AirDiscAdeptItem, AirDiscMasterItem;
	public static ItemSpellTome AirPullNoviceItem, AirPullAdeptItem, AirPullMasterItem;
	public static ItemSpellTome AirRingPullNoviceItem, AirRingPullAdeptItem, AirRingPullMasterItem;
	public static ItemSpellTome AirDomeSuckNoviceItem, AirDomeSuckAdeptItem, AirDomeSuckMasterItem;
	
	/* Other Items */
	public static ItemBendingSatchel BendingSatchelItem;
	/* Currency */
	public static ItemMoney MoneyFireGoldItem, MoneyFireSilverItem, MoneyFireBronzeItem;

	/* Food */
	public static ItemFoodSeaPrune SeaPruneItem;
	public static ItemFoodSeaPruneStew SeaPruneStewItem;
	public static ItemFoodSeaSquid SeaSquidItem;
	public static ItemFoodSeaSquidSoup SeaSquidSoupItem;
	public static ItemFoodSeaweed SeaweedItem;
	public static ItemFoodSeaweedSoup SeaweedSoupItem;
	public static ItemFoodSeaweedBread SeaweedBreadItem;
	public static ItemSeeds RiceGrainItem;
	public static ItemFoodFruitPie FruitPieItem;
	public static ItemFoodCustardTart CustardTartItem;
	public static ItemFoodRiceBowl RiceBowlItem;
	public static ItemFoodMoonPeach MoonPeachItem;
	public static ItemFoodCabbage CabbageItem;
	
	/* Tools */
	public static Item MetalHelmet, MetalChestplate, MetalLeggings, MetalBoots;
	public static ItemAirStaff AirStaffItem = new ItemAirStaff();
	public static ItemGlider GliderItemWhite = new ItemGlider(0);
	public static ItemGlider GliderItemOrange = new ItemGlider(1);
	public static ItemGlider GliderItemMagenta = new ItemGlider(2);
	public static ItemGlider GliderItemLightBlue = new ItemGlider(3);
	public static ItemGlider GliderItemYellow = new ItemGlider(4);
	public static ItemGlider GliderItemLightGreen = new ItemGlider(5);
	public static ItemGlider GliderItemPink = new ItemGlider(6);
	public static ItemGlider GliderItemGrey = new ItemGlider(7);
	public static ItemGlider GliderItemLightGrey = new ItemGlider(8);
	public static ItemGlider GliderItemCyan = new ItemGlider(9);
	public static ItemGlider GliderItemPurple = new ItemGlider(10);
	public static ItemGlider GliderItemBlue = new ItemGlider(11);
	public static ItemGlider GliderItemBrown = new ItemGlider(12);
	public static ItemGlider GliderItemGreen = new ItemGlider(13);
	public static ItemGlider GliderItemRed = new ItemGlider(14);
	public static ItemGlider GliderItemBlack = new ItemGlider(15);
	public static ItemGliderWing GliderWingItem;

	/* World generators */
	public static WorldGenSeaPlants SeaPruneGen, SeaweedGen; 
	public static WorldGenWaterSouthTribeVillage WaterSouthTribeVillageGen = new WorldGenWaterSouthTribeVillage();
	public static WorldGenWaterNorthTribeVillage WaterNorthTribeVillageGen = new WorldGenWaterNorthTribeVillage();
	public static WorldGenWaterSouthWatchTower WaterSouthWatchTowerGen = new WorldGenWaterSouthWatchTower();
	public static WorldGenWaterNorthWatchTower WaterNorthWatchTowerGen = new WorldGenWaterNorthWatchTower();
	public static WorldGenAirNomadVillageOne AirNomadVillageOneGen = new WorldGenAirNomadVillageOne();
	public static WorldGenAirNomadVillageTwo AirNomadVillageTwoGen = new WorldGenAirNomadVillageTwo();
	public static WorldGenAirNomadWatchTower AirNomadWatchTowerGen = new WorldGenAirNomadWatchTower();
	public static WorldGenAirNomadTemple AirNomadTemple = new WorldGenAirNomadTemple();
	public static WorldGenEarthKingdomCompound EarthKingdomCompound = new WorldGenEarthKingdomCompound();
	public static WorldGenEarthKingdomHouseOne EarthKingdomHouseOne = new WorldGenEarthKingdomHouseOne();
	public static WorldGenEarthKingdomHouseTwo EarthKingdomHouseTwo = new WorldGenEarthKingdomHouseTwo();
	public static WorldGenEarthKingdomTower EarthKingdomTower = new WorldGenEarthKingdomTower();
	public static WorldGenFireNationHouse FireNationHouse = new WorldGenFireNationHouse();
	public static WorldGenFireNationTower FireNationTower = new WorldGenFireNationTower();
	
	public static BiomeSpirit spiritBiome;

	/* Creative tabs */
	public static CreativeTabs tabFirebending = new FireTokenTab(CreativeTabs.getNextID(), "tabFirebending");
	public static CreativeTabs tabWaterbending = new WaterTokenTab(CreativeTabs.getNextID(), "tabWaterbending");
	public static CreativeTabs tabEarthbending = new EarthTokenTab(CreativeTabs.getNextID(), "tabEarthbending");
	public static CreativeTabs tabAirbending = new AirTokenTab(CreativeTabs.getNextID(), "tabAirbending");
	
	/* Channels */
	public static EnumMap<Side, FMLEmbeddedChannel> ChannelAvatarChi, ChannelAvatarFam, ChannelAvatarPar, ChannelAvatarMisc, ChannelAvatarKey, ChannelAvatarBend;

	public static int renderID;
	public static boolean shouldSpawnStructures = true;
	public static boolean shouldSpawnPlants = true;
	public static boolean isAvatarAllowed = false;
	public static int startEntityId = 500;

	@Instance("kieranvs.avatar")
	public static mod_Avatar instance;

	@SidedProxy(clientSide = "kieranvs.avatar.client.ClientProxy", serverSide = "kieranvs.avatar.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		CommandHandler commandManager = (CommandHandler) event.getServer().getCommandManager();
		commandManager.registerCommand(new CommandBending());
		commandManager.registerCommand(new CommandRegion());
	}

	public static ToolMaterial BENDINGFIRE = EnumHelper.addToolMaterial("BENDINGFIRE", 2, 500, 7F, 3, 14);
	static ArmorMaterial METAL = EnumHelper.addArmorMaterial("METAL", 25, new int[] { 3, 7, 6, 3 }, 10);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configurator config = new Configurator();
		config.doTheThing(event.getSuggestedConfigurationFile());
		
		proxy.registerRenderers();
		proxy.loadCommons();
		proxy.registerSoundHandler();
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this, this.proxy);
		
		NewNetworkHandler handler = new NewNetworkHandler();
		mod_Avatar.ChannelAvatarChi = NetworkRegistry.INSTANCE.newChannel("AvatarChi", handler);
		mod_Avatar.ChannelAvatarFam = NetworkRegistry.INSTANCE.newChannel("AvatarFam", handler);
		mod_Avatar.ChannelAvatarPar = NetworkRegistry.INSTANCE.newChannel("AvatarPar", handler);
		mod_Avatar.ChannelAvatarMisc = NetworkRegistry.INSTANCE.newChannel("AvatarMisc", handler);
		mod_Avatar.ChannelAvatarKey = NetworkRegistry.INSTANCE.newChannel("AvatarKey", handler);
		mod_Avatar.ChannelAvatarBend = NetworkRegistry.INSTANCE.newChannel("AvatarBend", handler);
		
		instance = this;
		
		/* Biomes */
		spiritBiome = new BiomeSpirit(75);
		
		/* Block definitions */
		EarthBlock = (BlockElementBlock) new BlockElementBlock(2).setBlockName("EarthBlock");
		GameRegistry.registerBlock(EarthBlock, "EarthBlock");
		EarthKingdomBlock = (BlockElementBlock) new BlockElementBlock(3).setBlockName("EarthKingdomBlock");
		GameRegistry.registerBlock(EarthKingdomBlock, "EarthKingdomBlock");
		WaterBlock = (BlockElementBlock) new BlockElementBlock(0).setBlockName("WaterBlock");
		GameRegistry.registerBlock(WaterBlock, "WaterBlock");
		WaterTribeBlock = (BlockElementBlock) new BlockElementBlock(1).setBlockName("WaterTribeBlock");
		GameRegistry.registerBlock(WaterTribeBlock, "WaterTribeBlock");
		FireBlock = (BlockElementBlock) new BlockElementBlock(4).setBlockName("FireBlock");
		GameRegistry.registerBlock(FireBlock, "FireBlock");
		FireNationBlock = (BlockElementBlock) new BlockElementBlock(5).setBlockName("FireNationBlock");
		GameRegistry.registerBlock(FireNationBlock, "FireNationBlock");
		AirBlock = (BlockElementBlock) new BlockElementBlock(6).setBlockName("AirBlock");
		GameRegistry.registerBlock(AirBlock, "AirBlock");
		AirNomadsBlock = (BlockElementBlock) new BlockElementBlock(7).setBlockName("AirNomadsBlock");
		GameRegistry.registerBlock(AirNomadsBlock, "AirNomadsBlock");
		BlueFireIns.setBlockName("BlueFire");
		GameRegistry.registerBlock(BlueFireIns, "BlueFire");
		SeaPrunePlantBlock = (BlockSeaPrunePlant) new BlockSeaPrunePlant().setBlockName("Sea Prune Plant");
		GameRegistry.registerBlock(SeaPrunePlantBlock, "Sea Prune Plant");
		RiceCropBlock = (BlockRiceCrop) new BlockRiceCrop().setBlockName("Rice Crop");
		GameRegistry.registerBlock(RiceCropBlock, "Rice Crop");
		MetalArmorBlock = (BlockMetalArmor) new BlockMetalArmor().setBlockName("Metal Armor Block");
		GameRegistry.registerBlock(MetalArmorBlock, "Metal Armor Block");
		StatueRokuBlock = new BlockStatueRoku();
		GameRegistry.registerBlock(StatueRokuBlock, "blockStatueRoku");
		StatueKyoshiBlock = new BlockStatueKyoshi();
		GameRegistry.registerBlock(StatueKyoshiBlock, "blockStatueKyoshi");
		StatueYangchenBlock = new BlockStatueYangchen();
		GameRegistry.registerBlock(StatueYangchenBlock, "blockStatueYangchen");
		SeaweedPlantBlock = (BlockSeaweedPlant) new BlockSeaweedPlant().setBlockName("SeaweedPlant");
		GameRegistry.registerBlock(SeaweedPlantBlock, "Seaweed Plant");
		AirVentBlock = (BlockAirVent) new BlockAirVent().setBlockName("AirVent");
		GameRegistry.registerBlock(AirVentBlock, "AirVentBlock");
		SpiritPortalBlock = (BlockSpiritPortal) new BlockSpiritPortal(471).setBlockName("SpiritPortal");
		GameRegistry.registerBlock(SpiritPortalBlock, "SpiritPortal");
		TreeOfTimeWoodBlock = (BlockTreeOfTimeWood) new BlockTreeOfTimeWood().setBlockName("TreeOfTimeWood");
		GameRegistry.registerBlock(TreeOfTimeWoodBlock, "Tree of Time Wood");
		CharredGrassBlock = (BlockGrassCustom) new BlockGrassCustom(2).setBlockName("CharredGrass");
		GameRegistry.registerBlock(CharredGrassBlock, "Charred Grass");
		SpiritGrassBlock = (BlockGrassCustom) new BlockGrassCustom(1).setBlockName("SpiritGrass");
		GameRegistry.registerBlock(SpiritGrassBlock, "Spirit Grass");
		YellowGrassBlock = (BlockGrassCustom) new BlockGrassCustom(0).setBlockName("YellowGrass");
		GameRegistry.registerBlock(YellowGrassBlock, "Yellow Grass");
		IceBrick = (BlockColdDecorative)new BlockColdDecorative(Material.ice, 0).setBlockName("IceBrick");
		GameRegistry.registerBlock(IceBrick, "IceBrick");
		IceBrickChiseled = (BlockColdDecorative)new BlockColdDecorative(Material.ice, 1).setBlockName("IceBrickChiseled");
		GameRegistry.registerBlock(IceBrickChiseled, "IceBrickChiseled");
		SnowBrick = (BlockColdDecorative)new BlockColdDecorative(Material.craftedSnow, 2).setBlockName("SnowBrick");
		GameRegistry.registerBlock(SnowBrick, "SnowBrick");
		SnowBrickChiseled = (BlockColdDecorative)new BlockColdDecorative(Material.craftedSnow, 3).setBlockName("SnowBrickChiseled");
		GameRegistry.registerBlock(SnowBrickChiseled, "SnowBrickChiseled");
		PackedIceBrick = (BlockColdDecorative)new BlockColdDecorative(Material.ice, 4).setBlockName("PackedIceBrick");
		GameRegistry.registerBlock(PackedIceBrick, "PackedIceBrick");
		PackedIceBrickChiseled = (BlockColdDecorative)new BlockColdDecorative(Material.packedIce, 5).setBlockName("PackedIceBrickChiseled");
		GameRegistry.registerBlock(PackedIceBrickChiseled, "PackedIceBrickChiseled");
		
		IceDoubleSlab = (BlockSlab) new BlockColdDecorativeSlabs(true, Material.ice, 0).setBlockName("IceDoubleSlab");
		IceSlab = (BlockSlab) new BlockColdDecorativeSlabs(false, Material.ice, 0).setBlockName("IceSlab");
		SnowDoubleSlab = (BlockSlab) new BlockColdDecorativeSlabs(true, Material.craftedSnow, 1).setBlockName("SnowDoubleSlab");
		SnowSlab = (BlockSlab) new BlockColdDecorativeSlabs(false, Material.craftedSnow, 1).setBlockName("SnowSlab");
		PackedIceDoubleSlab = (BlockSlab) new BlockColdDecorativeSlabs(true, Material.packedIce, 2).setBlockName("PackedIceDoubleSlab");
		PackedIceSlab = (BlockSlab) new BlockColdDecorativeSlabs(false, Material.packedIce, 2).setBlockName("PackedIceSlab");
		GameRegistry.registerBlock(IceDoubleSlab, ItemIceSlab.class, "IceDoubleSlab");
		GameRegistry.registerBlock(IceSlab, ItemIceSlab.class, "IceSlab");
		GameRegistry.registerBlock(SnowDoubleSlab, ItemSnowSlab.class, "SnowDoubleSlab");
		GameRegistry.registerBlock(SnowSlab, ItemSnowSlab.class, "SnowSlab");
		GameRegistry.registerBlock(PackedIceDoubleSlab, ItemPackedIceSlab.class, "PackedIceDoubleSlab");
		GameRegistry.registerBlock(PackedIceSlab, ItemPackedIceSlab.class, "PackedIceSlab");
		
		/* Item definitions */
		/* Fire */
		FireExplosionNoviceItem = new ItemSpellTome("Explosion", 0, 0);
		GameRegistry.registerItem(FireExplosionNoviceItem, "FireExplosionNoviceItem");
		FireExplosionAdeptItem = new ItemSpellTome("Explosion", 0, 1);
		GameRegistry.registerItem(FireExplosionAdeptItem, "FireExplosionAdeptItem");
		FireExplosionMasterItem = new ItemSpellTome("Explosion", 0, 2);
		GameRegistry.registerItem(FireExplosionMasterItem, "FireExplosionMasterItem");
		FireLightningNoviceItem = new ItemSpellTome("Lightning", 0, 0);
		GameRegistry.registerItem(FireLightningNoviceItem, "FireLightningNoviceItem");
		FireLightningAdeptItem = new ItemSpellTome("Lightning", 0, 1);
		GameRegistry.registerItem(FireLightningAdeptItem, "FireLightningAdeptItem");
		FireLightningMasterItem = new ItemSpellTome("Lightning", 0, 2);
		GameRegistry.registerItem(FireLightningMasterItem, "FireLightningMasterItem");
		FireFireballNoviceItem = new ItemSpellTome("Fireball", 0, 0);
		GameRegistry.registerItem(FireFireballNoviceItem, "FireFireballNoviceItem");
		FireFireballAdeptItem = new ItemSpellTome("Fireball", 0, 1);
		GameRegistry.registerItem(FireFireballAdeptItem, "FireFireballAdeptItem");
		FireFireballMasterItem = new ItemSpellTome("Fireball", 0, 2);
		GameRegistry.registerItem(FireFireballMasterItem, "FireFireballMasterItem");
		FireBlastNoviceItem = new ItemSpellTome("Blast", 0, 0);
		GameRegistry.registerItem(FireBlastNoviceItem, "FireBlastNoviceItem");
		FireBlastAdeptItem = new ItemSpellTome("Blast", 0, 1);
		GameRegistry.registerItem(FireBlastAdeptItem, "FireBlastAdeptItem");
		FireBlastMasterItem = new ItemSpellTome("Blast", 0, 2);
		GameRegistry.registerItem(FireBlastMasterItem, "FireBlastMasterItem");
		FireFlightNoviceItem = new ItemSpellTome("Flight", 0, 0);
		GameRegistry.registerItem(FireFlightNoviceItem, "FireFlightNoviceItem");
		FireFlightAdeptItem = new ItemSpellTome("Flight", 0, 1);
		GameRegistry.registerItem(FireFlightAdeptItem, "FireFlightAdeptItem");
		FireFlightMasterItem = new ItemSpellTome("Flight", 0, 2);
		GameRegistry.registerItem(FireFlightMasterItem, "FireFlightMasterItem");
		FireJetNoviceItem = new ItemSpellTome("Jet", 0, 0);
		GameRegistry.registerItem(FireJetNoviceItem, "FireJetNoviceItem");
		FireJetAdeptItem = new ItemSpellTome("Jet", 0, 1);
		GameRegistry.registerItem(FireJetAdeptItem, "FireJetAdeptItem");
		FireJetMasterItem = new ItemSpellTome("Jet", 0, 2);
		GameRegistry.registerItem(FireJetMasterItem, "FireJetMasterItem");
		FireWallNoviceItem = new ItemSpellTome("Wall", 0, 0);
		GameRegistry.registerItem(FireWallNoviceItem, "FireWallNoviceItem");
		FireWallAdeptItem = new ItemSpellTome("Wall", 0, 1);
		GameRegistry.registerItem(FireWallAdeptItem, "FireWallAdeptItem");
		FireWallMasterItem = new ItemSpellTome("Wall", 0, 2);
		GameRegistry.registerItem(FireWallMasterItem, "FireWallMasterItem");
		FireStreamNoviceItem = new ItemSpellTome("Stream", 0, 0);
		GameRegistry.registerItem(FireStreamNoviceItem, "FireStreamNoviceItem");
		FireStreamAdeptItem = new ItemSpellTome("Stream", 0, 1);
		GameRegistry.registerItem(FireStreamAdeptItem, "FireStreamAdeptItem");
		FireStreamMasterItem = new ItemSpellTome("Stream", 0, 2);
		GameRegistry.registerItem(FireStreamMasterItem, "FireStreamMasterItem");
		FireRingNoviceItem = new ItemSpellTome("Ring", 0, 0);
		GameRegistry.registerItem(FireRingNoviceItem, "FireRingNoviceItem");
		FireRingAdeptItem = new ItemSpellTome("Ring", 0, 1);
		GameRegistry.registerItem(FireRingAdeptItem, "FireRingAdeptItem");
		FireRingMasterItem = new ItemSpellTome("Ring", 0, 2);
		GameRegistry.registerItem(FireRingMasterItem, "FireRingMasterItem");
		FireStarNoviceItem = new ItemSpellTome("Star", 0, 0);
		GameRegistry.registerItem(FireStarNoviceItem, "FireStarNoviceItem");
		FireStarAdeptItem = new ItemSpellTome("Star", 0, 1);
		GameRegistry.registerItem(FireStarAdeptItem, "FireStarAdeptItem");
		FireStarMasterItem = new ItemSpellTome("Star", 0, 2);
		GameRegistry.registerItem(FireStarMasterItem, "FireStarMasterItem");
		FireMeltNoviceItem = new ItemSpellTome("Melt", 0, 0);
		GameRegistry.registerItem(FireMeltNoviceItem, "FireMeltNoviceItem");
		FireMeltAdeptItem = new ItemSpellTome("Melt", 0, 1);
		GameRegistry.registerItem(FireMeltAdeptItem, "FireMeltAdeptItem");
		FireMeltMasterItem = new ItemSpellTome("Melt", 0, 2);
		GameRegistry.registerItem(FireMeltMasterItem, "FireMeltMasterItem");

		/* Water */
		WaterIceStreamNoviceItem = new ItemSpellTome("Ice Stream", 1, 0);
		GameRegistry.registerItem(WaterIceStreamNoviceItem, "WaterIceStreamNoviceItem");
		WaterIceStreamAdeptItem = new ItemSpellTome("Ice Stream", 1, 1);
		GameRegistry.registerItem(WaterIceStreamAdeptItem, "WaterIceStreamAdeptItem");
		WaterIceStreamMasterItem = new ItemSpellTome("Ice Stream", 1, 2);
		GameRegistry.registerItem(WaterIceStreamMasterItem, "WaterIceStreamMasterItem");
		WaterIceBridgeNoviceItem = new ItemSpellTome("Ice Bridge", 1, 0);
		GameRegistry.registerItem(WaterIceBridgeNoviceItem, "WaterIceBridgeNoviceItem");
		WaterIceBridgeAdeptItem = new ItemSpellTome("Ice Bridge", 1, 1);
		GameRegistry.registerItem(WaterIceBridgeAdeptItem, "WaterIceBridgeAdeptItem");
		WaterIceBridgeMasterItem = new ItemSpellTome("Ice Bridge", 1, 2);
		GameRegistry.registerItem(WaterIceBridgeMasterItem, "WaterIceBridgeMasterItem");
		WaterIceRingNoviceItem = new ItemSpellTome("Ice Ring", 1, 0);
		GameRegistry.registerItem(WaterIceRingNoviceItem, "WaterIceRingNoviceItem");
		WaterIceRingAdeptItem = new ItemSpellTome("Ice Ring", 1, 1);
		GameRegistry.registerItem(WaterIceRingAdeptItem, "WaterIceRingAdeptItem");
		WaterIceRingMasterItem = new ItemSpellTome("Ice Ring", 1, 2);
		GameRegistry.registerItem(WaterIceRingMasterItem, "WaterIceRingMasterItem");
		WaterManipulateItem = new ItemSpellTome("Manipulate", 1, 0);
		GameRegistry.registerItem(WaterManipulateItem, "WaterManipulateItem");
		WaterFreezeWaterfallNoviceItem = new ItemSpellTome("Freeze Waterfall", 1, 0);
		GameRegistry.registerItem(WaterFreezeWaterfallNoviceItem, "WaterFreezeWaterfallItem");
		WaterIceWallNoviceItem = new ItemSpellTome("Ice Wall", 1, 0);
		GameRegistry.registerItem(WaterIceWallNoviceItem, "WaterIceWallNoviceItem");
		WaterIceWallAdeptItem = new ItemSpellTome("Ice Wall", 1, 1);
		GameRegistry.registerItem(WaterIceWallAdeptItem, "WaterIceWallAdeptItem");
		WaterIceWallMasterItem = new ItemSpellTome("Ice Wall", 1, 2);
		GameRegistry.registerItem(WaterIceWallMasterItem, "WaterIceWallMasterItem");
		WaterSnowWallNoviceItem = new ItemSpellTome("Snow Wall", 1, 0);
		GameRegistry.registerItem(WaterSnowWallNoviceItem, "WaterSnowWallNoviceItem");
		WaterSnowWallAdeptItem = new ItemSpellTome("Snow Wall", 1, 1);
		GameRegistry.registerItem(WaterSnowWallAdeptItem, "WaterSnowWallAdeptItem");
		WaterSnowWallMasterItem = new ItemSpellTome("Snow Wall", 1, 2);
		GameRegistry.registerItem(WaterSnowWallMasterItem, "WaterSnowWallMasterItem");
		WaterSnowballNoviceItem = new ItemSpellTome("Snow Ball", 1, 0);
		GameRegistry.registerItem(WaterSnowballNoviceItem, "WaterSnowballNoviceItem");
		WaterSnowballAdeptItem = new ItemSpellTome("Snow Ball", 1, 1);
		GameRegistry.registerItem(WaterSnowballAdeptItem, "WaterSnowballAdeptItem");
		WaterSnowballMasterItem = new ItemSpellTome("Snow Ball", 1, 2);
		GameRegistry.registerItem(WaterSnowballMasterItem, "WaterSnowballMasterItem");
		WaterManipulateShootNoviceItem = new ItemSpellTome("Manipulate Shoot", 1, 0);
		GameRegistry.registerItem(WaterManipulateShootNoviceItem, "WaterManipulateShootNoviceItem");
		WaterManipulateShootAdeptItem = new ItemSpellTome("Manipulate Shoot", 1, 1);
		GameRegistry.registerItem(WaterManipulateShootAdeptItem, "WaterManipulateShootAdeptItem");
		WaterManipulateShootMasterItem = new ItemSpellTome("Manipulate Shoot", 1, 2);
		GameRegistry.registerItem(WaterManipulateShootMasterItem, "WaterManipulateShootMasterItem");
		WaterManipulateFreezeItem = new ItemSpellTome("Manipulate Freeze", 1, 0);
		GameRegistry.registerItem(WaterManipulateFreezeItem, "Manipulate Freeze");
		WaterOctopusFormNoviceItem = new ItemSpellTome("Octopus Form", 1, 0);
		GameRegistry.registerItem(WaterOctopusFormNoviceItem, "Octopus Form");
		WaterMeltNoviceItem = new ItemSpellTome("Melt", 1, 0);
		GameRegistry.registerItem(WaterMeltNoviceItem, "WaterMeltNoviceItem");
		WaterMeltAdeptItem = new ItemSpellTome("Melt", 1, 1);
		GameRegistry.registerItem(WaterMeltAdeptItem, "WaterMeltAdeptItem");
		WaterMeltMasterItem = new ItemSpellTome("Melt", 1, 2);
		GameRegistry.registerItem(WaterMeltMasterItem, "WaterMeltMasterItem");

		/* Earth 741-760 : 753-760 free */
		EarthRockThrowNoviceItem = new ItemSpellTome("Rock Throw", 2, 0);
		GameRegistry.registerItem(EarthRockThrowNoviceItem, "EarthRockThrowNoviceItem");
		EarthRockThrowAdeptItem = new ItemSpellTome("Rock Throw", 2, 1);
		GameRegistry.registerItem(EarthRockThrowAdeptItem, "EarthRockThrowAdeptItem");
		EarthRockThrowMasterItem = new ItemSpellTome("Rock Throw", 2, 2);
		GameRegistry.registerItem(EarthRockThrowMasterItem, "EarthRockThrowMasterItem");
		EarthRaisePlatformNoviceItem = new ItemSpellTome("Raise Platform", 2, 0);
		GameRegistry.registerItem(EarthRaisePlatformNoviceItem, "EarthRaisePlatformNoviceItem");
		EarthRaisePlatformAdeptItem = new ItemSpellTome("Raise Platform", 2, 1);
		GameRegistry.registerItem(EarthRaisePlatformAdeptItem, "EarthRaisePlatformAdeptItem");
		EarthRaisePlatformMasterItem = new ItemSpellTome("Raise Platform", 2, 2);
		GameRegistry.registerItem(EarthRaisePlatformMasterItem, "EarthRaisePlatformMasterItem");
		EarthWallNoviceItem = new ItemSpellTome("Wall", 2, 0);
		GameRegistry.registerItem(EarthWallNoviceItem, "EarthWallNoviceItem");
		EarthWallAdeptItem = new ItemSpellTome("Wall", 2, 1);
		GameRegistry.registerItem(EarthWallAdeptItem, "EarthWallAdeptItem");
		EarthWallMasterItem = new ItemSpellTome("Wall", 2, 2);
		GameRegistry.registerItem(EarthWallMasterItem, "EarthWallMasterItem");
		EarthTentNoviceItem = new ItemSpellTome("Tent", 2, 0);
		GameRegistry.registerItem(EarthTentNoviceItem, "EarthTentNoviceItem");
		EarthManipulateItem = new ItemSpellTome("Manipulate", 2, 0);
		GameRegistry.registerItem(EarthManipulateItem, "EarthManipulateItem");
		EarthManipulateShootNoviceItem = new ItemSpellTome("Manipulate Shoot", 2, 0);
		GameRegistry.registerItem(EarthManipulateShootNoviceItem, "EarthManipulateShootNoviceItem");
		EarthManipulateShootAdeptItem = new ItemSpellTome("Manipulate Shoot", 2, 1);
		GameRegistry.registerItem(EarthManipulateShootAdeptItem, "EarthManipulateShootAdeptItem");
		EarthManipulateShootMasterItem = new ItemSpellTome("Manipulate Shoot", 2, 2);
		GameRegistry.registerItem(EarthManipulateShootMasterItem, "EarthManipulateShootMasterItem");
		EarthLaunchNoviceItem = new ItemSpellTome("Launch", 2, 0);
		GameRegistry.registerItem(EarthLaunchNoviceItem, "EarthLaunchNoviceItem");
		EarthLaunchAdeptItem = new ItemSpellTome("Launch", 2, 1);
		GameRegistry.registerItem(EarthLaunchAdeptItem, "EarthLaunchAdeptItem");
		EarthLaunchMasterItem = new ItemSpellTome("Launch", 2, 2);
		GameRegistry.registerItem(EarthLaunchMasterItem, "EarthLaunchMasterItem");
		EarthTunnelColumnNoviceItem = new ItemSpellTome("Tunnel Column", 2, 0);
		GameRegistry.registerItem(EarthTunnelColumnNoviceItem, "EarthTunnelColumnNoviceItem");
		EarthTunnelColumnAdeptItem = new ItemSpellTome("Tunnel Column", 2, 1);
		GameRegistry.registerItem(EarthTunnelColumnAdeptItem, "EarthTunnelColumnAdeptItem");
		EarthTunnelColumnMasterItem = new ItemSpellTome("Tunnel Column", 2, 2);
		GameRegistry.registerItem(EarthTunnelColumnMasterItem, "EarthTunnelColumnMasterItem");
		EarthBuildWallNoviceItem = new ItemSpellTome("Build Wall", 2, 0);
		GameRegistry.registerItem(EarthBuildWallNoviceItem, "EarthBuildWallNoviceItem");
		EarthBuildWallAdeptItem = new ItemSpellTome("Build Wall", 2, 1);
		GameRegistry.registerItem(EarthBuildWallAdeptItem, "EarthBuildWallAdeptItem");
		EarthBuildWallMasterItem = new ItemSpellTome("Build Wall", 2, 2);
		GameRegistry.registerItem(EarthBuildWallMasterItem, "EarthBuildWallMasterItem");
		EarthBuildTowerNoviceItem = new ItemSpellTome("Build Tower", 2, 0);
		GameRegistry.registerItem(EarthBuildTowerNoviceItem, "EarthBuildTowerNoviceItem");
		EarthBuildTowerAdeptItem = new ItemSpellTome("Build Tower", 2, 1);
		GameRegistry.registerItem(EarthBuildTowerAdeptItem, "EarthBuildTowerAdeptItem");
		EarthBuildTowerMasterItem = new ItemSpellTome("Build Tower", 2, 2);
		GameRegistry.registerItem(EarthBuildTowerMasterItem, "EarthBuildTowerMasterItem");
		EarthBridgeNoviceItem = new ItemSpellTome("Bridge", 2, 0);
		GameRegistry.registerItem(EarthBridgeNoviceItem, "EarthBridgeNoviceItem");
		EarthBridgeAdeptItem = new ItemSpellTome("Bridge", 2, 1);
		GameRegistry.registerItem(EarthBridgeAdeptItem, "EarthBridgeAdeptItem");
		EarthBridgeMasterItem = new ItemSpellTome("Bridge", 2, 2);
		GameRegistry.registerItem(EarthBridgeMasterItem, "EarthBridgeMasterItem");
		EarthOreSenseNoviceItem = new ItemSpellTome("Ore Sense", 2, 0);
		GameRegistry.registerItem(EarthOreSenseNoviceItem, "EarthOreSenseNoviceItem");
		EarthOreSenseAdeptItem = new ItemSpellTome("Ore Sense", 2, 1);
		GameRegistry.registerItem(EarthOreSenseAdeptItem, "EarthOreSenseAdeptItem");
		EarthOreSenseMasterItem = new ItemSpellTome("Ore Sense", 2, 2);
		GameRegistry.registerItem(EarthOreSenseMasterItem, "EarthOreSenseMasterItem");

		/* Air */
		AirGustNoviceItem = new ItemSpellTome("Gust", 3, 0);
		GameRegistry.registerItem(AirGustNoviceItem, "AirGustNoviceItem");
		AirGustAdeptItem = new ItemSpellTome("Gust", 3, 1);
		GameRegistry.registerItem(AirGustAdeptItem, "AirGustAdeptItem");
		AirGustMasterItem = new ItemSpellTome("Gust", 3, 2);
		GameRegistry.registerItem(AirGustMasterItem, "AirGustMasterItem");
		AirRingNoviceItem = new ItemSpellTome("Ring", 3, 0);
		GameRegistry.registerItem(AirRingNoviceItem, "AirRingNoviceItem");
		AirRingAdeptItem = new ItemSpellTome("Ring", 3, 1);
		GameRegistry.registerItem(AirRingAdeptItem, "AirRingAdeptItem");
		AirRingMasterItem = new ItemSpellTome("Ring", 3, 2);
		GameRegistry.registerItem(AirRingMasterItem, "AirRingMasterItem");
		AirJumpNoviceItem = new ItemSpellTome("High Jump", 3, 0);
		GameRegistry.registerItem(AirJumpNoviceItem, "AirJumpNoviceItem");
		AirJumpAdeptItem = new ItemSpellTome("High Jump", 3, 1);
		GameRegistry.registerItem(AirJumpAdeptItem, "AirJumpAdeptItem");
		AirJumpMasterItem = new ItemSpellTome("High Jump", 3, 2);
		GameRegistry.registerItem(AirJumpMasterItem, "AirJumpMasterItem");
		AirSpringNoviceItem = new ItemSpellTome("Spring", 3, 0);
		GameRegistry.registerItem(AirSpringNoviceItem, "AirSpringNoviceItem");
		AirSpringAdeptItem = new ItemSpellTome("Spring", 3, 1);
		GameRegistry.registerItem(AirSpringAdeptItem, "AirSpringAdeptItem");
		AirSpringMasterItem = new ItemSpellTome("Spring", 3, 2);
		GameRegistry.registerItem(AirSpringMasterItem, "AirSpringMasterItem");
		AirWindRunNoviceItem = new ItemSpellTome("Wind Run", 3, 0);
		GameRegistry.registerItem(AirWindRunNoviceItem, "AirWindRunNoviceItem");
		AirWindRunAdeptItem = new ItemSpellTome("Wind Run", 3, 1);
		GameRegistry.registerItem(AirWindRunAdeptItem, "AirWindRunAdeptItem");
		AirWindRunMasterItem = new ItemSpellTome("Wind Run", 3, 2);
		GameRegistry.registerItem(AirWindRunMasterItem, "AirWindRunMasterItem");
		AirStreamNoviceItem = new ItemSpellTome("Stream", 3, 0);
		GameRegistry.registerItem(AirStreamNoviceItem, "AirStreamNoviceItem");
		AirStreamAdeptItem = new ItemSpellTome("Stream", 3, 1);
		GameRegistry.registerItem(AirStreamAdeptItem, "AirStreamAdeptItem");
		AirStreamMasterItem = new ItemSpellTome("Stream", 3, 2);
		GameRegistry.registerItem(AirStreamMasterItem, "AirStreamMasterItem");
		AirDomeBurstNoviceItem = new ItemSpellTome("Dome Burst", 3, 0);
		GameRegistry.registerItem(AirDomeBurstNoviceItem, "AirDomeBurstNoviceItem");
		AirDomeBurstAdeptItem = new ItemSpellTome("Dome Burst", 3, 1);
		GameRegistry.registerItem(AirDomeBurstAdeptItem, "AirDomeBurstAdeptItem");
		AirDomeBurstMasterItem = new ItemSpellTome("Dome Burst", 3, 2);
		GameRegistry.registerItem(AirDomeBurstMasterItem, "AirDomeBurstMasterItem");
		AirTwisterNoviceItem = new ItemSpellTome("Twister", 3, 0);
		GameRegistry.registerItem(AirTwisterNoviceItem, "AirTwisterNoviceItem");
		AirTwisterAdeptItem = new ItemSpellTome("Twister", 3, 1);
		GameRegistry.registerItem(AirTwisterAdeptItem, "AirTwisterAdeptItem");
		AirTwisterMasterItem = new ItemSpellTome("Twister", 3, 2);
		GameRegistry.registerItem(AirTwisterMasterItem, "AirTwisterMasterItem");
		AirShieldNoviceItem = new ItemSpellTome("Shield", 3, 0);
		GameRegistry.registerItem(AirShieldNoviceItem, "AirShieldNoviceItem");
		AirShieldAdeptItem = new ItemSpellTome("Shield", 3, 1);
		GameRegistry.registerItem(AirShieldAdeptItem, "AirShieldAdeptItem");
		AirShieldMasterItem = new ItemSpellTome("Shield", 3, 2);
		GameRegistry.registerItem(AirShieldMasterItem, "AirShieldMasterItem");
		AirDiscNoviceItem = new ItemSpellTome("Disc", 3, 0);
		GameRegistry.registerItem(AirDiscNoviceItem, "AirDiscNoviceItem");
		AirDiscAdeptItem = new ItemSpellTome("Disc", 3, 1);
		GameRegistry.registerItem(AirDiscAdeptItem, "AirDiscAdeptItem");
		AirDiscMasterItem = new ItemSpellTome("Disc", 3, 2);
		GameRegistry.registerItem(AirDiscMasterItem, "AirDiscMasterItem");
		AirPullNoviceItem = new ItemSpellTome("Pull", 3, 0);
		GameRegistry.registerItem(AirPullNoviceItem, "AirPullNoviceItem");
		AirPullAdeptItem = new ItemSpellTome("Pull", 3, 1);
		GameRegistry.registerItem(AirPullAdeptItem, "AirPullAdeptItem");
		AirPullMasterItem = new ItemSpellTome("Pull", 3, 2);
		GameRegistry.registerItem(AirPullMasterItem, "AirPullMasterItem");
		AirRingPullNoviceItem = new ItemSpellTome("Ring Pull", 3, 0);
		GameRegistry.registerItem(AirRingPullNoviceItem, "AirRingPullNoviceItem");
		AirRingPullAdeptItem = new ItemSpellTome("Ring Pull", 3, 1);
		GameRegistry.registerItem(AirRingPullAdeptItem, "AirRingPullAdeptItem");
		AirRingPullMasterItem = new ItemSpellTome("Ring Pull", 3, 2);
		GameRegistry.registerItem(AirRingPullMasterItem, "AirRingPullMasterItem");
		AirDomeSuckNoviceItem = new ItemSpellTome("Dome Pull", 3, 0);
		GameRegistry.registerItem(AirDomeSuckNoviceItem, "AirDomeSuckNoviceItem");
		AirDomeSuckAdeptItem = new ItemSpellTome("Dome Pull", 3, 1);
		GameRegistry.registerItem(AirDomeSuckAdeptItem, "AirDomeSuckAdeptItem");
		AirDomeSuckMasterItem = new ItemSpellTome("Dome Pull", 3, 2);
		GameRegistry.registerItem(AirDomeSuckMasterItem, "AirDomeSuckMasterItem");
		
		BendingSatchelItem = new ItemBendingSatchel();
		GameRegistry.registerItem(BendingSatchelItem, "BendingSatchelItem");

		/* Currency */
		MoneyFireGoldItem = new ItemMoney(0);
		GameRegistry.registerItem(MoneyFireGoldItem, "MoneyFireGoldItem");
		MoneyFireSilverItem = new ItemMoney(1);
		GameRegistry.registerItem(MoneyFireSilverItem, "MoneyFireSilverItem");
		MoneyFireBronzeItem = new ItemMoney(2);
		GameRegistry.registerItem(MoneyFireBronzeItem, "MoneyFireBronzeItem");

		/* Food */
		SeaPruneItem = new ItemFoodSeaPrune();
		GameRegistry.registerItem(SeaPruneItem, "SeaPruneItem");
		SeaPruneStewItem = new ItemFoodSeaPruneStew();
		GameRegistry.registerItem(SeaPruneStewItem, "SeaPruneStewItem");
		SeaSquidItem = new ItemFoodSeaSquid();
		GameRegistry.registerItem(SeaSquidItem, "SeaSquidItem");
		SeaSquidSoupItem = new ItemFoodSeaSquidSoup();
		GameRegistry.registerItem(SeaSquidSoupItem, "SeaSquidSoupItem");
		SeaweedItem = new ItemFoodSeaweed();
		GameRegistry.registerItem(SeaweedItem, "SeaweedItem");
		SeaweedSoupItem = new ItemFoodSeaweedSoup();
		GameRegistry.registerItem(SeaweedSoupItem, "SeaweedSoupItem");
		SeaweedBreadItem = new ItemFoodSeaweedBread();
		GameRegistry.registerItem(SeaweedBreadItem, "SeaweedBreadItem");
		RiceGrainItem = new ItemRiceGrain();
		GameRegistry.registerItem(RiceGrainItem, "RiceGrainItem");
		FruitPieItem = new ItemFoodFruitPie();
		GameRegistry.registerItem(FruitPieItem, "FruitPieItem");
		CustardTartItem = new ItemFoodCustardTart();
		GameRegistry.registerItem(CustardTartItem, "CustardTartItem");
		RiceBowlItem = new ItemFoodRiceBowl();
		GameRegistry.registerItem(RiceBowlItem, "RiceBowlItem");
		MoonPeachItem = new ItemFoodMoonPeach();
		GameRegistry.registerItem(MoonPeachItem, "MoonPeachItem");
		CabbageItem = new ItemFoodCabbage();
		GameRegistry.registerItem(CabbageItem, "CabbageItem");
		
		/* Tools */
		MetalHelmet = new ItemMetalArmor(852, METAL, 3, 0).setUnlocalizedName("MetalHelmet");
		GameRegistry.registerItem(MetalHelmet, "MetalHelmet");
		MetalChestplate = new ItemMetalArmor(853, METAL, 3, 1).setUnlocalizedName("MetalChestplate");
		GameRegistry.registerItem(MetalChestplate, "MetalChestplate");
		MetalLeggings = new ItemMetalArmor(854, METAL, 3, 2).setUnlocalizedName("MetalLeggings");
		GameRegistry.registerItem(MetalLeggings, "MetalLeggings");
		MetalBoots = new ItemMetalArmor(855, METAL, 3, 3).setUnlocalizedName("MetalBoots");
		GameRegistry.registerItem(MetalBoots, "MetalBoots");
		GliderWingItem = new ItemGliderWing();
		GameRegistry.registerItem(GliderWingItem, "GliderWing");
		
		GliderItemWhite.setUnlocalizedName("White Glider");
		GameRegistry.registerItem(GliderItemWhite, "White Glider");
		GenerationUtils.gliders.add(GliderItemWhite);
		GliderItemOrange.setUnlocalizedName("Orange Glider");
		GameRegistry.registerItem(GliderItemOrange, "Orange Glider");
		GenerationUtils.gliders.add(GliderItemOrange);
		GliderItemMagenta.setUnlocalizedName("Magenta Glider");
		GameRegistry.registerItem(GliderItemMagenta, "Magenta Glider");
		GenerationUtils.gliders.add(GliderItemMagenta);
		GliderItemLightBlue.setUnlocalizedName("LightBlue Glider");
		GameRegistry.registerItem(GliderItemLightBlue, "LightBlue Glider");
		GenerationUtils.gliders.add(GliderItemLightBlue);
		GliderItemYellow.setUnlocalizedName("Yellow Glider");
		GameRegistry.registerItem(GliderItemYellow, "Yellow Glider");
		GenerationUtils.gliders.add(GliderItemYellow);
		GliderItemLightGreen.setUnlocalizedName("LightGreen Glider");
		GameRegistry.registerItem(GliderItemLightGreen, "LightGreen Glider");
		GenerationUtils.gliders.add(GliderItemLightGreen);
		GliderItemPink.setUnlocalizedName("Pink Glider");
		GameRegistry.registerItem(GliderItemPink, "Pink Glider");
		GenerationUtils.gliders.add(GliderItemPink);
		GliderItemGrey.setUnlocalizedName("Grey Glider");
		GameRegistry.registerItem(GliderItemGrey, "Grey Glider");
		GenerationUtils.gliders.add(GliderItemGrey);
		GliderItemLightGrey.setUnlocalizedName("LightGrey Glider");
		GameRegistry.registerItem(GliderItemLightGrey, "LightGrey Glider");
		GenerationUtils.gliders.add(GliderItemLightGrey);
		GliderItemCyan.setUnlocalizedName("Cyan Glider");
		GameRegistry.registerItem(GliderItemCyan, "Cyan Glider");
		GenerationUtils.gliders.add(GliderItemCyan);
		GliderItemPurple.setUnlocalizedName("Purple Glider");
		GameRegistry.registerItem(GliderItemPurple, "Purple Glider");
		GenerationUtils.gliders.add(GliderItemPurple);
		GliderItemBlue.setUnlocalizedName("Blue Glider");
		GameRegistry.registerItem(GliderItemBlue, "Blue Glider");
		GenerationUtils.gliders.add(GliderItemBlue);
		GliderItemBrown.setUnlocalizedName("Brown Glider");
		GameRegistry.registerItem(GliderItemBrown, "Brown Glider");
		GenerationUtils.gliders.add(GliderItemBrown);
		GliderItemGreen.setUnlocalizedName("Green Glider");
		GameRegistry.registerItem(GliderItemGreen, "Green Glider");
		GenerationUtils.gliders.add(GliderItemGreen);
		GliderItemRed.setUnlocalizedName("Red Glider");
		GameRegistry.registerItem(GliderItemRed, "Red Glider");
		GenerationUtils.gliders.add(GliderItemRed);
		GliderItemBlack.setUnlocalizedName("Black Glider");
		GameRegistry.registerItem(GliderItemBlack, "Black Glider");
		GenerationUtils.gliders.add(GliderItemBlack);
		
		AirStaffItem.setUnlocalizedName("Air Staff");
		GameRegistry.registerItem(AirStaffItem, "AirStaff");

		/* Tile Entities */
		GameRegistry.registerTileEntity(TileEntityKyoshiStatue.class, "Kyoshi Statue");
		GameRegistry.registerTileEntity(TileEntityYangchenStatue.class, "Yangchen Statue");
		GameRegistry.registerTileEntity(TileEntityRokuStatue.class, "Roku Statue");
		GameRegistry.registerTileEntity(TileEntityAirVent.class, "Air Vent");
		GameRegistry.registerTileEntity(TileEntityBlueFire.class, "Blue Fire");

		/* Entity definitions */

		EntityRegistry.registerModEntity(EntityWaterMan.class, "WaterTribeMan", 2, this, 80, 3, true);
		registerEntityEgg(EntityWaterMan.class, 0x0800FF, 0x85D2F2);

		EntityRegistry.registerModEntity(EntityWaterWoman.class, "WaterTribeWoman", 3, this, 80, 3, true);
		registerEntityEgg(EntityWaterWoman.class, 0x0800FF, 0xF03C84);

		EntityRegistry.registerModEntity(EntityEarthMan.class, "EarthKingdomMan", 4, this, 80, 3, true);
		registerEntityEgg(EntityEarthMan.class, 0x14B845, 0x85D2F2);

		EntityRegistry.registerModEntity(EntityEarthWoman.class, "EarthKingdomWoman", 5, this, 80, 3, true);
		registerEntityEgg(EntityEarthWoman.class, 0x14B845, 0xF03C84);

		EntityRegistry.registerModEntity(EntityFireMan.class, "FireNationMan", 6, this, 80, 3, true);
		registerEntityEgg(EntityFireMan.class, 0xFF0000, 0x85D2F2);

		EntityRegistry.registerModEntity(EntityFireWoman.class, "FireNationWoman", 7, this, 80, 3, true);
		registerEntityEgg(EntityFireWoman.class, 0xFF0000, 0xF03C84);

		EntityRegistry.registerModEntity(EntityAirWoman.class, "AirNomadWoman", 8, this, 80, 3, true);
		registerEntityEgg(EntityAirWoman.class, 0xFFAE00, 0xF03C84);

		EntityRegistry.registerModEntity(EntityAirMan.class, "AirNomadMan", 9, this, 80, 3, true);
		registerEntityEgg(EntityAirMan.class, 0xFFAE00, 0x85D2F2);

		EntityRegistry.registerModEntity(EntityOtterPenguin.class, "OtterPenguin", 10, this, 80, 3, true);
		EntityRegistry.addSpawn(EntityOtterPenguin.class, 4, 5, 15, EnumCreatureType.creature, BiomeGenBase.coldTaiga, BiomeGenBase.coldTaigaHills, BiomeGenBase.icePlains, BiomeGenBase.iceMountains, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver);
		registerEntityEgg(EntityOtterPenguin.class, 0xffffff, 0x727273);

		EntityRegistry.registerModEntity(EntityBison.class, "Bison", 1, this, 80, 3, true);
		EntityRegistry.addSpawn(EntityBison.class, 4, 1, 3, EnumCreatureType.creature, BiomeGenBase.extremeHills, BiomeGenBase.stoneBeach, BiomeGenBase.extremeHillsPlus);
		registerEntityEgg(EntityBison.class, 0xEDEDDF, 0xD6B76F);

		EntityRegistry.registerModEntity(EntityPolarBearDog.class, "PolarBearDog", 11, this, 80, 3, true);
		EntityRegistry.addSpawn(EntityPolarBearDog.class, 3, 1, 3, EnumCreatureType.creature, BiomeGenBase.coldTaiga, BiomeGenBase.coldTaigaHills, BiomeGenBase.icePlains, BiomeGenBase.iceMountains);
		registerEntityEgg(EntityPolarBearDog.class, 0xB3E4E8, 0x727273);
		
		EntityRegistry.registerModEntity(EntityBadgerMole.class, "BadgerMole", 18, this, 80, 3, true);
		EntityRegistry.addSpawn(EntityBadgerMole.class, 4, 1, 3, EnumCreatureType.creature, BiomeGenBase.mesa, BiomeGenBase.mesaPlateau, BiomeGenBase.mesaPlateau_F, BiomeGenBase.stoneBeach);
		registerEntityEgg(EntityBadgerMole.class, 0x331A00, 0x050300);
		
		EntityRegistry.registerModEntity(EntityBandit.class, "Bandit", 12, this, 120, 3, true);
		EntityRegistry.addSpawn(EntityBandit.class, 5, 1, 3, EnumCreatureType.creature, BiomeGenBase.roofedForest, BiomeGenBase.plains, BiomeGenBase.desert);
		registerEntityEgg(EntityBandit.class, 0x4C4C4C, 0x456276);
		
		EntityRegistry.registerModEntity(EntityFireBandit.class, "Fire Bandit", 19, this, 120, 3, true);
		EntityRegistry.addSpawn(EntityFireBandit.class, 5, 1, 3, EnumCreatureType.creature, BiomeGenBase.roofedForest, BiomeGenBase.plains, BiomeGenBase.desert);
		registerEntityEgg(EntityFireBandit.class, 0xFF0000, 0x456276);
		
		EntityRegistry.registerModEntity(EntityWaterBandit.class, "Water Bandit", 20, this, 120, 3, true);
		EntityRegistry.addSpawn(EntityWaterBandit.class, 5, 1, 3, EnumCreatureType.creature, BiomeGenBase.roofedForest, BiomeGenBase.plains, BiomeGenBase.desert);
		registerEntityEgg(EntityWaterBandit.class, 0x0800FF, 0x456276);
		
		EntityRegistry.registerModEntity(EntityEarthBandit.class, "Earth Bandit", 21, this, 120, 3, true);
		EntityRegistry.addSpawn(EntityEarthBandit.class, 5, 1, 3, EnumCreatureType.creature, BiomeGenBase.roofedForest, BiomeGenBase.plains, BiomeGenBase.desert);
		registerEntityEgg(EntityEarthBandit.class, 0x14B845, 0x456276);
		
		EntityRegistry.registerModEntity(EntityArcherBandit.class, "Archer Bandit", 22, this, 120, 3, true);
		EntityRegistry.addSpawn(EntityArcherBandit.class, 5, 1, 3, EnumCreatureType.creature, BiomeGenBase.roofedForest, BiomeGenBase.plains, BiomeGenBase.desert);
		registerEntityEgg(EntityArcherBandit.class, 0xD6B76F, 0x456276);

		EntityRegistry.registerModEntity(EntityRock.class, "Rock", 14, this, 80, 1, true);
		EntityRegistry.registerModEntity(EntityAirBall.class, "Ball", 15, this, 80, 1, true);
		EntityRegistry.registerModEntity(EntityGlider.class, "Glider", 16, this, 80, 1, true);
		EntityRegistry.registerModEntity(EntityBlock.class, "Block", 17, this, 80, 1, true);
		
		/* Crafting recipes */
		GameRegistry.addShapelessRecipe(new ItemStack(SeaPruneStewItem), new Object[] { new ItemStack(SeaPruneItem), new ItemStack(SeaPruneItem), new ItemStack(Items.bowl) });
		GameRegistry.addShapelessRecipe(new ItemStack(SeaSquidSoupItem), new Object[] { new ItemStack(SeaSquidItem), new ItemStack(SeaSquidItem), new ItemStack(Items.bowl) });
		GameRegistry.addShapelessRecipe(new ItemStack(SeaweedSoupItem), new Object[] { new ItemStack(SeaweedItem), new ItemStack(SeaweedItem), new ItemStack(Items.bowl) });
		GameRegistry.addShapelessRecipe(new ItemStack(RiceBowlItem), new Object[] { new ItemStack(RiceGrainItem), new ItemStack(RiceGrainItem), new ItemStack(Items.bowl) });
		GameRegistry.addRecipe(new ItemStack(SeaweedBreadItem), "xyx", 'x', Items.wheat, 'y', SeaweedItem);
		GameRegistry.addRecipe(new ItemStack(MetalArmorBlock), "ab", "yz", 'a', mod_Avatar.MetalHelmet, 'b', mod_Avatar.MetalChestplate, 'y', mod_Avatar.MetalLeggings, 'z', mod_Avatar.MetalBoots);
		GameRegistry.addRecipe(new ItemStack(BendingSatchelItem), "l l", "l l", "dld", 'l', Items.leather, 'd', Items.diamond);
		GameRegistry.addRecipe(new ItemStack(AirVentBlock), "ibi", "ihi", "rfr", 'i', Items.iron_ingot, 'b', Blocks.iron_bars, 'h', Blocks.hopper, 'r', Items.redstone, 'f', Blocks.furnace);
		GameRegistry.addShapelessRecipe(new ItemStack(GliderWingItem), new Object[] { new ItemStack(Blocks.wool, 1, 0), new ItemStack(Blocks.wool, 1, 0), new ItemStack(Blocks.wool, 1, 0), new ItemStack(Blocks.wool, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(GliderItemWhite), "wsw", "wsw", " s ", 'w', mod_Avatar.GliderWingItem, 's', Items.stick);
		GameRegistry.addRecipe(new ItemStack(IceBrick), "xx", "xx", 'x', Blocks.ice);
		GameRegistry.addRecipe(new ItemStack(IceSlab), "xxx", 'x', Blocks.ice);
		GameRegistry.addRecipe(new ItemStack(IceBrickChiseled), "x", "x", 'x', mod_Avatar.IceSlab);
		GameRegistry.addRecipe(new ItemStack(SnowBrick), "xx", "xx", 'x', Blocks.snow);
		GameRegistry.addRecipe(new ItemStack(SnowSlab), "xxx", 'x', Blocks.snow);
		GameRegistry.addRecipe(new ItemStack(SnowBrickChiseled), "x", "x", 'x', mod_Avatar.SnowSlab);
		GameRegistry.addRecipe(new ItemStack(PackedIceBrick), "xx", "xx", 'x', Blocks.packed_ice);
		GameRegistry.addRecipe(new ItemStack(PackedIceSlab), "xxx", 'x', Blocks.packed_ice);
		GameRegistry.addRecipe(new ItemStack(PackedIceBrickChiseled), "x", "x", 'x', mod_Avatar.PackedIceSlab);
 
		GameRegistry.addShapelessRecipe(new ItemStack(GliderItemOrange), new Object[] { new ItemStack(GliderItemWhite), new ItemStack(Items.dye, 1, 14)});
		GameRegistry.addShapelessRecipe(new ItemStack(GliderItemMagenta), new Object[] { new ItemStack(GliderItemWhite), new ItemStack(Items.dye, 1, 13)});
		GameRegistry.addShapelessRecipe(new ItemStack(GliderItemLightBlue), new Object[] { new ItemStack(GliderItemWhite), new ItemStack(Items.dye, 1, 12)});
		GameRegistry.addShapelessRecipe(new ItemStack(GliderItemYellow), new Object[] { new ItemStack(GliderItemWhite), new ItemStack(Items.dye, 1, 11)});
		GameRegistry.addShapelessRecipe(new ItemStack(GliderItemLightGreen), new Object[] { new ItemStack(GliderItemWhite), new ItemStack(Items.dye, 1, 10)});
		GameRegistry.addShapelessRecipe(new ItemStack(GliderItemPink), new Object[] { new ItemStack(GliderItemWhite), new ItemStack(Items.dye, 1, 9)});
		GameRegistry.addShapelessRecipe(new ItemStack(GliderItemGrey), new Object[] { new ItemStack(GliderItemWhite), new ItemStack(Items.dye, 1, 8)});
		GameRegistry.addShapelessRecipe(new ItemStack(GliderItemLightGrey), new Object[] { new ItemStack(GliderItemWhite), new ItemStack(Items.dye, 1, 7)});
		GameRegistry.addShapelessRecipe(new ItemStack(GliderItemCyan), new Object[] { new ItemStack(GliderItemWhite), new ItemStack(Items.dye, 1, 6)});
		GameRegistry.addShapelessRecipe(new ItemStack(GliderItemPurple), new Object[] { new ItemStack(GliderItemWhite), new ItemStack(Items.dye, 1, 5)});
		GameRegistry.addShapelessRecipe(new ItemStack(GliderItemBlue), new Object[] { new ItemStack(GliderItemWhite), new ItemStack(Items.dye, 1, 4)});
		GameRegistry.addShapelessRecipe(new ItemStack(GliderItemBrown), new Object[] { new ItemStack(GliderItemWhite), new ItemStack(Items.dye, 1, 3)});
		GameRegistry.addShapelessRecipe(new ItemStack(GliderItemGreen), new Object[] { new ItemStack(GliderItemWhite), new ItemStack(Items.dye, 1, 2)}); 
		GameRegistry.addShapelessRecipe(new ItemStack(GliderItemRed), new Object[] { new ItemStack(GliderItemWhite), new ItemStack(Items.dye, 1, 1)});
		GameRegistry.addShapelessRecipe(new ItemStack(GliderItemBlack), new Object[] { new ItemStack(GliderItemWhite), new ItemStack(Items.dye, 1, 0)});
		SeaPruneGen = new WorldGenSeaPlants(SeaPrunePlantBlock);
		SeaweedGen = new WorldGenSeaPlants(SeaweedPlantBlock);
		
		/* World generation loads */
		if(this.shouldSpawnPlants){
			GameRegistry.registerWorldGenerator(SeaPruneGen, 0);
			GameRegistry.registerWorldGenerator(SeaweedGen, 0);
		}
		if(this.shouldSpawnStructures){
			GameRegistry.registerWorldGenerator(WaterSouthTribeVillageGen, 0);
			GameRegistry.registerWorldGenerator(WaterNorthTribeVillageGen, 0);
			GameRegistry.registerWorldGenerator(WaterSouthWatchTowerGen, 0);
			GameRegistry.registerWorldGenerator(WaterNorthWatchTowerGen, 0);
			GameRegistry.registerWorldGenerator(AirNomadVillageOneGen, 0);
			GameRegistry.registerWorldGenerator(AirNomadVillageTwoGen, 0);
			GameRegistry.registerWorldGenerator(AirNomadWatchTowerGen, 0);
			GameRegistry.registerWorldGenerator(AirNomadTemple, 0);
			GameRegistry.registerWorldGenerator(EarthKingdomCompound, 0);
			GameRegistry.registerWorldGenerator(EarthKingdomHouseOne, 0);
			GameRegistry.registerWorldGenerator(EarthKingdomHouseTwo, 0);
			GameRegistry.registerWorldGenerator(EarthKingdomTower, 0);
			GameRegistry.registerWorldGenerator(FireNationHouse, 0);
			GameRegistry.registerWorldGenerator(FireNationTower, 0);
		}
				
		/* Dimensions */
		DimensionManager.registerProviderType(dimensionIdSpiritWorld, WorldProviderSpiritWorld.class, false);
		DimensionManager.registerDimension(dimensionIdSpiritWorld, dimensionIdSpiritWorld);

		/* Event registration */
		MinecraftForge.EVENT_BUS.register(new ForgeListener());
		MinecraftForge.EVENT_BUS.register(new BendingListener());
		MinecraftForge.EVENT_BUS.register(new kieranvs.avatar.bending.air.PassiveListener());
		MinecraftForge.EVENT_BUS.register(new kieranvs.avatar.bending.fire.PassiveListener());
		
		HelpStrings.loadHelpMessages();

	}

	/* Allocate entity egg IDs */
	public static int getUniqueEntityId() {
		do {
			startEntityId++;
		} while (EntityList.getStringFromID(startEntityId) != null);

		return startEntityId;
	}

	public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor) {
		int id = getUniqueEntityId();
		EntityList.IDtoClassMapping.put(id, entity);
		EntityList.entityEggs.put(id, new EntityList.EntityEggInfo(id, primaryColor, secondaryColor));
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

}
