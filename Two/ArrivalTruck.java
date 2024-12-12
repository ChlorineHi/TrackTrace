import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class ArrivalTruck extends Thread {
    private Warehouse warehouse;
    private Random random;
    private static final String LOG_FILE = "PackageLog.txt";

    public ArrivalTruck(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            String packageId = "包裹-" + random.nextInt(1000);
            boolean isHighPriority = random.nextBoolean();
            Package pkg = new Package(packageId, isHighPriority);
            warehouse.storePackage(pkg);
            logOperation("存储: " + packageId + (isHighPriority ? " (高优先级)" : " (常规优先级)"));

            // 添加搞笑情节
            if (random.nextInt(10) < 2) { // 20%的概率
                logOperation("搞笑事件: 包裹 " + packageId + " 被人偷了!");
            }

            // 添加更多搞笑情节
            if (random.nextInt(10) < 2) { // 20%的概率
                logOperation("搞笑事件: 包裹 " + packageId + " 被仓库的猫偷走了!");
            }

            // 在存储包裹后添加写作业的烦恼
            if (random.nextInt(10) < 3) { // 30%的概率
                logOperation("搞笑事件: 卡车司机一边运送包裹，一边还得写Java作业，真是忙不过来了!");
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
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
