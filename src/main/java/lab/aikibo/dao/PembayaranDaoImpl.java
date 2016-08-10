package lab.aikibo.dao;

import org.springframework.stereotype.Repository;

import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.Query;

import java.math.BigInteger;

import lab.aikibo.model.Status;
import lab.aikibo.model.PembayaranSppt;
import lab.aikibo.model.SpptSismiop;

@Repository("pembayaranDao")
public class PembayaranDaoImpl extends AbstractDao<Integer, PembayaranSppt>
    implements PembayaranDao {

  Session session = null;
  Transaction tx = null;
  SpptSismiop sppt = null;

  @Override
  public Status prosesPembayaran(String nop, String thn, BigInteger pokok, BigInteger denda) {
    // ambil data dari sppt
    Query qry = getSession().createQuery("from SpptSismiop where kdPropinsi = :kdPropinsi " +
      "kdDati2 = :kdDati2 and kdKecamatan = :kdKecamatan and kdKelurahan = :kdKelurahan " +
      "kdBlok = :kdBlok and noUrut = :noUrut and kdJnsOp = :kdJnsOp");

    qry = getSession().createSQLQuery("insert into pembayaran_sppt (" +
        "kd_propinsi, kd_dati2, kd_kecamatan, kd_kelurahan, kd_blok, no_urut, kd_jns_op," +
        "thn_pajak_sppt, pembayaran_sppt_ke, kd_kanwil_bank, kd_kppbb_bank, kd_bank_tunggal," +
        "kd_bank_persepsi, kd_tp, denda_sppt, jml_sppt_yg_dibayar, tgl_pembayaran_sppt) " +
        "values (" +
        ":kdPropinsi, :kdDati2, :kdKecamatan, :kdKelurahan, :kdBlok, :noUrut, :kdJnsOp, :thnPajakSppt, " +
        ":pembayaranSpptKe, :kdKanwilBank, :kdKppbbBank, :kdBankTunggal, :kdBankPersepsi, :kdTp, " +
        ":dendaSppt, :jmlSpptYgDibayar, :tglPembayaranSppt)");

    return null;
  }

}
