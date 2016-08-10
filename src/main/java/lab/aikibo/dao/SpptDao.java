package lab.aikibo.dao;

import lab.aikibo.model.Sppt;

public interface SpptDao {

	public Sppt getSpptByNopThn(String nop, String thn);
	public Sppt inqSpptByNopThn(String nop, String thn);

}
