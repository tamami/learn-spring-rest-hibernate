package lab.aikibo.controller;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import lab.aikibo.model.Sppt;
import lab.aikibo.model.Status;
import lab.aikibo.model.Message;
import lab.aikibo.services.SpptServices;
import lab.aikibo.services.RefKelurahanService;
import lab.aikibo.model.RefKelurahan;

import java.util.List;

@RestController
public class SpptRestController {

	@Autowired
	SpptServices spptService;
	@Autowired
	RefKelurahanService refKelurahanService;

	static final Logger logger = Logger.getLogger(SpptRestController.class);

	public static Logger getLogger() {
		return logger;
	}

	@RequestMapping("/")
	public String welcome() {
		String info = "Selamat Datang\n";
		info += "gunakan perintah berikut:";
		return info;
	}

	// single inquiry
	@RequestMapping(value="/sppt/{nop}/{thn}", method = RequestMethod.GET)
	public Sppt getDataSppt(@PathVariable("nop") String nop, @PathVariable("thn") String thnPajak) {
		logger.debug("NOP: " + nop);
		logger.debug("THN: " + thnPajak);
		Sppt sppt = null;
		try {
			sppt = spptService.getSpptByNopThn(nop, thnPajak);
			logger.debug("Sppt sudah diambil dengan nop : " + sppt.getNop());
		} catch(Exception e) {
			logger.debug(e);
		}

		return sppt;
	}

	@RequestMapping(value="/daftarKelurahan/{kdKecamatan}", method = RequestMethod.GET)
	public List<RefKelurahan> getDaftarKelurahan(@PathVariable("kdKecamatan")String kdKecamatan) {
		List<RefKelurahan> result = null;
		try {
			result = refKelurahanService.getDaftarKelurahan(kdKecamatan);
		} catch(Exception e) {
			logger.debug(e);
		}
		return result;
	}

	@RequestMapping(value="/info/{user}", method = RequestMethod.GET)
	public Message getMessage(@PathVariable String user) {
		Message msg = new Message(user, "Halo " + user);
		return msg;
	}

}
