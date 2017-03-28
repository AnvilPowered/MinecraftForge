package com.clowcadia.test.entities.living.ai;

import com.clowcadia.test.entities.living.Test;
import com.clowcadia.test.init.ItemHandler;
import com.clowcadia.test.utils.LumberJack;
import com.clowcadia.test.utils.TerrainScan;
import com.clowcadia.test.utils.TreeArea;
import com.clowcadia.test.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class AIGoto extends EntityAIBase{
    
    private final Test test;
    private final World world;
    private int timeToRecalcPath;
    private final double goToSpeed;
    
    public AIGoto(Test test, double goToSpeed) {
        Utils.getLogger().info("AIGoto: Constructor");
        this.test = test;
        this.world = test.world;
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
            this.test.getNavigator().tryMoveToXYZ(getPos().getX(), getPos().getY(), getPos().getZ(), goToSpeed);
            
            if (this.test.isCollided){
                BlockPos cPos = getRootPos().down(1);
                
                List<BlockPos> terrain = new ArrayList<BlockPos>();
                new TerrainScan(world, cPos, terrain);
                
                List<BlockPos> treeRoots = new ArrayList<BlockPos>();
                for (BlockPos pos: terrain)
                    if(getBlock(pos.up()) == Blocks.LOG || getBlock(pos.up()) == Blocks.LOG2) treeRoots.add(pos.up());
                TreeArea trArea;
                if(treeRoots!=null){
                    trArea = new TreeArea(treeRoots);
                    new LumberJack(world, getRootPos(),
                            trArea.zNBorder, trArea.xNBorder, trArea.zPBorder, trArea.xPBorder);
                }
                
            }
        }
    }
    
    private boolean isItemAvailable() {
        ItemStack stack = this.test.handler.getStackInSlot(0);
        return world != null && ! world.isRemote &&
                stack.getItem() == ItemHandler.target && ! (test.getDistanceSq(getPos()) < 1);
    }
    private BlockPos getPos(){
        ItemStack stack = this.test.handler.getStackInSlot(0);
        NBTTagCompound nbt = stack.getTagCompound();
        return NBTUtil.getPosFromTag(nbt);
    }
    
    private BlockPos getRootPos(){
        BlockPos pos = getPos();
        for(int d = 0; getBlock(getPos().down(d)).isWood(world, getPos().down(d)); d++){
            pos = getPos().down(d);
        }
        return pos;
    }
    
    private Block getBlock(BlockPos pos){
        return getIBS(pos).getBlock();
    }
    
    private IBlockState getIBS(BlockPos pos){
        return  world.getBlockState(pos);
    }
    
     
}
