package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A json class for representing a journal object
 *
 * @param week WeekJson of the journal
 * @param quote Quotes and notes section
 */
public record JournalJson(
    @JsonProperty("week") WeekJson week,
    @JsonProperty("quote") String quote) {
}
