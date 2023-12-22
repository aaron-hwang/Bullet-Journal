package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OneWeekTest {
  OneWeekModel oneWeekModel;
  @BeforeEach
  public void setup() {
    List<Day> dayList = new ArrayList<>();
    for (DayOfWeek day : DayOfWeek.values()) {
      dayList.add(new Day(day, new ArrayList<>(), new ArrayList<>()));
    }
    oneWeekModel = new OneWeek(dayList, 5,5);
  }

  @Test
  void getDays() {
    assertEquals(oneWeekModel.getDays().size(), 7);
    assertEquals(oneWeekModel.getDays().get(0).getDayOfWeek(), DayOfWeek.SUNDAY);
  }

  @Test
  void getMaxEvents() {
    assertEquals(oneWeekModel.getMaxEvents(), 5);
    assertEquals(new OneWeek().getMaxEvents(), 0);
  }

  @Test
  void getMaxTasks() {
    assertEquals(oneWeekModel.getMaxTasks(), 5);
    assertEquals(new OneWeek().getMaxTasks(), 0);
  }

  @Test
  void addTask() {
    oneWeekModel.addTask("test", "test", DayOfWeek.SUNDAY, "");
    assertEquals(oneWeekModel.getTotalTasksCount(), 1);
  }

  @Test
  void addEvent() {
    oneWeekModel.addEvent("test", "test", DayOfWeek.SUNDAY,
        "12:00", "12 mins");
  }

  @Test
  void getTotalEventCount() {
    oneWeekModel.addEvent("test", "test", DayOfWeek.SUNDAY,
        "12:00", "12 mins");
    assertEquals(oneWeekModel.getTotalEventCount(), 1);
  }

  @Test
  void getTotalTaskCount() {
    oneWeekModel.addEvent("test", "test", DayOfWeek.SUNDAY,
        "12:00", "12 mins");
    oneWeekModel.addTask("test", "test", DayOfWeek.SUNDAY, "");
    assertEquals(oneWeekModel.getTotalTasksCount(), 1);
  }

  @Test
  void getTotalTasksCompleted() {
    oneWeekModel.addEvent("test", "test", DayOfWeek.SUNDAY,
        "12:00", "12 mins");
    oneWeekModel.addTask("test", "test", DayOfWeek.SUNDAY, "");
    assertEquals(oneWeekModel.getTotalTasksCount(),1);
  }
}