package com.clowcadia.test.render;

import net.minecraftforge.fml.relauncher.SideOnly;

import com.clowcadia.test.TestModHandler;
import com.clowcadia.test.entities.Test;
import com.clowcadia.test.utils.Utils;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;

@SideOnly(Side.CLIENT)
public class RenderEntityTest extends RenderLiving{
	
	private static final ResourceLocation EntityTestTextures = new ResourceLocation(TestModHandler.modId + ":textures/entities/test.png");
		
	public RenderEntityTest(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
		super(rendermanagerIn, modelbaseIn, shadowsizeIn);
		Utils.getLogger().info("RenderEntityTest: Constructor");
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		//Utils.getLogger().info("RenderEntityTest: getEntityTexture");
		
		return EntityTestTextures;
	}

}
 
