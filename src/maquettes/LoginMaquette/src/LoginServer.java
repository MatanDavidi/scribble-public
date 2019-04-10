
import java.util.List;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Matan Davidi
 */
public class LoginServer extends Thread {

    private DatagramSocket socket;

    private List<String> users;

    private List<ChatListener> chatListeners;

    public LoginServer() throws SocketException {

        users = new ArrayList<>();
        chatListeners = new ArrayList<>();

        int port = -1;

        while (port == -1) {

            try {

                port = Integer.parseInt(JOptionPane.showInputDialog("Enter port on which to listen to:"));
                socket = new DatagramSocket(port);
                System.out.println("Listening on port " + port);

            } catch (NumberFormatException nfe) {

            }

        }

    }
    
    public boolean addChatListener(ChatListener listener) {
        
        return chatListeners.add(listener);
        
    }

    private boolean updateUsers(String newUser) {

        boolean updated = false;

        if (!users.contains(newUser)) {

            users.add(newUser);
            updated = true;

        }

        return updated;

    }

    @Override
    public void run() {

        while (true) {

            byte[] buffer = new byte[256];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            try {

                socket.receive(packet);

                String newUser = new String(packet.getData());

                if (updateUsers(newUser)) {

                    for (ChatListener chatListener : chatListeners) {

                        chatListener.appendToChat("New user logged in: " + newUser);

                    }

                }

            } catch (IOException ioe) {

                System.out.println(ioe.getMessage());

            }

        }

    }

    public static void main(String[] args) {

        try {

            (new LoginServer()).start();

        } catch (SocketException se) {

            System.out.println(se.getMessage());

        }

    }

}
