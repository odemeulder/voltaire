package odm.voltaire.services;

import static org.junit.Assert.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import odm.voltaire.models.Product;
import odm.voltaire.models.Subscription;
import odm.voltaire.models.SubscriptionProduct;

public class BillingServiceTest {
  private static final double DELTA = 1e-15;

  @Test
  public void CalculateChargesForSubscriptionProductDayOfWeekSpecific() {
    double dayRate = WednesdayHdProduct().getDayRate();
    SubscriptionProduct sp = new SubscriptionProduct();
    sp.setProduct(WednesdayHdProduct());

    List<TestCase> cases = new ArrayList<>();
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,2), dayRate * 1));
    cases.add(new TestCase(LocalDate.of(2020,1,2), LocalDate.of(2020,1,3), 0.0));
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,14), dayRate * 2));
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,28), dayRate * 4));
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,29), dayRate * 5));
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,30), dayRate * 5));

    CalendarService cs = new CalendarService();
    BillingService bs = new BillingService(cs);
    for (TestCase tc: cases) {
      assertEquals((Double)tc.expected, (Double)bs.CalculateChargesForSubscriptionProductBetween(tc.start, tc.end, sp));
    }
  }

  @Test
  public void CalculateChargesForSubscriptionProduct() {
    double dayRate = DigitalProduct().getDayRate();
    SubscriptionProduct sp = new SubscriptionProduct();
    sp.setProduct(DigitalProduct());

    List<TestCase> cases = new ArrayList<>();
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,1), dayRate * 1));
    cases.add(new TestCase(LocalDate.of(2020,1,2), LocalDate.of(2020,1,3), dayRate * 2));
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,14), dayRate * 14));
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,28), dayRate * 28));

    CalendarService cs = new CalendarService();
    BillingService bs = new BillingService(cs);
    for (TestCase tc: cases) {
      assertEquals((Double)tc.expected, (Double)bs.CalculateChargesForSubscriptionProductBetween(tc.start, tc.end, sp));
    }
  }

  @Test
  public void CaclulateChargesForSubscriptionBetween() {
    SubscriptionProduct spWed = new SubscriptionProduct();
    spWed.setProduct(WednesdayHdProduct());
    SubscriptionProduct spSun1 = new SubscriptionProduct();
    spSun1.setProduct(SundayHdProduct());
    SubscriptionProduct spSun2 = new SubscriptionProduct();
    spSun2.setProduct(SundayHdProduct());
    SubscriptionProduct spXword = new SubscriptionProduct();
    spXword.setProduct(XWordProduct());
    SubscriptionProduct spCooking = new SubscriptionProduct();
    spCooking.setProduct(DigitalProduct());
    Subscription s = new Subscription();
    List<SubscriptionProduct> coll = new ArrayList<>();
    coll.add(spWed);
    coll.add(spSun1);
    coll.add(spSun2);
    coll.add(spXword);
    coll.add(spCooking);
    s.setProducts(coll);

    List<TestCase> cases = new ArrayList<>();
    // 1 Wednesday      1.0
    // 0 Sundays        0.0
    // 1 day of xword   0.15
    // 1 day of cooking 0.20
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,1), 1.35));
    // 0 Wednesday      0.0
    // 2 Sundays        4.0
    // 1 day of xword   0.15
    // 1 day of cooking 0.20
    cases.add(new TestCase(LocalDate.of(2020,1,5), LocalDate.of(2020,1,5), 4.35));
    // 1 Wednesday      1.0
    // 2 Sundays        4.0
    // 5 day of xword   0.75
    // 5 day of cooking 1.0
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,5), 6.75));
    // 4 Wednesday       4.0
    // 8 Sundays        16.0
    // 28 day of xword   4.2
    // 28 day of cooking 5.6
    cases.add(new TestCase(LocalDate.of(2020,1,1), LocalDate.of(2020,1,28), 29.8));
    CalendarService cs = new CalendarService();
    BillingService bs = new BillingService(cs);
    for (TestCase tc: cases) {
      assertEquals(tc.expected, bs.CalculateChargesForSubscriptionBetween(tc.start, tc.end, s), DELTA);
    }
  }

  private Product WednesdayHdProduct() {
    Product p = new Product();
    p.setDayRate(1.0);
    p.setWeekdaySpecific(true);
    p.setDeliveryDay(DayOfWeek.WEDNESDAY);
    return p;
  }

  private Product SundayHdProduct() {
    Product p = new Product();
    p.setDayRate(2.0);
    p.setWeekdaySpecific(true);
    p.setDeliveryDay(DayOfWeek.SUNDAY);
    return p;
  }
  private Product DigitalProduct() {
    Product p = new Product();
    p.setDayRate(0.2);
    p.setWeekdaySpecific(false);
    return p;
  }
  private Product XWordProduct() {
    Product p = new Product();
    p.setDayRate(0.15);
    p.setWeekdaySpecific(false);
    return p;
  }

  private class TestCase {
    public LocalDate start;
    public LocalDate end;
    public Double expected;
    public TestCase(LocalDate d1, LocalDate d2, Double expected) {
      this.start = d1;
      this.end = d2;
      this.expected = expected;
    }
  }

}