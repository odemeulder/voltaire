package odm.voltaire.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import odm.voltaire.fixtures.ProductFixture;
import odm.voltaire.fixtures.SubscriptionFixture;
import odm.voltaire.fixtures.SubscriptionProductFixture;
import odm.voltaire.models.Subscription;
import odm.voltaire.models.SubscriptionProduct;

public class BillingUtilitiesTest {
  // private static final Double DELTA = 1e-15;

  private BillingUtilities bu;

  @Before
  public void Setup() {
    CalendarService cs = new CalendarService();
    bu = new BillingUtilities(cs);
  }

  @Test
  public void CalculateChargesForSubscriptionProductDayOfWeekSpecific() {
    BigDecimal dayRate = ProductFixture.HdWednesday().getDayRate();

    List<TestCase> cases = new ArrayList<>();
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,2), dayRate.multiply(new BigDecimal(1))));
    cases.add(new TestCase(LocalDate.of(2020,1,2), LocalDate.of(2020,1,3), BigDecimal.ZERO));
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,14), dayRate.multiply(new BigDecimal(2))));
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,28), dayRate.multiply(new BigDecimal(4))));
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,29), dayRate.multiply(new BigDecimal(5))));
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,30), dayRate.multiply(new BigDecimal(5))));

    for (TestCase tc: cases) {
      BigDecimal actual = bu.CalculateChargesForSubscriptionProductBetween(
        tc.start, 
        tc.end, 
        SubscriptionProductFixture.HdWednesday(),
        SubscriptionFixture.HdSundayOnly());
      
      assertEquals(tc.expected, actual);
    }
  }

  @Test
  public void CalculateChargesForSubscriptionProduct() {
    BigDecimal dayRate = ProductFixture.Digital().getDayRate();

    List<TestCase> cases = new ArrayList<>();
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,1), dayRate.multiply(new BigDecimal(1))));
    cases.add(new TestCase(LocalDate.of(2020,1,2), LocalDate.of(2020,1,3), dayRate.multiply(new BigDecimal(2))));
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,14), dayRate.multiply(new BigDecimal(14))));
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,28), dayRate.multiply(new BigDecimal(28))));

    for (TestCase tc: cases) {
      BigDecimal actual = bu.CalculateChargesForSubscriptionProductBetween(
        tc.start, 
        tc.end, 
        SubscriptionProductFixture.Digital(),
        SubscriptionFixture.DigitalOnly());
      assertEquals(tc.expected, actual);
    }
  }

  @Test
  public void CaclulateChargesForSubscriptionBetween() {
    SubscriptionProduct spWed = SubscriptionProductFixture.HdWednesday();
    SubscriptionProduct spSun1 = SubscriptionProductFixture.HdSunday();
    SubscriptionProduct spSun2 = SubscriptionProductFixture.HdSunday();
    SubscriptionProduct spXword = SubscriptionProductFixture.XWord();
    SubscriptionProduct spCooking = SubscriptionProductFixture.Digital();
    Subscription s = new Subscription();
    List<SubscriptionProduct> coll = new ArrayList<>();
    coll.add(spWed);
    coll.add(spSun1);
    coll.add(spSun2);
    coll.add(spXword);
    coll.add(spCooking);
    s.setProducts(coll);
    s.setStartDate(LocalDate.of(2020,1,1));

    List<TestCase> cases = new ArrayList<>();
    // 1 Wednesday      1.0
    // 0 Sundays        0.0
    // 1 day of xword   0.15
    // 1 day of cooking 0.20
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,1), new BigDecimal(1.35)));
    // 0 Wednesday      0.0
    // 2 Sundays        4.0
    // 1 day of xword   0.15
    // 1 day of cooking 0.20
    cases.add(new TestCase(LocalDate.of(2020,1,5), LocalDate.of(2020,1,5), new BigDecimal(4.35)));
    // 1 Wednesday      1.0
    // 2 Sundays        4.0
    // 5 day of xword   0.75
    // 5 day of cooking 1.0
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,5), new BigDecimal(6.75)));
    // 4 Wednesday       4.0
    // 8 Sundays        16.0
    // 28 day of xword   4.2
    // 28 day of cooking 5.6
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,28), new BigDecimal(29.8)));
    for (TestCase tc: cases) {
      BigDecimal actual = bu.CalculateChargesForSubscriptionBetween(tc.start, tc.end, s);
      assertEquals(tc.expected.setScale(2, RoundingMode.HALF_EVEN), actual.setScale(2, RoundingMode.HALF_EVEN));
    }
  }

  private class TestCase {
    public LocalDate start;
    public LocalDate end;
    public BigDecimal expected;
    public TestCase(LocalDate d1, LocalDate d2, BigDecimal expected) {
      this.start = d1;
      this.end = d2;
      this.expected = expected;
    }
  }

}