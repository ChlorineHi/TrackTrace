import java.time.LocalDateTime;

public class Package {
    private String id; // 唯一标识符
    private String status; // 当前状态
    private boolean isHighPriority; // 优先级
    private LocalDateTime creationTime; // 创建时间
    private LocalDateTime lastUpdateTime; // 最后更新时间

    public Package(String id, boolean isHighPriority) {
        this.id = id;
        this.isHighPriority = isHighPriority;
        this.status = "In Storage"; // 初始状态
        this.creationTime = LocalDateTime.now();
        this.lastUpdateTime = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public boolean isHighPriority() {
        return isHighPriority;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public LocalDateTime getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void promoteToHighPriority() {
        this.isHighPriority = true;
        this.lastUpdateTime = LocalDateTime.now(); // 更新最后更新时间
    }

    public void ship() {
        this.status = "Shipped";
        this.lastUpdateTime = LocalDateTime.now(); // 更新最后更新时间
    }
}
