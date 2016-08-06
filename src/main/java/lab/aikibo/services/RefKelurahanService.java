package lab.aikibo.services;

import java.util.List;

import lab.aikibo.model.RefKelurahan;

public interface RefKelurahanService {
  List<RefKelurahan> getDaftarKelurahan(String kdKecamatan);
}
