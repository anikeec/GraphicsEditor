/*
 * 
 * 
 */
package com.apu.graphicseditor.shapes;

import com.apu.graphicseditor.OutputInterface;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author al
 */
@NoArgsConstructor
public class Composite{
    
    @Getter
    List<Shape> shapes = new ArrayList<>();;
    
    public void draw(OutputInterface output) {
       for(Shape s: shapes){
           //s.applyColors();
           s.draw(output);
       }
    }
    public void addShape(Shape s){
        shapes.add(s);
    }
    
}
