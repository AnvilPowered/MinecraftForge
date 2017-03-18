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
    private boolean targetSet;/*
    private int targetX;
    private int targetY;
    private int targetZ;*/
    
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
        
        if (this.test.world != null && !this.test.world.isRemote) {
            if (stack.getItem() == ItemHandler.target) {
                
                //NBTTagCompound nbt = stack.getItem().getNBTShareTag(stack);
                NBTTagCompound nbt = stack.getTagCompound();
                
                /*this.targetX = test.getTargetPos("x");
                this.targetY = test.getTargetPos("y");
                this.targetZ = test.getTargetPos("z");*/
                //Utils.getLogger().info("AIGoto: shouldExecute: " + stack.getItem().getNBTShareTag(stack).getInteger("targetX") + " " + stack.getItem().getNBTShareTag(stack).getInteger("targetY") + " " + stack.getItem().getNBTShareTag(stack).getInteger("targetZ"));
                Utils.getLogger().info(nbt.getInteger("targetX"));
                this.targetSet = false;//! (test.getDistance(stack.getTagCompound().getInteger("targetX"), stack.getTagCompound().getInteger("targetY"), stack.getTagCompound().getInteger("targetZ")) < 1);
            }else{
                targetSet = false;
            }
        }else{
            targetSet = false;
        }
        
        return targetSet;
    }
    
    public void startExecuting(){
        Utils.getLogger().info("AIGoto: startExecuting");
        
        this.timeToRecalcPath = 0;
    }
    
    public boolean continueExecuting() {
        //Utils.getLogger().info("AIGoto: continueExecuting");
        return false;//!(test.getDistance(targetX, targetY, targetZ) < 1);
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
            //test.getNavigator().tryMoveToXYZ(targetX, targetY, targetZ, goToSpeed);
        }
    }
}
