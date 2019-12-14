package com.example.msasample.mono.travelreservation.model.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * フライト予約用リポジトリ。
 * 
 * @author ootsuka
 *
 */
@Repository
public interface FlightReservationRepository extends JpaRepository<FlightReservation, Long> {

}
