import java.util.concurrent.TimeUnit;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class DepartureTruck extends Thread {
    private Warehouse warehouse;
    private static final String LOG_FILE = "PackageLog.txt";

    public DepartureTruck(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (true) {
            Package pkg = warehouse.retrievePackage();
            if (pkg != null) {
                logOperation("发货: " + pkg.getId());
                // 添加搞笑情节
                if (Math.random() < 0.1) { // 10%的概率
                    logOperation("搞笑事件: 包裹 " + pkg.getId() + " 不想被发货，拒绝出门!");
                }
                if (Math.random() < 0.2) { // 20%的概率
                    logOperation("搞笑事件: 包裹 " + pkg.getId() + " 在发货前大哭，表示不舍!");
                }
                pkg.ship();
            } else {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private void logOperation(String message) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(LOG_FILE, true), StandardCharsets.UTF_8))) {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
