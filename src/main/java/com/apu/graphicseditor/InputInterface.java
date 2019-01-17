/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor;

import com.apu.graphicseditor.editor.BtnType;
import com.apu.graphicseditor.editor.ColorType;
import com.apu.graphicseditor.editor.KeyType;
import com.apu.graphicseditor.shapes.ColorRGB;
import com.apu.graphicseditor.shapes.Point;

/**
 *
 * @author apu
 */
public interface InputInterface {

    void onChangeColor(ColorType colorType, ColorRGB color);
    
    void onGuiPressButton(BtnType button);

    void onKeyboardPressButton(KeyType button);

    void onKeyboardReleaseButton(KeyType button);

    void onMouseDraggPrymary(Point point);

    void onMousePressPrymary(Point point);

    void onMouseReleasePrymary(Point point);
    
}
