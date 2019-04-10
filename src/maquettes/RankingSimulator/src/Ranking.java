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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Players ranking management.
 *
 * @author mattiaruberto
 * @author gabrialessi
 * @version 1.1 (10.04.2019)
 */
public class Ranking {

    /**
     * Default path of the ranking csv file.
     */
    public static final Path CSV_PATH = Paths.get("data", "ranking.csv");

    /**
     * Path of the ranking csv file.
     */
    private Path csvPath = CSV_PATH;

    /**
     * List of players.
     */
    private List<Player> players = new ArrayList<>();

    /**
     * Constructor method where the path of the csv file is defined.
     *
     * @param csvPath Path of the csv file.
     */
    public Ranking(Path csvPath) {
        setCsvPath(csvPath);
    }

    /**
     * Get the path of the csv file.
     *
     * @return Path of the csv file.
     */
    public Path getCsvPath() {
        return this.csvPath;
    }

    /**
     * Set the path of the csv file.
     *
     * @param csvPath Path of the csv file.
     */
    private void setCsvPath(Path csvPath) {
        this.csvPath = csvPath;
    }

    /**
     * Creation of the csv file.
     */
    private void createCsv() throws IOException {
        // Get the file.
        File csvFile = new File(getCsvPath().toString());
        // Create the file if not exists.
        if (csvFile.createNewFile()) {
            System.out.println("File created.");
        } else {
            System.out.println("File not created: already exists.");
        }
        // Write ranking csv header.
        FileWriter writer = new FileWriter(csvFile);
        writer.write("username,score");
        writer.close();
    }

    private void rank() {

    }

    /**
     * Main method.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Path csvPath = Paths.get("data", "ranking.csv");
        Ranking ranking = new Ranking(csvPath);
        try {
            ranking.createCsv();
        } catch (IOException ex) {
            System.out.println("Error creating file.");
        }
    }

}
