package com.clowcadia.test.entities.living.ai;

import com.clowcadia.test.entities.living.Test;
import com.clowcadia.test.utils.Utils;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathNodeType;

public class AIGoto extends EntityAIBase{
	
	private final Test test;
    private final double goToSpeed;
    private int timeToRecalcPath;
    private float oldWaterCost;
    
    public AIGoto(Test test, double goToSpeed) {
    	Utils.getLogger().info("AIGoto: Constructor");
		this.test = test;
		this.goToSpeed = goToSpeed;
		this.setMutexBits(1);
    }
		
	@Override
	public boolean shouldExecute() {
		Utils.getLogger().info("AIGoto: shouldExecute");
        
        return !(test.getDistance(- 30, 59, 261) < 2);
	}

    public void startExecuting(){
        Utils.getLogger().info("AIGoto: startExecuting");
    
        this.timeToRecalcPath = 0;
        //this.oldWaterCost = this.test.getPathPriority(PathNodeType.WATER);
        //this.test.setPathPriority(PathNodeType.WATER, 0.0F);
        //
	}
    
    public boolean continueExecuting() {
        return !(test.getDistance(- 31, 59, 255) < 0);
    }
    
    @Override
    public void resetTask() {
        //this.test.getNavigator().clearPathEntity();
        //this.test.setPathPriority(PathNodeType.WATER, this.oldWaterCost);
    }
    
    @Override
    public void updateTask() {
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            test.getNavigator().tryMoveToXYZ(-31, 59, 255, goToSpeed);
        }
    }
}
