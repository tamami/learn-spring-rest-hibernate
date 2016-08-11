package lab.aikibo.dao;

import org.springframework.stereotype.Repository;

import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.Query;

import java.math.BigInteger;

import lab.aikibo.model.StatusTrx;
import lab.aikibo.model.PembayaranSppt;
import lab.aikibo.model.SpptSismiop;

@Repository("pembayaranDao")
public class PembayaranDaoImpl extends AbstractDao<Integer, PembayaranSppt>
    implements PembayaranDao {

  @Autowired
  private BoneCPDataSource boneCPDs;

  Session session = null;
  Transaction tx = null;
  SpptSismiop sppt = null;

  @Override
  public StatusTrx prosesPembayaran(String nop, String thn, BigInteger pokok, BigInteger denda) {
    // ambil data dari sppt
    CallableStatement cs = null;

    cs = boneCPDs.getConnection().prepareCall("call ");

    return null;
  }

}
