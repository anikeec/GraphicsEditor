/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor.states;

import com.apu.graphicseditor.InputInterface;
import com.apu.graphicseditor.editor.BtnType;
import com.apu.graphicseditor.editor.ColorType;
import com.apu.graphicseditor.editor.GraphicsEditor;
import com.apu.graphicseditor.editor.KeyType;
import com.apu.graphicseditor.OutputInterface;
import com.apu.graphicseditor.shapes.ColorRGB;
import com.apu.graphicseditor.shapes.Point;

/**
 *
 * @author apu
 */
public class StateEngine implements InputInterface{
    State stateSelect;
    State stateGroupSelect;
    State stateDrawCircle;
    State stateDrawLine;
    State stateDrawRectangle;    
    State stateInsertImage;
    State stateFill;
    State stateRemove;
    private State state;
    GraphicsEditor graphicsEditor;
    OutputInterface output;

    public StateEngine() {
        stateSelect = new StateSelect(this);
        stateGroupSelect = new StateGroupSelect(this);  
        stateDrawCircle = new StateDrawCircle(this);
        stateDrawLine = new StateDrawLine(this);
        stateDrawRectangle = new StateDrawRectangle(this);
        stateInsertImage = new StateInsertImage(this);
        stateFill = new StateFill(this); 
        stateRemove = new StateRemove(this); 
        state = stateSelect;
        graphicsEditor = GraphicsEditor.getInstance();
        output = graphicsEditor.getOutputInterface();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    
    @Override
    public void onChangeColor(ColorType colorType, ColorRGB color) {
        if(colorType == ColorType.BORDER_COLOR) {
            graphicsEditor.setBorderColor(color);
        } else if (colorType == ColorType.FILL_COLOR) {
            graphicsEditor.setFillColor(color);
        }
    }

    @Override
    public void onMousePressPrymary(Point point) {
        state.onMousePressPrymary(point);
        graphicsEditor.getCurrentSheet().setLastMousePressPoint(point);
        graphicsEditor.getCurrentSheet().update(output);
        if(graphicsEditor.getCurrentSheet().getTempShape() != null)
            graphicsEditor.getCurrentSheet().getTempShape().show(output);
    }

    @Override
    public void onMouseDraggPrymary(Point point) {
        graphicsEditor.getCurrentSheet().update(output);
        state.onMouseDraggPrymary(point);
        if(graphicsEditor.getCurrentSheet().getTempShape() != null)
            graphicsEditor.getCurrentSheet().getTempShape().show(output);
    }

    @Override
    public void onMouseReleasePrymary(Point point) {
        state.onMouseReleasePrymary(point);
        graphicsEditor.getCurrentSheet().update(output);
    }

    @Override
    public void onGuiPressButton(BtnType button) {
        state.onGuiPressButton(button);
        graphicsEditor.getCurrentSheet().update(output);
    }

    @Override
    public void onKeyboardPressButton(KeyType button) {
        state.onKeyboardPressButton(button);
    }

    @Override
    public void onKeyboardReleaseButton(KeyType button) {
        state.onKeyboardReleaseButton(button);
    }    
    
}
