package lab.aikibo.dao;

import lab.aikibo.model.SpptSismiop;

public interface StoreProceduresDao {
  public SpptSismiop getDataSppt(String nop, String thn);
}
