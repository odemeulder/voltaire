package odm.voltaire.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import odm.voltaire.models.SuspensionCredit;
import odm.voltaire.models.Subscription;

public class BillingService {

  private BillingUtilities billingUtilities;
  private SuspensionCreditService suspensionCreditService;

  public BillingService(
    BillingUtilities billingUtilities,
    SuspensionCreditService suspensionCreditService) {
    this.billingUtilities = billingUtilities;
    this.suspensionCreditService = suspensionCreditService;
  }

  public BigDecimal RunBilling(LocalDate billDate, Subscription s) {
    LocalDate billStart = billDate;
    LocalDate billEnd = billDate.plusDays(s.getTerm()-1);
    BigDecimal total = billingUtilities.CalculateChargesForSubscriptionBetween(billStart, billEnd, s);

    LocalDate creditEligibleStart = billDate.minusDays(s.getTerm() + 1);
    LocalDate creditEligibleEnd = billDate.minusDays(1);
    List<SuspensionCredit> suspensionCredits = suspensionCreditService.CalculateSuspensionCreditsBetween(creditEligibleStart, creditEligibleEnd, s);
    BigDecimal credits = suspensionCredits.stream()
      .map(SuspensionCredit::getAmount)
      .reduce(BigDecimal.ZERO, BigDecimal::add);
    return total.subtract(credits);
  }

}