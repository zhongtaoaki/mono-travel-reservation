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
 * ホテルの予約情報を表す。
 * 
 * @author ootsuka
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
@AttributeOverride(name = "ID", column = @Column(name = "hotel_reservation_id"))
public class HotelReservation extends Reservation {

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

	@Override
	public LocalDateTime getStartDateTime() {
		return checkInDateTime;
	}

	@Override
	public LocalDateTime getEndDateTime() {
		return checkOutDateTime;
	}

}
