package com.clowcadia.test.entities;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public class Test extends EntityTameable{

	public Test(World worldIn) {
		super(worldIn);
		this.tasks.addTask(0, new EntityAITempt(this, 0.5d, Items.APPLE, false));
	}
	
	@Override
	public boolean isAIDisabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
