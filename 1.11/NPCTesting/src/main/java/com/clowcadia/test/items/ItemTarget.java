package com.clowcadia.test.items;

import com.clowcadia.test.utils.Utils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemTarget extends Item {
    
    private BlockPos target;
    
    public ItemTarget (){
        Utils.getLogger().info("ItemTarget: Constructor");
        
        setUnlocalizedName("target");
        setCreativeTab(CreativeTabs.REDSTONE);
        
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        Utils.getLogger().info("ItemTarget: onItemRightClick");
        Utils.getLogger().info("ItemTarget: onItemRightClick: "+target+" "+this.getUnlocalizedName());
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
    
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Utils.getLogger().info("ItemTarget: onItemUse");
        
        this.target = pos;
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
        
    }
    
    public BlockPos getTarget() {
        return target;
    }
}
