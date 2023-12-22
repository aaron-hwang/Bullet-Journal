package cs3500.pa05.model.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A Json utility class
 */
public class JsonUtils {
  /**
   * Converts a given record object to a JsonNode.
   *
   * @param record the record to convert
   * @return the JsonNode representation of the given record
   * @throws IllegalArgumentException if the record could not be converted correctly
   */
  public static JsonNode serializeRecord(Record record) throws IllegalArgumentException {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.convertValue(record, JsonNode.class);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Given record cannot be serialized");
    }
  }

  /**
   * Serialize a string into a json node
   *
   * @param input the string to seralize
   * @return The serialized json node
   */
  public static JsonNode serializeString(String input) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.readTree(input);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Something went wrong");
    }
  }


}
