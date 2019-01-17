/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor.shapes;

import javafx.scene.paint.Color;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author apu
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "color", propOrder = {"red","green","blue","opacity"})
public class ColorRGB {
    @XmlElement(required = true)
    public final double red;
    @XmlElement(required = true)
    public final double green;
    @XmlElement(required = true)
    public final double blue;
    @XmlElement(required = true)
    public final double opacity;

    public ColorRGB() {
        this(0,0,0,0);
    }

    public ColorRGB(double R, double G, double B, double opacity) {
        this.red = R;
        this.green = G;
        this.blue = B;
        this.opacity = opacity;
    }

    public ColorRGB(Color color) {
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
        this.opacity = color.getOpacity();
    }
    
    
    
    public static ColorRGB colorToRGB(Color color) {
        ColorRGB ret = new ColorRGB(color.getRed(),
                                    color.getGreen(), 
                                    color.getBlue(), 
                                    color.getOpacity());
        return ret;
    }
    
    public static Color rgbToColor(ColorRGB color) {
        Color ret = new Color(color.getRed(),
                                color.getGreen(), 
                                color.getBlue(), 
                                color.getOpacity());
        return ret;
    }

    public double getRed() {
        return red;
    }

    public double getGreen() {
        return green;
    }

    public double getBlue() {
        return blue;
    }

    public double getOpacity() {
        return opacity;
    }
    
    
}
