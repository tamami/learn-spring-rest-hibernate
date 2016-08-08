package lab.aikibo.dao;

import lab.aikibo.model.Status;

import java.math.BigInteger;

public interface PembayaranDao {
  public Status prosesPembayaran(String nop, String thn, BigInteger pokok, BigInteger denda);
}
