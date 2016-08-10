package lab.aikibo.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.IdClass;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import java.util.Date;

import java.math.BigInteger;

import java.io.Serializable;

import lab.aikibo.constant.SerialConstant;

@Entity
@Table(name="sppt")
@IdClass(SpptSismiop.SpptSismiopPk.class)
public class SpptSismiop implements Serializable {

  private static final long serialVersionUID = SerialConstant.SERIAL_SPPT_SISMIOP;

  @Id
  @Column(name="KD_PROPINSI", columnDefinition="char(2)", nullable=false) // 1
  private String kdPropinsi;
  @Id
  @Column(name="KD_DATI2", columnDefinition="char(2)", nullable=false) // 2
  private String kdDati2;
  @Id
  @Column(name="KD_KECAMATAN", columnDefinition="char(3)", nullable=false) // 3
  private String kdKecamatan;
  @Id
  @Column(name="KD_KELURAHAN", columnDefinition="char(3)", nullable=false) // 4
  private String kdKelurahan;
  @Id
  @Column(name="KD_BLOK", columnDefinition="char(3)", nullable=false) // 5
  private String kdBlok;
  @Id
  @Column(name="NO_URUT", columnDefinition="char(4)", nullable=false) // 6
  private String noUrut;
  @Id
  @Column(name="KD_JNS_OP", columnDefinition="char(1)", nullable=false) // 7
  private String kdJnsOp;
  @Id
  @Column(name="THN_PAJAK_SPPT", columnDefinition="char(4)", nullable=false) // 8
  private String thnPajakSppt;

  @Column(name="SIKLUS_SPPT", nullable=true) // 9
  private int siklusSppt;
  @Column(name="KD_KANWIL_BANK", columnDefinition="char(2)", nullable=true) // 10
  private String kdKanwilBank;
  @Column(name="KD_KPPBB_BANK", columnDefinition="char(2)", nullable=true) // 11
  private String kdKppbbBank;
  @Column(name="KD_BANK_TUNGGAL", columnDefinition="char(2)", nullable=true) // 12
  private String kdBankTunggal;
  @Column(name="KD_BANK_PERSEPSI", columnDefinition="char(2)", nullable=true) // 13
  private String kdBankPersepsi;
  @Column(name="KD_TP", columnDefinition="char(2)", nullable=true) // 14
  private String kdTp;
  @Column(name="NM_WP_SPPT", nullable=true) // 15
  private String nmWpSppt;
  @Column(name="JLN_WP_SPPT", nullable=true) // 16
  private String jlnWpSppt;
  @Column(name="BLOK_KAV_NO_WP_SPPT", nullable=true) // 17
  private String blokKavNoWpSppt;
  @Column(name="RW_WP_SPPT", columnDefinition="char(2)", nullable=true) // 18
  private String rwWpSppt;
  @Column(name="RT_WP_SPPT", columnDefinition="char(3)", nullable=true) // 19
  private String rtWpSppt;
  @Column(name="KELURAHAN_WP_SPPT", nullable=true) // 20
  private String kelurahanWpSppt;
  @Column(name="KOTA_WP_SPPT", nullable=true) // 21
  private String kotaWpSppt;
  @Column(name="KD_POS_WP_SPPT", nullable=true) // 22
  private String kdPosWpSppt;
  @Column(name="NPWP_SPPT", nullable=true) // 23
  private String npwpSppt;
  @Column(name="NO_PERSIL_SPPT", nullable=true) // 24
  private String noPersilSppt;
  @Column(name="KD_KLS_TANAH", columnDefinition="char(3)", nullable=true) // 25
  private String kdKlsTanah;
  @Column(name="THN_AWAL_KLS_TANAH", columnDefinition="char(4)", nullable=true) // 26
  private String thnAwalKlsTanah;
  @Column(name="KD_KLS_BNG", columnDefinition="char(3)", nullable=true) // 27
  private String kdKlsBng;
  @Column(name="THN_AWAL_KLS_BNG", columnDefinition="char(4)", nullable=true) // 28
  private String thnAwalKlsBng;
  @Column(name="TGL_JATUH_TEMPO_SPPT", columnDefinition="date", nullable=true) // 29
  private Date tglJatuhTempoSppt;
  @Column(name="LUAS_BUMI_SPPT", nullable=true) // 30
  private long luasBumiSppt;
  @Column(name="LUAS_BNG_SPPT", nullable=true)  // 31
  private long luasBngSppt;
  @Column(name="NJOP_BUMI_SPPT", nullable=true) // 32
  private BigInteger njopBumiSppt;
  @Column(name="NJOP_BNG_SPPT", nullable=true) // 33
  private BigInteger njopBngSppt;
  @Column(name="NJOP_SPPT", nullable=true) // 34
  private BigInteger njopSppt;
  @Column(name="NJOPTKP_SPPT", nullable=true)  // 35
  private BigInteger njoptkpSppt;
  @Column(name="NJKP_SPPT", nullable=true)  // 36
  private BigInteger njkpSppt;
  @Column(name="PBB_TERHUTANG_SPPT", nullable=true) // 37
  private BigInteger pbbTerhutangSppt;
  @Column(name="FAKTOR_PENGURANG_SPPT", nullable=true) // 38
  private BigInteger faktorPengurangSppt;
  @Column(name="PBB_YG_HARUS_DIBAYAR_SPPT", nullable=true) // 39
  private BigInteger pbbYgHarusDibayarSppt;
  @Column(name="STATUS_PEMBAYARAN_SPPT", nullable=true) // 40
  private char statusPembayaranSppt;
  @Column(name="STATUS_TAGIHAN_SPPT", nullable=true) // 41
  private char statusTagihanSppt;
  @Column(name="STATUS_CETAK_SPPT", nullable=true) // 42
  private char statusCetakSppt;
  @Column(name="TGL_TERBIT_SPPT", columnDefinition="date", nullable=true) // 43
  private Date tglTerbitSppt;
  @Column(name="TGL_CETAK_SPPT", columnDefinition="date", nullable=true) // 44
  private Date tglCetakSppt;
  @Column(name="NIP_PENCETAK_SPPT", columnDefinition="char(9)", nullable=true) // 45
  private String nipPencetakSppt;



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

  public int getSiklusSppt() { return siklusSppt; }

  public void setSiklusSppt(int siklusSppt) { this.siklusSppt = siklusSppt; }

  public String getKdKanwilBank() { return kdKanwilBank; }

  public void setKdKanwilBank(String kdKanwilBank) { this.kdKanwilBank = kdKanwilBank; }

  public String getKdKppbbBank() { return kdKppbbBank; }

  public void setKdKppbbBank(String kdKppbbBank) { this.kdKppbbBank = kdKppbbBank; }

  public String getKdBankTunggal() { return kdBankTunggal; }

  public void setKdBankTunggal(String kdBankTunggal) { this.kdBankTunggal = kdBankTunggal; }

  public String getKdBankPersepsi() { return kdBankPersepsi; }

  public void setKdBankPersepsi(String kdBankPersepsi) { this.kdBankPersepsi = kdBankPersepsi; }

  public String getKdTp() { return kdTp; }

  public void setKdTp(String kdTp) { this.kdTp = kdTp; }

  public String getNmWpSppt() { return nmWpSppt; }

  public void setNmWpSppt(String nmWpSppt) { this.nmWpSppt = nmWpSppt; }

  public String getJlnWpSppt() { return jlnWpSppt; }

  public void setJlnWpSppt(String jlnWpSppt) { this.jlnWpSppt = jlnWpSppt; }

  public String getBlokKavNoWpSppt() { return blokKavNoWpSppt; }

  public void setBlokKavNoWpSppt(String blokKavNoWpSppt) { this.blokKavNoWpSppt = blokKavNoWpSppt; }

  public String getRwWpSppt() { return rwWpSppt; }

  public void setRwWpSppt(String rwWpSppt) { this.rwWpSppt = rwWpSppt; }

  public String getRtWpSppt() { return rtWpSppt; }

  public void setRtWpSppt(String rtWpSppt) { this.rtWpSppt = rtWpSppt; }

  public String getKelurahanWpSppt() { return kelurahanWpSppt; }

  public void setKelurahanWpSppt(String kelurahanWpSppt) { this.kelurahanWpSppt = kelurahanWpSppt; }

  public String getKotaWpSppt() { return kotaWpSppt; }

  public void setKotaWpSppt(String kotaWpSppt) { this.kotaWpSppt = kotaWpSppt; }

  public String getKdPosWpSppt() { return kdPosWpSppt; }

  public void setKdPosWpSppt(String kdPosWpSppt) { this.kdPosWpSppt = kdPosWpSppt; }

  public String getNpwpSppt() { return npwpSppt; }

  public void setNpwpSppt(String npwpSppt) { this.npwpSppt = npwpSppt; }

  public String getNoPersilSppt() { return noPersilSppt; }

  public void setNoPersilSppt(String noPersilSppt) { this.noPersilSppt = noPersilSppt; }

  public String getKdKlsTanah() { return kdKlsTanah; }

  public void setKdKlsTanah(String kdKlsTanah) { this.kdKlsTanah = kdKlsTanah; }

  public String getThnAwalKlsTanah() { return thnAwalKlsTanah; }

  public void setThnAwalKlsTanah(String thnAwalKlsTanah) { this.thnAwalKlsTanah = thnAwalKlsTanah; }

  public String getKdKlsBng() { return kdKlsBng; }

  public void setKdKlsBng(String kdKlsBng) { this.kdKlsBng = kdKlsBng; }

  public String getThnAwalKlsBng() { return thnAwalKlsBng; }

  public void setThnAwalKlsBng(String thnAwalKlsBng) { this.thnAwalKlsBng = thnAwalKlsBng; }

  public Date getTglJatuhTempoSppt() { return tglJatuhTempoSppt; }

  public void setTglJatuhTempoSppt(Date tglJatuhTempoSppt) { this.tglJatuhTempoSppt = tglJatuhTempoSppt; }

  public long getLuasBumiSppt() { return luasBumiSppt; }

  public void setLuasBumiSppt(long luasBumiSppt) { this.luasBumiSppt = luasBumiSppt; }

  public long getLuasBngSppt() { return luasBngSppt; }

  public void setLuasBngSppt(long luasBngSppt) { this.luasBngSppt = luasBngSppt; }

  public BigInteger getNjopBumiSppt() { return njopBumiSppt; }

  public void setNjopBumiSppt(BigInteger njopBumiSppt) { this.njopBumiSppt = njopBumiSppt; }

  public BigInteger getNjopBngSppt() { return njopBngSppt; }

  public void setNjopBngSppt(BigInteger njopBngSppt) { this.njopBngSppt = njopBngSppt; }

  public BigInteger getNjopSppt() { return njopSppt; }

  public void setNjopSppt(BigInteger njopSppt) { this.njopSppt = njopSppt; }

  public BigInteger getNjoptkpSppt() { return njoptkpSppt; }

  public void setNjoptkpSppt(BigInteger njoptkpSppt) { this.njoptkpSppt = njoptkpSppt; }

  public BigInteger getNjkpSppt() { return njkpSppt; }

  public void setNjkpSppt(BigInteger njkpSppt) { this.njkpSppt = njkpSppt; }

  public BigInteger getPbbTerhutangSppt() { return pbbTerhutangSppt; }

  public void setPbbTerhutangSppt(BigInteger pbbTerhutangSppt) { this.pbbTerhutangSppt = pbbTerhutangSppt; }

  public BigInteger getFaktorPengurangSppt() { return faktorPengurangSppt; }

  public void setFaktorPengurangSppt(BigInteger faktorPengurangSppt) { this.faktorPengurangSppt = faktorPengurangSppt; }

  public BigInteger getPbbYgHarusDibayarSppt() { return pbbYgHarusDibayarSppt; }

  public void setPbbYgHarusDibayarSppt(BigInteger pbbYgHarusDibayarSppt) { this.pbbYgHarusDibayarSppt = pbbYgHarusDibayarSppt; }

  public char getStatusPembayaranSppt() { return statusPembayaranSppt; }

  public void setStatusPembayaranSppt(char statusPembayaranSppt) { this.statusPembayaranSppt = statusPembayaranSppt; }

  public char getStatusTagihanSppt() { return statusTagihanSppt; }

  public void setStatusTagihanSppt(char statusTagihanSppt) { this.statusTagihanSppt = statusTagihanSppt; }

  public char getStatusCetakSppt() { return statusCetakSppt; }

  public void setStatusCetakSppt(char statusCetakSppt) { this.statusCetakSppt = statusCetakSppt; }

  public Date getTglTerbitSppt() { return tglTerbitSppt; }

  public void setTglTerbitSppt(Date tglTerbitSppt) { this.tglTerbitSppt = tglTerbitSppt; }

  public Date getTglCetakSppt() { return tglCetakSppt; }

  public void setTglCetakSppt(Date tglCetakSppt) { this.tglCetakSppt = tglCetakSppt; }

  public String getNipPencetakSppt() { return nipPencetakSppt; }

  public void setNipPencetakSppt(String nipPencetakSppt) { this.nipPencetakSppt = nipPencetakSppt; }

  // --- inner class

  @Embeddable
  public class SpptSismiopPk implements Serializable {

    private static final long serialVersionUID = SerialConstant.SERIAL_SPPT_SISMIOP_PK;

    private String kdPropinsi;
    private String kdDati2;
    private String kdKecamatan;
    private String kdKelurahan;
    private String kdBlok;
    private String noUrut;
    private String kdJnsOp;
    private String thnPajakSppt;

    //public SpptSismiopPk() {}

    /*
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
    */

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
