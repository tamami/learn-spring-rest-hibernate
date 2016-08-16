package lab.aikibo.services;

import lab.aikibo.model.StatusTrx;
import java.math.BigInteger;

import org.joda.time.DateTime;

public interface PembayaranServices {
  public StatusTrx prosesPembayaran(String nop, String thn, DateTime tglBayar);
}
