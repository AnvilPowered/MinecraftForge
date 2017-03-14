package com.clowcadia.test.npc;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class BasicAITasks extends EntityAIBase {
	
	private Basic basic;
	private EntityLivingBase theOwner;
	
	public BasicAITasks(Basic basicEntity) {
		this.basic = basicEntity;
		setMutexBits(1);
		System.out.println("EntityAIBase constructor()");
	}

	@Override
	public boolean shouldExecute() {
		if(basic.getStomach()==800) return true;
		else return false;
	}
	
	@Override
	public void startExecuting() {
	}
	
	@Override
	public boolean continueExecuting() {
		boolean continueExectuing = basic.getStomach()==800;
		return true;
	}

}
