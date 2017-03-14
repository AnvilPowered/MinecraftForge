package com.clowcadia.test;

import com.clowcadia.test.entities.TestEntities;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = TestModHandler.modId, name = TestModHandler.name, version = TestModHandler.version)
public class TestModHandler {
	public static final String modId = "testmod";
	public static final String name = "Test Mod";
	public static final String version = "1.0.0";
	
	@Mod.Instance(modId)
	public static TestModHandler instance;
	
	@SidedProxy(serverSide = "com.clowcadia.test.CommonProxy", clientSide = "com.clowcadia.test.ClientProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		TestModelManager.registerAllModels();//Only belongs here, not server or client side
		NPCHandler.registerNPCs();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		
		TestEntities.registerEntity();
		
		proxy.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
	}
}
