package cs3500.pa05.json;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.JsonNode;
import cs3500.pa05.model.JournalImpl;
import cs3500.pa05.model.json.JsonUtils;
import cs3500.pa05.model.json.TaskJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JsonUtilsTest {
  TaskJson taskJson;

  @BeforeEach
  public void setup() {
    taskJson = new TaskJson(
        "Test", "Test", "THURSDAY", false, "");
  }

  @Test
  void serializeRecord() {
    JsonNode node = JsonUtils.serializeRecord(taskJson);
    assertInstanceOf(JsonNode.class, node);
  }

  @Test
  void serializeString() {
    JsonNode node = JsonUtils.serializeRecord(taskJson);
    assertInstanceOf(JsonNode.class, JsonUtils.serializeString(node.toString()));
    try {
      assertThrows(RuntimeException.class,
          () -> JsonUtils.serializeString("hi"));
    } catch (Exception e) {
      fail();
    }
  }
}