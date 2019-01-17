/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor;

import com.apu.graphicseditor.editor.BtnType;
import com.apu.graphicseditor.editor.ColorType;
import com.apu.graphicseditor.editor.KeyType;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import com.apu.graphicseditor.shapes.ColorRGB;
import com.apu.graphicseditor.shapes.Point;
import com.apu.graphicseditor.states.StateEngine;

/**
 *
 * @author apu
 */
public class JavaFxInAdapter implements InAdapterInterface{
    
    private InputInterface inputEngine;

    public JavaFxInAdapter() {
        inputEngine = new StateEngine();
    }
  
    @Override
    public void onChangeColor(ColorType colorType, Color color) {
        inputEngine.onChangeColor(colorType, new ColorRGB(color));
    }
    
    @Override
    public void onClickButton(BtnType btn){
        inputEngine.onGuiPressButton(btn);
    }
    
    @Override
    public void onMousePressPrimary(double x, double y){
        inputEngine.onMousePressPrymary(new Point(x,y));
    }
    
    @Override
    public void onMouseReleasePrymary(double x, double y){
        inputEngine.onMouseReleasePrymary(new Point(x,y));
    }
    
    @Override
    public void onMouseDraggPrymary(double x, double y){
        inputEngine.onMouseDraggPrymary(new Point(x,y));
    }
    
    @Override
    public void onKeyboardPressButton(KeyCode key) {        
        if(null != key) switch (key) {
            case ESCAPE:
                        inputEngine.onKeyboardPressButton(KeyType.ESC);
                        break;
            case ENTER:
                        inputEngine.onKeyboardPressButton(KeyType.ENTER);
                        break;
            case CONTROL:
                        inputEngine.onKeyboardPressButton(KeyType.CTRL);
                        break;
            default:
                        break;
        }
    }
    
    @Override
    public void onKeyboardReleaseButton(KeyCode key) {
        if(null != key) switch (key) {
            case ESCAPE:
                        inputEngine.onKeyboardReleaseButton(KeyType.ESC);
                        break;
            case ENTER:
                        inputEngine.onKeyboardReleaseButton(KeyType.ENTER);
                        break;
            case CONTROL:
                        inputEngine.onKeyboardReleaseButton(KeyType.CTRL);
                        break;
            default:
                        break;
        }
    }
    
}
