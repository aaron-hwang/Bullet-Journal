package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DayTest {
  Day d;
  @BeforeEach
  public void setup() {
    d = new Day(DayOfWeek.SUNDAY, new ArrayList<>(), new ArrayList<>());
  }

  @Test
  void getTasks() {
    assertEquals(d.getTasks(), new ArrayList<>());
  }

  @Test
  void getEvents() {
    assertEquals(d.getEvents(), new ArrayList<>());
  }

  @Test
  void getDayOfWeek() {
    assertEquals(d.getDayOfWeek(), DayOfWeek.SUNDAY);
  }

  @Test
  void addTask() {
    d.addTask("name", "test", DayOfWeek.SUNDAY, "none");
    assertEquals(d.getTasks().size(), 1);
    assertInstanceOf(Task.class, d.getTasks().get(0));
  }

  @Test
  void addEvent() {
    d.addEvent(
        "name",
        "Test",
        DayOfWeek.SUNDAY,
        "12:00",
        "12 minutes");
    assertEquals(d.getEvents().size(), 1);
    assertInstanceOf(Event.class, d.getEvents().get(0));
    assertEquals(d.getEvents().get(0).getDayOfWeek(), DayOfWeek.SUNDAY);
  }

  @Test
  void getCompletedTasks() {
    d.addTask("test", "test", DayOfWeek.SUNDAY, "none");
    assertEquals(d.getCompletedTasks(), 0);
  }

  @Test
  void getMaxTasks() {
    assertEquals(d.getMaxTasks(), 5);
  }

  @Test
  void getMaxEvents() {
    assertEquals(d.getMaxEvents(), 5);
  }

  @Test
  void setMaxEvents() {
    d.setMaxEvents(0);
    assertEquals(d.getMaxEvents(), 0);
  }

  @Test
  void setMaxTasks() {
    d.setMaxTasks(0);
    assertEquals(d.getMaxTasks(), 0);

  }
}