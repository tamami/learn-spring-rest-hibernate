package lab.aikibo.services;

import org.springframework.beans.factory.annotation.Autowired;

import lab.aikibo.dao.SpptDao;
import lab.aikibo.model.Sppt;

public class SpptServicesImpl implements SpptServices {
	
	@Autowired
	SpptDao spptDao;

	@Override
	public Sppt getSpptByNopThn(String nop, String thn) throws Exception {
		return spptDao.getSpptByNopThn(nop, thn);
	}

}
