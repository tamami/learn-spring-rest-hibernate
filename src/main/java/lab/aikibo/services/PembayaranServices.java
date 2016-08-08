package lab.aikibo.services;

import lab.aikibo.model.Status;
import java.math.BigInteger;

public interface PembayaranServices {
  public Status prosesPembayaran(String nop, String thn, BigInteger pokok, BigInteger denda);
}
