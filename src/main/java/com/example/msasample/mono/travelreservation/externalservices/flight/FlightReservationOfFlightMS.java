package com.example.msasample.mono.travelreservation.externalservices.flight;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * フライトの予約情報を表す。
 * 
 * @author ootsuka
 *
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class FlightReservationOfFlightMS {

	/**
	 * フライト予約番号。
	 */
	private Long flightReservationId;

	/**
	 * フライト名。
	 */
	private String flightName;

	/**
	 * 出発日時。
	 */
	private LocalDateTime departureDateTime;

	/**
	 * 到着日時。
	 */
	private LocalDateTime arrivalDateTime;

	/**
	 * 出発空港。
	 */
	private String departureAirport;

	/**
	 * 到着空港。
	 */
	private String arrivalAirport;

	/**
	 * 座席番号。
	 */
	private String seatNo;

}
