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
public interface State {
    public void onMousePressPrymary(Point point);
    public void onMouseDraggPrymary(Point point);
    public void onMouseReleasePrymary(Point point);
    public void onGuiPressButton(BtnType button);
    public void onKeyboardPressButton(KeyType button);
    public void onKeyboardReleaseButton(KeyType button);
}
