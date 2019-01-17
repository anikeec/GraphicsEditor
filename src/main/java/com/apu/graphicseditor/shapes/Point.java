/*
 * 
 * 
 */
package com.apu.graphicseditor.shapes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author al
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "point", propOrder = {"x","y"})
public class Point {
    @XmlElement(required = true)
    protected double x;
    @XmlElement(required = true)
    protected double y;

    public Point() {
        this(0,0);
    }
    
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
/*
    Point(double xRes, double yRes) {
        Double xDbl = xRes;
        Double yDbl = yRes;
        int xInt = xDbl.intValue();
        int yInt = yDbl.intValue();
        this.x = xInt;
        this.y = yInt;
    }
*/    
    public double getX() {
        return x;
    }
    
    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    /*
    public void moveTo(Point p){
        x=p.getX();
        y=p.getY();
    }*/
    
    public boolean move(double dx, double dy) {
        x += dx;
        y += dy;
        return true;
    }
    
    public boolean isInsideArea(Point outerPointMin, Point outerPointMax) {
        return false;
    }
    
    public boolean contain(Point p, boolean filledFlagNoUsed) {
        if((p.getX() == x) && (p.getY() == y)) 
            return true;
        return false;
    };
}
