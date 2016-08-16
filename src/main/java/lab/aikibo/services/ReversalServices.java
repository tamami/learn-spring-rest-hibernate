package lab.aikibo.services;

import lab.aikibo.model.StatusRev;

public interface ReversalServices {
  public StatusRev prosesReversal(String nop, String thn, String ntpd, String ipClient);
}
