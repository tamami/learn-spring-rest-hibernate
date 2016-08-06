package lab.aikibo.model;

import java.io.Serializable;

public class RefKelurahanPK implements Serializable {

  private static final long serialVersionUID = 2L;

  private String kdPropinsi;
  private String kdDati2;
  private String kdKecamatan;
  private String kdKelurahan;

  @Override
  public boolean equals(Object obj) {
    if(obj == null) return false;
    if(!(obj instanceof RefKelurahanPK)) return false;

    RefKelurahanPK pk = (RefKelurahanPK) obj;
    return pk.kdPropinsi.equals(kdPropinsi) && pk.kdDati2.equals(kdDati2) &&
        pk.kdKecamatan.equals(kdKecamatan) && pk.kdKelurahan.equals(kdKelurahan);
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + kdPropinsi.hashCode();
    result = 31 * result + kdDati2.hashCode();
    result = 31 * result + kdKecamatan.hashCode();
    result = 31 * result + kdKelurahan.hashCode();
    return result;
  }

  // --- setter getter

  public String getKdPropinsi() {
    return kdPropinsi;
  }

  public void setKdPropinsi(String kdPropinsi) {
    this.kdPropinsi = kdPropinsi;
  }

  public String getKdDati2() {
    return kdDati2;
  }

  public void setKdDati2(String kdDati2) {
    this.kdDati2 = kdDati2;
  }

  public String getKdKecamatan() {
    return kdKecamatan;
  }

  public void setKdKecamatan(String kdKecamatan) {
    this.kdKecamatan = kdKecamatan;
  }

  public String getKdKelurahan() {
    return kdKelurahan;
  }

  public void setKdKelurahan(String kdKelurahan) {
    this.kdKelurahan = kdKelurahan;
  }

}
