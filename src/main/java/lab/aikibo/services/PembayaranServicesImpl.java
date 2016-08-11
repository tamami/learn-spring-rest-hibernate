package lab.aikibo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lab.aikibo.dao.PembayaranDao;
import lab.aikibo.model.StatusTrx;

import java.math.BigInteger;

@Service("pembayaranServices")
@Transactional
public class PembayaranServicesImpl implements PembayaranServices {
  @Autowired
  private PembayaranDao pembayaranDao;

  @Override
  public StatusTrx prosesPembayaran(String nop, String thn, BigInteger pokok, BigInteger denda) {
    return pembayaranDao.prosesPembayaran(nop, thn, pokok, denda);
  }
}
