package com.clowcadia.test.init;

import com.clowcadia.test.ModHandler;
import com.clowcadia.test.items.ItemTarget;
import com.clowcadia.test.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemHandler {
    
    private static Item target;
    //
    public static void registerItems(){
        Utils.getLogger().info("ItemHandler: registerItems");
        
        target = new ItemTarget();
        registerItem(target);
        
    }
    
    private static void registerItem(Item item){
        Utils.getLogger().info("ItemHandler: registerItem");
        
        GameRegistry.register(item, new ResourceLocation(ModHandler.modId, item.getUnlocalizedName().substring(5)));
    }
    
    public static void registerRenders(){
        Utils.getLogger().info("ItemHandler: registerRenders");
        
        registerRender(target);
    }
    
    private static void registerRender(Item item){
        Utils.getLogger().info("ItemHandler: registerRender");
        
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item,0,
                new ModelResourceLocation(ModHandler.modId+":"+item.getUnlocalizedName().substring(5),"inventory"));
    }
    
}
