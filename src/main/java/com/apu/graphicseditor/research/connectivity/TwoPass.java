/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor.research.connectivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

/**
 *
 * @author apu
 */
public class TwoPass {
    
    private int[] LABELS = {0xFFFF8080, 0xFFFFFF80, 0xFF80FF80, 0xFF80FFFF, 0xFF0080FF,
                            0xFFFF8080, 0xFFFFFF80, 0xFF80FF80, 0xFF80FFFF, 0xFF0080FF,
                            0xFFFF8080, 0xFFFFFF80, 0xFF80FF80, 0xFF80FFFF, 0xFF0080FF,
                            0xFFFF8080, 0xFFFFFF80, 0xFF80FF80, 0xFF80FFFF, 0xFF0080FF};
    
    private static int nextLabel = 0;
    
    public Image update(Image image) {
        Map<Integer, List<Pixel>> linked = new HashMap<>();
        int width = new Double(image.getWidth()).intValue();
        int height = new Double(image.getHeight()).intValue();
        int[][] labels = new int[width][height];
        
        PixelReader reader = image.getPixelReader();
        WritableImage dest = new WritableImage(width, height);
        PixelWriter writer = dest.getPixelWriter();
        
        Pixel pixel;
        int pixelColor;
        
        //First pass
        for(int y=1; y<height-1; y++) {
            for(int x=1; x<width-1; x++) {
                pixel = new Pixel(x, y, reader.getArgb(x, y));
                if(pixel.getBwvalue() != Pixel.WHITE) {
                    Pixel8 pixel8 = ImageScanner.getPixel8(reader, x, y);
                    if(is4NeighborsEmpty(pixel8)) { 
                        List list;
                        if(linked.containsKey(nextLabel))
                            list = linked.get(nextLabel);
                        else {
                            list = new ArrayList<>();
                            linked.put(nextLabel, list);
                        }
                        list.add(pixel);
                        labels[x][y] = LABELS[nextLabel++];
                    } else {
                        int minLabel = getSmallestNeighborLabel(pixel8, labels);
                        if(minLabel == 0)
                            minLabel = LABELS[nextLabel++];
                        labels[x][y] = minLabel;
//                        unionWithNeibLabels()
                    }
                }                
            }
        }
        
        for(int y=1; y<height-1; y++) {
            for(int x=1; x<width-1; x++) {
                writer.setArgb(x, y, labels[x][y]);
            }
        }
        
        return dest;        
    }
    
//    public void unionWithNeibLabels(Pixel8 pixel8, int label, Map<Integer, List<Pixel>> linked, int[][] labels) {
//        Pixel[] neighbors = pixel8.getNeighbor();
//        if(!linked.containsKey(label))
//            return;
//        List list = linked.get(label);
//        for(int i=0; i<neighbors.length-1; i++) {
//            Pixel nb = neighbors[i];
//            int label = labels[nb.getX()][nb.getY()]
//        }
//    }
    
    public boolean is4NeighborsEmpty(Pixel8 pixel8) {
        Pixel[] neighbors = pixel8.getNeighbor();
        if(neighbors[0].getBwvalue() != Pixel.WHITE)
                return false;
        if(neighbors[1].getBwvalue() != Pixel.WHITE)
                return false;
        if(neighbors[2].getBwvalue() != Pixel.WHITE)
                return false;
        if(neighbors[7].getBwvalue() != Pixel.WHITE)
                return false;
        return true;
    }
    
    public int getSmallestNeighborLabel(Pixel8 pixel8, int[][] labels) {
        Pixel[] neighbors = pixel8.getNeighbor();
        Pixel neighbor = neighbors[0];
        int minLabel = labels[neighbor.getX()][neighbor.getY()];
        for(int i=0; i<neighbors.length - 1; i++) {
            neighbor = neighbors[i];
            int tempLabel = labels[neighbor.getX()][neighbor.getY()];
            if(tempLabel < minLabel)
                minLabel = tempLabel;
        }
        return minLabel;
    }
    
    
}
