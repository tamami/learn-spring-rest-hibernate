package lab.aikibo.model;

public class StatusInq extends Status {
  private Sppt sppt;

  public StatusInq() {}

  public StatusInq(int code, String message, Sppt sppt) {
    super(code, message);
    this.sppt = sppt;
  }

  public Sppt getSppt() { return sppt; }

  public void setSppt(Sppt sppt) { this.sppt = sppt; }

}
