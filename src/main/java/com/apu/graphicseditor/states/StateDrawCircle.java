/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor.states;

import com.apu.graphicseditor.editor.BtnType;
import com.apu.graphicseditor.editor.KeyType;
import com.apu.graphicseditor.editor.MouseKeyType;
import com.apu.graphicseditor.shapes.Circle;
import com.apu.graphicseditor.shapes.Point;
import com.apu.graphicseditor.shapes.Shape;

/**
 *
 * @author Ksusha
 */
public class StateDrawCircle extends StateBase{    

    public StateDrawCircle(StateEngine stateEngine) {
        this.stateEngine = stateEngine;
    }   

    @Override
    public void onMousePressPrymary(Point point) {
        if(!stateEngine.graphicsEditor.getCurrentSheet().isInSheetBorder(point))   return;
        Circle circle = new Circle();
        circle.onMousePressButton(MouseKeyType.PRYMARY, point);
        circle.setColor(stateEngine.graphicsEditor.getBorderColor());
        circle.setFill(stateEngine.graphicsEditor.getFillColor());
        stateEngine.graphicsEditor.getCurrentSheet().setTempShape(circle);
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
