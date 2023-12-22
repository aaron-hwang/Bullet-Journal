package cs3500.pa05.json;

import static cs3500.pa05.model.DayOfWeek.SATURDAY;
import static cs3500.pa05.model.DayOfWeek.SUNDAY;
import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.TaskAdapter;
import cs3500.pa05.model.json.TaskJson;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskAdapterTest {

  Task t;
  List<Task> taskList;
  @BeforeEach
  public void setup() {
    t = new Task("test", "test", DayOfWeek.SUNDAY, "none", false);
    taskList = new ArrayList<>();
    for (DayOfWeek day : DayOfWeek.values()) {
      taskList.add(
          new Task("test" + day.ordinal(),
              "test" + day.ordinal(),
              day,
              "none",
              true));
    }
  }

  @Test
  void jsonListToTasks() {
    List<TaskJson> taskJsons = new ArrayList<>();
    for (DayOfWeek day : DayOfWeek.values()) {
      taskJsons.add(
          new TaskJson(
              "test" + day.ordinal(),
              "test", day.toString(),
              false, "12"));
    }

    List<Task> tasks = TaskAdapter.jsonListToTasks(taskJsons);
    int i = 0;
    for (Task task : tasks) {
      assertFalse(task.isComplete());
      assertEquals(task.getName(), "test" + i);
      assertEquals(task.getCategory(), "12");
      i++;
    }
  }

  @Test
  void jsonToTask() {
    TaskJson taskJson = new TaskJson(
        "name",
        "desc",
        "SATURDAY",
        false, "");
    Task task = TaskAdapter.jsonToTask(taskJson);
    assertEquals(task.getName(), "name");
    assertEquals(task.getCategory(), "");
    assertEquals(task.getDayOfWeek(), SATURDAY);
    assertFalse(task.isComplete());
    assertEquals(task.getDescription(), "desc");
  }

  @Test
  void tasksToJson() {
    List<TaskJson> taskJsons = TaskAdapter.tasksToJson(taskList);
    for (int i = 0; i < taskJsons.size(); i++) {
      TaskJson json = taskJsons.get(i);
      assertTrue(json.name().contains("test" + i));
      assertTrue(json.complete());
      assertEquals(json.category(), "none");
    }
  }

  @Test
  void oneTaskToJson() {
    TaskJson json = TaskAdapter.oneTaskToJson(t);
    assertEquals(json.name(), "test");
    assertEquals(json.description(), "test");
    assertEquals(json.dayOfWeek(), SUNDAY.toString());
  }
}