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
                Ter ter = new Ter(world);
                List<BlockPos> terList = new ArrayList<BlockPos>();
                List<BlockPos> treeList = new ArrayList<BlockPos>();
                List<BlockPos> posList = new ArrayList<BlockPos>();
                BlockPos cPos;
                
                cPos = getRootPos().down(1);
                ter.record(cPos,terList);
                for (int n = 1; n < 7; n++){
                    cPos = cPos.north(n);
                    ter.record(cPos,terList);
                    
                    for (int w = 0; w < 7; w++){
                        cPos = cPos.west(w);
                        ter.record(cPos,terList);
                    }
                    
                    cPos = getRootPos().down(1).north(n);
                    for (int e = 1; e < 7; e++){
                        cPos = cPos.east(e);
                        ter.record(cPos,terList);
                    }
                }
                
                for (int s = 1; s < 7; s++){
                    cPos = cPos.south(s);
                    ter.record(cPos,terList);
                    
                    for (int w = 0; w < 7; w++){
                        cPos = cPos.west(w);
                        ter.record(cPos,terList);
                    }
    
                    cPos = getRootPos().down(1).south(s);
                    for (int e = 1; e < 7; e++){
                        cPos = cPos.east(e);
                        ter.record(cPos,terList);
                    }
                }
                for (BlockPos pos: terList) {
                    Utils.getLogger().info(" BLOCK " + getBlock(pos) + " POS " + pos);
                }
                Utils.getLogger().info(" REPEATING ");
                
                /*for(int t = 0; cBlock.isWood(world, cPos); t++){
                    cPos = getRootPos().up(t);
                    cBlock = getBlock(cPos);
                    posList.add(cPos);
                    world.setBlockToAir(cPos);
                }*/
                    
                /*for(int u = 0; isTree(getRootPos().up(u)); u++){
                    cPos = getRootPos().up(u);
                    if(isRecordable(u, cPos, posList)) {
                        posList.add(cPos);
                    }
                    
                    for(int n = 0; isTree(getRootPos().up(u).north(n)); n++){
                        cPos = getRootPos().up(u).north(n);
                        if(isRecordable(u, cPos, posList)) {
                            posList.add(cPos);
                        }
                        
                        for(int e = 0; isTree(getRootPos().up(u).north(n).east(e)); e++){
                            cPos = getRootPos().up(u).north(n).east(e);
                            if(isRecordable(u, cPos, posList)) {
                                posList.add(cPos);
                            }
                        }

                        for(int w = 0; isTree(getRootPos().up(u).north(n).west(w)); w++){
                            cPos = getRootPos().up(u).north(n).west(w);
                            if(isRecordable(u, cPos, posList)) {
                                posList.add(cPos);
                            }
                        }
                    }

                    for(int s = 0; isTree(getRootPos().up(u).south(s)); s++){
                        cPos = getRootPos().up(u).south(s);
                        if(isRecordable(u, cPos, posList)) {
                            posList.add(cPos);
                        }
    
                        for(int e = 0; isTree(getRootPos().up(u).south(s).east(e)); e++){
                            cPos = getRootPos().up(u).south(s).east(e);
                            if(isRecordable(u, cPos, posList)) {
                                posList.add(cPos);
                            }
                        }
    
                        for(int w = 0; isTree(getRootPos().up(u).south(s).west(w)); w++){
                            cPos = getRootPos().up(u).south(s).west(w);
                            if(isRecordable(u, cPos, posList)) {
                                posList.add(cPos);
                            }
                        }
                    }
                }*/
                /*for (BlockPos pos: posList) {
                    if(getBlock(pos).isWood(world,pos)) world.setBlockToAir(pos);
                }
                for (BlockPos pos: posList) {
                    if(getBlock(pos).isLeaves(getIBS(pos),world,pos)){
                        world.setBlockToAir(pos);
                        //Utils.getLogger().info(" SET POSITION " + pos + " " + getBlock(pos)+" "+getIBS(pos).getValue(BlockLeaves.CHECK_DECAY));
                    }
                }*/
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
        BlockPos pos = new BlockPos(getPos());
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
    
    
    
    private boolean isTree(BlockPos pos){
        return getBlock(pos).isWood(world,pos) | getBlock(pos).isLeaves(getIBS(pos),world,pos);
    }
    
    
    
    private boolean isRecordable(int up, BlockPos pos, List<BlockPos> list){
        boolean isRecordable = false;
        if((up > 1 && !getBlock(pos.down(1)).isWood(world, pos)) | blockListCheck(0, list, pos)) {
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
