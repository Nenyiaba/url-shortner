package com.keex.demo.controller;


import com.keex.demo.dto.Request;
import com.keex.demo.utility.UrlUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("url")
public class UrlController {

	@Autowired
	public UrlUtility urlUtility;


	@GetMapping("")
	public String check(){
		return "Url Service Up and Running";
	}


	@PostMapping("/shorten")
	public String shortnUrl(@RequestBody Request request){
		return urlUtility.shorten(request.url);
	}


	@PostMapping("/retrieve")
	public ResponseEntity getFullUrl(@RequestBody Request request){
		String returnedUrl = urlUtility.getFullUrl(request.url);
		if(returnedUrl == null){
			return new ResponseEntity<String>("Unknown short url",HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(returnedUrl, HttpStatus.OK);
	}

}
