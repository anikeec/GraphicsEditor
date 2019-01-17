/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor.states;

import com.apu.graphicseditor.editor.BtnType;
import com.apu.graphicseditor.editor.KeyType;
import com.apu.graphicseditor.shapes.Point;

/**
 *
 * @author apu
 */
public class StateGroupSelect extends StateBase {

    public StateGroupSelect(StateEngine stateEngine) {
        this.stateEngine = stateEngine;
    }
    
    @Override
    public void onMousePressPrymary(Point point) {        
        stateEngine.graphicsEditor.getCurrentSheet().selectFigure(point, true);
    }

    @Override
    public void onMouseDraggPrymary(Point point) {
        stateEngine.graphicsEditor.moveSelectedFigures(point);
    }

    @Override
    public void onMouseReleasePrymary(Point point) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onGuiPressButton(BtnType button) {
        //onGuiPressButtonBase(button);
        throw new UnsupportedOperationException("Not working."); 
    }

    @Override
    public void onKeyboardPressButton(KeyType button) {         
        onKeyboardPressButtonBase(button);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onKeyboardReleaseButton(KeyType button) {
        if(button == KeyType.CTRL) {
            stateEngine.setState(stateEngine.stateSelect);
        } else {
            onKeyboardReleaseButtonBase(button);
        }
    }
    
}
