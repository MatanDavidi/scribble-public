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
package samt.scribble;

/**
 * La classe DebugVerbosity contiene le costanti utili a definire il livello di
 * verbosità delle informazioni di debug in modo da poter scegliere che tipo di
 * informazioni stampare attraverso l'esecuzione del programma.
 *
 * @author MatanDavidi
 * @version 1.0.1 (2019-05-04 - 2019-05-06)
 */
public class DebugVerbosity {

    /**
     * Costante che dice che deve mostrare tutti gli errori.
     */
    public static final byte RELEASE = 0;
    /**
     * Costante che dice che deve mostrare gli avvisi e gli errori.
     */
    public static final byte ERRORS = 1;
    /**
     * Costante che dice che deve mostrare solo gli errori.
     */
    public static final byte WARNINGS = 2;
    /**
     * Costante che dice che deve mostrare solo le informazioni.
     */
    public static final byte INFORMATION = 3;

}
