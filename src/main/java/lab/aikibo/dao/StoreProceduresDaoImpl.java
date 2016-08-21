package lab.aikibo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.ParameterMode;

import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;

import org.joda.time.DateTime;
import org.joda.time.Months;

import oracle.jdbc.OracleTypes;

import com.jolbox.bonecp.BoneCPDataSource;

import lab.aikibo.model.Sppt;
import lab.aikibo.model.SpptJ;
import lab.aikibo.model.StatusInq;
import lab.aikibo.model.StatusRev;
import lab.aikibo.model.SpptSismiop;
import lab.aikibo.model.StatusTrx;
import lab.aikibo.model.PembayaranSppt;
import lab.aikibo.model.ReversalPembayaran;

import lab.aikibo.constant.StatusRespond;

import lab.aikibo.controller.SpptRestController;

@Repository("storeProceduresDao")
public class StoreProceduresDaoImpl implements StoreProceduresDao {

  @Autowired
  private SessionFactory sessionFactory;
  @Autowired
  private BoneCPDataSource boneCPDs;

  CallableStatement callable;
  Sppt sppt;
  PembayaranSppt pembayaranSppt;
  ReversalPembayaran revPembayaran;
  StatusInq status;
  StatusTrx statusTrx;
  StatusRev statusRev;

  public StatusInq getDataSppt(String nop, String thn, String ipClient) {
    // -- ini cara 1; lumpuh saat panggil ke connection() deprecated

    callable = null;
    sppt = null;
    status = null;

    try {
      callable = boneCPDs.getConnection().prepareCall("call sppt_terhutang(?,?,?)");
      callable.registerOutParameter(1, OracleTypes.CURSOR);
      callable.setString(2, nop);
      callable.setString(3, thn);
      //if(callable.executeUpdate() == 0) {
      callable.executeUpdate();
      ResultSet rs = (ResultSet) callable.getObject(1);
      ResultSetMetaData rsmd = rs.getMetaData();
      for(int i=1; i<rsmd.getColumnCount(); i++) {
        SpptRestController.getLogger().debug(" >>> data " + i + " : " + rsmd.getColumnName(i));
      }
      sppt = new Sppt();

      while(rs.next()) {
        String nama = rs.getString("NAMA");
        String alamatOp = rs.getString("ALAMAT_OP");
        BigInteger pokok = rs.getBigDecimal("POKOK").toBigInteger();
        BigInteger denda = rs.getBigDecimal("DENDA").toBigInteger();
        SpptRestController.getLogger().debug(" >>> NAMA : " + nama);
        SpptRestController.getLogger().debug(" >>> ALAMAT OP : " + alamatOp);
        SpptRestController.getLogger().debug(" >>> POKOK : " + pokok);
        SpptRestController.getLogger().debug(" >>> DENDA : " + denda);
        sppt.setNop(nop);
        sppt.setThn(thn);
        sppt.setNama(nama);
        sppt.setAlamatOp(alamatOp);
        sppt.setPokok(pokok);
        sppt.setDenda(denda);
      }
      if(sppt.getNop() == null) {
        status = new StatusInq(StatusRespond.DATA_INQ_NIHIL, "Data Tidak Ditemukan", null);
        return status;
      }

      status = new StatusInq(StatusRespond.APPROVED, "Data ditemukan", sppt);
      return status;
    } catch(Exception e) {
      status = new StatusInq(StatusRespond.DATABASE_ERROR, "Kesalahan DB", null);
    }
    return status;
  }

  public StatusTrx prosesPembayaran(String nop, String thn, Date tglBayar, String ipClient) {
    callable = null;
    pembayaranSppt = null;
    statusTrx = null;

    try {
      callable = boneCPDs.getConnection().prepareCall("call proses_pembayaran(?,?,?,?,?)");
      callable.registerOutParameter(1, OracleTypes.CURSOR);
      callable.setString(2, nop);
      callable.setString(3, thn);
      callable.setDate(4, new java.sql.Date(tglBayar.getTime()));
      callable.setString(5, ipClient);

      callable.executeUpdate();
      ResultSet rs = (ResultSet) callable.getObject(1);
      ResultSetMetaData rsMeta = rs.getMetaData();
      pembayaranSppt = new PembayaranSppt();
      while(rs.next()) {
        SpptRestController.getLogger().debug(" >>> Berhasil masuk iterasi rs.next");
        SpptRestController.getLogger().debug(" >>> nama kolom yang ada : " + rsMeta.getColumnName(1));
        if(!rsMeta.getColumnName(1).equals("KODE_ERROR")) {
          SpptRestController.getLogger().debug(" >>> nop-nya ada : " + rs.getString("nop"));
          pembayaranSppt.setNop(rs.getString("NOP"));
          pembayaranSppt.setThn(rs.getString("THN"));
          pembayaranSppt.setNtpd(rs.getString("NTPD"));
          pembayaranSppt.setMataAnggaranPokok(rs.getString("MATA_ANGGARAN_POKOK"));
          pembayaranSppt.setPokok(rs.getBigDecimal("POKOK").toBigInteger());
          pembayaranSppt.setMataAnggaranSanksi(rs.getString("MATA_ANGGARAN_POKOK"));
          pembayaranSppt.setSanksi(rs.getBigDecimal("SANKSI").toBigInteger());
          pembayaranSppt.setNamaWp(rs.getString("NAMA_WP"));
          pembayaranSppt.setAlamatOp(rs.getString("ALAMAT_OP"));
        } else {
          String infoSp = rs.getString("KODE_ERROR");
          if(infoSp.equals("01")) {
            statusTrx = new StatusTrx(StatusRespond.TAGIHAN_TELAH_TERBAYAR, "Tagihan Telah Terbayar atau Pokok Pajak Nihil.", null);
            return statusTrx;
          } else if(infoSp.equals("02")) {
            // not used
          } else if(infoSp.equals("03")) {
            statusTrx = new StatusTrx(StatusRespond.TAGIHAN_TELAH_TERBAYAR, "Tagihan Telah Terbayar", null);
            return statusTrx;
          } else if(infoSp.equals("04")) {
            statusTrx = new StatusTrx(StatusRespond.JUMLAH_SETORAN_NIHIL, "Tagihan SPPT Telah Dibatalkan", null);
            return statusTrx;
          }
        }
      }
      statusTrx = new StatusTrx(StatusRespond.APPROVED, "Pembayaran Telah Tercatat", pembayaranSppt);
    } catch(Exception ex) {
      SpptRestController.getLogger().debug(" >>> hasil Exception : " + ex);
      statusTrx = new StatusTrx(StatusRespond.DATABASE_ERROR, "Kesalahan Server", null);
      return statusTrx;
    }

    return statusTrx;
  }

  public StatusRev reversalPembayaran(String nop, String thn, String ntpd, String ipClient) {
    callable = null;
    revPembayaran = null;
    statusRev = null;

    try {
      callable = boneCPDs.getConnection().prepareCall("call reversal_pembayaran(?,?,?,?,?)");
      callable.registerOutParameter(1, OracleTypes.CURSOR);
      callable.setString(2, nop);
      callable.setString(3, thn);
      callable.setString(4, ntpd);
      callable.setString(5, ipClient);
      callable.executeUpdate();

      ResultSet rs = (ResultSet) callable.getObject(1);
      ResultSetMetaData rsMeta = rs.getMetaData();
      ReversalPembayaran revBayar = new ReversalPembayaran();
      while(rs.next()) {
        if(!rsMeta.getColumnName(1).equals("KODE_ERROR")) {
          revBayar.setNop(rs.getString("NOP"));
          revBayar.setThn(rs.getString("THN"));
          revBayar.setNtpd(rs.getString("NTPD"));
          statusRev = new StatusRev(StatusRespond.APPROVED, "Proses Reversal Berhasil", revBayar);
        } else {
          String infoSp = rs.getString("KODE_ERROR");
          if(infoSp.equals("01")) {
            statusRev = new StatusRev(StatusRespond.DATA_INQ_NIHIL, "Data Yang Diminta Tidak Ada", null);
            return statusRev;
          } else if(infoSp.equals("02")) {
            statusRev = new StatusRev(StatusRespond.DATABASE_ERROR, "Data tersebut Ganda", null);
            return statusRev;
          }
        }
      }
    } catch(Exception ex) {
      SpptRestController.getLogger().debug(" >>> hasil Exception : " + ex);
      statusRev = new StatusRev(StatusRespond.DATABASE_ERROR, "Kesalahan Server", null);
      return statusRev;
    }
    return statusRev;
  }

  private BigInteger hitungDenda(BigInteger pokok, Date tglJatuhTempo) {
    DateTime start = new DateTime(tglJatuhTempo);
    DateTime end = new DateTime();
    int selisih = selisihBulan(start, end);
    if(selisih < 0) return new BigInteger("0");
    else return new BigInteger("0");

  }

  public int selisihBulan(DateTime start, DateTime end) {
    return Months.monthsBetween(start.withDayOfMonth(start.getDayOfMonth()),
        end.withDayOfMonth(start.getDayOfMonth())).getMonths();
  }

}
