import java.time.LocalDate;
import java.util.*;

public class TaskManager {
    // Keep track of tasks
    private Map<LocalDate, List<Task>> taskMap = new HashMap<>();

    public void addTask(Task task) {
        taskMap.computeIfAbsent(task.getDate(), k -> new ArrayList<>()).add(task);
    }

    public List<Task> getTasks(LocalDate date) {
        return taskMap.getOrDefault(date, new ArrayList<>());
    }

}
