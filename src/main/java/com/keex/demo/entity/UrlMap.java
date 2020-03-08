package com.keex.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class UrlMap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String key;
	private String value;

	public UrlMap(){

	}

	public UrlMap(String key, String value) {
		this.key = key;
		this.value = value;
	}
}
