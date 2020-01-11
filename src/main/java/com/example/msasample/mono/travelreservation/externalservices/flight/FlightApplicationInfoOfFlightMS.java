package com.example.msasample.mono.travelreservation.externalservices.flight;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * フライト予約のリクエスト情報。
 * 
 * @author zhong
 *
 */
@Data
@SuperBuilder
public class FlightApplicationInfoOfFlightMS {

	/**
	 * フライト名。
	 */
	public String flightName;

	/**
	 * 出発日。
	 */
	public LocalDate departureDate;

	/**
	 * 旅行者。
	 */
	private List<String> tourists;

}
