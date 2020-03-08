package com.keex.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Class representing the request argument when a long url is requested to be shortened.
 * Has only one argument, which is the long url  that is to be shortened.
 *
 * @author Keex
 *
 */
@Setter
@Getter
@ToString
public class ShortnerRequest {

	public String url;
}
