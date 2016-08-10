package lab.aikibo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.IdClass;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import lab.aikibo.constant.SerialConstant;

@Entity
@Table(name="REF_KELURAHAN")
@IdClass(RefKelurahan.RefKelurahanPk.class)
public class RefKelurahan implements Serializable {

  private static final long serialVersionUID = SerialConstant.SERIAL_REF_KELURAHAN;

  @Id
  @Column(name="KD_PROPINSI", columnDefinition="char(2)")
  private String kdPropinsi;
  @Id
  @Column(name="KD_DATI2", columnDefinition="char(2)")
  private String kdDati2;
  @Id
  @Column(name="KD_KECAMATAN", columnDefinition="char(3)")
  private String kdKecamatan;
  @Id
  @Column(name="KD_KELURAHAN", columnDefinition="char(3)")
  private String kdKelurahan;
  @Column(name="KD_SEKTOR", columnDefinition="char(2)")
  private String kdSektor;
  @Column(name="NM_KELURAHAN")
  private String nmKelurahan;
  @Column(name="NO_KELURAHAN")
  private int noKelurahan;
  @Column(name="KD_POS_KELURAHAN")
  private String kdPosKelurahan;

  // --- setter getter

  public String getKdPropinsi() { return kdPropinsi; }

  public void setKdPropinsi(String kdPropinsi) { this.kdPropinsi = kdPropinsi; }

  public String getKdDati2() { return kdDati2; }

  public void setKdDati2(String kdDati2) { this.kdDati2 = kdDati2; }

  public String getKdKecamatan() { return kdKecamatan; }

  public void setKdKecamatan(String kdKecamatan) { this.kdKecamatan = kdKecamatan; }

  public String getKdKelurahan() { return kdKelurahan; }

  public void setKdKelurahan(String kdKelurahan) { this.kdKelurahan = kdKelurahan; }

  public String getKdSektor() { return kdSektor; }

  public void setKdSektor(String kdSektor) { this.kdSektor = kdSektor; }

  public String getNmKelurahan() { return nmKelurahan; }

  public void setNmKelurahan(String nmKelurahan) { this.nmKelurahan = nmKelurahan; }

  public int getNoKelurahan() { return noKelurahan; }

  public void setNoKelurahan(int noKelurahan) { this.noKelurahan = noKelurahan; }

  public String getKdPosKelurahan() { return kdPosKelurahan; }

  public void setKdPosKelurahan(String kdPosKelurahan) { this.kdPosKelurahan = kdPosKelurahan; }

  // --- inner class

  @Embeddable
  public class RefKelurahanPk implements Serializable {
    private static final long serialVersionUID = SerialConstant.SERIAL_REF_KELURAHAN_PK;

    private String kdPropinsi;
    private String kdDati2;
    private String kdKecamatan;
    private String kdKelurahan;

    // --- constructor

    public RefKelurahanPk() {}

    public RefKelurahanPk(String kdPropinsi, String kdDati2, String kdKecamatan,
        String kdKelurahan) {
      this.kdPropinsi = kdPropinsi;
      this.kdDati2 = kdDati2;
      this.kdKecamatan = kdKecamatan;
      this.kdKelurahan = kdKelurahan;
    }

    // --- setter getter

    public String getKdPropinsi() { return kdPropinsi; }

    public void setKdPropinsi(String kdPropinsi) { this.kdPropinsi = kdPropinsi; }

    public String getKdDati2() { return kdDati2; }

    public void setKdDati2(String kdDati2) { this.kdDati2 = kdDati2; }

    public String getKdKecamatan() { return kdKecamatan; }

    public void setKdKecamatan(String kdKecamatan) { this.kdKecamatan = kdKecamatan; }

    public String getKdKelurahan() { return kdKelurahan; }

    public void setKdKelurahan(String kdKelurahan) { this.kdKelurahan = kdKelurahan; }
  }

}
