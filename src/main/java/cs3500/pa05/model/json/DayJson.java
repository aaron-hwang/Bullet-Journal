package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Record class for representing a json of a Day object
 */
public record DayJson(
    @JsonProperty("dayOfTheWeek") String dayOfTheWeek,
    @JsonProperty("tasks") List<TaskJson> tasks,
    @JsonProperty("events") List<EventJson> events
) {
}
