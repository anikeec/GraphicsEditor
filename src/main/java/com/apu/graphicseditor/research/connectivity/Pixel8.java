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
public class Pixel8 {
    
    public static int PIXELS_AMOUNT = 9;
    public static int BASE_PIXEL = 8;
    
    @Getter
    private Pixel pixel;

    @Getter   
    Pixel[] neighbor = new Pixel[PIXELS_AMOUNT];

    public Pixel8(Pixel[] neighbor, Pixel pixel) {
        this.neighbor = neighbor;
        this.pixel = pixel;
    }
    
}
