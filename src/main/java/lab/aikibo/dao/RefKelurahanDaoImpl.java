package lab.aikibo.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

import lab.aikibo.model.RefKelurahan;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

@Repository("refKelurahanDao")
public class RefKelurahanDaoImpl extends AbstractDao<Integer, RefKelurahan>
    implements RefKelurahanDao {

  Session session = null;
  Transaction tx = null;

  @Override
  public List<RefKelurahan> getDaftarKelurahan(String kdKecamatan) {
    session = getSession();
    Query qry = session.createQuery("from RefKelurahan where kdKecamatan = :kdKecamatan");
    qry.setParameter("kdKecamatan", kdKecamatan);
    return (List<RefKelurahan>) qry.list();
  }

}
