package com.example.msasample.mono.travelreservation.externalservices.hotel;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class HotelReservationOfHotelMS {
	
	/**
	 * ホテル予約番号。
	 */
	private Long hotelReservationId;
	
	/**
	 * ホテル名称。
	 */
	private String name;
	
	/**
	 * チェックイン日時。
	 */
	private LocalDateTime checkInDateTime;
	
	/**
	 * チェックアウト日時。
	 */
	private LocalDateTime checkOutDateTime;
	
	/**
	 * 部屋番号。
	 */
	private String roomNo;

}
