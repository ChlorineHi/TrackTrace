// Train.java

public class Train extends Transport {
    private int numberOfCarriages;
    private String fuelType; // e.g., "electric" or "diesel"

    public Train(String vehicleID, int capacity, String route, String status, int numberOfCarriages, String fuelType) {
        super(vehicleID, capacity, route, status);
        this.numberOfCarriages = numberOfCarriages;
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return super.toString() + " [numberOfCarriages=" + numberOfCarriages + ", fuelType=" + fuelType + "]";
    }
}
