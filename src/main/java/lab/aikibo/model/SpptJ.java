package lab.aikibo.model;

import java.io.Serializable;
import java.math.BigInteger;

import lab.aikibo.constant.SerialConstant;

public class SpptJ {

	private static final long serialVersionUID = SerialConstant.SERIAL_SPPT;

	private String nop;
	private String thn;
	private String nama;
	private String alamatOp;
	private BigInteger pokok;
	private BigInteger denda;

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

}
