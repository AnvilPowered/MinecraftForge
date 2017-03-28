package com.clowcadia.test.utils;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class LumberJack {
    
    private World world;
    
    public LumberJack(World world, BlockPos root, int sLimit, int eLimit, int nLimit, int wLimit){
        
        this.world = world;
        BlockPos pos;
        Block block;
        IBlockState ibs;
        List<BlockPos> list = new ArrayList<BlockPos>();
        int sL = 7;
        int eL = 7;
        int nL = 7;
        int wL = 7;
        if(sLimit != 0) sL = sLimit;
        Utils.getLogger().info("SL"+ sL);
        if(eLimit != 0) eL = eLimit;
        Utils.getLogger().info("EL"+ eL);
        if(nLimit != 0) nL = nLimit;
        Utils.getLogger().info("NL"+ nL);
        if(wLimit != 0) wL = wLimit;
        Utils.getLogger().info("WL"+ wL);
        
        for(int u = 15; u >= 0; u--){
            for(int s = sL; s >= 0; s--) {
                for (int e = eL; e >= 0; e--) {
                    pos = root.up(u).south(s).east(e);
                    block = getBlock(pos);
                    ibs = getIBS(pos);
                    if (isRecordable(list, pos, block, ibs)) list.add(pos);
                }
                for (int w = wL; w >= 0; w--) {
                    pos = root.up(u).south(s).east(w);
                    block = getBlock(pos);
                    ibs = getIBS(pos);
                    if (isRecordable(list, pos, block, ibs)) list.add(pos);
                }
            }
            for(int n = nL; n >= 0; n--) {
                for (int e = eL; e >= 0; e--) {
                    pos = root.up(u).north(n).east(e);
                    block = getBlock(pos);
                    ibs = getIBS(pos);
                    if (isRecordable(list, pos, block, ibs)) list.add(pos);
                }
                for (int w = wL; w >= 0; w--) {
                    pos = root.up(u).north(n).east(w);
                    block = getBlock(pos);
                    ibs = getIBS(pos);
                    if (isRecordable(list, pos, block, ibs)) list.add(pos);
                }
            }
        }
        for(BlockPos entry: list) world.setBlockToAir(entry);
            //Utils.getLogger().info("TREE BLOCKS: "+ getBlock(entry) + " " + entry);
    }
    
    private Block getBlock(BlockPos pos){
        IBlockState ibs = world.getBlockState(pos);
        return ibs.getBlock();
    }
    
    private IBlockState getIBS(BlockPos pos){
        return  world.getBlockState(pos);
    }
    
    private boolean isRecordable( List<BlockPos> list, BlockPos pos, Block block, IBlockState ibs){
        boolean isRecordable = false;
        if(!list.contains(pos) && (block.isWood(world,pos) || block.isLeaves(ibs,world,pos)))
            isRecordable = true;
        return isRecordable;
    }
    
}
