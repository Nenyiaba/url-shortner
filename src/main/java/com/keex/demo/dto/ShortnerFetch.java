package com.keex.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Class representing the request argument when a short url is inputted.
 * this is what you're expecting from the front end i.e the url code from the user ***
 * Has only one argument, which is the short url code that is to be mapped and redirected.
 *
 * @author Keex
 *
 */
@Setter
@Getter
@ToString
public class ShortnerFetch {
	public String urlCode;
}
