package cs3500.pa05.json;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa05.model.json.FileCompiler;
import java.io.File;
import org.junit.jupiter.api.Test;

class FileCompilerTest {
  File file = new File("src/main/resources/sample.txt");

  @Test
  void compile() {
    try {
      assertEquals(FileCompiler.compile(file), "this is a test" + System.lineSeparator());
    } catch (Exception e) {
      fail();
    }

  }
}