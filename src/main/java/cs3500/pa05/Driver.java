package cs3500.pa05;

import cs3500.pa05.model.JournalApplication;
import javafx.application.Application;

/**
 * Serves as the entry point for this program
 */
public class Driver {

  /**
   * Starts our application
   *
   * @param args No usage for command line arguments with this program - simply run and go.
   */
  public static void main(String[] args) {
    //Start our application
    Application.launch(JournalApplication.class, args);
  }
}