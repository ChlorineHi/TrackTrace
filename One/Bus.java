// Bus.java

public class Bus extends Transport implements WiFiEnabled {
    private boolean hasWiFi;

    public Bus(String vehicleID, int capacity, String route, String status, boolean hasWiFi) {
        super(vehicleID, capacity, route, status);
        this.hasWiFi = hasWiFi;
    }

    @Override
    public boolean isWiFiAvailable() {
        return hasWiFi;
    }

    @Override
    public String toString() {
        return super.toString() + " [hasWiFi=" + hasWiFi + "]";
    }
}
