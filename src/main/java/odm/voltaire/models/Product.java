package odm.voltaire.models;

import java.math.BigDecimal;
import java.time.DayOfWeek;

import lombok.Data;

@Data
public class Product {
  private String name;
  private DayOfWeek DeliveryDay;
  private boolean WeekdaySpecific;
  private RatePlan ratePlan;

  public BigDecimal getDayRate() {
    return ratePlan.getTierForDay(Integer.MAX_VALUE).getDailyRate();
  }
  public BigDecimal getDayRate(int daysSinceStart) {
    return ratePlan.getTierForDay(daysSinceStart).getDailyRate();    
  }
}
    
