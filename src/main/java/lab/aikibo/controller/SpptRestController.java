package lab.aikibo.controller;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import lab.aikibo.constant.StatusRespond;
import lab.aikibo.model.Sppt;
import lab.aikibo.model.SpptJ;
import lab.aikibo.model.Status;
import lab.aikibo.model.StatusInq;
import lab.aikibo.model.StatusTrx;
import lab.aikibo.model.StatusRev;
import lab.aikibo.model.Message;
import lab.aikibo.services.SpptServices;
import lab.aikibo.services.PembayaranServices;
import lab.aikibo.services.ReversalServices;

import org.joda.time.DateTime;

/**
 * Mapping Rest dengan pola berikut :
 *
 * /Spring-Rest-Hibernate-Pbb/sppt/{nop}/{thn}
 *   untuk inquiry data PBB
 *
 * /Spring-Rest-Hibernate-Pbb/bayar/{nop}/{thn}/{tglBayar}/{jamBayar}
 *   untuk melakukan pencatatan pembayaran, request ini akan secara otomatis mencatatkan pembayaran
 *   ke tabel pembayaran_sppt, bila ada kesalahan, maka perlu request reversal
 *   Format tglBayar: DDMMYYYY
 *   Format jamBayar: HH24MI
 *
 * /Spring-Rest-Hibernate-Pbb/reversal/{nop}/{thn}/{ntpd}
 *   melakukan reversal atas pencatatan pembayaran sebelumnya.
 *
 * @author: tamami
 */
@RestController
public class SpptRestController {

	@Autowired
	SpptServices spptServices;

	@Autowired
	PembayaranServices pembayaranServices;

	@Autowired
	ReversalServices reversalServices;

	static final Logger logger = Logger.getLogger(SpptRestController.class);

	public static Logger getLogger() {
		return logger;
	}

	@RequestMapping("/")
	public String welcome() {
		String info = "Selamat Datang<br>";
		info += "gunakan perintah berikut:<br>";
		info += "<dl>";
		info += "<dt>sppt/{nop}/{thn}</dt>";
		info += "<dd>untuk inquiry data per nop</dd>";
		info += "<dt>bayar/{nop}/{thn}/{tglBayar}/{jamBayar}</dt>";
		info += "<dd>untuk melakukan pembayaran, data akan otomatis tersimpan sebagai transaksi pembayaran apabila ini di-request</dd>";
		info += "<dt>reversal/{nop}/{thn}/{ntpd}</dt>";
		info += "<dd>untuk melakukan reversal pembayaran</dd>";
		info += "</dl>";
		return info;
	}

	// single inquiry
	@RequestMapping(value="/sppt/{nop}/{thn}", method = RequestMethod.GET)
	public StatusInq getDataSppt(@PathVariable("nop") String nop, @PathVariable("thn") String thnPajak,
	    HttpServletRequest request) {
		logger.debug("NOP: " + nop);
		logger.debug("THN: " + thnPajak);
		String ipClient = request.getHeader("X-FORWARDED-FOR");
		if(ipClient == null) {
			ipClient = request.getRemoteAddr();
		}
		StatusInq status = null;

		// test thnPajak
		try {
			Integer.parseInt(thnPajak);
		} catch(NumberFormatException ex) {
			status = new StatusInq(StatusRespond.THN_PAJAK_BUKAN_ANGKA, "Tahun Pajak Mengandung Karakter bukan Angka", null);
			return status;
		}

		try {
			status = spptServices.getSpptByNopThn(nop, thnPajak, ipClient);
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
			@PathVariable("jamBayar")String jamBayarString, HttpServletRequest request) {
	  StatusTrx status = null;
		BigInteger pokok = null;
		BigInteger denda = null;
		String ipClient = request.getHeader("X-FORWARDED-FOR");
		if(ipClient == null) {
			ipClient = request.getRemoteAddr();
		}
		logger.debug(" >>> IP CLIENT: " + ipClient);

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
      status = pembayaranServices.prosesPembayaran(nop, thnPajak, tglBayar, ipClient);
		} catch(Exception e) {
			logger.error(e);
			logger.debug(" >>> GetData >>> " + status);
		}

		return status;
	}

	@RequestMapping(value="/reversal/{nop}/{thn}/{ntpd}", method = RequestMethod.GET)
	public StatusRev prosesReversal(@PathVariable("nop") String nop,
	    @PathVariable("thn") String thn, @PathVariable("ntpd") String ntpd,
			HttpServletRequest request) {
    StatusRev status = null;

		// get IP Client
		String ipClient = request.getHeader("X-FORWARDED-FOR");
		if(ipClient == null) {
			ipClient = request.getRemoteAddr();
		}
		logger.debug(" >>> IP CLIENT: " + ipClient);

		try {
			status = reversalServices.prosesReversal(nop, thn, ntpd, ipClient);
		} catch(Exception ex) {
			logger.error(ex);
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
