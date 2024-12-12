// Transport.java

public class Transport {
    private String vehicleID;
    private int capacity;
    private String route;
    private String status; // e.g., "active" or "inactive"

    public Transport(String vehicleID, int capacity, String route, String status) {
        this.vehicleID = vehicleID;
        this.capacity = capacity;
        this.route = route;
        this.status = status;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [vehicleID=" + vehicleID + "]";
    }

    // Getters and Setters
    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
