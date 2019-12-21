package odm.voltaire.fixtures;

import java.time.LocalDate;
import java.util.*;

import odm.voltaire.models.Suspension;

/**
 * SuspensionFixture
 */
public class SuspensionFixture {

  // 0 credit days
  public static List<Suspension> Past() {
    Suspension s = new Suspension();
    s.setStartDate(LocalDate.of(2019,12,21));
    s.setEndDate(LocalDate.of(2019,12,31));
    List<Suspension> l = new ArrayList<>();
    l.add(s);
    return l;
  }

  // 5 credit days
  public static List<Suspension> OverlapStart() {
    Suspension s = new Suspension();
    s.setStartDate(LocalDate.of(2019,12,25));
    s.setEndDate(LocalDate.of(2020,1,5));
    List<Suspension> l = new ArrayList<>();
    l.add(s);
    return l;
  }

  // 6 credit days
  public static List<Suspension> PresentFromStart() {
    Suspension s = new Suspension();
    s.setStartDate(LocalDate.of(2020,1,1));
    s.setEndDate(LocalDate.of(2020,1,6));
    List<Suspension> l = new ArrayList<>();
    l.add(s);
    return l;
  }

  // 3 credit days
  public static List<Suspension> Middle() {
    Suspension s = new Suspension();
    s.setStartDate(LocalDate.of(2020,1,3));
    s.setEndDate(LocalDate.of(2020,1,5));
    List<Suspension> l = new ArrayList<>();
    l.add(s);
    return l;
  }

  // 8 credit days
  public static List<Suspension> PresentToEnd() {
    Suspension s = new Suspension();
    s.setStartDate(LocalDate.of(2020,1,21));
    s.setEndDate(LocalDate.of(2020,1,28));
    List<Suspension> l = new ArrayList<>();
    l.add(s);
    return l;
  }

  // 9 credit days
  public static List<Suspension> OverlapEnd() {
    Suspension s = new Suspension();
    s.setStartDate(LocalDate.of(2020,1,20));
    s.setEndDate(LocalDate.of(2020,1,29));
    List<Suspension> l = new ArrayList<>();
    l.add(s);
    return l;
  }

  // 0 credit days
  public static List<Suspension> Future() {
    Suspension s = new Suspension();
    s.setStartDate(LocalDate.of(2020,1,29));
    s.setEndDate(LocalDate.of(2020,2,4));
    List<Suspension> l = new ArrayList<>();
    l.add(s);
    return l;
  }

  // 5 + 10 credit days
  public static List<Suspension> TwoSuspensions() {
    Suspension s1 = new Suspension();
    s1.setStartDate(LocalDate.of(2020,1,4));
    s1.setEndDate(LocalDate.of(2020,1,8));
    Suspension s2 = new Suspension();
    s2.setStartDate(LocalDate.of(2020,1,13));
    s2.setEndDate(LocalDate.of(2020,1,22));
    List<Suspension> l = new ArrayList<>();
    l.add(s1);
    l.add(s2);
    return l;
  }

}