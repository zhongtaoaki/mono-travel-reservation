package com.example.msasample.mono.travelreservation.model.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 申込情報用リポジトリ。
 * 
 * @author ootsuka
 *
 */
@Repository
public interface ApplicationInfoRepository extends JpaRepository<ApplicationInfo, Long> {

}
