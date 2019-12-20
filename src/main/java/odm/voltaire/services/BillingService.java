package odm.voltaire.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

public class BillingService {

  private final int DAYS_IN_A_WEEK = 7;

  public Map<DayOfWeek, Integer> CalculateDays(final LocalDate start, final LocalDate end) {

    final int days = Period.between(start, end).getDays();
    if (days < 0) {
      throw new IllegalArgumentException("End date needs to come after start date");
    }

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

}