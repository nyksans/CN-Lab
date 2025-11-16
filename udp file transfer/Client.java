import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        DatagramSocket skt = null;
        try {
            skt = new DatagramSocket(7799);
            byte[] buffer = new byte[1000];
            System.out.println("Client ready. Waiting for server messages...");

            while (true) {
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
                skt.receive(dp);
                String msg = new String(dp.getData(), 0, dp.getLength());
                System.out.println("Server says: " + msg);
            }
        } catch (IOException e) {
            System.err.println("IOException: " + e);
        } finally {
            if (skt != null)
                skt.close();
        }
    }
}
