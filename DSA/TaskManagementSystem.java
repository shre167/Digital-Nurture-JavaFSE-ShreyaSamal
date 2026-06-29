class Task {
    private final int taskId;
    private final String taskName;
    private final String status;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    @Override
    public String toString() {
        return String.format("Task{id=%d, name=%s, status=%s}", taskId, taskName, status);
    }
}

class TaskNode {
    Task task;
    TaskNode next;

    TaskNode(Task task) {
        this.task = task;
    }
}

public class TaskManagementSystem {
    private TaskNode head;

    public void addTask(Task task) {
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
            return;
        }
        TaskNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public Task searchTask(int taskId) {
        TaskNode current = head;
        while (current != null) {
            if (current.task.getTaskId() == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    public void traverseTasks() {
        TaskNode current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    public boolean deleteTask(int taskId) {
        if (head == null) {
            return false;
        }
        if (head.task.getTaskId() == taskId) {
            head = head.next;
            return true;
        }
        TaskNode current = head;
        while (current.next != null) {
            if (current.next.task.getTaskId() == taskId) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public static void main(String[] args) {
        TaskManagementSystem manager = new TaskManagementSystem();
        manager.addTask(new Task(1, "Design UI", "In Progress"));
        manager.addTask(new Task(2, "Write tests", "Pending"));
        manager.addTask(new Task(3, "Deploy", "Pending"));

        System.out.println("All tasks:");
        manager.traverseTasks();

        System.out.println("\nSearch task 2:");
        System.out.println(manager.searchTask(2));

        manager.deleteTask(1);
        System.out.println("\nAfter deleting task 1:");
        manager.traverseTasks();
    }
}
