package lab.aikibo.dao;

import org.springframework.stereotype.Repository;

import org.hibernate.Transaction;
import org.hibernate.Session;

import lab.aikibo.model.Status;
import lab.aikibo.model.PembayaranSppt;

@Repository("pembayaranDao")
public class PembayaranDaoImpl extends AbstractDao<Integer, PembayaranSppt>
    implements PembayaranDao {

  Session session = null;
  Transaction tx = null;

  @Override
  public Status prosesPembayaran(String nop, String thn, BigInteger pokok, BigInteger denda) {
    
  }

}
