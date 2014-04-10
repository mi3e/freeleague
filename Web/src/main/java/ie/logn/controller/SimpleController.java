package ie.logn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class SimpleController {

	@Controller
	public class LinkController {

		@RequestMapping(value = "/hello")
		public ModelAndView goToHelloPage() {
			ModelAndView view = new ModelAndView();
			view.setViewName("hello"); // name of the jsp-file in the "page"
										// folder

			String str = "MVC Spring is here!";
			view.addObject("message", str); // adding of str object as "message"
											// parameter

			return view;
		}

	}

}
