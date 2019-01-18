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
        Pixel pix = pixel;
        if(pix.getBwvalue() == Pixel.WHITE) {
            pix.setColor(Pixel.COLOR_WHITE);
        } else {
            pix.setColor(Pixel.COLOR_BLACK);
        }
        return pix;
    }
    
}
