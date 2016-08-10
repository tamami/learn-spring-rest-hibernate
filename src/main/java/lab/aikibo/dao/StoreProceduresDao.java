package lab.aikibo.dao;

import lab.aikibo.model.Sppt;

public interface StoreProceduresDao {
  public Sppt getDataSppt(String nop, String thn);
}
