// Metro.java

public class Metro extends Transport {
    private boolean isUnderground;

    public Metro(String vehicleID, int capacity, String route, String status, boolean isUnderground) {
        super(vehicleID, capacity, route, status);
        this.isUnderground = isUnderground;
    }

    @Override
    public String toString() {
        return super.toString() + " [isUnderground=" + isUnderground + "]";
    }
}
