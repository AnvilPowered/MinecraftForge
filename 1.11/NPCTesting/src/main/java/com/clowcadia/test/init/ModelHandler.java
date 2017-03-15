package com.clowcadia.test.init;

import com.clowcadia.test.entities.living.Test;
import com.clowcadia.test.render.RenderTestFactory;
import com.clowcadia.test.utils.Utils;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ModelHandler {
	
	public static void registerAllModels(){
		Utils.getLogger().info("ModelHandler: registerAllModels");
		
		registerMobModels();
	}
	
	private static void registerMobModels(){
		Utils.getLogger().info("ModelHandler: registerMobModels");
		
		registerMobModel(Test.class, RenderTestFactory.INSTANCE);
	}
	

	private static <T extends Entity> void registerMobModel(Class<T> entity, IRenderFactory<? super T> renderFactory)
	{
		Utils.getLogger().info("ModelHandler: registerMobModel");
		
		RenderingRegistry.registerEntityRenderingHandler(entity, renderFactory);
	}

}
