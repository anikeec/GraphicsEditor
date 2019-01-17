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
@XmlRootElement(name = "Line")
public class Line extends Shape{
    @XmlElement(name = "A", required = true)
    Point A;
    @XmlElement(name = "C", required = true)
    Point C;

    public Line() {
        this(new Point(0,0),new Point(0,0));
    }
    @Override
    public void draw(OutputInterface output) {
        output.drawLine(getA(), getC(), 0, lineWidth, color);
    }

    public Line(Point A, Point C) {
        this.A = A;
        this.C = C;
    }

    public Point getA() {
        return A;
    }

    public Point getC() {
        return C;
    }
    
    public void setA(Point A) {
        this.A = A;
    }
    
    public void setC(Point C) {
        this.C = C;
    }
    
    @Override
    Point getMinPoint() {
        Point min = new Point();
        if(getA().getX() <= getC().getX()) {
            min.setX(getA().getX());
        } else {
            min.setX(getC().getX());
        }
        if(getA().getY() <= getC().getY()) {
            min.setY(getA().getY());
        } else {
            min.setY(getC().getY());
        }        
        return min;
    }

    @Override
    Point getMaxPoint() {
        Point max = new Point();
        if(getA().getX() <= getC().getX()) {
            max.setX(getC().getX());
        } else {
            max.setX(getA().getX());
        }
        if(getA().getY() <= getC().getY()) {
            max.setY(getC().getY());
        } else {
            max.setY(getA().getY());
        }        
        return max;
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
    public boolean move(double dx, double dy) {
     
        getA().setX(getA().getX() + dx);
        getA().setY(getA().getY() + dy);
        getC().setX(getC().getX() + dx);
        getC().setY(getC().getY() + dy);        
        return true;
    }

    @Override
    public boolean contain(Point p, boolean filledFlagNoUsed) {
        
        double x1, x2, y1, y2;
        double div1, div2;
        
        x1 = getA().getX();
        y1 = getA().getY();
        x2 = getC().getX();
        y2 = getC().getY();
        
        if(x1 == x2) {
            if(p.getX() != x1)  return false;
            if(y2 > y1) {
                if((p.getY() >= y1) && (p.getY() <= y2)) return true;
            } else {
                if((p.getY() >= y2) && (p.getY() <= y1)) return true;
            }
        } 
        
        if(y1 == y2) {
            if(p.getY() != y1)  return false;
            if(x2 > x1) {
                if((p.getX() >= x1) && (p.getX() <= x2)) return true;
            } else {
                if((p.getX() >= x2) && (p.getX() <= x1)) return true;
            }
        }
        
        div1 = (p.getX() - x1)/(x2 - x1);
        div2 = (p.getY() - y1)/(y2 - y1);
        if(div1 == div2) {
            if(x2 > x1) {
                if((p.getX() < x1) || (p.getX() > x2)) return false;
            } else {
                if((p.getX() < x2) || (p.getX() > x1)) return false;
            }
            if(y2 > y1) {
                if((p.getY() < y1) || (p.getY() > y2)) return false;
            } else {
                if((p.getY() < y2) || (p.getY() > y1)) return false;
            }            
            return true;
        }

        return false;
    }

    @Override
    public boolean containCursor(Cursor cursor, boolean filledFlagNoUsed) {
         List<Line> list = cursor.getLines();
         for(Line line:list){
             if(this.getLineCrosspoint(line) != null)
                 return true;
         }
         return false;
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
    
    enum Position {LEFT,  RIGHT,  BEYOND,  BEHIND, BETWEEN, ORIGIN};
    
    // return point's position relative to a line
    Position pointPosToLine(Point point) {
        Point a = getA();
        Point c = getC();
        double sa = ((c.getX() - a.getX()) * (point.getY() - a.getY())) - 
                    ((point.getX() - a.getX()) * (c.getY() - a.getY()));
        if (sa > 0.005)
            return Position.LEFT;
        if (sa < -0.005)
            return Position.RIGHT;
        if ((((c.getX() - a.getX()) * (point.getX() - a.getX())) < 0.0) || 
             ((c.getY() - a.getY()) * (point.getY() - a.getY()) < 0.0))
            return Position.BEHIND;
        double l1 = length(a,c);
        double l2 = length(a,point);
        if (length(a,c) < length(a,point))
            return Position.BEYOND;
        if (((getA().getX() == point.getX())&&(getA().getY() == point.getY())) || 
            ((getC().getX() == point.getX())&&(getC().getY() == point.getY())))
            return Position.ORIGIN;
        return Position.BETWEEN;
    }
    
    // return line's length
    public static double length(Point a, Point c) {
        return Math.sqrt(Math.pow((c.getX() - a.getX()),2) + 
                         Math.pow((c.getY() - a.getY()),2));
    }
    
    // return line's crosspoint (null if crosspoint absent)
    Point getLineCrosspoint(Line line) { //http://javatalks.ru/topics/question/49398?page=1#248957
        double x11, y11, x12, y12;
        double x21, y21, x22, y22;
        
        x11 = getA().getX();
        y11 = getA().getY();
        x12 = getC().getX();
        y12 = getC().getY();
        x21 = line.getA().getX();
        y21 = line.getA().getY();
        x22 = line.getC().getX();
        y22 = line.getC().getY();      
        
        double dy1 = y11 - y12;      // lines parameters
        double dx1 = x12 - x11;
        double dy2 = y21 - y22;
        double dx2 = x22 - x21;        
        
        double d = dy1*dx2 - dy2*dx1;   // cross coordinates
        if( d != 0 ) {	
            double c1 = y12 * x11 - x12 * y11; 
            double c2 = y22 * x21 - x22 * y21;

            double xRes = (dx1 * c2 - dx2 * c1) / d;
            double yRes = (dy2 * c1 - dy1 * c2) / d;
            Point crosspoint = new Point(xRes, yRes);
            if(this.pointPosToLine(crosspoint) != Position.BETWEEN) {
                return null;
            }
            if(line.pointPosToLine(crosspoint) != Position.BETWEEN) {
                return null;
            }
            return crosspoint;
        }     
        
        return null;
    }
    
    /*
    public static void main(String[] args) {
        Line line = new Line(new Point(4,4), new Point(6,4));
        Position pos1 = line.pointPosToLine(new Point(1,1));
        Position pos2 = line.pointPosToLine(new Point(3,4));
        Position pos3 = line.pointPosToLine(new Point(4,2));
        Position pos4 = line.pointPosToLine(new Point(6,6));
        Position pos5 = line.pointPosToLine(new Point(3,3));
        System.out.println("");
        Line line1 = new Line(new Point(5,1), new Point(5,6));
        Point p1 = line.getLineCrosspoint(line1);
        System.out.println("");        
        Line line2 = new Line(new Point(2,5), new Point(10,6));
        Point p2 = line.getLineCrosspoint(line2);
        System.out.println("");        
    }
    */
    
}
