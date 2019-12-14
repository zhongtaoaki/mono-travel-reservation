package com.example.msasample.mono.travelreservation.model.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * フライト申込情報。
 * 
 * @author ootsuka
 *
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
public class FlightApplicationInfo {

	/**
	 * PK. <br>
	 * 自動採番される。
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.PRIVATE)
	private Long id;

	/**
	 * フライト名。
	 */
	@NotNull
	public @NotNull String flightName;
	/**
	 * 出発日。
	 */
	@NotNull
	public @NotNull LocalDate departureDate;

}