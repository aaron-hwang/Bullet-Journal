package cs3500.pa05.model;

/**
 * A class that represents a task
 */
public class Task {
  private String name;
  private String description;
  private String category;
  private DayOfWeek day;
  private boolean isComplete;

  /**
   * The default constructor for a task
   *
   * @param name This task's name
   * @param description The description of this task
   * @param day The day of the week
   * @param category the category of this task
   * @param isComplete whether this task is complete
   */
  public Task(String name, String description, DayOfWeek day, String category, boolean isComplete) {
    this.name = name;
    this.description = description;
    this.category = category;
    this.isComplete = isComplete;
    this.day = day;
  }

  /**
   * Getter method returning the task name
   *
   * @return Task name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Return whether this task is complete
   *
   * @return the completeness of this task
   */
  public boolean isComplete() {
    return this.isComplete;
  }

  /**
   * Get the description of this task
   *
   * @return Whether this task is complete
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Get the day of the week of this task
   *
   * @return the DayOfWeek of this task
   */
  public DayOfWeek getDayOfWeek() {
    return this.day;
  }

  /**
   * Get the category of this category
   *
   * @return this task's category
   */
  public String getCategory() {
    return this.category;
  }

  /**
   * copies the info from the given task to this task
   *
   * @param task task we are copying the data of
   */
  public void copyOver(Task task) {
    this.name = task.getName();
    this.description = task.getDescription();
    this.day = task.getDayOfWeek();
    this.category = task.getCategory();
    this.isComplete = task.isComplete();
  }

}
