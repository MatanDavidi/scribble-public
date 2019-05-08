/*
 * The MIT License
 *
 * Copyright 2019 Asus.
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
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import javax.swing.JOptionPane;
import samt.scribble.DefaultScribbleParameters;
import samt.scribble.client.game.PlayerRole;
import samt.scribble.communication.Commands;
import samt.scribble.communication.Connection;
import samt.scribble.communication.DatagramListener;
import samt.scribble.communication.MessageSender;
import samt.scribble.communication.messages.WordGuessMessage;

/**
 * Classe che gestisce le parole indoviate e le invia.
 *
 * @author MattiaRuberto
 * @author MatanDavidi
 * @version 1.0.1 (2019-05-08 - 2019-05-08)
 */
public class GamePanel extends javax.swing.JPanel implements DatagramListener {

    /**
     * Attributo che rappresenta la connessione al server.
     */
    private Connection serverConnection;
    /**
     * Attributo che rappresenta il pannello di scribble.
     */
    private ScribblePanel scribblePanel;

    public GamePanel(Connection connection, PlayerRole playerRole) {
        initComponents();
        this.serverConnection = connection;
        serverConnection.addDatagramListener(this);
        scribblePanel = new ScribblePanel(connection, playerRole);
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

        jTextFieldWord = new javax.swing.JTextField();
        jButtonSendWord = new javax.swing.JButton();

        setLayout(new java.awt.GridLayout(2, 0));
        add(jTextFieldWord);

        jButtonSendWord.setText("Send Word");
        jButtonSendWord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonSendWordMouseClicked(evt);
            }
        });
        add(jButtonSendWord);
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Metodo che parte quando l'utente schiaccia il bottone per inviare al
     * server la parola da indovinare.
     *
     * @param evt Attributo che rappresenta le informazioni del bottone.
     */
    private void jButtonSendWordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSendWordMouseClicked
        String wordToGuess = jTextFieldWord.getText().trim();
        if (!wordToGuess.isEmpty()) {

            WordGuessMessage wordGuessMessage = new WordGuessMessage(wordToGuess);

            try {
                MessageSender.sendMessage(
                        InetAddress.getByName(DefaultScribbleParameters.SERVER_ADDRESS),
                        DefaultScribbleParameters.DEFAULT_SERVER_PORT,
                        wordGuessMessage
                );

            } catch (IOException ex) {

                JOptionPane.showMessageDialog(this, ex.getMessage());

            }

        } else {

            JOptionPane.showMessageDialog(this, "Inserire la parola");

        }
    }//GEN-LAST:event_jButtonSendWordMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSendWord;
    private javax.swing.JTextField jTextFieldWord;
    // End of variables declaration//GEN-END:variables

    @Override
    public void messageReceived(DatagramPacket datagramPacket) {

        byte[] bytes = datagramPacket.getData();

        switch (bytes[0]) {

            case Commands.WORD_GUESS:
                JOptionPane.showMessageDialog(this, "Hai indovinato la parola!!!");
                break;

        }

    }
}
