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

/**
 * Representation of a player.
 *
 * @author mattiaruberto
 * @author gabrialessi
 * @version 1.1 (10.04.2019)
 */
public class Player {

    /**
     * Username of the player. Default value: "UNKNOWN".
     */
    private String username = "UNKNOWN";

    /**
     * Total score of the player. Default value: 0.
     */
    private int score = 0;

    /**
     * Constructor method where the username is defined.
     *
     * @param username Username of the player.
     */
    public Player(String username) {
        setUsername(username);
    }

    /**
     * Constructor method where the username and the total score are defined.
     *
     * @param username Username of the player.
     * @param score Total score of the player.
     */
    public Player(String username, int score) {
        this(username);
        setScore(score);
    }

    /**
     * Get the username of the player.
     *
     * @return Username of the player.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Get the total score of the player.
     *
     * @return Total score of the player.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Set the username of the player.
     *
     * @param username Username of the player.
     */
    private void setUsername(String username) {
        this.username = username;
    }

    /**
     * Set the total score of the player.
     *
     * @param score Total score of the player.
     */
    private void setScore(int score) {
        if (score < 0) {
            score = 0;
        }
        this.score = score;
    }

}
