package lab.aikibo.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="sppt_terhutang")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sppt implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private SpptPK spptPK;
	private String nama;
	private String alamatOp;
	private BigInteger pokok;
	private BigInteger denda;
	
	// --- setter getter
	
	public SpptPK getSpptPK() {
		return spptPK;
	}
	
	public void setSpptPK(SpptPK spptPK) {
		this.spptPK = spptPK;
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
	
	
	// --- inner class
	@Embeddable
	public static class SpptPK implements Serializable {
		private static final long serialVersionUID = 1L;
		
		protected String nop;
		protected String thnPajak;
		
		public SpptPK() {}
		
		public SpptPK(String nop, String thnPajak) {
			this.nop = nop;
			this.thnPajak = thnPajak;
		}
		
		public String getNop() {
			return nop;
		}
		
		public String thnPajak() {
			return thnPajak;
		}
		
		public void setNop(String nop) {
			this.nop = nop;
		}
		
		public void setThnPajak(String thnPajak) {
			this.thnPajak = thnPajak;
		}
		
	}

}
