package br.com.felipeassis.emptools.controllers;

import java.util.Calendar;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Path;

@Controller
public class HomeController {

	@Inject
	private Result result;

	@Path("/")
	public void index() {
		result.include("msg", "Message from your controller");
		result.include("ano", Calendar.getInstance().get(Calendar.YEAR));
	}
}