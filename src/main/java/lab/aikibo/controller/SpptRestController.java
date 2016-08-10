package lab.aikibo.controller;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigInteger;

import lab.aikibo.model.Sppt;
import lab.aikibo.model.Status;
import lab.aikibo.model.Message;
import lab.aikibo.services.SpptServices;
import lab.aikibo.services.PembayaranServices;

@RestController
public class SpptRestController {

	@Autowired
	SpptServices spptServices;

	@Autowired
	PembayaranServices pembayaranServices;

	static final Logger logger = Logger.getLogger(SpptRestController.class);

	public static Logger getLogger() {
		return logger;
	}

	@RequestMapping("/")
	public String welcome() {
		String info = "Selamat Datang<br>";
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
			sppt = spptServices.getSpptByNopThn(nop, thnPajak);
			logger.debug("Sppt sudah diambil dengan nop : " + sppt.getNop());
		} catch(Exception e) {
			logger.error(e);
		}

		return sppt;
	}

	// single transaction
  @RequestMapping(value="/bayar/{nop}/{thn}/{pokok}/{denda}")
	public Status prosesPembayaran(@PathVariable("nop") String nop,
			@PathVariable("thn") String thnPajak, @PathVariable("pokok") BigInteger pokok,
			@PathVariable("denda") BigInteger denda) {
	  Status status = null;
		try {
			status = pembayaranServices.prosesPembayaran(nop, thnPajak, pokok, denda);
		} catch(Exception e) {
			logger.error(e);
		}

		return status;
	}

	@RequestMapping(value="/info/{user}", method = RequestMethod.GET)
	public Message getMessage(@PathVariable String user) {
		Message msg = new Message(user, "Halo " + user);
		return msg;
	}

}
