package lab.aikibo.services;

import lab.aikibo.model.StatusRev;

import lab.aikibo.dao.StoreProceduresDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("reversalServices")
@Transactional
public class ReversalServicesImpl implements ReversalServices {

  @Autowired
  private StoreProceduresDao spDao;

  public StatusRev prosesReversal(String nop, String thn, String ntpd, String ipClient) {
    return spDao.reversalPembayaran(nop, thn, ntpd, ipClient);
  }
}
