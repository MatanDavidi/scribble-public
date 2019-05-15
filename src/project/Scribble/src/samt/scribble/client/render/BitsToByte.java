/*
 * The MIT License
 *
 * Copyright 2019 giuliobosco.
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
package samt.scribble.client.render;

/**
 * Convertitore da bit a byte.
 *
 * @author giuliobosco
 * @author jarinaeser
 * @version 1.1 (2019-04-26 - 2019-04-26)
 */
public class BitsToByte {

    /**
     * Converte 8 bit in un byte.
     *
     * @param bits 8 bit da convertire.
     * @return 8 bit convertiti in un byte.
     */
    private static byte getByte(boolean[] bits) {
        if (bits.length == 8) {
            int bitesSum = 0;
            for (int i = bits.length - 1; i >= 0; i--) {
                bitesSum += Math.pow(2, (bits.length - i - 1)) * (bits[i] ? 1 : 0);
            }
            return (byte) bitesSum;
        } else {
            throw new IllegalArgumentException("Bits must have at least 8 elements.");
        }
    }

    /**
     * Converte una matrice di bit (il cui numero totale di celle è multiplo di
     * 8) in un array di byte.
     *
     * @param matrix Matrice da convertire in bytes.
     * @return Bytes della matrice.
     */
    public static byte[] getBytes(boolean[][] matrix) {
        if (matrix.length > 0) {
            int bytesCounter = 0;
            byte[] bytes = new byte[matrix.length * matrix[0].length / 8];

            int bitsCounter = 0;
            boolean[] bits = new boolean[8];

            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    bits[bitsCounter] = matrix[i][j];
                    bitsCounter++;
                    if (bitsCounter == bits.length) {
                        bytes[bytesCounter] = getByte(bits);
                        bitsCounter = 0;
                        bytesCounter++;
                    }
                }
            }

            return bytes;
        }

        throw new IllegalArgumentException();
    }

    /**
     * Converte un byte in 8 bits (array di booleani).
     *
     * @param b Byte da convertire in bits.
     * @return Bits convertiti dal byte.
     */
    private static boolean[] getBits(byte b) {
        int n = 0x000000FF & b;

        boolean[] bits = new boolean[8];
        int bitsCounter = bits.length - 1;

        while (n > 0) {
            bits[bitsCounter] = n % 2 == 1;
            n /= 2;
            bitsCounter--;
        }

        while (bitsCounter >= 0) {
            bits[bitsCounter] = false;
            bitsCounter--;
        }

        return bits;
    }

    /**
     * Converte un array di byte in una matrice di booleani.
     *
     * @param bytes Bytes da convertire.
     * @param rows Righe della matrice
     * @param cols Colonne della matrice.
     * @return La matrice di booleani.
     */
    public static boolean[][] getMatrix(byte[] bytes, int rows, int cols) {
        boolean[][] matrix = new boolean[rows][cols];

        int x = 0, y = 0;

        for (int k = 0; k < bytes.length; k++) {
            boolean[] b = getBits(bytes[k]);
            for (int i = 0; i < b.length; i++) {
                matrix[x][y] = b[i];

                y++;
                if (y >= cols) {
                    y = 0;
                    x++;
                    if (x > rows) {
                        return matrix;
                    }
                }
            }
        }

        return matrix;
    }

    /**
     * Metodo main della classe usato per testare i metodi.
     *
     * @param args Argomenti da linea di comando.
     */
    public static void main(String[] args) {

        // test del metodo getByte(bits)
        // risultato apettato: -127
        boolean[] bits = {true, false, false, false, false, false, false, false};
        System.out.println(getByte(bits));

        System.out.println();

        // test del metodo getBytes(matrix)
        // risultato aspettato 1, 2, 3
        boolean[][] matrix = {
            {false, false, false, false, false, false, false, true, false, false, false, false},
            {false, false, true, false, false, false, false, false, false, false, true, true}
        };
        byte[] bytes = getBytes(matrix);
        for (byte b : bytes) {
            System.out.println(b);
        }

        System.out.println();

        // test del metodo getBits(byte)
        // risultato attesto true, false, false, false, false, false, false, false
        bits = getBits((byte) -128);
        for (int i = 0; i < bits.length; i++) {
            System.out.println(bits[i]);
        }

        System.out.println();

        // test del metodo getMatrix(bytes, rows, cols)
        // risultato atteso:
        // false false false false false false false true false false false false
        // false false true false false false false false false false true true
        matrix = getMatrix(bytes, 2, 12);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
