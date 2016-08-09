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
  SpptSismiop sppt = null;

  @Override
  public Status prosesPembayaran(String nop, String thn, BigInteger pokok, BigInteger denda) {

    Query qry = getSession().createSQLQuery("insert into pembayaran_sppt (" +
        "kd_propinsi, kd_dati2, kd_kecamatan, kd_kelurahan, kd_blok, no_urut, kd_jns_op," +
        "thn_pajak_sppt, pembayaran_sppt_ke, kd_kanwil_bank, kd_kppbb_bank, kd_bank_tunggal," +
        "kd_bank_persepsi, kd_tp, denda_sppt, jml_sppt_yg_dibayar, tgl_pembayaran_sppt) " +
        "values (" +

        ")"
    );
  }

}
