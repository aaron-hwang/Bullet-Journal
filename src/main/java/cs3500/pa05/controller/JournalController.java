package cs3500.pa05.controller;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.OneWeekModel;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.json.JournalAdapter;
import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * A controller for a journal
 */
public class JournalController implements Controller {
  private static final int progressBarRow = 2;
  private static final int taskNumsRow = 3;
  private static final int BUTTON_SIZE = 107;
  private final Journal journal;
  @FXML
  GridPane gridPane;
  @FXML
  Button saveFile;
  @FXML
  Button openFile;
  @FXML
  Button nameOfWeek;
  @FXML
  Button newEvent;
  @FXML
  Button newTask;
  @FXML
  Button quotesAndNotes;
  @FXML
  Label weekTasks;
  @FXML
  Label weekEvents;
  @FXML
  Label completePercentage;
  @FXML
  Button customizeThemeButton;
  @FXML
  Button maxEventsTasksButton;
  @FXML
  AnchorPane scrollPane;
  @FXML
  AnchorPane scene;

  List<Label> dayTaskNums;
  Map<Day, Label> dayTaskNumsMap;
  List<ProgressBar> dayProgressBars;
  Map<Day, ProgressBar> dayProgressBarsMap;
  int tasksDone;
  int totalTasks;
  int totalEvents;
  private ArrayList<Button> taskButtonList;
  private ArrayList<Button> eventButtonList;

  /**
   * creates a controller the handles GUI Inputs
   *
   * @param journal - information you wish to display
   */
  public JournalController(Journal journal) {
    this.journal = journal;
    this.tasksDone = 0;
    this.totalTasks = 0;
    this.totalEvents = 0;
    this.dayProgressBars = new ArrayList<>();
    this.dayTaskNums = new ArrayList<>();
    this.dayProgressBarsMap = new HashMap<>();
    this.dayTaskNumsMap = new HashMap<>();
  }

  /**
   * Initialize the progress bars for each day
   */
  private void initProgressBars() {
    for (Day d : journal.getOneWeekModel().getDays()) {
      ProgressBar dayProgressBar = new ProgressBar();
      this.gridPane.add(dayProgressBar, d.getDayOfWeek().ordinal(), progressBarRow);
      this.dayProgressBars.add(dayProgressBar);
      this.dayProgressBarsMap.put(d, dayProgressBar);
    }
  }

  /**
   * Initialize the labels for each day of the week of the # of completed tasks
   */
  private void initTaskNums() {
    //For every day of the week, create a blank label indicating the number of completed tasks
    for (Day d : journal.getOneWeekModel().getDays()) {
      Label taskNumDayLabel = new Label();
      this.gridPane.add(taskNumDayLabel, d.getDayOfWeek().ordinal(), taskNumsRow);
      this.dayTaskNums.add(taskNumDayLabel);
      this.dayTaskNumsMap.put(d, taskNumDayLabel);
    }
  }

  @Override
  @FXML
  public void run() {
    initTaskAndEvents();
    //initialize the progress bars (empty)
    initProgressBars();
    //initialize the task number trackers for each day
    initTaskNums();
    saveFile.setOnAction(event -> saveToFile());
    openFile.setOnAction(event -> openFile());
    nameOfWeek.setOnAction(event -> changeText(nameOfWeek));
    quotesAndNotes.setOnAction(event -> changeText(quotesAndNotes));
    newEvent.setOnAction(event -> newEventCreation());
    newTask.setOnAction(event -> newTaskCreation());
    maxEventsTasksButton.setOnAction(event -> setMaxEventsTasks());
    customizeThemeButton.setOnAction(event -> changeTheme());
    updateData();
  }

  private void changeTheme() {
    Dialog<ArrayList<Paint>> dialog = new Dialog<>();
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    dialog.setTitle("Customize Theme");
    Label sceneColorContext = new Label("Background:");
    ColorPicker sceneColor = new ColorPicker();
    Label taskColorContext = new Label("Tasks: ");
    ColorPicker taskColor = new ColorPicker();
    Label eventColorContext = new Label("Events:");
    ColorPicker eventColor = new ColorPicker();
    Label otherColorContext = new Label("Other:");
    ColorPicker otherColor = new ColorPicker();
    dialog.getDialogPane().setContent(
        new VBox(
            sceneColorContext, sceneColor,
            taskColorContext, taskColor,
            eventColorContext, eventColor,
            otherColorContext, otherColor
        )
    );
    Optional<ArrayList<Paint>> results = dialog.showAndWait();

    if (results.isPresent()) {
      scene.setBackground(Background.fill(sceneColor.getValue()));
      setColorTasks(taskColor.getValue());
      setColorEvents(taskColor.getValue());
      setColorOther(taskColor.getValue());
      updateData();
      initTaskAndEvents();
    } else {
      changeTheme();
    }
  }

  private void setColorOther(Color value) {
    saveFile.setBackground(Background.fill(value));
    openFile.setBackground(Background.fill(value));
    quotesAndNotes.setBackground(Background.fill(value));
    nameOfWeek.setBackground(Background.fill(value));
    newEvent.setBackground(Background.fill(value));
    newTask.setBackground(Background.fill(value));
  }

  private void setColorEvents(Color value) {
    for (Button b : eventButtonList) {
      b.setBackground(Background.fill(value));
    }
  }

  private void setColorTasks(Color value) {
    for (Button b : taskButtonList) {
      b.setBackground(Background.fill(value));
    }
  }

  private void setMaxEventsTasks() {
    Dialog<String> dayDialog = new Dialog<>();
    dayDialog.setTitle("Day Picker");
    dayDialog.setHeaderText("Which day would you like to set preferences for?");
    dayDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    TextField dayField = new TextField("Day: ");
    dayDialog.getDialogPane().setContent(new VBox(dayField));
    Optional<String> dayResult = dayDialog.showAndWait();
    if (!(dayField.getText().equals("") || dayField.getText().equals("Day: "))) {
      DayOfWeek day = DayOfWeek.SUNDAY;
      try {
        day = DayOfWeek.valueOf(dayField.getText().toUpperCase());
      } catch (IllegalArgumentException e) {
        setMaxEventsTasks();
      }
      setMaxEventsTasksSubDialog(day);
    }
  }

  private void setMaxEventsTasksSubDialog(DayOfWeek day) {
    Dialog<ArrayList<String>> dialog = new Dialog<>();
    dialog.setTitle("Set Max");
    dialog.setHeaderText("Please set your preferences for maximum tasks and events.");
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    Day dayObjectReference = journal.getOneWeekModel().getDays().get(day.ordinal());
    TextField maxEvents = new TextField("Max Events: " + dayObjectReference.getMaxEvents());
    TextField maxTasks = new TextField("Max Tasks: " + dayObjectReference.getMaxTasks());
    dialog.getDialogPane().setContent(new VBox(maxEvents, maxTasks));
    Optional<ArrayList<String>> results = dialog.showAndWait();
    String maxEventsTxt = "Max Events: " + dayObjectReference.getMaxEvents();
    String maxTasksTxt = "Max Tasks: " + dayObjectReference.getMaxTasks();

    if (!(maxEvents.getText().equals("") || maxEvents.getText().equals(maxEventsTxt)
        || maxTasks.getText().equals("") || maxEvents.getText().equals(maxTasksTxt))) {
      int maxEventsInt = 0;
      int maxTasksInt = 0;
      try {
        maxEventsInt = Integer.parseInt(maxEvents.getText());
        maxTasksInt = Integer.parseInt(maxTasks.getText());
      } catch (NumberFormatException e) {
        setMaxEventsTasksSubDialog(day);
      }

      if (maxTasksInt < 0 || maxEventsInt < 0) {
        setMaxEventsTasksSubDialog(day);
      }

      dayObjectReference.setMaxEvents(maxEventsInt);
      dayObjectReference.setMaxTasks(maxTasksInt);
    }
  }

  /**
   * Updates information in gridpane to reflect tests and events accurately
   */
  public void initTaskAndEvents() {
    taskButtonList = new ArrayList<>();
    eventButtonList = new ArrayList<>();
    OneWeekModel oneWeek = this.journal.getOneWeekModel();
    int taskCount = 0;

    for (Day d : oneWeek.getDays()) {
      VBox box = new VBox();

      for (Task task : d.getTasks()) {
        Button button = new Button(task.getName());
        button.setPrefWidth(BUTTON_SIZE);
        button.setPrefHeight(BUTTON_SIZE);
        button.setOnAction(event -> changeMindTask(task));
        box.getChildren().add(button);
        taskButtonList.add(button);
      }

      for (Event e : d.getEvents()) {
        Button button = new Button(e.getName());
        button.setPrefWidth(BUTTON_SIZE);
        button.setPrefHeight(BUTTON_SIZE);
        button.setOnAction(event -> changeMindEvent(e));
        box.getChildren().add(button);
        eventButtonList.add(button);
      }
      gridPane.add(box, d.getDayOfWeek().ordinal(), 1);
    }
    this.totalTasks = taskCount;

    updateData();
  }

  /**
   * updates the data in the GUI to match that in the journal
   */
  public void updateData() {
    updateLabels();
    intItScrollPane();
  }

  /**
   * updates the labels in the view, programmatically
   */
  public void updateLabels() {
    int totalWeekTasks = 0;
    int totalWeekCompleteTasks = 0;
    int totalWeekEvents = 0;
    for (Day d : this.dayTaskNumsMap.keySet()) {
      int totalTasks = d.getTasks().size();
      int completedTasksAmt = d.getCompletedTasks();
      int totalEvents = d.getEvents().size();
      totalWeekCompleteTasks += completedTasksAmt;
      totalWeekEvents += totalEvents;
      totalWeekTasks += totalTasks;
      Label taskLabel = this.dayTaskNumsMap.get(d);
      ProgressBar bar = this.dayProgressBarsMap.get(d);
      bar.setProgress((double) completedTasksAmt / totalTasks);
      taskLabel.setText(Integer.toString(totalTasks - completedTasksAmt));
    }
    float percentage = (float) totalWeekCompleteTasks / totalWeekTasks;
    percentage *= 100;
    String textPercentage = Float.toString(percentage);
    if (textPercentage.length() >= 5) {
      textPercentage = textPercentage.substring(0, 5);
    }

    this.weekTasks.setText(Integer.toString(totalWeekTasks));
    this.weekEvents.setText(Integer.toString(totalWeekEvents));
    this.completePercentage.setText(textPercentage);
  }

  /**
   * Initialize our task queue, as a scroll pane
   */
  public void intItScrollPane() {
    this.scrollPane.getChildren().removeAll(this.scrollPane.getChildren());
    OneWeekModel oneWeek = this.journal.getOneWeekModel();
    VBox box = new VBox();

    for (Day d : oneWeek.getDays()) {
      for (Task t : d.getTasks()) {
        Button l;
        if (t.isComplete()) {
          l = new Button(t.getName() + ": Complete");
        } else {
          l = new Button(t.getName() + ": Incomplete");
        }
        box.getChildren().add(l);
      }
    }
    this.scrollPane.getChildren().add(box);
  }

  /**
   * Creates a new event, opens a dialog pane
   */
  private void newEventCreation() {
    Dialog<ArrayList<String>> dialog = new Dialog<>();
    dialog.setTitle("Creation");
    dialog.setHeaderText("Please input the following fields...");
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    TextField nameField = new TextField("Name:");
    TextField descripField = new TextField("Description:");
    TextField dayField = new TextField("Day of Week:");
    TextField timeField = new TextField("Time:");
    TextField durationField = new TextField("Duration:");
    dialog.getDialogPane().setContent(
        new VBox(nameField, descripField, dayField, timeField, durationField));
    Optional<ArrayList<String>> results = dialog.showAndWait();
    String dayTxt = dayField.getText();

    if (!results.isEmpty()) {
      if (!(dayTxt.isEmpty() || dayTxt.equals("Day of Week:"))) {
        journal.addEvent(nameField.getText(), descripField.getText(), dayField.getText(),
            timeField.getText(), durationField.getText());
      }
    }
    updateData();
    initTaskAndEvents();
  }

  private void errDialog() {
    Dialog<String> errDialog = new Dialog<>();
    errDialog.setTitle("Error");
    errDialog.setHeaderText("There was an error.");
    errDialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
    errDialog.showAndWait();
  }

  /**
   * Creates a new task, opens a dialog pane
   */
  private void newTaskCreation() {
    Dialog<ArrayList<String>> dialog = new Dialog<>();
    dialog.setTitle("Creation");
    dialog.setHeaderText("Please input the following fields...");
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    TextField nameField = new TextField("Name:");
    TextField descripField = new TextField("Description:");
    TextField dayField = new TextField("Day of Week:");
    TextField categoryField = new TextField("Category:");
    dialog.getDialogPane().setContent(
        new VBox(nameField, descripField, dayField, categoryField));
    Optional<ArrayList<String>> results = dialog.showAndWait();
    String dayTxt = dayField.getText();

    if (!results.isEmpty()) {
      if (!(dayTxt.isEmpty() || dayTxt.equals("Day of Week:"))) {
        journal.addTask(nameField.getText(), descripField.getText(), dayField.getText(),
            categoryField.getText());
      }
    }
    updateData();
    initTaskAndEvents();
  }

  /**
   * Creates a dialog that sets label name if added
   */
  private void changeText(Button button) {
    Dialog<String> dialog = new Dialog<>();
    dialog.setTitle("Change");
    dialog.setHeaderText("Please type in the new content.");
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    TextField text = new TextField();
    dialog.getDialogPane().setContent(new VBox(text));

    Optional<String> result = dialog.showAndWait();

    if (result.isPresent()) {
      button.setText(text.getText());
    }
  }

  /**
   * Edit a task
   *
   * @param task The task to edit
   */
  private void changeMindTask(Task task) {
    Dialog<ArrayList<String>> dialog = new Dialog<>();
    dialog.setTitle("Change Task");
    dialog.setHeaderText("Please edit below to your satisfaction");
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    TextField nameField = new TextField(task.getName());
    TextField descripField = new TextField(task.getDescription());
    TextField dayField = new TextField(task.getDayOfWeek().toString());
    TextField categoryField = new TextField(task.getCategory());
    TextField trueFalseField = new TextField(String.valueOf(task.isComplete()));
    dialog.getDialogPane().setContent(
        new VBox(
            nameField, descripField, dayField, categoryField, trueFalseField
        )
    );
    Optional<ArrayList<String>> results = dialog.showAndWait();

    if (results.isPresent()) {
      DayOfWeek day = DayOfWeek.SUNDAY;
      boolean complete = false;
      try {
        day = DayOfWeek.valueOf(dayField.getText().toUpperCase());
        complete = Boolean.parseBoolean(trueFalseField.getText().toUpperCase());
      } catch (IllegalArgumentException e) {
        changeMindTask(task);
      }
      task.copyOver(
          new Task(nameField.getText(), descripField.getText(), day, categoryField.getText(),
              complete));
    }
    updateData();
    initTaskAndEvents();
  }

  /**
   * Change an event
   *
   * @param event Event to change
   */
  private void changeMindEvent(Event event) {
    Dialog<ArrayList<String>> dialog = new Dialog<>();
    dialog.setTitle("Change Event");
    dialog.setHeaderText("Please change to your satisfaction");
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    TextField nameField = new TextField(event.getName());
    TextField descripField = new TextField(event.getDescription());
    TextField dayField = new TextField(event.getDayOfWeek().toString());
    TextField timeField = new TextField(event.getStartTime());
    TextField durationField = new TextField(event.getDuration());

    dialog.getDialogPane().setContent(new VBox(nameField, descripField, dayField, timeField,
        durationField));
    Optional<ArrayList<String>> results = dialog.showAndWait();

    if (results.isPresent()) {
      DayOfWeek day = DayOfWeek.SUNDAY;
      try {
        day = DayOfWeek.valueOf(dayField.getText().toUpperCase());
      } catch (IllegalArgumentException e) {
        changeMindEvent(event);
      }
      event.copyOver(
          new Event(nameField.getText(), descripField.getText(), day, timeField.getText(),
              durationField.getText()));
    }
    updateData();
    initTaskAndEvents();
  }

  /**
   * save the journal to a given file
   */
  private void saveToFile() {
    updateData();
    //open a dialog that gets a string
    Dialog<String> dialog = new Dialog<>();
    dialog.setTitle("Get File Path");
    dialog.setHeaderText("Please type in the name of the file you'd like to save this to.");
    TextField fileNameField = new TextField();
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    dialog.getDialogPane().setContent(new VBox(fileNameField));

    Optional<String> result = dialog.showAndWait();
    if (!result.isEmpty()) {
      Path filePath;
      File file;
      try {
        filePath = Path.of("src/main/resources/" + fileNameField.getText() + ".bujo");
        file = filePath.toFile();
      } catch (InvalidPathException e) {
        file = new File("src/main/resources" + fileNameField.getText() + ".bujo");
      }

      try {
        JournalAdapter.writeToFile(file, this.journal);
      } catch (IOException e) {
        errDialog();
      }
    }
  }

  /**
   * Open a bujo file
   */
  private void openFile() {
    //open dialog that gets a string
    //make into file path
    //if file doesn't exist trap until they put one that does
    Dialog<String> dialog = new Dialog<>();
    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    dialog.setTitle("Open File");
    dialog.setHeaderText("Please enter the name of the file you wish to open.");
    TextField fileNameField = new TextField();
    dialog.getDialogPane().setContent(new VBox(fileNameField));
    Optional<String> result = dialog.showAndWait();

    if (!result.isEmpty()) {
      Path path = Path.of("src\\main\\resources\\" + fileNameField.getText() + ".bujo");
      File file = path.toFile();
      try {
        this.journal.copyOver(JournalAdapter.createFromFile(file));
        updateData();
        initTaskAndEvents();
      } catch (IllegalArgumentException e) {
        errDialog();
      }
    }
  }
}

// write dialog to change max events n tasks *kelsey* (X)
// write save and open file dialogs *kelsey* (X)
// optional: refactor programmatically creating buttons *together* (X)
// format features file *eric*

// write readme file *eric*
// test up to 90% in model *aaron*
// custom themes *kelsey & eric*
// mind changes *kelsey* (X)

// extra credit
// jar file (easy af)
// visual flourish