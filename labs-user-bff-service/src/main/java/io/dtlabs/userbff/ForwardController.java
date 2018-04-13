package io.dtlabs.userbff;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForwardController {

	@RequestMapping(value = {"/register/**", "/contact", "/library", "/library/**"})
	public String forward(){
		return "forward:/";
	}
}