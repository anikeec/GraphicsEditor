/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor.states;

import com.apu.graphicseditor.editor.BtnType;
import com.apu.graphicseditor.editor.KeyType;

/**
 *
 * @author apu
 */
public abstract class StateBase implements State{
    StateEngine stateEngine;

    public void onGuiPressButtonBase(BtnType button) {
        if(null != button) switch (button) {
            case CURSOR:
                        stateEngine.setState(stateEngine.stateSelect);
                        break;
            case SELECT_AREA:
                        break;
            case OPEN:
                        stateEngine.graphicsEditor.getCurrentSheet().loadFigures();
                        break;
            case SAVE:
                        stateEngine.graphicsEditor.getCurrentSheet().saveFigures();
                        break;
            case REMOVE:   
                        stateEngine.setState(stateEngine.stateRemove);
                        break;
            case FILL:
                        stateEngine.setState(stateEngine.stateFill);
                        break;
            case LINE:
                        stateEngine.setState(stateEngine.stateDrawLine);
                        break;
            case RECTANGLE:
                        stateEngine.setState(stateEngine.stateDrawRectangle);
                        break;
            case CIRCLE:  
                        stateEngine.setState(stateEngine.stateDrawCircle);
                        break;
            case SQUARE:
            case TRIANGLE:
            case POLYGON:
            case RHOMBUS:            
            case ELLIPSE:
            case PENTAGON:
            case OCTAGON:
                        break;
            default:
                        break;
        }
    }

    public void onKeyboardPressButtonBase(KeyType button) {
        
    }  
    
    public void onKeyboardReleaseButtonBase(KeyType button) {
        
    }
    
}
