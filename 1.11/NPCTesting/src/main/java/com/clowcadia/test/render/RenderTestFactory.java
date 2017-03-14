package com.clowcadia.test.render;

import com.clowcadia.test.model.ModelTest;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import scala.xml.dtd.PublicID;

public class RenderTestFactory implements IRenderFactory<EntityTameable>{
	
	public static final RenderTestFactory INSTANCE = new RenderTestFactory();

	@Override
	public Render<? super EntityTameable> createRenderFor(RenderManager manager) {
		// TODO Auto-generated method stub
		return new RenderEntityTest(manager, new ModelBiped(), 1.0f);
	}

}
