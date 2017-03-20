package com.clowcadia.test.entities.living.ai;

import com.clowcadia.test.entities.living.Test;
import com.clowcadia.test.init.ItemHandler;
import com.clowcadia.test.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

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
            this.test.getNavigator().tryMoveToXYZ(getPos().getX(), getPos().getY(), getPos().getZ(), goToSpeed);
            
            if (this.test.isCollided){
                List<BlockPos> posList = new ArrayList<BlockPos>();
                BlockPos cPos;
                Block cBlock;
                IBlockState cIBS;
                cPos = getRootPos();
                cBlock = getBlock(cPos);
                
                /*for(int t = 0; cBlock.isWood(this.test.world, cPos); t++){
                    cPos = getRootPos().up(t);
                    cBlock = getBlock(cPos);
                    this.test.world.setBlockToAir(cPos);
                }*/
                    
                for(int u = 0; u < 15; u++){
                    cPos = getRootPos().up(u);
                    cBlock = getBlock(cPos);
                    cIBS = getIBS(cPos);
                    if(isRecordable(u,cBlock,cPos,cIBS,posList)) {
                        posList.add(cPos);
                    }
                    
                    for(int n = 0; n < 8; n++){
                        cPos = getRootPos().up(u).north(n);
                        cBlock = getBlock(cPos);
                        cIBS = getIBS(cPos);
                        if(isRecordable(u,cBlock,cPos,cIBS,posList)) {
                            posList.add(cPos);
                        }
                        
                        for(int e = 0; e < 8; e++){
                            cPos = getRootPos().up(u).north(n).east(e);
                            cBlock = getBlock(cPos);
                            cIBS = getIBS(cPos);
                            if(isRecordable(u,cBlock,cPos,cIBS,posList)) {
                                posList.add(cPos);
                            }
                        }

                        for(int w = 0; w < 8; w++){
                            cPos = getRootPos().up(u).north(n).west(w);
                            cBlock = getBlock(cPos);
                            cIBS = getIBS(cPos);
                            if(isRecordable(u,cBlock,cPos,cIBS,posList)) {
                                posList.add(cPos);
                            }
                        }
                    }

                    for(int s = 0; s < 8; s++){
                        cPos = getRootPos().up(u).south(s);
                        cBlock = getBlock(cPos);
                        cIBS = getIBS(cPos);
                        if(isRecordable(u,cBlock,cPos,cIBS,posList)) {
                            posList.add(cPos);
                        }
    
                        for(int e = 0; e < 8; e++){
                            cPos = getRootPos().up(u).south(s).east(e);
                            cBlock = getBlock(cPos);
                            cIBS = getIBS(cPos);
                            if(isRecordable(u,cBlock,cPos,cIBS,posList)) {
                                posList.add(cPos);
                            }
                        }
    
                        for(int w = 0; w < 8; w++){
                            cPos = getRootPos().up(u).south(s).west(w);
                            cBlock = getBlock(cPos);
                            cIBS = getIBS(cPos);
                            if(isRecordable(u,cBlock,cPos,cIBS,posList)) {
                                posList.add(cPos);
                            }
                        }
                    }
                }
                /*for (BlockPos pos: posList) {
                    if(getBlock(pos).isWood(this.test.world,pos)) this.test.world.setBlockToAir(pos);
                }*/
                for (BlockPos pos: posList) {
                    if(getBlock(pos).isLeaves(getIBS(pos),this.test.world,pos) && getIBS(pos).getValue(BlockLeaves.CHECK_DECAY).booleanValue()){
                        //this.test.world.setBlockToAir(pos);
                        Utils.getLogger().info(" SET POSITION " + pos + " " + getBlock(pos)+" "+getIBS(pos).getValue(BlockLeaves.CHECK_DECAY));
                    }
                }
            }
        }
    }
    
    private boolean isItemAvailable() {
        ItemStack stack = this.test.handler.getStackInSlot(0);
        return this.test.world != null && ! this.test.world.isRemote &&
                stack.getItem() == ItemHandler.target && ! (test.getDistanceSq(getPos()) < 1);
    }
    private BlockPos getPos(){
        ItemStack stack = this.test.handler.getStackInSlot(0);
        NBTTagCompound nbt = stack.getTagCompound();
        return NBTUtil.getPosFromTag(nbt);
    }
    
    private BlockPos getRootPos(){
        BlockPos pos = new BlockPos(getPos());
        for(int d = 0; getBlock(getPos().down(d)).isWood(this.test.world, getPos().down(d)); d++){
            pos = getPos().down(d);
        }
        return pos;
    }
    
    private Block getBlock(BlockPos pos){
        IBlockState ibs = test.world.getBlockState(pos);
        return ibs.getBlock();
    }
    
    private IBlockState getIBS(BlockPos pos){
        return  test.world.getBlockState(pos);
    }
    
    private boolean isRecordable(int up, Block block, BlockPos pos, IBlockState ibs, List<BlockPos> list){
        boolean isRecordable = false;
        if((((up > 1 && block.isWood(this.test.world, pos)) && !getBlock(pos.down(1)).isWood(this.test.world, pos)) |
                block.isLeaves(ibs,this.test.world,pos)) &
                blockListCheck(0, list, pos)) {
            isRecordable = true;
        }
        return isRecordable;
    }
    
    private boolean blockListCheck(int offset, List<BlockPos> list, BlockPos pos){
        boolean posAdd = false;
            if(!list.contains(pos.down(offset))) {
                posAdd = true;
            }
            if(!posAdd & !list.contains(pos.up(offset))) {
                posAdd = true;
            }
            if(!posAdd & !list.contains(pos.north(offset))) {
                posAdd = true;
            }
            if(!posAdd & !list.contains(pos.south(offset))) {
                posAdd = true;
            }
            if(!posAdd & !list.contains(pos.east(offset))) {
                posAdd = true;
            }
            if(!posAdd & !list.contains(pos.west(offset))) {
                posAdd = true;
            }
        return posAdd;
    }
}
