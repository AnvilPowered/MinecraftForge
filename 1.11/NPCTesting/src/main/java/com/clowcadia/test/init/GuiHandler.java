package com.clowcadia.test.init;


import java.security.PublicKey;


import com.clowcadia.test.containers.ContainerBasic;
import com.clowcadia.test.gui.GuiBasic;
import com.clowcadia.test.utils.Utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.datafix.fixes.EntityId;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{
	
	
	public static final int TEST = 1;	

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		Utils.getLogger().info("GuiHandler: Constructor");
		
		if(ID==TEST){
			Entity test = world.getEntityByID(x);
			
			return new ContainerBasic(player.inventory,test);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		Utils.getLogger().info("GuiHandler: getClientGuiElement");
		
		if(ID==TEST){
			Entity test = world.getEntityByID(x);
			return new GuiBasic(player.inventory, test);
		}
		return null;
	}

}
