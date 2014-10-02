package kieranvs.avatar.bending.earth;

import kieranvs.avatar.Protection;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;
import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.AvatarDamageSource;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.EntityActionPerformer;
import kieranvs.avatar.util.Messaging;
import kieranvs.avatar.util.PacketSender;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.PotionEffect;

public class EarthLaunch extends AsynchronousAbility {

	private long time;
	private int iteration;
	private Location location;
	private BlockBukkit block;
	private final float power;
	
	private final EntityLivingBase user2;

	public EarthLaunch(EntityLivingBase user, Long cooldown, float power) {
		super(user, cooldown);
		this.user = user;
		this.user2 = user;
		this.time = System.currentTimeMillis();
		this.iteration = 0;
		this.block = BlockBukkit.getTargetBlock(user);
		this.location = block.getLocation();
		this.power = power;
	}

	@Override
	public void update() {
		if(!BendingUtils.isEarthBendable(block.getType())){
			if(this.user instanceof EntityPlayer){
				Messaging.avatarMessage((EntityPlayer)this.user, "Target block is not an Earth bendable block!");
			}
			destroy();
			return;
		}
		BendingUtils.damageEntitiesWithCustomAction(this.user2, location, 2F, AvatarDamageSource.earthbending, 1, new EntityActionPerformer(){
			@Override
			public void performAction(Entity e) {
				PacketSender.sendVelocity(e, new Vector(0, power, 0));
				((EntityLivingBase) e).addPotionEffect(new PotionEffect(9, 20 * 4, 0));
			}
		});
		PacketSender.spawnParticle("Avatar_dig", user.worldObj, location.getX(), location.getY(), location.getZ(), 50, 0.5);
		Block blockType = null;
		int metadata = 0;
		if(BendingUtils.isEarthBendable(block.getType())){
			blockType = Blocks.stone;
		}
		if(block.getType() == Blocks.dirt || block.getType() == Blocks.grass){
			blockType = Blocks.dirt;
		}
		if(block.getType() == Blocks.sand || block.getType() == Blocks.sandstone){
			blockType = Blocks.sandstone;
		}
		if(block.getType() == Blocks.stained_hardened_clay || block.getType() == Blocks.hardened_clay || block.getType() == Blocks.clay || block.getType() == Blocks.gravel){
			blockType = block.getType();
			Location loc2 = this.location.getBlock().getRelative(BlockBukkit.DOWN).getLocation();
			metadata = loc2.getWorld().getBlockMetadata(loc2.getBlockX(), loc2.getBlockY(), loc2.getBlockZ());
		}
		Protection.trySetBlockAndMeta(user.worldObj, blockType, block.getRelative(BlockBukkit.UP).getX(), block.getRelative(BlockBukkit.UP).getY(), block.getRelative(BlockBukkit.UP).getZ(), metadata, 0x02);
		Protection.trySetBlockAndMeta(user.worldObj, blockType, block.getRelative(BlockBukkit.UP, 2).getX(), block.getRelative(BlockBukkit.UP, 2).getY(), block.getRelative(BlockBukkit.UP, 2).getZ(), metadata, 0x02);
		destroy();
		return;

	}

}
