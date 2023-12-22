package cs3500.pa05.model;

import static cs3500.pa05.model.DayOfWeek.SUNDAY;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JournalImplTest {
  Journal j;
  List<Day> dayList;
  OneWeekModel oneWeekModel;
  Journal journal;
  List<Event> events;
  List<Task> tasks;

  @BeforeEach
  public void setup() {
    events = new ArrayList<>();
    tasks = new ArrayList<>();
    j = new JournalImpl();
    dayList = new ArrayList<>();
    events.add(
        new Event(
            "test",
            "test", SUNDAY,
            "12:00", "12 minutes"));
    tasks.add(
        new Task(
            "test",
            "Test",
            SUNDAY,
            "none", false));
    for (DayOfWeek day : DayOfWeek.values()) {
      dayList.add(new Day(day, events, tasks));
    }
    oneWeekModel = new OneWeek(dayList, 5, 5);
    journal = new JournalImpl(oneWeekModel, "this is a test");
  }

  @Test
  void copyOver() {
    j.copyOver(journal);
    assertEquals(j.getQuotesAndNotes(), "this is a test");
    assertEquals(j.getOneWeekModel().getMaxEvents(), 5);
    assertEquals(j.getOneWeekModel().getMaxTasks(), 5);
  }

  @Test
  void getOneWeekModel() {
    assertEquals(j.getOneWeekModel().getMaxEvents(), 0);
    assertEquals(j.getOneWeekModel().getMaxTasks(), 0);
    assertEquals(j.getQuotesAndNotes(), "");
    assertEquals(j.getOneWeekModel().getTotalCompletedTasks(),0);
  }

  @Test
  void addTask() {
    j.addTask("name", "hi", "SUNDAY", "none");
    assertThrows(IllegalArgumentException.class,
        () -> j.addTask("", "test", "sunday", ""));
    assertThrows(IllegalArgumentException.class,
        () -> j.addTask("test", "test", "test", "tes"));
    assertEquals(j.getOneWeekModel().getTotalTasksCount(), 1);
  }

  @Test
  void addEvent() {
    assertThrows(IllegalArgumentException.class,
        () -> j.addEvent("", "test", "sunday", "none", "12"));
    assertThrows(IllegalArgumentException.class,
        () -> j.addEvent(
            "test", "test", "test", "12:00", "12 mins"));
    j.addEvent(
        "test", "test", "sunday", "12:00", "12 mins");
    assertEquals(j.getOneWeekModel().getTotalEventCount(), 1);
  }

  @Test
  void getQuotesAndNotes() {
    assertEquals(j.getQuotesAndNotes(), "");
    assertEquals(journal.getQuotesAndNotes(), "this is a test");
  }
}