package com.clowcadia.test.init;

import com.clowcadia.test.items.Target;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemHandler {
    
    private static Item target = new Target();
    
    public static void registerItems(){
    
        GameRegistry.register(target);
        
    }
    
    public static void registerRenders(){
        registerRender(target);
    }
    
    private static void registerRender(Item item){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item,0,
                new ModelResourceLocation(item.getRegistryName(),"inventory"));
    }
    
}
