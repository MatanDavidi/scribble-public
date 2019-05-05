/*
 * The MIT License
 *
 * Copyright 2019 SAMT.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package samt.scribble.client.game;


import java.awt.Point;

/**
 * Classe che setta pixel e ritorna il valore di una matrice in Byte.
 * @author ThorDublin
 * @author nemastojanovic
 * @version 1.0 (2019-05-05 - 2019-05-05)
 */
public class ScribbleGame {
    
    /**
     * Array multidimensionale che definisce la matrice.
     */
    private byte[][] matrix;
    
    /**
     * Definisce l'altezza della matrice.
     */
    private int height;
    
    /**
     * Definisce la larghezza della matrice.
     */
    private int width;
    
    /**
     * Costante che definisce il valore da settare.
     */
    public static final byte SET = 1;
    
    /**
     * Costante che definisce il valore del reset.
     */
    public static final byte RESET = 0;
    
    /**
     * Metodo costruttore con i parametri che definiscono la grandezza della matrice.
     * @param height definisce l'altezza della matrice.
     * @param width definisce la larghezza della matrice.
     */
    public ScribbleGame(int height, int width){
        setProperties(height, width);
        setMatrix();
    }
    
    /**
     * Metodo che setta un pixel della matrice.
     */
    private void setPixel(byte value, Point position){
        matrix[position.x][position.y] = value;
    }
    
    /**
     * Metodo che setta più punti della matrice.
     */
    private void setPixels(byte[] value, Point[] positions){
        for(int i = 0; i < positions.length; i++){
            matrix[positions[i].x][positions[i].y] = value[i];
        }
    }
    
    /**
     * Metodo che ritorna un array di Byte.
     * @return array di byte.
     */
    private Byte[] BitsToByte(byte[][] matrix){
        Byte[] bytes = new Byte[height];
        
        String b = "";
        for(int h = 0; h < height; h++){
            for(int w = 0; w < width; w++){
                b += matrix[height][width];
            }
            bytes[h] = Byte.valueOf(b);
            b = "";
        }
                
                
        return bytes;
    }
    
    /**
     * Metodo che setta le proprietà della matrice.
     * @param height definisce l'altezza della matrice.
     * @param width definisce la larghezza della matrice.
     */
    private void setProperties(int height, int width){
        if(height < 0){
            height = 0;
        }
        if(width < 0){
            width = 0;
        }
        
        this.height = height;
        this.width = width;
    }
    
    /**
     * Metodo che setta le proprietà alla matrice.
     */
    private void resetMatrix(){
        matrix = new byte[height][width];
        for(int h = 0; h < height; h++){
            for(int w = 0; w < width; w++){
                matrix[h][w] = RESET;
            }
        }
    }
    
}

