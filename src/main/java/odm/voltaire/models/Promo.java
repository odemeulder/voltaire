package odm.voltaire.models;

import lombok.Data;

/**
 * Promo
 */
@Data
public class Promo {
  private int id;
  private String description;
  private double discount;
}