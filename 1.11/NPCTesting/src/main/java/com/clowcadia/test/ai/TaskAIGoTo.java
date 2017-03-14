package com.clowcadia.test.ai;

import com.clowcadia.test.entities.Test;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.World;

public class TaskAIGoTo extends EntityAIBase{
	
	private final Test test;
	World world;
    private final double goToSpeed;
    private final PathNavigate testPathFinder;
    
    public TaskAIGoTo(Test test, double goToSpeed) {
		this.test = test;
		this.world = test.world;
		this.goToSpeed = goToSpeed;
		this.testPathFinder = test.getNavigator();
		this.setMutexBits(3);
    }
		
	@Override
	public boolean shouldExecute() {
		EntityLivingBase entitylivingbase = this.test.getOwner();
		if (entitylivingbase == null)
        {
            return false;
        }else return true;
	}
	
	public boolean continueExecuting(){
		return !this.testPathFinder.noPath()&&test.getDistance(29, 62, 265)>2;		
    }
	
	 public void updateTask(){
		 if(test.getDistance(29, 62, 265)>2){
			 this.testPathFinder.tryMoveToXYZ(29, 62, 265, goToSpeed);
		 }
	 }

}