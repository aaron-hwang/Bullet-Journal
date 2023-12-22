package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EventTest {
  Event e;

  @BeforeEach
  public void setup() {
    e = new Event("test", "test", DayOfWeek.SUNDAY, "12:00", "12");
  }

  @Test
  void getName() {
    assertEquals(e.getName(), "test");
  }

  @Test
  void getDescription() {
    assertEquals(e.getDescription(), "test");
  }

  @Test
  void getDayOfWeek() {
    assertEquals(e.getDayOfWeek(), DayOfWeek.SUNDAY);
  }

  @Test
  void getStartTime() {
    assertEquals(e.getStartTime(), "12:00");
  }

  @Test
  void getDuration() {
    assertEquals(e.getDuration(), "12");
  }

  @Test
  void copyOver() {
    e.copyOver(new Event("day",
        "bro",
        DayOfWeek.SUNDAY,
        "12:00",
        "12 minutes"));
    assertEquals(e.getDuration(), "12 minutes");
    assertEquals(e.getName(), "day");
    assertEquals(e.getDescription(), "bro");
  }
}