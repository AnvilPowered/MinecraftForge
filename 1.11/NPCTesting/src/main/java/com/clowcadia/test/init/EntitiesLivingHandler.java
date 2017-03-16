package com.clowcadia.test.init;


import com.clowcadia.test.ModHandler;
import com.clowcadia.test.entities.living.Test;
import com.clowcadia.test.render.RenderTestFactory;
import com.clowcadia.test.utils.Utils;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntitiesLivingHandler {
	
	public static void registerEntity(){
		Utils.getLogger().info("EntitiesLivingHandler: registerEntity");
		
		createEntity(Test.class, "Test", 0xa62323, 0xed1515, 1);
	}
	
	private static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor, int id){
		Utils.getLogger().info("EntitiesLivingHandler: createEntity");
		
		EntityRegistry.registerModEntity(new ResourceLocation(ModHandler.modId, entityName), entityClass, entityName, id, ModHandler.instance, 64, 1, true, solidColor, spotColor);
	}
	
	public static void registerAllModels(){
		Utils.getLogger().info("EntitiesLivingHandler: registerAllModels");
		
		registerMobModels();
	}
	
	private static void registerMobModels(){
		Utils.getLogger().info("EntitiesLivingHandler: registerMobModels");
		
		registerMobModel(Test.class, RenderTestFactory.INSTANCE);
	}
	
	
	private static <T extends Entity> void registerMobModel(Class<T> entity, IRenderFactory<? super T> renderFactory)
	{
		Utils.getLogger().info("EntitiesLivingHandler: registerMobModel");
		
		RenderingRegistry.registerEntityRenderingHandler(entity, renderFactory);
	}

}
