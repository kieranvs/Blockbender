package kieranvs.avatar.entity;

import kieranvs.avatar.util.FileLocation;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityEarthMan extends EntityPerson {

	public EntityEarthMan(World par1World) {
		super(par1World);
	}
	
	public EntityEarthMan(World par1World, int xOrigin, int yOrigin, int zOrigin, int radiusOfTown){
		super(par1World, xOrigin, yOrigin, zOrigin, radiusOfTown);
	}
	
	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(24D);
	}

	@Override
	protected void setupTasks() {
		this.TemplateSetupBasic();
	}

	@Override
	protected String getHurtSound() {
		return "random.classic_hurt";
	}

	@Override
	protected String getDeathSound() {
		return "random.classic_hurt";
	}

	@Override
	public String getTextureLocation() {
		return FileLocation.ENTITYTEXTURE + "EarthKingdomMan.png";
	}

}
