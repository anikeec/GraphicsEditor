/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor;

import com.apu.graphicseditor.shapes.ColorRGB;
import com.apu.graphicseditor.shapes.Point;
import javafx.scene.image.Image;

/**
 *
 * @author apu
 */
public interface OutputInterface {
    
    public void drawLine(Point A, Point C, double angle, int lineWidth, ColorRGB borderColor);
    public void drawRectangle(Point A, Point C, double angle, int lineWidth, ColorRGB border, boolean filled, ColorRGB fill);
    public void drawCircle(Point A, double radius, int lineWidth, ColorRGB borderColor, boolean filled, ColorRGB fillColor);
    public void drawImage(Image image, double x, double y, double width, double height);
    
}
