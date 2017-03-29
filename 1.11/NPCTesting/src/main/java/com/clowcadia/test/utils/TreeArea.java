package com.clowcadia.test.utils;

import net.minecraft.util.math.BlockPos;

import java.util.List;

public class TreeArea {
    
    public int xPBorder = 0, xNBorder = 0, zPBorder = 0, zNBorder = 0;
    public boolean xPZ = false, xNZ = false, zPZ = false, zNZ = false;
    
    public TreeArea(List<BlockPos> list){
        int xDPC = 0, xDNC = 0, zDPC = 0, zDNC = 0;
        
        BlockPos mainTree = list.get(0);
        
        for(BlockPos pos: list){
            if(pos != mainTree){
                int xDP, xDN, zDP, zDN;
            
                if((mainTree.getX() - pos.getX())>0){
                    xDN = mainTree.getX() - pos.getX();
                    if(xDNC == 0 || xDN < xDNC) {
                        xDNC = xDN;
                        xNBorder = xDNC/2;
                        if(xNBorder == 0) xNZ = true;
                        Utils.getLogger().info("xDN"+xDN+" xDNC"+xDNC+" xNBorder"+xNBorder+" xNZ"+xNZ);
                    }
                }else{
                    xDP = (mainTree.getX() - pos.getX())*-1;
                    if(xDPC == 0 || xDP < xDPC) {
                        xDPC = xDP;
                        xPBorder = xDPC/2;
                        if(xPBorder == 0) xPZ = true;
                        Utils.getLogger().info("xDP"+xDP+" xDPC"+xDPC+" xPBorder"+xPBorder+" xPZ"+xPZ);
                    }
                }
                if((mainTree.getZ() - pos.getZ())>0){
                    zDN = mainTree.getZ() - pos.getZ();
                    if(zDNC == 0 || zDN < zDNC) {
                        zDNC = zDN;
                        zNBorder = zDNC/2;
                        if(zNBorder == 0) zNZ = true;
                        Utils.getLogger().info("zDN"+zDN+" zDNC"+zDNC+" zNBorder"+zNBorder+" zNZ"+zNZ);
                    }
                }else{
                    zDP = (mainTree.getZ() - pos.getZ())*-1;
                    if(zDPC == 0 || zDP < xDPC) {
                        zDPC = zDP;
                        zPBorder = zDPC/2;
                        if(zPBorder == 0) zPZ = true;
                        Utils.getLogger().info("zDP"+zDP+" zDPC"+zDPC+" zPBorder"+zPBorder+" zPZ"+zPZ);
                    }
                }
            }
        }
    }
}
