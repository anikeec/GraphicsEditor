package com.apu.graphicseditor.research.lab2;

import com.apu.graphicseditor.research.connectivity.Pixel;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class UpdateImage {
    private Image image;
    private int width;
    private int height;
    private final int PERIMETR = 200;

    public UpdateImage(Image image) {
        this.image = image;
        this.width = new Double(image.getWidth()).intValue();
        this.height = new Double(image.getHeight()).intValue();
    }
    
    public Image update() {

        int[][] matrix = getMonoMatrixFromImage(image,width ,height );
        int[][] labels = new int[height][width];
        labeling(matrix, labels, width, height);

        int[] pixels = matrixToArray(labels);
 
        Map<Integer, Integer> perimeterOfObjects = getPerimeter(labels);
       
        for (int i = 0; i < pixels.length; i++) {
            int objectIndex = pixels[i];
            if (objectIndex == 0) {
                pixels[i] = 0;
                continue;
            }
            int perimetr = perimeterOfObjects.get(objectIndex);
            if (perimetr < PERIMETR) {
            	pixels[i] = Pixel.BLACK;
            } else {
            	pixels[i] = Pixel.WHITE;                                            //.getRGB();
            }
        }
        Image img = getImageFromArray(pixels, width, height);

        return img;
    }

    private Image getImageFromArray(int[] pixels, int width, int height) {
        WritableImage dest = new WritableImage(width, height);
        PixelWriter writer = dest.getPixelWriter();
        for(int y=0; y<height; y++) {                                               //change height and width
            for(int x=0; x<width; x++) {
                writer.setArgb(x, y, pixels[x*y]);                
            }
        }
        return dest;    
//        BufferedImage pixelImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        pixelImage.setRGB(0, 0, width, height, pixels, 0, width);
//        return pixelImage;
    }
    private int[][] getMonoMatrixFromImage(Image image, int width, int height) {
        int[][] matrix = new int[height][width];
        
        PixelReader reader = image.getPixelReader();
        for(int y=0; y<height; y++) {                                               //change height and width
            for(int x=0; x<width; x++) {
                matrix[y][x] = reader.getArgb(x, y);
            }
        }        
        
//        BufferedImage bufferedImage = (BufferedImage)image;
//        for(int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                int pixel = bufferedImage.getRGB(j, i) == -1 ? 1 : 0;
//                matrix[i][j] = pixel;
//            }
//        }
        return matrix;
    }

    private void labeling(int[][] img, int[][] labels, int width, int height) {
        int L = 1;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                fill(img, labels, x, y, L++);
            }
        }   
    }
    
    private void fill(int[][] img, int[][] labels, int x, int y, int L) {
        if (labels[y][x] == 0 && img[y][x] == 1) {
            labels[y][x] = L;
            if (x > 0) {
                fill(img, labels, x - 1, y, L);
            }
            if (x < width - 1) {
                fill(img, labels, x + 1, y, L);
            }
            if (y > 0) {
                fill(img, labels, x, y - 1, L);
            }
            if (y < height -1) {
                fill(img, labels, x, y + 1, L);
            }       
        }
    }

    private int[] matrixToArray(int[][] matrix) {
        int[] array = new int[width * height];
        int index = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                array[index++] = matrix[i][j];
            }
        }       return array;
    }
    private Map<Integer, Integer> getPerimeter(int[][] matrix){
    	Map<Integer, Integer> map = new HashMap<>();
    	int[]yarray= new int[]{-1,1,0,0	};
    	int[]xarray= new int[]{0,0,-1,1	};
    	for(int i=0; i<height;i++){
            for(int j=0; j<width;j++){
                if(matrix[i][j]!=0){
                    for(int a=0;a<yarray.length;a++){
                        try {
                            if (matrix[i + yarray[a]][j + xarray[a]] == 0) {    							
                                int pixel = matrix[i][j];
                                if (!map.containsKey(pixel)) {
                                    map.put(pixel, 1);
                                } else {
                                    Integer value = map.get(pixel);
                                    map.put(pixel, ++value);
                                }
                                break;
                            }
                        } catch (Exception e) {
                            continue;
                        }    			
                    }    			
                }    		
            }
    	}		
        return map;
    }
    
}
