/*
 * 
 * 
 */
package com.apu.graphicseditor.shapes;

import com.apu.graphicseditor.editor.MouseKeyType;
import com.apu.graphicseditor.OutputInterface;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author al
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"color","fill","colorBeforeHighlight",
                                 "lineWidth","groupId","filled","R","A"})
@XmlRootElement(name = "Circle")
public class Circle extends Shape {
    @XmlElement(name = "R", required = true)
    private double R;
    @XmlElement(name = "A", required = true)
    private Point A;

    public Circle() {
        this(new Point(0,0), 0);
    }

    public Circle(Point center, double R) {
        A = center;
        this.R = R;
    }

    public double getR() {
        return R;
    }

    public void setR(double R) {
        this.R = R;
    }

    public Point getA() {
        return A;
    }

    public void setA(Point A) {
        this.A = A;
    }

    @Override
    public void draw(OutputInterface output) {
        output.drawCircle(getA(), getR(), lineWidth, color, filled, fill);
    }
    
    @Override
    Point getMinPoint() {
        return new Point(getA().getX() - getR(), getA().getY() - getR());
    }

    @Override
    Point getMaxPoint() {
        return new Point(getA().getX() + getR(), getA().getY() + getR());
    }
    
    // checking for current shape - it is inside outer rectangle area or not
    @Override
    public boolean isInsideArea(Point outerPointMin, Point outerPointMax) {
        if((getMinPoint().getX() < outerPointMin.getX()) ||
           (getMinPoint().getY() < outerPointMin.getY()) || 
           (getMaxPoint().getX() > outerPointMax.getX()) ||
           (getMaxPoint().getY() > outerPointMax.getY()))     
            return false;
        
        return true;
    }
    
    @Override
    public boolean contain(Point p, boolean filledFlagNoUsed) {        
        
        double length = Line.length(getA(), p);
        if(length == getR()) return true;        
        
        if(isFilled()||filledFlagNoUsed) {
            if(length <= getR())    return true;            
        }        
        return false;
    }
    
    @Override
    public boolean containCursor(Cursor cursor, boolean filledFlagNoUsed) {
        double length = Line.length(getA(), cursor.getCursorPoint());        
        double radius = getR();
        double accuracy = cursor.CURSOR_RADIUS;
        
        if(radius >= length) {
            if((radius - length) <= accuracy)   return true;
        } else {
            if((length - radius) <= accuracy)   return true;
        }       
        
        if(isFilled()||filledFlagNoUsed) {
            if((length - accuracy) <= radius)    return true;            
        }        
        return false;
    }
    
    @Override
    public boolean move(double dx, double dy) {        
        getA().setX(getA().getX() + dx);
        getA().setY(getA().getY() + dy);
        return true;
    }

    @Override
    public void onMousePressButton(MouseKeyType mouse, Point point) {
        if(mouse == MouseKeyType.PRYMARY) {
            setA(point);
        }
    }

    @Override
    public void onMouseDragg(MouseKeyType mouse, Point point) {
        if(mouse == MouseKeyType.PRYMARY) {
            double x1 = getA().getX();
            double x2 = point.getX();
            if(x2 > x1) {
                setR(x2 - x1);
            } else {
                setR(x1 - x2);
            }
            
        }
    }

    @Override
    public void onMouseReleaseButton(MouseKeyType mouse, Point point) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
