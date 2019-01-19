/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor.research.connectivity;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 *
 * @author apu
 */
public class ImageScanner {
    
    //scan full image and apply filter to every pixel
    public static Image scan(Image image, ImageFilter filter) {
        InnerImage innerImage = new InnerImage(image);
        int height = innerImage.getHeight();
        int width = innerImage.getWidth();
        
//        PixelReader reader = image.getPixelReader();
        WritableImage dest = new WritableImage(width, height);
        PixelWriter writer = dest.getPixelWriter();

        Pixel pixel;
        int pixelColor;
        for(int y=1; y<height-1; y++) {
            for(int x=1; x<width-1; x++) {
                pixel = filter.handlePixel8(getPixel8(innerImage, x, y));
                pixelColor = pixel.getColor();
                writer.setArgb(x, y, pixelColor);
                
            }
        }
        
        return dest;
    }
    
    public static Pixel8 getPixel8(InnerImage innerImage, int xBase, int yBase) {
        Pixel[] neighbor = new Pixel[Pixel8.PIXELS_AMOUNT];
        
        int x = xBase - 1;
        int y = yBase + 1;        
        neighbor[0] = innerImage.getPixels()[x][y];
        
        x = xBase;
        y = yBase + 1;
        neighbor[1] = innerImage.getPixels()[x][y];
        
        x = xBase + 1;
        y = yBase + 1;
        neighbor[2] = innerImage.getPixels()[x][y];
        
        x = xBase + 1;
        y = yBase;
        neighbor[3] = innerImage.getPixels()[x][y];
        
        x = xBase + 1;
        y = yBase - 1;
        neighbor[4] = innerImage.getPixels()[x][y];
        
        x = xBase;
        y = yBase - 1;
        neighbor[5] = innerImage.getPixels()[x][y];
        
        x = xBase - 1;
        y = yBase - 1;
        neighbor[6] = innerImage.getPixels()[x][y];
        
        x = xBase - 1;
        y = yBase;
        neighbor[7] = innerImage.getPixels()[x][y];
        
        x = xBase;
        y = yBase;
//        Pixel8 pixel8 = new Pixel8(neighbor, x, y, reader.getArgb(x, y));
        Pixel8 pixel8 = new Pixel8(neighbor, innerImage.getPixels()[x][y]);
        
        return pixel8;
    }
    
}
