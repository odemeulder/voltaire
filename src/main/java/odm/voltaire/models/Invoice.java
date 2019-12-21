package odm.voltaire.models;

import java.time.LocalDate;
import java.util.List;

public class Invoice {

  public LocalDate date;
  public Account account;
  public List<InvoiceLineItem> lineItems;

}