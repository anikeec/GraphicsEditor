/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor.research.connectivity;

/**
 *
 * @author apu
 */
public class ErodeImageFilter implements ImageFilter {

    @Override
    public Pixel handlePixel8(Pixel8 pixel) {
        Pixel[] neighbors = pixel.getNeighbor();
        for(int i=0; i<neighbors.length - 1; i++) {
            if(neighbors[i].getBwvalue() == Pixel.BLACK) {
                pixel.setColor(Pixel.COLOR_BLACK);
                return pixel;
            }
        }
        return pixel;
    }
    
}
