package lab.aikibo.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.IdClass;
import javax.persistence.Id;
import javax.persistence.Column;

import java.util.Date;

import java.math.BigInteger;

import java.io.Serializable;

@Entity
@Table(name="sppt")
@IdClass(SpptSismiop.SpptSismiopPk.class)
public class SpptSismiop implements Serializable {

  private static final long serialVersionUID = 3L;

  @Id
  private String kdPropinsi;
  @Id
  private String kdDati2;
  @Id
  private String kdKecamatan;
  @Id
  private String kdKelurahan;
  @Id
  private String kdBlok;
  @Id
  private String noUrut;
  @Id
  private String kdJnsOp;
  @Id
  private String thnPajakSppt;
  @Column(name="SIKLUS_SPPT")
  private int siklusSppt;
  @Column(name="KD_KANWIL_BANK")
  private String kdKanwilBank;
  @Column(name="KD_KPPBB_BANK")
  private String kdKppbbBank;
  @Column(name="KD_BANK_TUNGGAL")
  private String kdBankTunggal;
  @Column(name="KD_BANK_PERSEPSI")
  private String kdBankPersepsi;
  @Column(name="KD_TP")
  private String kdTp;
  @Column(name="NM_WP_SPPT")
  private String nmWpSppt;
  @Column(name="JLN_WP_SPPT")
  private String jlnWpSppt;
  @Column(name="BLOK_KAV_NO_WP_SPPT")
  private String blokKavNoWpSppt;
  @Column(name="RW_WP_SPPT")
  private String rwWpSppt;
  @Column(name="RT_WP_SPPT")
  private String rtWpSppt;
  @Column(name="KELURAHAN_WP_SPPT")
  private String kelurahanWpSppt;
  @Column(name="KOTA_WP_SPPT")
  private String kotaWpSppt;
  @Column(name="KD_POS_WP_SPPT")
  private String kdPosWpSppt;
  @Column(name="NPWP_SPPT")
  private String npwpSppt;
  @Column(name="NO_PERSIL_SPPT")
  private String noPersilSppt;
  @Column(name="KD_KLS_TANAH")
  private String kdKlsTanah;
  @Column(name="THN_AWAL_KLS_TANAH")
  private String thnAwalKlsTanah;
  @Column(name="KD_KLS_BNG")
  private String kdKlsBng;
  @Column(name="THN_AWAL_KLS_BNG")
  private String thnAwalKlsBng;
  @Column(name="TGL_JATUH_TEMPO_SPPT")
  private Date tglJatuhTempoSppt;
  @Column(name="LUAS_BUMI_SPPT")
  private long luasBumiSppt;
  @Column(name="LUAS_BNG_SPPT")
  private long luasBngSppt;
  @Column(name="NJOP_BUMI_SPPT")
  private BigInteger njopBumiSppt;
  @Column(name="NJOP_BNG_SPPT")
  private BigInteger njopBngSppt;
  @Column(name="NJOP_SPPT")
  private BigInteger njopSppt;
  @Column(name="NJOPTKP_SPPT")
  private BigInteger njoptkpSppt;
  @Column(name="NJKP_SPPT")
  private BigInteger njkpSppt;
  @Column(name="PBB_TERHUTANG_SPPT")
  private BigInteger pbbTerhutangSppt;
  @Column(name="FAKTOR_PENGURANG_SPPT")
  private BigInteger faktorPengurangSppt;
  @Column(name="PBB_YG_HARUS_DIBAYAR_SPPT")
  private BigInteger pbbYgHarusDibayarSppt;
  @Column(name="STATUS_PEMBAYARAN_SPPT")
  private char statusPembayaranSppt;
  @Column(name="STATUS_TAGIHAN_SPPT")
  private char statusTagihanSppt;
  @Column(name="STATUS_CETAK_SPPT")
  private char statusCetakSppt;
  @Column(name="TGL_TERBIT_SPPT")
  private Date tglTerbitSppt;
  @Column(name="TGL_CETAK_SPPT")
  private Date tglCetakSppt;
  @Column(name="NIP_PENCETAK_SPPT")
  private String nipPencetakSppt;



  // --- setter getter

  public String getKdPropinsi() { return kdPropinsi; }

  public String getKdDati2() { return kdDati2; }

  public String getKdKecamatan() { return kdKecamatan; }

  public String getKdKelurahan() { return kdKelurahan; }

  public String getKdBlok() { return kdBlok; }

  public String getNoUrut() { return noUrut; }

  public String getKdJnsOp() { return kdJnsOp; }

  public String getThnPajakSppt() { return thnPajakSppt; }

  public int getSiklusSppt() { return siklusSppt; }

  public String getKdKanwilBank() { return kdKanwilBank; }

  public String getKdKppbbBank() { return kdKppbbBank; }

  public String getKdBankTunggal() { return kdBankTunggal; }

  public String getKdBankPersepsi() { return kdBankPersepsi; }

  public String getKdTp() { return kdTp; }

  public String getNmWpSppt() { return nmWpSppt; }

  public String getJlnWpSppt() { return jlnWpSppt; }

  public String getBlokKavNoWpSppt() { return blokKavNoWpSppt; }

  public String getRwWpSppt() { return rwWpSppt; }

  public String getRtWpSppt() { return rtWpSppt; }

  public String getKelurahanWpSppt() { return kelurahanWpSppt; }

  public String getKotaWpSppt() { return kotaWpSppt; }

  public String getKdPosWpSppt() { return kdPosWpSppt; }

  public String getNpwpSppt() { return npwpSppt; }

  public String getNoPersilSppt() { return noPersilSppt; }

  public String getKdKlsTanah() { return kdKlsTanah; }

  public String getThnAwalKlsTanah() { return thnAwalKlsTanah; }

  public String getKdKlsBng() { return kdKlsBng; }

  public String getThnAwalKlsBng() { return thnAwalKlsBng; }

  public Date getTglJatuhTempoSppt() { return tglJatuhTempoSppt; }

  public BigInteger getLuasBumiSppt() { return luasBumiSppt; }

  public BigInteger getLuasBngSppt() { return luasBngSppt; }

  public BigInteger getNjopBumiSppt() { return njopBumiSppt; }

  public BigInteger getNjopBngSppt() { return njopBngSppt; }

  public BigInteger getNjopSppt() { return njopSppt; }

  public BigInteger getNjoptkpSppt() { return njoptkpSppt; }

  public BigInteger getNjkpSppt() { return njkpSppt; }

  public BigInteger getPbbTerhutangSppt() { return pbbTerhutangSppt; }

  public BigInteger getFaktorPengurangSppt() { return faktorPengurangSppt; }

  public BigInteger getPbbYgHarusDibayarSppt() { return pbbYgHarusDibayarSppt; }

  public char getStatusPembayaranSppt() { return statusPembayaranSppt; }

  public char getStatusTagihanSppt() { return statusTagihanSppt; }

  public char getStatusCetakSppt() { return statusCetakSppt; }

  public Date getTglTerbitSppt() { return tglTerbitSppt; }

  public Date getTglCetakSppt() { return tglCetakSppt; }

  public String getNipPencetakSppt() { return nipPencetakSppt; }

  // --- inner class

  public class SpptSismiopPk implements Serializable {

    private static final long serialVersionUID = 4L;

    private String kdPropinsi;
    private String kdDati2;
    private String kdKecamatan;
    private String kdKelurahan;
    private String kdBlok;
    private String noUrut;
    private String kdJnsOp;
    private String thnPajakSppt;

    public SpptSismiopPk() {}

    public SpptSismiopPk(String kdPropinsi, String kdDati2, String kdKecamatan,
        String kdKelurahan, String kdBlok, String noUrut, String kdJnsOp,
        String thnPajakSppt) {
      this.kdPropinsi = kdPropinsi;
      this.kdDati2 = kdDati2;
      this.kdKecamatan = kdKecamatan;
      this.kdKelurahan = kdKelurahan;
      this.kdBlok = kdBlok;
      this.noUrut = noUrut;
      this.kdJnsOp = kdJnsOp;
      this.thnPajakSppt = thnPajakSppt;
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

    public String getKdBlok() { return kdBlok; }

    public void setKdBlok(String kdBlok) { this.kdBlok = kdBlok; }

    public String getNoUrut() { return noUrut; }

    public void setNoUrut(String noUrut) { this.noUrut = noUrut; }

    public String getKdJnsOp() { return kdJnsOp; }

    public void setKdJnsOp(String kdJnsOp) { this.kdJnsOp = kdJnsOp; }

    public String getThnPajakSppt() { return thnPajakSppt; }

    public void setThnPajakSppt(String thnPajakSppt) { this.thnPajakSppt = thnPajakSppt; }

  }

}
