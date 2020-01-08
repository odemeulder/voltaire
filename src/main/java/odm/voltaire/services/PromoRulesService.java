package odm.voltaire.services;

import java.util.ArrayList;
import java.util.List;

import odm.voltaire.models.ProductPromoRule;

/**
 * PromoRulesService
 */
public class PromoRulesService implements PromoRulesServiceInterface{

  public List<ProductPromoRule> allRules() {
    return new ArrayList<ProductPromoRule>();
  }
}