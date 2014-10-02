package kieranvs.avatar.entity;

import kieranvs.avatar.util.FileLocation;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityAirMan extends EntityPerson {

	public EntityAirMan(World par1World) {
		super(par1World);
	}

	public EntityAirMan(World par1World, int xOrigin, int yOrigin, int zOrigin, int radiusOfTown) {
		super(par1World, xOrigin, yOrigin, zOrigin, radiusOfTown);
	}

	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(17D);
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
		return FileLocation.ENTITYTEXTURE + "AirNomadMan.png";
	}

}
