package odm.voltaire.models;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Suspension {
  private LocalDate startDate;
  private LocalDate endDate;
}