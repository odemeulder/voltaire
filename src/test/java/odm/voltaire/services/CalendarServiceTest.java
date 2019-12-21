package odm.voltaire.services;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.Map;
import java.util.HashMap;


public class CalendarServiceTest {
    
  @Test(expected = IllegalArgumentException.class)
  public void testCalculateDaysThrowsException() {

    CalendarService cs = new CalendarService();
    LocalDate d1 = LocalDate.of(2020, 1, 2);
    LocalDate d2 = LocalDate.of(2020, 1, 1);
    cs.DayOfWeekFrequencyBetween(d1, d2);
  }

  @Test
  public void testFrequenciesForOneDay() {
    Map<DayOfWeek, Integer> expected = newSet();
    expected.put(DayOfWeek.WEDNESDAY,1);
    LocalDate d1 = LocalDate.of(2020, 1, 1);
    LocalDate d2 = LocalDate.of(2020, 1, 1);
    CalendarService cs = new CalendarService();

    Map<DayOfWeek, Integer> actual = cs.DayOfWeekFrequencyBetween(d1, d2);

    assertTrue(mapsAreEqual(expected, actual));
  }

  @Test
  public void testFrequenciesForTwoDays() {
    Map<DayOfWeek, Integer> expected = newSet();
    expected.put(DayOfWeek.WEDNESDAY,1);
    expected.put(DayOfWeek.THURSDAY,1);
    LocalDate d1 = LocalDate.of(2020, 1, 1);
    LocalDate d2 = LocalDate.of(2020, 1, 2);
    CalendarService cs = new CalendarService();

    Map<DayOfWeek, Integer> actual = cs.DayOfWeekFrequencyBetween(d1, d2);

    assertTrue(mapsAreEqual(expected, actual));
  }

  @Test
  public void testFrequenciesForSixDays() {
    Map<DayOfWeek, Integer> expected = newSet();
    expected.put(DayOfWeek.WEDNESDAY,1);
    expected.put(DayOfWeek.THURSDAY,1);
    expected.put(DayOfWeek.FRIDAY,1);
    expected.put(DayOfWeek.SATURDAY,1);
    expected.put(DayOfWeek.SUNDAY,1);
    expected.put(DayOfWeek.MONDAY,1);
    LocalDate d1 = LocalDate.of(2020, 1, 1);
    LocalDate d2 = LocalDate.of(2020, 1, 6);
    CalendarService cs = new CalendarService();

    Map<DayOfWeek, Integer> actual = cs.DayOfWeekFrequencyBetween(d1, d2);

    assertTrue(mapsAreEqual(expected, actual));
  }


  @Test
  public void testFrequenciesForSevenDays() {
    Map<DayOfWeek, Integer> expected = newSet();
    expected.put(DayOfWeek.WEDNESDAY,1);
    expected.put(DayOfWeek.THURSDAY,1);
    expected.put(DayOfWeek.FRIDAY,1);
    expected.put(DayOfWeek.SATURDAY,1);
    expected.put(DayOfWeek.SUNDAY,1);
    expected.put(DayOfWeek.MONDAY,1);
    expected.put(DayOfWeek.TUESDAY,1);
    LocalDate d1 = LocalDate.of(2020, 1, 1);
    LocalDate d2 = LocalDate.of(2020, 1, 7);
    CalendarService cs = new CalendarService();

    Map<DayOfWeek, Integer> actual = cs.DayOfWeekFrequencyBetween(d1, d2);

    assertTrue(mapsAreEqual(expected, actual));
  }

  @Test
  public void testFrequenciesForEightDays() {
    Map<DayOfWeek, Integer> expected = newSet();
    expected.put(DayOfWeek.WEDNESDAY,2);
    expected.put(DayOfWeek.THURSDAY,1);
    expected.put(DayOfWeek.FRIDAY,1);
    expected.put(DayOfWeek.SATURDAY,1);
    expected.put(DayOfWeek.SUNDAY,1);
    expected.put(DayOfWeek.MONDAY,1);
    expected.put(DayOfWeek.TUESDAY,1);
    LocalDate d1 = LocalDate.of(2020, 1, 1);
    LocalDate d2 = LocalDate.of(2020, 1, 8);
    CalendarService cs = new CalendarService();

    Map<DayOfWeek, Integer> actual = cs.DayOfWeekFrequencyBetween(d1, d2);

    assertTrue(mapsAreEqual(expected, actual));
  }

  @Test
  public void testFrequenciesFor27Days() {
    Map<DayOfWeek, Integer> expected = newSet();
    expected.put(DayOfWeek.WEDNESDAY,4);
    expected.put(DayOfWeek.THURSDAY,4);
    expected.put(DayOfWeek.FRIDAY,4);
    expected.put(DayOfWeek.SATURDAY,4);
    expected.put(DayOfWeek.SUNDAY,4);
    expected.put(DayOfWeek.MONDAY,4);
    expected.put(DayOfWeek.TUESDAY,3);
    LocalDate d1 = LocalDate.of(2020, 1, 1);
    LocalDate d2 = LocalDate.of(2020, 1, 27);
    CalendarService cs = new CalendarService();

    Map<DayOfWeek, Integer> actual = cs.DayOfWeekFrequencyBetween(d1, d2);

    assertTrue(mapsAreEqual(expected, actual));
  }
  @Test
  public void testFrequenciesFor28Days() {
    Map<DayOfWeek, Integer> expected = newSet();
    expected.put(DayOfWeek.WEDNESDAY,4);
    expected.put(DayOfWeek.THURSDAY,4);
    expected.put(DayOfWeek.FRIDAY,4);
    expected.put(DayOfWeek.SATURDAY,4);
    expected.put(DayOfWeek.SUNDAY,4);
    expected.put(DayOfWeek.MONDAY,4);
    expected.put(DayOfWeek.TUESDAY,4);
    LocalDate d1 = LocalDate.of(2020, 1, 1);
    LocalDate d2 = LocalDate.of(2020, 1, 28);
    CalendarService cs = new CalendarService();

    Map<DayOfWeek, Integer> actual = cs.DayOfWeekFrequencyBetween(d1, d2);

    assertTrue(mapsAreEqual(expected, actual));
  }

  @Test
  public void testFrequenciesFor29Days() {
    Map<DayOfWeek, Integer> expected = newSet();
    expected.put(DayOfWeek.WEDNESDAY,5);
    expected.put(DayOfWeek.THURSDAY,4);
    expected.put(DayOfWeek.FRIDAY,4);
    expected.put(DayOfWeek.SATURDAY,4);
    expected.put(DayOfWeek.SUNDAY,4);
    expected.put(DayOfWeek.MONDAY,4);
    expected.put(DayOfWeek.TUESDAY,4);
    LocalDate d1 = LocalDate.of(2020, 1, 1);
    LocalDate d2 = LocalDate.of(2020, 1, 29);
    CalendarService cs = new CalendarService();

    Map<DayOfWeek, Integer> actual = cs.DayOfWeekFrequencyBetween(d1, d2);

    assertTrue(mapsAreEqual(expected, actual));
  }

  @Test public void daysBetween() {
    CalendarService cs = new CalendarService();
    assertEquals(1L, cs.DaysBetween(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1)));
    assertEquals(2L, cs.DaysBetween(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 2)));
    assertEquals(3L, cs.DaysBetween(LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 3)));
  }

  // assertEquals(expected.size(), 7);
  // assertEquals(actual.size(),7);
  // assertEquals(0, (int)actual.get(DayOfWeek.MONDAY));
  // assertEquals(0, (int)actual.get(DayOfWeek.TUESDAY));
  // assertEquals(1, (int)actual.get(DayOfWeek.WEDNESDAY));
  // assertEquals(0, (int)actual.get(DayOfWeek.THURSDAY));
  // assertEquals(0, (int)actual.get(DayOfWeek.FRIDAY));
  // assertEquals(0, (int)actual.get(DayOfWeek.SATURDAY));
  // assertEquals(0, (int)actual.get(DayOfWeek.SUNDAY));

  private Map<DayOfWeek, Integer> newSet() {
    final Map<DayOfWeek, Integer> rv = new HashMap<>();
    rv.put(DayOfWeek.MONDAY, 0);
    rv.put(DayOfWeek.TUESDAY, 0);
    rv.put(DayOfWeek.WEDNESDAY, 0);
    rv.put(DayOfWeek.THURSDAY, 0);
    rv.put(DayOfWeek.SATURDAY, 0);
    rv.put(DayOfWeek.FRIDAY, 0);
    rv.put(DayOfWeek.SUNDAY, 0);
    return rv;
  }

  // Testing the private mapsAreEqual private method
  @Test
  public void testInternalMapComparer() {
    final Map<DayOfWeek, Integer> m1 = new HashMap<>();
    m1.put(DayOfWeek.SUNDAY, 0);
    m1.put(DayOfWeek.MONDAY, 1);

    final Map<DayOfWeek, Integer> m2 = new HashMap<>();
    m2.put(DayOfWeek.SUNDAY, 0);
    m2.put(DayOfWeek.MONDAY, 1);
    assertTrue(mapsAreEqual(m1,m2));

    final Map<DayOfWeek, Integer> m3 = new HashMap<>();
    m3.put(DayOfWeek.SUNDAY, 0);
    m3.put(DayOfWeek.MONDAY, 2);
    assertFalse(mapsAreEqual(m1,m3));

    final Map<DayOfWeek, Integer> m4 = new HashMap<>();
    m4.put(DayOfWeek.SUNDAY, 0);
    assertFalse(mapsAreEqual(m1,m4));

    final Map<DayOfWeek, Integer> m5 = new HashMap<>();
    m5.put(DayOfWeek.SUNDAY, 0);
    m5.put(DayOfWeek.MONDAY, 0);
    m5.put(DayOfWeek.TUESDAY, 0);
    assertFalse(mapsAreEqual(m1,m5));
  }

  private boolean mapsAreEqual(Map<DayOfWeek, Integer> m1, Map<DayOfWeek, Integer> m2) {
    if (m1.size() != m2.size()) return false;

    return m1.entrySet().stream()
      .allMatch(e -> e.getValue().equals(m2.get(e.getKey())));
  }
}
