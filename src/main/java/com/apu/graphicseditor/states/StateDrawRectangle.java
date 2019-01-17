/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor.states;

import com.apu.graphicseditor.editor.BtnType;
import com.apu.graphicseditor.editor.KeyType;
import com.apu.graphicseditor.editor.MouseKeyType;
import com.apu.graphicseditor.shapes.Point;
import com.apu.graphicseditor.shapes.Rectangle;
import com.apu.graphicseditor.shapes.Shape;

/**
 *
 * @author apu
 */
public class StateDrawRectangle extends StateBase{    

    public StateDrawRectangle(StateEngine stateEngine) {
        this.stateEngine = stateEngine;
    }   

    @Override
    public void onMousePressPrymary(Point point) {
        if(!stateEngine.graphicsEditor.getCurrentSheet().isInSheetBorder(point))   return;
        Rectangle rect = new Rectangle();
        rect.onMousePressButton(MouseKeyType.PRYMARY, point);
        rect.setColor(stateEngine.graphicsEditor.getBorderColor());
        rect.setFill(stateEngine.graphicsEditor.getFillColor());
        stateEngine.graphicsEditor.getCurrentSheet().setTempShape(rect);
    }

    @Override
    public void onMouseDraggPrymary(Point point) {
        if(!stateEngine.graphicsEditor.getCurrentSheet().isInSheetBorder(point))   return;
        stateEngine.graphicsEditor.getCurrentSheet().getTempShape().onMouseDragg(MouseKeyType.PRYMARY, point);
    }

    @Override
    public void onMouseReleasePrymary(Point point) {
        Shape shape = stateEngine.graphicsEditor.getCurrentSheet().getTempShape();
        if(shape == null)   return;
        stateEngine.graphicsEditor.getCurrentSheet().add(shape);
        stateEngine.graphicsEditor.getCurrentSheet().setTempShape(null);
    }    

    @Override
    public void onGuiPressButton(BtnType button) {
        onGuiPressButtonBase(button);
    }
    
    @Override
    public void onKeyboardPressButton(KeyType button) {
        onKeyboardPressButtonBase(button);
    }

    @Override
    public void onKeyboardReleaseButton(KeyType button) {
        onKeyboardReleaseButtonBase(button);
    }
}
