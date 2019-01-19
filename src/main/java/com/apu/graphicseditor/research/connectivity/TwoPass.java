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
        InnerImage innerImage = new InnerImage(image);
        int height = innerImage.getHeight();
        int width = innerImage.getWidth();
        Pixel[][] labeledPixels = new Pixel[width][height];
        
//        PixelReader reader = image.getPixelReader();
        WritableImage dest = new WritableImage(width, height);
        PixelWriter writer = dest.getPixelWriter();
        
        Pixel pixel;
        int pixelColor;
        
        //First pass
        for(int y=1; y<height-1; y++) {
            for(int x=1; x<width-1; x++) {
                pixel = innerImage.getPixels()[x][y];
                if(pixel.getBwvalue() != Pixel.WHITE) {
                    Pixel8 pixel8 = ImageScanner.getPixel8(innerImage, x, y);
                    if(is4NeighborsEmpty(pixel8)) { 
                        List list;
                        if(linked.containsKey(nextLabel)) 
                            list = linked.get(nextLabel);
                        else {
                            list = new ArrayList<>();
                            linked.put(nextLabel, list);
                        }
                        pixel.setLabel(nextLabel);
                        list.add(pixel);
                        labeledPixels[x][y] = pixel;
                        pixel.setLabel(LABELS[nextLabel++]);
                    } else {
                        int minLabel = getSmallestNeighborLabel(pixel8, labeledPixels);
                        if(minLabel == 0)
                            minLabel = LABELS[nextLabel++];
                        labeledPixels[x][y] = pixel;
                        pixel.setLabel(minLabel);
//                        unionWithNeibLabels(pixel8, minLabel, linked, labeledPixels);
                    }
                }                
            }
        }
        
        for(int y=1; y<height-1; y++) {
            for(int x=1; x<width-1; x++) {
                writer.setArgb(x, y, labeledPixels[x][y].getLabel());
            }
        }
        
        return dest;        
    }
    
    public void unionWithNeibLabels(Pixel8 pixel8, 
                                    int label, 
                                    Map<Integer, List<Pixel>> linked, 
                                    Pixel[][] labeledPixels) {
        Pixel[] neighbors = pixel8.getNeighbor();
        if(!linked.containsKey(label))
            return;
        List<Pixel> listForCurrentLabel = linked.get(label);
        for(int i=0; i<neighbors.length-1; i++) {
            //get neighbor
            Pixel neighbor = neighbors[i];
            
            for(Pixel labeledPixel: listForCurrentLabel) {
                if(labeledPixel == neighbor) {
                    //remove from one labeled List and add to another
                    if(linked.containsKey(label)) {
                        List<Pixel> originalList = linked.get(label);
                        originalList.remove(neighbor);
                    }         
                    neighbor.setLabel(label);
                    listForCurrentLabel.add(neighbor);
                }
            }
//            int label = labels[nb.getX()][nb.getY()]
        }
    }
    
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
    
    public int getSmallestNeighborLabel(Pixel8 pixel8, Pixel[][] labeledPixels) {
        Pixel[] neighbors = pixel8.getNeighbor();
        Pixel neighbor = neighbors[0];
        int minLabel = labeledPixels[neighbor.getX()][neighbor.getY()].getLabel();
        for(int i=0; i<neighbors.length - 1; i++) {
            neighbor = neighbors[i];
            int tempLabel = labeledPixels[neighbor.getX()][neighbor.getY()].getLabel();
            if(tempLabel < minLabel)
                minLabel = tempLabel;
        }
        return minLabel;
    }
    
    
}
