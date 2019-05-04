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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utile per la gestione di un file di testo e fare i controlli.
 *
 * @author bryanbeffa
 * @author matteoforni
 * @author mattiaruberto
 * @author gabrialessi
 * @version 2019-05-01
 */
public class FileManager {

    /**
     * Costante che contiene il percorso di default del file.
     */
    public static final Path DEFAULT_FILEPATH = Paths.get("data", "file.txt");

    /**
     * Attributo che rappresenta il percorso del file csv.
     */
    private Path filePath = DEFAULT_FILEPATH;

    /**
     * Metodo costruttore dove si definisce il percorso del file.
     *
     * @param filePath Percorso del file.
     * @throws java.io.IOException Se si verifica un'eccezione di input o di
     * output.
     */
    public FileManager(Path filePath) throws IOException {
        setFilePath(filePath);
    }

    /**
     * Metodo che imposta il percorso del file.
     *
     * @param filePath Percorso del file.
     */
    private void setFilePath(Path filePath) throws IOException {
        if (Files.exists(filePath) && !Files.notExists(filePath)) {
            if (Files.isReadable(filePath)) {
                this.filePath = filePath;
            } else {
                throw new IOException("File non leggibile!");
            }
        } else {
            throw new IOException("File non accessibile!");
        }
    }

    /**
     * Metodo utile per ottenere il percorso del file.
     *
     * @return Il percorso del file.
     */
    public Path getFilePath() {
        return this.filePath;
    }

    /**
     * Lettura del contenuto del file.
     *
     * @return La lista con il contenuto del file.
     */
    public List<String> readFile() throws IOException {
        List<String> lines = Files.readAllLines(getFilePath());
        return lines;
    }
}
