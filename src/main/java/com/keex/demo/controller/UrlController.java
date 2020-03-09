package com.keex.demo.controller;


import com.keex.demo.dto.ShortnerFetch;
import com.keex.demo.dto.ShortnerRequest;
import com.keex.demo.dto.StatisticResponse;
import com.keex.demo.entity.UrlMap;
import com.keex.demo.service.UrlUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Base controller class for all incoming requests.
 *
 * @author Keex
 *
 */
@RestController
@RequestMapping("url")
public class UrlController {

	@Autowired
	private UrlUtilityService urlUtilityService;


	@GetMapping("")
	public String check(){
		return "Url Service Up and Running";
	}

	//Generating short url from long url
	@PostMapping("/shorten")
	public String shortnUrl(@RequestBody ShortnerRequest shortnerRequest){
		return urlUtilityService.shorten(shortnerRequest.url);
	}

	@GetMapping("/all")
	public List<UrlMap> getAll(){
		List<UrlMap> allMappings = urlUtilityService.getAllMappings();
		return allMappings;
	}

	//Mapping the short url tio the long url using HTTP GET
	@GetMapping("/fetch/{code}")
	public ModelAndView redirect(@PathVariable("code") String code, HttpServletResponse resp) throws IOException {
		String fullUrl = urlUtilityService.getFullUrl(code);
		if(!(fullUrl == null)){
			urlUtilityService.increaseVisitCount(code);
			return new ModelAndView("redirect:"+fullUrl);
		}
		else{
			resp.sendError( HttpServletResponse.SC_NOT_FOUND );
			return null;
		}
	}

	//Mapping the short url tio the long url using HTTP POST
	@PostMapping("/fetch")
	public ModelAndView redirectPost(@RequestBody ShortnerFetch shortnerFetch, HttpServletResponse resp) throws IOException {
		String fullUrl = urlUtilityService.getFullUrl(shortnerFetch.urlCode);
		if(!(fullUrl == null)){
			urlUtilityService.increaseVisitCount(shortnerFetch.urlCode);
			return new ModelAndView("redirect:"+fullUrl);
		}
		else{
			resp.sendError( HttpServletResponse.SC_NOT_FOUND );
			return null;
		}
	}

	@GetMapping("/statistic/most-visited")
	public List<StatisticResponse> mostVisited(){
		List<StatisticResponse> mostVisited = urlUtilityService.getTopMappings();
		return mostVisited;
	}

	@GetMapping("/statistic/count")
	public String totalCount(){
		int size = urlUtilityService.getAllMappings().size();
		return "TOTAL NUMBER OF CURRENT MAPPINGS - "+String.valueOf(size);
	}
}
