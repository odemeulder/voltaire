package odm.voltaire.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

public class CalendarService {
  private final int DAYS_IN_A_WEEK = 7;

  // Calculates, for each day of the week, their occurrence, between two dates, inclusive start and end 
  public Map<DayOfWeek, Integer> DayOfWeekFrequencyBetween(final LocalDate start, final LocalDate end) {

    final int days = DaysBetween(start, end);
    final int weeks = days / DAYS_IN_A_WEEK;
    final Map<DayOfWeek, Integer> rv = new HashMap<>();
    rv.put(DayOfWeek.MONDAY, weeks);
    rv.put(DayOfWeek.TUESDAY, weeks);
    rv.put(DayOfWeek.WEDNESDAY, weeks);
    rv.put(DayOfWeek.THURSDAY, weeks);
    rv.put(DayOfWeek.SATURDAY, weeks);
    rv.put(DayOfWeek.FRIDAY, weeks);
    rv.put(DayOfWeek.SUNDAY, weeks);

    final int remainder = days % DAYS_IN_A_WEEK;
    DayOfWeek dayOfWeek = start.getDayOfWeek();
    for ( int i = 0; i < remainder; i++) {
      rv.put(dayOfWeek.plus(i), rv.get(dayOfWeek.plus(i)) + 1);
    }
    return rv;
  }

  public int DaysBetween(LocalDate start, LocalDate end) {
    final int days = Period.between(start, end.plusDays(1)).getDays();
    if (days <= 0) {
      throw new IllegalArgumentException("End date needs to come after start date");
    }
    return days;
  }

}