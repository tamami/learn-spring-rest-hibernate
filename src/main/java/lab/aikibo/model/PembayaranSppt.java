package lab.aikibo.model;

import java.io.Serializable;

import java.math.BigInteger;

import lab.aikibo.constant.SerialConstant;

public class PembayaranSppt implements Serializable {

  private static final long serialVersionUID = SerialConstant.SERIAL_PEMBAYARAN_SPPT;

  private String nop;
  private String thn;
  private String ntpd;
  private String mataAnggaranPokok;
  private BigInteger pokok;
  private String mataAnggaranSanksi;
  private BigInteger sanksi;
  private String namaWp;
  private String alamatOp;

  // --- constructors

  public PembayaranSppt() {}

  public PembayaranSppt(String nop, String thn, String ntpd,
      String mataAnggaranPokok, BigInteger pokok, String mataAnggaranSanksi,
      BigInteger sanksi, String namaWp, String alamatOp) {
    this.nop = nop;
    this.thn = thn;
    this.ntpd = ntpd;
    this.mataAnggaranPokok = mataAnggaranPokok;
    this.pokok = pokok;
    this.mataAnggaranSanksi = mataAnggaranSanksi;
    this.sanksi = sanksi;
    this.namaWp = namaWp;
    this.alamatOp = alamatOp;
  }


  // -- setter getter

  public String getNop() { return nop; }

  public void setNop(String nop) { this.nop = nop; }

  public String getThn() { return thn; }

  public void setThn(String thn) { this.thn = thn; }

  public String getNtpd() { return ntpd; }

  public void setNtpd(String ntpd) { this.ntpd = ntpd; }

  public String getMataAnggaranPokok() { return mataAnggaranPokok; }

  public void setMataAnggaranPokok(String mataAnggaranPokok) { this.mataAnggaranPokok = mataAnggaranPokok; }

  public BigInteger getPokok() { return pokok; }

  public void setPokok(BigInteger pokok) { this.pokok = pokok; }

  public String getMataAnggaranSanksi() { return mataAnggaranSanksi; }

  public void setMataAnggaranSanksi(String mataAnggaranSanksi) { this.mataAnggaranSanksi = mataAnggaranSanksi; }

  public BigInteger getSanksi() { return sanksi; }

  public void setSanksi(BigInteger sanksi) { this.sanksi = sanksi; }

  public String getNamaWp() { return namaWp; }

  public void setNamaWp(String namaWp) { this.namaWp = namaWp; }

  public String getAlamatOp() { return alamatOp; }

  public void setAlamatOp(String alamatOp) { this.alamatOp = alamatOp; }

}
