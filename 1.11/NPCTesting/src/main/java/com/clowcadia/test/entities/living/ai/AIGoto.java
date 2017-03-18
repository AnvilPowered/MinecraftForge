package com.clowcadia.test.entities.living.ai;

import com.clowcadia.test.entities.living.Test;
import com.clowcadia.test.init.ItemHandler;
import com.clowcadia.test.utils.Utils;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class AIGoto extends EntityAIBase{
    
    private final Test test;
    private final double goToSpeed;
    private int timeToRecalcPath;
    private boolean targetSet;
    
    public AIGoto(Test test, double goToSpeed) {
        Utils.getLogger().info("AIGoto: Constructor");
        this.test = test;
        this.goToSpeed = goToSpeed;
        this.setMutexBits(1);
    }
    
    @Override
    public boolean shouldExecute() {
        //Utils.getLogger().info("AIGoto: shouldExecute");
        
        ItemStack stack = this.test.handler.getStackInSlot(0);
        if (this.test.world != null && !this.test.world.isRemote){
            if(stack.getItem() == ItemHandler.target) {
                NBTTagCompound nbt = stack.getTagCompound();
                Utils.getLogger().info(nbt.getInteger("targetX"));
                this.targetSet = false;//! (test.getDistance(, , ) < 1);
            }else targetSet = false;
        }else targetSet = false;
        return targetSet;
    }
    
    public void startExecuting(){
        Utils.getLogger().info("AIGoto: startExecuting");
        
        this.timeToRecalcPath = 0;
    }
    
    public boolean continueExecuting() {
        //Utils.getLogger().info("AIGoto: continueExecuting");
        return false;//!(test.getDistance(, , ) < 1);
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
            //test.getNavigator().tryMoveToXYZ(, , , goToSpeed);
        }
    }
}
