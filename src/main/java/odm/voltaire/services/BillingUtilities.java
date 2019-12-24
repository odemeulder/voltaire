package odm.voltaire.services;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

import odm.voltaire.models.Product;
import odm.voltaire.models.Subscription;
import odm.voltaire.models.SubscriptionProduct;

/**
 * BillingUtilities
 */
public class BillingUtilities {

  private CalendarService calendarService;
  
  public BillingUtilities(CalendarService cs) {
    this.calendarService = cs;
  }

  public BigDecimal CalculateChargesForSubscriptionBetween(LocalDate start, LocalDate end, Subscription s) {
    BigDecimal total = new BigDecimal(0.0);
    for (SubscriptionProduct sp : s.getProducts()) {
      total = CalculateChargesForSubscriptionProductBetween(start, end, sp, s).add(total);
    }
    return total;
  }

  // Start and end date inclusive
  public BigDecimal CalculateChargesForSubscriptionProductBetween(
    LocalDate start, 
    LocalDate end, 
    SubscriptionProduct sp,
    Subscription s) {
    if (sp.getProduct() == null) {
      throw new IllegalArgumentException("Invalid product (null)");
    }
    
    Product product = sp.getProduct();
    BigDecimal days;
    if (product.isWeekdaySpecific()) {
      Map<DayOfWeek, Integer> frequencies = calendarService.DayOfWeekFrequencyBetween(start, end);
      days = BigDecimal.valueOf(frequencies.get(product.getDeliveryDay())); 
    } else {
      days = BigDecimal.valueOf(calendarService.DaysBetween(start, end));
    }
    int dayInTier = calendarService.DaysBetween(s.getStartDate(), start) ;
    return product.getDayRate(dayInTier).multiply(days);
  }

}