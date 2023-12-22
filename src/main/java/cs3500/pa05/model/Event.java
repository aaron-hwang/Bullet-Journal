package cs3500.pa05.model;

/**
 * A class that represents an event
 */
public class Event {

  private String name;
  private String description;
  private DayOfWeek dayOfWeek;
  private String startTime;
  private String duration;

  /**
   * Default constructor
   *
   * @param name The name of the event
   * @param description Description for the event
   * @param day Day of the event
   * @param startTime Start time of the event
   * @param duration Duration of the event
   */
  public Event(String name, String description, DayOfWeek day, String startTime, String duration) {
    this.name = name;
    this.description = description;
    this.dayOfWeek = day;
    this.startTime = startTime;
    this.duration = duration;
  }

  /**
   * Getter method returning the Event name
   *
   * @return Event name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Get the descripion of this event
   *
   * @return The description of this event
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Getter for this event's dayofweek
   *
   * @return The day of week
   */
  public DayOfWeek getDayOfWeek() {
    return this.dayOfWeek;
  }

  /**
   * Getter for the start time of the event
   *
   * @return The start time
   */
  public String getStartTime() {
    return this.startTime;
  }

  /**
   * Get the duration of this Event
   *
   * @return This event's duration
   */
  public String getDuration() {
    return this.duration;
  }

  /**
   * Copy the info from the given event to this event
   *
   * @param event The event to copy over
   */
  public void copyOver(Event event) {
    this.name = event.getName();
    this.description = event.getDescription();
    this.dayOfWeek = event.getDayOfWeek();
    this.startTime = event.getStartTime();
    this.duration = event.getDuration();
  }
}
