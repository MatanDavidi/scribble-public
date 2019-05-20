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
package samt.scribble.client.login;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import javax.swing.JOptionPane;
import samt.scribble.DebugVerbosity;
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
 * @author MatanDavidi
 * @version 1.1 (2019-05-04 - 2019-05-06)
 */
public class LoginPanel extends javax.swing.JPanel implements DatagramListener {

    /**
     * Il LoginListener che deve ricevere gli eventi relativi al login sollevati
     * da quest'istanza di LoginPanel (vedi
     * {@link samt.scribble.client.LoginListener LoginListener}).
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
     * {@link samt.scribble.client.LoginListener LoginListener}.
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
        formPanel = new javax.swing.JPanel();
        usernameLabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        errorLabel = new javax.swing.JLabel();
        loginButtonPanel = new javax.swing.JPanel();
        loginButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        usernamePanel.setBackground(new java.awt.Color(255, 255, 255));
        usernamePanel.setLayout(new java.awt.GridLayout(3, 1));

        formPanel.setBackground(new java.awt.Color(255, 255, 255));
        formPanel.setLayout(new java.awt.GridLayout(1, 0));

        usernameLabel.setBackground(new java.awt.Color(255, 255, 255));
        usernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernameLabel.setText("Inserisci il tuo nome utente:");
        formPanel.add(usernameLabel);

        usernamePanel.add(formPanel);

        usernameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usernamePanel.add(usernameTextField);

        errorLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        errorLabel.setForeground(new java.awt.Color(255, 0, 51));
        errorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernamePanel.add(errorLabel);

        add(usernamePanel, java.awt.BorderLayout.CENTER);

        loginButtonPanel.setBackground(new java.awt.Color(255, 255, 255));

        loginButton.setText("Accedi");
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginButtonMouseClicked(evt);
            }
        });
        loginButtonPanel.add(loginButton);

        add(loginButtonPanel, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginButtonMouseClicked

        String username = usernameTextField.getText().trim();
        if (!username.isEmpty()) {

            if (username.matches(DefaultScribbleParameters.USERNAME_REGEX)) {
                this.username = username;

                try {
                    if (listeningThread == null) {
                        listeningThread = new ListeningThread(0);
                        listeningThread.addDatagramListener(this);
                        listeningThread.start();
                    }

                    JoinMessage joinMessage = new JoinMessage(username, listeningThread.getPort());

                    MessageSender.sendMessage(
                            InetAddress.getByName(DefaultScribbleParameters.SERVER_ADDRESS),
                            DefaultScribbleParameters.DEFAULT_SERVER_PORT,
                            joinMessage
                    );

                    if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.INFORMATION) {
                        System.out.println("Inviata richiesta di accesso.");
                    }

                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            } else {
                errorLabel.setText("<html><body>Il nome utente specificato contiene"
                        + "<br> caratteri non validi.</body></html>");
            }
        } else {
            errorLabel.setText("<html><body>Inserire un nome utente <br> "
                    + "all'interno del relativo campo.</body></html>");
        }
    }//GEN-LAST:event_loginButtonMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel errorLabel;
    private javax.swing.JPanel formPanel;
    private javax.swing.JButton loginButton;
    private javax.swing.JPanel loginButtonPanel;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JPanel usernamePanel;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void messageReceived(DatagramPacket datagramPacket) {

        if (DefaultScribbleParameters.DEBUG_VERBOSITY >= DebugVerbosity.INFORMATION) {

            System.out.println(this.getClass().getName() + ": ricevuto pacchetto UDP.");

        }

        byte[] packetData = datagramPacket.getData();

        if (packetData.length > 0) {

            byte[] messageBytes;

            if (packetData[0] == Commands.GROUP_ADDRESS_MESSAGE) {

                messageBytes = new byte[4];

            } else {

                messageBytes = new byte[datagramPacket.getLength() - 1];

            }

            for (int i = 0; i < messageBytes.length; ++i) {

                messageBytes[i] = packetData[i + 1];

            }

            if (packetData[0] == Commands.GROUP_ADDRESS_MESSAGE) {

                try {

                    if (username != null && listener != null) {

                        GroupConnection groupConnection = new GroupConnection(InetAddress.getByAddress(messageBytes), DefaultScribbleParameters.DEFAULT_GROUP_PORT);
                        MessageSender sender = new MessageSender();

                        listener.loggedIn(username, new Connection(groupConnection, listeningThread, sender));

                    }

                } catch (IOException ex) {

                    JOptionPane.showMessageDialog(this, ex.getMessage());

                }

            }

        }

    }
}
