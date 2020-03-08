package com.keex.demo.repository;

import com.keex.demo.entity.UrlMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<UrlMap, Long> {

	UrlMap findByValue(String value);
	UrlMap findByKey(String key);
	@Query(value = "SELECT * FROM URL_MAP ORDER BY NUMBER_OF_VISITS DESC", nativeQuery = true)
	List<UrlMap> findTopMappings();
}
