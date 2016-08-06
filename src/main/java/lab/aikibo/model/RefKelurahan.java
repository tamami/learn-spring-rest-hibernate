package lab.aikibo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.IdClass;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name = "ref_kelurahan")
@IdClass(value = RefKelurahanPK.class)
public class RefKelurahan implements Serializable {

  private static final long serialVersionUID = 3L;

  @Id
  private String kdPropinsi;
  @Id
  private String kdDati2;
  @Id
  private String kdKecamatan;
  @Id
  private String kdKelurahan;

  @Column(name="nm_kelurahan")
  private String nmKelurahan;

  // --- setter and getter

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

  public String getNmKelurahan() {
    return nmKelurahan;
  }

  public void setNmKelurahan(String nmKelurahan) {
    this.nmKelurahan = nmKelurahan;
  }

}
