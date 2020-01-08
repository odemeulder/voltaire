package odm.voltaire.models;

import java.util.List;

import lombok.Data;

/**
 * ProductPromoRule
 */
@Data
public class ProductPromoRule {
  private List<Integer> productCombination;
  private Promo promo;
}