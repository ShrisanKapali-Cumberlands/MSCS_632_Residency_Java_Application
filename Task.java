import java.util.Objects;

public class Task {

    public enum Status {
        PENDING,
        COMPLETED
    }

    private final int taskId;
    private final String description;
    private final String category;
    private Status status;
    private final User assignedTo;

    public Task(int taskId, String description, String category, User assignedTo) {
        this.taskId = taskId;
        this.description = Objects.requireNonNull(description, "Description cannot be null");
        this.category = Objects.requireNonNull(category, "Category cannot be null");
        this.assignedTo = Objects.requireNonNull(assignedTo, "Task must be assigned to a user");
        this.status = Status.PENDING;
    }

    public int getTaskId() { return taskId; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
    public Status getStatus() { return status; }
    public User getAssignedTo() { return assignedTo; }

    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("Task[ID=%d, Desc='%s', Cat='%s', Status=%s, User=%s]",
                taskId, description, category, status, assignedTo.getUsername());
    }
}