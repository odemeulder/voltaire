package odm.voltaire.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import odm.voltaire.models.Subscription;
import odm.voltaire.models.SubscriptionProduct;
import odm.voltaire.models.Suspension;
import odm.voltaire.models.SuspensionCredit;

/**
 * SuspensionCreditService Service to calculate suspension credits
 */
public class SuspensionCreditService {

  private BillingUtilities billingUtilities;

  public SuspensionCreditService(BillingUtilities bs) {
    this.billingUtilities = bs;
  }

  public List<SuspensionCredit> CalculateSuspensionCreditsBetween(LocalDate start, LocalDate end, Subscription s) {
    List<SuspensionCredit> credits = new ArrayList<>();
    for (SubscriptionProduct sp : s.getProducts()) {
      if (sp.getPendingSuspensions() == null) continue;
      for (Suspension suspension : sp.getPendingSuspensions()) {
        if (suspension.getStartDate().isAfter(end) || suspension.getEndDate().isBefore(start)) {
          continue;
        }
        LocalDate suspensionStart = suspension.getStartDate().isBefore(start) ? start : suspension.getStartDate();
        LocalDate suspensionEnd = suspension.getEndDate().isAfter(end) ? end : suspension.getEndDate();
        BigDecimal credit = billingUtilities.CalculateChargesForSubscriptionProductBetween(
          suspensionStart, 
          suspensionEnd, 
          sp,
          s.getStartDate().plusDays(-s.getTerm()));
        credits.add(new SuspensionCredit(suspensionStart, suspensionEnd, credit));
      }
    }
    return credits;
  }

}