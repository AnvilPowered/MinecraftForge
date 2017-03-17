package com.clowcadia.test.entities.living.ai;

import com.clowcadia.test.entities.living.Test;
import com.clowcadia.test.init.ItemHandler;
import com.clowcadia.test.utils.Utils;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;

public class AIGoto extends EntityAIBase{
	
	private final Test test;
    private final double goToSpeed;
    private int timeToRecalcPath;
    private boolean targetSet;
    private int targetX;
    private int targetY;
    private int targetZ;
    
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
        if (this.test.world != null) {
            if (! this.test.world.isRemote) {
                if (stack.getItem() == ItemHandler.target) {
                    this.targetX = stack.getItem().getNBTShareTag(stack).getInteger("targetX");
                    this.targetY = stack.getItem().getNBTShareTag(stack).getInteger("targetY");
                    this.targetZ = stack.getItem().getNBTShareTag(stack).getInteger("targetZ");
                    Utils.getLogger().info("AIGoto: shouldExecute: " + targetX + " " + targetY + " " + targetZ);
                    this.targetSet = ! (test.getDistance(targetX, targetY, targetZ) < 1);
                }else targetSet = false;
            }else targetSet = false;
        }else targetSet = false;
        return targetSet;
		/*this.targetPos = test.targetPos;
        if (test.targetPos != null)
        
        return test.targetPos != null && !(test.getDistanceSq(test.targetPos) < 1) ;*/
	}

    public void startExecuting(){
        Utils.getLogger().info("AIGoto: startExecuting");
    
        this.timeToRecalcPath = 0;
	}
    
    public boolean continueExecuting() {
        return !(test.getDistance(targetX, targetY, targetZ) < 1);
    }
    
    @Override
    public void resetTask() {
    }
    
    @Override
    public void updateTask() {
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            test.getNavigator().tryMoveToXYZ(targetX, targetY, targetZ, goToSpeed);
        }
    }
}
