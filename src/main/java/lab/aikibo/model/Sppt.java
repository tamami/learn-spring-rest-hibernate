package lab.aikibo.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.IdClass;
import javax.persistence.Id;
import javax.persistence.AttributeOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;

import lab.aikibo.constant.SerialConstant;

@Entity
@Table(name="sppt_terhutang_matre")
@IdClass(Sppt.SpptPK.class)
public class Sppt implements Serializable {

	private static final long serialVersionUID = SerialConstant.SERIAL_SPPT;

	@Id
	@Column(name="NOP", columnDefinition="char")
  private String nop;
	@Id
	@Column(name="THN", columnDefinition="char")
	private String thn;
	@Column(name="NAMA")
	private String nama;
	@Column(name="ALAMAT_OP")
	private String alamatOp;
	@Column(name="POKOK")
	private BigInteger pokok;
	@Column(name="DENDA")
	private BigInteger denda;



	private String statusPembayaran;

	// --- setter getter

	public String getNop() {
		return nop;
	}

	public void setNop(String nop) {
		this.nop = nop;
	}

	public String getThn() {
		return thn;
	}

	public void setThn(String thn) {
		this.thn = thn;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getAlamatOp() {
		return alamatOp;
	}

	public void setAlamatOp(String alamatOp) {
		this.alamatOp = alamatOp;
	}

	public BigInteger getPokok() {
		return pokok;
	}

	public void setPokok(BigInteger pokok) {
		this.pokok = pokok;
	}

	public BigInteger getDenda() {
		return denda;
	}

	public void setDenda(BigInteger denda) {
		this.denda = denda;
	}

	public String getStatusPembayaran() {
		return statusPembayaran;
	}

	public void setStatusPembayaran(String statusPembayaran) {
		this.statusPembayaran = statusPembayaran;
	}


	// --- inner class
	@Embeddable
	public static class SpptPK implements Serializable {
		private static final long serialVersionUID = SerialConstant.SERIAL_SPPT_PK;

		protected String nop;
		protected String thn;

		public SpptPK() {}

		public SpptPK(String nop, String thn) {
			this.nop = nop;
			this.thn = thn;
		}

		public String getNop() {
			return nop;
		}

		public String getThn() {
			return thn;
		}

		public void setNop(String nop) {
			this.nop = nop;
		}

		public void setThn(String thn) {
			this.thn = thn;
		}

	}

}
