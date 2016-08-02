package lab.aikibo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lab.aikibo.model.Sppt;
import lab.aikibo.model.Status;
import lab.aikibo.services.SpptServices;

@Controller
@RequestMapping("/sppt")
public class RestController {
	
	@Autowired
	SpptServices spptService;
	
	static final Logger logger = Logger.getLogger(RestController.class);
	
	// sample for transaction
	/*
	@RequestMapping(value="/create", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status trxPbb(@RequestBody ReqTransaksi trx) {
		try {
			spptService.addEntity(trx);
			return new Status(1, "Transaction Successfully!");
		} catch(Exception e) {
			return new Status(0, e.toString());
		}
	}
	*/
	
	// single inquiry
	@RequestMapping(value="/{nop}/{thn}", method = RequestMethod.GET)
	public @ResponseBody Sppt getDataSppt(@PathVariable("nop") String nop, @PathVariable("thn") String thnPajak) {
		Sppt sppt = null;
		try {
			sppt = spptService.getSpptByNopThn(nop, thnPajak);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return sppt;
	}

}
