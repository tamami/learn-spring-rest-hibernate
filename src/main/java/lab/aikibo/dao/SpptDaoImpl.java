package lab.aikibo.dao;


import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import lab.aikibo.model.Sppt;
import lab.aikibo.model.Sppt.SpptPK;

public class SpptDaoImpl implements SpptDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	Session session = null;
	Transaction tx = null;

	@Override
	public Sppt getSpptByNopThn(String nop, String thn) throws Exception {
		session = sessionFactory.openSession();
		SpptPK spptPk = new SpptPK(nop, thn);
		Sppt sppt = (Sppt) session.get(Sppt.class, spptPk);
		tx = session.getTransaction();
		session.beginTransaction();
		tx.commit();
		
		return sppt;
	}

}
