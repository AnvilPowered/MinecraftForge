package com.clowcadia.test.init;


import com.clowcadia.test.ModHandler;
import com.clowcadia.test.entities.living.Test;
import com.clowcadia.test.utils.Utils;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntitiesHandler {
	
	public static void registerEntity(){
		Utils.getLogger().info("EntitiesHandler: registerEntity");
		
		createEntity(Test.class, "Test", 0xa62323, 0xed1515, 1);
	}
	
	private static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor, int id){
		Utils.getLogger().info("EntitiesHandler: createEntity");
		
		EntityRegistry.registerModEntity(new ResourceLocation(ModHandler.modId, entityName), entityClass, entityName, id, ModHandler.instance, 64, 1, true, solidColor, spotColor);
	}

}
