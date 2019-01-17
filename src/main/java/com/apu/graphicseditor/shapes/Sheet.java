/*
 * 
 * 
 */
package com.apu.graphicseditor.shapes;

import com.apu.graphicseditor.OutputInterface;
import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author al
 */
public class Sheet {
    private final double SHEET_WIDTH = 800;
    private final double SHEET_HEIGHT = 480;
    public final static int NO_GROUP_ID = 0;
    public final static int START_GROUP_ID = 1;    
    private ColorRGB colorSheet;
    private Composite figures;
    private Point lastMousePressPoint;
    private Point size;
    private Point startPoint;
    private Shape tempShape;
    private Set<Integer> usedGroupId;

    public Sheet(double x, double y) {
        figures = new Composite();
        lastMousePressPoint = new Point(0,0);
        size = new Point(SHEET_WIDTH, SHEET_HEIGHT);
        usedGroupId = new HashSet<Integer>();        
    }

    public Composite getFigures() {
        return figures;
    }
    
    public Point getLastMousePressPoint() {
        return lastMousePressPoint;
    }
    
    public Point getSheetSize() {
        return size;
    }

    public Point getStartPoint() {
        return startPoint;
    }
    
    public Shape getTempShape() {
        return tempShape;
    }    

    public void setBackgroundColor(ColorRGB colorSheet) {
        this.colorSheet = colorSheet;
    }

    public void setLastMousePressPoint(Point lastMousePressPoint) {
        this.lastMousePressPoint = lastMousePressPoint;
    }
    
    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }
    
    public void setTempShape(Shape tempShape) {
        this.tempShape = tempShape;
    } 
    
    public void addShape(Shape s){
        figures.addShape(s);
    }
    
    public void draw(OutputInterface output) {
        figures.draw(output);
    }
    
    public void erase(OutputInterface output) {
        Rectangle rect = new Rectangle();
        rect.setC(new Point(size.getX(), size.getY()));
        rect.setColor(colorSheet);
        rect.setFill(colorSheet);
        rect.setFilled(true);
        rect.draw(output);
    }
    
    public void update(OutputInterface output) {
        erase(output);
        draw(output);
    }  

    public boolean isInSheetBorder(Point point) {
        if((point.getX() > getSheetSize().getX()) ||
           (point.getY() > getSheetSize().getY()))   return false;
        if((point.getX() < 0) || (point.getY() < 0)) return false;
        return true;
    }
    
    public void moveBackwardSelected() {
        List<Shape> tempList = figures.getShapes();
        for(int i=1;i<tempList.size();i++) {
            if(tempList.get(i).isSelected()) {
                if(!tempList.get(i-1).isSelected()) {
                    Shape shape1 = tempList.get(i - 1);
                    Shape shape2 = tempList.get(i);
                    tempList.set(i, shape1);
                    tempList.set(i - 1, shape2);                    
                }
            }
        }
    }
    
    public void moveForwardSelected() {
        List<Shape> tempList = figures.getShapes();
        for(int i=tempList.size()-1;i>0;i--) {
            if(tempList.get(i-1).isSelected()) {
                if(!tempList.get(i).isSelected()) {
                    Shape shape1 = tempList.get(i - 1);
                    Shape shape2 = tempList.get(i);
                    tempList.set(i, shape1);
                    tempList.set(i - 1, shape2);                    
                }
            }
        }        
    }
    
    public void moveSelectedFigures(Point point) {    
        if(!isInSheetBorder(point)) return;        
        Point lastPoint = getLastMousePressPoint();        
        double dx = point.getX() - lastPoint.getX();
        double dy = point.getY() - lastPoint.getY();
        for(Shape figure : figures.getShapes()){
            if(figure.isSelected()) {
                    figure.move(dx, dy);
            }
        }          
        setLastMousePressPoint(point);  
    }
    
    public void saveFigures() {
        Shape shape;
        int id = createGroupId();
        
        java.io.StringWriter sw = new StringWriter();
        JAXBContext jc = null;
        Marshaller marshaller = null;        
        
        try {
        List<Shape> tempList = figures.getShapes();
            for(int i=0;i<tempList.size();i++) {
                shape = tempList.get(i);
                if(shape instanceof Line) {
                    jc = JAXBContext.newInstance(Line.class);
                } else if(shape instanceof Rectangle) {
                    jc = JAXBContext.newInstance(Rectangle.class);
                } else if(shape instanceof Circle) {
                    jc = JAXBContext.newInstance(Circle.class);
                };
                marshaller = jc.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller.marshal(shape, sw);
            };
            String way = System.getProperty("user.dir");
            FileWriter fw = new FileWriter(way + "//file.xml");
            fw.write(sw.toString());
            fw.close();
        } catch  (JAXBException ex) {
            Logger.getLogger(Sheet.class.getName() + ex.getErrorCode() + ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(Sheet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadFigures() {        
        
        JAXBContext jc = null;
        Unmarshaller unmarshaller = null;  
        java.io.StringWriter sw = new StringWriter();        
        
        try {
            String way = System.getProperty("user.dir");
            FileReader fr = new FileReader(way + "//file.xml");
            char cbuf[] = new char[1];
            
            while(fr.ready()) {
                fr.read(cbuf);
                sw.write(cbuf);
            }
            
            figures = new Composite();
            
            String str = sw.toString();
            String splitter = "<?xml";
            Integer istart = 0;
            Integer ifinish = 0;
            String substr;
            while(true) {
                istart = str.indexOf(splitter, istart);
                if(istart == -1) break;
                ifinish = str.indexOf(splitter, istart + 1);
                if(ifinish == -1) {
                    substr = str.substring(istart, str.length() - 1);
                } else {
                    substr = str.substring(istart, ifinish - 1);
                    istart = ifinish;
                }
                StringReader srd = new StringReader(substr);
                
                
                if(substr.contains("Rectangle")) {                 
                    jc = JAXBContext.newInstance(Rectangle.class);
                    unmarshaller = jc.createUnmarshaller();
                    Rectangle fig = (Rectangle) unmarshaller.unmarshal(srd);
                    figures.addShape(fig);
                } else if(substr.contains("Circle")) {
                    jc = JAXBContext.newInstance(Circle.class);
                    unmarshaller = jc.createUnmarshaller();
                    figures.addShape((Circle) unmarshaller.unmarshal(srd));
                } else if(substr.contains("Line")) {
                    jc = JAXBContext.newInstance(Line.class);
                    unmarshaller = jc.createUnmarshaller();
                    figures.addShape((Line) unmarshaller.unmarshal(srd));
                }
                
                if(ifinish == -1) break;      
            }
        } catch (Exception e) {
            
        }
        
        
        
        //JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
    }
    
    public void groupSelected() {
        Shape shape;
        int id = createGroupId();
        List<Shape> tempList = figures.getShapes();
        for(int i=0;i<tempList.size();i++) {
            shape = tempList.get(i);
            if(shape.isSelected()) {
                shape.setGroupId(id);
            }
        }
    }
    
    public void ungroupSelected() {
        Shape shape;
        List<Shape> tempList = figures.getShapes();
        for(int i=0;i<tempList.size();i++) {
            shape = tempList.get(i);
            if(shape.isSelected()) {
                if(shape.getGroupId() != NO_GROUP_ID) {
                    int tempId = shape.getGroupId();
                    renumberGroup(tempId, NO_GROUP_ID);
                }
                shape.setGroupId(NO_GROUP_ID);
            }
        }        
    }
    
    public boolean isAvailableGroupId(Integer groupId) {
        if(usedGroupId.contains(groupId))   
            return true;
        return false;
    }
    
    // create new groupId & add to base.
    public int createGroupId() {
        Integer id = START_GROUP_ID;
        while(true) {
            if(usedGroupId.contains(id)) {
                id++;
                continue;
            }
            usedGroupId.add(id);
            break;
        }
        return id;
    }
    
    public void removeGroupId(Integer groupId) {
        usedGroupId.remove(groupId);
    }
    
    private void renumberGroup(Integer oldGroupId, Integer newGroupId) {
        Shape shape;
        List<Shape> tempList = figures.getShapes();        
        for(int i=0;i<tempList.size();i++) {
            shape = tempList.get(i);
            if(shape.getGroupId().compareTo(oldGroupId) == 0) {
                shape.setGroupId(newGroupId);
            }
        }
    }
    
    public void fillFigure(Point point, ColorRGB fillColor) {
        List<Shape> list = getFigures().getShapes();
        Shape figure;
        Cursor cursor = Cursor.getInstance(point);
        for(int i=list.size(); i>0; i--) {
            figure = list.get(i-1);
            if(figure.containCursor(cursor, true)){
                figure.setFilled(true);
                figure.setFill(fillColor);
                break;
            }
        }
    }
    
    public void removeFigure(Point point) {
        selectFigure(point, false);
        removeSelected();
    }
    
    public void removeSelected() {
        List<Shape> list = getFigures().getShapes();
        for(int i=list.size(); i>0; i--) {
            if(list.get(i-1).selected)
                list.remove(i-1);
        }
    }
    
    public void unselectAllFigures() {
        for(Shape figure : getFigures().getShapes()) {
            figure.setHighlight(false);
            figure.setSelected(false);
        }
    }
    
    public void selectFigure(Point point, boolean groupSelectEnable) {
        List<Shape> list = getFigures().getShapes();
        Shape figure;
        Cursor cursor = Cursor.getInstance(point);
        
        if(!groupSelectEnable) {  // unselect all shapes
            unselectAllFigures();
        }
        for(int i=list.size(); i>0; i--) {
            figure = list.get(i-1);
            if(figure.containCursor(cursor, false)){
                Integer id = figure.getGroupId();
                if(id != NO_GROUP_ID) {
                    selectGroupedFigures(id);
                }
                figure.setHighlight(true);
                figure.setSelected(true);
                break;
            }
        }
    }
    
    private void selectGroupedFigures(Integer groupId) {
        for(Shape figure : figures.getShapes()){
            if(figure.getGroupId().compareTo(groupId) == 0) {
                figure.setHighlight(true);
                figure.setSelected(true);
            }
        }        
    }

}
