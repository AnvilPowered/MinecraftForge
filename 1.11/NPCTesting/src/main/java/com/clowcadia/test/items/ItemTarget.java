package com.clowcadia.test.items;

import com.clowcadia.test.utils.Utils;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemTarget extends Item {
        
    public ItemTarget() {
        Utils.getLogger().info("ItemTarget: Constructor");
        setUnlocalizedName("target");
        setCreativeTab(CreativeTabs.REDSTONE);
        
    }
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        Utils.getLogger().info("ItemTarget: onItemUse");
        
        if (!worldIn.isRemote){
            ItemStack stack = player.getHeldItem(hand);
            if(pos!=null){
                NBTTagCompound nbt = NBTUtil.createPosTag(pos);
                stack.setTagCompound(nbt);
                Utils.getLogger().info("ItemTarget: onItemUse: "+NBTUtil.getPosFromTag(nbt));
            }
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }
    
}