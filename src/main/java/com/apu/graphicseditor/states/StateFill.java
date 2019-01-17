/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor.states;

import com.apu.graphicseditor.editor.BtnType;
import com.apu.graphicseditor.editor.KeyType;
import com.apu.graphicseditor.shapes.ColorRGB;
import com.apu.graphicseditor.shapes.Point;

/**
 *
 * @author apu
 */
public class StateFill extends StateBase{

    public StateFill(StateEngine stateEngine) {
        this.stateEngine = stateEngine;
    }

    @Override
    public void onMousePressPrymary(Point point) {
        ColorRGB color = stateEngine.graphicsEditor.getFillColor();
        stateEngine.graphicsEditor.getCurrentSheet().fillFigure(point, color);
    }

    @Override
    public void onMouseDraggPrymary(Point point) {
        //not used
    }

    @Override
    public void onMouseReleasePrymary(Point point) {
        //not used
    }

    @Override
    public void onGuiPressButton(BtnType button) {
        onGuiPressButtonBase(button);
    }

    @Override
    public void onKeyboardPressButton(KeyType button) {
        onKeyboardPressButtonBase(button);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onKeyboardReleaseButton(KeyType button) {
        onKeyboardReleaseButtonBase(button);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
