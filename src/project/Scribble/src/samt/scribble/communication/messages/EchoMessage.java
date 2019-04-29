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
 
package samt.scribble.communication.messages;

import samt.scribble.communication.Commands;

/**
 * Comando echo, ritorna al mittente il messaggio.
 *
 * @author giuliobosco (giuliobva@gmail.com)
 * @version 1.0 (2019-04-19)
 */
public class EchoMessage extends Message {

    /**
     * Create echo message.
     *
     * @param text EchoMessage message.
     */
    public EchoMessage(String text) {
        super(Commands.ECHO, text.getBytes());
    }

    /**
     * Testare la classe il messaggio echo e la classe Message.
     *
     * @param args Argomenti da linea di comando.
     */
    public static void main(String[] args) {
        String message = "echo";
        EchoMessage echo = new EchoMessage(message);
        byte[] bytes = echo.getWholeMessage();

        String rebuild = "";
        for (int i = 1; i < bytes.length; i++) {
            rebuild += (char) bytes[i];
        }

        assert message.equals(rebuild);
        System.out.println(message.equals(rebuild));
    }
    
}
