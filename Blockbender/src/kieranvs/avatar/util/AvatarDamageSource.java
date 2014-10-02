package kieranvs.avatar.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;

public class AvatarDamageSource extends DamageSource {

	private static DamageSource inFire, onFire, lava, inWall, drown, starve, cactus, fall, outOfWorld, generic, explosion, field_76375_l, magic, wither, anvil, fallingBlock;

	public static final AvatarDamageSource firebending = new AvatarDamageSource(0);
	public static final AvatarDamageSource waterbending = new AvatarDamageSource(1);
	public static final AvatarDamageSource earthbending = new AvatarDamageSource(2);
	public static final AvatarDamageSource airbending = new AvatarDamageSource(3);

	private int element;

	protected AvatarDamageSource(int element) {
		super("bending");
		this.element = element;
	}

	/*getDeathMessage*/
	@Override
	public IChatComponent func_151519_b(EntityLivingBase entity) {
		String message;
		if(entity instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) entity;
			switch (element) {
			case 0:
				message = player.getDisplayName() + " was killed by firebending.";
			case 1:
				message = player.getDisplayName() + " was killed by waterbending.";
			case 2:
				message = player.getDisplayName() + " was killed by earthbending.";
			case 3:
				message = player.getDisplayName() + " was killed by airbending.";
			}
			message =  "";	
			return new ChatComponentText(message);
		}
		else{
			return super.func_151519_b(entity);
		}
	}

}
