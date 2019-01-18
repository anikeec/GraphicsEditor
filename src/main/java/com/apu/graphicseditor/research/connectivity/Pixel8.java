/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor.research.connectivity;

import lombok.Getter;

/**
 *
 * @author apu
 */
public class Pixel8 extends Pixel {
    
    public static int PIXELS_AMOUNT = 9;
    public static int BASE_PIXEL = 8;

    @Getter    
    Pixel[] neighbor = new Pixel[PIXELS_AMOUNT];    

    public Pixel8(Pixel[] neighbor, int x, int y, int color) {
        super(x, y, color);
        this.neighbor = neighbor;        
    }

    public Pixel8(int x, int y, int color) {
        super(x, y, color);
    }

    public Pixel8() {
        this(0, 0, 0);
    }
    
}
