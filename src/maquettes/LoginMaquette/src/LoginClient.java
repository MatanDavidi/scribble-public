
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Matan Davidi
 */
public class LoginClient {
    
    public void sendLogin(String username, String ipAddress, String port) throws SocketException, UnknownHostException, IOException, NumberFormatException {
        
        int portInt = Integer.parseInt(port);
        sendLogin(username, ipAddress, portInt);
        
    }
    
    public void sendLogin(String username, String ipAddress, int port) throws SocketException, UnknownHostException, IOException {
        
        byte[] usernameBytes = username.getBytes();
        DatagramPacket packet = new DatagramPacket(usernameBytes, usernameBytes.length, InetAddress.getByName(ipAddress), port);
        DatagramSocket socket = new DatagramSocket();
        socket.send(packet);
        
    }
    
}
