package duke;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Represents user interface class.
 */
public class Ui {
    /** Bot's reply prefix */
    private static final String bot = "Dave says:";

    /** Scanner object */
    private final Scanner scanner;

    /**
     * Constructs a new Ui object.
     */
    Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns greetings from bot.
     * @return Greetings.
     */
    public String displayGreetings() {
        return "Greetings from me, Dave!\n" + "How can I help you? ^_^\n";
    }

    /**
     * Returns prefix of reply.
     * @return String of prefix of reply.
     */
    public static String getBot() {
        return Ui.bot;
    }

    /**
     * Returns bye text from bot.
     * @return Bye text.
     */
    public String displayBye() {
        return "Goodbye! Hope to see you again soon! ^_^";
        //System.exit(0);
    }

    /**
     * Returns tasks in task list.
     * @param tasks TaskList containing tasks.
     * @return Task list.
     */
    public String displayTaskList(TaskList tasks) {
        if (tasks.getTasks().isEmpty()) {
            return "There are no tasks in your list yet! >_< \n";
        } else {
            StringBuilder list = new StringBuilder();
            list.append("Here are the tasks in your list:\n");
            IntStream.range(0, tasks.size()).forEach(i ->
                    list.append(String.format("%d. %s\n", i + 1, tasks.getTasks().get(i))));
            return list.toString();
        }
    }

    /**
     * Returns scheduled tasks.
     * @param scheduledTasks TaskList containing scheduled tasks.
     * @return Scheduled Tasks.
     */
    public String displayScheduledTasks(ArrayList<Task> scheduledTasks) {
        if (scheduledTasks.isEmpty()) {
            return "There are no tasks scheduled for this date! >_<";
        } else {
            StringBuilder list = new StringBuilder();
            list.append("Here are the tasks scheduled for this date:\n");
            IntStream.range(0, scheduledTasks.size()).forEach(i ->
                    list.append(String.format("%d. %s\n", i + 1, scheduledTasks
                    .get(i))));
            return list.toString();
        }
    }

    /**
     * Returns task to be deleted and displayed text when deleted.
     * @param userInput User input.
     * @param taskList User's tasks.
     * @return Text when a task is deleted.
     * @throws DukeException When an invalid input is entered.
     */
    public String displayDelete(String userInput, TaskList taskList) throws DukeException {
        if (!userInput.substring(6).isBlank()) {
            try {
                String toDelete = userInput.substring(7);
                int index = Integer.parseInt(toDelete);
                if (index <= taskList.size() && index > 0) {
                    String deleted = taskList.getTasks().get(index - 1).toString();
                    taskList.getTasks().remove(index - 1);
                    return "Noted! I've deleted this task:\n" + deleted
                            + "\nNow you have " + taskList.size()
                            + " tasks in the list.";
                } else {
                    throw new IndexOutOfBoundsException();
                }
            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                throw new DukeException("The number keyed in is invalid!");
            }
        } else {
            throw new DukeException("The description of a delete cannot be empty!");
        }
    }

    /**
     * Returns and marks task as done.
     * @param tasks Arraylist of tasks.
     * @param pos Index in arraylist.
     * @throws DukeException When index < 0 or index > tasks.size().
     */
    public String displayDone(ArrayList<Task> tasks, int pos) throws DukeException {
        if (pos <= tasks.size() && pos > 0) {
            tasks.get(pos - 1).markAsDone(); //marking task as done
            return "Great work! I've marked this task as done:\n"
                    + tasks.get(pos - 1).toString()
                    + "\nKeep the ticks going! ^_^";
        } else {
            throw new DukeException("You have keyed in an invalid number!");
        }
    }

    /**
     * Prints tasks with the keyword.
     * @param findings Arraylist of tasks with the keyword.
     * @return Tasks that contains keyword.
     * @throws DukeException When findings is empty.
     */
    public String displayFindings(ArrayList<Task> findings) throws DukeException {
        StringBuilder list = new StringBuilder();
        if (findings.isEmpty()) {
            throw new DukeException("There are no such tasks with this keyword! :(");
        } else {
            list.append("These are the tasks with your keyword:\n");
            int i = 0;
            for (Task task : findings) {
                list.append(String.format("%d.%s\n", i + 1, task));
                i++;
            }
        }
        return list.toString();
    }

    /**
     * Returns text when ToDo task is added.
     * @param todoTask Task to be done.
     * @param tasks TaskList to add task to.
     * @return Text when todo task is added.
     */
    public String displayAddTodo(ToDo todoTask, TaskList tasks) {
        return "Got it! I've added this task:\n" + todoTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Returns text when Event task is added.
     * @param eventTask Event task.
     * @param tasks TaskList to add task to.
     * @return Text when event task is added.
     */
    public String displayAddEvent(Event eventTask, TaskList tasks) {
        return "Got it! I've added this task:\n" + eventTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Returns text when Deadline task is added.
     * @param deadlineTask Deadline task.
     * @param tasks TaskList to add task to.
     * @return Text when deadline task is added.
     */
    public String displayAddDeadline(Deadline deadlineTask, TaskList tasks) {
        return "Got it! I've added this task:\n" + deadlineTask.toString()
            + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Returns user inputs.
     * @return User input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Checks if there is more user input.
     * @return True if there is more input, if not false.
     */
    public boolean hasMoreInput() {
        return scanner.hasNext();
    }

    /**
     * Prints loading of tasks error.
     */
    public void showLoadingError() {
        System.out.println("I am unable to load your tasks! D:");
    }
}
