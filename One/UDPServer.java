// UDPServer.java
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPServer {
    private static Map<String, Transport> vehicleMap = new HashMap<>();

    public static void main(String[] args) {
        // Initialize vehicle list
        initializeVehicles();

        try (DatagramSocket socket = new DatagramSocket(8686)) {
            System.out.println("Server is listening on port 8686...");
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received message: " + message);

                // Parse the vehicle ID from the message
                String vehicleID = parseVehicleID(message);
                if (vehicleID != null) {
                    updateVehicleStatus(vehicleID);
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void initializeVehicles() {
        vehicleMap.put("vehicleID-1234", new Bus("vehicleID-1234", 50, "Route A", "inactive", true));
        // Add more vehicles as needed
    }

    private static String parseVehicleID(String message) {
        if (message.startsWith("Update request: Activate ")) {
            return message.substring(25); // Extract vehicleID
        }
        return null;
    }

    private static void updateVehicleStatus(String vehicleID) {
        Transport vehicle = vehicleMap.get(vehicleID);
        if (vehicle != null) {
            vehicle.setStatus("active");
            System.out.println("Updated vehicle: " + vehicle);
            // Start a new thread to log the update
            new Thread(() -> logVehicleUpdate(vehicleID, vehicle.getStatus())).start();
        } else {
            System.out.println("Vehicle not found: " + vehicleID);
        }
    }

    private static synchronized void logVehicleUpdate(String vehicleID, String status) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String logEntry = timestamp + ": " + vehicleID + " - " + status;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transportLogs.txt", true))) {
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
