package com.clowcadia.test;

import com.clowcadia.test.utils.Utils;

import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
    
    public void preInit() {
        Utils.getLogger().info("CommonProxy: preInit");
    }
	
	public void init() {
		Utils.getLogger().info("CommonProxy: init");
        
		NetworkRegistry.INSTANCE.registerGuiHandler(TestModHandler.instance, new GuiHandler());
	}
    

}
