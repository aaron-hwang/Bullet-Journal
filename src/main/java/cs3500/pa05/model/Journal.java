package cs3500.pa05.model;

/**
 * An interface for a Journal of any type
 */
public interface Journal {
  /**
   * Get the oneWeekModel of a journal
   *
   * @return a OneWeekModel
   */
  OneWeekModel getOneWeekModel();

  /**
   * Add a task to the journal
   *
   * @param name Name of the task
   * @param description description of the task
   * @param day Day of the task
   * @param category Category of the task
   */
  void addTask(String name, String description, String day, String category);

  /**
   * Get the quotes and notes of this journal
   *
   * @return The Quotes and Notes of the journal
   */
  String getQuotesAndNotes();

  /**
   * Copy over the data of another journal
   *
   * @param fromBujoFile journal to copy from
   */
  void copyOver(Journal fromBujoFile);

  /**
   * Add an event
   *
   * @param name Name of the event
   * @param description Description of the event
   * @param dayStr Day of the event
   * @param startTime Start time of the event
   * @param duration Duration of the event
   */
  void addEvent(String name, String description, String dayStr, String startTime, String duration);
}
