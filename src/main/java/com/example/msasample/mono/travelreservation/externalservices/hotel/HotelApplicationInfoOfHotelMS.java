package com.example.msasample.mono.travelreservation.externalservices.hotel;

import java.time.LocalDate;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * ホテル予約のリクエスト情報。
 * 
 * @author zhong
 *
 */
@Data
@SuperBuilder
public class HotelApplicationInfoOfHotelMS {
	/**
	 * ホテル名。
	 */
	private String name;

	/**
	 * チェックイン日。
	 */
	private LocalDate checkInDate;

	/**
	 * チェックアウト日。
	 */
	private LocalDate checkOutDate;
}
