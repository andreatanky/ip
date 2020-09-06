package duke;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents taskList containing tasks.
 */
public class TaskList {

    /** ArrayList of tasks */
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new instance of TaskList with an arraylist of tasks.
     * @param tasks ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks cannot be null";
        this.tasks = tasks;
    }

    /**
     * Adds tasks to TaskList.
     * @param task ArrayList of tasks.
     */
    public void addTask(Task task) {
        assert task != null : "Task cannot be null";
        this.tasks.add(task);
    }

    /**
     * Returns an Arraylist of tasks with keyword.
     * @param userInput User input as string.
     * @return Arraylist of tasks with keyword.
     */
    public ArrayList<Task> findTasks(String userInput) {
        String keyword = userInput.substring(5);
        ArrayList<Task> findings;
        findings = tasks.stream().filter(x -> x.description
                .contains(keyword)).collect(Collectors.toCollection(ArrayList::new));
        return findings;
    }

    /**
     * Returns number of tasks in task list.
     * @return Number of tasks in task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns arraylist of tasks.
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
}
