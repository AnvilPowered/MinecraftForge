package com.clowcadia.test;

import com.clowcadia.test.init.GuiHandler;
import com.clowcadia.test.init.ModelHandler;
import com.clowcadia.test.init.EntitiesHandler;
import com.clowcadia.test.utils.Utils;

import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy{
	
	public void preInit(){
		Utils.getLogger().info("ClientProxy: preInit");
		super.preInit();
		
		ModelHandler.registerAllModels();
	}
	
	@Override
	public void init(){
		Utils.getLogger().info("ClientProxy: init");
		super.init();
		
		EntitiesHandler.registerEntity();
		NetworkRegistry.INSTANCE.registerGuiHandler(ModHandler.instance, new GuiHandler());
	}

}
