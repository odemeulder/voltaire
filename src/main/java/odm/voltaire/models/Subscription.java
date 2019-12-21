package odm.voltaire.models;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class Subscription {
  private int id;
  private int accountId;
  private LocalDate startDate;
  private List<SubscriptionProduct> products;
  private int term;
}
