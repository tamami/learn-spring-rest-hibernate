package lab.aikibo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sppt")
public class RestController {
	
	@Autowired
	SpptService spptService;
	
	static final Logger logger = Logger.getLogger(RestController.class);
	
	@RequestMapping(value="/")

}
