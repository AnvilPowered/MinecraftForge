package com.clowcadia.test.entities.ai;

import com.clowcadia.test.entities.Test;
import com.clowcadia.test.utils.Utils;
import net.minecraft.entity.ai.EntityAIBase;

public class TaskAIGoTo extends EntityAIBase{
	
	private final Test test;
    private final double goToSpeed;
    
    public TaskAIGoTo(Test test, double goToSpeed) {
    	Utils.getLogger().info("TaskAIGoTo: Constructor");
		this.test = test;
		this.goToSpeed = goToSpeed;
		this.setMutexBits(1);
    }
		
	@Override
	public boolean shouldExecute() {
		Utils.getLogger().info("TaskAIGoTo: shouldExecute");
		
        if (!(test.getDistance(-30, 59, 261)<2)) return true;
        else return false;
	}

    public void startExecuting(){
        Utils.getLogger().info("TaskAIGoTo: startExecuting");
        
        test.getNavigator().tryMoveToXYZ(-30, 59, 261, goToSpeed);
        super.startExecuting();
	}
}
