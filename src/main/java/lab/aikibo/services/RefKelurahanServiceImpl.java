package lab.aikibo.services;

import java.util.List;

import lab.aikibo.model.RefKelurahan;
import lab.aikibo.dao.RefKelurahanDao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Service("refKelurahanService")
@Transactional
public class RefKelurahanServiceImpl implements RefKelurahanService {

  @Autowired
  private RefKelurahanDao refKelurahanDao;

  @Override
  public List<RefKelurahan> getDaftarKelurahan(String kdKecamatan) {
    return refKelurahanDao.getDaftarKelurahan(kdKecamatan);
  }

}
