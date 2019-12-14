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
 * ホテル情報。
 * 
 * @author ootsuka
 *
 */
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
public class HotelApplicationInfo {

	/**
	 * PK. <br>
	 * 自動採番される。
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.PRIVATE)
	private Long id;

	/**
	 * ホテル名。
	 */
	@NotNull
	private String name;

	/**
	 * チェックイン日。
	 */
	@NotNull
	private LocalDate checkInDate;

	/**
	 * チェックアウト日。
	 */
	@NotNull
	private LocalDate checkOutDate;
}