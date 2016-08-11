package lab.aikibo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lab.aikibo.dao.SpptDao;
import lab.aikibo.dao.StoreProceduresDao;
import lab.aikibo.model.Sppt;
import lab.aikibo.model.SpptJ;
import lab.aikibo.model.StatusInq;
import lab.aikibo.controller.SpptRestController;

@Service("spptServices")
@Transactional
public class SpptServicesImpl implements SpptServices {

	@Autowired
	private SpptDao spptDao;

	@Autowired
	private StoreProceduresDao spDao;

	@Override
	public StatusInq getSpptByNopThn(String nop, String thn) {
		//return spptDao.inqSpptByNopThn(nop, thn);
		return spDao.getDataSppt(nop, thn);
	}

}
