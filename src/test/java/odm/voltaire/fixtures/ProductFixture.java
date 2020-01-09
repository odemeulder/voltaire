package odm.voltaire.fixtures;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import odm.voltaire.models.Product;

/**
 * ProductFixtures
 */
public class ProductFixture {

  public static Product HdMonday() {
    return createHdProduct(1.0, DayOfWeek.MONDAY);
  }
  public static Product HdTuesday() {
    return createHdProduct(1.0, DayOfWeek.TUESDAY);
  }
  public static Product HdWednesday() {
    return createHdProduct(1.0, DayOfWeek.WEDNESDAY);
  }
  public static Product HdThursday() {
    return createHdProduct(1.0, DayOfWeek.THURSDAY);
  }
  public static Product HdFriday() {
    return createHdProduct(1.0, DayOfWeek.FRIDAY);
  }
  public static Product HdSaturday() {
    return createHdProduct(1.0, DayOfWeek.SATURDAY);
  }  
  public static Product HdSunday() {
    return createHdProduct(2.0, DayOfWeek.SUNDAY);
  }

  public static Product Digital() {
    Product p = new Product();
    p.setRatePlan(RatePlanFixture.basicHdRatePlan(new BigDecimal(0.2)));
    p.setWeekdaySpecific(false);
    return p;
  }

  public static Product XWord() {
    Product p = new Product();
    p.setRatePlan(RatePlanFixture.basicHdRatePlan(new BigDecimal(0.15)));
    p.setWeekdaySpecific(false);
    return p;
  }

  private static Product createHdProduct(double rate, DayOfWeek day) {
    Product p = new Product();
    p.setRatePlan(RatePlanFixture.basicHdRatePlan(new BigDecimal(rate)));
    p.setWeekdaySpecific(true);
    p.setDeliveryDay(day);
    p.setId(day.getValue());
    return p;
  }

  public static List<Product> combo12() {
    return new ArrayList<>(
      Arrays.asList(ProductFixture.HdMonday(), 
                    ProductFixture.HdTuesday())
    );
  }
  public static List<Product> combo123() {
    return new ArrayList<>(
      Arrays.asList(ProductFixture.HdMonday(), 
                    ProductFixture.HdTuesday(), 
                    ProductFixture.HdWednesday())
    );
  }
  public static List<Product> combo1234() {
    return new ArrayList<>(
      Arrays.asList(ProductFixture.HdMonday(), 
                    ProductFixture.HdTuesday(), 
                    ProductFixture.HdWednesday(),
                    ProductFixture.HdThursday())
    );
  }
  public static List<Product> combo12345() {
    return new ArrayList<>(
      Arrays.asList(ProductFixture.HdMonday(), 
                    ProductFixture.HdTuesday(), 
                    ProductFixture.HdWednesday(),
                    ProductFixture.HdThursday(),
                    ProductFixture.HdFriday())
    );
  }
  public static List<Product> combo45() {
    return new ArrayList<>(
      Arrays.asList(ProductFixture.HdThursday(), ProductFixture.HdFriday())
    );
  }
  public static List<Product> combo5() {
    return new ArrayList<>(
      Arrays.asList(ProductFixture.HdFriday())
    );
  }
}