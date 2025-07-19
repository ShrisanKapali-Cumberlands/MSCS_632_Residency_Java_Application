import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TodoListManager {

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

    public boolean userExists(String username) {
        return users.containsKey(username);
    }

    public Collection<User> getUsers() {
        return users.values();
    }


    // --- Task Management ---
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

    public boolean removeTask(int taskId) {
        // The remove operation on ConcurrentHashMap is atomic
        return tasks.remove(taskId) != null;
    }

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

    public List<Task> getAllTasks() {
        return List.copyOf(tasks.values());
    }

    public List<Task> getTasksByUser(String username) {
        return tasks.values().stream()
                .filter(task -> task.getAssignedTo().getUsername().equals(username))
                .collect(Collectors.toList());
    }
}