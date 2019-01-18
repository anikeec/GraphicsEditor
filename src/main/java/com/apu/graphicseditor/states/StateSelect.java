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
public class StateSelect extends StateBase{

    public StateSelect(StateEngine stateEngine) {
        this.stateEngine = stateEngine;
    }

    @Override
    public void onMousePressPrymary(Point point) {
        stateEngine.graphicsEditor.getCurrentSheet().selectFigure(point, false);
    }

    @Override
    public void onMouseDraggPrymary(Point point) {
        stateEngine.graphicsEditor.moveSelectedFigures(point);
    }

    @Override
    public void onMouseReleasePrymary(Point point) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onGuiPressButton(BtnType button) {        
        if(null == button) {
            onGuiPressButtonBase(button);
        } else switch (button) {
            case GROUP: 
                        stateEngine.graphicsEditor.getCurrentSheet().groupSelected();
                        break;
            case UNGROUP:
                        stateEngine.graphicsEditor.getCurrentSheet().ungroupSelected();
                        break;
            case UP:
                        stateEngine.graphicsEditor.getCurrentSheet().moveForwardSelected();
                        break;
            case DOWN:
                        stateEngine.graphicsEditor.getCurrentSheet().moveBackwardSelected();
                        break;
            case FIRST_RESEARCH:
                        stateEngine.graphicsEditor.runFirstResearch();
                        break;
            case SECOND_RESEARCH:
                        stateEngine.graphicsEditor.runSecondResearch();
                        break;
            case ERODE:
                        stateEngine.graphicsEditor.applyErodeFilter();
                        break;
            case DILATE:
                        stateEngine.graphicsEditor.applyDilateFilter();
                        break;
            case BLACK_WHITE:
                        stateEngine.graphicsEditor.applyBlackWhiteFilter();
                        break;
            case COLOR:
                        stateEngine.graphicsEditor.applyColorFilter();
                        break;
            default:
                        stateEngine.graphicsEditor.getCurrentSheet().unselectAllFigures();
                        onGuiPressButtonBase(button);
                        break;
        }
    }

    @Override
    public void onKeyboardPressButton(KeyType button) {
        if(button == KeyType.CTRL) {
            stateEngine.setState(stateEngine.stateGroupSelect);
        } else {
            onKeyboardPressButtonBase(button);
        }
    }    

    @Override
    public void onKeyboardReleaseButton(KeyType button) {
        onKeyboardReleaseButtonBase(button);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
