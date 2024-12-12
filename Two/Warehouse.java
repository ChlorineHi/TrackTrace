import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class Warehouse {
    private static final String LOG_FILE = "warehouse.log"; // 日志文件名
    private ConcurrentLinkedQueue<Package> highPriorityQueue;
    private ConcurrentLinkedQueue<Package> regularPriorityQueue;
    private Lock lock;

    public Warehouse() {
        highPriorityQueue = new ConcurrentLinkedQueue<>();
        regularPriorityQueue = new ConcurrentLinkedQueue<>();
        lock = new ReentrantLock();

        // 定时任务，每分钟检查常规队列中的包裹
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                promoteRegularPackages();
            }
        }, 0, 60000); // 每60秒执行一次
    }

    // 存储包裹
    public void storePackage(Package pkg) {
        lock.lock();
        try {
            if (pkg.isHighPriority()) {
                highPriorityQueue.offer(pkg);
            } else {
                regularPriorityQueue.offer(pkg);
            }
        } finally {
            lock.unlock();
        }
    }

    // 检索包裹
    public Package retrievePackage() {
        lock.lock();
        try {
            Package pkg = highPriorityQueue.poll();
            if (pkg == null) {
                pkg = regularPriorityQueue.poll();
            }
            return pkg;
        } finally {
            lock.unlock();
        }
    }

    // 提升常规包裹优先级的方法
    private void promoteRegularPackages() {
        lock.lock();
        try {
            for (Package pkg : regularPriorityQueue) {
                if (LocalDateTime.now().minusMinutes(10).isAfter(pkg.getCreationTime())) {
                    pkg.promoteToHighPriority();
                    highPriorityQueue.offer(pkg);
                    regularPriorityQueue.remove(pkg);
                    logOperation("提升: " + pkg.getId() + " 为高优先级");
                }
            }
        } finally {
            lock.unlock();
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
