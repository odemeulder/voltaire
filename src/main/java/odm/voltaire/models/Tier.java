package odm.voltaire.models;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class Tier {
  private Integer startDay;
  private Integer endDay;
  private Integer billingFrequency; // in days
  private BigDecimal rate;
  @Setter(AccessLevel.NONE)
  private BigDecimal dailyRate;

  public Integer getEndDay() {
    if (endDay == null || endDay == 0) {
      return Integer.MAX_VALUE;
    }
    return endDay;
  }

  public void setRate(int frequency, BigDecimal rate) {
    this.billingFrequency = frequency;
    this.rate = rate;
    this.dailyRate = rate.divide(new BigDecimal(frequency));
  }

  public BigDecimal getDailyRate() {
    return dailyRate;
  }
}
