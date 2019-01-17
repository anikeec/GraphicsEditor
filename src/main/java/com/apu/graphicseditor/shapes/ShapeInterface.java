/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor.shapes;

import com.apu.graphicseditor.editor.MouseKeyType;
import com.apu.graphicseditor.OutputInterface;

/**
 *
 * @author apu
 */
public interface ShapeInterface {
    
    public void draw(OutputInterface output);
    
    public void show(OutputInterface output);
    
    public void erase(OutputInterface output);
    
    public void onMousePressButton(MouseKeyType mouse, Point point);
    public void onMouseDragg(MouseKeyType mouse, Point point);
    public void onMouseReleaseButton(MouseKeyType mouse, Point point);
    //public void onKeyboardPressButton(Key key, Mode mode);
    
}
