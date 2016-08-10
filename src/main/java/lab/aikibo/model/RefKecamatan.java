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
@Table(name="REF_KECAMATAN")
@IdClass(RefKecamatan.RefKecamatanPk.class)
public class RefKecamatan implements Serializable {

  private static final long serialVersionUID = SerialConstant.SERIAL_REF_KECAMATAN;

  @Id
  @Column(name="KD_PROPINSI", columnDefinition="char(2)")
  private String kdPropinsi;
  @Id
  @Column(name="KD_DATI2", columnDefinition="char(2)")
  private String kdDati2;
  @Id
  @Column(name="KD_KECAMATAN", columnDefinition="char(3)")
  private String kdKecamatan;
  @Column(name="NM_KECAMATAN")
  private String nmKecamatan;

  // --- setter getter

  public String getKdPropinsi() { return kdPropinsi; }

  public void setKdPropinsi(String kdPropinsi) { this.kdPropinsi = kdPropinsi; }

  public String getKdDati2() { return kdDati2; }

  public void setKdDati2(String kdDati2) { this.kdDati2 = kdDati2; }

  public String getKdKecamatan() { return kdKecamatan; }

  public void setKdKecamatan(String kdKecamatan) { this.kdKecamatan = kdKecamatan; }

  public String getNmKecamatan() { return nmKecamatan; }

  public void setNmKecamatan(String nmKecamatan) { this.nmKecamatan = nmKecamatan; }


  // --- inner class

  @Embeddable
  public class RefKecamatanPk implements Serializable {

    private static final long serialVersionUID = SerialConstant.SERIAL_REF_KECAMATAN_PK;

    private String kdPropinsi;
    private String kdDati2;
    private String kdKecamatan;

    // --- constructor

    public RefKecamatanPk() {}

    public RefKecamatanPk(String kdPropinsi, String kdDati2, String kdKecamatan) {
      this.kdPropinsi = kdPropinsi;
      this.kdDati2 = kdDati2;
      this.kdKecamatan = kdKecamatan;
    }

    // --- setter getter

    public String getKdPropinsi() { return kdPropinsi; }

    public void setKdPropinsi(String kdPropinsi) { this.kdPropinsi = kdPropinsi; }

    public String getKdDati2() { return kdDati2; }

    public void setKdDati2(String kdDati2) { this.kdDati2 = kdDati2; }

    public String getKdKecamatan() { return kdKecamatan; }

    public void setKdKecamatan(String kdKecamatan) { this.kdKecamatan = kdKecamatan; }

  }

}
