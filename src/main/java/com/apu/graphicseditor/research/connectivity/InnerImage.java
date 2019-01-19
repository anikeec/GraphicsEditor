/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor.research.connectivity;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import lombok.Getter;

/**
 *
 * @author apu
 */
public class InnerImage {
    
    @Getter
    private Pixel[][] pixels;
    
    @Getter
    private int width;
    
    @Getter
    private int height;
    
    private Image image;

    public InnerImage(Image image) {
        this.image = image;
        this.loadImage(image);
    }
    
    private void loadImage(Image image) {
        this.width = new Double(image.getWidth()).intValue();
        this.height = new Double(image.getHeight()).intValue();
        this.pixels = new Pixel[width][height];
        
        PixelReader reader = image.getPixelReader();
        Pixel pixel;
        for(int y=0; y<height; y++) {
            for(int x=0; x<width; x++) {
                pixel = new Pixel(x, y, reader.getArgb(x, y));
                pixels[x][y] = pixel;
            }
        }
    }
    
    
    
}
