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
import lab.aikibo.model.SpptSismiop;

import lab.aikibo.constant.StatusRespond;

import lab.aikibo.controller.SpptRestController;

@Repository("storeProceduresDao")
public class StoreProceduresDaoImpl implements StoreProceduresDao {

  @Autowired
  private SessionFactory sessionFactory;
  @Autowired
  private BoneCPDataSource boneCPDs;

  public StatusInq getDataSppt(String nop, String thn) {
    // -- ini cara 1; lumpuh saat panggil ke connection() deprecated

    CallableStatement callable = null;
    Sppt sppt = null;
    StatusInq status = null;

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
