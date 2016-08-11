package lab.aikibo.model;

public class StatusTrx extends Status {

  private PembayaranSppt byrSppt;

  public StatusTrx() {}

  public StatusTrx(int code, String message, PembayaranSppt byrSppt) {
    super(code, message);
    this.byrSppt = byrSppt;
  }

  // --- setter getter

  public PembayaranSppt getByrSppt() {
    return byrSppt;
  }

  public void setByrSppt(PembayaranSppt byrSppt) {
    this.byrSppt = byrSppt;
  }

}
