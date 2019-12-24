package odm.voltaire.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import odm.voltaire.fixtures.SubscriptionFixture;

public class BillingServiceTest {
  // private static final double DELTA = 1e-15;

  private BillingService bs;

  @Before
  public void Setup() {
    CalendarService cs = new CalendarService();
    BillingUtilities bu = new BillingUtilities(cs);
    SuspensionCreditService scs = new SuspensionCreditService(bu);
    bs = new BillingService(bu, scs);
  }

  @Test
  public void BasicSundayOnlyBilling() {
    LocalDate billDate = LocalDate.of(2020,1,1);

    assertEquals(new BigDecimal("32"), bs.RunBilling(billDate, SubscriptionFixture.HdSevenDay()));
    assertEquals(new BigDecimal("8"), bs.RunBilling(billDate, SubscriptionFixture.HdSundayOnly()));
    assertEquals(new BigDecimal("5.6").setScale(2), 
      bs.RunBilling(billDate, SubscriptionFixture.DigitalOnly()).setScale(2, RoundingMode.HALF_EVEN));
    // 32 - 13
    assertEquals(new BigDecimal("19"), bs.RunBilling(billDate, SubscriptionFixture.HdSevenDayWithSuspension()));
    // 8 - 4
    assertEquals(new BigDecimal("4"), bs.RunBilling(billDate, SubscriptionFixture.HdSundayOnlyWithSuspension()));
    assertEquals(new BigDecimal("3.4").setScale(2), 
      bs.RunBilling(billDate, SubscriptionFixture.DigitalOnlyWithSuspension()).setScale(2, RoundingMode.HALF_EVEN));

  }

}