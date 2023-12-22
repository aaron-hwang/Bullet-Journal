package cs3500.pa05.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a given singular week
 */
public class OneWeek implements OneWeekModel {

  //maybe change to hashmap?
  private final List<Day> days;
  private final int maxEvents;
  private final int maxTasks;

  /**
   * The default constructor for a OneWeek
   */
  public OneWeek() {
    this.days = new ArrayList<>();
    this.initDays();
    this.maxEvents = 0;
    this.maxTasks = 0;
  }

  /**
   * A constructor for a OneWeek
   *
   * @param days The Days that consist of this week
   * @param maxEvents max events of this week
   * @param maxTasks max tasks of this week
   */
  public OneWeek(List<Day> days, int maxEvents, int maxTasks) {
    this.days = days;
    this.maxEvents = maxEvents;
    this.maxTasks = maxTasks;
  }

  /**
   * Initialize the days of the week for this one week
   */
  private void initDays() {
    for (DayOfWeek day : DayOfWeek.values()) {
      Day oneDay = new Day(day, new ArrayList<>(), new ArrayList<>());
      this.days.add(oneDay);
    }
  }

  /**
   * Getter method returning the list of days
   *
   * @return List of Days
   */
  public List<Day> getDays() {
    return this.days;
  }

  /**
   * Returns the amount of max events in this week
   *
   * @return the max events of this week
   */
  @Override
  public int getMaxEvents() {
    return this.maxEvents;
  }

  /**
   * Return the maximum amount of tasks for this week
   *
   * @return max tasks of this week
   */
  @Override
  public int getMaxTasks() {
    return this.maxTasks;
  }

  /**
   * Return the amount of completed tasks this week contains
   *
   * @return completed tasks jin this week
   */
  @Override
  public int getTotalCompletedTasks() {
    int completedAmount = 0;
    for (Day d : this.days) {
      completedAmount += d.getCompletedTasks();
    }

    return completedAmount;
  }

  /**
   * Retrieve the total task count from this week
   *
   * @return task count of this week
   */
  @Override
  public int getTotalTasksCount() {
    int total = 0;
    for (Day d : this.days) {
      total += d.getTasks().size();
    }
    return total;
  }

  /**
   * Get the total count of events in this week
   *
   * @return An int representing the amount of Events in this week
   */
  @Override
  public int getTotalEventCount() {
    int total = 0;
    for (Day d : this.days) {
      total += d.getEvents().size();
    }

    return total;
  }

  /**
   * Add a task to this one week
   *
   * @param name Name of the task
   * @param description Description of the task
   * @param day day associated with the task
   * @param category category of the task to add
   */
  @Override
  public void addTask(String name, String description, DayOfWeek day, String category) {
    for (Day d : days) {
      if (d.getDayOfWeek().equals(day)) {
        d.addTask(name, description, day, category);
      }
    }
  }

  /**
   * Add an event to the week
   *
   * @param name The name of the event
   * @param description the description of the event
   * @param day The day this event belongs to
   * @param startTime The start tiem of this event
   * @param duration Duration of the event
   */
  @Override
  public void addEvent(String name, String description, DayOfWeek day, String startTime,
                       String duration) {
    for (Day d : days) {
      if (d.getDayOfWeek().equals(day)) {
        d.addEvent(name, description, day, startTime, duration);
      }
    }
  }
}
