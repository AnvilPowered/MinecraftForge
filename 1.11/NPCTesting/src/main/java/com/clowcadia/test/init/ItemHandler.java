package com.clowcadia.test.init;

import com.clowcadia.test.ModHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemHandler {
    
    private static Item target;
    
    public static void registerItems(){
        
        target = new Item().setUnlocalizedName("target").setCreativeTab(CreativeTabs.REDSTONE);
        GameRegistry.register(target,new ResourceLocation(ModHandler.modId, "target"));
        
    }
    
    public static void registerRenders(){
        registerRender(target);
    }
    
    private static void registerRender(Item item){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item,0,
                new ModelResourceLocation(ModHandler.modId+":"+item.getUnlocalizedName().substring(5),"inventory"));
    }
    
}
