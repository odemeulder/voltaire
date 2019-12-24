package odm.voltaire.services;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

import odm.voltaire.fixtures.SubscriptionProductFixture;
import odm.voltaire.fixtures.SuspensionFixture;
import odm.voltaire.models.Subscription;
import odm.voltaire.models.SubscriptionProduct;
import odm.voltaire.models.Suspension;
import odm.voltaire.models.SuspensionCredit;

/**
 * SuspensionCreditServiceTest
 */
public class SuspensionCreditServiceTest {
  private SuspensionCreditService scs;

  @Before
  public void Setup() {
    CalendarService cs = new CalendarService();
    BillingUtilities bu = new BillingUtilities(cs);
    scs = new SuspensionCreditService(bu);
  }

  @Test
  public void CalculateSuspensionCredits() {
    LocalDate d1 = LocalDate.of(2020,1,1);
    LocalDate d2 = LocalDate.of(2020,1,28);
    BigDecimal dailyRate = SubscriptionProductFixture.Digital().getProduct().getDayRate();

    List<TestCase> cases = new ArrayList<>();
    cases.add(new TestCase(getSub(SuspensionFixture.Past()), new ArrayList<>()));
    List<SuspensionCredit> credits1 = new ArrayList<>();
    credits1.add(new SuspensionCredit(d1, 
                                      SuspensionFixture.OverlapStart().get(0).getEndDate(), 
                                      dailyRate.multiply(new BigDecimal(5))));
    cases.add(new TestCase(getSub(SuspensionFixture.OverlapStart()), credits1));
    List<SuspensionCredit> credits2 = new ArrayList<>();
    credits2.add(new SuspensionCredit(SuspensionFixture.PresentFromStart().get(0).getStartDate(), 
                                      SuspensionFixture.PresentFromStart().get(0).getEndDate(), 
                                      dailyRate.multiply(new BigDecimal(6))));
    cases.add(new TestCase(getSub(SuspensionFixture.PresentFromStart()), credits2));
    List<SuspensionCredit> credits3 = new ArrayList<>();
    credits3.add(new SuspensionCredit(SuspensionFixture.Middle().get(0).getStartDate(), 
                                      SuspensionFixture.Middle().get(0).getEndDate(), 
                                      dailyRate.multiply(new BigDecimal(3))));
    cases.add(new TestCase(getSub(SuspensionFixture.Middle()), credits3));
    List<SuspensionCredit> credits4 = new ArrayList<>();
    credits4.add(new SuspensionCredit(SuspensionFixture.PresentToEnd().get(0).getStartDate(), 
                                      SuspensionFixture.PresentToEnd().get(0).getEndDate(), 
                                      dailyRate.multiply(new BigDecimal(8))));
    cases.add(new TestCase(getSub(SuspensionFixture.PresentToEnd()), credits4));
    List<SuspensionCredit> credits5 = new ArrayList<>();
    credits5.add(new SuspensionCredit(SuspensionFixture.OverlapEnd().get(0).getStartDate(), 
                                      d2, 
                                      dailyRate.multiply(new BigDecimal(9))));
    cases.add(new TestCase(getSub(SuspensionFixture.OverlapEnd()), credits5));
    cases.add(new TestCase(getSub(SuspensionFixture.Future()), new ArrayList<>()));
    List<SuspensionCredit> credits6 = new ArrayList<>();
    credits6.add(new SuspensionCredit(SuspensionFixture.TwoSuspensions().get(0).getStartDate(), 
                                      SuspensionFixture.TwoSuspensions().get(0).getEndDate(), 
                                      dailyRate.multiply(new BigDecimal(5))));
    credits6.add(new SuspensionCredit(SuspensionFixture.TwoSuspensions().get(1).getStartDate(), 
                                      SuspensionFixture.TwoSuspensions().get(1).getEndDate(), 
                                      dailyRate.multiply(new BigDecimal(10))));
    cases.add(new TestCase(getSub(SuspensionFixture.TwoSuspensions()), credits6));

    for (TestCase tc : cases) {
      assertEquals(tc.expected, scs.CalculateSuspensionCreditsBetween(d1, d2, tc.subscription));
    }
  }

  private Subscription getSub(List<Suspension> suspensionList) {
    Subscription s = new Subscription();
    s.setStartDate(LocalDate.of(2020,1,1));
    List<SubscriptionProduct> spl = new ArrayList<>();
    SubscriptionProduct sp = SubscriptionProductFixture.Digital();
    sp.setPendingSuspensions(suspensionList);
    spl.add(sp);
    s.setProducts(spl);
    return s;
  }

  private class TestCase {
    public Subscription subscription;
    public List<SuspensionCredit> expected;
    public TestCase(Subscription s, List<SuspensionCredit> credits) {
      subscription = s;
      expected = credits;
    }
  }
}