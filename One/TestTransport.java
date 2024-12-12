// TestTransport.java

public class TestTransport {
    public static void main(String[] args) {
        // Create instances of each class
        Transport[] transports = new Transport[4];
        transports[0] = new Bus("B001", 50, "Route A", "active", true);
        transports[1] = new Train("T001", 200, "Route B", "active", 10, "electric");
        transports[2] = new Metro("M001", 100, "Route C", "inactive", true);
        transports[3] = new Bus("B002", 60, "Route D", "inactive", false);

        // Iterate through the array and print details
        for (Transport transport : transports) {
            System.out.println(transport);
            if (transport instanceof WiFiEnabled) {
                System.out.println("WiFi available: " + ((WiFiEnabled) transport).isWiFiAvailable());
            }
        }
    }
}
