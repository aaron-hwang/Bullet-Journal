package cs3500.pa05.view;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.JournalController;
import cs3500.pa05.model.Journal;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * The View for a Journal
 */
public class JournalView implements View {
  private final FXMLLoader loader;

  /**
   * Default constructor for the view of a journal
   *
   * @param controller The controller of the view
   */
  public JournalView(Controller controller) {
    this.loader = new FXMLLoader();
    this.loader.setLocation(getClass().getClassLoader()
        .getResource("journal.fxml"));
    this.loader.setController(controller);
  }

  /**
   * Load the view
   *
   * @return the scene for the view
   * @throws IllegalStateException Thrown when the journal is unable to be loaded
   */
  @Override
  public Scene load() throws IllegalStateException {
    try {
      return this.loader.load();
    } catch (IOException e) {
      throw new IllegalStateException("Unable to load our journal");
    }
  }
}
