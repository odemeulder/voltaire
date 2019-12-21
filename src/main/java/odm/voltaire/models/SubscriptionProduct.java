package odm.voltaire.models;

import lombok.Data;
import java.util.List;

@Data
public class SubscriptionProduct {
  private Subscription subscription;
  private Product product;
  private Address deliveryAddress;
  private List<Suspension> pendingSuspensions;
}
