package cs3500.pa05.json;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.OneWeek;
import cs3500.pa05.model.OneWeekModel;
import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.WeekAdapter;
import cs3500.pa05.model.json.WeekJson;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeekAdapterTest {
  OneWeekModel oneWeekModel;
  List<DayJson> list;

  @BeforeEach
  public void setup() {
    list = new ArrayList<>();
    for (DayOfWeek day : DayOfWeek.values()) {
      list.add(new DayJson(day.toString(), new ArrayList<>(), new ArrayList<>()));
    }
    oneWeekModel = new OneWeek();
  }

  @Test
  void jsonToWeek() {
    OneWeekModel week = WeekAdapter.jsonToWeek(new WeekJson(list, 5, 5));
    assertEquals(week.getTotalTasksCount(), 0);
    assertEquals(week.getMaxTasks(), 5);
    assertEquals(week.getTotalEventCount(), 0);
    assertEquals(week.getMaxEvents(), 5);
    assertEquals(week.getDays().size(), 7);
  }

  @Test
  void weekToJson() {
    WeekJson json = WeekAdapter.weekToJson(oneWeekModel);
    assertEquals(json.days().size(), 7);
    assertEquals(json.maxEvents(), 0);
    assertEquals(json.maxTasks(), 0);
  }
}