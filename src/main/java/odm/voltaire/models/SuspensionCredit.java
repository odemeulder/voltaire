package odm.voltaire.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuspensionCredit {
  LocalDate startDate;
  LocalDate endDate;
  BigDecimal amount;
}