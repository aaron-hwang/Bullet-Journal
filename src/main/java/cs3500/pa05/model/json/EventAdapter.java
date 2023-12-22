package cs3500.pa05.model.json;

import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that adapts events to their respective json files, and lists of events
 */
public class EventAdapter {

  /**
   * Adapts a list of EventJsons to a List of Event objects
   *
   * @param eventJsons The list of event jsons to adapt
   * @return the list of event objects
   */
  public static List<Event> jsonListToEvents(List<EventJson> eventJsons) {
    List<Event> toReturn = new ArrayList<>();
    for (EventJson eventJson : eventJsons) {
      toReturn.add(EventAdapter.jsonToEvent(eventJson));
    }

    return toReturn;
  }

  /**
   * Adapt an EventJson to an Event object
   *
   * @param event EventJson object to adapt
   * @return The event object
   */
  public static Event jsonToEvent(EventJson event) {
    return new Event(
        event.name(),
        event.description(),
        DayOfWeek.valueOf(event.dayOfWeek()),
        event.startTime(),
        event.duration());
  }

  /**
   * Convert a list of events to a list of EventJson
   *
   * @param events The Events we wish to convert
   * @return a list of event jsons
   */
  public static List<EventJson> eventsToJson(List<Event> events) {
    List<EventJson> toReturn = new ArrayList<>();
    for (Event e : events) {
      toReturn.add(EventAdapter.eventToJson(e));
    }

    return toReturn;
  }

  /**
   * Convert an event to a corresponding EventJson
   *
   * @param e the Event to convert
   * @return the appropriately formatted eventjson
   */
  public static EventJson eventToJson(Event e) {
    //I hate this as much as you do, trust me
    return new EventJson(
        e.getName(),
        e.getDescription(),
        e.getDayOfWeek().toString(),
        e.getStartTime(),
        e.getDuration());
  }
}
