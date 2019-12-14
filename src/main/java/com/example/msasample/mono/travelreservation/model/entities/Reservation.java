package com.example.msasample.mono.travelreservation.model.entities;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@MappedSuperclass
public abstract class Reservation {

	/**
	 * PK. <br>
	 * 自動採番される。
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.PRIVATE)
	private Long id;

	/**
	 * 開始日時。<br>
	 * <br>
	 * フライトであれば出発日時（Departure date time）、 ホテル予約であればチェックイン日時（Check in date time）を表す。
	 * 
	 */
	public abstract LocalDateTime getStartDateTime();

	/**
	 * 終了日時。<br>
	 * <br>
	 * フライトであれば到着日時（Arrival date time）、 ホテル予約であればチェックイン日時（Check out date time）を表す。
	 */
	public abstract LocalDateTime getEndDateTime();

}
