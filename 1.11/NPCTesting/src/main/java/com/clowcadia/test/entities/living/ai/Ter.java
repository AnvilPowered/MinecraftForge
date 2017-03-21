package com.clowcadia.test.entities.living.ai;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

class Ter {
    private World world;
    
    Ter(World world){
        this.world = world;
    }
    
    private IBlockState getIBS(BlockPos pos){
        return  this.world.getBlockState(pos);
    }
    
    private Block getBlock(BlockPos pos){
        return getIBS(pos).getBlock();
    }
    
    private boolean isGround(BlockPos pos){
        return (getBlock(pos) == Blocks.DIRT | getBlock(pos) == Blocks.GRASS) | getBlock(pos) == Blocks.STONE;
    }
    
    private boolean isAir(BlockPos pos){
        return getBlock(pos).isAir(getIBS(pos),this.world,pos) |
                ( getBlock(pos) == Blocks.LOG | getBlock(pos) == Blocks.LOG2) |
                (getBlock(pos) == Blocks.LEAVES | getBlock(pos) == Blocks.LEAVES2);
    }
    
    private BlockPos getFloor(BlockPos pos){
        BlockPos floor = pos;
        if(isGround(pos)){
            for(int u = 1; isGround(pos.up(u)); u++){
                if(isAir(pos.up(u))) floor = pos.up(u-1);
            }
        }else if(isAir(pos)){
            for(int d = 1; isAir(pos.down(d)); d++){
                if(isGround(pos.down(d))) floor = pos.down(d-1);
            }
        }
        return floor;
    }
    
    List<BlockPos> record(BlockPos pos, List<BlockPos> list){
        if(isRecordable(getFloor(pos),list))list.add(pos);
        return list;
    }
    
    private boolean isRecordable( BlockPos pos, List<BlockPos> list){
        boolean isRecordable = false;
        if(!list.contains(pos)) {
            isRecordable = true;
        }
        return isRecordable;
    }
    
}
