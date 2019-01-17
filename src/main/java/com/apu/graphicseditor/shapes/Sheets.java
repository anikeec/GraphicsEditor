/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor.shapes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apu
 */
public class Sheets {
    private static List<Shape> list;
    
    static {
        list = new ArrayList<Shape>();
    }

    public static List<Shape> getList() {
        return list;
    }
    
    
}
