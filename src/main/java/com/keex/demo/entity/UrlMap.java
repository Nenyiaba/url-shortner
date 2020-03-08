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
	private Long id;
	private String key;
	@Column(columnDefinition="MEDIUMTEXT")
	private String value;
	private Long numberOfVisits=0l;

	public UrlMap(){

	}

	public UrlMap(String key, String value) {
		this.key = key;
		this.value = value;
	}
}
