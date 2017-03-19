package com.clowcadia.test.entities.living.ai;

import com.clowcadia.test.entities.living.Test;
import com.clowcadia.test.init.ItemHandler;
import com.clowcadia.test.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class AIGoto extends EntityAIBase{
    
    private final Test test;
    private int timeToRecalcPath;
    private final double goToSpeed;
    private BlockPos cPos;
    private Block cBlock;
    
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
        if (this.test.world != null && !this.test.world.isRemote && stack.getItem() == ItemHandler.target){
            NBTTagCompound nbt = stack.getTagCompound();
            this.cPos = new BlockPos(nbt.getInteger("targetX"),nbt.getInteger("targetY"),nbt.getInteger("targetZ"));
            return ! (test.getDistanceSq(cPos) < 1);
            //Utils.getLogger().info(this.targetSet);
        }else return false;
        //return targetSet;
    }
    
    public void startExecuting(){
        Utils.getLogger().info("AIGoto: startExecuting");
        
        this.timeToRecalcPath = 0;
    }
    
    public boolean continueExecuting() {
        //Utils.getLogger().info("AIGoto: continueExecuting");
        return ! (test.getDistanceSq(this.cPos) < 1);
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
            test.getNavigator().tryMoveToXYZ(this.cPos.getX(), this.cPos.getY(), this.cPos.getZ(), goToSpeed);
            
            if (test.isCollided){
                cBlock = getBlock(this.cPos.getX(), this.cPos.getY(), this.cPos.getZ());
                if(cBlock== Blocks.LOG | cBlock == Blocks.LOG2){
                    test.world.setBlockToAir(cPos);
                    
                    for(int i=1; getBlock(this.cPos.getX(), this.cPos.getY()-i, this.cPos.getZ()) == Blocks.LOG | getBlock(this.cPos.getX(), this.cPos.getY()-i, this.cPos.getZ()) == Blocks.LOG2; i--){
                        cPos = new BlockPos(this.cPos.getX(), this.cPos.getY()-i, this.cPos.getZ());
                        test.world.setBlockToAir(cPos);
                    }
                    
                    for(int i = 1; getBlock(this.cPos.getX(), this.cPos.getY()+i, this.cPos.getZ()) == Blocks.LOG | getBlock(this.cPos.getX(), this.cPos.getY()+i, this.cPos.getZ()) == Blocks.LOG2; i++){
                        cPos = new BlockPos(this.cPos.getX(), this.cPos.getY()+i, this.cPos.getZ());
                        test.world.setBlockToAir(cPos);
                    }
                    
                }
                
            }
        }
    }
    
    private Block getBlock(int x, int y, int z){
        BlockPos pos = new BlockPos(x, y, z);
        IBlockState ibs = test.world.getBlockState(pos);
        return ibs.getBlock();
    }
}
