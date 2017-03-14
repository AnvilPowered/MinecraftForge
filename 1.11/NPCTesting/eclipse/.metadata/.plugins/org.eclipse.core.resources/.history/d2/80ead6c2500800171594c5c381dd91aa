package com.clowcadia.test;

import com.clowcadia.test.NPCHandler;
import com.clowcadia.test.entities.Test;
import com.clowcadia.test.model.ModelTest;
import com.clowcadia.test.npc.Basic;
import com.clowcadia.test.npc.BasicRF;
import com.clowcadia.test.render.RenderBasic;
import com.clowcadia.test.render.RenderEntityTest;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.event.terraingen.WorldTypeEvent.InitBiomeGens;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ClientProxy extends CommonProxy{
	
	
	@Override
	public void init(){
		
		NetworkRegistry.INSTANCE.registerGuiHandler(TestModHandler.instance, new GuiHandler());
	}

}
