package lab.aikibo.model;

import java.io.Serializable;

import lab.aikibo.constant.SerialConstant;

public class ReversalPembayaran implements Serializable {

  private static final long serialVersionUID = SerialConstant.SERIAL_REVERSAL_PEMBAYARAN;

  private String nop;
  private String thn;
  private String ntpd;

  public ReversalPembayaran() {};

  public ReversalPembayaran(String nop, String thn, String ntpd) {
    this.nop = nop;
    this.thn = thn;
    this.ntpd = ntpd;
  }

  // --- setter getter

  public String getNop() { return nop; }

  public void setNop(String nop) { this.nop = nop; }

  public String getThn() { return thn; }

  public void setThn(String thn) { this.thn = thn; }

  public String getNtpd() { return ntpd; }

  public void setNtpd(String ntpd) { this.ntpd = ntpd; }

}
