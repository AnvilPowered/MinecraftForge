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
        //BlockPos[] logScan;
        //logScan[1] = getPos();
        
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = 10;
            this.test.getNavigator().tryMoveToXYZ(getPos().getX(), getPos().getY(), getPos().getZ(), goToSpeed);
            
            if (this.test.isCollided){
                if(getBlock().isWood(this.test.world, getPos())){
                    this.test.world.setBlockToAir(getPos());
                    this.test.stomach = this.test.stomach - 1;
                    
                    for(int d=1; getBlock(getPos().down(d)).isWood(this.test.world, getPos().down(d)); d++){
                        this.test.world.setBlockToAir(getPos().down(d));
                        this.test.stomach = this.test.stomach - 1;
                    }
    
                    for(int u=1; getBlock(getPos().up(u)).isWood(this.test.world, getPos().up(u))||
                            getBlock(getPos().up(u)).isLeaves(getIBS(getPos().up(u)),this.test.world, getPos().up(u)); u++){
                        this.test.world.setBlockToAir(getPos().up(u));
                        this.test.stomach = this.test.stomach - 1;
                        
                        for(int n=1; getBlock(getPos().up(u).north(n)).isWood(this.test.world, getPos().up(u).north(n)) ||
                                getBlock(getPos().up(u).north(n)).isLeaves(getIBS(getPos().up(u).north(n)),this.test.world, getPos().up(u).north(n)); n++){
                            this.test.world.setBlockToAir(getPos().up(u).north(n));
                            this.test.stomach = this.test.stomach - 1;
    
                            for(int w=1; getBlock(getPos().up(u).north(n).east(w)).isWood(this.test.world, getPos().up(u).north(n).east(w)) ||
                                getBlock(getPos().up(u).north(n).west(w)).isLeaves(getIBS(getPos().up(u).north(n).west(w)),this.test.world, getPos().up(u).north(n).west(w)); w++){
                                this.test.world.setBlockToAir(getPos().up(u).north(n).west(w));
                                this.test.stomach = this.test.stomach - 1;
                            }
    
                            for(int e=1; getBlock(getPos().up(u).north(n).east(e)).isWood(this.test.world, getPos().up(u).north(n).east(e)) ||
                                getBlock(getPos().up(u).north(n).east(e)).isLeaves(getIBS(getPos().up(u).north(n).east(e)),this.test.world, getPos().up(u).north(n).east(e)); e++){
                                this.test.world.setBlockToAir(getPos().up(u).north(n).east(e));
                                this.test.stomach = this.test.stomach - 1;
                            }
                        }
                        
                        for(int s=1; getBlock(getPos().up(u).south(s)).isWood(this.test.world, getPos().up(u).south(s)) ||
                                getBlock(getPos().up(u).south(s)).isLeaves(getIBS(getPos().up(u).south(s)),this.test.world, getPos().up(u).south(s)); s++){
                            this.test.world.setBlockToAir(getPos().up(u).south(s));
                            this.test.stomach = this.test.stomach - 1;
    
                            for(int w=1; getBlock(getPos().up(u).south(s).west(w)).isWood(this.test.world, getPos().up(u).south(s).west(w)) ||
                                    getBlock(getPos().up(u).south(s).west(w)).isLeaves(getIBS(getPos().up(u).south(s).west(w)),this.test.world, getPos().up(u).south(s).west(w)); w++){
                                this.test.world.setBlockToAir(getPos().up(u).south(s).west(w));
                                this.test.stomach = this.test.stomach - 1;
                            }
    
                            for(int e=1; getBlock(getPos().up(u).south(s).east(e)).isWood(this.test.world, getPos().up(u).south(s).east(e)) ||
                                    getBlock(getPos().up(u).south(s).east(e)).isLeaves(getIBS(getPos().up(u).south(s).east(e)),this.test.world, getPos().up(u).south(s).east(e)); e++){
                                this.test.world.setBlockToAir(getPos().up(u).south(s).east(e));
                                this.test.stomach = this.test.stomach - 1;
                            }
                        }
    
                        for(int w=1; getBlock(getPos().up(u).west(w)).isWood(this.test.world, getPos().up(u).west(w)) ||
                                getBlock(getPos().up(u).west(w)).isLeaves(getIBS(getPos().up(u).west(w)),this.test.world, getPos().up(u).west(w)); w++) {
                            this.test.world.setBlockToAir(getPos().up(u).west(w));
                            this.test.stomach = this.test.stomach - 1;
    
                            for(int s=1; getBlock(getPos().up(u).west(w).south(s)).isWood(this.test.world, getPos().up(u).west(w).south(s)) ||
                                    getBlock(getPos().up(u).west(w).south(s)).isLeaves(getIBS(getPos().up(u).west(w).south(s)),this.test.world, getPos().up(u).west(w).south(s)); s++) {
                                this.test.world.setBlockToAir(getPos().up(u).west(w).south(s));
                                this.test.stomach = this.test.stomach - 1;
                            }
    
                            for(int n=1; getBlock(getPos().up(u).west(w).north(n)).isWood(this.test.world, getPos().up(u).west(w).north(n)) ||
                                    getBlock(getPos().up(u).west(w).north(n)).isLeaves(getIBS(getPos().up(u).west(w).north(n)),this.test.world, getPos().up(u).west(w).north(n)); n++) {
                                this.test.world.setBlockToAir(getPos().up(u).west(w).north(n));
                                this.test.stomach = this.test.stomach - 1;
                            }
                        }
    
                        for(int e=1; getBlock(getPos().up(u).east(e)).isWood(this.test.world, getPos().up(u).east(e)) ||
                                getBlock(getPos().up(u).east(e)).isLeaves(getIBS(getPos().up(u).east(e)),this.test.world, getPos().up(u).east(e)); e++) {
                            this.test.world.setBlockToAir(getPos().up(u).east(e));
                            this.test.stomach = this.test.stomach - 1;
    
                            for(int s=1; getBlock(getPos().up(u).east(e).south(s)).isWood(this.test.world, getPos().up(u).east(e).south(s)) ||
                                    getBlock(getPos().up(u).east(e).south(s)).isLeaves(getIBS(getPos().up(u).east(e).south(s)),this.test.world, getPos().up(u).east(e).south(s)); s++) {
                                this.test.world.setBlockToAir(getPos().up(u).east(e).south(s));
                                this.test.stomach = this.test.stomach - 1;
                            }
    
                            for(int n=1; getBlock(getPos().up(u).east(e).north(n)).isWood(this.test.world, getPos().up(u).east(e).north(n)) ||
                                    getBlock(getPos().up(u).east(e).north(n)).isLeaves(getIBS(getPos().up(u).east(e).north(n)),this.test.world, getPos().up(u).east(e).north(n)); n++) {
                                this.test.world.setBlockToAir(getPos().up(u).east(e).north(n));
                                this.test.stomach = this.test.stomach - 1;
                            }
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
        //BlockLeaves blah = (BlockLeaves)ibs.getBlock();
        //ibs.getActualState(test.world,pos).getValue()
        return ibs.getBlock();
    }
    
    private IBlockState getIBS(BlockPos pos){
        return  test.world.getBlockState(pos);
    }
}
