package lab.aikibo.services;

import lab.aikibo.model.StatusTrx;
import java.math.BigInteger;

public interface PembayaranServices {
  public StatusTrx prosesPembayaran(String nop, String thn, BigInteger pokok, BigInteger denda);
}
