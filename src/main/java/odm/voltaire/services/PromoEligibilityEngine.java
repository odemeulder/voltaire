package odm.voltaire.services;

import java.util.List;
import java.util.stream.Collectors;

import odm.voltaire.models.Product;
import odm.voltaire.models.ProductPromoRule;
import odm.voltaire.models.Promo;


/**
 * PromoEligibilityEngine
 */
public class PromoEligibilityEngine {

  private PromoRulesServiceInterface prs;
  PromoEligibilityEngine(PromoRulesServiceInterface promoRulesService) {
    prs = promoRulesService;
  }

  public List<Promo> promosFor(List<Product> products) {

    List<Integer> productIds = products.stream()
      .map((Product p) -> p.getId())
      .collect(Collectors.toList());

    return prs.allRules()
      .stream()
      .filter((ProductPromoRule ppr) -> ppr.getProductCombination().containsAll(productIds))
      .map((ProductPromoRule ppr) -> ppr.getPromo())
      .collect(Collectors.toList());
  }

  public boolean isPromoEligibile(Promo promo, List<Product> products) {
    
    List<Promo> eligiblePromos = promosFor(products);
    
    return eligiblePromos.contains(promo);
  
  }
}