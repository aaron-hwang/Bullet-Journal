package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A json to represent an event object
 */
public record EventJson(
    @JsonProperty("name") String name,
    @JsonProperty("description") String description,
    @JsonProperty("dayOfTheWeek") String dayOfWeek,
    @JsonProperty("startTime") String startTime,
    @JsonProperty("duration") String duration
) {
}
