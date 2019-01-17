/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import com.apu.graphicseditor.shapes.ColorRGB;
import com.apu.graphicseditor.shapes.Point;

/**
 *
 * @author apu
 */
public class JavaFxOutAdapter implements OutputInterface{
    private static GraphicsContext gc;
    private static JavaFxOutAdapter instance;

    private JavaFxOutAdapter() {
    }

    public static JavaFxOutAdapter getInstance() {
        if(instance == null) {
            instance = new JavaFxOutAdapter();
        }
        return instance;
    }

    public void setGraphicsContext(GraphicsContext gc) {
        JavaFxOutAdapter.gc = gc;
    }
    
    Color rgbToJavaFxColor(ColorRGB color) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawLine(Point A, Point C, double angle, int lineWidth, ColorRGB brdColor) {
        gc.setLineWidth(lineWidth);
        gc.setStroke(ColorRGB.rgbToColor(brdColor));
        gc.strokeLine(A.getX(), A.getY(), C.getX(), C.getY());
    }

    @Override
    public void drawRectangle(Point A, Point C, double angle, int lineWidth, ColorRGB brdColor, boolean filled, ColorRGB fill) {
        gc.setLineWidth(lineWidth);
        gc.setStroke(ColorRGB.rgbToColor(brdColor));
        gc.setFill(ColorRGB.rgbToColor(fill));
        if(filled){
            gc.fillRect(A.getX(), A.getY(), C.getX() - A.getX(), C.getY() - A.getY());
        }
        gc.strokeRect(A.getX(), A.getY(), C.getX() - A.getX(), C.getY() - A.getY());
    }

    @Override
    public void drawCircle(Point A, double radius, int lineWidth, ColorRGB brdColor, boolean filled, ColorRGB fill) {
        gc.setLineWidth(lineWidth);
        gc.setStroke(ColorRGB.rgbToColor(brdColor));
        gc.setFill(ColorRGB.rgbToColor(fill));
        if(filled){
            gc.fillOval(A.getX() - radius, 
                        A.getY() - radius, 
                        2 * radius, 
                        2 * radius);
        }
        gc.strokeOval(A.getX() - radius, 
                        A.getY() - radius, 
                        2 * radius, 
                        2 * radius);
    }
    
}
