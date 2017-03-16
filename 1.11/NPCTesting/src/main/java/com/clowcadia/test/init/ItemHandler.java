package com.clowcadia.test.init;

import com.clowcadia.test.items.Target;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemHandler {
    
    private static Item target = new Target();
    
    public static void registerItems(){
    
        GameRegistry.register(target);
        
    }
    
}
