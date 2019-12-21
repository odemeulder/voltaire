package odm.voltaire.fixtures;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import odm.voltaire.models.Tier;

/**
 * TierFixture
 */
public class TierFixture {

  public static Tier basicOneTier(BigDecimal dailyRate) {
    Tier t = new Tier();
    t.setStartDay(57);
    t.setRate(1, dailyRate);
    return t;    
  }

  public static List<Tier> basicOneTierList(BigDecimal dailyRate) {
    List<Tier> tiers = new ArrayList<>();
    tiers.add(basicOneTier(dailyRate));
    return tiers;
  }

  public static Tier from1To28() {
    Tier t = new Tier();
    t.setStartDay(1);
    t.setEndDay(28);
    return t;
  }

  public static Tier from28To56() {
    Tier t = new Tier();
    t.setStartDay(28);
    t.setEndDay(56);
    return t;
  }

  public static Tier from29To56() {
    Tier t = new Tier();
    t.setStartDay(29);
    t.setEndDay(56);
    return t;
  }

  public static Tier from30To56() {
    Tier t = new Tier();
    t.setStartDay(30);
    t.setEndDay(56);
    return t;
  }

  public static Tier from57toEnd() {
    Tier t = new Tier();
    t.setStartDay(57);
    return t;
  }

}