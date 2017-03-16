package com.clowcadia.test;

import com.clowcadia.test.init.EntitiesLivingHandler;
import com.clowcadia.test.init.ItemHandler;
import com.clowcadia.test.init.GuiHandler;
import com.clowcadia.test.utils.Utils;

import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy{
	
	public void preInit(){
		Utils.getLogger().info("ClientProxy: preInit");
		super.preInit();
		
		ItemHandler.registerItems();
		EntitiesLivingHandler.registerAllModels();
	}
	
	@Override
	public void init(){
		Utils.getLogger().info("ClientProxy: init");
		super.init();
		
		ItemHandler.registerRenders();
		EntitiesLivingHandler.registerEntity();
		NetworkRegistry.INSTANCE.registerGuiHandler(ModHandler.instance, new GuiHandler());
	}

}
