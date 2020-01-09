package odm.voltaire.fixtures;

import odm.voltaire.models.Product;
import odm.voltaire.models.SubscriptionProduct;

/**
 * ProductFixtures
 */
public class SubscriptionProductFixture {

  public static SubscriptionProduct HdMonday() {
    return createSubscriptionProduct(ProductFixture.HdMonday());
  }
  public static SubscriptionProduct HdTuesday() {
    return createSubscriptionProduct(ProductFixture.HdTuesday());
  }
  public static SubscriptionProduct HdWednesday() {
    return createSubscriptionProduct(ProductFixture.HdWednesday());
  }
  public static SubscriptionProduct HdThursday() {
    return createSubscriptionProduct(ProductFixture.HdThursday());
  }
  public static SubscriptionProduct HdFriday() {
    return createSubscriptionProduct(ProductFixture.HdFriday());
  }
  public static SubscriptionProduct HdSaturday() {
    return createSubscriptionProduct(ProductFixture.HdSaturday());
  }
  public static SubscriptionProduct HdSunday() {
    return createSubscriptionProduct(ProductFixture.HdSunday());
  }
  public static SubscriptionProduct HdMondayWithSuspension() {
    return createSubscriptionProductWithSuspension(ProductFixture.HdMonday());
  }
  public static SubscriptionProduct HdTuesdayWithSuspension() {
    return createSubscriptionProductWithSuspension(ProductFixture.HdTuesday());
  }
  public static SubscriptionProduct HdWednesdayWithSuspension() {
    return createSubscriptionProductWithSuspension(ProductFixture.HdWednesday());
  }
  public static SubscriptionProduct HdThursdayWithSuspension() {
    return createSubscriptionProductWithSuspension(ProductFixture.HdThursday());
  }
  public static SubscriptionProduct HdFridayWithSuspension() {
    return createSubscriptionProductWithSuspension(ProductFixture.HdFriday());
  }
  public static SubscriptionProduct HdSaturdayWithSuspension() {
    return createSubscriptionProductWithSuspension(ProductFixture.HdSaturday());
  }
  public static SubscriptionProduct HdSundayWithSuspension() {
    return createSubscriptionProductWithSuspension(ProductFixture.HdSunday());
  }
  public static SubscriptionProduct HdMondayWithPromo() {
    return createSubscriptionProductWithPromo(ProductFixture.HdMonday());
  }
  public static SubscriptionProduct HdTuesdayWithPromo() {
    return createSubscriptionProductWithPromo(ProductFixture.HdTuesday());
  }
  public static SubscriptionProduct HdWednesdayWithPromo() {
    return createSubscriptionProductWithPromo(ProductFixture.HdWednesday());
  }

  public static SubscriptionProduct Digital() {
    return createSubscriptionProduct(ProductFixture.Digital());
  }
  public static SubscriptionProduct DigitalWithSuspension() {
    return createSubscriptionProductWithSuspension(ProductFixture.Digital());
  }

  public static SubscriptionProduct XWord() {
    return createSubscriptionProduct(ProductFixture.XWord());
  }

  private static SubscriptionProduct createSubscriptionProduct(Product p) {
    return SubscriptionProduct.builder().product(p).build();
  }

  private static SubscriptionProduct createSubscriptionProductWithSuspension(Product p) {
    return SubscriptionProduct.builder().product(p).pendingSuspensions(SuspensionFixture.Past()).build();
  }

  private static SubscriptionProduct createSubscriptionProductWithPromo(Product p) {
    return SubscriptionProduct.builder().product(p).promotion(PromoFixture.Promo1()).build();
  }


}