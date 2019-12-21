package odm.voltaire.fixtures;

import java.util.ArrayList;

import odm.voltaire.models.Subscription;
import odm.voltaire.models.SubscriptionProduct;

public class SubscriptionFixture {
  
  public static Subscription HdSundayOnly() {
    Subscription s = new Subscription();
    s.setTerm(28);
    s.setProducts(new ArrayList<SubscriptionProduct>());
    s.getProducts().add(SubscriptionProductFixture.HdSunday());
    return s;
  }
  public static Subscription HdSundayOnlyWithSuspension() {
    Subscription s = new Subscription();
    s.setTerm(28);
    s.setProducts(new ArrayList<SubscriptionProduct>());
    s.getProducts().add(SubscriptionProductFixture.HdSundayWithSuspension());
    return s;
  }

  public static Subscription HdSevenDay() {
    Subscription s = new Subscription();
    s.setTerm(28);
    s.setProducts(new ArrayList<SubscriptionProduct>());
    s.getProducts().add(SubscriptionProductFixture.HdMonday());
    s.getProducts().add(SubscriptionProductFixture.HdTuesday());
    s.getProducts().add(SubscriptionProductFixture.HdWednesday());
    s.getProducts().add(SubscriptionProductFixture.HdThursday());
    s.getProducts().add(SubscriptionProductFixture.HdFriday());
    s.getProducts().add(SubscriptionProductFixture.HdSaturday());
    s.getProducts().add(SubscriptionProductFixture.HdSunday());
    return s;
  }

  public static Subscription HdSevenDayWithSuspension() {
    Subscription s = new Subscription();
    s.setTerm(28);
    s.setProducts(new ArrayList<SubscriptionProduct>());
    s.getProducts().add(SubscriptionProductFixture.HdMondayWithSuspension());
    s.getProducts().add(SubscriptionProductFixture.HdTuesdayWithSuspension());
    s.getProducts().add(SubscriptionProductFixture.HdWednesdayWithSuspension());
    s.getProducts().add(SubscriptionProductFixture.HdThursdayWithSuspension());
    s.getProducts().add(SubscriptionProductFixture.HdFridayWithSuspension());
    s.getProducts().add(SubscriptionProductFixture.HdSaturdayWithSuspension());
    s.getProducts().add(SubscriptionProductFixture.HdSundayWithSuspension());
    return s;
  }

  public static Subscription DigitalOnly() {
    Subscription s = new Subscription();
    s.setTerm(28);
    s.setProducts(new ArrayList<SubscriptionProduct>());
    s.getProducts().add(SubscriptionProductFixture.Digital());
    return s;
  }
  public static Subscription DigitalOnlyWithSuspension() {
    Subscription s = new Subscription();
    s.setTerm(28);
    s.setProducts(new ArrayList<SubscriptionProduct>());
    s.getProducts().add(SubscriptionProductFixture.DigitalWithSuspension());
    return s;
  }


}