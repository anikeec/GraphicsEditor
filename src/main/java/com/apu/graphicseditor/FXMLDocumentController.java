/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor;

import com.apu.graphicseditor.editor.BtnType;
import com.apu.graphicseditor.editor.ColorType;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author apu
 */
public class FXMLDocumentController implements Initializable {
    
     @FXML
    private VBox vBox;

    @FXML
    private Button cursorButton;

    @FXML
    private Button openButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button selectButton;

    @FXML
    private Button eraseButton;

    @FXML
    private Button fillButton;
    
    @FXML
    private Button upButton;

    @FXML
    private Button downButton;
    
    @FXML
    private ColorPicker borderColorPicker;

    @FXML
    private ColorPicker fillColorPicker;
    
    @FXML
    private Button groupButton;

    @FXML
    private Button ungroupButton;

    @FXML
    private Button lineButton;

    @FXML
    private Button squareButton;

    @FXML
    private Button triangleButton;

    @FXML
    private Button polygonButton;

    @FXML
    private Button circleButton;

    @FXML
    private Button pentagonButton;

    @FXML
    private Button rectangleButton;

    @FXML
    private Button rhombusButton;

    @FXML
    private Button octagonButton;

    @FXML
    private Button imageButton;

    @FXML
    private Canvas mainCanvas;

    @FXML
    private Label label;
    
    @FXML
    private Button buttonFirstResearch;
    
    @FXML
    private Button buttonSecondResearch;
    
    @FXML
    private Button buttonErode;
    
    @FXML
    private Button buttonDilate;
    
    @FXML
    private Button buttonBlackWhite;
    
    @FXML
    private Button buttonColor;
    
    private InAdapterInterface adapter;

    public GraphicsContext getGraphicsContext() {
        return mainCanvas.getGraphicsContext2D();
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Click - " + event.getSource());
        Object button = event.getSource();
        if(button == cursorButton) {
            adapter.onClickButton(BtnType.CURSOR);
        } else if(button == selectButton) {
            adapter.onClickButton(BtnType.SELECT_AREA); 
        } else if(button == eraseButton) {
            adapter.onClickButton(BtnType.REMOVE);
        } else if(button == fillButton) {
            adapter.onClickButton(BtnType.FILL);
        } else if(button == groupButton) {
            adapter.onClickButton(BtnType.GROUP);
        } else if(button == ungroupButton) {
            adapter.onClickButton(BtnType.UNGROUP);
        } else if(button == upButton) {
            adapter.onClickButton(BtnType.UP);
        } else if(button == downButton) {
            adapter.onClickButton(BtnType.DOWN);
        } else if(button == circleButton) {
            adapter.onClickButton(BtnType.CIRCLE);
        } else if(button == lineButton) {
            adapter.onClickButton(BtnType.LINE);
        } else if(button == rectangleButton) {
            adapter.onClickButton(BtnType.RECTANGLE);
        } else if(button == imageButton) {
            adapter.onClickButton(BtnType.IMAGE);
        } else if(button == saveButton) {
            adapter.onClickButton(BtnType.SAVE);
        } else if(button == openButton) {
            adapter.onClickButton(BtnType.OPEN);
        } else if(button == buttonFirstResearch) {
            adapter.onClickButton(BtnType.FIRST_RESEARCH);
        } else if(button == buttonSecondResearch) {
            adapter.onClickButton(BtnType.SECOND_RESEARCH);
        } else if(button == buttonErode) {
            adapter.onClickButton(BtnType.ERODE);
        } else if(button == buttonDilate) {
            adapter.onClickButton(BtnType.DILATE);
        } else if(button == buttonBlackWhite) {
            adapter.onClickButton(BtnType.BLACK_WHITE);
        } else if(button == buttonColor) {
            adapter.onClickButton(BtnType.COLOR);
        }
    }
    
    @FXML
    private void handleMouseAction(MouseEvent event) { 
        double x = event.getX();
        double y = event.getY();
        System.out.println(" " + x + "," + y + " - " + event.getSource());
        label.setText("X:" + x + ", Y:" + y);
        if(event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            adapter.onMousePressPrimary(x, y);
        } else if(event.getEventType() == MouseEvent.MOUSE_RELEASED) {
            adapter.onMouseReleasePrymary(x, y);
        } else if(event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            adapter.onMouseDraggPrymary(x, y);
        }
    }
    
    @FXML
    void handleKeyPress(KeyEvent event) {
        adapter.onKeyboardPressButton(event.getCode());        
    }

    @FXML
    void handleKeyRelease(KeyEvent event) {
        adapter.onKeyboardReleaseButton(event.getCode()); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mainCanvas.setFocusTraversable(true);
        borderColorPicker.setValue(Color.BLUE);
        fillColorPicker.setValue(Color.ALICEBLUE);
        adapter = new JavaFxInAdapter();
        adapter.onChangeColor(ColorType.BORDER_COLOR, borderColorPicker.getValue());
        adapter.onChangeColor(ColorType.FILL_COLOR, fillColorPicker.getValue());
        JavaFxOutAdapter.getInstance().setGraphicsContext(getGraphicsContext());
    } 
    
    @FXML
    void handleColorPicker(ActionEvent event) {
        if(event.getSource() == borderColorPicker) {
            adapter.onChangeColor(ColorType.BORDER_COLOR, borderColorPicker.getValue());
        } else if(event.getSource() == fillColorPicker) {
            adapter.onChangeColor(ColorType.FILL_COLOR, fillColorPicker.getValue());
        }
    }
    
}
