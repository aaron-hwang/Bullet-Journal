package cs3500.pa05.model.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.JournalImpl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Adapts a Journal to and from json
 */
public class JournalAdapter {

  /**
   * Converts a JournalJson into a new JournalImpl instance
   *
   * @param json the json to convert
   * @return The journal object
   */
  public static JournalImpl jsonToJournal(JournalJson json) {
    WeekJson week = json.week();
    return new JournalImpl(WeekAdapter.jsonToWeek(week), "");
  }

  /**
   * Convert a given Journal into json holding all its data
   *
   * @param journal Journal to adapt
   * @return A journal json
   */
  public static JournalJson journalToJson(Journal journal) {
    WeekJson weekJson = WeekAdapter.weekToJson(journal.getOneWeekModel());
    return new JournalJson(weekJson, journal.getQuotesAndNotes());
  }

  /**
   * Write a given Journal object to a given file
   *
   * @param file File to write to
   * @param journal Journal object
   * @throws IOException When file cannot be read
   */
  public static void writeToFile(File file, Journal journal) throws IOException {
    FileWriter writer;
    try {
      writer = new FileWriter(file);
    } catch (IOException e) {
      throw new IOException("File could not be found/created");
    }
    JournalJson journalJson = JournalAdapter.journalToJson(journal);
    JsonNode journalNode = JsonUtils.serializeRecord(journalJson);
    writer.write(journalNode.toString());
    writer.close();
  }

  /**
   * Create a Journal Object from a given file
   *
   * @param file file t0 create from
   * @return new journal object
   */
  public static Journal createFromFile(File file) {
    boolean isBujo = file.getName().endsWith(".bujo");
    if (!isBujo) {
      throw new IllegalArgumentException("File must end in .bujo");
    }

    JsonNode node;
    try {
      node = JsonUtils.serializeString(FileCompiler.compile(file));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File could not be found");
    }
    ObjectMapper mapper = new ObjectMapper();
    JournalJson journalJson = mapper.convertValue(node, JournalJson.class);
    return JournalAdapter.jsonToJournal(journalJson);
  }

}
