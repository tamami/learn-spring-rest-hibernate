package lab.aikibo.model;

public class StatusRev extends Status {
  private ReversalPembayaran revPembayaran;

  public StatusRev() {}

  public StatusRev(int code, int message, ReversalPembayaran revPembayaran) {
    super(code, message);
    this.revPembayaran = revPembayaran;
  }

  // --- getter setter
  public ReversalPembayaran getRevPembayaran() { return revPembayaran; }

  public void setRevPembayaran(ReversalPembayaran revPembayaran) { this.revPembayaran = revPembayaran; }
}
