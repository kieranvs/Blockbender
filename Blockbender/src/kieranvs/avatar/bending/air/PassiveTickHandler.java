package kieranvs.avatar.bending.air;

import kieranvs.avatar.mod_Avatar;
import kieranvs.avatar.bending.Ability;
import kieranvs.avatar.bukkit.BlockBukkit;
import kieranvs.avatar.bukkit.Location;

import kieranvs.avatar.bukkit.Vector;
import kieranvs.avatar.util.PacketSender;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;

public class PassiveTickHandler {

	private boolean isAirSoFar = false;
	private int firstY = 0;

	public void update() {
	}

	public void updatePlayer(EntityPlayer player) {
//		if (!Ability.isInUse(player, AirWindRun.class)) {
//			if (player.lastTickPosY > player.posY) {
//
//					Location l = new Location(player);
//					BlockBukkit bl = l.getBlock();
//
//					if (firstY == 0) {
//						firstY = (int) l.getY();
//					}
//
//					if (player.isSneaking()) {
//						Vector vel = new Vector(player);
//						vel.setY(-3);
//						PacketSender.sendVelocity(player, vel);
//						if (!(l.getBlock().getType() == Blocks.water || l.getBlock().getType() == Blocks.lava)) {
//							if (l.getY() - (l.getWorld().getHeightValue((int) l.getX(), (int) l.getZ())) <= 5) {
//								PacketSender.spawnParticle("Avatar_cloud", player.worldObj, player.posX, player.posY, player.posZ);
//							}
//						}
//						if (l.getBlock().getRelative(BlockBukkit.DOWN).getType() != Blocks.air || 
//								l.getBlock().getRelative(BlockBukkit.DOWN).getType() != Blocks.water ||
//								l.getBlock().getRelative(BlockBukkit.DOWN).getType() != Blocks.lava) {
//							
//							if (firstY >= 100 + player.worldObj.getHeightValue((int) l.getX(), (int) l.getZ())) {
//								new AirRing(player, 9, 3, 2, false);
//							} else if (firstY >= 70 + player.worldObj.getHeightValue((int) l.getX(), (int) l.getZ())) {
//								new AirRing(player, 6, 2, 1, false);
//							} else if (firstY >= 40 + player.worldObj.getHeightValue((int) l.getX(),(int) l.getZ())) {
//								new AirRing(player, 3, 1, 1, false);
//							}
//
//						}
//
//					} else {
//						if (firstY > player.worldObj.getHeightValue((int) l.getX(), (int) l.getZ()) + 10) {
//							
//							BlockBukkit b = new Location(player).getBlock();
//
//							if (b.getRelative(BlockBukkit.DOWN, 8).getType() != Blocks.air) {
//								isAirSoFar = true;
//							}
//							if (isAirSoFar) {
//								Vector vel = new Vector(player);
//								vel.setY(-0.3);
//								PacketSender.sendVelocity(player, vel);
//								PacketSender.spawnParticle("Avatar_cloud", player.worldObj, player.posX, player.posY, player.posZ);
//							}
//							if (b.getRelative(BlockBukkit.DOWN, 3).getType() != Blocks.air) {
//							isAirSoFar = false;
//							}
//						} else {
//							return;
//						}
//
//					}
//
//					if (l.distance(new Location(player.worldObj, l.getX(),
//							player.worldObj.getHeightValue((int) l.getX(),
//									(int) l.getZ()), l.getZ())) == 0) {
//						firstY = 0;
//					}
//
//				}
//		}
	}
}
