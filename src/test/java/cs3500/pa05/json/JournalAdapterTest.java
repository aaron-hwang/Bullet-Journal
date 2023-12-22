package cs3500.pa05.json;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.DayOfWeek;
import cs3500.pa05.model.Journal;
import cs3500.pa05.model.JournalImpl;
import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.FileCompiler;
import cs3500.pa05.model.json.JournalAdapter;
import cs3500.pa05.model.json.JournalJson;
import cs3500.pa05.model.json.WeekJson;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for journal adapter class
 */
class JournalAdapterTest {
  JournalJson journalJson;
  WeekJson weekJson;
  List<DayJson> days;


  @BeforeEach
  public void setup() {
    days = new ArrayList<>();
    for (DayOfWeek day : DayOfWeek.values()) {
      days.add(new DayJson(day.toString(), new ArrayList<>(), new ArrayList<>()));
    }
    weekJson = new WeekJson(days, 0, 0);
    journalJson = new JournalJson(weekJson, "");
  }

  @Test
  void jsonToJournal() {
    Journal journal = JournalAdapter.jsonToJournal(journalJson);
    Journal emptyJournal = new JournalImpl();
    assertInstanceOf(Journal.class, journal);
    assertEquals(
        journal.getOneWeekModel().getMaxTasks(),
        emptyJournal.getOneWeekModel().getMaxTasks());
    assertEquals(
        journal.getOneWeekModel().getMaxEvents(),
        emptyJournal.getOneWeekModel().getMaxTasks()
    );


  }

  @Test
  void journalToJson() {
    Journal journal = new JournalImpl();
    JournalJson json = JournalAdapter.journalToJson(journal);
    assertEquals(json.week().maxEvents(), 0);
    assertEquals(json.week().maxTasks(), 0);
    int i = 0;
    for (DayJson dayJson : json.week().days()) {
      assertEquals(DayOfWeek.valueOf(dayJson.dayOfTheWeek()).ordinal(), i);
      i++;
    }
  }

  @Test
  void writeToFile() {
    try {
      JournalAdapter.writeToFile(new File("sample.bujo"), new JournalImpl());
      assertTrue(FileCompiler.compile(new File("sample.bujo")).contains("SUNDAY"));
    } catch (Exception e) {
      fail();
    }
    try {
      assertThrows(IOException.class,
          () -> JournalAdapter.writeToFile(new File("src"), new JournalImpl()));
    } catch (Exception e) {
      fail();
    }

  }

  @Test
  void createFromFile() {
    try {
      assertInstanceOf(Journal.class,
          JournalAdapter.createFromFile(new File("sample.bujo")));
    } catch (Exception e) {
      fail();
    }

    try {
      assertThrows(IllegalArgumentException.class,
          () -> JournalAdapter.createFromFile(new File("sample.txt")));
    } catch (Exception e) {
      fail();
    }

    try {
      assertThrows(IllegalArgumentException.class,
          () -> JournalAdapter.createFromFile(new File("among.bujo")));
    } catch (Exception e) {
      fail();
    }
  }
}