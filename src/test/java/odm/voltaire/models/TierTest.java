package odm.voltaire.models;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * TierTest
 */
public class TierTest {

  @Test
  public void dailyRate() {
    Tier t1 = new Tier();
    t1.setRate(28, new BigDecimal(14.0));
    assertEquals(new BigDecimal(0.5), t1.getDailyRate());
    Tier t2 = new Tier();
    t2.setRate(1, new BigDecimal(0.2));
    assertEquals(new BigDecimal(0.2), t2.getDailyRate());
  }
}