package odm.voltaire.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import odm.voltaire.models.SubscriptionProduct;
import odm.voltaire.models.Suspension;
import odm.voltaire.models.SuspensionCredit;
import odm.voltaire.models.Product;
import odm.voltaire.models.Subscription;

public class BillingService {

  private CalendarService calendarService;

  public BillingService(CalendarService calendarService) {
    this.calendarService = calendarService;
  }

  public double RunBilling(LocalDate billDate, Subscription s) {
    LocalDate billStart = billDate;
    LocalDate billEnd = billDate.plusDays(s.getTerm());
    double total = CalculateChargesForSubscriptionBetween(billStart, billEnd, s);

    LocalDate creditEligibleStart = billDate.minusDays(s.getTerm() + 1);
    LocalDate creditEligibleEnd = billDate.minusDays(1);
    List<SuspensionCredit> suspensionCredits = CalculateSuspensionCreditsBetween(creditEligibleStart, creditEligibleEnd, s);
    Double credits = suspensionCredits.stream().mapToDouble(c -> c.getAmount()).sum();
    return total - credits;
  }

  public List<SuspensionCredit> CalculateSuspensionCreditsBetween(LocalDate start, LocalDate end, Subscription s) {
    List<SuspensionCredit> credits = new ArrayList<>();
    for (SubscriptionProduct sp : s.getProducts()) {
      for (Suspension suspension : sp.getPendingSuspensions()) {
        if (suspension.getStartDate().isAfter(end) || suspension.getEndDate().isBefore(start)) {
          continue;
        }
        LocalDate suspensionStart = suspension.getStartDate().isBefore(start) ? start : suspension.getStartDate();
        LocalDate suspensionEnd = suspension.getEndDate().isAfter(end) ? end : suspension.getEndDate();
        double credit = CalculateChargesForSubscriptionProductBetween(suspensionStart, suspensionEnd, sp);
        credits.add(new SuspensionCredit(suspensionStart, suspensionEnd, credit));
      }
    }
    return credits;
  }

  public double CalculateChargesForSubscriptionBetween(LocalDate start, LocalDate end, Subscription s) {
    double total = 0;
    for (SubscriptionProduct sp : s.getProducts()) {
      total += CalculateChargesForSubscriptionProductBetween(start, end, sp);
    }
    return total;
  }

  // Start and end date inclusive
  public double CalculateChargesForSubscriptionProductBetween(LocalDate start, LocalDate end, SubscriptionProduct sp) {
    if (sp.getProduct() == null) {
      throw new IllegalArgumentException("Invalid product (null)");
    }
    
    Product product = sp.getProduct();
    if (product.isWeekdaySpecific()) {
      Map<DayOfWeek, Integer> frequencies = calendarService.DayOfWeekFrequencyBetween(start, end);
      return frequencies.get(product.getDeliveryDay()) * product.getDayRate();
    } else {
      int days = calendarService.DaysBetween(start, end);
      return days * product.getDayRate();
    }
  }

}