/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor;

import com.apu.graphicseditor.editor.BtnType;
import com.apu.graphicseditor.editor.ColorType;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 *
 * @author apu
 */
interface InAdapterInterface {

    void onChangeColor(ColorType colorType, Color color);  
    
    void onClickButton(BtnType btn);

    void onKeyboardPressButton(KeyCode key);

    void onKeyboardReleaseButton(KeyCode key);

    void onMouseDraggPrymary(double x, double y);

    void onMousePressPrimary(double x, double y);

    void onMouseReleasePrymary(double x, double y);
    
}
