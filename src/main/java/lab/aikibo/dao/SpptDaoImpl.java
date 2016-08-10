package lab.aikibo.dao;

import java.util.List;
import java.math.BigInteger;

import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lab.aikibo.model.Sppt;
import lab.aikibo.model.Sppt.SpptPK;
import lab.aikibo.model.SpptSismiop;
import lab.aikibo.model.RefKecamatan;
import lab.aikibo.model.RefKelurahan;
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
		List<Sppt> list = (List<Sppt>) qry.list();
		sppt = (Sppt) list.get(0);

		return sppt;
	}

	@Override
	public Sppt inqSpptByNopThn(String nop, String thn) {
		session = getSession();
		Sppt sppt;

		SpptRestController.getLogger().debug(" >>> masuk inquiry data");
		// hql

		Query qry = session.createQuery("from SpptSismiop where " +
		  "kdPropinsi = :kdPropinsi and kdDati2 = :kdDati2 and kdKecamatan = :kdKecamatan and " +
			"kdKelurahan = :kdKelurahan and " +
			"kdBlok = :kdBlok and noUrut = :noUrut and kdJnsOp = :kdJnsOp and " +
			"thnPajakSppt = :thnPajakSppt");

		qry.setParameter("kdPropinsi", nop.substring(0,2));
		qry.setParameter("kdDati2", nop.substring(2,4));
		qry.setParameter("kdKecamatan", nop.substring(4,7));
		qry.setParameter("kdKelurahan", nop.substring(7,10));
		qry.setParameter("kdBlok", nop.substring(10,13));
		qry.setParameter("noUrut", nop.substring(13,17));
		qry.setParameter("kdJnsOp", nop.substring(17,18));
		qry.setParameter("thnPajakSppt", thn);
		
		SpptRestController.getLogger().debug(" >>> parameter sppt terisi data :");
		SpptRestController.getLogger().debug(" >>> kdPropinsi : " + nop.substring(0,2));
		SpptRestController.getLogger().debug(" >>> kdDati2 : " + nop.substring(2,4));
		SpptRestController.getLogger().debug(" >>> kdKecamatan : " + nop.substring(4,7));
		SpptRestController.getLogger().debug(" >>> kdKelurahan : " + nop.substring(7,10));
		SpptRestController.getLogger().debug(" >>> kdBlok : " + nop.substring(10,13));
		SpptRestController.getLogger().debug(" >>> noUrut : " + nop.substring(13,17));
		SpptRestController.getLogger().debug(" >>> kdJnsOp : " + nop.substring(17,18));
		SpptRestController.getLogger().debug(" >>> thnPajakSppt : " + thn);

		List<SpptSismiop> list = (List<SpptSismiop>) qry.list();

    SpptRestController.getLogger().debug(" >>> isi data: " + list.size());

		SpptSismiop result = (SpptSismiop) list.get(0);

		qry = session.createQuery("from RefKelurahan where kdPropinsi = :kdPropinsi " +
		  "and kdDati2 = :kdDati2 and kdKecamatan = :kdKecamatan and " +
			"kdKelurahan = :kdKelurahan");
		qry.setParameter("kdPropinsi", nop.substring(0,2));
		qry.setParameter("kdDati2", nop.substring(2,4));
		qry.setParameter("kdKecamatan", nop.substring(4,7));
		qry.setParameter("kdKelurahan", nop.substring(7,10));
		List<RefKelurahan> listKelurahan = (List<RefKelurahan>) qry.list();
		RefKelurahan resultKelurahan = (RefKelurahan) listKelurahan.get(0);

		qry = session.createQuery("from RefKecamatan where kdPropinsi = :kdPropinsi " +
		  "and kdDati2 = :kdDati2 and kdKecamatan = :kdKecamatan");
		qry.setParameter("kdPropinsi", nop.substring(0,2));
		qry.setParameter("kdDati2", nop.substring(2,4));
		qry.setParameter("kdKecamatan", nop.substring(4,7));
		List<RefKecamatan> listKecamatan = (List<RefKecamatan>) qry.list();
		RefKecamatan resultKecamatan = (RefKecamatan) listKecamatan.get(0);

		sppt = new Sppt();
		sppt.setNop(nop);
		sppt.setThn(thn);
		sppt.setNama(result.getNmWpSppt());
		sppt.setAlamatOp(resultKelurahan.getNmKelurahan() + " - " + resultKecamatan.getNmKecamatan());
		sppt.setPokok(result.getPbbYgHarusDibayarSppt());
		sppt.setDenda(new BigInteger("0"));
		return sppt;
	}

}
