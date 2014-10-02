package kieranvs.avatar.bending.earth;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import kieranvs.avatar.Protection;
import kieranvs.avatar.bending.AsynchronousAbility;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;

import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.BendingUtils;
import kieranvs.avatar.util.BlockActionPerformer;
import kieranvs.avatar.util.GenericProgressor;
import kieranvs.avatar.util.PacketSender;

public class EarthBuildWall extends AsynchronousAbility {
	
	private Location origin;
	private Vector direction;

	public EarthBuildWall(EntityLivingBase e, Location origin, Vector direction, int h, int length) {
		super(e, 30000);
		final int height = h;
		this.origin = origin;
		this.direction = direction.setY(0).straightifyXZ();
		new GenericProgressor(e, this.origin, this.direction, length, true, new BlockActionPerformer(){
			@Override
			public void performAction(BlockBukkit b) {
				Block m = b.getRelative(BlockBukkit.DOWN).getType();
				if(BendingUtils.isEarthBendable(m)){
					for(int i = 0; i<height; i++){
						PacketSender.spawnParticle("Avatar_dig", user.worldObj, b.getRelative(BlockBukkit.UP, i).getX(), b.getRelative(BlockBukkit.UP, i).getY(), b.getRelative(BlockBukkit.UP, i).getZ(), 50, 0.5);
						Protection.trySetBlock(user.worldObj, Blocks.stone, b.getRelative(BlockBukkit.UP, i).getX(), b.getRelative(BlockBukkit.UP, i).getY(), b.getRelative(BlockBukkit.UP, i).getZ());
					}
				}
				if(m == Blocks.dirt || m == Blocks.grass){
					for(int i = 0; i<height; i++){
						PacketSender.spawnParticle("Avatar_dig", user.worldObj, b.getRelative(BlockBukkit.UP, i).getX(), b.getRelative(BlockBukkit.UP, i).getY(), b.getRelative(BlockBukkit.UP, i).getZ(), 50, 0.5);
						Protection.trySetBlock(user.worldObj, Blocks.dirt, b.getRelative(BlockBukkit.UP, i).getX(), b.getRelative(BlockBukkit.UP, i).getY(), b.getRelative(BlockBukkit.UP, i).getZ());
					}
				}
				if(m == Blocks.sand || m == Blocks.sandstone){
					for(int i = 0; i<height; i++){
						PacketSender.spawnParticle("Avatar_dig", user.worldObj, b.getRelative(BlockBukkit.UP, i).getX(), b.getRelative(BlockBukkit.UP, i).getY(), b.getRelative(BlockBukkit.UP, i).getZ(), 50, 0.5);
						Protection.trySetBlock(user.worldObj, Blocks.sandstone, b.getRelative(BlockBukkit.UP, i).getX(), b.getRelative(BlockBukkit.UP, i).getY(), b.getRelative(BlockBukkit.UP, i).getZ());
					}
				}
				if(m == Blocks.stained_hardened_clay || m == Blocks.hardened_clay || m == Blocks.clay || m == Blocks.gravel){
					for(int i = 0; i<height; i++){
						PacketSender.spawnParticle("Avatar_dig", user.worldObj, b.getRelative(BlockBukkit.UP, i).getX(), b.getRelative(BlockBukkit.UP, i).getY(), b.getRelative(BlockBukkit.UP, i).getZ(), 50, 0.5);
						Protection.trySetBlockAndMeta(user.worldObj, m, b.getRelative(BlockBukkit.UP, i).getX(), b.getRelative(BlockBukkit.UP, i).getY(), b.getRelative(BlockBukkit.UP, i).getZ(), 
								b.getRelative(BlockBukkit.DOWN).getMetadata(), 0x02);
					}
				}

			}
		});
	}

	@Override
	public void update() {
		destroy();
	}

}
