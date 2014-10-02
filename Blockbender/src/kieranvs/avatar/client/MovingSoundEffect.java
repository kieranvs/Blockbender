package kieranvs.avatar.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class MovingSoundEffect extends MovingSound{
	
    private final Entity entity;
    private float field_147669_l = 0.0F;
    private static final String __OBFID = "CL_00001118";

    public MovingSoundEffect(Entity entity, String resourceLocation){
        super(new ResourceLocation(resourceLocation));
        this.entity = entity;
        this.repeat = true;
        this.field_147665_h = 0;
    }

    /**
     * Updates the JList with a new model.
     */
    public void update(){
        if (this.entity.isDead){
            this.donePlaying = true;
        }
        else{
            this.xPosF = (float)this.entity.posX;
            this.yPosF = (float)this.entity.posY;
            this.zPosF = (float)this.entity.posZ;
            float f = MathHelper.sqrt_double(this.entity.motionX * this.entity.motionX + this.entity.motionZ * this.entity.motionZ);

            if ((double)f >= 0.01D){
                this.field_147669_l = MathHelper.clamp_float(this.field_147669_l + 0.0025F, 0.0F, 1.0F);
                this.volume = 0.0F + MathHelper.clamp_float(f, 0.0F, 0.5F) * 0.7F;
            }else{
                this.field_147669_l = 0.0F;
                this.volume = 0.0F;
            }
        }
    }
}