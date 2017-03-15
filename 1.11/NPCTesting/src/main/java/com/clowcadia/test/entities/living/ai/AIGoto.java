package com.clowcadia.test.entities.living.ai;

import com.clowcadia.test.entities.living.Test;
import com.clowcadia.test.utils.Utils;
import net.minecraft.entity.ai.EntityAIBase;

public class AIGoto extends EntityAIBase{
	
	private final Test test;
    private final double goToSpeed;
    
    public AIGoto(Test test, double goToSpeed) {
    	Utils.getLogger().info("AIGoto: Constructor");
		this.test = test;
		this.goToSpeed = goToSpeed;
		this.setMutexBits(1);
    }
		
	@Override
	public boolean shouldExecute() {
		Utils.getLogger().info("AIGoto: shouldExecute");
		
        if (!(test.getDistance(-30, 59, 261)<2)) return true;
        else return false;
	}

    public void startExecuting(){
        Utils.getLogger().info("AIGoto: startExecuting");
        
        test.getNavigator().tryMoveToXYZ(-30, 59, 261, goToSpeed);
        super.startExecuting();
	}
}
