/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apu.graphicseditor.research.convolution;

/**
 *
 * @author apu
 */
public class Filter {
    
    public static Matrix identityFilter(){
        double[][] filter = {{0,0,0},
                             {0,1,0},
                             {0,0,0}};

        return new Matrix(filter);
    }

    public static Matrix blurFilter(){
        Matrix m = new Matrix(3,3);
        m.reset(1.0/9.0);
        return m;
    }

    public static Matrix negativeFilter(){
        double[][] filter = {{-0.5,0,0},
                             {0,-0.5,0},
                             {0,0,-0.5}};

        return new Matrix(filter);
    }

    public static Matrix testFilter(){
        double[][] filter = {{0,0,0},
                             {0,2,0},
                             {0,0,0}};

        return new Matrix(filter);
    }
    
    public static Matrix highPassConvolutionFilter() {
        double[][] filter = {{1,1,1},
                             {1,-8,1},
                             {1,1,1}};

        return new Matrix(filter);
    }
    
    public static Matrix deleteSmallFilter() {
        double[][] filter = {{1,1,1},
                             {1,0,1},
                             {1,1,1}};

        return new Matrix(filter);
    }
    
    public static Matrix blackWhiteFilter() {
        double[][] filter = {{0.2125,0.7154,0.0721},
                             {0.2125,0.7154,0.0721},
                             {0.2125,0.7154,0.0721}};

        return new Matrix(filter);
    }
    
}
