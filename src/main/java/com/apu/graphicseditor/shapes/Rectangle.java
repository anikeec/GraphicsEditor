/*
 * 
 * 
 */
package com.apu.graphicseditor.shapes;

import com.apu.graphicseditor.editor.MouseKeyType;
import com.apu.graphicseditor.OutputInterface;
import java.util.List;
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
                                 "lineWidth","groupId","filled","A","C"})
@XmlRootElement(name = "Rectangle")
public class Rectangle extends Shape {
    @XmlElement(name = "A", required = true)
    Point A;
    @XmlElement(name = "C", required = true)
    Point C;

    @Override
    public void draw(OutputInterface output) {
        output.drawRectangle(getA(), getC(), 0, lineWidth, color, filled, fill);
    }

    public Rectangle() {
        this(new Point(0,0), new Point(0,0));
    }

    public Rectangle(Point A, Point C) {
        this.A = A;
        this.C = C;
    }

    public Point getA() {
        return A;
    }

    public void setA(Point A) {
        this.A = A;
    }

    public Point getC() {
        return C;
    }

    public void setC(Point C) {
        this.C = C;
    } 
    
    @Override
    Point getMinPoint() {
        if((getA().getX() <= getC().getX()) &&
           (getA().getY() <= getC().getY())) 
            return getA(); 
        else
            return getC();
    }

    @Override
    Point getMaxPoint() {
        if((getA().getX() <= getC().getX()) &&
           (getA().getY() <= getC().getY())) 
            return getC(); 
        else
            return getA();
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
        Line line;
        
        line = new Line(getA(), new Point(getC().getX(), getA().getY()));
        if(line.contain(p, filledFlagNoUsed)) return true;
        line = new Line(new Point(getC().getX(), getA().getY()), getC());
        if(line.contain(p, filledFlagNoUsed)) return true;
        line = new Line(getC(),new Point(getA().getX(), getC().getY()));
        if(line.contain(p, filledFlagNoUsed)) return true;
        line = new Line(new Point(getA().getX(), getC().getY()), getA());
        if(line.contain(p, filledFlagNoUsed)) return true;
        
        if(isFilled()||filledFlagNoUsed) {
            if((p.getX() >= getA().getX()) &&
               (p.getX() <= getC().getX()) &&
               (p.getY() >= getA().getY()) &&
               (p.getY() <= getC().getY())) {
                return true;
            }
        }
        
        return false;
    }

    @Override
    public boolean containCursor(Cursor cursor, boolean filledFlagNoUsed) {        
        Line line;
        Point p;
        
        line = new Line(getA(), new Point(getC().getX(), getA().getY()));
        if(line.containCursor(cursor, filledFlagNoUsed)) return true;
        line = new Line(new Point(getC().getX(), getA().getY()), getC());
        if(line.containCursor(cursor, filledFlagNoUsed)) return true;
        line = new Line(getC(),new Point(getA().getX(), getC().getY()));
        if(line.containCursor(cursor, filledFlagNoUsed)) return true;
        line = new Line(new Point(getA().getX(), getC().getY()), getA());
        if(line.containCursor(cursor, filledFlagNoUsed)) return true;
        
        //check for all 4 points of cursor - are some of there in rectangle area
        if(isFilled()||filledFlagNoUsed) {
            List<Point> list = cursor.getPoints();
            for(int i=0;i<list.size();i++) {
                if(contain(list.get(i),filledFlagNoUsed)) return true;
            }
        }       
        
        return false;
    }
    
    @Override
    public boolean move(double dx, double dy) {
        
        getA().setX(getA().getX() + dx);
        getA().setY(getA().getY() + dy);
        getC().setX(getC().getX() + dx);
        getC().setY(getC().getY() + dy);
        return true;
    }
    
    @Override
    public void onMousePressButton(MouseKeyType mouse, Point point) {
        if(mouse == MouseKeyType.PRYMARY) {
            setA(point);
            setC(point);
        }
    }
    
    @Override
    public void onMouseDragg(MouseKeyType mouse, Point point) {
        if(mouse == MouseKeyType.PRYMARY) {
            setC(point);
        }
    }
    
    @Override
    public void onMouseReleaseButton(MouseKeyType mouse, Point point) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
