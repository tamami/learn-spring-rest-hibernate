package lab.aikibo.services;

import org.springframework.beans.factory.annotation.Autowired;

import lab.aikibo.dao.PembayaranDao;
import lab.aikibo.model.Status;

import java.math.BigInteger;

public class PembayaranServicesImpl implements PembayaranServices {
  @Autowired
  private PembayaranDao pembayaranDao

  @Override
  public Status prosesPembayaran(String nop, String thn, BigInteger pokok, BigInteger denda) {
    return pembayaranDao.prosesPembayaran(nop, thn, pokok, denda);
  }
}
