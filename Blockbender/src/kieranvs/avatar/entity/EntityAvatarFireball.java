package kieranvs.avatar.entity;

import kieranvs.avatar.util.AvatarDamageSource;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityAvatarFireball extends EntityFireball 
{
    public int power = 1;

    public EntityAvatarFireball(World par1World, EntityLivingBase par2EntityLiving, double par3, double par5, double par7, int power) {
        super(par1World, par2EntityLiving.posX, par2EntityLiving.posY + 1.62, par2EntityLiving.posZ, par3, par5, par7);
        this.power = power;
        this.shootingEntity = par2EntityLiving;
    }

    protected void onImpact(MovingObjectPosition par1MovingObjectPosition) {
        if (!this.worldObj.isRemote) {
            if (par1MovingObjectPosition.entityHit != null) {
                par1MovingObjectPosition.entityHit.attackEntityFrom(AvatarDamageSource.firebending, 6);
            }

            this.worldObj.newExplosion((Entity)null, this.posX, this.posY, this.posZ, (float)this.power, true, true);
            this.setDead();
        }
    }

    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("ExplosionPower", this.power);
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        if (par1NBTTagCompound.hasKey("ExplosionPower"))
        {
            this.power = par1NBTTagCompound.getInteger("ExplosionPower");
        }
    }
}
