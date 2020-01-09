package odm.voltaire.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import odm.voltaire.fixtures.ProductFixture;
import odm.voltaire.fixtures.ProductPromoRuleFixture;
import odm.voltaire.fixtures.PromoFixture;
import odm.voltaire.fixtures.SubscriptionFixture;
import odm.voltaire.models.Product;
import odm.voltaire.models.Promo;

/**
 * PromoEligibilityEngineTest
 */
public class PromoEligibilityEngineTest {

  private PromoRulesServiceInterface prs;
  private PromoEligibilityEngine eligibilityEngine;

  @Before
  public void Setup() {
    prs = mock(PromoRulesService.class);
    when(prs.allRules()).thenReturn(ProductPromoRuleFixture.allRules());
    eligibilityEngine = new PromoEligibilityEngine(prs);
  }

  @Test
  public void returnsCorrectNumberOfPromos() {
    // 2 promos
    List<Promo> eligibilePromos = eligibilityEngine.promosFor(ProductFixture.combo123());
    assertEquals(2, eligibilePromos.size());

    // 4 days --> 3 promos
    eligibilePromos = eligibilityEngine.promosFor(ProductFixture.combo1234());
    assertEquals(3, eligibilePromos.size());

    // 5 days --> 4 promos
    eligibilePromos = eligibilityEngine.promosFor(ProductFixture.combo12345());
    assertEquals(4, eligibilePromos.size());

    // one day -> one promo
    eligibilePromos = eligibilityEngine.promosFor(ProductFixture.combo5());
    assertEquals(1, eligibilePromos.size());

    // two days -> one promo
    eligibilePromos = eligibilityEngine.promosFor(ProductFixture.combo45());
    assertEquals(1, eligibilePromos.size());
    
    // no promos eligible
    eligibilePromos = eligibilityEngine.promosFor(ProductFixture.combo12());
    assertEquals(0, eligibilePromos.size());
  }

  @Test
  public void returnsCorrectPromoEligibilityForCompon123() {
    List<Product> products = ProductFixture.combo123();
    assertTrue(eligibilityEngine.isPromoEligibile(PromoFixture.Promo1(), products));
    assertTrue(eligibilityEngine.isPromoEligibile(PromoFixture.Promo2(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Promo3(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Inteligible(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Promo5(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Promo6(), products));
  }

  @Test
  public void returnsCorrectPromoEligibilityForCompon12() {
    List<Product> products = ProductFixture.combo12();
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Promo1(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Promo2(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Promo3(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Inteligible(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Promo5(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Promo6(), products));
  }
  
  @Test
  public void returnsCorrectPromoEligibilityForCompon1234() {
    List<Product> products = ProductFixture.combo1234();
    assertTrue(eligibilityEngine.isPromoEligibile(PromoFixture.Promo1(), products));
    assertTrue(eligibilityEngine.isPromoEligibile(PromoFixture.Promo2(), products));
    assertTrue(eligibilityEngine.isPromoEligibile(PromoFixture.Promo3(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Inteligible(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Promo5(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Promo6(), products));
  }
  @Test
  public void returnsCorrectPromoEligibilityForCompon12345() {
    List<Product> products = ProductFixture.combo12345();
    assertTrue(eligibilityEngine.isPromoEligibile(PromoFixture.Promo1(), products));
    assertTrue(eligibilityEngine.isPromoEligibile(PromoFixture.Promo2(), products));
    assertTrue(eligibilityEngine.isPromoEligibile(PromoFixture.Promo3(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Inteligible(), products));
    assertTrue(eligibilityEngine.isPromoEligibile(PromoFixture.Promo5(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Promo6(), products));
  }
  @Test
  public void returnsCorrectPromoEligibilityForCompon5() {
    List<Product> products = ProductFixture.combo5();
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Promo1(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Promo2(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Promo3(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Inteligible(), products));
    assertTrue(eligibilityEngine.isPromoEligibile(PromoFixture.Promo5(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Promo6(), products));
  }

  @Test
  public void returnsCorrectPromoEligibilityForCompon45() {
    List<Product> products = ProductFixture.combo5();
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Promo1(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Promo2(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Promo3(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Inteligible(), products));
    assertTrue(eligibilityEngine.isPromoEligibile(PromoFixture.Promo5(), products));
    assertFalse(eligibilityEngine.isPromoEligibile(PromoFixture.Promo6(), products));
  }

  @Test
  public void returnsEligibilityForSubscription() {
    assertFalse(eligibilityEngine.isPromoEligibleForSubscription(PromoFixture.Promo1(), SubscriptionFixture.HdFiveDayWithPromo()));
    assertFalse(eligibilityEngine.isPromoEligibleForSubscription(PromoFixture.Promo2(), SubscriptionFixture.HdFiveDayWithPromo()));
    assertFalse(eligibilityEngine.isPromoEligibleForSubscription(PromoFixture.Promo3(), SubscriptionFixture.HdFiveDayWithPromo()));
    assertFalse(eligibilityEngine.isPromoEligibleForSubscription(PromoFixture.Inteligible(), SubscriptionFixture.HdFiveDayWithPromo()));
    assertTrue(eligibilityEngine.isPromoEligibleForSubscription(PromoFixture.Promo5(), SubscriptionFixture.HdFiveDayWithPromo()));
    assertFalse(eligibilityEngine.isPromoEligibleForSubscription(PromoFixture.Promo6(), SubscriptionFixture.HdFiveDayWithPromo()));
    assertTrue(eligibilityEngine.isPromoEligibleForSubscription(PromoFixture.Promo1(), SubscriptionFixture.HdSevenDay()));
    assertTrue(eligibilityEngine.isPromoEligibleForSubscription(PromoFixture.Promo2(), SubscriptionFixture.HdSevenDay()));
    assertTrue(eligibilityEngine.isPromoEligibleForSubscription(PromoFixture.Promo3(), SubscriptionFixture.HdSevenDay()));
    assertFalse(eligibilityEngine.isPromoEligibleForSubscription(PromoFixture.Inteligible(), SubscriptionFixture.HdSevenDay()));
    assertTrue(eligibilityEngine.isPromoEligibleForSubscription(PromoFixture.Promo5(), SubscriptionFixture.HdSevenDay()));
    assertTrue(eligibilityEngine.isPromoEligibleForSubscription(PromoFixture.Promo6(), SubscriptionFixture.HdSevenDay()));
  }
}