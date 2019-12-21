package odm.voltaire.models;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Data;

/**
 * RatePlan
 */
@Data
public class RatePlan {
  private String description;
  private List<Tier> tiers;

  public void setTiers(List<Tier> tiers) {
    validateTiers(tiers);
    this.tiers = tiers;
  }

  private void validateTiers(List<Tier> tiers) throws IllegalArgumentException {
    if (tiers.size() == 0) {
      throw new IllegalArgumentException("Need at least one tier");
    }
    int numTiers = tiers.size();
    List<Tier> sortedTiers = tiers.stream()
      .sorted(Comparator.comparing(Tier::getStartDay))
      .collect(Collectors.toList());
    for (int i = 0; i < numTiers - 1; i++) {
      Tier curr = sortedTiers.get(i);
      Tier next = sortedTiers.get(i+1);
      if (curr.getEndDay() >= next.getStartDay()) {
        throw new IllegalArgumentException("No overlapping tiers allowed");
      }
      if (curr.getEndDay() + 1 != next.getStartDay()) {
        throw new IllegalArgumentException("No gaps between tiers allowed");
      }
      if (i == numTiers - 2 && !next.getEndDay().equals(Integer.MAX_VALUE)) {
        throw new IllegalArgumentException("Last tier should never end");
      }
    }
  }
  
  public Tier getTierForDay(int day) {
    validateTiers(this.tiers);
    return this.tiers
      .stream()
      .filter(t -> day >= t.getStartDay() && day <= t.getEndDay())
      .findFirst()
      .get();
  }
}