package com.clowcadia.test;

import com.clowcadia.test.utils.Utils;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModHandler.modId, name = ModHandler.name, version = ModHandler.version)
public class ModHandler {
	public static final String modId = "testmod";
	public static final String name = "Test Mod";
	public static final String version = "1.0.0";
	
	@Mod.Instance(modId)
	public static ModHandler instance;
	
	@SidedProxy(serverSide = "com.clowcadia.test.CommonProxy", clientSide = "com.clowcadia.test.ClientProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		Utils.getLogger().info("ModHandler: preInit");
		
		proxy.preInit();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		Utils.getLogger().info("ModHandler: init");
		
		proxy.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event){
		Utils.getLogger().info("TestModhandler: postInit");
	}
}
