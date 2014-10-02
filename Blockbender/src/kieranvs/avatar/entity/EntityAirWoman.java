package kieranvs.avatar.entity;

import kieranvs.avatar.util.FileLocation;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityAirWoman extends EntityPerson {

	public EntityAirWoman(World par1World) {
		super(par1World);
	}

	public EntityAirWoman(World par1World, int xOrigin, int yOrigin, int zOrigin, int radiusOfTown) {
		super(par1World, xOrigin, yOrigin, zOrigin, radiusOfTown);
	}
	
	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15D);
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
    protected float getSoundPitch(){
		return (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.5F;
	}

	@Override
	public String getTextureLocation() {
		return FileLocation.ENTITYTEXTURE + "AirNomadWoman.png";
	}


}
