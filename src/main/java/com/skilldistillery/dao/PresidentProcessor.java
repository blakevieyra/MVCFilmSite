package com.skilldistillery.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class PresidentProcessor {
  private PresidentDAO presidentDAO;

  public PresidentProcessor(PresidentDAO presidentDAO) {
    super();
    this.presidentDAO = presidentDAO;
  }

  public PresidentDAO getPresidentDAO() {
    return presidentDAO;
  }

  public void setPresidentDAO(PresidentDAO presidentDAO) {
    this.presidentDAO = presidentDAO;
  }
  
  public List<President> filterByParty(String string) {
    List<President> filtered = new ArrayList<>();

    BiPredicate<President,String> matcher = (President p, String s) -> p.getParty().equals(s);
    for (President p : presidentDAO.getPresidents()) {
      if (matcher.test(p, string)) {
        filtered.add(p);
      }
    }
    return filtered;
  }
  
  public List<President> filterByLastName(String string) {
    List<President> filtered = new ArrayList<>();

    BiPredicate<President,String> matcher = (President p, String s) -> p.getLastName().equals(s);
    for (President p : presidentDAO.getPresidents()) {
      if (matcher.test(p, string)) {
        filtered.add(p);
      }
    }
    return filtered;
  }
}
