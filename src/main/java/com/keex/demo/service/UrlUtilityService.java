package com.keex.demo.service;


import com.keex.demo.dto.StatisticResponse;
import com.keex.demo.entity.UrlMap;
import com.keex.demo.repository.UrlRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UrlUtilityService {

	@Autowired
	private UrlRepository urlRepository;

	/**
	 * Method for shortening urls.
	 *
	 * @param url the url to be shortened
	 * @return string code representing the url.
	 */
	public String shorten(String url){
		UrlMap foundUrl = urlRepository.findByValue(url);//Checking if the url exists first
		if(foundUrl != null){
			return foundUrl.getKey();//Return corresponding short url if long url exists already
		}
		//Otherwise, generate new short url
		String generatedUrl = generateShortUrl();
		UrlMap newUrlMap = new UrlMap(generatedUrl, url);
		urlRepository.save(newUrlMap);
		return generatedUrl;
	}

	/**
	 * Method for increasing the count of visited urls.
	 * Each time a short url is used to access a long url, the count is increased by 1.
	 *
	 * @param urlCode the url code to be used to find url record.
	 * @return returns true if completed successfully.
	 */
	public boolean increaseVisitCount(String urlCode){
		UrlMap returnedMap = urlRepository.findByKey(urlCode);
		returnedMap.setNumberOfVisits(returnedMap.getNumberOfVisits()+1);
		urlRepository.save(returnedMap);
		return true;
	}

	/**
	 *
	 * @return List of all existing url mappings.
	 */
	public List<UrlMap> getAllMappings(){
		return urlRepository.findAll();
	}

	/**
	 *
	 * @return List of top three most visited url mappings.
	 */
	public List<StatisticResponse> getTopMappings(){
		List<UrlMap> allMappings = urlRepository.findTopMappings();//Return a list of all mappings but sorted by descending count
		List<StatisticResponse> mostVisited = new ArrayList<>();
		int topThreeCounter = 0;
		for(UrlMap map : allMappings){
			StatisticResponse statisticResponse = new StatisticResponse();
			statisticResponse.setDestinationUrl(map.getValue());
			statisticResponse.setShortCode(map.getKey());
			statisticResponse.setNumberOfVisits(map.getNumberOfVisits());
			mostVisited.add(statisticResponse);
			topThreeCounter++;
			if(topThreeCounter == 3){
				break;
			}
		}
		return mostVisited;
	}

	/**
	 * Method for generating short url code using RandomStringUtils.
	 *
	 * @return shortened url code.
	 */
	private String generateShortUrl(){
		int length = 8;
		boolean useLetters = true;
		boolean useNumbers = true;
		return RandomStringUtils.random(length, useLetters, useNumbers);
	}

	/**
	 * Method for retrieving full url using the short url code
	 *
	 * @param shortUrlCode the url code to be used to find url record.
	 * @return If short code exists,return full url, otherwise, return null.
	 */
	public String getFullUrl(String shortUrlCode) {
		UrlMap returnedMap = urlRepository.findByKey(shortUrlCode);
		if(returnedMap == null){
			return null;
		}
		return returnedMap.getValue();
	}
}
