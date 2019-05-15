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
package samt.scribble.server.modules;

import java.io.IOException;
import java.net.DatagramPacket;

/**
 * Lettura di una parola e controllo del tentativo, poi si controllano anche
 * quanti giocatori devono ancora indovinare.
 *
 * @author PaoloGuebeli
 * @version 1.0 (2019-05-08)
 */
public class DatagramConverter {
    /**
     * Metodo che converte il pacchetto contenente il messaggio in una stringa.
     * @param packet messaggio da convertire.
     * @return pacchetto convertito in stringa.
     * @throws IOException che gestisce un eventuale eccezione nella lettura del pacchetto.
     */
    public static String dataToString(DatagramPacket packet) throws IOException {
        String text = "";
        for (int i = 1; i < packet.getData().length; i++) {
            if (packet.getData()[i] != 0) {
                text += (char) packet.getData()[i];
            }
        }
        return text;
    }

}
