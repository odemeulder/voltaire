package odm.voltaire.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuspensionCredit {
  LocalDate startDate;
  LocalDate endDate;
  double amount;
}