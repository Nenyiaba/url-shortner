package com.keex.demo.utility;


import com.keex.demo.entity.UrlMap;
import com.keex.demo.repository.UrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlUtility {


	@Autowired
	UrlRepository urlRepository;

	//Map<String, String> map = new HashMap<>();

	public String shorten(String url){

		UrlMap foundUrl = urlRepository.findByValue(url);
		if(foundUrl != null){
			return foundUrl.getKey();
		}

		String generatedUrl = generateShortUrl();
		UrlMap newUrl = new UrlMap(generatedUrl, url);

		urlRepository.save(newUrl);
		return generatedUrl;
		//if(!map.containsKey(generatedUrl)) {
			//map.putIfAbsent(generateShortUrl(), url);
		//}

	}


	private String generateShortUrl(){
		int length = 8;
		boolean useLetters = true;
		boolean useNumbers = true;
		String generatedString =  RandomStringUtils.random(length, useLetters, useNumbers);
		return "www.short.ly/"+generatedString;
	}

	public String getFullUrl(String shortUrl) {
		UrlMap returnedMap = urlRepository.findByKey(shortUrl);
		if(returnedMap == null){
			return null;
		}
		return returnedMap.getValue();
	}
}
