package odm.voltaire.services;

import java.util.List;
import java.util.stream.Collectors;

import odm.voltaire.models.Product;
import odm.voltaire.models.ProductPromoRule;
import odm.voltaire.models.Promo;
import odm.voltaire.models.Subscription;
import odm.voltaire.models.SubscriptionProduct;


/**
 * PromoEligibilityEngine
 */
public class PromoEligibilityEngine {

  private PromoRulesServiceInterface prs;
  PromoEligibilityEngine(PromoRulesServiceInterface promoRulesService) {
    prs = promoRulesService;
  }

  // Get eligible promos for a list of products
  public List<Promo> promosFor(List<Product> products) {

    List<Integer> productIds = products.stream()
      .map((Product p) -> p.getId())
      .collect(Collectors.toList());

    return prs.allRules()
      .stream()
      .filter((ProductPromoRule ppr) -> productIds.containsAll(ppr.getProductCombination()))
      .map((ProductPromoRule ppr) -> ppr.getPromo())
      .collect(Collectors.toList());
  }

  // Check if a particular promo is elibile for a list of products
  public boolean isPromoEligibile(Promo promo, List<Product> products) {
    List<Promo> eligiblePromos = promosFor(products);
    return eligiblePromos.contains(promo);
  }

  // Check if a particular promo is eligible for a specific subscription
  public boolean isPromoEligibleForSubscription(Promo promo, Subscription subscription) {
    List<Product> products = subscription.getProducts().stream()
      .filter((SubscriptionProduct sp) -> sp.getPromotion() == null)
      .map((SubscriptionProduct sp) -> sp.getProduct())
      .collect(Collectors.toList());
    return isPromoEligibile(promo, products);
  }
}