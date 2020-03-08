package com.keex.demo.repository;

import com.keex.demo.entity.UrlMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlMap, Long> {

	UrlMap findByValue(String value);
	UrlMap findByKey(String key);
}
