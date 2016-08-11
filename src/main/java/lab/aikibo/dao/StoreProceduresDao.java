package lab.aikibo.dao;

import lab.aikibo.model.StatusInq;

public interface StoreProceduresDao {
  public StatusInq getDataSppt(String nop, String thn);
}
