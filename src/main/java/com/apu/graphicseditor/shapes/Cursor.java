/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor.shapes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apu
 */
public class Cursor extends Point{
    private static List<Point> points;
    private static List<Line> lines;
    private static Cursor instance = new Cursor();
    private static Point cursorPoint;
    public static final int CURSOR_RADIUS = 5;
    
    private Cursor() {
        points = new ArrayList<Point>();
        lines = new ArrayList<Line>();
        cursorPoint = new Point();
        for(int i=0;i<4;i++) {
            points.add(new Point());            
        }
        lines.add(new Line(points.get(0), points.get(1)));
        lines.add(new Line(points.get(1), points.get(2)));
        lines.add(new Line(points.get(2), points.get(3)));
        lines.add(new Line(points.get(3), points.get(0)));
    }

    public static Cursor getInstance(Point point) {
        cursorUpdatePoints(point);
        return instance;
    }

    public Point getCursorPoint() {
        return cursorPoint;
    }

    public List<Point> getPoints() {
        return points;
    }

    public List<Line> getLines() {
        return lines;
    }
    
    private static void cursorUpdatePoints(Point point) {
        cursorPoint.setX(point.getX());
        cursorPoint.setY(point.getY());
        points.get(0).x = cursorPoint.getX() - CURSOR_RADIUS;
        points.get(0).y = cursorPoint.getY() - CURSOR_RADIUS;
        points.get(1).x = cursorPoint.getX() - CURSOR_RADIUS;
        points.get(1).y = cursorPoint.getY() + CURSOR_RADIUS;
        points.get(2).x = cursorPoint.getX() + CURSOR_RADIUS;
        points.get(2).y = cursorPoint.getY() + CURSOR_RADIUS;
        points.get(3).x = cursorPoint.getX() + CURSOR_RADIUS;
        points.get(3).y = cursorPoint.getY() - CURSOR_RADIUS;
    }
}
