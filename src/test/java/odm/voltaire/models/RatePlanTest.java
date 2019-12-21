package odm.voltaire.models;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;

import odm.voltaire.fixtures.TierFixture;

/**
 * RatePlanTest
 */
public class RatePlanTest {

  @Test(expected = IllegalArgumentException.class)
  public void tierValidationNoTiers() {
    RatePlan rp = new RatePlan();
    List<Tier> tiers = new ArrayList<>();
    rp.setTiers(tiers);
  }

  @Test(expected = IllegalArgumentException.class)
  public void tierValidationOverlap() {
    RatePlan rp = new RatePlan();
    List<Tier> tiers = new ArrayList<>();
    tiers.add(TierFixture.from1To28());
    tiers.add(TierFixture.from28To56());
    rp.setTiers(tiers);
  }

  @Test(expected = IllegalArgumentException.class)
  public void tierValidationGap() {
    RatePlan rp = new RatePlan();
    List<Tier> tiers = new ArrayList<>();
    tiers.add(TierFixture.from1To28());
    tiers.add(TierFixture.from30To56());
    rp.setTiers(tiers);
  }

  @Test(expected = IllegalArgumentException.class)
  public void tierValidationNoEnd() {
    RatePlan rp = new RatePlan();
    List<Tier> tiers = new ArrayList<>();
    tiers.add(TierFixture.from1To28());
    tiers.add(TierFixture.from29To56());
    rp.setTiers(tiers);
  }

  @Test
  public void validateUnsortedTiers() {
    RatePlan rp = new RatePlan();
    List<Tier> tiers = new ArrayList<>();
    tiers.add(TierFixture.from1To28());
    tiers.add(TierFixture.from57toEnd());    
    tiers.add(TierFixture.from29To56());
    rp.setTiers(tiers);
  }

  @Test
  public void getCurrentTier() {
    RatePlan rp = new RatePlan();
    List<Tier> tiers = new ArrayList<>();
    tiers.add(TierFixture.from1To28());
    tiers.add(TierFixture.from29To56());
    tiers.add(TierFixture.from57toEnd());    
    rp.setTiers(tiers);

    assertEquals(TierFixture.from1To28().getStartDay(), rp.getTierForDay(1).getStartDay());
    assertEquals(TierFixture.from1To28().getStartDay(), rp.getTierForDay(2).getStartDay());
    assertEquals(TierFixture.from1To28().getStartDay(), rp.getTierForDay(28).getStartDay());
    assertEquals(TierFixture.from29To56().getStartDay(), rp.getTierForDay(29).getStartDay());
    assertEquals(TierFixture.from29To56().getStartDay(), rp.getTierForDay(30).getStartDay());
    assertEquals(TierFixture.from29To56().getStartDay(), rp.getTierForDay(56).getStartDay());
    assertEquals(TierFixture.from57toEnd().getStartDay(), rp.getTierForDay(57).getStartDay());
    assertEquals(TierFixture.from57toEnd().getStartDay(), rp.getTierForDay(58).getStartDay());
    assertEquals(TierFixture.from57toEnd().getStartDay(), rp.getTierForDay(999).getStartDay());
  }

}