package lab.aikibo.dao;

@Repository
public class StoreProceduresDaoImpl extends AbstractDao<Integer, SpptSismiop>
    implements StoreProceduresDao {

  public SpptSismiop getDataSppt(String nop, String thn) {
    // -- ini cara 1
    Connection conn = getSession().connection();
    CallableStatement callable = null;
    try {
      callable = conn.prepareCall("execute sppt_terhutang(?,?,?)");
      callable.registerOutParameter(1, SpptSismiop.class);
      callable.registerInParameter(2, nop);
      callable.registerInParameter(3, thn);
      callable.execute();
      SpptSismiop result = (SpptSismiop) callable.get(1);
      return result;
    } catch(Exception e) {}


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
    Query qry = sess.createSQLQuery("CALL sppt_terhutang(:nop,:thn )");
    sess.addEntity(SpptSismiop.class);
    sess.setParameter("nop", nop);
    sess.setParameter("thn", thn);

    List result = qry.list();
    for(int i=0; i<result.size(); i++) {
      SpptSismiop sppt = (SpptSismiop) result.get(i);
    }
    return result;
  }

}
