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
package samt.scribble.client;

import java.awt.BorderLayout;
import samt.scribble.client.game.PlayerRole;
import samt.scribble.communication.Connection;

/**
 * La classe GamePanel è una sottoclasse di JPanel che contiene la grafica e la
 * logica del gioco.
 *
 * @author Matan Davidi
 * @version 1.0 (2019-05-08 - 2019-05-08)
 */
public class GamePanel extends javax.swing.JPanel {

    private ScribblePanel scribblePanel;

    /**
     * Creates new form GamePanel
     *
     * @param connection
     * @param playerRole
     */
    public GamePanel(Connection connection, PlayerRole playerRole) {
        initComponents();
        scribblePanel = new ScribblePanel(connection, PlayerRole.Drawer);
        add(scribblePanel, BorderLayout.WEST);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        wordGuessPanel = new javax.swing.JPanel();
        wordGuessTextField = new javax.swing.JTextField();
        sendWordGuessButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        wordGuessPanel.setLayout(new java.awt.GridLayout(2, 1));
        wordGuessPanel.add(wordGuessTextField);

        sendWordGuessButton.setText("Invia");
        wordGuessPanel.add(sendWordGuessButton);

        add(wordGuessPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton sendWordGuessButton;
    private javax.swing.JPanel wordGuessPanel;
    private javax.swing.JTextField wordGuessTextField;
    // End of variables declaration//GEN-END:variables
}
