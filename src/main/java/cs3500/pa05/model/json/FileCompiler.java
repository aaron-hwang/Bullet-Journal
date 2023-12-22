package cs3500.pa05.model.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Compiles files
 */
public class FileCompiler {

  /**
   * Compiles a given file to a String
   * NOTE: Preserves newlines and other such formatting.
   *
   * @param file File to compile
   * @return The compiled file, as a string
   * @throws FileNotFoundException when file cannot be found
   */
  public static String compile(File file) throws FileNotFoundException {
    Scanner scanner = new Scanner(file);
    StringBuilder builder = new StringBuilder();
    while (scanner.hasNextLine()) {
      builder.append(scanner.nextLine()).append(System.lineSeparator());
    }

    return builder.toString();
  }

}
