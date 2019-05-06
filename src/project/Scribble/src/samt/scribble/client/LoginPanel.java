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
package samt.scribble.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import javax.swing.JOptionPane;
import samt.scribble.DefaultScribbleParameters;
import samt.scribble.communication.Commands;
import samt.scribble.communication.Connection;
import samt.scribble.communication.DatagramListener;
import samt.scribble.communication.GroupConnection;
import samt.scribble.communication.ListeningThread;
import samt.scribble.communication.MessageSender;
import samt.scribble.communication.messages.JoinMessage;

/**
 * La classe LoginPanel è una sottoclasse di JPanel e contiene i componenti
 * utili per accedere al server.
 *
 * TODO: Fare in modo che l'istanza riceva pacchetti tramite il ListeningThread.
 *
 * @author MatanDavidi
 * @version 1.1 (2019-05-04 - 2019-05-06)
 */
public class LoginPanel extends javax.swing.JPanel implements DatagramListener {

    /**
     * Il LoginListener che deve ricevere gli eventi relativi al login sollevati
     * da quest'istanza di LoginPanel (vedi
     * {@link samt.scribble.client.LoginListener# LoginListener}).
     */
    private LoginListener listener;

    /**
     * Il nome utente del giocatore con cui si vuole accedere al server.
     */
    private String username;

    /**
     * Il thread di ascolto che permette di ricevere dei pacchetti UDP.
     */
    private ListeningThread listeningThread;

    /**
     * Creates new form LoginPanel
     */
    public LoginPanel() {
        initComponents();
        listener = null;
    }

    /**
     * Imposta un valore al campo LoginListener. Da adesso fino a quando questo
     * oggetto non venga distrutto o si disassoci quel LoginListener riceverà
     * gli eventi descritti all'interno dell'interfaccia
     * {@link samt.scribble.client.LoginListener# LoginListener}.
     *
     * @param listener Il LoginListener da assegnare a ques'istanza di
     * LoginPanel. Esso deve ricevere gli eventi relativi al login sollevati da
     * quest'istanza di LoginPanel.
     */
    public void setListener(LoginListener listener) {
        this.listener = listener;
    }

    /**
     * Annulla l'associazione al LoginListener. Da adesso fino alla prossima
     * chiamata del metodo
     * {@link samt.scribble.client.LoginPanel#setListener setListener} nessuna
     * LoginListener riceverà gli eventi sollevati da quest'istanza di
     * LoginPanel.
     */
    public void unsetListener() {
        this.listener = null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usernamePanel = new javax.swing.JPanel();
        usernameLabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        loginButton = new javax.swing.JButton();

        setLayout(new java.awt.GridLayout(2, 1));

        usernamePanel.setLayout(new java.awt.GridLayout());

        usernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernameLabel.setText("Inserisci il tuo nome utente:");
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTextField);

        add(usernamePanel);

        loginButton.setText("Accedi");
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginButtonMouseClicked(evt);
            }
        });
        add(loginButton);
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseClicked
        String username = usernameTextField.getText();

        if (!username.isEmpty()) {

            JoinMessage joinMessage = new JoinMessage(username);
            this.username = username;

            try {
                
                MessageSender.sendMessage(
                        InetAddress.getByName(DefaultScribbleParameters.SERVER_ADDRESS),
                        DefaultScribbleParameters.DEFAULT_SERVER_PORT,
                        joinMessage
                );
                listeningThread = new ListeningThread(DefaultScribbleParameters.DEFAULT_CLIENT_PORT);
                listeningThread.addDatagramListener(this);
                listeningThread.start();

            } catch (IOException ex) {

                JOptionPane.showMessageDialog(this, ex.getMessage());

            }

        } else {

            JOptionPane.showMessageDialog(this, "Inserire un nome utente all'interno del relativo campo.");

        }
    }//GEN-LAST:event_loginButtonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JPanel usernamePanel;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void messageReceived(DatagramPacket datagramPacket) {

        byte[] packetData = datagramPacket.getData();

        if (packetData.length > 0) {

            byte[] messageBytes = new byte[packetData.length - 1];

            for (int i = 1; i < packetData.length; ++i) {

                messageBytes[i - 1] = packetData[i];

            }

            if (packetData[0] == Commands.GROUP_ADDRESS_MESSAGE) {

                try {
                    
                    if (username != null && listener != null) {
                        
                        GroupConnection groupConnection = new GroupConnection(InetAddress.getByAddress(messageBytes), DefaultScribbleParameters.DEFAULT_GROUP_PORT);
                        MessageSender sender = new MessageSender();
                        listeningThread.interrupt();
                        
                        listener.loggedIn(username, new Connection(groupConnection, listeningThread, sender));
                        
                    }

                } catch (IOException ex) {

                    JOptionPane.showMessageDialog(this, ex.getMessage());

                }

            }

            JOptionPane.showMessageDialog(this, new String(messageBytes));

        }

    }
}
