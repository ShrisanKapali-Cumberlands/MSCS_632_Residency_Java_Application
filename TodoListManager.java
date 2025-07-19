import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TodoListManager {
    // Thread-safe collections to store users and tasks
    // Using ConcurrentHashMap for thread-safe operations
    // AtomicInteger for generating unique task IDs
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Map<Integer, Task> tasks = new ConcurrentHashMap<>();
    private final AtomicInteger taskIdCounter = new AtomicInteger(0);

    // --- User Management ---
    public User addUser(String username) {
        // Return null if user already exists, to be handled by the UI
        if (users.containsKey(username)) {
            return null;
        }
        return users.computeIfAbsent(username, User::new);
    }

    // Check if a user exists
    // This method is used to verify user existence before adding tasks
    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    // Gets all users
    // This method returns a collection of all users in the system
    public Collection<User> getUsers() {
        return users.values();
    }

    // --- Add Task ---
    public Task addTask(String description, String category, String username) {
        User user = users.get(username);
        if (user == null) {
            return null;
        }
        int taskId = taskIdCounter.incrementAndGet();
        Task task = new Task(taskId, description, category, user);
        tasks.put(taskId, task);
        return task;
    }

    // REmove Task
    public boolean removeTask(int taskId) {
        // The remove operation on ConcurrentHashMap is atomic
        return tasks.remove(taskId) != null;
    }

    // Mark Task as Complete
    // This method marks a task as completed if it is currently pending
    public boolean markTaskAsComplete(int taskId) {
        Task task = tasks.get(taskId);
        if (task != null) {
            synchronized (task) {
                if (task.getStatus() == Task.Status.PENDING) {
                    task.setStatus(Task.Status.COMPLETED);
                    return true;
                }
            }
        }
        return false;
    }

    // GEt All Tasks
    public List<Task> getAllTasks() {
        return List.copyOf(tasks.values());
    }

    // Get Tasks by User
    public List<Task> getTasksByUser(String username) {
        return tasks.values().stream()
                .filter(task -> task.getAssignedTo().getUsername().equals(username))
                .collect(Collectors.toList());
    }
}