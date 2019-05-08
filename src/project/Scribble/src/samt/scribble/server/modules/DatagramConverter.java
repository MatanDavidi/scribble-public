
/*
 * The MIT License
 *
 * Copyright 2019 guebe.
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

package samt.scribble.server.modules;

import java.io.IOException;
import java.net.DatagramPacket;


/**
 * Questo metodo legge la parola inviata e controlla se questa è corretta,
 * se lo è controlla quanti player devono ancora indovinare.
 * 
 * @author Paolo Guebeli
 * @version 1.0 (2019-05-08)
 */
public class DatagramConverter {
    
    public static String dataToString(DatagramPacket datagramPacket) throws IOException{

        String text = "";
        for (int i = 1; i < datagramPacket.getData().length; i++) {
            if (datagramPacket.getData()[i] != 0) {
                text += (char) datagramPacket.getData()[i];
            }
        }
        
        return text;
    }
    
}
