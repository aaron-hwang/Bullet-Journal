package cs3500.pa05.model;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.JournalController;
import cs3500.pa05.view.JournalView;
import cs3500.pa05.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A class to represent our journal application
 */
public class JournalApplication extends Application {
  /**
   * The main entry point for all JavaFX applications.
   * The start method is called after the init method has returned,
   * and after the system is ready for the application to begin running.
   *
   * <p>
   * NOTE: This method is called on the JavaFX Application Thread.
   * </p>
   *
   * @param primaryStage the primary stage for this application, onto which
   *                     the application scene can be set.
   *                     Applications may create other stages, if needed, but they will not be
   *                     primary stages.
   */
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Bullet Journal");
    Journal emptyJournal = new JournalImpl();
    Controller journalController = new JournalController(emptyJournal);
    View journalView = new JournalView(journalController);
    primaryStage.setScene(journalView.load());
    journalController.run();
    primaryStage.show();
  }
}
