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
    Promo promo1 = newPromo("A");
    Promo promo2 = newPromo("B");
    Promo promo3 = newPromo("C");
    Promo promo4 = newPromo("D");
    Promo promo5 = newPromo("E");
    Promo promo6 = newPromo("F");
    List<Integer> combo1 = new ArrayList<>(Arrays.asList(1,2,3));
    List<Integer> combo2 = new ArrayList<>(Arrays.asList(1,2,3,4));
    List<Integer> combo3 = new ArrayList<>(Arrays.asList(5));
    List<Integer> combo4 = new ArrayList<>();
    List<Integer> combo5 = new ArrayList<>(Arrays.asList(6, 7, 8));
    List<ProductPromoRule> rv = new ArrayList<>();
    rv.add(newProductPromoRule(combo1, promo1));
    rv.add(newProductPromoRule(combo1, promo2));
    rv.add(newProductPromoRule(combo2, promo3));
    rv.add(newProductPromoRule(combo4, promo4));
    rv.add(newProductPromoRule(combo5, promo5));
    rv.add(newProductPromoRule(combo3, promo6));
    return rv;
  }

  private static Promo newPromo(String description) {
    Promo p = new Promo();
    p.setId(1);
    p.setDescription(description);
    p.setDiscount(.1);
    return p;
  }

  private static ProductPromoRule newProductPromoRule(List<Integer> combo, Promo promo) {
    ProductPromoRule ppr = new ProductPromoRule();
    ppr.setProductCombination(combo);
    ppr.setPromo(promo);
    return ppr;
  }

}