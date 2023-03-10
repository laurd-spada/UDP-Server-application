package application;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Main {
    public static void main(String[] args) {
        try{
            DatagramSocket datagramSocket = new DatagramSocket(5000);
            while (true){
                byte[] buffer = new byte[50];
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(datagramPacket);
                System.out.println("Text received is: " + new String(buffer, 0, datagramPacket.getLength()));

                String returnString = "echo: " + new String(buffer, 0, datagramPacket.getLength());
                byte[] buffer1 = returnString.getBytes();
                InetAddress inetAddress = datagramPacket.getAddress();
                int port = datagramPacket.getPort();
                datagramPacket = new DatagramPacket(buffer1, buffer1.length, inetAddress, port);
                datagramSocket.send(datagramPacket);
            }
        } catch (SocketException e){
            System.out.println("SocketException: " + e.getMessage());
        } catch (IOException e){
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
