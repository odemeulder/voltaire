package odm.voltaire.models;

import java.time.DayOfWeek;

import lombok.Data;

@Data
public class Product {
  private String name;
  private Double DayRate;
  private DayOfWeek DeliveryDay;
  private boolean WeekdaySpecific;
}
    
