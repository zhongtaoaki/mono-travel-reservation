package com.example.msasample.mono.travelreservation.model.entities;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * フライトの予約情報を表す。
 * 
 * @author ootsuka
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
@AttributeOverride(name = "ID", column = @Column(name = "flight_reservation_id"))
public class FlightReservation extends Reservation {

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

	@Override
	public LocalDateTime getStartDateTime() {
		return departureDateTime;
	}

	@Override
	public LocalDateTime getEndDateTime() {
		return arrivalDateTime;
	}

}
