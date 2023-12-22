package cs3500.pa05.model;

import java.util.List;

/**
 * A class that represents a Day
 */
public class Day {

  private final DayOfWeek dayOfTheWeek;
  //problem: Records cannot exteend classes, jsons need to be seperated and cant be abstracted since
  //events and tasks have distinct fields that are not subsets of each other
  //solution: create overarching actionjson that has a list of
  private final List<Event> events;
  private final List<Task> tasks;
  private int maxTasks;
  private int maxEvents;

  /**
   * Default constructor
   *
   * @param day Day of the Week
   * @param events Events for this day
   * @param tasks Tasks for this day
   */
  public Day(DayOfWeek day, List<Event> events, List<Task> tasks) {
    this.dayOfTheWeek = day;
    this.events = events;
    this.tasks = tasks;
    this.maxTasks = 5;
    this.maxEvents = 5;
  }

  /**
   * Getter method returning a list of tasks
   *
   * @return List of tasks
   */
  public List<Task> getTasks() {
    return this.tasks;
  }

  /**
   * Getter for this Day's events
   *
   * @return the list of events belonging to this day
   */
  public List<Event> getEvents() {
    return this.events;
  }

  /**
   * Gets the day of the week of this Day
   *
   * @return This Day's DayOfWeek
   */
  public DayOfWeek getDayOfWeek() {
    return this.dayOfTheWeek;
  }

  /**
   * Gets the max tasks for this Day
   *
   * @return max tasks for the day
   */
  public int getMaxTasks() {
    return this.maxTasks;
  }

  /**
   * Gets the max Events of this Day
   *
   * @return max events of the day
   */
  public int getMaxEvents() {
    return this.maxEvents;
  }

  /**
   * Adds a new task to this day
   *
   * @param name Name of the task
   * @param description Description of the task
   * @param day The day of the week
   * @param category The category this task belongs to
   */
  public void addTask(String name, String description, DayOfWeek day, String category) {
    tasks.add(new Task(name, description, day, category, false));
  }

  /**
   * Add an event to this week
   *
   * @param name Name of the task
   * @param description Description of the task
   * @param day Day of the week
   * @param startTime Start time of the event
   * @param duration Duration of the event
   */
  public void addEvent(String name, String description, DayOfWeek day, String startTime,
                       String duration) {
    events.add(new Event(name, description, day, startTime, duration));
  }

  /**
   * Get the amount of completed tasks in this day
   *
   * @return Int amount of completed tasks for this day
   */
  public int getCompletedTasks() {
    int completedAmount = 0;
    for (Task t : this.tasks) {
      if (t.isComplete()) {
        completedAmount++;
      }
    }

    return completedAmount;
  }

  /**
   * Set the max events of this day
   *
   * @param maxEventsInt amount to set it to
   */
  public void setMaxEvents(int maxEventsInt) {
    this.maxEvents = maxEventsInt;
  }

  /**
   * Set the max tasks of this day
   *
   * @param maxTasksInt amount to set it to
   */
  public void setMaxTasks(int maxTasksInt) {
    this.maxTasks = maxTasksInt;
  }
}
