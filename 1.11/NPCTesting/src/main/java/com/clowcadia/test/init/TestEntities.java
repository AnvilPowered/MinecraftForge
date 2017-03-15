package com.clowcadia.test.init;


import com.clowcadia.test.TestModHandler;
import com.clowcadia.test.entities.Test;
import com.clowcadia.test.utils.Utils;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class TestEntities {
	
	public static void registerEntity(){
		Utils.getLogger().info("TestEntities: registerEntity");
		
		createEntity(Test.class, "Test", 0xa62323, 0xed1515, 1);
	}
	
	private static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor, int id){
		Utils.getLogger().info("TestEntities: createEntity");
		
		EntityRegistry.registerModEntity(new ResourceLocation(TestModHandler.modId, entityName), entityClass, entityName, id, TestModHandler.instance, 64, 1, true, solidColor, spotColor);
	}

}
