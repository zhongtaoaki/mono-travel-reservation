package com.example.msasample.mono.travelreservation.model.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.msasample.mono.travelreservation.externalservices.hotel.HotelReservationOfHotelMS;

/**
 * ホテル申込用リポジトリ。
 * 
 * @author ootsuka
 *
 */
@Repository
public interface HotelApplicationInfoRepository extends JpaRepository<HotelReservationOfHotelMS, Long> {

}
