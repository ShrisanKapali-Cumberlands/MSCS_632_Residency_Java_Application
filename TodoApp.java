import java.util.List;
import java.util.Scanner;

public class TodoApp {

    private static final TodoListManager manager = new TodoListManager();
    private static final Scanner scanner = new Scanner(System.in);
    private static User currentUser = null; // Manages the currently "logged-in" user

    public static void main(String[] args) {
        while (true) {
            // If no user is selected, show the user management menu
            if (currentUser == null) {
                showUserMenu();
            } else {
                // If a user is selected, show the task management menu
                showTaskMenu();
            }
        }
    }

    // --- User Menu and Actions ---
    private static void showUserMenu() {
        System.out.println("\n--- User Menu ---");
        System.out.println("1. Select User");
        System.out.println("2. Create New User");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");

        int choice = readIntInput();

        switch (choice) {
            case 1:
                selectUser();
                break;
            case 2:
                createUser();
                break;
            case 3:
                System.out.println("Exiting application. Goodbye!");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void selectUser() {
        System.out.println("\n--- Available Users ---");
        if (manager.getUsers().isEmpty()) {
            System.out.println("No users exist. Please create one first.");
            return;
        }
        manager.getUsers().forEach(user -> System.out.println("- " + user.getUsername()));
        System.out.print("Enter username to select: ");
        String username = scanner.nextLine();
        if (manager.userExists(username)) {
            currentUser = new User(username); // Set the current user
            System.out.println("User '" + username + "' selected.");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void createUser() {
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        if (username.isBlank()) {
            System.out.println("Username cannot be empty.");
            return;
        }
        User newUser = manager.addUser(username);
        if (newUser != null) {
            currentUser = newUser; // Automatically select the new user
            System.out.println("User '" + username + "' created and selected.");
        } else {
            System.out.println("ERROR: User already exists.");
        }
    }

    // --- Task Menu and Actions ---
    private static void showTaskMenu() {
        System.out.println("\n--- Task Menu (User: " + currentUser.getUsername() + ") ---");
        System.out.println("1. Add Task");
        System.out.println("2. Remove Task");
        System.out.println("3. Mark Task as Complete");
        System.out.println("4. View My Tasks");
        System.out.println("5. View All Tasks");
        System.out.println("6. Switch User (Log Out)");
        System.out.print("Enter your choice: ");

        int choice = readIntInput();

        switch (choice) {
            case 1:
                addTask();
                break;
            case 2:
                removeTask();
                break;
            case 3:
                markTaskAsComplete();
                break;
            case 4:
                viewTasksByUser(currentUser.getUsername());
                break;
            case 5:
                viewAllTasks();
                break;
            case 6:
                currentUser = null; // Log out by setting current user to null
                System.out.println("You have been logged out.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter task category: ");
        String category = scanner.nextLine();

        Task newTask = manager.addTask(description, category, currentUser.getUsername());
        System.out.println("SUCCESS: Task '" + newTask.getDescription() + "' added with ID: " + newTask.getTaskId());
    }

    private static void removeTask() {
        System.out.print("Enter the Task ID to remove: ");
        int taskId = readIntInput();
        if (manager.removeTask(taskId)) {
            System.out.println("SUCCESS: Task " + taskId + " removed.");
        } else {
            System.out.println("ERROR: Task not found.");
        }
    }

    private static void markTaskAsComplete() {
        System.out.print("Enter the Task ID to mark as complete: ");
        int taskId = readIntInput();
        if (manager.markTaskAsComplete(taskId)) {
            System.out.println("SUCCESS: Task " + taskId + " marked as complete.");
        } else {
            System.out.println("ERROR: Task not found or already complete.");
        }
    }

    private static void viewAllTasks() {
        System.out.println("\n--- All Tasks ---");
        List<Task> tasks = manager.getAllTasks();
        printTasks(tasks);
    }

    private static void viewTasksByUser(String username) {
        System.out.println("\n--- Tasks for " + username + " ---");
        List<Task> tasks = manager.getTasksByUser(username);
        printTasks(tasks);
    }

    // --- Helper Methods ---
    private static int readIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Return an invalid choice
        }
    }

    private static void printTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        String format = "| %-4s | %-30s | %-15s | %-12s | %-15s |%n";
        System.out.format(
                "+------+--------------------------------+-----------------+--------------+-----------------+%n");
        System.out.format(format, "ID", "Description", "Category", "Status", "Assigned To");
        System.out.format(
                "+------+--------------------------------+-----------------+--------------+-----------------+%n");
        for (Task task : tasks) {
            System.out.format(format,
                    task.getTaskId(),
                    task.getDescription(),
                    task.getCategory(),
                    task.getStatus(),
                    task.getAssignedTo().getUsername());
        }
        System.out.format(
                "+------+--------------------------------+-----------------+--------------+-----------------+%n");
    }
}