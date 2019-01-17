/*
 * 
 * 
 */
package com.apu.graphicseditor.shapes;

import com.apu.graphicseditor.OutputInterface;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author al
 */
public class Composite{
    List<Shape> shapes;

    public Composite() {
        shapes = new ArrayList<>();
    }  
    
    public void draw(OutputInterface output) {
       for(Shape s: shapes){
           //s.applyColors();
           s.draw(output);
       }
    }
    public void add(Shape s){
        shapes.add(s);
    }

    public List<Shape> getShapes() {
        return shapes;
    }    
    
}
