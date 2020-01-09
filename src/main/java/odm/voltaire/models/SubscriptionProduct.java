package odm.voltaire.models;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class SubscriptionProduct {
  private Subscription subscription;
  private Product product;
  private Address deliveryAddress;
  private List<Suspension> pendingSuspensions;
  private Promo promotion;
}
