package odm.voltaire.services;

import java.util.List;

import odm.voltaire.models.ProductPromoRule;

/**
 * PromoRulesServiceInterface
 */
public interface PromoRulesServiceInterface {

  public List<ProductPromoRule> allRules();
}