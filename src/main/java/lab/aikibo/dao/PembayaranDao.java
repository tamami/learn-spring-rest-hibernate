package lab.aikibo.dao;

import lab.aikibo.model.StatusTrx;

import java.math.BigInteger;

import org.joda.time.DateTime;

public interface PembayaranDao {
  public StatusTrx prosesPembayaran(String nop, String thn, BigInteger pokok, BigInteger denda, DateTime tglBayar);
}
