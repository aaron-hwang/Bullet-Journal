package cs3500.pa05.model;

/**
 * A class that represents an instance of a journal
 */
public class JournalImpl implements Journal {
  private OneWeekModel weekModel;

  /**
   * Field of the quotes and notes of a given journal
   */
  private String quotesAndNotes;

  /**
   * The constructor for loading a journal with a known one week
   *
   * @param weekModel The week model to construct our journal with
   * @param quotes quotes and notes section
   */
  public JournalImpl(OneWeekModel weekModel, String quotes) {
    this.weekModel = weekModel;
    this.quotesAndNotes = quotes;
  }

  /**
   * The default constructor for creating an empty OneWeek
   */
  public JournalImpl() {
    this.weekModel = new OneWeek();
    this.quotesAndNotes = "";
  }

  /**
   * Copy (shallow) the information from the given JournalImpl to this JournalImpl
   *
   * @param fromBujoFile - Journal from the bujo file we wish to mutate this journal impl into
   */
  public void copyOver(Journal fromBujoFile) {
    this.weekModel = fromBujoFile.getOneWeekModel();
    this.quotesAndNotes = fromBujoFile.getQuotesAndNotes();
  }

  /**
   * Getter method returning the week model
   *
   * @return One Week Model
   */
  public OneWeekModel getOneWeekModel() {
    return this.weekModel;
  }

  /**
   * Getter method returning the quotes and notes
   *
   * @return String quotes and notes
   */
  public String getQuotesAndNotes() {
    return this.quotesAndNotes;
  }

  /**
   * Add a task to the journal
   *
   * @param name Name of the task
   * @param description description of the task
   * @param dayStr Day of the task
   * @param category Category of the task
   * @throws IllegalArgumentException When name is blank or day is not a valid day
   */
  @Override
  public void addTask(String name, String description, String dayStr, String category)
      throws IllegalArgumentException {
    DayOfWeek day;

    //validate name as nonnull
    if (name.equals("")) {
      throw new IllegalArgumentException("Your name is null.");
    }
    try {
      day = DayOfWeek.valueOf(dayStr.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Your day is incorrect.");
    }

    weekModel.addTask(name, description, day, category);
  }

  /**
   * Add an event to the journal
   *
   * @param name Name of the event
   * @param description Description of the event
   * @param dayStr Day of the event
   * @param startTime Start time of the event
   * @param duration Duration of the event
   * @throws IllegalArgumentException When name is null or day is incorrect
   */
  @Override
  public void addEvent(String name, String description, String dayStr, String startTime,
                       String duration) throws IllegalArgumentException {
    DayOfWeek day;

    //validate name as nonnull
    if (name.equals("")) {
      throw new IllegalArgumentException("Your name is null.");
    }
    try {
      day = DayOfWeek.valueOf(dayStr.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Your day is incorrect.");
    }

    weekModel.addEvent(name, description, day, startTime, duration);
  }
}
