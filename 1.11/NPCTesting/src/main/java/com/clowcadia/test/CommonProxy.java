package com.clowcadia.test;

import com.clowcadia.test.utils.Utils;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
	
	public void init() {
		Utils.getLogger().info("CommonProxy: init");
		
		NetworkRegistry.INSTANCE.registerGuiHandler(TestModHandler.instance, new GuiHandler());		
	}

}
