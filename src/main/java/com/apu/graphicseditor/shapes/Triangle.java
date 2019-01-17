/*
 * 
 * 
 */
package com.apu.graphicseditor.shapes;

import com.apu.graphicseditor.editor.MouseKeyType;
import com.apu.graphicseditor.OutputInterface;

/**
 *
 * @author al
 */
public class Triangle extends Shape{
    private Line AB;
    private Line BC;
    private Line CA;

    public Triangle(Point A, Point B, Point C) {
        AB = new Line(A,B);
        BC = new Line(B,C);
        CA = new Line(C,A);
    }

    
    @Override
    public void draw(OutputInterface output) {
       AB.draw(output);
       BC.draw(output);
       CA.draw(output);
    }

    @Override
    public boolean move(double dx, double dy) {
        //return super.move(dx, dy); //To change body of generated methods, choose Tools | Templates.
        return false;
    }    
    

    @Override
    public boolean contain(Point p, boolean filledFlagNoUsed) {
        
        if(AB.contain(p, filledFlagNoUsed)) return true;
        if(BC.contain(p, filledFlagNoUsed)) return true;
        if(CA.contain(p, filledFlagNoUsed)) return true;
        
        if(isFilled()) {
            //....
        }
        
        return false;
    }

    @Override
    public void onMousePressButton(MouseKeyType mouse, Point point) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onMouseDragg(MouseKeyType mouse, Point point) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onMouseReleaseButton(MouseKeyType mouse, Point point) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
