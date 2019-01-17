/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor.editor;

import com.apu.graphicseditor.shapes.ImageShape;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author apu
 */
@NoArgsConstructor
public class EditorModel {
    
    private static EditorModel model;
    
    @Getter @Setter
    private Stage stage;
    
    @Getter @Setter
    private ImageShape imageShape;
    
    public static EditorModel getInstance() {
        if(model == null)
            model = new EditorModel();
        return model;
    }
    
}
