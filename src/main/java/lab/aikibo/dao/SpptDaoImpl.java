package lab.aikibo.dao;

import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lab.aikibo.model.Sppt;
import lab.aikibo.model.Sppt.SpptPK;
import lab.aikibo.controller.SpptRestController;


@Repository("spptDao")
public class SpptDaoImpl extends AbstractDao<Integer, Sppt> implements SpptDao {

	Session session = null;
	Transaction tx = null;

	@Override
	public Sppt getSpptByNopThn(String nop, String thn) {
		session = getSession();
		//SpptPK spptPk = new SpptPK(nop, thn);
		Sppt sppt;
		Query qry = session.createQuery("from Sppt s where s.nop = :nop and s.thn = :thn");
		qry.setParameter("nop", nop);
		qry.setParameter("thn", thn);
		SpptRestController.getLogger().debug(">>> sql: " + qry.getQueryString());
		List<Sppt> list = (List<Sppt>) qry.list();
    SpptRestController.getLogger().debug("-----");
		SpptRestController.getLogger().debug("DATA LIST ADA : " + list.size());
		sppt = (Sppt) list.get(0);

		return sppt;
	}

}
