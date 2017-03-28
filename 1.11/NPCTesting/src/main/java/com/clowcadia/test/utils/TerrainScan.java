package com.clowcadia.test.utils;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class TerrainScan {
    private World world;
    
    public TerrainScan(World world){
        this.world = world;
    }
    
    public List<BlockPos> processTerrain(BlockPos pos, List<BlockPos> list){
        BlockPos posChanged = pos;
        for (int n = 1; n < 7; n++){
            posChanged = posChanged.north(n);
            record(posChanged,list);
        
            for (int w = 0; w < 7; w++){
                posChanged = posChanged.west(w);
                record(posChanged,list);
            }
        
            posChanged = pos.north(n);
            for (int e = 1; e < 7; e++){
                posChanged = posChanged.east(e);
                record(posChanged,list);
            }
        }
    
        for (int s = 1; s < 7; s++){
            posChanged = posChanged.south(s);
            record(posChanged,list);
        
            for (int w = 0; w < 7; w++){
                posChanged = posChanged.west(w);
                record(posChanged,list);
            }
        
            posChanged = pos.south(s);
            for (int e = 1; e < 7; e++){
                posChanged = posChanged.east(e);
                record(posChanged,list);
            }
        }
        return list;
    }
    
    private BlockPos getFloor(BlockPos pos){
        BlockPos floor = pos;
        int up = 0;
        int down = 0;
        
        if(isGround(pos)){
            while (isGround(pos.up(up))) {
                up++;
            }
            floor = pos.up(up-1);
        }else if(isAir(pos)){
            while(isAir(pos.down(down))){
                down++;
            }
            floor = pos.down(down);
        }
        return floor;
    }
    
    public List<BlockPos> record(BlockPos pos, List<BlockPos> list){
        if(isRecordable(getFloor(pos),list))list.add(getFloor(pos));
        return list;
    }
    
    private boolean isRecordable( BlockPos pos, List<BlockPos> list){
        boolean isRecordable = false;
        if(!list.contains(pos)) {
            isRecordable = true;
        }
        return isRecordable;
    }
    
    private IBlockState getIBS(BlockPos pos){ return  this.world.getBlockState(pos); }
    
    private Block getBlock(BlockPos pos){ return getIBS(pos).getBlock(); }
    
    private boolean isGround(BlockPos pos){
        return getBlock(pos) == Blocks.DIRT ||
                getBlock(pos) == Blocks.GRASS ||
                getBlock(pos) == Blocks.STONE ||
                getBlock(pos) == Blocks.COBBLESTONE;
    }
    
    private boolean isAir(BlockPos pos){
        return getBlock(pos).isAir(getIBS(pos),this.world,pos) ||
                getBlock(pos) == Blocks.LOG ||
                getBlock(pos) == Blocks.LOG2 ||
                getBlock(pos) == Blocks.LEAVES ||
                getBlock(pos) == Blocks.LEAVES2 ||
                getBlock(pos) == Blocks.TALLGRASS ||
                getBlock(pos) == Blocks.RED_FLOWER ||
                getBlock(pos) == Blocks.YELLOW_FLOWER ||
                getBlock(pos) == Blocks.DOUBLE_PLANT ||
                getBlock(pos) == Blocks.SAPLING;
    }
}
