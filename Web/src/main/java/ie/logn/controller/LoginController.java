package ie.logn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({ "/login" })
public class LoginController {

	@RequestMapping({ "/login" })
	public String doLogin(Model model) {
		return "login";
	}
}
