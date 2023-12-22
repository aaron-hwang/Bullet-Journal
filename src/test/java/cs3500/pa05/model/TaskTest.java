package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TaskTest {
  Task task;
  @BeforeEach
  public void setup() {
    task = new Task(
        "test", "Test", DayOfWeek.SUNDAY,"none", false);
  }

  @Test
  void getName() {
    assertEquals(task.getName(), "test");
  }

  @Test
  void isComplete() {
    assertFalse(task.isComplete());
  }

  @Test
  void getDescription() {
    assertEquals(task.getDescription(), "Test");
  }

  @Test
  void getDayOfWeek() {
    assertEquals(task.getDayOfWeek(), DayOfWeek.SUNDAY);
  }

  @Test
  void getCategory() {
    assertEquals(task.getCategory(), "none");
  }

  @Test
  void copyOver() {
    task.copyOver(
        new Task("hi", "hi", DayOfWeek.SUNDAY, "none", false));
    assertEquals(task.getName(), "hi");
    assertEquals(task.getCategory(), "none");
    assertEquals(task.getDescription(), "hi");
    assertEquals(task.getDayOfWeek(), DayOfWeek.SUNDAY);
  }

}