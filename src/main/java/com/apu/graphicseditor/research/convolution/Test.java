package com.apu.graphicseditor.research.convolution;

/**
 * Created by Omar on 15/09/2016.
 */
public class Test {
    public static void main (String [] arg) {
        Matrix[] image = Tools.loadImage("image.jpg");
        Matrix[] image2 = Tools.applyFilter(image, Filter.testFilter());
        Matrix[] image3 = Tools.applyFilter(image, Filter.negativeFilter());

        Tools.saveImage(image2, "image2.jpg");
        Tools.saveImage(image3, "image3.jpg");

        System.out.println("Done!");
    }

}