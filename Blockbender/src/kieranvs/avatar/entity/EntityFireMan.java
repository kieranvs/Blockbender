package kieranvs.avatar.entity;

import kieranvs.avatar.ai.EntityAIAttackWithBending;
import kieranvs.avatar.ai.EntityAIWanderInTown;
import kieranvs.avatar.util.FileLocation;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class EntityFireMan extends EntityPerson {
	
	public EntityFireMan(World par1World) {
		super(par1World);
	}
	
	public EntityFireMan(World par1World, int xOrigin, int yOrigin, int zOrigin, int radiusOfTown) {
		super(par1World, xOrigin, yOrigin, zOrigin, radiusOfTown);
	}
	
	@Override
	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20D);
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
		return FileLocation.ENTITYTEXTURE + "FireNationMan.png";
	}

}
