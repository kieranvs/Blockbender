package kieranvs.avatar.ai;

import kieranvs.avatar.entity.EntityPerson;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

public class EntityAIWanderInTown extends EntityAIBase {

	private EntityPerson entity;
	private double xPosition;
	private double yPosition;
	private double zPosition;
	private float speed;

	public EntityAIWanderInTown(EntityPerson entity, float speed) {
		this.entity = entity;
		this.speed = speed;
		this.setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {

		if ((Math.abs(this.entity.posX - this.entity.xOrigin) <= entity.radiusOfTown) && (Math.abs(this.entity.posY - this.entity.yOrigin) <= entity.radiusOfTown) && (Math.abs(this.entity.posZ - this.entity.zOrigin) <= entity.radiusOfTown)) {
			Vec3 var1 = RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);
			if (var1 == null) {
				return false;
			}
			else {
				if ((Math.abs(var1.xCoord - entity.xOrigin) <= entity.radiusOfTown) && (Math.abs(var1.yCoord - entity.yOrigin) <= entity.radiusOfTown) && (Math.abs(var1.zCoord - entity.zOrigin) <= entity.radiusOfTown)) {
					this.xPosition = var1.xCoord;
					this.yPosition = var1.yCoord;
					this.zPosition = var1.zCoord;
				}
				return true;
			}
		}
		else {
			this.xPosition = this.entity.xOrigin;
			this.yPosition = this.entity.yOrigin;
			this.zPosition = this.entity.zOrigin;
			return true;
		}

	}

	@Override
	public boolean continueExecuting() {
		return !this.entity.getNavigator().noPath();
	}

	@Override
	public void startExecuting() {
		this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
	}
}
