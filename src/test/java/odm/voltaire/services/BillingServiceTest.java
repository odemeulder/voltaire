package odm.voltaire.services;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.Map;
import java.util.HashMap;


public class BillingServiceTest {
    
  @Test(expected = IllegalArgumentException.class)
  public void testCalculateDaysThrowsException() {

    BillingService bs = new BillingService();
    LocalDate d1 = LocalDate.of(2020, 1, 2);
    LocalDate d2 = LocalDate.of(2020, 1, 1);
    bs.CalculateDays(d1, d2);
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
