package com.clowcadia.test.render;

import com.clowcadia.test.npc.Basic;

import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.ResourceLocation;

public class RenderBasic extends RenderLiving{

	 private static final ResourceLocation BASIC_TEXTURES = new ResourceLocation("textures/entity/cow/cow.png");

	    public RenderBasic(RenderManager p_i47210_1_)
	    {
	        super(p_i47210_1_, new ModelCow(), 0.7F);
	    }

	    /**
	     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	     */
	    protected ResourceLocation getEntityTexture(Basic entity)
	    {
	        return BASIC_TEXTURES;
	    }

		@Override
		protected ResourceLocation getEntityTexture(Entity entity) {
			// TODO Auto-generated method stub
			return this.getEntityTexture((Basic)entity);
		}

}
