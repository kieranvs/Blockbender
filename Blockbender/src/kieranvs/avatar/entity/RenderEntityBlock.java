package kieranvs.avatar.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RenderEntityBlock extends Render {

	private final RenderBlocks renderBlocks = new RenderBlocks();

	public RenderEntityBlock(){
		this.shadowSize = 0.5F;
	}

	@Override
	public void doRender(Entity e, double posX, double posY, double posZ, float poop1, float poop2){
		EntityBlock entityBlock = (EntityBlock) e;
		World world = entityBlock.worldObj;
		Block block = entityBlock.getImitatingBlock();
		int i = MathHelper.floor_double(entityBlock.posX);
		int j = MathHelper.floor_double(entityBlock.posY);
		int k = MathHelper.floor_double(entityBlock.posZ);

		if (block != null && block != world.getBlock(i, j, k))
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float)posX, (float)posY, (float)posZ);
			this.bindEntityTexture(entityBlock);
			GL11.glDisable(GL11.GL_LIGHTING);
			Tessellator tessellator;
			this.renderBlocks.setRenderBoundsFromBlock(block);
			this.renderBlocks.renderBlockSandFalling(block, world, 0, 200, 0, entityBlock.metadata);

			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glPopMatrix();
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1){
		return TextureMap.locationBlocksTexture;
	}

}
