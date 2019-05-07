/*
 * The MIT License
 *
 * Copyright 2019 Matan Davidi.
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
package samt.scribble.client.lobby;

import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.List;
import samt.scribble.DefaultScribbleParameters;
import samt.scribble.communication.Commands;
import samt.scribble.communication.Connection;
import samt.scribble.communication.DatagramListener;

/**
 *
 * @author Matan Davidi
 */
public class LobbyPanel extends javax.swing.JPanel implements DatagramListener {

    private Connection serverConnection;

    private List<String> usernames;

    private final String idleText = "In attesa di altri giocatori";

    /**
     * Creates new form LobbyPanel
     *
     * @param serverConnection La connessione al server sotto forma di istanza
     * di {@link samt.scribble.communication.Connection Connection}.
     */
    public LobbyPanel(Connection serverConnection) {
        initComponents();
        this.serverConnection = serverConnection;
        serverConnection.getGroupConnection().addDatagramListener(this);
        serverConnection.getListeningThread().addDatagramListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usersListScrollPane = new javax.swing.JScrollPane();
        usersListTextArea = new javax.swing.JTextArea();
        idleLabel = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        usersListTextArea.setEditable(false);
        usersListTextArea.setColumns(20);
        usersListTextArea.setRows(5);
        usersListScrollPane.setViewportView(usersListTextArea);

        add(usersListScrollPane, java.awt.BorderLayout.LINE_END);

        idleLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        idleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        idleLabel.setText("In attesa di altri giocatori...");
        add(idleLabel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel idleLabel;
    private javax.swing.JScrollPane usersListScrollPane;
    private javax.swing.JTextArea usersListTextArea;
    // End of variables declaration//GEN-END:variables

    @Override
    public void messageReceived(DatagramPacket datagramPacket) {

        byte[] packetData = datagramPacket.getData();

        byte[] message = new byte[packetData.length - 1];

        for (int i = 0; i < message.length; ++i) {

            message[i] = packetData[i + 1];

        }

        switch (packetData[0]) {

            case Commands.USERS_LIST:
                usernames = rebuildUsernames(message);
                updateUsersListTextArea();
                break;

            case Commands.START:
                break;

        }

    }

    private List<String> rebuildUsernames(byte[] packetData) {

        List<String> usernames = new ArrayList<>();

        StringBuilder username = new StringBuilder();

        for (int i = 0; i < packetData.length; ++i) {

            if (packetData[i] == DefaultScribbleParameters.USERS_LIST_SEPARATOR) {

                usernames.add(username.toString());
                username.setLength(0);

            } else {

                username.append((char) packetData[i]);

            }

        }

        return usernames;

    }

    private void updateUsersListTextArea() {

        usersListTextArea.setText("");
        for (String username : usernames) {

            usersListTextArea.append(username);

        }
        repaint();

    }

}