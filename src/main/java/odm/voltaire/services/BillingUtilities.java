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

  private final CalendarService calendarService;

  public BillingUtilities(final CalendarService cs) {
    this.calendarService = cs;
  }

  public BigDecimal CalculateChargesForSubscriptionBetween(final LocalDate start, final LocalDate end, final Subscription s) {
    BigDecimal total = new BigDecimal(0.0);
    for (final SubscriptionProduct sp : s.getProducts()) {
      total = CalculateChargesForSubscriptionProductBetween(start, end, sp, s.getStartDate()).add(total);
    }
    return total;
  }

  // Start and end date inclusive
  public BigDecimal CalculateChargesForSubscriptionProductBetween(
    final LocalDate start, 
    final LocalDate end, 
    final SubscriptionProduct sp,
    LocalDate licenseStart) {
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
    int dayInTier = calendarService.DaysBetween(licenseStart, start) ;
    return product.getDayRate(dayInTier).multiply(days);
  }

}