package cs3500.pa05.json;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.json.EventAdapter;
import cs3500.pa05.model.json.EventJson;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import cs3500.pa05.model.Event;

class EventAdapterTest {

  List<Event> eventList = new ArrayList<>();
  Event e;
  @BeforeEach
  public void setup() {
    e = new Event("test",
        "test",
        DayOfWeek.SUNDAY,
        "12:00",
        "12 min");

    for (DayOfWeek day :DayOfWeek.values()) {
      eventList.add(
          new Event(
              "test" + day.ordinal(),
              "test",
              day,
              "12", "12"));
    }

  }


  @Test
  void jsonListToEvents() {
    List<EventJson> eventJsons = new ArrayList<>();
    for (DayOfWeek day : DayOfWeek.values()) {
      eventJsons.add(
          new EventJson(
              "test" + day.ordinal(),
              "test", day.toString(),
              "12:00", "12"));
    }

    List<Event> events = EventAdapter.jsonListToEvents(eventJsons);
    assertEquals(events.size(), 7);
    int i = 0;
    for (Event e : events) {
      assertEquals(e.getName(), "test" + i);
      i++;
    }

  }

  @Test
  void jsonToEvent() {
    EventJson json = new EventJson(
        "test", "test",
        "SUNDAY", "12", "12");
    Event event = EventAdapter.jsonToEvent(json);
    assertEquals(event.getDayOfWeek(), DayOfWeek.SUNDAY);
    assertEquals(event.getName(), "test");
    assertEquals(event.getDescription(), "test");
  }

  @Test
  void eventsToJson() {
    List<EventJson> eventJsons = EventAdapter.eventsToJson(eventList);
    int i = 0;
    for (EventJson eventJson : eventJsons) {
      assertEquals(eventJson.name(), "test" + i);
      i++;
    }
  }

  @Test
  void eventToJson() {
    EventJson eventJson = EventAdapter.eventToJson(e);
    assertEquals(eventJson.dayOfWeek(), DayOfWeek.SUNDAY.toString());
    assertEquals(eventJson.name(), "test");
    assertEquals(eventJson.description(), "test");
  }
}