// UDPClient.java
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // Create a DatagramSocket
            socket = new DatagramSocket();
            // Prepare the message
            String message = "Update request: Activate vehicleID-1234";
            byte[] buffer = message.getBytes();
            // Get the server address
            InetAddress serverAddress = InetAddress.getByName("localhost");
            // Create a DatagramPacket
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, 8686);
            // Send the packet
            socket.send(packet);
            System.out.println("Message sent: " + message);
        } catch (SocketException e) {
            System.err.println("Socket error: " + e.getMessage());
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
