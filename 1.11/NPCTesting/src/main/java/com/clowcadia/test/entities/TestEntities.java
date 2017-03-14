package com.clowcadia.test.entities;

import java.util.BitSet;

import com.clowcadia.test.TestModHandler;
import com.clowcadia.test.utils.Utils;

import net.minecraft.entity.EntityList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class TestEntities {
	
	public static void registerEntity(){
		Utils.getLogger().info("TestEntities: registerEntity");
		
		createEntity(Test.class, "Test", 0xa62323, 0xed1515, 1);
	}
	
	public static void createEntity(Class entityClass, String entityName, int solidColor, int spotColor, int id){
		Utils.getLogger().info("TestEntiteis: createEntity");
		
		EntityRegistry.registerModEntity(new ResourceLocation(TestModHandler.modId,entityName), entityClass, entityName, id, TestModHandler.instance, 64, 1, true, solidColor, spotColor);
	}

}
