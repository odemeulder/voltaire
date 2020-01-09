package odm.voltaire.fixtures;

import odm.voltaire.models.Promo;

/**
 * PromoFixture
 */
public class PromoFixture {

  public static Promo Promo1() {
    return Promo
      .builder()
      .id(1)
      .description("A")
      .discount(0.05)
      .build();
  }

  public static Promo Promo2() {
    return Promo
      .builder()
      .id(2)
      .description("B")
      .discount(0.09)
      .build();
  }
  public static Promo Promo3() {
    return Promo
      .builder()
      .id(3)
      .description("C")
      .discount(0.1)
      .build();
  }
  public static Promo Inteligible() {
    return Promo
      .builder()
      .id(4)
      .description("D")
      .discount(0.15)
      .build();
  }
  public static Promo Promo5() {
    return Promo
      .builder()
      .id(5)
      .description("E")
      .discount(0.20)
      .build();
  }
  public static Promo Promo6() {
    return Promo
      .builder()
      .id(6)
      .description("F")
      .discount(0.25)
      .build();
  }

}