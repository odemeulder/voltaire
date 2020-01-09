package odm.voltaire.fixtures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import odm.voltaire.models.ProductPromoRule;
import odm.voltaire.models.Promo;

/**
 * ProductPromoRuleFixture
 */
public class ProductPromoRuleFixture {

  public static List<ProductPromoRule> allRules() {
    List<Integer> combo123 = new ArrayList<>(Arrays.asList(1,2,3));
    List<Integer> combo1234 = new ArrayList<>(Arrays.asList(1,2,3,4));
    List<Integer> combo5 = new ArrayList<>(Arrays.asList(5));
    List<Integer> combo6 = new ArrayList<>(Arrays.asList(5, 6, 7));
    List<ProductPromoRule> rv = new ArrayList<>();
    rv.add(newProductPromoRule(combo123, PromoFixture.Promo1()));
    rv.add(newProductPromoRule(combo123, PromoFixture.Promo2()));
    rv.add(newProductPromoRule(combo1234, PromoFixture.Promo3()));
    rv.add(newProductPromoRule(combo5, PromoFixture.Promo5()));
    rv.add(newProductPromoRule(combo6, PromoFixture.Promo6()));
    return rv;
  }

  private static ProductPromoRule newProductPromoRule(List<Integer> combo, Promo promo) {
    ProductPromoRule ppr = new ProductPromoRule();
    ppr.setProductCombination(combo);
    ppr.setPromo(promo);
    return ppr;
  }

}