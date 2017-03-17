package com.clowcadia.test.entities.living.ai;

import com.clowcadia.test.entities.living.Test;
import com.clowcadia.test.utils.Utils;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.BlockPos;

public class AIGoto extends EntityAIBase{
	
	private final Test test;
	private BlockPos targetPos;
    private final double goToSpeed;
    private int timeToRecalcPath;
    
    public AIGoto(Test test, double goToSpeed) {
    	Utils.getLogger().info("AIGoto: Constructor");
		this.test = test;
		this.goToSpeed = goToSpeed;
		this.setMutexBits(1);
    }
		
	@Override
	public boolean shouldExecute() {
		//Utils.getLogger().info("AIGoto: shouldExecute");
		this.targetPos = test.targetPos;
        if (test.targetPos != null)
        Utils.getLogger().info("AIGoto: shouldExecute: "+test.getDistanceSq(test.targetPos));
        return test.targetPos != null && !(test.getDistanceSq(test.targetPos) < 1) ;
	}

    public void startExecuting(){
        Utils.getLogger().info("AIGoto: startExecuting");
    
        this.timeToRecalcPath = 0;
	}
    
    public boolean continueExecuting() {
        return !(test.getDistanceSq(test.targetPos) < 1);
    }
    
    @Override
    public void resetTask() {
    }
    
    @Override
    public void updateTask() {
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            test.getNavigator().tryMoveToXYZ(test.targetPos.getX(), test.targetPos.getY(), test.targetPos.getY(), goToSpeed);
        }
    }
}
