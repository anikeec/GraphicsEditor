/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor.editor;

import com.apu.graphicseditor.JavaFxOutAdapter;
import com.apu.graphicseditor.OutputInterface;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import com.apu.graphicseditor.shapes.ColorRGB;
import com.apu.graphicseditor.shapes.Point;
import com.apu.graphicseditor.shapes.Shape;
import com.apu.graphicseditor.shapes.Sheet;

/**
 *
 * @author apu
 */
public class GraphicsEditor {

    public final ColorRGB DEFAULT_BORDER_COLOR = new ColorRGB(Color.BLUE);
    public final ColorRGB DEFAULT_FILL_COLOR = new ColorRGB(Color.GREEN);
    
    private static GraphicsEditor editor;
    private List<Sheet> sheets;
    private int currentSheet;
    private ColorRGB borderColor;
    private ColorRGB fillColor;    
    private OutputInterface outputInterface;
    
    private GraphicsEditor() {
        borderColor = DEFAULT_BORDER_COLOR;
        fillColor = DEFAULT_FILL_COLOR;
        Sheet temp = new Sheet(0, 0);
        temp.setBackgroundColor(new ColorRGB(Color.WHITE));
        sheets = new ArrayList<Sheet>();                // buffer for all sheets
        sheets.add(temp);
        currentSheet = 0;  
        outputInterface = JavaFxOutAdapter.getInstance();
    }

    public static GraphicsEditor getInstance() {
        if(editor == null) {
            editor = new GraphicsEditor();
        }
        return editor;
    }

    public Sheet getCurrentSheet() {
        return sheets.get(currentSheet);
    }

    public ColorRGB getBorderColor() {
        return borderColor;
    }

    public ColorRGB getFillColor() {
        return fillColor;
    }

    public OutputInterface getOutputInterface() {
        return outputInterface;
    }
    
    public int getSheetsCount(){
        return sheets.size();
    } 

    public void setBorderColor(ColorRGB borderColor) {
        this.borderColor = borderColor;
    }
    
    public void setCurrentSheet(int number) {
        if(number > 0) {  
            currentSheet = number - 1;
        }
    }

    public void setFillColor(ColorRGB fillColor) {
        this.fillColor = fillColor;
    }

    public void setOutputInterface(OutputInterface outputInterface) {
        this.outputInterface = outputInterface;
    }
    
    public void moveSelectedFigures(Point point) {
        getCurrentSheet().moveSelectedFigures(point);            
    }
   
}
