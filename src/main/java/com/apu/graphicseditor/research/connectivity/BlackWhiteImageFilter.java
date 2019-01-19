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
public class BlackWhiteImageFilter implements ImageFilter {

    @Override
    public Pixel handlePixel8(Pixel8 pixel) {
        Pixel8 pix = pixel;
        if(pix.getPixel().getBwvalue() == Pixel.WHITE) {
            pix.getPixel().setColor(Pixel.COLOR_WHITE);
        } else {
            pix.getPixel().setColor(Pixel.COLOR_BLACK);
        }
        return pix.getPixel();
    }
    
}
