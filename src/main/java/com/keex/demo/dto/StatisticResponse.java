package com.keex.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Class representing the response for statistic of existing urls.
 *
 * @author Keex
 *
 */
@Setter
@Getter
@ToString
public class StatisticResponse {
	public String shortCode;
	public String destinationUrl;
	public Long numberOfVisits;
}
