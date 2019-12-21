package odm.voltaire.fixtures;

import java.math.BigDecimal;

import odm.voltaire.models.RatePlan;

/**
 * RatePlantFixture
 */
public class RatePlanFixture {


  public static RatePlan basicHdRatePlan(BigDecimal dailyRate) {
    RatePlan rp = new RatePlan();
    rp.setTiers(TierFixture.basicOneTierList(dailyRate));
    return rp;
  }
}