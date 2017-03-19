package com.clowcadia.test.entities.living.ai;

import com.clowcadia.test.entities.living.Test;
import com.clowcadia.test.init.ItemHandler;
import com.clowcadia.test.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;

public class AIGoto extends EntityAIBase{
    
    private final Test test;
    private int timeToRecalcPath;
    private final double goToSpeed;
    
    public AIGoto(Test test, double goToSpeed) {
        Utils.getLogger().info("AIGoto: Constructor");
        this.test = test;
        this.goToSpeed = goToSpeed;
        this.setMutexBits(1);
    }
    
    @Override
    public boolean shouldExecute() {
        //Utils.getLogger().info("AIGoto: shouldExecute");
        return isItemAvailable();
    }
    
    public void startExecuting(){
        Utils.getLogger().info("AIGoto: startExecuting");
        
        this.timeToRecalcPath = 0;
    }
    
    public boolean continueExecuting() {
        //Utils.getLogger().info("AIGoto: continueExecuting");
        return isItemAvailable();
    }
    
    @Override
    public void resetTask() {
        Utils.getLogger().info("AIGoto: resetTask");
    }
    
    @Override
    public void updateTask() {
        //Utils.getLogger().info("AIGoto: updateTask");
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            test.getNavigator().tryMoveToXYZ(getPos().getX(), getPos().getY(), getPos().getZ(), goToSpeed);
            
            if (test.isCollided){
                if(getBlock().isWood(test.world, getPos())){
                    test.world.setBlockToAir(getPos());
                    
                    for(int i=1; getBlock().isWood(test.world, getPos().down(i)); i++){
                        test.world.setBlockToAir(getPos().down(i));
                    }
    
                    for(int i=1; getBlock().isWood(test.world, getPos().up(i)); i++){
                        test.world.setBlockToAir(getPos().up(i));
                    }
                    
                }
                
            }
        }
    }
    private boolean isItemAvailable(){
        ItemStack stack = this.test.handler.getStackInSlot(0);
        if (this.test.world != null && !this.test.world.isRemote && stack.getItem() == ItemHandler.target){
            Utils.getLogger().info(" SET POSITION "+getPos());
            return ! (test.getDistanceSq(getPos()) < 1);
        }else return false;
    }
    private BlockPos getPos(){
        ItemStack stack = this.test.handler.getStackInSlot(0);
        NBTTagCompound nbt = stack.getTagCompound();
        return NBTUtil.getPosFromTag(nbt);
    }
    
    private Block getBlock(){
        IBlockState ibs = test.world.getBlockState(getPos());
        return ibs.getBlock();
    }
    
    private Block getBlock(BlockPos pos){
        IBlockState ibs = test.world.getBlockState(pos);
        return ibs.getBlock();
    }
}
