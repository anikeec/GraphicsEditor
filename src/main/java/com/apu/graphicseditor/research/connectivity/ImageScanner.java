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
        int width = new Double(image.getWidth()).intValue();
        int height = new Double(image.getHeight()).intValue();
        
        PixelReader reader = image.getPixelReader();
        WritableImage dest = new WritableImage(width, height);
        PixelWriter writer = dest.getPixelWriter();
        
        Pixel pixel;
        int pixelColor;
        for(int y=1; y<height-1; y++) {
            for(int x=1; x<width-1; x++) {
//                pixelColor = reader.getArgb(x, y);
                pixel = filter.handlePixel8(getPixel8(reader, x, y));
                
//                pixelColor = pixel.getRed();
//                pixelColor = (pixelColor << 8) + pixel.getGreen();
//                pixelColor = (pixelColor << 8) + pixel.getBlue();
//                pixelColor |= 0x4F000000;
                pixelColor = pixel.getColor();
                writer.setArgb(x, y, pixelColor);
                
            }
        }
        
        return dest;
    }
    
    private static Pixel8 getPixel8(PixelReader reader, int xBase, int yBase) {
        Pixel[] neighbor = new Pixel[Pixel8.PIXELS_AMOUNT];
        
        int x = xBase - 1;
        int y = yBase + 1;        
        neighbor[0] = new Pixel(x, y, reader.getArgb(x, y));
        
        x = xBase;
        y = yBase + 1;
        neighbor[1] = new Pixel(x, y, reader.getArgb(x, y));
        
        x = xBase + 1;
        y = yBase + 1;
        neighbor[2] = new Pixel(x, y, reader.getArgb(x, y));
        
        x = xBase + 1;
        y = yBase;
        neighbor[3] = new Pixel(x, y, reader.getArgb(x, y));
        
        x = xBase + 1;
        y = yBase - 1;
        neighbor[4] = new Pixel(x, y, reader.getArgb(x, y));
        
        x = xBase;
        y = yBase - 1;
        neighbor[5] = new Pixel(x, y, reader.getArgb(x, y));
        
        x = xBase - 1;
        y = yBase - 1;
        neighbor[6] = new Pixel(x, y, reader.getArgb(x, y));
        
        x = xBase - 1;
        y = yBase;
        neighbor[7] = new Pixel(x, y, reader.getArgb(x, y));
        
        x = xBase;
        y = yBase;
        Pixel8 pixel8 = new Pixel8(neighbor, x, y, reader.getArgb(x, y));
        
        return pixel8;
    }
    
}
