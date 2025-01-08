package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            String address = "127.0.0.1";
            int port = 23456;
            Socket socket = new Socket(InetAddress.getByName(address), port);
            System.out.println("Client started!");

            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            String messageToSend = "Give me a record # 12";
            output.writeUTF(messageToSend);
            System.out.println("Sent: " + messageToSend);

            String responseFromServer = input.readUTF();
            System.out.println("Received: " + responseFromServer);

            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
