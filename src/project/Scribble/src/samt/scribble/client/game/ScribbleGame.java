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
 * Classe che definisce i valori dell'interfaccia di gioco (matrice dei punti).
 *
 * @author ThorDublin
 * @author nemastojanovic
 * @author MattiaRuberto
 * @author gabrialessi
 * @author giuliobosco
 * @version 1.0.3 (2019-05-05 - 2019-05-08)
 */
public class ScribbleGame {

    /**
     * Matrice per i punti da disegnare nell'interfaccia.
     */
    private boolean[][] matrix;

    /**
     * Altezza della matrice.
     */
    private int height;

    /**
     * Larghezza della matrice.
     */
    private int width;

    /**
     * Metodo costruttore, si definiscono le grandezze della matrice.
     *
     * @param height Altezza della matrice.
     * @param width Larghezza della matrice.
     */
    public ScribbleGame(int height, int width) {
        setSize(height, width);
        resetMatrix();
    }

    /**
     * Metodo utile per ottenere l'altezza della matrice.
     *
     * @return L'altezza della matrice.
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Metodo utile per ottenere la larghezza della matrice.
     *
     * @return La larghezza della matrice.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Ritorna il valore del campo
     * {@link samt.scribble.client.game.ScribbleGame#matrix matrix}.
     *
     * @return La matrice per i punti da disegnare nell'interfaccia.
     */
    public boolean[][] getMatrix() {
        return matrix;
    }

    /**
     * Metodo che imposta un pixel della matrice.
     *
     * @param position Il punto da impostare.
     */
    public void setPixel(Point position) {
        int x = (int) position.getX();
        int y = (int) position.getY();
        if (x >= 0 && x <= getWidth() && y >= 0 && y <= getWidth()) {
            this.matrix[x][y] = true;
        }
    }

    /**
     * Metodo che imposta più pixel della matrice.
     *
     * @param positions I punti da impostare.
     */
    public void setPixels(Point[] positions) {
        for (int i = 0; i < positions.length; i++) {
            this.matrix[(int) positions[i].getX()][(int) positions[i].getY()] = true;
        }
    }

    /**
     * Metodo che imposta le grandezze della matrice.
     *
     * @param height Altezza della matrice.
     * @param width Larghezza della matrice.
     */
    private void setSize(int height, int width) {
        if (height < 0) {
            height = 0;
        }
        if (width < 0) {
            width = 0;
        }
        this.height = height;
        this.width = width;
    }

    /**
     * Metodo che azzera le proprietà alla matrice.
     */
    public void resetMatrix() {
        this.matrix = new boolean[getHeight()][getWidth()];
        for (int h = 0; h < getHeight(); h++) {
            for (int w = 0; w < getWidth(); w++) {
                this.matrix[h][w] = false;
            }
        }
    }

}
