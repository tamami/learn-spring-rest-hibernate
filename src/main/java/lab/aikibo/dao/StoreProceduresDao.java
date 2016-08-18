package lab.aikibo.dao;

import lab.aikibo.model.StatusInq;
import lab.aikibo.model.StatusTrx;
import lab.aikibo.model.StatusRev;

import java.util.Date;

public interface StoreProceduresDao {
  public StatusInq getDataSppt(String nop, String thn, String ipClient);
  public StatusTrx prosesPembayaran(String nop, String thn, Date tglBayar, String ipClient);
  public StatusRev reversalPembayaran(String nop, String thn, String ntpd, String ipClient);
}
