package com.keex.demo.repository;

import com.keex.demo.entity.UrlMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UrlRepository extends JpaRepository<UrlMap, Long> {

	//Select a short url from the database using the long url
	UrlMap findByValue(String value);

	//Select a long url from the database using the short url
	UrlMap findByKey(String key);


	//Select all urls from the database ordered by the number of visits in a descending order.
	@Query(value = "SELECT * FROM URL_MAP ORDER BY NUMBER_OF_VISITS DESC", nativeQuery = true)
	List<UrlMap> findTopMappings();
}
