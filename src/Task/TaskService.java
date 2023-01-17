package Task;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TaskService {
    private final Map<Integer, Task> taskMap = new TreeMap<>();
    private final List<Task> removedTasks = new LinkedList<>();

    public void add(Task task) {
        taskMap.put(task.getId(), task);
    }

    public Task updateDescription(int id, String description) {
        if (!taskMap.containsKey(id) || taskMap.get(id) == null) {
            throw new TaskNotFoundException();
        }
        taskMap.get(id).setDescription(description);
        return taskMap.get(id);
    }

    public List<Task> getRemovedTasks() {
        return removedTasks;
    }

    public Task remove(int id) {
        if (!taskMap.containsKey(id) || taskMap.get(id) == null) {
            throw new TaskNotFoundException();
        }
        Task task = taskMap.get(id);
        removedTasks.add(task);
        taskMap.remove(id);
        return task;
    }

    public Map<LocalDate, LinkedList<Task>> getAllGroupByDate() {
        Map<LocalDate, LinkedList<Task>> resultMap = new TreeMap<>();
        for (Map.Entry<Integer, Task> entry : taskMap.entrySet()) {
            if (entry.getValue() != null) {
                LocalDate localDate = entry.getValue().getDateTime().toLocalDate();
                if (!resultMap.containsKey(localDate)) {
                    resultMap.put(localDate, new LinkedList<>());
                }
                resultMap.get(localDate).add(entry.getValue());
            }
        }
        return resultMap;
    }

    public Task updateTitle(int id, String title) {
        if (!taskMap.containsKey(id) || taskMap.get(id) == null) {
            throw new TaskNotFoundException();
        }
        taskMap.get(id).setTitle(title);
        return taskMap.get(id);
    }

    public List<Task> getAllByDate(LocalDate localDate) {
        List<Task> list = new LinkedList<>();
        for (Map.Entry<Integer, Task> entry : taskMap.entrySet()) {
            if (entry.getValue() != null && (entry.getValue().getDateTime().toLocalDate().equals(localDate) || entry.getValue().isRepeatable(localDate))) {
                list.add(entry.getValue());
            }
        }
        return list;
    }

    public Task getTaskById(int id) {
        return taskMap.get(id);
    }

    public int getSize() {
        return taskMap.size();
    }
}
