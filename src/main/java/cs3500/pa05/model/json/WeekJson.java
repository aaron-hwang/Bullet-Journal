package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents a week object in json format
 *
 * @param days list of days to adapt
 * @param maxEvents maximum events for this week
 * @param maxTasks max tasks for this week
 */
public record WeekJson(
    @JsonProperty("days") List<DayJson> days,
    @JsonProperty("max-events") int maxEvents,
    @JsonProperty("max-tasks") int maxTasks
) {
}
