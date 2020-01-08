package odm.voltaire.services;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import odm.voltaire.fixtures.ProductPromoRuleFixture;

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
  public void returnsPromos() {
    
  }
  
}