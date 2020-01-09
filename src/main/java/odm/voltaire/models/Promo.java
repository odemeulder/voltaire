package odm.voltaire.models;

import lombok.Builder;
import lombok.Data;

/**
 * Promo
 */
@Data
@Builder
public class Promo {
  private int id;
  private String description;
  private double discount;
}