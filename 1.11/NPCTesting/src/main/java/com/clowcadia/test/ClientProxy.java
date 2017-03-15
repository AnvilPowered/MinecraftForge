package com.clowcadia.test;

import com.clowcadia.test.init.TestEntities;
import com.clowcadia.test.utils.Utils;

import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy{
	
	public void preInit(){
		Utils.getLogger().info("ClientProxy: preInit");
		
		TestModelManager.registerAllModels();
	}
	
	@Override
	public void init(){
		Utils.getLogger().info("ClientProxy: init");
		
		TestEntities.registerEntity();
		NetworkRegistry.INSTANCE.registerGuiHandler(TestModHandler.instance, new GuiHandler());
	}

}
