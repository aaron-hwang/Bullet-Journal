package cs3500.pa05.view;

import javafx.scene.Scene;

/**
 * A view for anything
 */
public interface View {

  /**
   * Load a scene
   *
   * @return the scene
   * @throws IllegalStateException if null scene/fxml file doesn't exist
   */
  Scene load() throws IllegalStateException;
}
