package lab.aikibo.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.math.BigInteger;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.hibernate.jpamodelgen.xml.jaxb.ParameterMode;

import org.joda.time.DateTime;
import org.joda.time.Months;

import oracle.jdbc.OracleTypes;

import lab.aikibo.model.Sppt;
import lab.aikibo.model.SpptSismiop;

import lab.aikibo.controller.SpptRestController;

@Repository("storeProceduresDao")
public class StoreProceduresDaoImpl extends AbstractDao<Integer, SpptSismiop>
    implements StoreProceduresDao {



  public Sppt getDataSppt(String nop, String thn) {
    // -- ini cara 1; lumpuh saat panggil ke connection() deprecated

    CallableStatement callable = null;
    Sppt sppt = null;
    /*
    try {
      getSession().doWork(
        new Work() {
          public void execute(Connection conn) throws SQLException {
            callable = conn.prepareCall("{call sppt_terhutang(?,?,?)}");
          }
        }
      );
      callable.registerOutParameter(1, OracleTypes.CURSOR);
      callable.setString(2, nop);
      callable.setString(3, thn);
      callable.executeUpdate();
      ResultSet rs = (ResultSet) callable.getObject(1);
      while(rs.next()) {
        String nama = rs.getString("NM_WP_SPPT");
        String alamatOp = rs.getString("KD_KECAMATAN") + rs.getString("KD_KELURAHAN");
        BigInteger pokok = rs.getBigDecimal("PBB_YG_HARUS_DIBAYAR_SPPT").toBigInteger();
        Date tglJatuhTempo = rs.getDate("TGL_JATUH_TEMPO_SPPT");
        BigInteger denda = hitungDenda(pokok, tglJatuhTempo);
      }
      return sppt;
    } catch(Exception e) {}
    */

    // -- ini cara 2
    // seharusnya bentuk sp seperti ini
    //
    // create procedure sppt_terhutang(nop varchar, thn varchar)
    // begin
    //   select * from sppt where status_pembayaran_sppt = '0'
    //     and nop = nop
    //     and thn_pajak_sppt = thn;
    // end
    Session sess = getSession();
    Query qry = sess.createSQLQuery("CALL sppt_terhutang(:nop,:thn )")
    .addEntity(SpptSismiop.class)
    .setParameter("nop", nop)
    .setParameter("thn", thn);

    List result = qry.list();
    SpptSismiop spptSismiop = (SpptSismiop) result.get(0);
    SpptRestController.getLogger().debug(" >>> nop " + spptSismiop.getKdPropinsi());
    for(int i=0; i<result.size(); i++) {
      //sppt = (SpptSismiop) result.get(i);
    }

    // -- ini cara 3
    /*
    StoredProcedureQuery query = entityManager
      .createStoredProcedureQuery("sppt_terhutang")
      .registerStoredProcedureParameter(1, SpptSismiop.class, ParameterMode.REF_CURSOR)
      .registerStoredProcedureParameter(2, String.class, ParameterMode.IN)
      .registerStoredProcedureParameter(3, String.class, ParameterMode.IN)
      .setParameter(1, nop)
      .setParameter(2, nop);
    query.execute();
    List<Object[]> post = query.getResultList();
    */

    // -- ini cara 4
    Session session = getSession();
    ProcedureCall call = session.createStoredProecedureCall("sppt_terhutang");
    call.registerParameter(1, SpptSismiop.class, ParameterMode.REF_CURSOR);
    call.registerParameter(2, String.class, ParameterMode.IN).bindValue(nop);
    call.registerParameter(3, String.class, ParameterMode.IN).bindValue(thn);

    Output output = call.getOutputs().getCurrent();
    if(output.isResultSet()) {
      List<Object[]> post = ((ResultSetOutput) output).getResultList();
    }

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
