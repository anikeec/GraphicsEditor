/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor.shapes;

import com.apu.graphicseditor.OutputInterface;
import com.apu.graphicseditor.editor.MouseKeyType;
import java.awt.image.BufferedImage;
import java.util.List;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author apu
 */
@NoArgsConstructor
public class ImageShape extends Rectangle {
    
    @Getter @Setter
    private Image image;
    
    @Getter @Setter
    private double x;
    
    @Getter @Setter
    private double y;
    
    @Getter @Setter
    private double width;
    
    @Getter @Setter
    private double height;

    public ImageShape(Image image, double x, double y, double width, double height) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(OutputInterface output) {
        output.drawImage(image, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
    
    @Override
    Point getMinPoint() {
        double xMin = this.getX();
        double yMin = this.getY();
        return new Point(xMin, yMin);
    }
    
    @Override
    Point getMaxPoint() {
        double xMax = this.getX()+this.getWidth();
        double yMax = this.getY()+this.getHeight();
        return new Point(xMax, yMax);
    }
    
    @Override
    public boolean isInsideArea(Point outerPointMin, Point outerPointMax) {
        
        double xMin = this.getX();
        double xMax = this.getX()+this.getWidth();
        double yMin = this.getY();
        double yMax = this.getY()+this.getHeight();
        
        if((outerPointMin.getX() <= xMin) &&
           (outerPointMin.getY() <= yMin) &&
           (outerPointMax.getX() >= xMax) &&
           (outerPointMax.getY() >= yMax))
            return true;
        return false;
    }
    
    @Override
    public boolean contain(Point p, boolean filledFlagNoUsed) {        
        return isContainCoordinates(p.getX(), p.getY());
    }
    
    @Override
    public boolean containCursor(Cursor cursor, boolean filledFlagNoUsed) {
        return isContainCoordinates(cursor.getCursorPoint().getX(), cursor.getCursorPoint().getY());
    }   
    
    @Override
    public boolean move(double dx, double dy) {        
        this.setX(this.getX() + dx);
        this.setY(this.getY() + dy);
        return true;
    }
    
    @Override
    public void onMousePressButton(MouseKeyType mouse, Point point) {
        if(mouse == MouseKeyType.PRYMARY) {
            setA(point);
            setX(point.getX());
            setY(point.getY());
            setC(point);
        }
    }
    
    @Override
    public void onMouseDragg(MouseKeyType mouse, Point point) {

    }
    
    @Override
    public void onMouseReleaseButton(MouseKeyType mouse, Point point) {

    }
    
    private boolean isContainCoordinates(double x, double y) {
        double cursorX = x;
        double cursorY = y;
        
        double xMin = this.getX();
        double xMax = this.getX()+this.getWidth();
        double yMin = this.getY();
        double yMax = this.getY()+this.getHeight();
        
        if((cursorX >= xMin) && (cursorX <= xMax) && 
           (cursorY >= yMin) && (cursorY <= yMax))
            return true;
        
        return false;
    }
    
}
