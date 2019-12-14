package com.example.msasample.mono.travelreservation.model.entities;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * 申込情報。
 * 
 * @author ootsuka
 *
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
public class ApplicationInfo {

	/**
	 * PK. <br>
	 * 自動採番される。
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.PRIVATE)
	private Long id;

	/**
	 * 旅行者。
	 */
	@NotEmpty
	@ElementCollection
	private List<String> tourists;

	/**
	 * 申込者
	 */
	@NotNull
	private String applicant;

	/**
	 * フライト申込情報。
	 */
	@NotEmpty
	@ElementCollection
	private List<FlightApplicationInfo> desiredFlights;

	/**
	 * ホテル申込情報。
	 */
	@NotEmpty
	@ElementCollection
	private List<HotelApplicationInfo> desiredHotels;
}
