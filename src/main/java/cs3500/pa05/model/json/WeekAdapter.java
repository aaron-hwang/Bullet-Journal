package cs3500.pa05.model.json;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.OneWeek;
import cs3500.pa05.model.OneWeekModel;
import java.util.ArrayList;
import java.util.List;

/**
 * An adapter that adapts weeks to their respective json, and vice versa
 */
public class WeekAdapter {

  /**
   * Adapts a week json to a OneWeek
   *
   * @param weekJson the week json to adapt
   * @return OneWeek object
   */
  public static OneWeek jsonToWeek(WeekJson weekJson) {
    List<Day> dayList = new ArrayList<>();
    for (DayJson dayJson : weekJson.days()) {
      dayList.add(DayAdapter.jsonToDay(dayJson));
    }

    return new OneWeek(dayList, weekJson.maxEvents(), weekJson.maxTasks());
  }

  /**
   * Adapt a WeekModel to a WeekJson
   *
   * @param weekModel The model to adapt
   * @return A json representing the data of the week
   */
  public static WeekJson weekToJson(OneWeekModel weekModel) {
    List<DayJson> dayJsons = new ArrayList<>();
    for (Day d : weekModel.getDays()) {
      dayJsons.add(DayAdapter.dayToJson(d));
    }
    return new WeekJson(dayJsons, weekModel.getMaxEvents(), weekModel.getMaxTasks());
  }

}
