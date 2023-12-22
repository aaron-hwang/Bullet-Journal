package cs3500.pa05.model.json;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import java.util.List;

/**
 * Adapts a day to json and vice versa
 */
public class DayAdapter {

  /**
   * Adapts a DayJson to a corresponding Day object
   *
   * @param dayJson The day json to adapt
   * @return A Day object
   */
  public static Day jsonToDay(DayJson dayJson) {
    List<Event> events = EventAdapter.jsonListToEvents(dayJson.events());
    List<Task> tasks = TaskAdapter.jsonListToTasks(dayJson.tasks());
    return new Day(DayOfWeek.valueOf(dayJson.dayOfTheWeek()), events, tasks);
  }

  /**
   * Adapts a day to a corresponding json
   *
   * @param day Day to adapt
   * @return The DayJson object
   */
  public static DayJson dayToJson(Day day) {
    List<TaskJson> taskJsons = TaskAdapter.tasksToJson(day.getTasks());
    List<EventJson> eventJsons = EventAdapter.eventsToJson(day.getEvents());
    return new DayJson(day.getDayOfWeek().toString(), taskJsons, eventJsons);
  }


}
