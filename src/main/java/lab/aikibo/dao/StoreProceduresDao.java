package lab.aikibo.dao;

import lab.aikibo.model.StatusInq;
import lab.aikibo.model.StatusTrx;

import java.util.Date;

public interface StoreProceduresDao {
  public StatusInq getDataSppt(String nop, String thn);
  public StatusTrx prosesPembayaran(String nop, String thn, Date tglBayar);
}
