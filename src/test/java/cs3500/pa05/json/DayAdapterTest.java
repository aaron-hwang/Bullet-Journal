package cs3500.pa05.json;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.json.DayAdapter;
import cs3500.pa05.model.json.DayJson;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import cs3500.pa05.model.Day;

class DayAdapterTest {

  Day d;
  DayJson json;
  @BeforeEach
  public void setup() {
    d = new Day(DayOfWeek.SATURDAY, new ArrayList<>(), new ArrayList<>());
    json = new DayJson("SATURDAY", new ArrayList<>(), new ArrayList<>());
  }

  @Test
  void jsonToDay() {
    assertInstanceOf(DayJson.class, json);
    assertInstanceOf(Day.class, DayAdapter.jsonToDay(json));
    assertEquals(d.getDayOfWeek().toString(), json.dayOfTheWeek());
  }

  @Test
  void dayToJson() {
    DayJson json = DayAdapter.dayToJson(d);
    assertEquals(json.dayOfTheWeek(), d.getDayOfWeek().toString());
    assertEquals(json.events().size(), d.getEvents().size());
    assertEquals(json.tasks().size(), d.getTasks().size());

  }
}