/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor.research.connectivity;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author apu
 */
public class Pixel {
    
    public static int WHITE = 1;
    public static int BLACK = 0;
    public static int COLOR_WHITE = 0xFFFFFFFF;
    public static int COLOR_BLACK = 0xFF000000;
    
    @Getter
    private int red;
    
    @Getter
    private int green;
    
    @Getter
    private int blue;
    
    @Getter @Setter
    private int color;
    
    @Getter
    private int bwvalue;
    
    @Getter
    private int x;
    
    @Getter
    private int y;
    
    @Getter @Setter
    private int label;

    public Pixel(int x, int y, int color) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.red = (color & 0x00ff0000) >> 16;
        this.green = (color & 0x0000ff00) >> 8;
        this.blue = color & 0x000000ff;
        bwvalue = (red + green + blue)/255;
        if(bwvalue > 1)
            bwvalue = WHITE;    //write
        else
            bwvalue = BLACK;    //black
    }
    
//    public 

    public Pixel() {
    }
    
}
