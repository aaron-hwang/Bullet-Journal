package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Json for representing a task
 */
public record TaskJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("dayOfWeek") String dayOfWeek,
    @JsonProperty("isComplete") boolean complete,
    @JsonProperty("category") String category
) {
}
