package lab.aikibo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lab.aikibo.dao.SpptDao;
import lab.aikibo.model.Sppt;

@Service("spptService")
@Transactional
public class SpptServicesImpl implements SpptServices {

	@Autowired
	private SpptDao spptDao;

	@Override
	public Sppt getSpptByNopThn(String nop, String thn) {
		return spptDao.getSpptByNopThn(nop, thn);
	}

}
