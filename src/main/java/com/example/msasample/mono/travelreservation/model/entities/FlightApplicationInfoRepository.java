package com.example.msasample.mono.travelreservation.model.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * フライト申込用リポジトリ。
 * 
 * @author ootsuka
 *
 */
@Repository
public interface FlightApplicationInfoRepository extends JpaRepository<FlightApplicationInfo, Long> {

}
