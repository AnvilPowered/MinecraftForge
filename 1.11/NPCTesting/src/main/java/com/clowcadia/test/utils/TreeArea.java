package com.clowcadia.test.utils;

import net.minecraft.util.math.BlockPos;

import java.util.List;

public class TreeArea {
    
    public int xPBorder = 0, xNBorder = 0, zPBorder = 0, zNBorder = 0;
    
    public TreeArea(List<BlockPos> list){
        int xDPC = 0, xDNC = 0, zDPC = 0, zDNC = 0;
        
        BlockPos mainTree = list.get(0);
        
        for(BlockPos pos: list){
            if(pos != mainTree){
                int xDP, xDN, zDP, zDN;
            
                if((mainTree.getX() - pos.getX())>0){
                    xDP = mainTree.getX() - pos.getX();
                    if(xDPC == 0 || xDP < xDPC) {
                        xDPC = xDP;
                        xPBorder = xDPC/2;
                    }
                }else{
                    xDN = (mainTree.getX() - pos.getX())*-1;
                    if(xDNC == 0 || xDN < xDNC) {
                        xDNC = xDN;
                        xNBorder = xDNC/2;
                    }
                }
                if((mainTree.getZ() - pos.getZ())>0){
                    zDP = mainTree.getZ() - pos.getZ();
                    if(zDPC == 0 || zDP < zDPC) {
                        zDPC = zDP;
                        zPBorder = zDPC/2;
                    }
                }else{
                    zDN = (mainTree.getZ() - pos.getZ())*-1;
                    if(zDNC == 0 || zDN < xDNC) {
                        zDNC = zDN;
                        zNBorder = zDNC/2;
                    }
                }
            }
        }
    }
    
}
