package com.keex.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * This class contains the custom error page to be displayed as opposed to the general white label error.
 *
 * @author Keex
 *
 */
@ApiIgnore
@RestController
public class CustomErrorController implements ErrorController {

	@RequestMapping("/error")
	public String handleError() {
		//do something like logging
		return "<h1>ERROR:</h1> <h3>NO SUCH URL FOUND</h3>.";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
