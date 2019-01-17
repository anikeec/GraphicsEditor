/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor.states;

import com.apu.graphicseditor.editor.BtnType;
import com.apu.graphicseditor.editor.EditorModel;
import com.apu.graphicseditor.editor.KeyType;
import com.apu.graphicseditor.editor.MouseKeyType;
import com.apu.graphicseditor.shapes.ImageShape;
import com.apu.graphicseditor.shapes.Point;
import com.apu.graphicseditor.shapes.Rectangle;
import com.apu.graphicseditor.shapes.Shape;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 *
 * @author apu
 */
public class StateInsertImage extends StateBase {
    
    public StateInsertImage(StateEngine stateEngine) {
        this.stateEngine = stateEngine;
    } 
    
    private Image getImageFromFileSystem() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select image file...");
        List<String> extensions = new ArrayList<>();
        extensions.add("*.jpg");
        extensions.add("*.bmp");
        extensions.add("*.png");
        ExtensionFilter filter = new FileChooser.ExtensionFilter("images", extensions);
        fileChooser.getExtensionFilters().add(filter);
        fileChooser.setSelectedExtensionFilter(filter);
        File file = fileChooser.showOpenDialog(
                                EditorModel.getInstance().getStage());
        Image image = null;
        if (file != null) {
            try {
                image = new Image(new FileInputStream(file));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StateInsertImage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return image;
    }

    @Override
    public void onMousePressPrymary(Point point) {
        if(!stateEngine.graphicsEditor.getCurrentSheet().isInSheetBorder(point))   return;
        
        Image image = this.getImageFromFileSystem();
        if(image == null) 
            return;
        ImageShape imageShape = new ImageShape();
        imageShape.setImage(image);
        imageShape.setWidth(image.getWidth());
        imageShape.setHeight(image.getHeight());
        imageShape.onMousePressButton(MouseKeyType.PRYMARY, point);
        imageShape.setColor(stateEngine.graphicsEditor.getBorderColor());
        imageShape.setFill(stateEngine.graphicsEditor.getFillColor());
        stateEngine.graphicsEditor.getCurrentSheet().setTempShape(imageShape);
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
        stateEngine.graphicsEditor.getCurrentSheet().addShape(shape);
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
