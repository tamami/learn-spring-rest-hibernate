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
import lab.aikibo.model.SpptSismiop;

import lab.aikibo.controller.SpptRestController;

@Repository("storeProceduresDao")
public class StoreProceduresDaoImpl implements StoreProceduresDao {

  @Autowired
  private SessionFactory sessionFactory;
  @Autowired
  private BoneCPDataSource boneCPDs;

  public SpptJ getDataSppt(String nop, String thn) {
    // -- ini cara 1; lumpuh saat panggil ke connection() deprecated

    CallableStatement callable = null;
    SpptJ sppt = null;

    try {
      /*
      getSession().doWork(
        new Work() {
          public void execute(Connection conn) throws SQLException {
            callable = conn.prepareCall("{call sppt_terhutang(?,?,?)}");
          }
        }
      );
      */
      callable = boneCPDs.getConnection().prepareCall("call sppt_terhutang(?,?,?)");
      callable.registerOutParameter(1, OracleTypes.CURSOR);
      callable.setString(2, nop);
      callable.setString(3, thn);
      callable.executeUpdate();
      ResultSet rs = (ResultSet) callable.getObject(1);
      ResultSetMetaData rsmd = rs.getMetaData();
      String data1 = rsmd.getColumnName(1);
      String data2 = rsmd.getColumnName(2);
      sppt = new SpptJ();

      while(rs.next()) {
        SpptRestController.getLogger().debug(" >>> DATA1: " + data1);
        SpptRestController.getLogger().debug(" >>> DATA2: " + data2);
        SpptRestController.getLogger().debug(" >>> NM_WP_SPPT: " + rs.findColumn("NM_WP_SPPT")); // 15
        SpptRestController.getLogger().debug(" >>> >>> : " + rs.getString(15));
        SpptRestController.getLogger().debug(" >>> >>> : " + rs.getString("NM_WP_SPPT"));
        //SpptRestController.getLogger().debug(" >>> KD_KECAMATAN: " + rs.findColumn("KD_KECAMATAN"));
        SpptRestController.getLogger().debug(" >>> KD_KECAMATAN: " + rs.findColumn("KD_KECAMATAN") +
          " -> " + rs.getString("KD_KECAMATAN"));
        //SpptRestController.getLogger().debug(" >>> KD_KELURAHAN: " + rs.findColumn("KD_KELURAHAN"));
        SpptRestController.getLogger().debug(" >>> KD_KELURAHAN: " + rs.findColumn("KD_KELURAHAN") +
          " -> " + rs.getString("KD_KELURAHAN"));
        //SpptRestController.getLogger().debug(" >>> PBB_YG_HARUS_DIBAYAR_SPPT : " + rs.findColumn("PBB_YG_HARUS_DIBAYAR_SPPT")); // 39
        SpptRestController.getLogger().debug(" >>> PBB_YG_HARUS_DIBAYAR_SPPT : " + rs.findColumn("PBB_YG_HARUS_DIBAYAR_SPPT") +
          " -> " + rs.getBigDecimal("PBB_YG_HARUS_DIBAYAR_SPPT"));
        //SpptRestController.getLogger().debug(" >>> TGL_JATUH_TEMPO_SPPT: " + rs.findColumn("TGL_JATUH_TEMPO_SPPT")); // 29
        SpptRestController.getLogger().debug(" >>> TGL_JATUH_TEMPO_SPPT: " + rs.findColumn("TGL_JATUH_TEMPO_SPPT") +
          " -> " + rs.getDate("TGL_JATUH_TEMPO_SPPT"));


        String nama = rs.getString("NM_WP_SPPT");
        SpptRestController.getLogger().debug(" >>> Nama terisi");
        String alamatOp = rs.getString("KD_KECAMATAN") + rs.getString("KD_KELURAHAN");
        SpptRestController.getLogger().debug(" >>> Alamat OP terisi");
        BigInteger pokok = rs.getBigDecimal("PBB_YG_HARUS_DIBAYAR_SPPT").toBigInteger();
        SpptRestController.getLogger().debug(" >>> Pokok terisi");
        Date tglJatuhTempo = rs.getDate("TGL_JATUH_TEMPO_SPPT");
        SpptRestController.getLogger().debug(" >>> Jatuh tempo terisi");
        BigInteger denda = hitungDenda(pokok, tglJatuhTempo);
        SpptRestController.getLogger().debug(" >>> Denda terisi");
        sppt.setNop(nop);
        SpptRestController.getLogger().debug(" >>> Sppt.nop terisi");
        sppt.setThn(thn);
        sppt.setNama(nama);
        sppt.setAlamatOp(alamatOp);
        sppt.setPokok(pokok);
        sppt.setDenda(denda);
        SpptRestController.getLogger().debug(" >>> " + sppt.getNop() + " : " +
          sppt.getThn() + " : " + sppt.getNama() + " : " + sppt.getAlamatOp() +
          " : " + sppt.getPokok() + " : " + sppt.getDenda());
      }
      SpptRestController.getLogger().debug(" >>> NOP: " + nop);
      return sppt;
    } catch(Exception e) {}
    return sppt;
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
