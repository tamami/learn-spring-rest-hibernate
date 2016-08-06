package lab.aikibo.dao;

import java.util.List;

import lab.aikibo.model.RefKelurahan;

public interface RefKelurahanDao {

  List<RefKelurahan> getDaftarKelurahan(String kdKecamatan);

}
