package com.example.msasample.mono.travelreservation.model.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.example.msasample.mono.travelreservation.util.DefaultClock;
import com.example.msasample.mono.travelreservation.util.SpringContext;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 旅程を表す。
 * 
 * @author ootsuka
 *
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
public class Itinerary {

	/**
	 * PK. <br>
	 * 自動採番される。
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.PRIVATE)
	private Long id;

	/**
	 * 更新日時。 <br>
	 * 更新時に自動的に更新される。
	 */
	@Setter(AccessLevel.PRIVATE)
	private LocalDateTime updatedAt;

	/**
	 * 旅行者。
	 */
//	@ElementCollection
//	private List<String> tourists;

	/**
	 * 申込者。
	 */
	private String applicant;

	/**
	 * 予約情報。<br>
	 * <br>
	 * フライト予約、ホテル予約のリスト。
	 */
	@ElementCollection
	private List<HotelReservation> hotelReservations;

	@ElementCollection
	private List<FlightReservation> flightReservations;

	@PrePersist
	public void prePersist() {
		updatedAt = LocalDateTime.now(SpringContext.getBean(DefaultClock.class).getClock());
	}

}
