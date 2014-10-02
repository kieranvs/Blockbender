package kieranvs.avatar.client;

import kieranvs.avatar.CommonProxy;
import kieranvs.avatar.KeyBind;
import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.block.BlueFireRenderer;
import kieranvs.avatar.block.TileEntityKyoshiRenderer;
import kieranvs.avatar.block.TileEntityKyoshiStatue;
import kieranvs.avatar.block.TileEntityRokuRenderer;
import kieranvs.avatar.block.TileEntityRokuStatue;
import kieranvs.avatar.block.TileEntitySpiritPortal;
import kieranvs.avatar.block.TileEntitySpiritPortalRenderer;
import kieranvs.avatar.block.TileEntityYangchenRenderer;
import kieranvs.avatar.block.TileEntityYangchenStatue;
import kieranvs.avatar.entity.EntityArcherBandit;
import kieranvs.avatar.entity.EntityBadgerMole;
import kieranvs.avatar.entity.EntityBandit;
import kieranvs.avatar.entity.EntityBison;
import kieranvs.avatar.entity.EntityBlock;
import kieranvs.avatar.entity.EntityEarthBandit;
import kieranvs.avatar.entity.EntityFireBandit;
import kieranvs.avatar.entity.EntityGlider;
import kieranvs.avatar.entity.EntityOtterPenguin;
import kieranvs.avatar.entity.EntityPerson;
import kieranvs.avatar.entity.EntityPolarBearDog;
import kieranvs.avatar.entity.EntityRock;
import kieranvs.avatar.entity.EntityWaterBandit;
import kieranvs.avatar.entity.ItemRenderAirStaff;
import kieranvs.avatar.entity.ModelBadgerMole;
import kieranvs.avatar.entity.ModelBenderBiped;
import kieranvs.avatar.entity.ModelFlyingBison;
import kieranvs.avatar.entity.ModelOtterPenguin;
import kieranvs.avatar.entity.ModelPolarBearDog;
import kieranvs.avatar.entity.RenderBadgerMole;
import kieranvs.avatar.entity.RenderBandit;
import kieranvs.avatar.entity.RenderBison;
import kieranvs.avatar.entity.RenderEntityBlock;
import kieranvs.avatar.entity.RenderGliderActive;
import kieranvs.avatar.entity.RenderGliderInHand;
import kieranvs.avatar.entity.RenderOtterPenguin;
import kieranvs.avatar.entity.RenderPerson;
import kieranvs.avatar.entity.RenderPolarBearDog;
import kieranvs.avatar.entity.RenderRock;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy {
	
	public static KeyBind key;

	@Override
	public void registerRenderers() {

		RenderingRegistry.registerEntityRenderingHandler(EntityBison.class, new RenderBison(new ModelFlyingBison(), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityOtterPenguin.class, new RenderOtterPenguin(new ModelOtterPenguin(), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPolarBearDog.class, new RenderPolarBearDog(new ModelPolarBearDog(), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBadgerMole.class, new RenderBadgerMole(new ModelBadgerMole(), 1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityRock.class, new RenderRock());
		RenderingRegistry.registerEntityRenderingHandler(EntityPerson.class, new RenderPerson(new ModelBenderBiped(),1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBandit.class, new RenderBandit(new ModelBenderBiped(),1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFireBandit.class, new RenderBandit(new ModelBenderBiped(),1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityWaterBandit.class, new RenderBandit(new ModelBenderBiped(),1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityEarthBandit.class, new RenderBandit(new ModelBenderBiped(),1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityArcherBandit.class, new RenderBandit(new ModelBenderBiped(),1.0F));
		RenderingRegistry.registerEntityRenderingHandler(EntityGlider.class, new RenderGliderActive());
		RenderingRegistry.registerEntityRenderingHandler(EntityBlock.class, new RenderEntityBlock());

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRokuStatue.class, new TileEntityRokuRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityKyoshiStatue.class, new TileEntityKyoshiRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityYangchenStatue.class, new TileEntityYangchenRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpiritPortal.class, new TileEntitySpiritPortalRenderer());

		MinecraftForgeClient.registerItemRenderer(mod_Avatar.AirStaffItem, (IItemRenderer) new ItemRenderAirStaff());
		IItemRenderer gliderRenderer = new RenderGliderInHand();
		MinecraftForgeClient.registerItemRenderer(mod_Avatar.GliderItemWhite, gliderRenderer);
		MinecraftForgeClient.registerItemRenderer(mod_Avatar.GliderItemOrange, gliderRenderer);
		MinecraftForgeClient.registerItemRenderer(mod_Avatar.GliderItemMagenta, gliderRenderer);
		MinecraftForgeClient.registerItemRenderer(mod_Avatar.GliderItemLightBlue, gliderRenderer);
		MinecraftForgeClient.registerItemRenderer(mod_Avatar.GliderItemYellow, gliderRenderer);
		MinecraftForgeClient.registerItemRenderer(mod_Avatar.GliderItemLightGreen, gliderRenderer);
		MinecraftForgeClient.registerItemRenderer(mod_Avatar.GliderItemPink, gliderRenderer);
		MinecraftForgeClient.registerItemRenderer(mod_Avatar.GliderItemGrey , gliderRenderer);
		MinecraftForgeClient.registerItemRenderer(mod_Avatar.GliderItemLightGreen, gliderRenderer);
		MinecraftForgeClient.registerItemRenderer(mod_Avatar.GliderItemCyan , gliderRenderer);
		MinecraftForgeClient.registerItemRenderer(mod_Avatar.GliderItemPurple, gliderRenderer);
		MinecraftForgeClient.registerItemRenderer(mod_Avatar.GliderItemBlue, gliderRenderer);
		MinecraftForgeClient.registerItemRenderer(mod_Avatar.GliderItemBrown, gliderRenderer);
		MinecraftForgeClient.registerItemRenderer(mod_Avatar.GliderItemGreen, gliderRenderer);
		MinecraftForgeClient.registerItemRenderer(mod_Avatar.GliderItemRed, gliderRenderer);
		MinecraftForgeClient.registerItemRenderer(mod_Avatar.GliderItemBlack, gliderRenderer);
		
		RenderingRegistry.registerBlockHandler(99, new BlueFireRenderer());

		mod_Avatar.renderID = RenderingRegistry.getNextAvailableRenderId();

		FMLCommonHandler.instance().bus().register(new ClientTickHandler());
		MinecraftForge.EVENT_BUS.register(new ClientForgeListener());
		
		key = new KeyBind();
		FMLCommonHandler.instance().bus().register(key);

		System.out.println("[Avatar] The ClientProxy has loaded.");
	}

	@Override
	public void registerSoundHandler() {
		MinecraftForge.EVENT_BUS.register(new SoundHandler());
	}

}
