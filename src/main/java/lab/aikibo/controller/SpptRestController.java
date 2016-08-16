package lab.aikibo.controller;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigInteger;

import javax.servlet.ServletRequest;

import lab.aikibo.constant.StatusRespond;
import lab.aikibo.model.Sppt;
import lab.aikibo.model.SpptJ;
import lab.aikibo.model.Status;
import lab.aikibo.model.StatusInq;
import lab.aikibo.model.StatusTrx;
import lab.aikibo.model.Message;
import lab.aikibo.services.SpptServices;
import lab.aikibo.services.PembayaranServices;

import org.joda.time.DateTime;

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
	public StatusInq getDataSppt(@PathVariable("nop") String nop, @PathVariable("thn") String thnPajak) {
		logger.debug("NOP: " + nop);
		logger.debug("THN: " + thnPajak);
		StatusInq status = null;

		// test thnPajak
		try {
			Integer.parseInt(thnPajak);
		} catch(NumberFormatException ex) {
			status = new StatusInq(StatusRespond.THN_PAJAK_BUKAN_ANGKA, "Tahun Pajak Mengandung Karakter bukan Angka", null);
			return status;
		}

		try {
			status = spptServices.getSpptByNopThn(nop, thnPajak);
		} catch(Exception e) {
			logger.error(e);
		}
		logger.debug(" >>> GetData >>> " + status);
		return status;
	}

	// single transaction
	// format tanggal : DDMMYYYY
	// format jam : HH24MI
  @RequestMapping(value="/bayar/{nop}/{thn}/{tglBayar}/{jamBayar}", method = RequestMethod.GET)
	public StatusTrx prosesPembayaran(@PathVariable("nop") String nop,
			@PathVariable("thn") String thnPajak, @PathVariable("tglBayar") String tglBayarString,
			@PathVariable("jamBayar")String jamBayarString, ServletRequest request) {
	  StatusTrx status = null;
		BigInteger pokok = null;
		BigInteger denda = null;
		String ipClient = request.getHeader("X-FORWARDED-FOR");
		if(ipClient == null) {
			ipClient = request.getRemoteAddr();
		}

		// cek tanggal bayar, tidak boleh lebih baru daripada tanggal saat ini
		int date = Integer.parseInt(tglBayarString.substring(0,2));
		int month = Integer.parseInt(tglBayarString.substring(2,4));
		int year = Integer.parseInt(tglBayarString.substring(4,8));
		int hour = Integer.parseInt(jamBayarString.substring(0,2));
		int min = Integer.parseInt(jamBayarString.substring(2,4));

		DateTime tglBayar = new DateTime(year, month, date, hour, min);
		if(tglBayar.isAfterNow()) {
			// keluarkan pesan error
			status = new StatusTrx(StatusRespond.TGL_JAM_BAYAR_LD_TGL_JAM_KIRIM,
			    "Tanggal atau jam pada saat dibayarkan melebihi tanggal dan jam saat ini", null);
			return status;
		}

    // proses pembayaran
		try {
      status = pembayaranServices.prosesPembayaran(nop, thnPajak, tglBayar);
		} catch(Exception e) {
			logger.error(e);
			logger.debug(" >>> GetData >>> " + status);
		}

		return status;
	}

	@RequestMapping(value="/info/{user}", method = RequestMethod.GET)
	public Message getMessage(@PathVariable String user) {
		Message msg = new Message(user, "Halo " + user);
		return msg;
	}

}
