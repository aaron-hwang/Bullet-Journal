package cs3500.pa05.model;

import java.util.List;

/**
 * An interface representing the core functionality of a week
 */
public interface OneWeekModel {

  /**
   * Gets the days from this one week
   *
   * @return the days of this week
   */
  List<Day> getDays();

  /**
   * Gets the max events from this model
   *
   * @return max events of this model
   */
  int getMaxEvents();

  /**
   * Gets the max tasks from this model
   *
   * @return max tasks of this week
   */
  int getMaxTasks();

  /**
   * Get the amount of completed tasks from this model
   *
   * @return completed task amount of this week
   */
  int getTotalCompletedTasks();

  /**
   * Get the total amount of tasks from this model
   *
   * @return total amount of tasks from this week
   */
  int getTotalTasksCount();

  /**
   * Get the total event count from this model
   *
   * @return total event count of this week
   */
  int getTotalEventCount();

  /**
   * Add a task to this model
   *
   * @param name Name of the task
   * @param description Description of the task
   * @param day day associated with the task
   * @param category category of the task to add
   */
  void addTask(String name, String description, DayOfWeek day, String category);

  /**
   * Add an event to this model
   *
   * @param name The name of the event
   * @param description the description of the event
   * @param day The day this event belongs to
   * @param startTime The start tiem of this event
   * @param duration Duration of the event
   */
  void addEvent(String name, String description, DayOfWeek day, String startTime, String duration);
}
