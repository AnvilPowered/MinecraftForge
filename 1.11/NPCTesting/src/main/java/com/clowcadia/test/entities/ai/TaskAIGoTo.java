package com.clowcadia.test.entities.ai;

import com.clowcadia.test.entities.Test;
import com.clowcadia.test.utils.Utils;
import net.minecraft.entity.EntityLivingBase;
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
/*		EntityLivingBase entitylivingbase = this.test.getOwner();
		if (entitylivingbase == null)
        {
			Utils.getLogger().info("TaskAIGoTo: shouldExecute : false");
            return false;
        }else {
        	Utils.getLogger().info("TaskAIGoTo: shouldExecute: true");
        	return true;
        }*/
        return true;
	}
	
	public boolean continueExecuting(){
		Utils.getLogger().info("TaskAIGoTo: continueExecute");
/*		boolean output =!this.test.getNavigator().noPath();
		Utils.getLogger().info("TaskAIGoTo: continueExecute: "+output+" distance: "+test.getDistance(29, 62, 265));
		return !this.test.getNavigator().noPath();*/
        return !(test.getDistance(-21, 61, 239)<2);
    }

    public void startExecuting(){
		//this.test.getNavigator().tryMoveToXYZ(-21, 61, 265, goToSpeed);
	}
    
    @Override
    public void updateTask() {
        if (test.getDistance(-21, 61, 239)<2){
            test.getNavigator().tryMoveToXYZ(-21, 61, 239, goToSpeed);
        }
    }
}
