package com.clowcadia.test.entities.living.ai;

import com.clowcadia.test.entities.living.Test;
import com.clowcadia.test.init.ItemHandler;
import com.clowcadia.test.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;

public class AIGoto extends EntityAIBase{
    
    private final Test test;
    private int timeToRecalcPath;
    private final double goToSpeed;
    
    public AIGoto(Test test, double goToSpeed) {
        Utils.getLogger().info("AIGoto: Constructor");
        this.test = test;
        this.goToSpeed = goToSpeed;
        this.setMutexBits(1);
    }
    
    @Override
    public boolean shouldExecute() {
        //Utils.getLogger().info("AIGoto: shouldExecute");
        return isItemAvailable();
    }
    
    public void startExecuting(){
        Utils.getLogger().info("AIGoto: startExecuting");
        
        this.timeToRecalcPath = 0;
    }
    
    public boolean continueExecuting() {
        //Utils.getLogger().info("AIGoto: continueExecuting");
        return isItemAvailable();
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
            test.getNavigator().tryMoveToXYZ(getPos().getX(), getPos().getY(), getPos().getZ(), goToSpeed);
            
            if (test.isCollided){
                if(getBlock().isWood(test.world, getPos())){
                    test.world.setBlockToAir(getPos());
                    
                    for(int d=1; getBlock(getPos().down(d)).isWood(test.world, getPos().down(d)); d++){
                        test.world.setBlockToAir(getPos().down(d));
                    }
    
                    for(int u=1; getBlock(getPos().up(u)).isWood(test.world, getPos().up(u))|
                            getBlock(getPos().up(u)).isLeaves(getIBS(getPos().up(u)),test.world, getPos().up(u)); u++){
                        test.world.setBlockToAir(getPos().up(u));
                        
                        for(int n=1; getBlock(getPos().up(u).north(n)).isWood(test.world, getPos().up(u).north(n)) |
                                getBlock(getPos().up(u).north(n)).isLeaves(getIBS(getPos().up(u).north(n)),test.world, getPos().up(u).north(n)); n++){
                            test.world.setBlockToAir(getPos().up(u).north(n));
    
                            for(int w=1; getBlock(getPos().north(n).east(w)).isWood(test.world, getPos().north(n).east(w)) |
                                getBlock(getPos().up(u).north(n).west(w)).isLeaves(getIBS(getPos().up(u).north(n).west(w)),test.world, getPos().up(u).north(n).west(w)); w++){
                                test.world.setBlockToAir(getPos().up(u).north(n).west(w));
                            }
    
                            for(int e=1; getBlock(getPos().north(n).east(e)).isWood(test.world, getPos().north(n).east(e)) |
                                getBlock(getPos().up(u).north(n).east(e)).isLeaves(getIBS(getPos().up(u).north(n).east(e)),test.world, getPos().up(u).north(n).east(e)); e++){
                                test.world.setBlockToAir(getPos().up(u).north(n).east(e));
                            }
                        }
                        
                        for(int s=1; getBlock(getPos().up(u).south(s)).isWood(test.world, getPos().up(u).south(s)) |
                                getBlock(getPos().up(u).south(s)).isLeaves(getIBS(getPos().up(u).south(s)),test.world, getPos().up(u).south(s)); s++){
                            test.world.setBlockToAir(getPos().up(u).south(s));
    
                            for(int w=1; getBlock(getPos().south(s).east(w)).isWood(test.world, getPos().south(s).east(w)) |
                                    getBlock(getPos().up(u).south(s).west(w)).isLeaves(getIBS(getPos().up(u).south(s).west(w)),test.world, getPos().up(u).south(s).west(w)); w++){
                                test.world.setBlockToAir(getPos().up(u).south(s).west(w));
                            }
    
                            for(int e=1; getBlock(getPos().south(s).east(e)).isWood(test.world, getPos().south(s).east(e)) |
                                    getBlock(getPos().up(u).south(s).east(e)).isLeaves(getIBS(getPos().up(u).south(s).east(e)),test.world, getPos().up(u).south(s).east(e)); e++){
                                test.world.setBlockToAir(getPos().up(u).south(s).east(e));
                            }
                        }
    
                        for(int w=1; getBlock(getPos().up(u).west(w)).isWood(test.world, getPos().up(u).west(w)) |
                                getBlock(getPos().up(u).west(w)).isLeaves(getIBS(getPos().up(u).west(w)),test.world, getPos().up(u).west(w)); w++) {
                            test.world.setBlockToAir(getPos().up(u).west(w));
                        }
    
                        for(int e=1; getBlock(getPos().up(u).east(e)).isWood(test.world, getPos().up(u).east(e)) |
                                getBlock(getPos().up(u).east(e)).isLeaves(getIBS(getPos().up(u).east(e)),test.world, getPos().up(u).east(e)); e++) {
                            test.world.setBlockToAir(getPos().up(u).east(e));
                        }
                    }
                    
                }
                
            }
        }
    }
    private boolean isItemAvailable(){
        ItemStack stack = this.test.handler.getStackInSlot(0);
        if (this.test.world != null && !this.test.world.isRemote && stack.getItem() == ItemHandler.target){
            Utils.getLogger().info(" SET POSITION "+getPos());
            return ! (test.getDistanceSq(getPos()) < 1);
        }else return false;
    }
    private BlockPos getPos(){
        ItemStack stack = this.test.handler.getStackInSlot(0);
        NBTTagCompound nbt = stack.getTagCompound();
        return NBTUtil.getPosFromTag(nbt);
    }
    
    private Block getBlock(){
        IBlockState ibs = test.world.getBlockState(getPos());
        return ibs.getBlock();
    }
    
    private Block getBlock(BlockPos pos){
        IBlockState ibs = test.world.getBlockState(pos);
        return ibs.getBlock();
    }
    
    private IBlockState getIBS(BlockPos pos){
        return  test.world.getBlockState(pos);
    }
}
