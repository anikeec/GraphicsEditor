/*
 * 
 * 
 */
package com.apu.graphicseditor.shapes;

import com.apu.graphicseditor.OutputInterface;
import javafx.scene.paint.Color;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author al
 */
@XmlTransient
public abstract class Shape extends Point implements ShapeInterface{

    @XmlElement(required = true)
    protected ColorRGB color = new ColorRGB(Color.BLUE);
    @XmlElement(required = true)
    protected ColorRGB fill;
    @XmlElement(required = true)
    protected ColorRGB colorBeforeHighlight;
    @XmlElement(required = true)
    protected int lineWidth = 1;
    @XmlElement(required = true)
    protected Integer groupId = Sheet.NO_GROUP_ID;
    @XmlTransient
    protected double angle = 0;
    @XmlElement(required = true)
    protected boolean filled = false;
    @XmlTransient
    protected boolean highlight = false;
    @XmlTransient
    protected boolean selected = false;
    @XmlTransient
    protected static final ColorRGB HIGHLIGHT_COLOR = 
            new ColorRGB(Color.YELLOW);

    public void setColor(ColorRGB c) {
        color = c;
    }

    public void setFill(ColorRGB c) {
        fill = c;
    }
    
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    
    public void setHighlight(boolean highlight) {
        if(this.highlight == highlight) return;
        this.highlight = highlight;
        if(highlight == true) {
            colorBeforeHighlight = new ColorRGB(color.getRed(), 
                                            color.getGreen(), 
                                            color.getBlue(), 
                                            color.getOpacity());
            setColor(HIGHLIGHT_COLOR); 
        }
        else {
            setColor(colorBeforeHighlight);
        }        
    }

    public void setLineWidth(int lw) {
        this.lineWidth = lw;
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public Integer getGroupId() {
        return groupId;
    }
    
    // return outer rectangle's minimal point(Xmin,Ymin) 
    Point getMinPoint() {
        throw new UnsupportedOperationException("Not supported. It's base object!!!");
    }
   
    // return outer rectangle's maximal point(Xmax,Ymax)
    Point getMaxPoint() {
        throw new UnsupportedOperationException("Not supported. It's base object!!!");
    } 
    
    public boolean isFilled() {
        return filled;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public boolean isSelected() {
        return selected;
    }
    
    @Override
    public void show(OutputInterface output){
        //applyColors();
        draw(output);
    }
    
    @Override
    public void erase(OutputInterface output) {
        throw new UnsupportedOperationException("Not supported. It's base object!!!");
    }
    
    public boolean containCursor(Cursor cursor, boolean filledFlagNoUsed) {
        throw new UnsupportedOperationException("Not supported. It's base object!!!");
    }
    
}
