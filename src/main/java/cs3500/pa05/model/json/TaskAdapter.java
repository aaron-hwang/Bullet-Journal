package cs3500.pa05.model.json;

import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapts tasks to json and vice versa
 */
public class TaskAdapter {

  /**
   * Adapt a list of taskJson to a list of task objects
   *
   * @param taskJsons The list to adapt
   * @return a new list of tasks
   */
  public static List<Task> jsonListToTasks(List<TaskJson> taskJsons) {
    List<Task> tasks = new ArrayList<>();
    for (TaskJson taskJson : taskJsons) {
      tasks.add(TaskAdapter.jsonToTask(taskJson));
    }

    return tasks;
  }

  /**
   * Adapt a task json to a task object
   *
   * @param taskJson The json to adapt
   * @return The corresponding task
   */
  public static Task jsonToTask(TaskJson taskJson) {
    return new Task(taskJson.name(),
        taskJson.description(),
        DayOfWeek.valueOf(taskJson.dayOfWeek()),
        taskJson.category(),
        taskJson.complete());
  }

  /**
   * Adapt a list of tasks to a list of task jsons
   *
   * @param taskList the list to adapt
   * @return a list of TaskJson
   */
  public static List<TaskJson> tasksToJson(List<Task> taskList) {
    List<TaskJson> toReturn = new ArrayList<>();
    for (Task t : taskList) {
      TaskJson json = TaskAdapter.oneTaskToJson(t);
      toReturn.add(json);
    }

    return toReturn;
  }

  /**
   * Convert a signular task to a TaskJson
   *
   * @param t The task we are converting
   * @return The corresponding taskJson
   */
  public static TaskJson oneTaskToJson(Task t) {
    //Snooze
    return new TaskJson(
        t.getName(),
        t.getDescription(),
        t.getDayOfWeek().toString(),
        t.isComplete(),
        t.getCategory());
  }


}
