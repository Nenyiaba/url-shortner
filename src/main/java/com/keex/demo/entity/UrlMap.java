package com.keex.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Entity for url mapping
 *
 * @author Keex
 *
 */
@Entity
@Getter
@Setter
public class UrlMap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//Primary key oif the table
	private Long id;

	//Short url generated by service
	private String key;

	@Column(columnDefinition="MEDIUMTEXT")
	//Long url inputted by user
	private String value;

	//Number of times a user has used the short url to visit the long url
	private Long numberOfVisits=0L;

	public UrlMap(){

	}

	public UrlMap(String key, String value) {
		this.key = key;
		this.value = value;
	}
}
