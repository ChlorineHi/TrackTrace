import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WarehouseManager {
    private static final int NUM_ARRIVAL_TRUCKS = 3; // 到达卡车数量
    private static final int NUM_DEPARTURE_TRUCKS = 2; // 离开卡车数量

    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_ARRIVAL_TRUCKS + NUM_DEPARTURE_TRUCKS);

        // 启动到达卡车线程
        for (int i = 0; i < NUM_ARRIVAL_TRUCKS; i++) {
            executorService.submit(new ArrivalTruck(warehouse));
        }

        // 启动离开卡车线程
        for (int i = 0; i < NUM_DEPARTURE_TRUCKS; i++) {
            executorService.submit(new DepartureTruck(warehouse));
        }

        // 关闭线程池
        executorService.shutdown();
        try {
            // 等待所有任务完成
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
